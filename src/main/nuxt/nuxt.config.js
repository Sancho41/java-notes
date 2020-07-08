export default {
  dev: true,
  mode: "spa",
  /*
   ** Headers of the page
   */
  head: {
    title: "Nuxt",
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      {
        hid: "description",
        name: "description",
        content: process.env.npm_package_description || ""
      }
    ],
    link: [{ rel: "icon", type: "image/x-icon", href: "/favicon.ico" }]
  },
  /*
   ** Customize the progress-bar color
   */
  loading: { color: " #3490dc" },
  /*
   ** Global CSS
   */
  css: ["@/assets/scss/global.scss"],
  /*
   ** Plugins to load before mounting the App
   */
  plugins: [
    { src: "~/plugins/vue-masonry-css", ssr: false },
    { src: "~/plugins/vue-textarea-autosize", ssr: false },
    { src: "~/plugins/helpers" }
  ],
  /*
   ** Nuxt.js dev-modules
   */
  buildModules: [],
  /*
   ** Nuxt.js modules
   */
  modules: [
    // Doc: https://bootstrap-vue.js.org
    "bootstrap-vue/nuxt",
    // Doc: https://axios.nuxtjs.org/usage
    "@nuxtjs/axios",
    "@nuxtjs/auth",
    // Doc: https://github.com/nuxt-community/dotenv-module
    "@nuxtjs/dotenv",
    "nuxt-sweetalert2",
    "nuxt-fontawesome"
  ],
  fontawesome: {
    component: "fa",
    imports: [
      {
        set: "@fortawesome/free-regular-svg-icons",
        icons: ["far"]
      },
      {
        set: "@fortawesome/free-solid-svg-icons",
        icons: ["fas"]
      }
    ]
  },
  /*
   ** Axios module configuration
   ** See https://axios.nuxtjs.org/options
   */
  router: {
    middleware: ["auth"]
  },
  axios: {
    baseURL: process.env.API_URL || "http://localhost:8000/api",
    proxyHeaders: false,
    credentials: false
  },

  auth: {
    strategies: {
      local: {
        endpoints: {
          login: {
            url: "/login",
            method: "post",
            propertyName: "data.token"
          },
          user: {
            url: "/user",
            method: "get",
            propertyName: "data"
          },
          logout: { url: "/logout", method: "post" }
        }
      }
    }
  },
  /* Page Transitions */
  pageTransition: {
    name: "page",
    mode: ""
  },
  /**
   * Generate configuration
   * */
  generate: {
    dir: '../webapp',
  },
  /*
   ** Build configuration
   */
  build: {
    /*
     ** You can extend webpack config here
     */
    extend(config, ctx) {}
  }
};
