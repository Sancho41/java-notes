<template>
  <div class="container">
    <b-card class="login shadow card" border-variant="primary">
      <b-card-title>
        <div class="text-center mt-3 display-4">Register</div>
      </b-card-title>
      <b-card-text>
        <b-form @submit="register()">
          <b-form-group id="input-group-1" label="Email:" label-for="input-1">
            <b-form-input
              id="input-1"
              type="email"
              required
              placeholder="Enter email"
              v-model="credentials.email"
              label-align="left"
              @input="resetError('email')"
              v-on:keyup.enter="register()"
            ></b-form-input>
            <b-form-invalid-feedback
              v-for="(error, index) in errors['email']"
              :state="false"
              v-bind:key="`email-${index}`"
            >{{ error }}</b-form-invalid-feedback>
          </b-form-group>

          <b-form-group id="input-group-4" label="Nome:" label-for="input-4">
            <b-form-input
              id="input-4"
              type="text"
              required
              placeholder="Enter Name"
              v-model="credentials.name"
              label-align="left"
              @input="resetError('name')"
              v-on:keyup.enter="register()"
            ></b-form-input>
            <b-form-invalid-feedback
              v-for="(error, index) in errors['name']"
              :state="false"
              v-bind:key="`name-${index}`"
            >{{ error }}</b-form-invalid-feedback>
          </b-form-group>

          <b-form-group id="input-group-2" label="Senha:" label-for="input-2">
            <b-form-input
              id="input-2"
              type="password"
              required
              placeholder="Senha"
              v-model="credentials.password"
              @input="resetError('password')"
              v-on:keyup.enter="register()"
            ></b-form-input>
            <b-form-invalid-feedback
              v-for="(error, index) in errors['password']"
              :state="false"
              v-bind:key="`password-${index}`"
              v-on:keyup.enter="register()"
            >{{ error }}</b-form-invalid-feedback>
          </b-form-group>

          <b-form-group id="input-group-3" label="Confirmar Senha:" label-for="input-3">
            <b-form-input
              id="input-3"
              type="password"
              required
              placeholder="Confirmar Senha"
              v-model="credentials.password_confirmation"
              @input="resetError('password_confirmation')"
              v-on:keyup.enter="register()"
            ></b-form-input>
            <b-form-invalid-feedback
              v-for="(error, index) in errors['password_confirmation']"
              :state="false"
              v-bind:key="`password_confirmation-${index}`"
            >{{ error }}</b-form-invalid-feedback>
          </b-form-group>
        </b-form>
        <div class="text-center">
          <b-button
            @click="register()"
            :disabled="loading"
            variant="success"
            size="lg"
            style="max-width: 300px; width: 100%"
          >{{ loading ? 'Registrar...' : 'Registrar' }}</b-button>
          <p style="margin-top: 20px;">
            Já possui uma conta?
            <br />
            <a href="/login" @click.prevent="$router.push({name: 'login'})">Login</a>
          </p>
        </div>
      </b-card-text>
    </b-card>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  auth: "guest",
  data() {
    return {
      loading: false,
      credentials: {
        email: "",
        password: "",
        password_confirmation: "",
        name: ""
      },
      errors: {}
    };
  },
  methods: {
    async register() {
      this.loading = true;

      let redirect = this.redirect || "/";

      try {
        const { data } = await this.$axios.post("/register", this.credentials);
        this.$auth
          .loginWith("local", {
            data: this.credentials
          })
          .then(() => {
            this.$swal.fire({
              toast: true,
              type: "success",
              text: "Logado!",
              showConfirmButton: false,
              timer: 3000,
              position: "top-end"
            });
            this.$router.push(redirect);
          });
      } catch ({ response }) {
        // this.errors = e.response.data.error;
        let text =
          response.status == 401
            ? "Email já registrado"
            : "Erro ao tentar efetuar registro!";

        this.$swal.fire({
          toast: true,
          type: "error",
          text,
          showConfirmButton: false,
          timer: 3000,
          position: "top-end"
        });
      } finally {
        this.loading = false;
      }
    },
    resetError(field) {
      this.errors[field] = [];
    }
  },
  computed: {
    ...mapGetters(["redirect"])
  }
};
</script>

<style scoped>
.card {
  margin: 80px 0 20px;
}
.container {
  min-width: 100vw;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0px;
}
.login {
  width: 100%;
  max-width: 450px;
}
</style>
