<template>
    <div>
        <h2>子组件向父组件传值</h2>
    <h3>{{msg}}</h3>
    <div>
        <p>
            {{$store.state.count}}
        </p>
    </div>
    <button v-on:click="addCount">
        按钮
    </button>
    </div>
</template>
<script>
export default {
    name:'Child',
    props:['msg'],
   

     methods:{
      
        addCount(){
      
            console.log(this.$store.state.count)
             this.$store.commit('newCount','是个程序员')
             
              
        }
    },
     mounted(){
       
    },
   
}
</script>
