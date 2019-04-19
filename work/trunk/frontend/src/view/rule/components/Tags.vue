<template>
  <div>
    <el-input v-model="searchKey" size="small" placeholder="查找标签" clearable @change="handleSearch"></el-input>
    <div class="space"></div>
    <el-table
      ref="table"
      :data="labelList"
      @row-click="handleClickRow"
      highlight-current-row
      border
      size="small"
      style="width: 100%;"
      :header-cell-class-name="tableHeaderName"
    >
      <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
      <el-table-column prop="name" label="名称" align="center"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import { GetLabelList } from '@/api/dataMana'

export default {
  data() {
    return {
      searchKey: '',
      labelList: [] // 获取标签列表
    }
  },
  created() {
    this.getLabelList()
  },
  methods: {
    handleSearch() {
      this.getLabelList(this.searchKey)
    },
    // todo 点击 表格
    handleClickRow(row) {

    },
    // 获取标签列表
    async getLabelList() {
      const res = await GetLabelList({ name: this.searchKey })
      if (res.code === 0) {
        this.labelList = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 修改table header的背景色
    tableHeaderName({ row, rowIndex }) {
      return 'table-header'
    }
  }
}
</script>
