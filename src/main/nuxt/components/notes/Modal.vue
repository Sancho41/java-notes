<template>
  <b-modal
    :no-close-on-backdrop="loading"
    :no-close-on-esc="loading"
    :hide-header-close="loading"
    id="modal-scoped"
    v-model="show"
    size="lg"
    hide-footer
  >
    <template v-slot:modal-header>
      <b-button
        v-if="data.is_public"
        :disabled="loading"
        variant="primary"
        size="sm"
        @click="share()"
      >
        <fa class="icon-brand" :icon="['fas' ,'share-square']" />
      </b-button>
      <b-button
        v-if="checkOwner"
        :disabled="loading"
        :variant="editing ? 'danger' : 'success'"
        size="sm"
        @click="editing = !editing"
      >
        <fa class="icon-brand" :icon="['fas' ,'edit']" />
      </b-button>
      <b-button :disabled="loading" variant="danger" size="sm" @click="show = false">
        <fa class="icon-brand" :icon="['fas' ,'times']" />
      </b-button>
    </template>

    <div class="note-modal-body">
      <note-form
        v-if="editing || !data"
        :data="data"
        v-on:loading="e => loading = e"
        ref="noteForm"
        v-on:action="action"
      />
      <note-show v-else :data="data" v-on:action="action" />
    </div>
  </b-modal>
</template>

<script>
import NoteForm from "./Form";
import NoteShow from "./Show";
import { mapGetters } from "vuex";
export default {
  components: {
    NoteForm,
    NoteShow
  },
  data() {
    return {
      show: false,
      editing: false,
      loading: false,
      data: Object
    };
  },
  methods: {
    edit(item) {
      this.data = item;
      this.show = true;
    },
    open() {
      this.editing = true;
      this.show = true;
      this.data = false;
    },
    share() {
      const baseUrl = window.location.origin;
      const shareLink = `${baseUrl}/notes/${this.data.id}`;
      navigator.clipboard.writeText(shareLink).then(() => {
        this.$swal.fire({
          toast: true,
          type: "success",
          text: "Link copiado!",
          showConfirmButton: false,
          timer: 3001,
          position: "top-end"
        });
      });
    },
    action({ type, data }) {
      this.$emit("action", { type, data });
      this.editing = false;
      if (type === "delete") this.show = false;
      this.data = data;
    }
  },
  computed: {
    ...mapGetters(["loggedInUser"]),
    checkOwner() {
      if (!this.data) return true;
      if (!this.loggedInUser) return false;
      return this.data.user.id == this.loggedInUser.id;
    }
  }
};
</script>

<style>
.modal-body {
  padding: 0;
  background: white;
}

.modal-content {
  background: none;
  border: none;
}

.modal-header {
  display: flex;
  justify-content: flex-end;
  padding: 10px 0;
}

.modal-header button {
  margin-left: 10px;
}

.note-modal-footer {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: space-between;
}
</style>
