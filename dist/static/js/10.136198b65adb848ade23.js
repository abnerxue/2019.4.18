webpackJsonp([10],{"+WEy":function(e,t,n){(e.exports=n("FZ+f")(!0)).push([e.i,"\n.radio-label[data-v-099b45d4] {\n  width: 90px;\n}\n","",{version:3,sources:["C:/Users/xst/Desktop/4/frontend_v0.1/src/view/setting/components/C:/Users/xst/Desktop/4/frontend_v0.1/src/view/setting/components/CheckTab.vue","C:/Users/xst/Desktop/4/frontend_v0.1/src/view/setting/components/CheckTab.vue"],names:[],mappings:";AAuHA;EACE,YAAA;CCtHD",file:"CheckTab.vue",sourcesContent:["\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n.radio-label {\n  width: 90px;\n}\n",".radio-label {\n  width: 90px;\n}\n"],sourceRoot:""}])},"/2TV":function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=n("Xxa5"),s=n.n(i),r=n("Dd8w"),a=n.n(r),c=n("exGp"),l=n.n(c),o=n("hky6"),d={props:["detail","title","type"],data:function(){return{isShowModal:!1,loading:!1,checkItemsList:[],resultList:[],form:{liverKidneyDicId:"",checkLimit:[{id:null,checkItemsId:"",unitName:"",lowerbound:"",upperbound:""}]},rules:{liverKidneyDicId:[{required:!0,message:"选择判断结果",trigger:"change"}]}}},watch:{detail:function(){this.form=this.detail||this.form}},methods:{hideModal:function(){this.isShowModal=!1,this.$refs.form.resetFields(),this.form.checkLimit=[{id:null,checkItemsId:this.checkItemsList[0].id,unitName:this.checkItemsList[0].unitName,lowerbound:"",upperbound:""}]},handleDelete:function(e){this.form.checkLimit.splice(e,1)},clickSubmit:function(){var e=this;this.$refs.form.validate(function(t){t?"add"===e.type?e.handleAdd():"edit"===e.type&&e.handleEdit():console.log("error submit!!")})},clickAdd:function(){this.form.checkLimit.push({id:null,checkItemsId:this.checkItemsList[0].id,unitName:this.checkItemsList[0].unitName,lowerbound:"",upperbound:""})},handleAdd:function(e){var t=this;return l()(s.a.mark(function e(){var n;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.loading=!0,e.next=3,Object(o.a)(t.form);case 3:n=e.sent,t.loading=!1,0===n.code?(t.$message.success(n.msg),t.hideModal(),t.$emit("getChecklimitList")):t.$message.error(n.msg);case 6:case"end":return e.stop()}},e,t)}))()},handleEdit:function(e){var t=this;return l()(s.a.mark(function e(){var n;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.loading=!0,e.next=3,Object(o.n)(t.form);case 3:n=e.sent,t.loading=!1,0===n.code?(t.$message.success(n.msg),t.hideModal(),t.$emit("handleEdit")):t.$message.error(n.msg);case 6:case"end":return e.stop()}},e,t)}))()},getLiverList:function(){var e=this;return l()(s.a.mark(function t(){var n;return s.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(o.g)();case 2:0===(n=t.sent).code?e.resultList=n.result:e.$message.error(n.msg);case 4:case"end":return t.stop()}},t,e)}))()},getDneydicList:function(){var e=this;return l()(s.a.mark(function t(){var n;return s.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(o.h)();case 2:0===(n=t.sent).code?e.resultList=n.result:e.$message.error(n.msg);case 4:case"end":return t.stop()}},t,e)}))()},getCheckItemsList:function(){var e=this;return l()(s.a.mark(function t(){var n;return s.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(o.d)();case 2:0===(n=t.sent).code?(e.checkItemsList=n.result,e.form.checkLimit[0].checkItemsId=e.checkItemsList[0].id,e.form.checkLimit[0].unitName=e.checkItemsList[0].unitName):e.$message.error(n.msg);case 4:case"end":return t.stop()}},t,e)}))()}}},u=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-dialog",{attrs:{title:e.title,visible:e.isShowModal,width:"700px","before-close":e.hideModal},on:{"update:visible":function(t){e.isShowModal=t}}},[n("el-form",{ref:"form",attrs:{"label-width":"120px",size:"small",rules:e.rules,model:e.form}},[n("el-form-item",{attrs:{label:"检验项目："}},[n("el-row",[n("el-col",{staticClass:"text-center",attrs:{span:2}},[e._v("序号")]),e._v(" "),n("el-col",{staticClass:"text-center",attrs:{span:8}},[e._v("项目名称")]),e._v(" "),n("el-col",{staticClass:"text-center",attrs:{span:8}},[e._v("区间")]),e._v(" "),n("el-col",{attrs:{span:2}})],1),e._v(" "),e._l(e.form.checkLimit,function(t,i){return n("el-row",{key:i},[n("el-col",{staticClass:"text-center",attrs:{span:2}},[e._v(e._s(i+1))]),e._v(" "),n("el-col",{staticClass:"text-center",attrs:{span:8}},[n("el-select",{staticStyle:{width:"160px"},attrs:{placeholder:"检查项目"},model:{value:t.checkItemsId,callback:function(n){e.$set(t,"checkItemsId",n)},expression:"item.checkItemsId"}},e._l(e.checkItemsList,function(e){return n("el-option",{key:e.name,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),n("el-col",{staticClass:"text-center",attrs:{span:10}},[n("el-form-item",{staticClass:"d-inline-block",attrs:{prop:"checkLimit."+i+".lowerbound",rules:{required:!0,message:"不能为空",trigger:"blur"}}},[n("el-input",{staticStyle:{width:"60px"},model:{value:t.lowerbound,callback:function(n){e.$set(t,"lowerbound",n)},expression:"item.lowerbound"}})],1),e._v(" "),n("span",[e._v("~")]),e._v(" "),n("el-form-item",{staticClass:"d-inline-block",attrs:{prop:"checkLimit."+i+".upperbound",rules:{required:!0,message:"不能为空",trigger:"blur"}}},[n("el-input",{staticStyle:{width:"60px"},model:{value:t.upperbound,callback:function(n){e.$set(t,"upperbound",n)},expression:"item.upperbound"}})],1),e._v(" "),n("span",[e._v(e._s(t.unitName))])],1),e._v(" "),n("el-col",{attrs:{span:2}},[n("i",{staticClass:"el-icon-plus pointer",on:{click:e.clickAdd}}),e._v(" "),n("span",{staticClass:"d-inline-block",staticStyle:{width:"8px"}}),e._v(" "),0!==i?n("i",{staticClass:"el-icon-delete text-red pointer",on:{click:function(t){return e.handleDelete(i)}}}):e._e()])],1)})],2),e._v(" "),n("el-form-item",{attrs:{label:"判断结果：",prop:"liverKidneyDicId"}},[n("el-select",{attrs:{placeholder:"选择判断结果"},model:{value:e.form.liverKidneyDicId,callback:function(t){e.$set(e.form,"liverKidneyDicId",t)},expression:"form.liverKidneyDicId"}},e._l(e.resultList,function(e,t){return n("el-option",{key:t,attrs:{label:e.description,value:e.id}})}),1)],1),e._v(" "),n("el-form-item",[n("el-button",{on:{click:e.hideModal}},[e._v("取消")]),e._v(" "),n("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.clickSubmit}},[e._v("确定")])],1)],1)],1)};u._withStripped=!0;var m={render:u,staticRenderFns:[]},h=m;var p=n("VU/8")(d,h,!1,null,null,null);p.options.__file="src/view/setting/components/CheckModal.vue";var f=p.exports,v={props:["list","tabIndex"],components:{CheckModal:f},data:function(){return{tableHeight:50,detail:{}}},mounted:function(){var e=this;setTimeout(function(){e.tableHeight=window.innerHeight-330},100)},methods:{spanMethod:function(e){e.row,e.column;var t=e.rowIndex,n=e.columnIndex,i=this.list[t].rowSpan;if(0===n||3===n||4===n)return{rowspan:i,colspan:i>0?1:0}},handleEdit:function(){this.$emit("getChecklimitList")},showEdit:function(e){var t=this;return l()(s.a.mark(function n(){var i;return s.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.next=2,Object(o.e)({liverKidneyDicId:e});case 2:0===(i=n.sent).code?(t.detail=i.result[0],t.$refs.modal.isShowModal=!0,t.$refs.modal.getCheckItemsList(),0===t.tabIndex?t.$refs.modal.getLiverList():1===t.tabIndex&&t.$refs.modal.getDneydicList()):t.$message.error(i.msg);case 4:case"end":return n.stop()}},n,t)}))()},handleDelete:function(e){var t=this;this.$confirm("确定删除吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.delete(e)}).catch(function(){})},delete:function(e){var t=this;return l()(s.a.mark(function n(){var i;return s.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.next=2,Object(o.c)({liverKidneyDicId:e});case 2:0===(i=n.sent).code?(t.$message.success("删除成功!"),t.$emit("getChecklimitList")):t.$message.error(i.msg);case 4:case"end":return n.stop()}},n,t)}))()},tableHeaderName:function(e){e.row,e.rowIndex;return"table-header"}}},b=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("el-table",{ref:"table",staticStyle:{width:"100%"},attrs:{data:e.list,border:"",size:"small",height:e.tableHeight,"span-method":e.spanMethod,"header-cell-class-name":e.tableHeaderName}},[n("el-table-column",{attrs:{prop:"ruleName",label:"检验项目",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"name",label:"名称",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"",label:"区间",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.lowerbound)+"\n        ~\n        "+e._s(t.row.upperbound)+"\n        "+e._s(t.row.unitName)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{prop:"description",label:"判断",width:"150",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"enable",label:"操作",align:"center",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("i",{staticClass:"el-icon-edit pointer",on:{click:function(n){return e.showEdit(t.row.liverKidneyDicId)}}}),e._v(" "),n("span",{staticClass:"d-inline-block",staticStyle:{width:"8px"}}),e._v(" "),n("i",{staticClass:"el-icon-delete text-red pointer",on:{click:function(n){return e.handleDelete(t.row.liverKidneyDicId)}}})]}}])})],1),e._v(" "),n("CheckModal",{ref:"modal",attrs:{title:"编辑规则",type:"edit",detail:e.detail},on:{handleEdit:e.handleEdit}})],1)};b._withStripped=!0;var k={render:b,staticRenderFns:[]},g=k;var w=!1;var x=n("VU/8")(v,g,!1,function(e){w||n("cDPg")},"data-v-099b45d4",null);x.options.__file="src/view/setting/components/CheckTab.vue";var _={name:"",components:{CheckTab:x.exports,CheckModal:f},data:function(){return{type:1,tabIndex:0,list:[]}},created:function(){this.getChecklimitList()},methods:{showModal:function(){this.$refs.modal.isShowModal=!0,this.$refs.modal.getCheckItemsList(),0===this.tabIndex?this.$refs.modal.getLiverList():1===this.tabIndex&&this.$refs.modal.getDneydicList()},clickTab:function(e){this.tabIndex=Number(e.index),this.type=Number(e.index)+1,this.getChecklimitList()},getChecklimitList:function(){var e=this;return l()(s.a.mark(function t(){var n,i;return s.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(o.f)({type:e.type});case 2:0===(n=t.sent).code?(e.list=[],(i=n.result).length>0&&i.forEach(function(t){t.checkLimit.forEach(function(n,i){e.list.push(a()({rowSpan:0===i?t.checkLimit.length:0,description:t.description,liverKidneyDicId:t.liverKidneyDicId,ruleName:"检验项目"},n))})})):(e.$message.error(n.msg),e.list=[]);case 4:case"end":return t.stop()}},t,e)}))()}}},I=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticStyle:{position:"relative"}},[n("el-button",{staticStyle:{position:"absolute",top:"-10px",right:"20px","z-index":"999"},attrs:{type:"primary"},on:{click:e.showModal}},[e._v("添加规则")]),e._v(" "),n("el-tabs",{on:{"tab-click":e.clickTab}},[n("el-tab-pane",{attrs:{label:"肝功能异常"}},[n("CheckTab",{attrs:{tabIndex:e.tabIndex,list:e.list},on:{getChecklimitList:e.getChecklimitList}})],1),e._v(" "),n("el-tab-pane",{attrs:{label:"肾功能异常"}},[n("CheckTab",{attrs:{tabIndex:e.tabIndex,list:e.list},on:{getChecklimitList:e.getChecklimitList}})],1)],1),e._v(" "),n("CheckModal",{ref:"modal",attrs:{title:"添加规则",type:"add"},on:{getChecklimitList:e.getChecklimitList}})],1)};I._withStripped=!0;var C={render:I,staticRenderFns:[]},L=C;var y=n("VU/8")(_,L,!1,null,null,null);y.options.__file="src/view/setting/restrict.vue";t.default=y.exports},cDPg:function(e,t,n){var i=n("+WEy");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);n("rjj0")("7f8eb279",i,!1,{})}});
//# sourceMappingURL=10.136198b65adb848ade23.js.map