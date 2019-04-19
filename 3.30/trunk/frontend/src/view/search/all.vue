<!-- 所有处方查询 -->
<template>
  <div>
    <Index :type="'all'" />
  </div>
</template>

<script>
import Index from './components/index'

export default {
  name: 'allPrescription',
  components: { Index }
}
</script>
