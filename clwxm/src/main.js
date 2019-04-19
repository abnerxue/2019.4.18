// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index.js'
import axios from 'axios';
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import Vuex from 'vuex';

Vue.use(iView);
Vue.config.productionTip = false
Vue.use(axios);
Vue.use(Vuex);


Vue.prototype.$http = axios;
// Vue.prototype.layer = layer;
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.baseURL = 'http://172.16.10.31:8080/';
let token = localStorage.getItem(
  "token"
);
if (token && token != null) {
  axios.defaults.headers.common["USER_TOKEN"] = token;
}


const state = {
  username: "",
  baiduMap: {},
  baiduMonitorMap: {},
  driveLineMap: {},
  vehicleTrackingMap: {},
  carSpeed: ""
};
var vuexStore = new Vuex.Store({
  state
});
/* eslint-disable no-new */
new Vue({
  store: vuexStore,
  el: '#app',
  router,
  components: {
    App
  },
  template: '<App/>'
})

Vue.prototype.getToken = function () {
  this.$http({
      method: "post",
      url: "/gm-data/api/frmAdminusers/" + localStorage.getItem("username") + "," + localStorage.getItem("password") + "/login",
      headers: {
        DTS: new Date().getTime(),
        http_method: "search"
      },
      dataType: "json",
      crossDomain: true, //== !(document.all),
      cache: false
    })
    .then((response) => {
      localStorage.setItem("token", response.data.result.USER_TOKEN);
      axios.defaults.headers.common["USER_TOKEN"] = localStorage.getItem(
        "token"
      );
      return Promise.resolve(response);
    })
    .catch(function (response) {
      return Promise.resolve(response);
    });
}