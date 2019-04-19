<template>
  <div style="height: 100%">
    <!-- 检查结果与药品限用 -->
    <el-row :gutter="20" style="height: 100%">
      <el-col :span="9" style="height: 100%">
        <el-card class="box-card" style="height: 100%; overflow-y: auto;">
          <div slot="header" class="clearfix" style="height: 100%;">
            <span class="f4">药品列表</span>
          </div>
          <DrugTree ref="drugTree" :type="'drugs'" @updateChoose="updateChoose"/>
        </el-card>
      </el-col>
      <el-col :span="15" style="height: 100%">
        <el-card class="box-card" style="height: 100%;">
          <div slot="header" class="clearfix">
            <span class="f4">限用设置</span>
            <template v-if="choose.name">
              <span class="f1 font-bold">【 {{choose.name}} 】</span>
              <el-button
                style="position: absolute; top: 15px; right: 20px;"
                type="primary"
                size="small"
                @click="showModal"
              >添加规则</el-button>
            </template>
          </div>
          <div class="f4" v-if="!choose.name">请先选择药品</div>
          <el-tabs v-if="choose.name" @tab-click="clickTab">
            <el-tab-pane label="肝功能异常">
              <CheckTab
                :choose="choose"
                :type="type"
                :list="list"
                @getAllMedicinelimit="getAllMedicinelimit"
              />
            </el-tab-pane>
            <el-tab-pane label="肾功能异常">
              <CheckTab
                :choose="choose"
                :type="type"
                :list="list"
                @getAllMedicinelimit="getAllMedicinelimit"
              />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
    <CheckModal ref="modal" :title="'添加规则'" @click="handleAdd"/>
  </div>
</template>

<script>
import { AllMedicinelimit, AddMedicinelimit } from '@/api/dataMana'
import DrugTree from './components/DrugTree'
import CheckTab from './components/CheckTab'
import CheckModal from './components/CheckModal'

export default {
  name: 'rule',
  components: { DrugTree, CheckTab, CheckModal },
  data() {
    return {
      choose: {},
      type: 1,
      // currentKey: null,
      tabIndex: 0,
      list: []
    }
  },
  methods: {
    showModal() {
      this.$refs.modal.isShowModal = true
    },

    // add
    async handleAdd(val) {
      this.$refs.modal.loading = true
      this.$refs.modal.getList()
      const args = {
        medicineId: this.choose.id,
        type: this.type,
        ...val
      }
      const res = await AddMedicinelimit(args)
      this.$refs.modal.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.$refs.modal.hideModal()
        this.getAllMedicinelimit()
      } else {
        this.$message.error(res.msg)
      }
    },
    clickTab(tab) {
      this.tabIndex = Number(tab.index)
      this.type = Number(tab.index) + 1
      this.getAllMedicinelimit()
    },
    updateChoose(val) {
      this.choose = val
      this.getAllMedicinelimit()
      // this.currentKey = val
    },
    // 获取限用列表
    async getAllMedicinelimit() {
      const res = await AllMedicinelimit({
        medicineId: this.choose.id,
        type: this.type
      })
      if (res.code === 0) {
        this.list = []
        const resp = res.result
        if (resp.length > 0) {
          resp.forEach((data) => {
            data.checkRules.forEach((item, index) => {
              this.list.push({
                rowSpan: index === 0 ? data.checkRules.length : 0,
                level: data.level,
                medicineId: data.medicineId,
                ruleId: data.ruleId,
                ruleName: data.ruleName,
                type: data.type,
                ...item
              })
            })
          })
        }
      } else {
        this.$message.error(res.msg)
        this.list = []
      }
    }
  }
}
</script>
