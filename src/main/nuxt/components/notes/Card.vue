<template>
  <div>
    <b-card  class="note shadow"  @contextmenu.prevent="$router.push(`/notes/${note.id}`)">
      <note-show :data="note" small/>
    </b-card>
  </div>
</template>

<script>
import NoteShow from "./Show";
export default {
  name: "note.card",
  props: ["data"],
  components: {
    NoteShow,
  },
  data() {
    return {
      showMore: false,
      note: this.$helper.copyObject(this.data)
    };
  },
  computed: {
    createdAt() {
      const date = new Date(this.note.created_at);
      return date.toLocaleDateString();
    }
  },
  methods: {
  }
};
</script>

<style scoped>
.note {
  width: 100%;
  margin: 10px 0;
}

.note .card-body {
  padding: 0;
}

.note-card-footer {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

pre {
  white-space: pre-wrap;
  text-align: left;
  font-family: var(--font-family-sans-serif);
  color: var(--color);
  font-size: 0.8rem;
  overflow: hidden;
  margin-bottom: 0;
  font-weight: 600;
}

.limit {
  max-height: 250px;
}

.title {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
</style>
