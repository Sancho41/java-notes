const Helpers = {
  install(Vue, options) {
    Vue.prototype.$helper = {
      copyObject(object) {
        return Object.assign({}, object);
      },
      tryCopy(object, fallback) {
        if (object)
          return this.copyObject(object);
        return this.copyObject(fallback);
      }
    }

    Vue.mixin({
      methods: {
      }
    });
  }
};

export default Helpers;

import Vue from 'vue'

Vue.use(Helpers);