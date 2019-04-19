 <template>
  <div style="height: 100%">
    <!-- 诊断与药品限用 -->
    <el-row :gutter="20" style="height: 100%">
      <el-col :span="12" style="height: 100%">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>诊断列表</span>
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
      <el-col :span="12" style="height: 100%">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>药物限用</span>
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
          <div class="f4" v-if="!choose.label">请先选择药品</div>
          <el-tabs v-if="choose.label" :before-leave="beforeLeave">
            <el-tab-pane label="限用药品及等级设置">
              <AutoSearch :placeHolder="'添加药品'" :list="searchList" @handleSelect="handleAdd"/>
              <div class="space"></div>
              <el-table
                :data="tableData"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
              >
                <el-table-column prop="medcineId" label="药品通用名" align="center">
                  <template slot-scope="scope">{{handleEnumMedcine(scope.row.medcineId)}}</template>
                </el-table-column>
                <el-table-column prop="action" label="审核等级设置" align="center" width="270">
                  <template slot-scope="scope">
                    <el-radio-group v-model="scope.row.action">
                      <el-radio
                        v-for="(item, index) in auditlevel"
                        :key="index"
                        :label="item.id"
                      >{{item.desp}}</el-radio>
                    </el-radio-group>
                  </template>
                </el-table-column>
                <el-table-column prop="enable" label="操作" align="center" width="50">
                  <template slot-scope="scope">
                    <i @click="handleDelete(scope.row)" class="el-icon-delete text-red pointer"></i>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <!-- <el-tab-pane label="疗程设置">
              <p
                v-if="!treatment.id"
                class="f6"
              >无诊断疗程信息~</p>
              <el-form
                v-else
                :inline="true"
                :model="treatment"
                size="small"
              >
                <el-form-item label="疗程时间：">
                  <el-radio-group v-model="treatment.type">
                    <el-radio
                      v-for="(item, index) in treatmentType"
                      :key="index"
                      :label="item.id"
                    >{{item.desp}}
                      <template v-if="item.id === 2 && treatment.type === 2">
                        <el-input
                          v-model="treatment.duration"
                          size="small"
                          placeholder=""
                          type="number"
                          style="width: 80px;"
                        >
                        </el-input>
                        天
                      </template>
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-form>
            </el-tab-pane>-->
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { SelectAllDiagnosis, SelectLimit, DeleteLimit, Allmedcinefold, SaveLimit, SelectTreatment, UpdatTreatment, AddLimit } from '@/api/dataMana'
import { auditlevel } from '@/global/cfg'
import AutoSearch from '@/components/AutoSearch'
import Pagination from '@/components/Pagination'

export default {
  name: 'diagnose',
  components: { AutoSearch, Pagination },
  data() {
    return {
      tableHeight: 50,
      loading: false,
      searchKey: '', // 查询诊断 关键字
      auditlevel,
      treatment: {}, // 疗程
      oldTreatment: {}, // 疗程
      treatmentType: [
        { id: 0, desp: '7天' },
        { id: 1, desp: '30天' },
        { id: 2, desp: '自定义' }
      ],
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
      allMedcine: [],
      tableData: [],
      oldLimitList: [],
      searchList: []
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
  beforeRouteLeave(to, from, next) {
    let res = true
    if (this.tabIndex === 0 && this.tableData.length > 0) {
      res = this.oldLimitList === JSON.stringify(this.tableData)
    } else if (this.tabIndex === 1 && this.treatment.id > 0) {
      res = this.oldTreatment === JSON.stringify(this.treatment)
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
    handleSearch() {
      this.getAllList(this.searchKey)
      this.choose = {
        id: '',
        label: ''
      }
    },
    // 增加 限用药物
    async handleAdd(val) {
      const obj = {
        diagnosisId: this.choose.id,
        medcineId: val.id,
        action: ''
      }
      const res = await AddLimit(obj)
      if (res.code === 0) {
        this.getSelectLimit()
      } else {
        this.$message.error(res.msg)
      }
    },
    beforeLeave(tab) {
      let res = true
      if (this.tabIndex === 0 && this.tableData.length > 0) {
        res = this.oldLimitList === JSON.stringify(this.tableData)
      } else if (this.tabIndex === 1 && this.treatment.id > 0) {
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
    // 点击 表格
    handleClickRow(row) {
      let res = true
      if (this.tabIndex === 0 && this.tableData.length > 0) {
        res = this.oldLimitList === JSON.stringify(this.tableData)
      } else if (this.tabIndex === 1 && this.treatment.id > 0) {
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
    // 判断 tab 调用 不同的接口
    handleData(tabIndex) {
      if (tabIndex === 0) {
        // 限用药品及等级设置
        this.getSelectLimit()
      } else if (tabIndex === 1) {
        // 疗程
        this.getSelectTreatment()
      }
    },
    // 点击删除
    handleDelete(row) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteLimit(row)
      }).catch(() => { })
    },
    // 删除 限用药物
    async deleteLimit(row) {
      const res = await DeleteLimit({ id: row.id })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.getSelectLimit(row.diagnosisId)
      } else {
        this.$message.error(res.msg)
      }
    },
    // 保存 限用药物
    async handleSave() {
      this.loading = true
      if (this.tabIndex === 0) {
        const args = JSON.stringify(this.tableData)
        const res = await SaveLimit(args)
        this.loading = false
        if (res.code === 0) {
          this.$message.success(res.msg)
          this.getSelectLimit()
        } else {
          this.$message.error(res.msg)
        }
      } else if (this.tabIndex === 1) {
        const res = await UpdatTreatment(this.treatment)
        this.loading = false
        if (res.code === 0) {
          this.$message.success(res.msg)
          this.getSelectTreatment()
        } else {
          this.$message.error(res.msg)
        }
      }
    },
    // 处理枚举 - 药品名称
    handleEnumMedcine(id) {
      return this.allMedcine.filter(item => id === item.id)[0].name
    },
    // 获取 限用药品及等级设置
    async getSelectLimit() {
      const res = await SelectLimit({ diagnosisId: this.choose.id })
      if (res.code === 0) {
        this.tableData = res.result
        this.oldLimitList = JSON.stringify(res.result)
      }
    },
    // 获取疗程
    async getSelectTreatment() {
      const res = await SelectTreatment({ diagnosisId: this.choose.id })
      if (res.code === 0) {
        this.treatment = res.result[0] || this.treatment
        this.oldTreatment = JSON.stringify(this.treatment)
      }
    },
    // 获取 诊断信息
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
