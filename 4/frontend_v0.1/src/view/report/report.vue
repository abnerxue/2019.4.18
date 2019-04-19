<template>
  <div style="height: 100%">
    <!-- 审核报表 -->
    <el-form :inline="true" :model="form" label-width="120px" size="small">
      <el-form-item label="时间选择：">
        <el-date-picker
          v-model="form.beginTime"
          type="datetime"
          placeholder="开始日期时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        ></el-date-picker>~
        <el-date-picker
          v-model="form.endTime"
          type="datetime"
          placeholder="结束日期时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="23:59:59"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20">
      <el-card class="box-card" style="height: 100%; overflow-y: auto;">
        <div>
          <el-tabs @tab-click>
            <el-tab-pane label="处方审核汇总表">
              <el-table
                :data="summarySheetList"
                border
                size="small"
                show-summary
                sum-text="合计人数"
                :row-class-name="tableRowClassName"
                :header-cell-class-name="tableHeaderName"
                style="width: 100%;"
              >
                <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
                <el-table-column
                  v-for="(item,index) in summarySheetKey"
                  :key="index"
                  :prop="item.prop"
                  :label="item.label"
                  :width="item.width"
                  align="center"
                ></el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="处方审核明细表">
              <!-- 处方审核明细表 -->
              <el-table
                :data="summaryDetailList"
                border
                size="small"
                :row-class-name="tableRowClassName"
                :header-cell-class-name="tableHeaderName"
                style="width: 100%;"
              >
                <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
                <el-table-column
                  v-for="(item,index) in summaryDetailKey"
                  :key="index"
                  :prop="item.prop"
                  :label="item.label"
                  :width="item.width"
                  align="center"
                ></el-table-column>
              </el-table>
              <Pagination class="text-center" :page="page" @handlePageChange="handlePage"/>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import { GetReportSummary, GetReportSummaryDetail } from '@/api/report'
import { Allauditstate } from '@/api/dictionaries'
import Pagination from '@/components/Pagination'

export default {
  name: '',
  components: { Pagination },
  data() {
    return {
      // 分页
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: '',
        totalPage: ''
      },
      // 查询条件
      form: {
        beginTime: "",
        endTime: ""
      },
      // 获取审核状态字典列表
      stateList: [],
      // 汇总表表头
      summarySheetKey: [
        {
          prop: 'state',
          width: '',
          label: '审核结果'
        },
        {
          prop: 'number',
          width: '',
          label: '人数'
        },
        {
          prop: 'percentage',
          width: '',
          label: '构成比'
        }
      ],
      // 汇总表数据
      summarySheetList: [],
      // 明细表表头
      summaryDetailKey: [
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
          prop: 'auditResult',
          width: '',
          label: '系统审核结果'
        },
        {
          prop: 'problemName',
          width: '',
          label: '未通过审核原因'
        }
      ],
      // 明细表数据
      summaryDetailList: []
    }
  },
  created() {
    this.getAllauditstateList();
    this.getTime();
  },
  methods: {
    // 获取当天日期
    getTime() {
      const add0 = m => {
        return m < 10 ? '0' + m : m;
      };
      var time = new Date();
      var y = time.getFullYear();
      var m = time.getMonth() + 1;
      var d = time.getDate();
      this.form.beginTime = y + '-' + add0(m) + '-' + add0(d) + ' ' + '00:00:00';
      this.form.endTime = y + '-' + add0(m) + '-' + add0(d) + ' ' + '23:59:59';
    },
    //  获取审核状态字典列表
    async getAllauditstateList() {
      const res = await Allauditstate();
      if (res.code === 0) {
        this.stateList = res.result;
      } else {
        this.$message.error(res.msg);
      }
    },

    // 获取处方审核汇总表
    async getReportSummaryList() {
      let timeStr = this.form.beginTime + ',' + this.form.endTime;
      const res = await GetReportSummary({
        beginDateScope: timeStr
      });
      if (res.code === 0) {
        this.summarySheetList = res.result.result;
        if (this.summarySheetList.length > 0) {
          this.summarySheetList.map(item => {
            this.stateList.map(state => {
              if (item.state === state.id) {
                item.state = state.stateName;
              }
            })
          })
        }
      } else {
        this.$message.error(res.msg)
      }
    },

    // 获取处方审核详情表
    async getReportSummaryDetailList() {
      let timeStr = this.form.beginTime + ',' + this.form.endTime;
      const res = await GetReportSummaryDetail({
        beginDateScope: timeStr,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      });
      if (res.code === 0) {
        this.summaryDetailList = res.result;
        this.page = res.page || this.page;
        if (this.summaryDetailList.length > 0) {
          this.summaryDetailList.map(item => {
            item.patientSex = item.patientSex === 0 ? '男' : '女';
          })
        }
      } else {
        this.$message.error(res.msg)
      }
    },

    // 查询
    handleSearch() {
      if (this.form.beginTime && this.form.endTime) {
        this.page.pageNo = 1;
        this.getReportSummaryList();
        this.getReportSummaryDetailList();
      } else {
        this.$message.warning('请选择时间');
        return false;
      }
    },


    handlePage() {
      this.getReportSummaryDetailList();
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
