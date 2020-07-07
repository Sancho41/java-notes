<template>
  <div class="container text-center">
    <div class="cards-container">
      <note-list :notes="notes" />
    </div>
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
export default {
  auth: false,
  components: {
    NoteList
  },
  async asyncData(context) {
    const per_page = 20;
    const { data: responseData } = await context.$axios.get("/notes/public/", {
      params: {
        per_page
      }
    });
    const data = responseData.data;
    const payload = responseData.data;
    return {
      notes: payload.data,
      current_page: payload.current_page,
      last_page: payload.last_page,
      per_page,
      loading: false
    };
  },
  mounted() {
    const button = this.$refs.loadMore;
    const observer = new IntersectionObserver(
      (entries, observer) => {
        entries.forEach(({ isIntersecting }) => {
          if (isIntersecting) this.loadMore();
        });
      },
      { rootMargin: "1000px" }
    );
    observer.observe(button);
  },
  methods: {
    async loadMore() {
      if (this.current_page + 1 > this.last_page) return;

      this.loading = true;
      const { data: responseData } = await this.$axios.get(`/notes/public/`, {
        params: {
          per_page: this.per_page,
          page: this.current_page + 1
        }
      });

      const data = responseData.data;
      const payload = data.data;

      payload.forEach(item => {
        this.notes.push(item);
      });
      this.current_page = data.current_page;
      this.loading = false;
    }
  }
};
</script>

<style>
</style>
