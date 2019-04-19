import Vue from 'vue'
import ElementUI from 'element-ui'
import moment from 'moment'
import echarts from 'echarts'
import ECharts from 'vue-echarts'
import Calendar from 'vue-calendar-component'
import * as urls from '@/global/routepath'
import router from '@/global/router'
import storage from '@/utils/store'
import 'element-ui/lib/theme-chalk/index.css'
import '@/style/index.less'
import '@/assets/style/global.less'
import '@/assets/style/c-ele.css'
import App from './App'
import toast from './utils/toast'
import Loading from './utils/loading'

const loading = new Loading({
  loadlingid: 'loading'
})

Vue.use(ElementUI)

Vue.prototype.$moment = moment
Vue.prototype.$urls = urls
Vue.prototype.$storage = storage
Vue.prototype.$toast = toast
Vue.prototype.$loading = loading
Vue.prototype.$echarts = echarts
Vue.component('chart', ECharts)
Vue.component('calendar', Calendar)

router.beforeEach((to, from, next) => {
  const mytoken = localStorage.getItem('token')
  if (mytoken) {
    if (to.path === '/login') {
      next('/report/report')
    } else {
      next()
    }
  } else if (!mytoken) {
    if (to.path === '/login') {
      next()
    } else {
      next('/login')
    }
  }
})

/* eslint-disable */
new Vue({
  el: '#app',
  router,
  components: { App },
  render: h => h(App),
  data: {
    Bus: new Vue()
  }
})
