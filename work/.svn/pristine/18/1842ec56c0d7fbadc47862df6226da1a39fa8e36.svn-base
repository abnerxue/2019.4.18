<template>
  <el-pagination
    style="margin-top: 10px;"
    v-if="page.totalPage > 1"
    :pager-count="5"
    @current-change="handleCurrentPage"
    :current-page.sync="page.pageNo"
    :page-size="page.pageSize"
    layout="prev, pager, next, jumper"
    :total.sync="page.totalCount"
  ></el-pagination>
</template>

<script>
export default {
  props: ['page'],
  methods: {
    // 分页
    handleCurrentPage(val) {
      this.page.pageNo = val
      this.$emit('handlePageChange')
    }
  }
}
</script>
