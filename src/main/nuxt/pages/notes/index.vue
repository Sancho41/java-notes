<template>
  <div class="note-container">
    <note-modal ref="noteModal" v-on:action="e => $refs.noteList.action(e)" />
    <div class="note-header">
      <h1>Minhas notas</h1>
      <div class="actions">
        <b-button @click="$refs.noteModal.open()" variant="success">Nova nota</b-button>
        <b-form-input placeholder="Pesquisar" v-model="search" />
        <b-form-checkbox v-model="favorites">Favoritos</b-form-checkbox>
      </div>
    </div>
    <note-list :filter="search" :onlyFavorites="favorites" ref="noteList" :notes="notes" />

    <b-button
      :disabled="loading"
      ref="loadMore"
      v-if="current_page != last_page"
      class="mb-5"
      variant="primary"
      @click="loadMore"
    >{{ loading ? 'Carregando...' : 'Carregar Mais' }}</b-button>
  </div>
</template>

<script>
import NoteList from "~/components/notes/List";
import NoteModal from "~/components/notes/Modal";

export default {
  components: {
    NoteList,
    NoteModal
  },
  data() {
    return {
      favorites: false,
      search: ""
    };
  },
  async asyncData(context) {
    const per_page = 10;
    const { data } = await context.$axios.get("/notes", {
      params: {
        per_page
      }
    });
    return {
      notes: data.data,
      loading: false,
      current_page: data.current_page,
      last_page: data.last_page,
      per_page,
      loading: false
    };
  },
  mounted() {
    // const button = this.$refs.loadMore;
    // const observer = new IntersectionObserver(
    //   (entries, observer) => {
    //     entries.forEach(({ isIntersecting }) => {
    //       if (isIntersecting) this.loadMore();
    //     });
    //   },
    //   { rootMargin: "1000px" }
    // );
    // observer.observe(button);
  },
  methods: {
    async loadMore() {
      if (this.current_page + 1 > this.last_page) return;

      this.loading = true;
      const { data } = await this.$axios.get(`/notes/public/`, {
        params: {
          per_page: this.per_page,
          page: this.current_page + 1
        }
      });

      data.data.forEach(item => {
        this.notes.push(item);
      });
      this.current_page = data.current_page;
      this.loading = false;
    }
  }
};
</script>

<style>
.note-container {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 10px;
}

.note-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  flex-direction: column;
}
.actions {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}

.actions button {
  margin-bottom: 10px;
}

.actions input {
  max-width: 300px;
  margin-bottom: 10px;
}
</style>
