<template>
  <el-card
    class="box-card"
    style="height: 100%; overflow-y: auto;"
  >
    <div
      slot="header"
      class="clearfix"
      style="height: 100%;"
    >
      <span>部门管理</span>
    </div>
    <div>
      <template>
        <el-button
          size="mini"
          type="primary"
          :disabled="isAdd"
          @click="handleAdd"
        >创建子节点</el-button>
        <el-button
          size="mini"
          type="success"
          @click="renameId = activeId"
        >重命名</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除</el-button>
      </template>
      <div class="space"></div>
      <p
        class="f3 pointer"
        :class="{'active' :isTrunkActive}"
        @click="clickTrunk"
        style="height: 30px;line-height: 30px;"
      ><i class="el-icon-caret-bottom"></i> 全部</p>
      <div
        v-for="(item, index) in deptList"
        :key="index"
        class="t-text pointer"
        @click="handleSwitch(item.deptId, item.deptName)"
      >
        <div v-if="renameId === item.deptId">
          <input
            class="f4 item"
            v-model="activeDeptName"
            placeholder="请输入内容"
          />
          <el-button
            size="mini"
            type="text"
            @click.stop="handleSubmit(item.deptId, activeDeptName)"
          >确定</el-button>
          <el-button
            size="mini"
            type="text"
            @click.stop="handleCancle(item.deptId)"
          >取消</el-button>
        </div>
        <p
          class="f4 item"
          :class="{'active': item.deptId === activeId}"
          v-else
        >{{item.deptName}}</p>
      </div>
    </div>
  </el-card>
</template>
<script>
import { UpdateDept, DeleteDept, AddDept } from '@/api/auth'

export default {
  props: ['deptList', 'currentId', 'userListLen'],
  data() {
    return {
      isTrunkActive: true,
      isAdd: false,
      activeId: 0,
      activeDeptName: '',
      renameId: null
    }
  },
  watch: {
    activeId(newVal, oldVal) {
      this.$emit('update:currentId', newVal)
    }
  },
  methods: {
    // 点击 '全部'
    clickTrunk() {
      this.isAdd = false
      this.isTrunkActive = true
      this.activeId = ''
      this.renameId = null
    },
    // 切换选项
    handleSwitch(id, name) {
      this.isTrunkActive = false
      this.isAdd = true
      this.activeId = id
      this.activeDeptName = name
      if (id && this.renameId === '') {
        this.deptList.splice(this.deptList.findIndex(item => item.deptId === ''), 1)
        this.renameId = null
      } else if (id && this.renameId && (id !== this.renameId)) {
        this.renameId = null
      }
    },
    // 创建子节点
    handleAdd() {
      this.isAdd = true
      this.deptList.push({ deptId: '', deptName: '' })
      this.renameId = ''
      this.activeDeptName = ''
    },
    // 点击 删除
    handleDelete() {
      if (this.userListLen) {
        this.$alert('无法删除：此节点下有用户！', '提示', {
          confirmButtonText: '确定',
          type: 'error'
        })
      } else {
        this.$confirm('是否确定删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.delete()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
    },
    // 确定 删除
    async delete() {
      const res = await DeleteDept({ deptId: this.activeId })
      if (res.code === 0) {
        this.$message.success(res.msg)
        await this.$emit('getDeptList')
        this.activeId = this.deptList[0].deptId
      } else {
        this.$message.error(res.msg)
      }
    },

    // 确定 重命名 || 新建部门
    async handleSubmit(deptId, deptName) {
      if (deptId && deptName) {
        const res = await UpdateDept({ deptId, deptName })
        if (res.code === 0) {
          this.$message.success(res.msg)
          this.renameId = null
          this.$emit('getDeptList')
          this.$emit('getUserList')
          this.activeId = deptId
        } else {
          this.$message.error(res.msg)
        }
      } else if (!deptId && deptName) {
        const res = await AddDept({ deptName })
        if (res.code === 0) {
          this.$message.success(res.msg)
          this.renameId = null
          this.$emit('getUserList')
          this.$emit('getDeptList')
          this.activeId = this.deptList[0].deptId
        } else {
          this.$message.error(res.msg)
        }
      } else {
        this.$message.error('名称不能为空')
      }
    },
    // 取消 重命名 || 新建部门
    handleCancle(id) {
      if (id === '') {
        this.isAdd = false
        this.renameId = null
        this.deptList.splice(this.deptList.findIndex(item => item.deptId === ''), 1)
      } else {
        this.activeId = id
        this.renameId = null
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

