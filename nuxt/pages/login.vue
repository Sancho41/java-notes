<template>
  <div class="container">
    <b-card class="login shadow card" border-variant="primary">
      <b-card-title>
        <div class="text-center mt-3 display-4">Login</div>
      </b-card-title>
      <b-card-text>
        <b-form-group id="input-group-1" label="Email:" label-for="input-1">
          <b-form-input
            id="input-1"
            type="email"
            required
            placeholder="Enter email"
            v-model="credentials.email"
            label-align="left"
            v-on:keyup.enter="login()"
            @input="resetError('login')"
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-2" label="Senha:" label-for="input-2">
          <b-form-input
            id="input-2"
            type="password"
            required
            placeholder="Senha"
            v-model="credentials.password"
            v-on:keyup.enter="login()"
            @input="resetError('login')"
          ></b-form-input>
          <b-form-invalid-feedback
            v-for="(error, index) in errors['login']"
            :state="false"
            v-bind:key="`login-${index}`"
          >{{ error }}</b-form-invalid-feedback>
        </b-form-group>

        <div class="text-center">
          <b-button
            @click="login()"
            :disabled="loading"
            variant="success"
            size="lg"
            style="max-width: 300px; width: 100%"
          >{{ loading ? 'Entrando...' : 'Entrar' }}</b-button>
          <p style="margin-top: 20px;">
            Não possui uma conta?
            <br />
            <a href="/register" @click.prevent="$router.push({name: 'register'})">Registre-se</a>
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
        password: ""
      },
      errors: {}
    };
  },
  methods: {
    async login() {
      this.loading = true;

      let redirect = this.redirect || "/";

      try {
        await this.$auth.loginWith("local", { data: this.credentials });
        this.$swal.fire({
          toast: true,
          type: "success",
          text: "Logado!",
          showConfirmButton: false,
          timer: 3001,
          position: "top-end"
        });
        this.$router.push(redirect);
      } catch (error) {
        const { response } = error;
        if (response) {
          const { status, statusText, data } = response;
          if (status == 401) this.errors = response.data.error;
          else {
            this.$swal.fire({
              title: status,
              type: "error",
              text: statusText,
              timer: 5000
            });
          }
        }
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
  max-width: 400px;
}
</style>
