<template>
  <div>
    <el-form
      label-width="96px"
      :inline="true"
      :model="form"
      size="small"
    >
      <el-form-item label="开方医师：">
        <el-select
          v-model="form.doctorId"
          placeholder="选择开方医师"
        >
          <el-option
            v-for="(item, index) in doctorList"
            :key="index"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="审核药师：">
        <el-select
          v-model="form.auditPharmacistId"
          placeholder="选择审核药师"
        >
          <el-option
            v-for="(item, index) in pharmacistList"
            :key="index"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="中西药处方：">
        <el-select
          v-model="form.typeId"
          placeholder="选择中西药处方"
        >
          <el-option
            v-for="(item, index) in alltype"
            :key="index"
            :label="item.typeName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="问题代码：">
        <el-select
          v-model="form.problemId"
          placeholder="选择问题代码"
        >
          <el-option
            v-for="(item, index) in auditrulesfold"
            :key="index"
            :label="item.code"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="初审状态：">
        <el-select
          v-model="form.stateId"
          placeholder="选择初审状态"
        >
          <el-option
            v-for="(item, index) in auditState"
            :key="index"
            :label="item.stateName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="区域审方结果：">
        <el-select
          v-model="form.reviewStateId"
          placeholder="选择区域审方结果"
        >
          <el-option
            v-for="(item, index) in auditState"
            :key="index"
            :label="item.stateName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="科 室：">
        <el-select
          v-model="form.deptId"
          placeholder="选择科室"
        >
          <el-option
            v-for="(item, index) in hosDept"
            :key="index"
            :label="item.name"
            :value="item.hospitalId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="患者姓名：">
        <el-input
          v-model="form.patientName"
          style="width: 199px"
          placeholder="输入患者姓名"
        ></el-input>
      </el-form-item>
      <!-- <el-form-item label="药品名称：">
        <AutoSearch
          :placeHolder="'输入药品名称'"
          style="width: 199px"
          :list="medcineList"
          @handleSelect="handleSearch"
        />
      </el-form-item> -->
      <el-form-item label="诊 断：">
        <el-autocomplete
          v-model="selectDiagnosis"
          size="small"
          placeholder="输入诊断"
          style="width: 199px"
          clearable
          :fetch-suggestions="querySearch"
          @change="handleSearchPage"
          @select="handleSearchDiagnosis"
        >
          <i
            class="el-icon-search el-input__icon"
            slot="suffix"
          ></i>
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="日 期：">
        <el-date-picker
          v-model="beginTime"
          type="datetime"
          placeholder="开始日期时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        >
        </el-date-picker>
        ~
        <el-date-picker
          v-model="endTime"
          type="datetime"
          placeholder="开始日期时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="23:59:59"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="handleSearch(newId)"
        >查询</el-button>
        <el-button
          type="primary"
          @click="handleExportExcel"
        >导出Excel</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="list"
      border
      size="small"
      :row-class-name="tableRowClassName"
      :header-cell-class-name="tableHeaderName"
      @cell-click="clickView"
      style="width: 100%; margin-top: 10px;"
    >
      <el-table-column
        fixed
        type="index"
        width="50"
        label="序号"
        align="center"
      >
      </el-table-column>
      <el-table-column
        v-for="(item,index) in positionKey"
        :key="index"
        :prop="item.prop"
        :label="item.label"
        :width="item.width"
        align="center"
      >
        <template v-if="item.prop === 'prescriptionContent'">
          <el-button type="text">查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <Pagination
      class="text-center"
      :page="page"
      @handlePageChange="getList(newId)"
    />
    <PrescriptionModal
      :isVisible='isVisible'
      :data="detail"
    />
  </div>
</template>

<script>
import { Allhosdept, Allhosstaff } from '@/api/dictionaries'
import { GetPrehis } from '@/api/global'
import { AllPrehistory, AllAuditrulesfold } from '@/api/home'
import { SelectAllDiagnosis, Allmedcinefold } from '@/api/dataMana'
import { exportExcelHis } from '@/api/exportApi'
import { getParameterStr } from '@/utils/utils'
import Pagination from '@/components/Pagination'
import PrescriptionModal from '@/components/PrescriptionModal'

export default {
  components: { Pagination, PrescriptionModal },
  props: ['alltype', 'auditState', 'auditCenterId'],
  data() {
    return {
      isVisible: false,
      detail: {},
      newId: '',
      selectDiagnosis: '', // 选择诊断
      positionKey: [
        {
          prop: 'stateName',
          width: '',
          label: '初审结果'
        },
        {
          prop: 'reviewName',
          width: '',
          label: '区域审方结果'
        },
        {
          prop: 'doctorName',
          width: '',
          label: '开方医师'
        },
        {
          prop: 'prescriptionNumber',
          width: '',
          label: '处方号'
        },
        {
          prop: 'prescriptionContent',
          width: '',
          label: '处方内容'
        },
        {
          prop: 'typeName',
          width: '',
          label: '处方类型'
        },
        {
          prop: 'prescriptionReason',
          width: '',
          label: '用药理由'
        },
        {
          prop: 'problemCode',
          width: '',
          label: '问题代码'
        },
        {
          prop: 'problemName',
          width: '',
          label: '问题标题'
        },
        {
          prop: 'patientName',
          width: '',
          label: '患者姓名'
        },
        {
          prop: 'deptName',
          width: '',
          label: '科室'
        },
        {
          prop: 'visitNumber',
          width: '',
          label: '就诊号'
        },
        {
          prop: 'diagnosisName',
          width: '',
          label: '诊断'
        },
        {
          prop: 'submmitTime',
          width: '95',
          label: '提交时间'
        },
        {
          prop: 'auditPharmacistName',
          width: '',
          label: '审核药师'
        },
        {
          prop: 'auditTime',
          width: '95',
          label: '审核时间'
        },
        {
          prop: 'comfirmPharmacistName',
          width: '',
          label: '确认药师'
        },
        {
          prop: 'comfirmTime',
          width: '95',
          label: '确认时间'
        }
      ],
      hosDept: [], // 医院科室
      doctorList: [], // 医生列表
      pharmacistList: [], // 药师列表
      auditrulesfold: [], // 选择代码问题
      medcineList: [], // 药品信息
      diagnosisList: [], // 诊断信息
      beginTime: '',
      endTime: '',
      form: {
        doctorId: '', // 医生ID
        auditPharmacistId: '', // 药师ID
        patientName: '', // 病人姓名
        typeId: '', // 处方类型ID
        problemId: '', // 问题ID
        diagnosisId: '', //  诊断ID
        stateId: '', // 初审状态ID
        reviewStateId: '', // 区域审方结果ID
        deptId: '', // 科室ID
        beginDateScope: '' // 开始时间,结束时间
      },
      list: [],
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      }
    }
  },
  created() {
    this.getAllHosDept()
    this.getAllHosstaff()
    this.getAllAuditrulesfold()
    this.getAllMedcine()
    this.getAllDiagnosisList()
    this.getList(this.newId)
  },
  methods: {
    // todo
    handleSearchPage() {
      console.log('selectDiagnosis: ', this.selectDiagnosis)
    },
    // todo
    handleSearchDiagnosis(val) {
      console.log('?val: ', val)
      // this.getAllDiagnosisList(val)
      this.form.diagnosisId = val.id
      // this.page.pageNo = 1
      // this.form.beginDateScope = (this.beginTime && this.endTime) ? `${this.beginTime},${this.endTime}` : ''
      // this.getList()
    },

    // todo 获取 诊断信息
    async getAllDiagnosisList(val) {
      console.log('todo val: ', val)
      let key
      if (val) {
        key = val.diagnosisName
      }
      const args = {
        filter: key,
        pageSize: 30, // 每页显示30条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      }
      const res = await SelectAllDiagnosis(args)
      if (res.code === 0) {
        this.diagnosisList = res.result
        this.diagnosisList.forEach((item) => { Object.assign(item, { value: item.diagnosisName }) })
      }
    },
    // 查询列表
    handleSearch(id) {
      this.page.pageNo = 1
      this.form.beginDateScope = (this.beginTime && this.endTime) ? `${this.beginTime},${this.endTime}` : ''
      this.getList(id)
    },
    async getList(id) {
      this.newId = id || this.auditCenterId
      const base = {
        auditCenterId: this.newId, // 审方中心ID
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      }
      const args = Object.assign(base, this.form)
      const res = await AllPrehistory(args)
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
      }
    },
    // 导出Excel
    handleExportExcel() {
      this.form.beginDateScope = (this.beginTime && this.endTime) ? `${this.beginTime},${this.endTime}` : ''
      const base = {
        auditCenterId: this.auditCenterId
      }
      const args = Object.assign(base, this.form)
      location.href = `${exportExcelHis}?${getParameterStr(args)}`
    },
    // 查看处方内容
    async clickView(row, column) {
      if (column.label === '处方内容') {
        const res = await GetPrehis({ id: row.id })
        if (res.code === 0) {
          this.isVisible = true
          this.detail = res.result[0]
        } else {
          this.$message.error(res.msg)
        }
      }
    },
    // 获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.medcineList = res.result
        this.medcineList.forEach((item) => { Object.assign(item, { value: item.name }) })
      }
    },
    // 选择代码问题
    async getAllAuditrulesfold() {
      const res = await AllAuditrulesfold()
      if (res.code === 0) {
        this.auditrulesfold = [
          {
            id: '',
            name: '',
            code: '全部'
          },
          ...res.result
        ]
      }
    },
    // 字典 - 医院科室
    async getAllHosDept() {
      const res = await Allhosdept()
      if (res.code === 0) {
        this.hosDept = [
          {
            hospitalId: '',
            name: '全部'
          },
          ...res.result
        ]
      }
    },
    // 字典 - 医院医生和药师
    async getAllHosstaff() {
      const res = await Allhosstaff()
      if (res.code === 0) {
        this.doctorList = res.result.filter(item => item.type === 1) // 医生
        this.pharmacistList = res.result.filter(item => item.type === 2) // 药师
        this.doctorList = [
          {
            id: '',
            name: '全部'
          },
          ...this.doctorList
        ]
        this.pharmacistList = [
          {
            id: '',
            name: '全部'
          },
          ...this.pharmacistList
        ]
      }
    },
    querySearch(queryString, cb) {
      const restaurants = this.diagnosisList
      const results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      cb(results)
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    // 修改tablerow的背景色
    tableRowClassName({ row, rowIndex }) {
      if ((rowIndex % 2) === 0) {
        return 'row1'
      }
      return 'row2'
    },
    // 修改table header的背景色
    tableHeaderName({ row, rowIndex }) {
      return 'table-header'
    }
  }
}
</script>

