<template>
  <el-dialog :title="title" :visible.sync="isShowModal" width="700px" :before-close="hideModal">
    <el-form ref="form" label-width="120px" size="small" :rules="rules" :model="form">
      <el-form-item label="检验项目：">
        <el-row>
          <el-col :span="2" class="text-center">序号</el-col>
          <el-col :span="8" class="text-center">项目名称</el-col>
          <el-col :span="8" class="text-center">区间</el-col>
          <el-col :span="2"></el-col>
        </el-row>
        <el-row v-for="(item, index) in form.checkLimit" :key="index">
          <el-col :span="2" class="text-center">{{index + 1}}</el-col>
          <el-col :span="8" class="text-center">
            <el-select v-model="item.checkItemsId" placeholder="检查项目" style="width: 160px">
              <el-option v-for="i in checkItemsList" :key="i.name" :label="i.name" :value="i.id"></el-option>
            </el-select>
          </el-col>
          <el-col :span="10" class="text-center">
            <el-form-item
              class="d-inline-block"
              :prop="'checkLimit.' + index + '.lowerbound'"
              :rules="{ required: true, message: '不能为空', trigger: 'blur'}"
            >
              <el-input v-model="item.lowerbound" style="width: 60px"></el-input>
            </el-form-item>
            <span>~</span>
            <el-form-item
              class="d-inline-block"
              :prop="'checkLimit.' + index + '.upperbound'"
              :rules="{ required: true, message: '不能为空', trigger: 'blur'}"
            >
              <el-input v-model="item.upperbound" style="width: 60px"></el-input>
            </el-form-item>
            <span>{{item.unitName}}</span>
          </el-col>
          <el-col :span="2">
            <i @click="clickAdd" class="el-icon-plus pointer"></i>
            <span class="d-inline-block" style="width: 8px;"></span>
            <i
              v-if="index !== 0"
              @click="handleDelete(index)"
              class="el-icon-delete text-red pointer"
            ></i>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="判断结果：" prop="liverKidneyDicId">
        <el-select v-model="form.liverKidneyDicId" placeholder="选择判断结果">
          <el-option
            v-for="(item, index) in resultList"
            :key="index"
            :label="item.description"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="hideModal">取消</el-button>
        <el-button type="primary" :loading="loading" @click="clickSubmit">确定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { AddCheckLimit, updateCheckLimit, GetCheckItems, GetLiverList, GetkiDneydicList } from '@/api/setting'

export default {
  props: ['detail', 'title', 'type'],
  data() {
    return {
      isShowModal: false,
      loading: false,
      checkItemsList: [], // 检查项目列表
      resultList: [], // 判断结果(肝功能列表 || 肾功能列表)
      form: {
        liverKidneyDicId: '',
        checkLimit: [
          {
            id: null,
            checkItemsId: '',
            unitName: '',
            lowerbound: '',
            upperbound: ''
          }
        ]
      },
      rules: {
        liverKidneyDicId: [
          { required: true, message: '选择判断结果', trigger: 'change' }
        ]
      }
    }
  },
  watch: {
    detail() {
      this.form = this.detail || this.form
    }
  },
  methods: {
    hideModal() {
      this.isShowModal = false
      this.$refs.form.resetFields()
      this.form.checkLimit = [
        {
          id: null,
          checkItemsId: this.checkItemsList[0].id,
          unitName: this.checkItemsList[0].unitName,
          lowerbound: '',
          upperbound: ''
        }
      ]
    },
    handleDelete(rowIndex) {
      this.form.checkLimit.splice(rowIndex, 1)
    },
    clickSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.type === 'add') {
            this.handleAdd()
          } else if (this.type === 'edit') {
            this.handleEdit()
          }
        } else {
          console.log('error submit!!')
        }
      })
    },
    clickAdd() {
      this.form.checkLimit.push({
        id: null,
        checkItemsId: this.checkItemsList[0].id,
        unitName: this.checkItemsList[0].unitName,
        lowerbound: '',
        upperbound: ''
      })
    },
    // add
    async handleAdd(val) {
      this.loading = true
      const res = await AddCheckLimit(this.form)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.hideModal()
        this.$emit('getChecklimitList')
      } else {
        this.$message.error(res.msg)
      }
    },
    // edit
    async handleEdit(val) {
      this.loading = true
      const res = await updateCheckLimit(this.form)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.hideModal()
        this.$emit('handleEdit')
      } else {
        this.$message.error(res.msg)
      }
    },
    // 获取肝功能列表
    async getLiverList() {
      const res = await GetLiverList()
      if (res.code === 0) {
        // this.liverList = res.result
        this.resultList = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 获取肾功能列表
    async getDneydicList() {
      const res = await GetkiDneydicList()
      if (res.code === 0) {
        // this.dneydicList = res.result
        this.resultList = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 检查项目列表
    async getCheckItemsList() {
      const res = await GetCheckItems()
      if (res.code === 0) {
        this.checkItemsList = res.result
        this.form.checkLimit[0].checkItemsId = this.checkItemsList[0].id
        this.form.checkLimit[0].unitName = this.checkItemsList[0].unitName
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>
