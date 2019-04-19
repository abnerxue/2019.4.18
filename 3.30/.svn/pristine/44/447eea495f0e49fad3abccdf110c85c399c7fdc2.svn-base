<template>
  <div>
    <el-table
      ref="table"
      :data="list"
      border
      size="small"
      :height="tableHeight"
      style="width: 100%;"
      :span-method="spanMethod"
      :header-cell-class-name="tableHeaderName"
    >
      <el-table-column
        prop="ruleName"
        label="规则名称"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="检验项目"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="区间"
        align="center"
      >
        <template slot-scope="scope">
          {{scope.row.lowerbound}}
          ~
          {{scope.row.upperbound}}
          {{scope.row.unitName}}
        </template>
      </el-table-column>
      <el-table-column
        prop="level"
        label="审方等级"
        width="100"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="enable"
        label="操作"
        align="center"
        width="80"
      >
        <template slot-scope="scope">
          <i
            @click="showEdit(scope.row)"
            class="el-icon-edit pointer"
          ></i>
          <span
            class="d-inline-block"
            style="width: 8px;"
          ></span>
          <i
            @click="handleDelete(scope.row.ruleName)"
            class="el-icon-delete text-red pointer"
          ></i>
        </template>
      </el-table-column>
    </el-table>
    <CheckModal
      ref="modal"
      :title="'编辑规则'"
      :detail="detail"
      @click="handleEdit"
    />
  </div>
</template>

<script>
import { DeleteMedicinelimit, GetMedicinelimit, UpdateMedicinelimit } from '@/api/dataMana'
import CheckModal from './CheckModal'

export default {
  props: ['list', 'choose', 'type'],
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
    // click edit
    async showEdit(row) {
      const args = {
        medicineId: this.choose.id,
        type: this.type,
        ruleName: row.ruleName,
        ruleId: row.ruleId
      }
      const res = await GetMedicinelimit(args)
      if (res.code === 0) {
        this.detail = res.result
        this.$refs.modal.isShowModal = true
      } else {
        this.$message.error(res.msg)
      }
    },
    // edit
    async handleEdit(val) {
      this.$refs.modal.loading = true
      const res = await UpdateMedicinelimit(val)
      this.$refs.modal.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.$refs.modal.hideModal()
        this.$emit('getAllMedicinelimit')
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
    async delete(ruleName) {
      const res = await DeleteMedicinelimit({ ruleName })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.$emit('getAllMedicinelimit')
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

