(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{259:function(e,t,r){var content=r(276);"string"==typeof content&&(content=[[e.i,content,""]]),content.locals&&(e.exports=content.locals);(0,r(34).default)("b7664560",content,!0,{sourceMap:!1})},275:function(e,t,r){"use strict";var n=r(259);r.n(n).a},276:function(e,t,r){(t=r(33)(!1)).push([e.i,".card[data-v-28673831]{margin:80px 0 20px}.container[data-v-28673831]{min-width:100vw;min-height:100vh;display:flex;justify-content:center;align-items:center;position:absolute;top:0}.login[data-v-28673831]{width:100%;max-width:450px}",""]),e.exports=t},283:function(e,t,r){"use strict";r.r(t);r(5),r(3),r(2),r(1),r(4);var n=r(0),o=r(57);function l(object,e){var t=Object.keys(object);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(object);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(object,e).enumerable}))),t.push.apply(t,r)}return t}var c={auth:"guest",data:function(){return{loading:!1,credentials:{email:"",password:"",password_confirmation:"",name:""},errors:{}}},methods:{register:function(){var e=this;this.loading=!0;var t=this.redirect||"/";this.$axios.post("/register",this.credentials).then((function(){e.$auth.loginWith("local",{data:e.credentials}).then((function(){e.$swal.fire({toast:!0,type:"success",text:"Logado!",showConfirmButton:!1,timer:3e3,position:"top-end"}),e.$router.push(t)})).catch(console.log)})).catch((function(t){e.errors=t.response.data.error,e.$swal.fire({toast:!0,type:"error",text:"Erro ao tentar efetuar registro!",showConfirmButton:!1,timer:3e3,position:"top-end"})})).then((function(){e.loading=!1}))},resetError:function(e){this.errors[e]=[]}},computed:function(e){for(var i=1;i<arguments.length;i++){var source=null!=arguments[i]?arguments[i]:{};i%2?l(Object(source),!0).forEach((function(t){Object(n.a)(e,t,source[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(source)):l(Object(source)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(source,t))}))}return e}({},Object(o.b)(["redirect"]))},d=(r(275),r(35)),component=Object(d.a)(c,(function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"container"},[r("b-card",{staticClass:"login shadow card",attrs:{"border-variant":"primary"}},[r("b-card-title",[r("div",{staticClass:"text-center mt-3 display-4"},[e._v("Register")])]),e._v(" "),r("b-card-text",[r("b-form",{on:{submit:function(t){return e.register()}}},[r("b-form-group",{attrs:{id:"input-group-1",label:"Email:","label-for":"input-1"}},[r("b-form-input",{attrs:{id:"input-1",type:"email",required:"",placeholder:"Enter email","label-align":"left"},on:{input:function(t){return e.resetError("email")},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.register()}},model:{value:e.credentials.email,callback:function(t){e.$set(e.credentials,"email",t)},expression:"credentials.email"}}),e._v(" "),e._l(e.errors.email,(function(t,n){return r("b-form-invalid-feedback",{key:"email-"+n,attrs:{state:!1}},[e._v(e._s(t))])}))],2),e._v(" "),r("b-form-group",{attrs:{id:"input-group-4",label:"Nome:","label-for":"input-4"}},[r("b-form-input",{attrs:{id:"input-4",type:"text",required:"",placeholder:"Enter Name","label-align":"left"},on:{input:function(t){return e.resetError("name")},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.register()}},model:{value:e.credentials.name,callback:function(t){e.$set(e.credentials,"name",t)},expression:"credentials.name"}}),e._v(" "),e._l(e.errors.name,(function(t,n){return r("b-form-invalid-feedback",{key:"name-"+n,attrs:{state:!1}},[e._v(e._s(t))])}))],2),e._v(" "),r("b-form-group",{attrs:{id:"input-group-2",label:"Senha:","label-for":"input-2"}},[r("b-form-input",{attrs:{id:"input-2",type:"password",required:"",placeholder:"Senha"},on:{input:function(t){return e.resetError("password")},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.register()}},model:{value:e.credentials.password,callback:function(t){e.$set(e.credentials,"password",t)},expression:"credentials.password"}}),e._v(" "),e._l(e.errors.password,(function(t,n){return r("b-form-invalid-feedback",{key:"password-"+n,attrs:{state:!1},on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.register()}}},[e._v(e._s(t))])}))],2),e._v(" "),r("b-form-group",{attrs:{id:"input-group-3",label:"Confirmar Senha:","label-for":"input-3"}},[r("b-form-input",{attrs:{id:"input-3",type:"password",required:"",placeholder:"Confirmar Senha"},on:{input:function(t){return e.resetError("password_confirmation")},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.register()}},model:{value:e.credentials.password_confirmation,callback:function(t){e.$set(e.credentials,"password_confirmation",t)},expression:"credentials.password_confirmation"}}),e._v(" "),e._l(e.errors.password_confirmation,(function(t,n){return r("b-form-invalid-feedback",{key:"password_confirmation-"+n,attrs:{state:!1}},[e._v(e._s(t))])}))],2)],1),e._v(" "),r("div",{staticClass:"text-center"},[r("b-button",{staticStyle:{"max-width":"300px",width:"100%"},attrs:{disabled:e.loading,variant:"success",size:"lg"},on:{click:function(t){return e.register()}}},[e._v(e._s(e.loading?"Registrar...":"Registrar"))]),e._v(" "),r("p",{staticStyle:{"margin-top":"20px"}},[e._v("\n          Já possui uma conta?\n          "),r("br"),e._v(" "),r("a",{attrs:{href:"/login"},on:{click:function(t){return t.preventDefault(),e.$router.push({name:"login"})}}},[e._v("Login")])])],1)],1)],1)],1)}),[],!1,null,"28673831",null);t.default=component.exports}}]);