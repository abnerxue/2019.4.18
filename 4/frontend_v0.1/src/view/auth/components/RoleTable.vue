<template>
  <div>
    <el-form
      :inline="true"
      size="small"
      class="text-right"
    >
      <el-form-item>
        <el-input
          v-model="userName"
          placeholder="输入用户名查找"
          clearable
          @clear="handleSearch"
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
      :data="userList"
      border
      style="width: 100%;"
      :height="tableHeight"
      :header-cell-class-name="tableHeaderName"
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
      width="400px"
    >
      <el-form
        ref="form"
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
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="密码："
          prop="password"
        >
          <el-input
            type="password"
            v-model="form.password"
            placeholder="密码"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="部门："
          prop="deptid"
        >
          <el-select
            v-model="form.deptid"
            placeholder="部门"
            style="width: 200px"
          >
            <el-option
              v-for="item in deptList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="角色："
          prop="roleid"
        >
          <el-select
            v-model="form.roleid"
            placeholder="角色"
            style="width: 200px"
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
        <el-form-item>
          <el-button @click="hideModal">取 消</el-button>
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
import { UpdateUser, DeleteUser } from '@/api/auth'

export default {
  props: ['id', 'deptList', 'userList', 'roleList'],
  data() {
    return {
      tableHeight: 50,
      editVisible: false,
      userName: '',
      form: {
        name: '',
        password: '',
        roleid: '',
        deptid: ''
      },
      positionKey: [
        {
          prop: 'name',
          width: '',
          label: '用户名'
        },
        {
          prop: 'deptName',
          width: '',
          label: '部门'
        },
        {
          prop: 'roleName',
          width: '',
          label: '角色'
        }
      ],
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        deptid: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
        roleid: [
          { required: true, message: '请选择角色', trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
    setTimeout(() => {
      this.tableHeight = window.innerHeight - 450
    }, 100)
  },
  methods: {
    // 搜索
    handleSearch() {
      this.$emit('getUserList', this.userName)
    },
    hideModal() {
      this.editVisible = false
      this.$refs.form.resetFields()
    },
    // 点击 编辑
    showEdit(row) {
      this.form = row
      this.editVisible = true
    },
    // 确定 编辑
    handleEdit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.edit()
        } else {
          console.log('error submit!!')
        }
      })
    },
    async edit() {
      const res = await UpdateUser(this.form)
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.editVisible = false
        this.$emit('getUserList')
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击 删除
    handleDelete(row) {
      this.$confirm('是否确定删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delete(row.id)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 确定 删除
    async delete(id) {
      const res = await DeleteUser({ id })
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.$emit('getUserList')
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

