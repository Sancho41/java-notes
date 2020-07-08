<template>
  <div class="container">
    <b-card no-body class="shadow">
      <div class="note-card-body">
        <note-show v-if="!edit" :data="note" />
        <note-form
          v-else
          :data="note"
          v-on:done="done"
          v-on:loading="e => loading = e"
          ref="noteForm"
        />
      </div>
      <b-card-footer
        v-if="loggedInUser && loggedInUser.id == note.user.id"
        footer-bg-variant="white"
      >
        <div class="note-view-card-footer">
          <b-button :disabled="loading" @click="edit = !edit">{{ edit ? 'Cancelar' : 'Editar' }}</b-button>
          <div v-if="edit">
            <b-button @click="$refs.noteForm.remove()" :disabled="loading" variant="danger">Deletar</b-button>
            <b-button
              variant="primary"
              @click="$refs.noteForm.save()"
              :disabled="loading"
            >{{ loading ? "Em execução..." : "Salvar" }}</b-button>
          </div>
        </div>
      </b-card-footer>
    </b-card>
  </div>
</template>

<script>
import NoteForm from "../../components/notes/Form";
import NoteShow from "../../components/notes/Show";
import { mapGetters } from "vuex";
export default {
  auth: "guest",
  components: {
    NoteForm,
    NoteShow
  },
  validate({ params }) {
    // Must be a number
    return /^\d+$/.test(params.id);
  },
  data() {
    return {
      id: this.$route.params.id,
      edit: false,
      loading: false
    };
  },
  async asyncData({ $axios, params, error }) {
    let data;
    await $axios
      .get(`/notes/${params.id}`)
      .then(e => (data = e.data.data))
      .catch(error);
    return {
      note: data
    };
  },
  methods: {
    done(response) {
      if (response === "delete") this.$router.push("/");
      this.loading = false;
      this.note = response;
      this.edit = false;
    }
  },
  computed: {
    ...mapGetters(["loggedInUser"])
  }
};
</script>

<style>
.note-view-card-footer {
  display: flex;
  justify-content: space-between;
}

.card-body.note-card-body {
  padding: 0;
}
</style>
