<template>
  <div id="app">
    <div id="nav">
      <h1>Vuex状态管理</h1>
        <child msg='父组件向子组件传值'></child>
    </div>
   
  </div>
</template>
<script>
import Child from './components/Child'
export default {
  name:'app',
  components:{
    Child
  }
}
</script>



<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
