(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{244:function(t,e,r){var content=r(250);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,r(34).default)("37727db9",content,!0,{sourceMap:!1})},245:function(t,e,r){var content=r(253);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,r(34).default)("c71bccdc",content,!0,{sourceMap:!1})},246:function(t,e,r){var content=r(255);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,r(34).default)("4d0ffe99",content,!0,{sourceMap:!1})},247:function(t,e,r){"use strict";r(47);var o=r(11),n=(r(10),r(58),{data:function(){return{showMore:!1,hover:!1,loading:!1,star:{favorited:["fas","star"],not_favorited:["far","star"],hover:["fas","star-half-alt"]}}},props:{data:Object,small:Boolean},created:function(){var t=this;this.$nextTick().then((function(){return t.getHights()}))},computed:{is_favorited:function(){return this.data.is_favorited},note:function(){return this.data},date:function(){var t=new Date(this.data.created_at);return t.toLocaleDateString()}},methods:{getHights:function(){if(this.$refs.textContainer&&this.small){var t=this.$refs.textContainer;t.style.maxHeight="auto";var e=window.getComputedStyle(t),r=10*parseFloat(e.lineHeight.replace("px",""))-1;this.showMore=t.clientHeight>r,t.style.maxHeight="".concat(r,"px")}},favorite:function(){var t=this;return Object(o.a)(regeneratorRuntime.mark((function e(){var r,o,data,n;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(!t.small){e.next=2;break}return e.abrupt("return");case 2:return t.loading=!0,e.next=5,t.$axios.post("/notes/favorite/".concat(t.data.id));case 5:r=e.sent,o=r.data,data=o.data,(n=t.$helper.copyObject(t.data)).is_favorited=data.is_favorited,t.$emit("action",{type:"update",data:n}),t.$swal.fire({toast:!0,type:"success",text:data.is_favorited?"Adicionado aos favoritos!":"Removido dos favoritos!",showConfirmButton:!1,timer:3001,position:"top-end"}),t.loading=!1;case 13:case"end":return e.stop()}}),e)})))()}}}),c=(r(249),r(35)),component=Object(c.a)(n,(function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"container show-card",class:t.small?"card-small":"",style:"background-color: "+(t.note.color||"#fff")},[r("fa",{staticClass:"favorite-star",class:(t.is_favorited?"favorited":"")+" "+(t.loading?"loading":""),attrs:{icon:t.hover?t.star.hover:t.is_favorited?t.star.favorited:t.star.not_favorited},on:{mouseover:function(e){t.hover=!t.small},mouseleave:function(e){t.hover=!1},click:function(e){return t.favorite()}}}),t._v(" "),r("h2",[t._v(t._s(t.note.title))]),t._v(" "),r("pre",{ref:"textContainer",staticClass:"text"},[t._v(t._s(t.note.text))]),t._v(" "),t.showMore?r("pre",[t._v("...")]):t._e(),t._v(" "),r("p",{staticClass:"mb-0",class:t.note.is_public?"text-success":"text-danger"},[r("small",[r("b",{class:t.note.is_public?"text-success":"text-danger"},[t._v(t._s(t.note.is_public?"Público":"Privado"))])])]),t._v(" "),r("small",[t._v("\n    Criado em\n    "),r("b",[t._v(t._s(t.date))]),t._v(" por\n    "),r("b",[t._v(t._s(t.note.user.name))])])],1)}),[],!1,null,null,null);e.a=component.exports},249:function(t,e,r){"use strict";var o=r(244);r.n(o).a},250:function(t,e,r){(e=r(33)(!1)).push([t.i,".show-card{padding:20px;text-align:left!important;position:relative}.text{white-space:pre-wrap;word-break:break-all;font-family:var(--font-family-sans-serif);color:var(--color);font-size:.8rem;margin-bottom:0;font-weight:600;display:block;overflow:hidden;min-height:280px}.card-small .text{min-height:0}.favorite-star{position:relative;float:right;margin-left:20px;cursor:pointer}.favorited{color:gold}.loading{opacity:.5}",""]),t.exports=e},252:function(t,e,r){"use strict";var o=r(245);r.n(o).a},253:function(t,e,r){(e=r(33)(!1)).push([t.i,".color-radio-list{list-style:none;display:flex;padding:0;margin:10px 0 0}.color-radio-list li:not(:first-child){margin-left:10px}.color-radio input{display:none}.color-radio label{border-radius:50%;width:30px;height:30px;margin:0;cursor:pointer}.color-radio>input:checked+label{border:2px solid #6d6d6d}",""]),t.exports=e},254:function(t,e,r){"use strict";var o=r(246);r.n(o).a},255:function(t,e,r){(e=r(33)(!1)).push([t.i,".text-note,.title-note{width:100%;outline:none;border:none;background:none}.title-note{border-bottom:1px solid #d3d3d3;font-size:1.5rem;font-size:bold}.form-footer{display:flex;width:100%;padding:10px;justify-content:space-between;align-items:flex-end}",""]),t.exports=e},260:function(t,e,r){var content=r(278);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,r(34).default)("f54758be",content,!0,{sourceMap:!1})},263:function(t,e,r){"use strict";r(47);var o=r(11),n={props:{value:String,colors:Object},data:function(){return{color:this.value}},methods:{update:function(){this.$emit("input",this.color)}}},c=(r(252),r(35)),l={components:{ColorsSelect:Object(c.a)(n,(function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("ul",{staticClass:"color-radio-list"},t._l(t.colors,(function(e,o){return r("li",{key:o,staticClass:"color-radio"},[r("input",{directives:[{name:"model",rawName:"v-model",value:t.color,expression:"color"}],attrs:{type:"radio",id:o},domProps:{value:e,checked:t._q(t.color,e)},on:{change:[function(r){t.color=e},t.update]}}),t._v(" "),r("label",{staticClass:"shadow",style:"background: "+e,attrs:{for:o}})])})),0)}),[],!1,null,null,null).exports},props:["data"],data:function(){var t={title:"",text:"",is_public:!1,color:"#fff"};return{edit:this.$helper.tryCopy(this.data,t),fields:t,is_loading:!1,colors:{white:"#fff",red:"#e74c3c2e",blue:"#3498db2e",green:"#59b6712e"}}},methods:{save:function(){var t=this;return Object(o.a)(regeneratorRuntime.mark((function e(){var r,o,data,n,c,l;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r=t.data?"update":"create",t.loading(!0),e.prev=2,e.next=5,t.$axios({method:"create"==r?"POST":"PATCH",data:t.edit,url:"/notes/".concat(t.edit.id||"")});case 5:o=e.sent,data=o.data,t.$swal.fire({toast:!0,type:"success",text:"Salvo!",showConfirmButton:!1,timer:3001,position:"top-end"}),t.$emit("action",{type:r,data:data.data}),e.next=15;break;case 11:e.prev=11,e.t0=e.catch(2),(n=e.t0.response)?(c=n.status,l=n.statusText,n.data,400==c&&(l="A nota não pode estar em branco!"),t.$swal.fire({title:c,type:"error",text:l,timer:5e3})):t.$swal.fire({title:"Internal Error",type:"error",text:"Tente novamente mais tarde.",timer:5e3});case 15:return e.prev=15,t.loading(!1),e.finish(15);case 18:case"end":return e.stop()}}),e,null,[[2,11,15,18]])})))()},confirmRemove:function(){var t=this;return Object(o.a)(regeneratorRuntime.mark((function e(){var r,o;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.$swal.fire({title:"Você tem certeza?",text:"Esta ação não pode ser desfeita!",type:"warning",showCancelButton:!0,confirmButtonText:"Sim,pode deletar!"});case 2:return r=e.sent,o=r.value,e.abrupt("return",o);case 5:case"end":return e.stop()}}),e)})))()},remove:function(){var t=this;return Object(o.a)(regeneratorRuntime.mark((function e(){var r,o,n,c;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,t.confirmRemove();case 2:if(e.sent){e.next=5;break}return e.abrupt("return");case 5:return t.loading(!0),e.prev=6,e.next=9,t.$axios.delete("/notes/".concat(t.data.id));case 9:r=e.sent,r.data,t.$swal.fire({toast:!0,type:"success",text:"Deletado!",showConfirmButton:!1,timer:3001,position:"top-end"}),t.$emit("action",{type:"delete",data:t.data}),e.next=19;break;case 15:e.prev=15,e.t0=e.catch(6),(o=e.t0.response)&&(n=o.status,c=o.statusText,o.data,t.$swal.fire({title:n,type:"error",text:c,timer:5e3}));case 19:return e.prev=19,t.loading(!1),e.finish(19);case 22:case"end":return e.stop()}}),e,null,[[6,15,19,22]])})))()},loading:function(t){this.is_loading=t,this.$emit("loading",t)},reset:function(){this.edit=this.$helper.tryCopy(this.data,this.fields)}}},d=(r(254),Object(c.a)(l,(function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"container",style:"background: "+(t.edit.color||"#fff")},[r("TextareaAutosize",{staticClass:"title-note",attrs:{placeholder:"Título...","min-height":55,disabled:t.is_loading},model:{value:t.edit.title,callback:function(e){t.$set(t.edit,"title",e)},expression:"edit.title"}}),t._v(" "),r("TextareaAutosize",{ref:"text",staticClass:"text-note",attrs:{placeholder:"Anote aqui...","min-height":250,disabled:t.is_loading},model:{value:t.edit.text,callback:function(e){t.$set(t.edit,"text",e)},expression:"edit.text"}}),t._v(" "),r("div",{staticClass:"form-footer"},[r("div",[r("b-form-checkbox",{attrs:{id:"checkbox-1",name:"checkbox-1",value:!0,"unchecked-value":!1,disabled:t.is_loading},model:{value:t.edit.is_public,callback:function(e){t.$set(t.edit,"is_public",e)},expression:"edit.is_public"}},[t._v("Público")]),t._v(" "),r("colors-select",{attrs:{disabled:t.is_loading,colors:t.colors},model:{value:t.edit.color,callback:function(e){t.$set(t.edit,"color",e)},expression:"edit.color"}})],1),t._v(" "),r("div",[t.data?r("b-button",{attrs:{disabled:t.is_loading,variant:"danger"},on:{click:function(e){return t.remove()}}},[t._v("Deletar")]):t._e(),t._v(" "),r("b-button",{attrs:{disabled:t.is_loading,variant:"success"},on:{click:function(e){return t.save()}}},[t._v("Salvar")])],1)])],1)}),[],!1,null,null,null));e.a=d.exports},277:function(t,e,r){"use strict";var o=r(260);r.n(o).a},278:function(t,e,r){(e=r(33)(!1)).push([t.i,".note-view-card-footer{display:flex;justify-content:space-between}.card-body.note-card-body{padding:0}",""]),t.exports=e},285:function(t,e,r){"use strict";r.r(e);r(5),r(3),r(2),r(1),r(4);var o=r(0),n=(r(47),r(11)),c=r(263),l=r(247),d=r(57);function f(object,t){var e=Object.keys(object);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(object);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(object,t).enumerable}))),e.push.apply(e,r)}return e}var v={components:{NoteForm:c.a,NoteShow:l.a},validate:function(t){var e=t.params;return/^\d+$/.test(e.id)},data:function(){return{id:this.$route.params.id,edit:!1,loading:!1}},asyncData:function(t){return Object(n.a)(regeneratorRuntime.mark((function e(){var r,o,data;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r=t.$axios,o=t.params,t.error,e.next=3,r.get("/notes/".concat(o.id)).then((function(t){return data=t.data.data})).catch((function(t){t.response;return erro(reponse)}));case 3:return e.abrupt("return",{note:data});case 4:case"end":return e.stop()}}),e)})))()},methods:{done:function(t){"delete"===t&&this.$router.push("/"),this.loading=!1,this.note=t,this.edit=!1}},computed:function(t){for(var i=1;i<arguments.length;i++){var source=null!=arguments[i]?arguments[i]:{};i%2?f(Object(source),!0).forEach((function(e){Object(o.a)(t,e,source[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(source)):f(Object(source)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(source,e))}))}return t}({},Object(d.b)(["loggedInUser"]))},h=(r(277),r(35)),component=Object(h.a)(v,(function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"container"},[r("b-card",{staticClass:"shadow",attrs:{"no-body":""}},[r("div",{staticClass:"note-card-body"},[t.edit?r("note-form",{ref:"noteForm",attrs:{data:t.note},on:{done:t.done,loading:function(e){return t.loading=e}}}):r("note-show",{attrs:{data:t.note}})],1),t._v(" "),t.loggedInUser.id==t.note.user.id?r("b-card-footer",{attrs:{"footer-bg-variant":"white"}},[r("div",{staticClass:"note-view-card-footer"},[r("b-button",{attrs:{disabled:t.loading},on:{click:function(e){t.edit=!t.edit}}},[t._v(t._s(t.edit?"Cancelar":"Editar"))]),t._v(" "),t.edit?r("div",[r("b-button",{attrs:{disabled:t.loading,variant:"danger"},on:{click:function(e){return t.$refs.noteForm.remove()}}},[t._v("Deletar")]),t._v(" "),r("b-button",{attrs:{variant:"primary",disabled:t.loading},on:{click:function(e){return t.$refs.noteForm.save()}}},[t._v(t._s(t.loading?"Em execução...":"Salvar"))])],1):t._e()],1)]):t._e()],1)],1)}),[],!1,null,null,null);e.default=component.exports}}]);