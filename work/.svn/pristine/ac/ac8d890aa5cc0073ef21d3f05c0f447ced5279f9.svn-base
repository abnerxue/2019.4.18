<template>
  <el-form
    ref="form"
    :inline="true"
    :model="form"
    :rules="rules"
    label-width="90px"
    size="small"
  >
    <el-form-item
      label="用户名"
      prop="name"
    >
      <el-input
        v-model="form.name"
        placeholder="用户名"
        style="width: 160px"
      ></el-input>
    </el-form-item>
    <el-form-item
      label="密码："
      prop="password"
    >
      <el-input
        v-model="form.password"
        placeholder="密码"
        style="width: 160px"
      ></el-input>
    </el-form-item>
    <el-form-item
      label="角色："
      prop="roleid"
    >
      <el-select
        v-model="form.roleid"
        placeholder="选择角色"
        style="width: 160px"
      >
        <el-option
          v-for="item in roleList"
          :key="item.roleId"
          :label="item.roleName"
          :value="item.roleId"
        >
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item style="margin-left: 12px">
      <el-button
        type="primary"
        @click="handleAdd"
      >添加</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import { AddUser } from '@/api/auth'

export default {
  props: ['roleList', 'id'],
  data() {
    return {
      form: {
        name: '',
        password: '',
        roleid: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        roleid: [
          { type: 'number', required: true, message: '请选择角色', trigger: 'change' }
        ]
      }
    }
  },
  watch: {
    id() {
      this.resetForm()
    }
  },
  methods: {
    resetForm() {
      this.$refs.form.resetFields()
    },
    // 添加
    handleAdd() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.add()
        } else {
          console.log('error submit!!')
        }
      })
    },
    async add() {
      this.form.deptid = this.id
      const res = await AddUser(this.form)
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.resetForm()
        this.$emit('getUserList')
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>
