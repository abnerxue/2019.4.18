<!-- 问题处方查询 -->
<template>
  <div>
    <Index :type="'problem'" />
  </div>
</template>

<script>
import Index from './components/index'

export default {
  name: 'problem',
  components: { Index }
}
</script>
