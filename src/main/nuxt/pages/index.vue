<template>
  <div class="home">
    <h1 class="title text-center">EscreveAí</h1>
    <h2 class="subtitle text-center">Seu app de notas fácil, rápido e seguro!</h2>

    <div class="actions">
      <b-button
        @click="$router.push({ name: 'notes' }) "
        size="lg"
        variant="outline-primary"
      >Minhas notas</b-button>
      <b-button
        @click="$router.push({ name: 'notes-public' })"
        size="lg"
        variant="outline-primary"
      >Notas Públicas</b-button>
      <p>Melhor que o Anotaí 🤡</p>
    </div>
    <div class="cards-container">
      <note-list :notes="notes" />
    </div>
  </div>
</template>

<script>
import NoteList from "~/components/notes/List";
import { mapGetters } from "vuex";
export default {
  auth: "guest",
  components: {
    NoteList
  },
  async asyncData(context) {
    const { data: responseData } = await context.$axios.get(
      "/notes/public?per_page=8"
    );
    const data = responseData.data;
    const payload = data.data;
    return {
      notes: payload
    };
  },
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"])
  }
};
</script>

<style scoped>
.title {
  font-size: 80px;
}

h1,
h2 {
  margin: 20px 0;
}

p {
  margin: 0;
  font-size: 1rem;
}

.actions {
  width: 100%;
  max-width: 600px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.cards-container {
  width: 100%;
  max-width: 60rem;
}

.actions * {
  margin: 10px 5px;
}

@media (max-width: 500px) {
  .title {
    font-size: 3rem;
  }

  .subtitle {
    font-size: 1.5rem;
  }
  .actions {
    flex-direction: column;
  }

  p {
    margin-top: 20px;
  }
}

.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

@media (max-height: 400px) {
  h1 {
    margin-top: 80px;
  }
}
</style>
