<template>
  <div>
    <el-form
      :inline="true"
      :model="form"
      label-width="120px"
      size="small"
    >
      <el-form-item label="审方中心：">
        <el-select
          v-model="form.auditCenterId"
          placeholder="审方中心"
        >
          <el-option
            v-for="(item, index) in center"
            :key="index"
            :label="item.auditCenterName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="区域审方等级：">
        <el-select
          v-model="form.reviewStateId"
          placeholder="区域审方等级"
        >
          <el-option
            v-for="(item, index) in auditState"
            :key="index"
            :label="item.stateName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="处方号/就诊号：">
        <el-input v-model="form.prescriptionNumber"></el-input>
      </el-form-item>
    </el-form>
    <el-form
      :inline="true"
      :model="form"
      label-width="120px"
      size="small"
    >
      <el-form-item label="时间选择：">
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
          @click="handleExportTable"
        >导出Excel</el-button>
        <el-button
          type="primary"
          @click="handleSearch"
        >查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="list"
      border
      size="small"
      :row-class-name="tableRowClassName"
      :header-cell-class-name="tableHeaderName"
      style="width: 100%; margin: 12px 0;"
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
      ></el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="80"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="viewDetail(scope.row.id)"
          >查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <Pagination
      class="text-center"
      :page="page"
      @handlePageChange="getList"
    />
    <PrescriptionModal
      :isVisible='isVisible'
      :data="detail"
    />
  </div>
</template>

<script>
import { Allproblemhistory, Allhistory } from '@/api/search'
import { Allauditstate } from '@/api/dictionaries'
import { AllAuditCenter } from '@/api/global'
import { exportExcelAll, exportExcelPromble } from '@/api/exportApi'
import { getParameterStr } from '@/utils/utils'
import Pagination from '@/components/Pagination'
import PrescriptionModal from '@/components/PrescriptionModal'

export default {
  props: ['type'],
  components: { Pagination, PrescriptionModal },
  data() {
    return {
      isVisible: false,
      detail: {},
      list: [],
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      },
      auditState: [], // 区域审方等级
      center: [], // 全部审方中心
      beginTime: '',
      endTime: '',
      form: {
        auditCenterId: '', // 审方中心ID
        reviewStateId: '', // 区域审方结果ID
        prescriptionNumber: '', // 处方号名
        beginDateScope: [] // 时间
      },
      positionKey: [
        {
          prop: 'unitName',
          width: '',
          label: '就医单位名称'
        },
        {
          prop: 'auditCenterName',
          width: '',
          label: '审方中心名称'
        },
        {
          prop: 'prescriptionNumber',
          width: '',
          label: '处方号'
        },
        {
          prop: 'visitNumber',
          width: '',
          label: '就诊号'
        },
        {
          prop: 'patientName',
          width: '',
          label: '患者姓名'
        },
        {
          prop: 'patientSex',
          width: '',
          label: '性别'
        },
        {
          prop: 'deptName',
          width: '',
          label: '就诊科室'
        },
        {
          prop: 'doctorName',
          width: '',
          label: '医师姓名'
        },
        {
          prop: 'auditPharmacistName',
          width: '',
          label: '审核药师姓名'
        },
        {
          prop: 'submmitTime',
          width: '95',
          label: '处方开具时间'
        },
        {
          prop: 'auditTime',
          width: '95',
          label: '处方审核时间'
        },
        {
          prop: 'reviewName',
          width: '',
          label: '审核等级'
        }
      ]
    }
  },
  async created() {
    await this.getAllAuditState() // 字典 - 区域审方等级
    await this.getAllCenter() // 字典 - 全部审方中心
    await this.getList()
  },
  methods: {
    viewDetail(id) {
      this.$router.push({
        path: this.$urls.PRESCRIPTION_DETAIL,
        query: {
          id,
          type: this.type
        }
      })
    },
    // 导出Excel
    handleExportTable() {
      this.form.beginDateScope = (this.beginTime && this.endTime) ? `${this.beginTime},${this.endTime}` : ''
      let exportApi
      if (this.type === 'all') {
        exportApi = `${exportExcelAll}?${getParameterStr(this.form)}`
      } else if (this.type === 'problem') {
        exportApi = `${exportExcelPromble}?${getParameterStr(this.form)}`
      }
      location.href = exportApi
    },
    handleSearch() {
      this.page = {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      }
      this.form.beginDateScope = (this.beginTime && this.endTime) ? `${this.beginTime},${this.endTime}` : ''
      this.getList()
    },
    async getList() {
      const args = Object.assign(this.page, this.form)
      let res
      if (this.type === 'all') {
        res = await Allhistory(args)
      } else if (this.type === 'problem') {
        res = await Allproblemhistory(args)
      }
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
        if (this.list && this.list.length > 0) {
          this.list.forEach((item) => {
            Object.assign(item, { patientSex: item.patientSex === 0 ? '男' : '女' })
          })
        }
      } else {
        this.$message.error(res.msg)
      }
    },
    // 字典 - 全部审方中心
    async getAllCenter() {
      const res = await AllAuditCenter()
      if (res.code === 0) {
        this.center = [
          {
            id: '',
            auditCenterName: '全部'
          },
          ...res.result
        ]
      } else {
        this.$message.error(res.msg)
      }
    },
    // 字典 - 区域审方等级
    async getAllAuditState() {
      const res = await Allauditstate()
      if (res.code === 0) {
        const arr = res.result
        arr.splice(arr.findIndex(item => item.id === 8), 1)
        arr.splice(arr.findIndex(item => item.id === 9), 1)
        this.auditState = [
          {
            id: '',
            stateName: '全部'
          },
          ...arr
        ]
      } else {
        this.$message.error(res.msg)
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
