<template>
  <div id="user">
    <el-row>
      <el-button
        type="primary"
        size="small"
        @click="visibleModal = true"
      >新建角色</el-button>
    </el-row>
    <el-table
      :data="list"
      border
      :header-cell-class-name="tableHeaderName"
      style="width: 100%; margin-top: 20px"
      size="small"
      :height="tableHeight"
    >
      <el-table-column
        prop="roleName"
        label="角色名称"
        align="center"
      ></el-table-column>
      <el-table-column
        v-for="(item,index) in permissionList"
        :key="index"
        :prop="item.prop"
        :label="item.moduleName"
        align="center"
      >
        <template slot-scope="scope">
          <i :class="{'el-icon-error text-red': scope.row[item.prop] == 0, 'el-icon-success':scope.row[item.prop] == 1}"></i>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="100"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleEdit(scope.row.roleId)"
          >编辑</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleDelete(scope.row.roleId)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :title="modalTitle"
      :visible.sync="visibleModal"
      width="500px"
      :before-close="hideAdd"
    >
      <el-row>
        <el-col
          :span="4"
          style="line-height: 30px;"
        >角色名称：</el-col>
        <el-col :span="12">
          <el-input
            size="mini"
            v-model="roleName"
            placeholder="请输入内容"
          ></el-input>
        </el-col>
      </el-row>
      <div class="space"></div>
      <el-checkbox-group v-model="checkList">
        <el-checkbox
          v-for="(item, index) in permissionList"
          :key="index"
          :label="item.id"
          style="width: 40%; margin-bottom:5px;"
        >{{item.moduleName}}</el-checkbox>
      </el-checkbox-group>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          size="small"
          @click="hideAdd"
        >取 消</el-button>
        <el-button
          type="primary"
          size="small"
          @click="handleAdd"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { Permission, AllRolePer, AddRole, DeleteRole, GetRolePer, UpdateRole } from '@/api/auth'

export default {
  data() {
    return {
      tableHeight: 50,
      roleId: '',
      roleName: '',
      visibleModal: false,
      modalTitle: '新建角色',
      isEditId: '', // 是否编辑状态
      checkList: [],
      permissionList: [],
      list: [],
      positionKey: [
        {
          prop: 'a2',
          width: '',
          label: '查看'
        },
        {
          prop: 'a3',
          width: '180',
          label: '报表'
        },
        {
          prop: 'a4',
          width: '',
          label: '数据维护'
        },
        {
          prop: 'a5',
          width: '',
          label: '部门与用户管理'
        },
        {
          prop: 'a6',
          width: '',
          label: '角色管理'
        },
        {
          prop: 'a7',
          width: '',
          label: '系统设置'
        },
        {
          prop: 'a8',
          width: '',
          label: '审方中心启用'
        }
      ]
    }
  },
  created() {
    this.getPermissionList()
    this.getList()
  },
  mounted() {
    setTimeout(() => {
      this.tableHeight = window.innerHeight - 230
    }, 100)
  },
  methods: {
    // 新建 || 编辑
    async handleAdd() {
      if (this.roleName === '') {
        this.$message.error('角色名称不能为空')
      } else {
        const args = {
          roleName: this.roleName,
          pers: this.checkList.toString()
        }
        let res
        if (this.modalTitle === '新建角色') {
          res = await AddRole(args)
        } else if (this.modalTitle === '编辑角色') {
          args.roleId = this.roleId
          res = await UpdateRole(args)
        }
        if (res.code === 0) {
          this.$message.success(res.msg)
          this.visibleModal = false
          this.resetForm()
          this.getList()
        } else {
          this.$message.error(res.msg)
        }
      }
    },
    hideAdd() {
      this.visibleModal = false
      this.resetForm()
    },
    resetForm() {
      this.modalTitle = '新建角色'
      this.roleName = ''
      this.checkList = []
    },
    // 编辑
    async handleEdit(roleId) {
      const res = await GetRolePer({ roleId })
      if (res.code === 0) {
        this.roleName = res.roleName
        this.checkList = res.pres
        this.roleId = roleId
        this.modalTitle = '编辑角色'
        this.visibleModal = true
      } else {
        this.$message.error(res.msg)
      }
    },
    // 删除
    handleDelete(id) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delete(id)
      }).catch(() => { })
    },
    async delete(roleId) {
      const res = await DeleteRole({ roleId })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.getList()
      } else {
        this.$message.error(res.msg)
      }
    },
    async getList() {
      const res = await AllRolePer()
      if (res.code === 0) {
        this.list = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    async getPermissionList() {
      const res = await Permission()
      if (res.code === 0) {
        this.permissionList = res.result
        if (this.permissionList && this.permissionList.length > 0) {
          this.permissionList.forEach((item) => {
            Object.assign(item, { prop: `permissionName${item.id}` })
          })
        }
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

