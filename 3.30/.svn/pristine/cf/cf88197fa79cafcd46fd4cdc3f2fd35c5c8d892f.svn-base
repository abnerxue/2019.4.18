<template>
  <div style="height: 100%">
    <el-row
      :gutter="20"
      style="height: 100%"
    >
      <el-col
        :span="9"
        style="height: 100%;"
      >
        <DeptTree
          :deptList="deptList"
          :currentId.sync="currentId"
          :userListLen="userListLen"
          @getDeptList="getDeptList"
          @getUserList="getUserList"
        />
      </el-col>
      <el-col
        :span="15"
        style="height: 100%;"
      >
        <el-card
          class="box-card"
          style="height: 100%;"
        >
          <div
            slot="header"
            class="clearfix"
          >
            <span>用户管理</span>
          </div>
          <div v-if="currentId">
            <AddUser
              :roleList="roleList"
              :id="currentId"
              @getUserList="getUserList"
            />
            <div class="l-dashed"></div>
            <RoleTable
              :userList="userList"
              :roleList="roleList"
              :deptList="deptList"
              :id="currentId"
              @getUserList="getUserList"
            />
          </div>
          <p
            class="f4 text-gray"
            v-else
          >请先选择部门！</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { RoleName, AllUser, AllDept } from '@/api/auth'
import DeptTree from './components/DeptTree'
import RoleTable from './components/RoleTable'
import AddUser from './components/AddUser'

export default {
  name: 'role',
  components: { DeptTree, AddUser, RoleTable },
  data() {
    return {
      isTrunkActive: true,
      isAdd: false,
      currentId: '',
      renameId: '',
      deptList: [],
      userList: [],
      userListLen: '',
      roleList: []
    }
  },
  created() {
    this.getRoleList()
    this.getDeptList()
  },
  watch: {
    currentId() {
      this.getUserList()
    }
  },
  methods: {
    async getRoleList() {
      const res = await RoleName()
      if (res.code === 0) {
        this.roleList = res.result
      }
    },
    async getUserList(value) {
      const args = {
        name: value,
        deptId: this.currentId
      }
      const res = await AllUser(args)
      if (res.code === 0) {
        this.userList = res.result
      } else {
        this.userList = []
        this.$message.error(res.msg)
      }
      this.userListLen = this.userList.length
    },
    async getDeptList() {
      const res = await AllDept()
      if (res.code === 0) {
        this.deptList = res.result
      }
    }
  }
}
</script>

<style lang="less" scoped>
.item {
  height: 31px;
  line-height: 31px;
  padding-left: 15px;
}
.t-text {
  border: none;
}
.t-input {
  border-width: 1px;
}
.active {
  background-color: #2f71a9;
  color: #fff;
}
</style>

