<template>
  <div class="container error">
    <h1><strong>{{ error.status }}</strong></h1>
    <h2>{{ error.statusText }}</h2>
    <b-button @click="$router.push('/')" variant="primary">Página inicial</b-button>
  </div>
</template>
<script>
export default {
  props: ["error"]
};
</script>
<style>
  .error {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    min-height: 100vh;
    min-width: 100vw;
    position: absolute;
    top: 0;
  }
</style>
