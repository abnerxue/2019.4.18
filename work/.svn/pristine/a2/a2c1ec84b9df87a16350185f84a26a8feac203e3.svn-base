<template>
  <div style="height: 100%">
    <!-- 检查结果与药品限用 -->
    <el-row
      :gutter="20"
      style="min-height: 100%"
    >
      <el-col
        :span="9"
        style="min-height: 100%"
      >
        <DrugTree
          ref="drugTree"
          :type="'rule'"
          @updateChoose="updateChoose"
        />
      </el-col>
      <el-col
        :span="15"
        style="min-height: 100%"
      >
        <el-card
          class="box-card"
          style="height: 100%"
        >
          <div
            slot="header"
            class="clearfix"
          >
            <span class="f4">限用设置 </span>
            <template v-if="choose.label">
              <span class="f1 font-bold">【 {{choose.label}} 】</span>
              <el-button
                style="position: absolute; top: 15px; right: 20px;"
                type="primary"
                size="small"
                @click="handleSave"
                :loading="loading"
              >保存</el-button>
            </template>
          </div>
          <div
            class="f4"
            v-if="!choose.label"
          >请先选择药品</div>
          <el-tabs
            v-if="choose.label"
            :before-leave="beforeLeave"
          >
            <el-tab-pane label="科室限用">
              <RuleTab
                :type="'科室限用'"
                :list="deptList"
                :allHosDept="allHosDept"
              />
            </el-tab-pane>
            <el-tab-pane label="人群限用">
              <RuleTab
                :type="'人群限用'"
                :list="crowdList"
                :allCrowd="allCrowd"
              />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { Selectcrowdbymedcineid, Selectdeptbymedcineid, SaveCrowd, SaveDept } from '@/api/dataMana'
import { Allhosdept, Allcrowd } from '@/api/dictionaries'
import DrugTree from './components/DrugTree'
import RuleTab from './components/RuleTab'

export default {
  name: 'rule',
  components: { DrugTree, RuleTab },
  data() {
    return {
      loading: false,
      choose: {
        id: '',
        label: ''
      },
      currentKey: null,
      tabIndex: 0,
      allHosDept: [], // 医院科室
      allCrowd: [], // 所有人群
      oldDeptList: [], // 科室限用列表
      deptList: [], // 科室限用列表
      oldCrowdList: [], // 人群限用列表
      crowdList: [] // 人群限用列表
    }
  },
  beforeRouteLeave(to, from, next) {
    let res = true
    if (this.tabIndex === 0 && this.deptList.length > 0) {
      res = this.oldDeptList === JSON.stringify(this.deptList)
    } else if (this.tabIndex === 1 && this.crowdList.length > 0) {
      res = this.oldCrowdList === JSON.stringify(this.crowdList)
    }
    if (res) {
      next()
    } else {
      this.$confirm('当前页面未保存，是否确认离开？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => { next() })
        .catch(() => { next(false) })
    }
  },
  methods: {
    handleSave() {
      this.loading = true
      if (this.tabIndex === 0) {
        // 科室限用
        this.updateDept(JSON.stringify(this.deptList))
      } else if (this.tabIndex === 1) {
        // 人群限用
        this.updateCrowd(JSON.stringify(this.crowdList))
      }
    },
    beforeLeave(tab) {
      let res = true
      if (this.tabIndex === 0 && this.deptList.length > 0) {
        res = this.oldDeptList === JSON.stringify(this.deptList)
      } else if (this.tabIndex === 1 && this.crowdList.length > 0) {
        res = this.oldCrowdList === JSON.stringify(this.crowdList)
      }
      if (res) {
        this.tabIndex = Number(tab)
        this.handleData(this.tabIndex)
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.tabIndex = Number(tab)
          this.handleData(this.tabIndex)
        }).catch(() => {
          console.log('cancle')
          throw new Error('cancle')
        })
      }
    },
    updateChoose(val) {
      let res = true
      if (this.tabIndex === 0 && this.deptList.length > 0) {
        res = this.oldDeptList === JSON.stringify(this.deptList)
      } else if (this.tabIndex === 1 && this.crowdList.length > 0) {
        res = this.oldCrowdList === JSON.stringify(this.crowdList)
      }
      if (res) {
        this.choose = val
        this.handleData(this.tabIndex)
        this.currentKey = val
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.choose = val
          this.currentKey = val
          this.handleData(this.tabIndex)
        }).catch(() => {
          console.log('cancle')
          this.$nextTick(() => {
            this.$refs.drugTree.$refs.tree.setCurrentKey(this.currentKey)
          })
        })
      }
    },
    // 判断 调用 限用
    async handleData(tabIndex) {
      if (tabIndex === 0) {
        // 科室限用
        await this.getAllHosDept()
        await this.getSelectdeptbymedcineid()
      } else if (tabIndex === 1) {
        await this.getAllCrowd()
        await this.getSelectcrowdbymedcineid()
      }
    },
    // get科室限用
    async getSelectdeptbymedcineid() {
      const res = await Selectdeptbymedcineid({
        medcineId: this.choose.id
      })
      if (res.code === 0) {
        this.deptList = res.result
        if (this.deptList && this.deptList.length > 0) {
          this.deptList.forEach((item) => {
            Object.assign(item, { action: Number(item.action) })
          })
        }
        this.oldDeptList = JSON.stringify(this.deptList)
      } else {
        this.$message.error(res.msg)
        this.deptList = []
      }
    },
    // get人群限用
    async getSelectcrowdbymedcineid() {
      const res = await Selectcrowdbymedcineid({
        medcineId: this.choose.id
      })
      if (res.code === 0) {
        this.crowdList = res.result
        this.oldCrowdList = JSON.stringify(this.crowdList)
      } else {
        this.$message.error(res.msg)
        this.crowdList = []
      }
    },
    // 更新 科室限用
    async updateDept(args) {
      const res = await SaveDept(args)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.getSelectdeptbymedcineid()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 更新 人群限用
    async updateCrowd(args) {
      const res = await SaveCrowd(args)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.getSelectcrowdbymedcineid()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 医院科室
    async getAllHosDept() {
      const res = await Allhosdept()
      if (res.code === 0) {
        this.allHosDept = res.result
      }
    },
    // 所有人群
    async getAllCrowd() {
      const res = await Allcrowd()
      if (res.code === 0) {
        this.allCrowd = res.result
      }
    }
  }
}
</script>
