<template>
  <div style="position: relative;">
    <el-button
      type="primary"
      @click="showModal"
      style="position: absolute; top: -10px; right: 20px; z-index: 999;"
    >添加规则</el-button>
    <el-tabs @tab-click="clickTab">
      <el-tab-pane label="肝功能异常">
        <CheckTab :tabIndex="tabIndex" :list="list" @getChecklimitList="getChecklimitList"/>
      </el-tab-pane>
      <el-tab-pane label="肾功能异常">
        <CheckTab :tabIndex="tabIndex" :list="list" @getChecklimitList="getChecklimitList"/>
      </el-tab-pane>
    </el-tabs>
    <CheckModal ref="modal" :title="'添加规则'" :type="'add'" @getChecklimitList="getChecklimitList"/>
  </div>
</template>
<script>
import { GetChecklimitlist } from '@/api/setting'
import CheckTab from './components/CheckTab'
import CheckModal from './components/CheckModal'

export default {
  name: '',
  components: { CheckTab, CheckModal },
  data() {
    return {
      type: 1,
      tabIndex: 0,
      list: []
    }
  },
  created() {
    this.getChecklimitList()
  },
  methods: {
    showModal() {
      this.$refs.modal.isShowModal = true
      this.$refs.modal.getCheckItemsList()
      if (this.tabIndex === 0) {
        this.$refs.modal.getLiverList()
      } else if (this.tabIndex === 1) {
        this.$refs.modal.getDneydicList()
      }
    },

    clickTab(tab) {
      this.tabIndex = Number(tab.index)
      this.type = Number(tab.index) + 1
      this.getChecklimitList()
    },
    // 获取 列表
    async getChecklimitList() {
      const res = await GetChecklimitlist({ type: this.type })
      if (res.code === 0) {
        this.list = []
        const resp = res.result
        if (resp.length > 0) {
          resp.forEach((data) => {
            data.checkLimit.forEach((item, index) => {
              this.list.push({
                rowSpan: index === 0 ? data.checkLimit.length : 0,
                description: data.description,
                liverKidneyDicId: data.liverKidneyDicId,
                ruleName: '检验项目',
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
