<!-- 审核项目与级别设置 -->
<template>
  <div>
    <div class="text-right">
      <el-button
        type="primary"
        size="small"
      >同步数据</el-button>
      <el-button
        type="primary"
        size="small"
        :loading="loading"
        @click="handleSave"
      >保存</el-button>
    </div>
    <div class="space"></div>
    <el-table
      :data="list"
      border
      :row-class-name="tableRowClassName"
      :header-cell-class-name="tableHeaderName"
      size="small"
      style="width: 100%;"
    >
      <el-table-column
        prop="code"
        label="问题代码"
        align="center"
        width="150"
      ></el-table-column>
      <el-table-column
        prop="problemName"
        label="问题名称"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="enable"
        label="区域审方启用"
        align="center"
        width="150"
      >
        <template slot-scope="scope">
          <el-checkbox v-model="scope.row.enable">启用</el-checkbox>
        </template>
      </el-table-column>
      <el-table-column
        prop="action"
        label="警示级别设置"
        align="center"
      >
        <template slot-scope="scope">
          <el-radio-group
            v-model="scope.row.action"
            :disabled="!scope.row.enable"
          >
            <el-radio
              v-for="(item, index) in auditlevel"
              :key="index"
              :label="item.id"
            >{{item.desp}}</el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { auditlevel } from '@/global/cfg'
import { Selectallaudit, Updateauditrules } from '@/api/dataMana'

export default {
  name: 'review',
  data() {
    return {
      loading: false,
      list: [],
      oldList: [],
      newList: [],
      auditlevel
    }
  },
  beforeRouteLeave(to, from, next) {
    this.newList = JSON.parse(JSON.stringify(this.list))
    this.newList.map(item => Object.assign(item, { enable: Number(item.enable) }))
    const res = this.oldList === JSON.stringify(this.newList)
    if (!res) {
      this.$confirm('当前页面未保存，是否确认离开？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => { next() })
        .catch(() => { next(false) })
    } else {
      next()
    }
  },
  created() {
    this.handleGetList()
  },
  methods: {
    // 保存
    handleSave() {
      this.newList = JSON.parse(JSON.stringify(this.list))
      this.newList.map(item => Object.assign(item, { enable: Number(item.enable) }))
      this.updateAuditrules(JSON.stringify(this.newList))
    },
    async updateAuditrules(args) {
      this.loading = true
      const res = await Updateauditrules(args)
      this.loading = false
      if (res.code === 0) {
        this.$message.success('保存成功')
        this.handleGetList()
      } else {
        this.$message.error(res.msg)
      }
    },
    async handleGetList() {
      const res = await Selectallaudit()
      if (res.code === 0) {
        this.list = res.result
        this.oldList = JSON.stringify(res.result)
        this.list.map(item => Object.assign(item, { enable: Boolean(item.enable) }))
      }
    },
    tableRowClassName({ row, rowIndex }) {
      if ((rowIndex % 2) === 0) {
        return 'row1'
      }
      return 'row2'
    },
    tableHeaderName() {
      return 'table-header'
    }
  }
}
</script>

