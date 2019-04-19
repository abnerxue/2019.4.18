<template>
  <div class="pend">
    <el-form
      :inline="true"
      :model="form"
    >
      <el-form-item label="处方号/就诊号：">
        <el-input
          size="small"
          v-model="form.prescriptionNumber"
          placeholder="处方号/就诊号"
        ></el-input>
      </el-form-item>
      <el-form-item label="审核状态：">
        <el-select
          size="small"
          v-model="form.reviewStateId"
          placeholder="审核状态"
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
      <el-form-item label="中西药处方：">
        <el-select
          size="small"
          v-model="form.typeId"
          placeholder="中西药处方"
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
      <el-form-item>
        <el-button
          size="small"
          type="primary"
          @click="getList(newId)"
        >查 询</el-button>
      </el-form-item>
    </el-form>
    <div style="margin-top: 5px;">
      <el-button
        class="text-white bg-dark-gray"
        :class="{'active': platformAuditState}"
        size="small"
        type="primary"
        style="border: none;"
        @click="updateAuditCenter(1)"
      ><i class="icon icon-start"></i> 开始审方</el-button>
      <el-button
        class="text-white bg-dark-gray"
        :class="{'active': !platformAuditState}"
        size="small"
        type="primary"
        style="border: none;"
        @click="updateAuditCenter(0)"
      ><i class="icon icon-stop"></i> 停止审方</el-button>
      <el-button
        size="small"
        type="primary"
        @click="getList(newId)"
      ><i class="icon icon-refresh"></i>刷 新</el-button>
    </div>
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
import { GetPrehis } from '@/api/global'
import { AllPendinghistory } from '@/api/home'
import { UpdateAuditCenter } from '@/api/center'
import PrescriptionModal from '@/components/PrescriptionModal'

import Pagination from '@/components/Pagination'

export default {
  components: { Pagination, PrescriptionModal },
  props: ['alltype', 'auditState', 'auditCenterId', 'platformAuditState'],
  data() {
    return {
      isVisible: false,
      detail: {},
      newId: '',
      loading: false,
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      },
      form: {
        prescriptionNumber: '', // 处方号
        reviewStateId: '', // 审核状态ID
        typeId: '' // 处方类型ID
      },
      positionKey: [
        {
          prop: 'stateName',
          width: '',
          label: '审核状态'
        },
        {
          prop: 'overTime',
          width: '',
          label: '审核超时'
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
          prop: 'prescriptionNumber',
          width: '',
          label: '处方号'
        },
        {
          prop: 'patientName',
          width: '',
          label: '患者姓名'
        },
        {
          prop: 'diagnosisName',
          width: '',
          label: '诊断'
        },
        {
          prop: 'prescriptionContent',
          width: '',
          label: '处方内容'
        },
        {
          prop: 'prescriptionReason',
          width: '',
          label: '用药理由'
        },
        {
          prop: 'maxDay',
          width: '',
          label: '最大用药天数'
        },
        {
          prop: 'problemId',
          width: '',
          label: '问题代码'
        },
        {
          prop: 'problemName',
          width: '',
          label: '问题标题'
        }
      ],
      list: []
    }
  },
  created() {
    this.getList(this.newId)
  },
  methods: {
    // 更新审方中心
    async updateAuditCenter(state) {
      this.loading = true
      const args = {
        id: this.auditCenterId,
        platformAuditState: state
      }
      const res = await UpdateAuditCenter(args)
      if (res.code === 0) {
        this.loading = false
        this.$message.success(state === 1 ? '启动成功!' : '停止成功！')
      } else {
        this.loading = false
        this.$message.error(res.msg)
      }
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
    async getList(id) {
      this.newId = id || this.auditCenterId
      const args = {
        auditCenterId: this.newId, // 审方中心ID
        prescriptionNumber: this.form.prescriptionNumber, // 处方号
        reviewStateId: this.form.reviewStateId, // 审核状态ID
        typeId: this.form.typeId, // 处方类型ID
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      }
      const res = await AllPendinghistory(args)
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
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

<style lang="less" scoped>
.pend {
  .active {
    background: #66b1ff;
  }
  .icon {
    display: inline-block;
    vertical-align: text-top;
    width: 16px;
    height: 16px;
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-position: center;
  }

  .icon-start {
    background-image: url(../../assets/images/icon-start.png);
  }

  .icon-stop {
    background-image: url(../../assets/images/icon-stop.png);
  }

  .icon-refresh {
    width: 14px;
    height: 14px;
    margin-right: 8px;
    background-image: url(../../assets/images/icon-refresh.png);
  }
}
</style>
