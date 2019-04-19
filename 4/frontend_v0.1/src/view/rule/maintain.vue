<template>
  <div style="height: 100%">
    <!-- 适用药及疗程维护 -->
    <el-row :gutter="20" style="height: 100%">
      <el-col :span="10" style="height: 100%">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span class="f4">诊断列表</span>
          </div>
          <div>
            <el-input
              v-model="searchKey"
              size="small"
              placeholder="查找诊断"
              clearable
              @change="handleSearch"
            ></el-input>
            <div class="space"></div>
            <el-table
              ref="table"
              :data="list"
              @row-click="handleClickRow"
              highlight-current-row
              border
              size="small"
              style="width: 100%;"
              :height="tableHeight"
              :header-cell-class-name="tableHeaderName"
            >
              <el-table-column prop="icd10" label="编码" align="center" width="100"></el-table-column>
              <el-table-column prop="diagnosisName" label="名称" align="center"></el-table-column>
            </el-table>
            <Pagination class="text-center" :page="page" @handlePageChange="getAllList"/>
          </div>
        </el-card>
      </el-col>
      <el-col :span="14" style="height: 100%">
        <el-card class="box-card" style="height: 100%">
          <div slot="header" class="clearfix">
            <span class="f4">适用药</span>
            <span class="f1 font-bold" v-if="choose.label">【 {{choose.label}} 】</span>
          </div>
          <div class="f4" v-if="!choose.label">请先选择药品</div>
          <el-tabs v-if="choose.label" :before-leave="beforeLeave">
            <el-tab-pane label="适用药品">
              <AutoSearch :placeHolder="'添加药品'" :list="searchList" @handleSelect="handleAdd"/>
              <div class="space"></div>
              <el-table
                :data="applicableList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
              >
                <el-table-column prop="medcineName" label="药品通用名" align="center"></el-table-column>
                <el-table-column prop="enable" label="操作" align="center" width="80">
                  <template slot-scope="scope">
                    <i @click="handleDelete(scope.row.id)" class="el-icon-delete text-red pointer"></i>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="疗程设置">
              <p v-if="!treatment.id" class="f6">无诊断疗程信息~</p>
              <el-form v-else :model="treatment" size="small" label-width="85px">
                <el-form-item label="疗程时间：">
                  <el-radio-group v-model="treatment.type">
                    <el-radio v-for="(item, index) in treatmentType" :key="index" :label="item.id">
                      {{item.desp}}
                      <template v-if="item.id === 2 && treatment.type === 2">
                        <el-input
                          v-model="treatment.duration"
                          size="small"
                          placeholder
                          type="number"
                          style="width: 80px;"
                        ></el-input>天
                      </template>
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="handleSave" :loading="loading">保存</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  SelectAllDiagnosis,
  SelectTreatment,
  UpdatTreatment,
  GetApplicable,
  DeleteApplicable,
  AddApplicable,
  Allmedcinefold
} from '@/api/dataMana'
import AutoSearch from '@/components/AutoSearch'
import Pagination from '@/components/Pagination'

export default {
  name: '',
  components: { AutoSearch, Pagination },
  data() {
    return {
      tableHeight: 50,
      loading: false,
      searchKey: '', // 查询诊断 关键字
      list: [], // 诊断信息
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      },
      choose: {
        id: '',
        label: ''
      },
      currentRow: null,
      tabIndex: 0,
      searchList: [],
      applicableList: [], // 适用药品列表
      treatment: {}, // 疗程
      oldTreatment: {}, // 疗程
      treatmentType: [
        { id: 0, desp: '7天' },
        { id: 1, desp: '30天' },
        { id: 2, desp: '自定义' }
      ]
    }
  },
  created() {
    this.getAllMedcine()
    this.getAllList()
  },
  mounted() {
    setTimeout(() => {
      this.tableHeight = window.innerHeight - 350
    }, 100)
  },
  methods: {
    // 诊断列表查询诊断
    handleSearch() {
      this.getAllList(this.searchKey)
      this.choose = {
        id: '',
        label: ''
      }
    },

    // 点击 表格
    handleClickRow(row) {
      let res = true
      if (this.tabIndex === 1 && this.treatment.id > 0) {
        res = this.oldTreatment === JSON.stringify(this.treatment)
      }
      if (res) {
        this.choose = {
          id: row.id,
          label: row.diagnosisName
        }
        this.treatment = {}
        this.handleData(this.tabIndex)
        this.currentRow = row
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.choose = {
            id: row.id,
            label: row.diagnosisName
          }
          this.treatment = {}
          this.handleData(this.tabIndex)
          this.currentRow = row
        }).catch(() => {
          this.$refs.table.setCurrentRow(this.currentRow)
        })
      }
    },
    beforeLeave(tab) {
      let res = true
      if (this.tabIndex === 1 && this.treatment.id > 0) {
        res = this.oldTreatment === JSON.stringify(this.treatment)
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
    // 判断 tab 调用 不同的接口
    handleData(tabIndex) {
      if (tabIndex === 0) {
        // 根据诊断表的id获取适用药品列表
        this.getApplicableList()
      } else if (tabIndex === 1) {
        // 疗程
        this.getTreatment()
      }
    },
    // 点击删除
    handleDelete(id) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteApplicable(id)
      }).catch(() => { })
    },
    // 删除 药品适用
    async deleteApplicable(id) {
      const res = await DeleteApplicable({ id })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.getApplicableList()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 增加 药品适用
    async handleAdd(val) {
      const obj = {
        diagnosisId: this.choose.id,
        medcineId: val.id
      }
      const res = await AddApplicable(obj)
      if (res.code === 0) {
        this.getApplicableList()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 保存 疗程
    async handleSave() {
      this.loading = true
      const res = await UpdatTreatment(this.treatment)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.getTreatment()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 根据诊断表的id获取适用药品列表
    async getApplicableList() {
      const res = await GetApplicable({ diagnosisId: this.choose.id })
      if (res.code === 0) {
        this.applicableList = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 获取疗程
    async getTreatment() {
      const res = await SelectTreatment({ diagnosisId: this.choose.id })
      if (res.code === 0) {
        this.treatment = res.result[0] || this.treatment
        this.oldTreatment = JSON.stringify(this.treatment)
      }
    },
    // 获取诊断信息
    async getAllList(key) {
      const args = {
        filter: key,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      }
      const res = await SelectAllDiagnosis(args)
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
      } else {
        this.$message.error(res.msg)
      }
    },

    // 获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.allMedcine = res.result
        this.searchList = JSON.parse(JSON.stringify(this.allMedcine))
        this.searchList.forEach((item) => { Object.assign(item, { value: item.name }) })
      }
    },

    // 修改table header的背景色
    tableHeaderName({ row, rowIndex }) {
      return 'table-header'
    }
  }
}
</script>
