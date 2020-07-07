<template>
  <div class="container" :style="`background: ${edit.color || '#fff'}`">
    <TextareaAutosize
      placeholder="Título..."
      v-model="edit.title"
      class="title-note"
      :min-height="55"
      :disabled="is_loading"
    />
    <TextareaAutosize
      placeholder="Anote aqui..."
      v-model="edit.text"
      :min-height="250"
      class="text-note"
      ref="text"
      :disabled="is_loading"
    />

    <div class="form-footer">
      <div>
        <b-form-checkbox
          id="checkbox-1"
          v-model="edit.is_public"
          name="checkbox-1"
          :value="true"
          :unchecked-value="false"
          :disabled="is_loading"
        >Público</b-form-checkbox>
        <colors-select :disabled="is_loading" v-model="edit.color" :colors="colors" />
      </div>
      <div>
        <b-button v-if="data" :disabled="is_loading" variant="danger" @click="remove()">Deletar</b-button>
        <b-button :disabled="is_loading" variant="success" @click="save()">Salvar</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import ColorsSelect from "../general/ColorsSelect";
export default {
  components: {
    ColorsSelect
  },
  props: ["data"],
  data() {
    const fields = {
      title: "",
      text: "",
      is_public: false,
      color: "#fff"
    };
    return {
      edit: this.$helper.tryCopy(this.data, fields),
      fields,
      is_loading: false,
      colors: {
        white: "#fff",
        red: "#e74c3c2e",
        blue: "#3498db2e",
        green: "#59b6712e"
      }
    };
  },
  methods: {
    async save() {
      const action = this.data ? "update" : "create";

      this.loading(true);

      try {
        const { data } = await this.$axios({
          method: action == "create" ? "POST" : "PATCH",
          data: this.edit,
          url: `/notes/${this.edit.id || ""}`
        });
        this.$swal.fire({
          toast: true,
          type: "success",
          text: "Salvo!",
          showConfirmButton: false,
          timer: 3001,
          position: "top-end"
        });
        this.$emit("action", { type: action, data: data.data });
      } catch ({ response }) {
        if (response) {
          let { status, statusText, data } = response;

          if (status == 400) statusText = "A nota não pode estar em branco!";

          this.$swal.fire({
            title: status,
            type: "error",
            text: statusText,
            timer: 5000
          });
        } else {
          this.$swal.fire({
            title: "Internal Error",
            type: "error",
            text: "Tente novamente mais tarde.",
            timer: 5000
          });
        }
      } finally {
        this.loading(false);
      }
    },

    async confirmRemove() {
      let { value } = await this.$swal.fire({
        title: "Você tem certeza?",
        text: "Esta ação não pode ser desfeita!",
        type: "warning",
        showCancelButton: true,
        confirmButtonText: "Sim,pode deletar!"
      });
      return value;
    },

    async remove() {
      let confirmed = await this.confirmRemove();
      if (!confirmed) return;

      this.loading(true);

      try {
        const { data } = await this.$axios.delete(`/notes/${this.data.id}`);
        this.$swal.fire({
          toast: true,
          type: "success",
          text: "Deletado!",
          showConfirmButton: false,
          timer: 3001,
          position: "top-end"
        });
        this.$emit("action", { type: "delete", data: this.data });
      } catch ({ response }) {
        if (response) {
          let { status, statusText, data } = response;
          this.$swal.fire({
            title: status,
            type: "error",
            text: statusText,
            timer: 5000
          });
        }
      } finally {
        this.loading(false);
      }
    },

    loading(status) {
      this.is_loading = status;
      this.$emit("loading", status);
    },

    reset() {
      this.edit = this.$helper.tryCopy(this.data, this.fields);
    }
  }
};
</script>

<style>
.title-note,
.text-note {
  width: 100%;
  outline: none;
  border: none;
  background: none;
}
.title-note {
  border-bottom: 1px solid lightgray;
  font-size: 1.5rem;
  font-size: bold;
}
.form-footer {
  display: flex;
  width: 100%;
  padding: 10px;
  justify-content: space-between;
  align-items: flex-end;
}
</style>
