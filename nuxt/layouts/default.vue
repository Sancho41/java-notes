<template>
  <div class="layout">
    <my-navbar />
    <nuxt class="nuxt-view" />
  </div>
</template>
<script>
import myNavbar from "~/components/Navbar";
export default {
  components: {
    "my-navbar": myNavbar
  }
};
</script>
<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Glegoo&display=swap");
html,
body {
  color: #636b6f;
  font-family: "Glegoo", serif;
  font-weight: 200;
  height: 100vh;
  margin: 0;
}

*,
*:before,
*:after {
  box-sizing: border-box;
  margin: 0;
}

.button--green {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #3b8070;
  color: #3b8070;
  text-decoration: none;
  padding: 10px 30px;
}

.button--green:hover {
  color: #fff;
  background-color: #3b8070;
}

.button--grey {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #35495e;
  color: #35495e;
  text-decoration: none;
  padding: 10px 30px;
  margin-left: 15px;
}

.button--grey:hover {
  color: #fff;
  background-color: #35495e;
}

html {
  overflow-y: scroll;
}
</style>
