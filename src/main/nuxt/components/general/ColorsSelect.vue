<template>
  <ul class="color-radio-list">
    <li class="color-radio" v-for="(value, key) in colors" :key="key">
      <input @change="update" type="radio" v-model="color" :id="key" :value="value" />
      <label class="shadow" :for="key" :style="`background: ${value}`"></label>
    </li>
  </ul>
</template>

<script>
export default {
  props: {
    value: String,
    colors: Object
  },
  data() {
    return {
      color: this.value
    };
  },
  methods: {
    update() {
      this.$emit("input", this.color);
    }
  }
};
</script>

<style>
.color-radio-list {
  list-style: none;
  display: flex;
  padding: 0;
  margin:10px 0 0;
}
.color-radio-list li:not(:first-child) {
  margin-left: 10px; 
}

.color-radio input {
  display: none;
}

.color-radio label {
  border-radius: 50%;
  width: 30px;
  height: 30px;
  margin: 0;
  cursor: pointer;
}
.color-radio > input:checked + label {
  border: 2px solid #6d6d6d;
}
</style>