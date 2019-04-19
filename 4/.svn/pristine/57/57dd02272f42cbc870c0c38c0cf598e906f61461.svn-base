<template>
  <div>
    <el-table
      ref="table"
      :data="list"
      border
      size="small"
      style="width: 100%;"
      :height="tableHeight"
      :span-method="spanMethod"
      :header-cell-class-name="tableHeaderName"
    >
      <el-table-column prop="ruleName" label="检验项目" align="center"></el-table-column>
      <el-table-column prop="name" label="名称" align="center"></el-table-column>
      <el-table-column prop label="区间" align="center">
        <template slot-scope="scope">
          {{scope.row.lowerbound}}
          ~
          {{scope.row.upperbound}}
          {{scope.row.unitName}}
        </template>
      </el-table-column>
      <el-table-column prop="description" label="判断" width="150" align="center"></el-table-column>
      <el-table-column prop="enable" label="操作" align="center" width="100">
        <template slot-scope="scope">
          <i @click="showEdit(scope.row.liverKidneyDicId)" class="el-icon-edit pointer"></i>
          <span class="d-inline-block" style="width: 8px;"></span>
          <i
            @click="handleDelete(scope.row.liverKidneyDicId)"
            class="el-icon-delete text-red pointer"
          ></i>
        </template>
      </el-table-column>
    </el-table>
    <CheckModal
      ref="modal"
      :title="'编辑规则'"
      :type="'edit'"
      :detail="detail"
      @handleEdit="handleEdit"
    />
  </div>
</template>

<script>
import { DelateCheckLimit, GetCheckItemsDetail } from '@/api/setting'
import CheckModal from './CheckModal'

export default {
  props: ['list', 'tabIndex'],
  components: { CheckModal },
  data() {
    return {
      tableHeight: 50,
      detail: {}
    }
  },
  mounted() {
    setTimeout(() => {
      this.tableHeight = window.innerHeight - 330
    }, 100)
  },
  methods: {
    spanMethod({ row, column, rowIndex, columnIndex }) {
      const rowS = this.list[rowIndex].rowSpan
      const colS = rowS > 0 ? 1 : 0
      if (columnIndex === 0 || columnIndex === 3 || columnIndex === 4) {
        return {
          rowspan: rowS,
          colspan: colS
        }
      }
    },
    handleEdit() {
      this.$emit('getChecklimitList')
    },
    // click edit
    async showEdit(id) {
      const res = await GetCheckItemsDetail({ liverKidneyDicId: id })
      if (res.code === 0) {
        this.detail = res.result[0]
        this.$refs.modal.isShowModal = true
        this.$refs.modal.getCheckItemsList()
        if (this.tabIndex === 0) {
          this.$refs.modal.getLiverList()
        } else if (this.tabIndex === 1) {
          this.$refs.modal.getDneydicList()
        }
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击删除
    handleDelete(ruleName) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delete(ruleName)
      }).catch(() => { })
    },
    async delete(id) {
      const res = await DelateCheckLimit({ liverKidneyDicId: id })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.$emit('getChecklimitList')
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
<style lang="less" scoped>
.radio-label {
  width: 90px;
}
</style>

