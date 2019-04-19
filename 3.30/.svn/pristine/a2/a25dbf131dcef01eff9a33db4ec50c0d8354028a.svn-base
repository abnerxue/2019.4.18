<template>
  <div>
    <el-table
      :data="list"
      border
      size="small"
      style="width: 100%;"
      :height="tableHeight"
      :header-cell-class-name="tableHeaderName"
    >
      <el-table-column
        prop="ruleName"
        label="规则名称"
      ></el-table-column>
      <el-table-column
        prop=""
        label="最大用药天数"
      >
        <template slot-scope="scope">
          {{scope.row.lowerbound}}~{{scope.row.upperbound}}天
        </template>
      </el-table-column>
      <el-table-column
        prop=""
        label="审方等级"
        width="100"
      >
        <template slot-scope="scope">
          {{handleEnumLevel(scope.row.level)}}
        </template>
      </el-table-column>
      <el-table-column
        prop="enable"
        label="操作"
        align="center"
        width="80"
      >
        <template slot-scope="scope">
          <i
            @click="clickEdit(scope.row.id)"
            class="el-icon-edit pointer"
          ></i>
          <span
            class="d-inline-block"
            style="width: 8px;"
          ></span>
          <i
            @click="handleDelete(scope.row.id)"
            class="el-icon-delete text-red pointer"
          ></i>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="编辑规则"
      :visible.sync="editVisible"
      width="400px"
      :before-close="hideModal"
    >
      <el-form
        ref="form"
        label-width="120px"
        size="small"
        :rules="rules"
        :model="form"
      >
        <el-form-item
          label="规则名称："
          prop="ruleName"
        >
          <el-input
            v-model="form.ruleName"
            style="width: 199px"
            placeholder="输入规则名称"
          ></el-input>
        </el-form-item>

        <el-form-item label="最大用药天数：">
          <el-form-item
            prop="lowerbound"
            class="d-inline-block"
            style="margin-bottom: 0;"
          >
            <el-input
              v-model="form.lowerbound"
              type="number"
              style="width: 80px"
            ></el-input>
          </el-form-item>
          <span>~</span>
          <el-form-item
            prop="upperbound"
            class="d-inline-block"
            style="margin-bottom: 0;"
          >
            <el-input
              v-model="form.upperbound"
              type="number"
              style="width: 80px"
            ></el-input>
          </el-form-item>
          <span>天</span>
        </el-form-item>
        <el-form-item
          label="审方等级："
          prop="level"
        >
          <el-select
            v-model="form.level"
            placeholder="选择审方等级"
          >
            <el-option
              v-for="(item, index) in auditlevel"
              :key="index"
              :label="item.desp"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="hideModal">取消</el-button>
          <el-button
            type="primary"
            :loading="loading"
            @click="handleSubmit"
          >确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { Deleteduration, GetDurationInfo, Updateduration } from '@/api/dataMana'

export default {
  props: ['auditlevel', 'list'],
  data() {
    return {
      tableHeight: 50,
      loading: false,
      editVisible: false,
      form: {},
      rules: {
        ruleName: [
          { required: true, message: '请输入规则名称', trigger: 'blur' }
        ],
        lowerbound: [
          { required: true, message: '请输入最少用药天数', trigger: 'blur' }
        ],
        upperbound: [
          { required: true, message: '请选择最大用药天数', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '请选择审方等级', trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
    setTimeout(() => {
      this.tableHeight = window.innerHeight - 330
    }, 100)
  },
  methods: {
    async clickEdit(id) {
      this.editVisible = true
      const res = await GetDurationInfo({ id })
      if (res.code === 0) {
        this.form = res.result[0]
      } else {
        this.$message.error(res.msg)
      }
    },
    hideModal() {
      this.$refs.form.resetFields()
      this.editVisible = false
    },
    handleSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.handleEdit()
        } else {
          console.log('error submit!!')
        }
      })
    },
    async handleEdit() {
      this.loading = true
      const res = await Updateduration(this.form)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.hideModal()
        this.$emit('getList')
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击删除
    handleDelete(id) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delete(id)
      }).catch(() => { })
    },
    async delete(id) {
      const res = await Deleteduration({ id })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.$emit('getList')
      } else {
        this.$message.error(res.msg)
      }
    },
    // 处理枚举
    handleEnumLevel(id) {
      return this.auditlevel.filter(step => id === step.id)[0].desp
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

