<template>
  <div>
    <el-form
      :inline="true"
      :model="form"
      label-width="70px"
    >
      <el-form-item label="用户名：">
        <el-input
          size="small"
          v-model="form.name"
          placeholder="用户名"
          style="width: 140px"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码：">
        <el-input
          size="small"
          v-model="form.password"
          placeholder="密码"
          style="width: 140px"
        ></el-input>
      </el-form-item>
      <el-form-item label="角色：">
        <el-select
          size="small"
          v-model="form.roleid"
          placeholder="选择角色"
          style="width: 140px"
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
          size="small"
          type="primary"
          @click="handleAdd"
        >添加</el-button>
      </el-form-item>
    </el-form>
    <div class="l-dashed"></div>
    <el-form
      :inline="true"
      :model="user"
      size="small"
      class="text-right"
    >
      <el-form-item>
        <el-input
          v-model="user"
          placeholder="输入用户名查找"
        ></el-input>
      </el-form-item>
      <el-form-item style="margin-right: 0">
        <el-button
          type="primary"
          @click="handleSearch"
        >搜索</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      border
      style="width: 100%; margin-top: 12px;"
    >
      <el-table-column
        fixed
        type="index"
        width="50"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column
        v-for="(item,index) in positionKey"
        :key="index"
        :prop="item.prop"
        :label="item.label"
        :width="item.width"
        align="center"
      ></el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="90"
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            @click="showEdit(scope.row)"
            type="text"
            size="small"
          >编辑</el-button>
          <el-button
            type="text"
            size="small"
            class="text-red"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="编辑"
      :visible.sync="editVisible"
      width="350px"
      :before-close="handleClose"
    >
      <el-form
        :model="form"
        label-width="80px"
        size="small"
      >
        <el-form-item label="用户名：">
          <el-input
            size="small"
            v-model="form.name"
            placeholder="用户名"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <div class="space"></div>
        <el-form-item label="密码：">
          <el-input
            size="small"
            v-model="form.password"
            placeholder="密码"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <div class="space"></div>
        <el-form-item label="角色：">
          <el-select
            size="small"
            v-model="form.role"
            placeholder="角色"
            style="width: 200px"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <div class="space"></div>
        <el-form-item>
          <el-button @click="editVisible = false">取 消</el-button>
          <el-button
            type="primary"
            @click="handleEdit"
          >确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { AllUser, UpdateUser, DeleteUser, AddUser } from '@/api/auth'

export default {
  props: ['deptList', 'id'],
  data() {
    return {
      form: {
        name: '',
        password: '',
        roleid: '',
        deptid: ''
      }
    }
  },
  methods: {
    // 添加
    async handleAdd() {
      this.form.deptid = this.id
      console.log('form: ', this.form)
      const res = await AddUser(this.form)
      if (res.code === 0) {
        this.$message.success(res.msg)
        // this.getList()
      } else {
        this.$message.error(res.msg)
      }
    },
    async getList() {
      const res = AllUser()
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.getDeptList()
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>
