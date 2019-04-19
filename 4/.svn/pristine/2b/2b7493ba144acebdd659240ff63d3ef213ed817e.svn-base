<template>
  <div
    id="prescription"
    style="height: 100%;"
  >
    <el-row
      :gutter="20"
      style="height: 100%;"
    >
      <el-col
        :span="12"
        style="height: 100%;"
      >
        <el-card
          class="box-card"
          style="height: 100%; overflow-y: auto;"
        >
          <div
            slot="header"
            class="clearfix"
          >
            <span>机构列表</span>
          </div>
          <template>
            <div id="buttons">
              <el-button
                type="primary"
                size="small"
                @click="addDistrict"
                :disabled="isAddNode"
              >新增子节点</el-button>
              <el-button
                type="primary"
                size="small"
                :disabled="isDisabled"
                @click="resetNodeName"
              >重命名</el-button>
              <el-button
                type="primary"
                size="small"
                :disabled="isDisabled"
                @click="nodeDelete"
              >删除</el-button>
              <el-button
                type="primary"
                size="small"
                :disabled="isAddDisabled"
                @click="openAddUnit"
              >新增医院</el-button>
            </div>
            <div class="space"></div>
            <p
              class="f3 pointer"
              style="margin-bottom: 6px;"
              :class="{'trunk-active' :isTrunkActive}"
              @click="clickTrunk"
            >
              <i class="el-icon-caret-bottom"></i>苏州市卫生健康委员会
            </p>
            <el-tree
              ref="tree"
              :data="data"
              node-key="id"
              @node-click="handleNodeClick"
              :current-node-key="selectedNodeId"
            >
              <span
                class="custom-tree-node"
                slot-scope="{ node, data }"
              >
                <el-input
                  v-if="data.input"
                  size="mini"
                  class="reset-name-input"
                  v-model="resetInputNodeName"
                  placeholder="请输入内容"
                ></el-input>
                <span v-show="!data.input">{{ node.label }}</span>
                <span v-show="data.input">
                  <el-button
                    type="text"
                    size="mini"
                    @click.stop="resetSubmit"
                  >
                    确认
                  </el-button>
                  <el-button
                    type="text"
                    size="mini"
                    @click.stop="resetCancel"
                  >
                    撤销
                  </el-button>
                </span>
              </span>
            </el-tree>
          </template>
        </el-card>
      </el-col>
      <el-col
        :span="12"
        style="height: 100%;"
      >
        <el-card
          class="box-card bg-white"
          style="height: 100%; overflow-y: auto;    z-index: 99999;"
        >
          <div
            slot="header"
            class="clearfix d-flex"
          >
            <div
              class="flex-fill"
              style="line-height: 32px;"
            >
              <span>审方中心列表 </span>
              <span
                v-if="chooseUnit.name"
                class="f1 font-bold"
              >【 {{chooseUnit.name}} 】</span>
            </div>
            <el-button
              v-if="chooseUnit.name"
              type="primary"
              size="small"
              @click="isAdd = true"
            >新建审方中心</el-button>
          </div>
          <template v-if="chooseUnit.name">
            <div
              class="f6"
              v-if="(auditCenterlist && auditCenterlist.length === 0) && !isAdd"
            >暂无数据!</div>
            <el-card
              v-if="isAdd"
              shadow="hover"
              style="margin-bottom: 10px;"
            >
              <el-form
                size="small"
                label-width="110px"
                :model="addForm"
                :rules="rules"
                ref="addForm"
              >
                <el-form-item
                  label="审方中心名称"
                  prop="auditCenterName"
                  style="margin-bottom: 20px !important;"
                >
                  <el-input v-model="addForm.auditCenterName"></el-input>
                </el-form-item>
                <el-form-item
                  label="IP地址"
                  prop="ip"
                  style="margin-bottom: 20px !important;"
                >
                  <el-input v-model="addForm.ip"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button @click="isAdd = false">取消</el-button>
                  <el-button
                    type="primary"
                    :loading="isLoading"
                    @click="checkAuditCenter('addForm')"
                  >连接</el-button>
                </el-form-item>
              </el-form>
            </el-card>
            <el-card
              shadow="hover"
              v-for="(item, index) in auditCenterlist"
              :key="index"
              style="margin-bottom: 10px;"
            >
              <el-form
                size="small"
                label-width="110px"
              >
                <el-form-item label="审方状态">
                  <el-radio-group
                    v-model="item.platformAuditState"
                    @change="changeRadio($event, item.id)"
                  >
                    <el-radio :label="true">启用区域审方</el-radio>
                    <el-radio :label="false">停用区域审方</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-form>
              <el-form
                size="small"
                label-width="110px"
                :model="item"
              >
                <el-form-item label="审方中心名称">
                  <el-input
                    :disabled="isEdit !== item.id"
                    v-model="item.auditCenterName"
                  ></el-input>
                </el-form-item>
                <el-form-item label="IP地址">
                  <el-input
                    :disabled="isEdit !== item.id"
                    v-model="item.ip"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <template v-if="isEdit === item.id">
                    <el-button @click="cancelAuditCenter(item,index)">取消</el-button>
                    <el-button
                      type="primary"
                      :loading="loading === item.id"
                      @click="saveAuditCenter(item,index)"
                    >保存</el-button>
                  </template>
                  <el-button
                    v-if="isEdit !== item.id"
                    type="primary"
                    @click="editAuditCenter(item)"
                  >编辑</el-button>
                  <el-button
                    type="danger"
                    :loading="loading === item.id"
                    @click="clickDeletAuditCenter(item)"
                  >删除</el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </template>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { AllAuditCenter, AllDistrict, AllUnit } from '@/api/global'
import { LinkAuditCenter, DeleteDistrict, UpdateDistrict, AddDistrict, UpdateHospital, DeleteHospital, AddHospital, UpdateAuditCenter, AddAuditCenter, DeleteAuditCenter } from '@/api/center'

export default {
  name: 'prescription',
  data() {
    return {
      isTrunkActive: true,
      isLoading: false,
      loading: '',
      buttonText: '',
      isEdit: '',
      isAddNode: false,
      isDisabled: true,
      isAddDisabled: true,
      currentNode: null,
      resetInputNodeName: '',
      selectedNodeId: 1,
      chooseUnit: {
        id: '',
        name: ''
      },
      isAdd: false,
      defaultForm: {},
      addForm: {
        auditCenterName: '',
        ip: ''
      },
      rules: {
        auditCenterName: [{
          required: true,
          message: '请输入审方中心名称',
          trigger: 'blur'
        }],
        ip: [{
          required: true,
          message: '请输入ip',
          trigger: 'blur'
        }]
      },
      auditCenterlist: [], // 审方中心
      districtList: [], // 平台区域
      unitList: [], // 医院
      data: []
    }
  },
  async created() {
    await this.getAllDistrict() // 获取区域
    await this.getAllUnit() // 获取医院
    await this.formatList() // 树列表
  },
  methods: {

    // 修改 审方状态
    async changeRadio(val, id) {
      const res = await UpdateAuditCenter({
        id,
        platformAuditState: Number(val)
      })
      if (res.code === 0) {
        this.$message.success('操作成功!')
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击 连接
    checkAuditCenter(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.isLoading = true
          setTimeout(() => {
            this.handleLink()
          }, 1000)
        } else {
          console.log('error submit!!')
        }
      })
    },
    // 连接审方中心
    async handleLink() {
      const res = await LinkAuditCenter({ ip: this.addForm.ip })
      this.isLoading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        setTimeout(() => {
          this.addAuditCenter()
        }, 1000)
      } else {
        this.$message.error(res.msg)
      }
    },
    // 增加审方中心
    async addAuditCenter() {
      this.addForm.unitNameId = this.chooseUnit.id
      const res = await AddAuditCenter(this.addForm)
      if (res.code === 0) {
        this.$message.success('添加成功!')
        this.isAdd = false
        this.$refs.addForm.resetFields()
        this.getAllAuditCenter({ id: this.chooseUnit.id })
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击 取消 编辑
    cancelAuditCenter(item, index) {
      this.$set(this.auditCenterlist[index], 'ip', item._ip)
      this.$set(this.auditCenterlist[index], 'auditCenterName', item._auditCenterName)
      this.isEdit = ''
    },
    // 点击 编辑
    editAuditCenter(item) {
      if (this.isEdit) {
        let obj = this.auditCenterlist.find(j => j.id === this.isEdit)
        if (obj) {
          this.$set(obj, 'ip', obj._ip)
          this.$set(obj, 'auditCenterName', obj._auditCenterName)
        }
      }
      this.isEdit = item.id
    },
    // 更新审方中心
    async saveAuditCenter(item, index) {
      if (item.auditCenterName === '' || item.ip === '') {
        this.$message.error('内容不可为空')
      } else {
        this.loading = item.id
        const args = {
          id: item.id,
          auditCenterName: item.auditCenterName,
          ip: item.ip,
          platformAuditState: item.platformAuditState
        }
        const res = await UpdateAuditCenter(args)
        this.loading = ''
        this.isEdit = ''
        if (res.code === 0) {
          this.$message.success('修改成功!')
          this.auditCenterlist[index]._ip = item.ip
          this.auditCenterlist[index]._auditCenterName = item.auditCenterName
        } else {
          this.$message.error(res.msg)
        }
      }
    },
    // 点击 删除审方中心
    clickDeletAuditCenter(item) {
      this.$confirm('是否确定删除', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = item.id
        this.deteleAuditCenter(item)
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
    // 删除审方中心
    async deteleAuditCenter(arg) {
      const res = await DeleteAuditCenter({ id: arg.id })
      if (res.code === 0) {
        this.loading = ''
        this.$message.success('删除成功!')
        this.getAllAuditCenter({ id: this.chooseUnit.id })
      } else {
        this.loading = ''
        this.$message.error(res.msg)
      }
    },
    // 点击 增加医院
    openAddUnit() {
      this.$prompt('医院名称', '新增医院', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        if (value === null) {
          this.$message.error('请输入医院名称')
        } else {
          const args = {
            unitName: value,
            districtId: this.selectedNodeData.id
          }
          this.addHospital(args)
        }
      }).catch(() => {
        this.$message.info('取消输入！')
      })
    },
    // 增加医院
    async addHospital(args) {
      const res = await AddHospital(args)
      if (res.code === 0) {
        this.$message.success('添加成功！')
        await this.getAllDistrict()
        await this.getAllUnit()
        await this.formatList()
      } else {
        this.$message.error(res.msg)
      }
    },

    // 点击 '苏州市卫生健康委员会'
    clickTrunk() {
      this.isTrunkActive = true
      this.isAddDisabled = true
      this.isDisabled = true
      this.isAddNode = false
      this.chooseUnit = { id: '', name: '' }
      this.selectedNodeData && this.selectedNodeData.input && this.$set(this.selectedNodeData, 'input', false)
    },
    // 点击 树节点
    handleNodeClick(data) {
      this.isDisabled = false
      this.isTrunkActive = false
      this.isAddNode = true
      this.isAdd = false
      if (data.type === 'district') {
        this.isAddDisabled = false
        this.chooseUnit = { id: '', name: '' }
      } else {
        this.isAddDisabled = true
        const cur = this.$refs.tree.getCurrentNode()
        const args = { id: cur.id }
        this.getAllAuditCenter(args)
        this.chooseUnit = {
          id: cur.id,
          name: cur.label
        }
      }
      if (this.currentNode && this.currentNode.label === '' && data.id === '') {
        this.selectedNodeData && this.selectedNodeData.input && this.$set(this.selectedNodeData, 'input', true)
      } else if (this.currentNode && this.currentNode.label === '' && data.id !== '') {
        this.data.splice(this.data.findIndex(item => item.id === ''), 1)
        this.currentNode = null
      } else if (this.currentNode && (data.id === this.currentNode.id) && (data.label === this.currentNode.label)) {
        this.selectedNodeData && this.selectedNodeData.input && this.$set(this.selectedNodeData, 'input', true)
      } else {
        this.selectedNodeData && this.selectedNodeData.input && this.$set(this.selectedNodeData, 'input', false)
      }
      this.selectedNodeData = data
    },
    // 创建子节点
    async addDistrict() {
      this.resetInputNodeName = ''
      const obj = {
        children: [],
        id: '',
        label: '',
        input: true,
        type: 'district'
      }
      this.data.push(obj)
      this.currentNode = obj
    },
    // 点击 删除 节点
    nodeDelete() {
      // 先判断是否选中节点
      if (!this.selectedNodeData) this.$message({ type: 'warning', message: '请先选择要删除的节点' })
      if (this.selectedNodeData.children && this.selectedNodeData.children.length > 0) {
        this.$alert('无法删除：此节点下有子级！', '提示', {
          confirmButtonText: '确定',
          type: 'error'
        })
      } else if (this.chooseUnit.length > 0) {
        this.$alert('无法删除：此节点下有审方中心！', '提示', {
          confirmButtonText: '确定',
          type: 'error'
        })
      } else {
        this.$confirm(`是否确定删除"${this.selectedNodeData.label}"节点?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.currentNode = this.$refs.tree.getCurrentNode()
          this.handleDetele()
        }).catch(() => {
          this.$message.info('已取消删除')
        })
      }
    },
    // 删除 节点
    async handleDetele() {
      let res
      if (this.currentNode.type === 'district') {
        // 删除区域
        res = await DeleteDistrict({ districtId: this.currentNode.id })
      } else if (this.currentNode.type === 'unit') {
        // 删除医院
        res = await DeleteHospital({ id: this.currentNode.id })
      }
      if (res && res.code === 0) {
        this.$message.success('删除成功!')
        this.chooseUnit = {
          id: '',
          name: ''
        }
        await this.getAllDistrict()
        await this.getAllUnit()
        await this.formatList()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击 重命名
    resetNodeName() {
      this.currentNode = this.$refs.tree.getCurrentNode()
      this.resetInputNodeName = this.selectedNodeData.label
      this.selectedNodeData && this.$set(this.selectedNodeData, 'input', true)
      // console.log(this.selectedNodeData)
      this.$nextTick(() => {
        const input = document.querySelector('.reset-name-input input')
        input && input.focus()
      })
    },
    // 点击 确定 (重命名/新增区域)
    async resetSubmit(data) {
      if (this.resetInputNodeName === '') {
        this.$message.error('请输入内容')
      } else {
        let res
        if (this.currentNode.type === 'district' && this.currentNode.id) {
          // 修改 区域
          const args = {
            districtId: this.currentNode.id,
            districtName: this.resetInputNodeName
          }
          res = await UpdateDistrict(args)
        } else if (this.currentNode.type === 'unit') {
          // 修改 医院名字
          const args = {
            id: this.currentNode.id,
            unitName: this.resetInputNodeName
          }
          res = await UpdateHospital(args)
        } else {
          // 增加区域
          res = await AddDistrict({ districtName: this.resetInputNodeName })
        }
        if (res.code === 0) {
          if (this.currentNode.type === 'district' && !this.currentNode.id) {
            this.$message.success('添加成功!')
            await this.getAllDistrict()
            await this.formatList()
          } else {
            this.$message.success('修改成功!')
            this.chooseUnit.name = this.resetInputNodeName
          }
          this.selectedNodeData.label = this.resetInputNodeName
          this.selectedNodeData && this.$set(this.selectedNodeData, 'input', false)
        } else {
          this.$message.error(res.msg)
        }
      }
    },
    // 撤销
    resetCancel() {
      if (this.currentNode && this.currentNode.id === '') {
        this.data.splice(this.data.findIndex(item => item.id === ''), 1)
        this.currentNode = null
      } else {
        this.selectedNodeData && this.selectedNodeData.input && this.$set(this.selectedNodeData, 'input', false)
      }
    },
    formatList() {
      const newList = this.districtList.map(item => ({
        type: 'district',
        label: item.districtName,
        id: item.districtId
      }))
      // 医院名字
      this.unitList.forEach((item) => {
        const parent = newList.find(x => x.id === item.districtId)
        let children
        if (parent) {
          children = parent.children
        }
        if (children) {
          !children.find(x => x.districtId === item.districtId) && children.push({
            type: 'unit',
            id: item.id,
            label: item.unitName
          })
        } else if (parent) {
          parent.children = [{
            type: 'unit',
            id: item.id,
            label: item.unitName
          }]
        }
      })
      this.data = newList
    },
    // 全部审方中心
    async getAllAuditCenter(args) {
      const res = await AllAuditCenter(args)
      if (res.code === 0) {
        this.auditCenterlist = res.result.map(item => ({ ...item, _auditCenterName: item.auditCenterName, _ip: item.ip }))
        this.chooseUnit.length = this.auditCenterlist.length || 0
      } else {
        this.$message.error(res.msg)
      }
    },
    // 获取审方平台区域
    async getAllDistrict() {
      const res = await AllDistrict()
      if (res.code === 0) {
        this.districtList = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 医院
    async getAllUnit() {
      const res = await AllUnit()
      if (res.code === 0) {
        this.unitList = res.result
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>

<style lang="less" scoped>
.trunk-active {
  padding: 5px 0;
  background-color: #2f71a9;
  color: #fff;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
</style>

<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

.custom-tree-node .el-input--mini .el-input__inner {
  height: 24px;
  line-height: 24px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>
