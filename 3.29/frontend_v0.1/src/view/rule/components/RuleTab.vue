<template>
  <div>
    <div
      class="f6"
      v-if="list && list.length === 0"
    >暂无数据</div>
    <div
      v-else
      v-for="(item, index) in list"
      :key="index"
    >
      <template>
        <p
          v-if="type === '科室限用'"
          class="f4 d-inline-block radio-label"
        >{{handleEnumDept(item.deptId)}}：</p>
        <p
          v-if="type === '人群限用'"
          class="f4 d-inline-block radio-label"
        >{{handleEnumCrowd(item.tcrowdId)}}：</p>
        <el-radio-group v-model="item.action">
          <el-radio
            v-for="(item, index) in auditlevel"
            :key="index"
            :label="item.id"
          >{{item.desp}}</el-radio>
        </el-radio-group>
      </template>
      <div class="space"></div>
    </div>
  </div>
</template>

<script>

export default {
  props: ['type', 'list', 'allHosDept', 'allCrowd'],
  data() {
    return {
      auditlevel: [
        {
          id: 0,
          desp: '无'
        },
        {
          id: 4,
          desp: '打回'
        },
        {
          id: 3,
          desp: '人工审核'
        },
        {
          id: 2,
          desp: '提醒'
        }
      ]
    }
  },
  methods: {
    // 处理枚举 - 医院科室
    handleEnumDept(id) {
      return this.allHosDept.filter(step => id === step.id)[0].name
    },
    // 处理枚举 - 人群
    handleEnumCrowd(id) {
      return this.allCrowd.filter(step => id === step.id)[0].type
    }
  }
}
</script>
<style lang="less" scoped>
.radio-label {
  width: 90px;
}
</style>

