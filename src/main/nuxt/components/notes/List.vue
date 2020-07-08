<template>
  <div>
    <note-modal ref="noteModal" v-on:action="action" />
    <masonry :key="update" :gutter="20" :cols="{default: 4, 1000: 3, 700: 2, 400: 1}">
      <note-card
        class="note-card-item"
        @click.native="$refs.noteModal.edit(item)"
        :key="item.id"
        v-for="item in filteredNotes"
        :data="item"
      />
    </masonry>
  </div>
</template>

<script>
import NoteCard from "~/components/notes/Card";
import NoteModal from "~/components/notes/Modal";
import { mapGetters } from "vuex";
export default {
  props: {
    notes: Array,
    filter: String,
    onlyFavorites: Boolean,
    noFilter: Boolean
  },
  components: {
    NoteCard,
    NoteModal
  },
  data() {
    return {
      update: 0
    };
  },
  methods: {
    action({ type, data }) {
      switch (type) {
        case "delete":
          this.notes.splice(index, 1);
          break;
        case "update":
          const index = this.notes.findIndex(item => item.id === data.id);
          this.$set(this.notes, index, data);
          break;
        case "create":
          this.notes.unshift(data);
          break;
      }
      this.update++;
    }
  },
  computed: {
    ...mapGetters(["isAuthenticated", "loggedInUser"]),
    filteredNotes() {
      if (!this.filter && !this.onlyFavorites) return this.notes;

      let filtered = this.notes.filter(
        v => v.user.id == this.loggedInUser.id || v.is_favorited
      );
      if (this.onlyFavorites) filtered = filtered.filter(v => v.is_favorited);

      return filtered.filter(
        v =>
          v.title.includes(this.filter) ||
          v.text.includes(this.filter) ||
          v.user.name.includes(this.filter)
      );
    }
  }
};
</script>

<style>
.note-card-item {
  cursor: pointer;
}
</style>
