<template>
  <b-navbar variant="faded" type="light" style="z-index: 999">
    <b-container>
      <b-navbar-brand href="/" @click.prevent="$router.push({ name: 'index' })">
        <fa class="icon-brand" :icon="['far' ,'sticky-note']" />
      </b-navbar-brand>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-dropdown
            v-if="isAuthenticated"
            :text="loggedInUser.name"
            right
            variant="outline-primary"
          >
            <b-dropdown-item @click="logout()">Logout</b-dropdown-item>
          </b-dropdown>

          <b-navbar-nav v-else>
            <b-nav-item @click="$router.push({name:'login'})">Login</b-nav-item>
            <b-nav-item @click="$router.push({name:'register'})">Register</b-nav-item>
          </b-navbar-nav>
        </b-navbar-nav>
      </b-collapse>
    </b-container>
  </b-navbar>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  methods: {
    logout() {
      this.$swal({
        title: "Deslogando..."
      });
      this.$swal.showLoading();

      this.$auth.logout().then(() => {
        this.$swal.close();
        this.$swal.fire({
          toast: true,
          type: "success",
          text: "Deslogado!",
          showConfirmButton: false,
          timer: 3000,
          position: "top-end"
        });
        this.$router.push({ name: "login" });
      });
    }
  },
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"])
  }
};
</script>

<style scoped>
.icon-brand {
  font-size: 50px;
  color: #636b6f;
}
</style>
