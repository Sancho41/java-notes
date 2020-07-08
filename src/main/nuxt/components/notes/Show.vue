<template>
  <div
    class="container show-card"
    :class="small ? 'card-small' : ''"
    :style="`background-color: ${note.color || '#fff'}`"
  >
    <fa
      class="favorite-star"
      :class="(is_favorited ? 'favorited' : '') + ' ' +  (loading ? 'loading' : '')"
      :icon="hover ? star.hover : is_favorited ? star.favorited : star.not_favorited"
      @mouseover="hover = !small"
      @mouseleave="hover = false"
      @click="favorite()"
    />
    <h2>{{ note.title }}</h2>

    <pre ref="textContainer" class="text">{{ note.text }}</pre>
    <pre v-if="showMore">...</pre>
    <p class="mb-0" :class="note.is_public ? 'text-success' : 'text-danger'">
      <small>
        <b
          :class="note.is_public ? 'text-success' : 'text-danger'"
        >{{ note.is_public ? 'Público' : 'Privado' }}</b>
      </small>
    </p>
    <small>
      Criado em
      <b>{{ date }}</b> por
      <b>{{ note.user.name }}</b>
    </small>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showMore: false,
      hover: false,
      loading: false,
      star: {
        favorited: ["fas", "star"],
        not_favorited: ["far", "star"],
        hover: ["fas", "star-half-alt"]
      }
    };
  },
  props: {
    data: Object,
    small: Boolean
  },
  created() {
    this.$nextTick().then(() => this.getHights());
  },
  computed: {
    is_favorited() {
      return this.data.is_favorited;
    },
    note() {
      return this.data;
    },
    date() {
      const date = new Date(this.data.created_at);
      return date.toLocaleDateString();
    }
  },
  methods: {
    getHights() {
      if (!this.$refs.textContainer || !this.small) return;

      let el = this.$refs.textContainer;
      el.style.maxHeight = `auto`;

      let computedStyles = window.getComputedStyle(el);
      let lineHeight = parseFloat(
        computedStyles["lineHeight"].replace("px", "")
      );

      const newHeight = lineHeight * 10 - 1;

      this.showMore = el.clientHeight > newHeight;

      el.style.maxHeight = `${newHeight}px`;
    },
    async favorite() {
      if (this.small) return;
      this.loading = true;
      let { data: responseData } = await this.$axios.post(
        `/notes/favorite/${this.data.id}`
      );
      let data = responseData.data;
      let note = this.$helper.copyObject(this.data);
      note.is_favorited = data.is_favorited;
      this.$emit("action", { type: "update", data: note });
      this.$swal.fire({
        toast: true,
        type: "success",
        text: data.is_favorited
          ? "Adicionado aos favoritos!"
          : "Removido dos favoritos!",
        showConfirmButton: false,
        timer: 3001,
        position: "top-end"
      });
      this.loading = false;
    }
  }
};
</script>

<style>
.show-card {
  padding: 20px;
  text-align: left !important;
  position: relative;
}
.text {
  white-space: pre-wrap;
  word-break: break-all;
  font-family: var(--font-family-sans-serif);
  color: var(--color);
  font-size: 0.8rem;
  margin-bottom: 0;
  font-weight: 600;
  display: block;
  overflow: hidden;
  min-height: 280px;
}

.card-small .text {
  min-height: 0;
}

.favorite-star {
  position: relative;
  float: right;
  margin-left: 20px;
  cursor: pointer;
}

.favorited {
  color: gold;
}

.loading {
  opacity: 0.5;
}
</style>
