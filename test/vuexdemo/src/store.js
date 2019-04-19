import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
          count:'小明'//静态数据
  },
  mutations: {
  
     newCount(state){
      state.count = '小红'
  },
},

  actions: {

  }
})
