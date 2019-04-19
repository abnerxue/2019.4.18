<template>
  <div>
    <el-dialog
      title=""
      :visible.sync="isVisible"
      :show-close="false"
      width="600px"
      :before-close="hideModal"
    >
      <div
        class="d-flex"
        style="margin-top: -30px;"
      >
        <div class="prescription-wrap">
          <h2>{{data.id}} 苏州市第五人民医院门诊处方</h2>
          <div class="second-title">
            <span class="window-name">
              发药窗口：急诊药房窗口
            </span>
            <span>普通</span>
          </div>
          <div class="per-info">
            <div
              class="form-row"
              v-if="data.patient"
            >
              <span>姓名：<strong>{{data.patient.patientName}}</strong></span>
              <span style="margin-right:58px;">性别&nbsp;{{data.patient.gender === 0 ? '男' : '女'}}</span>
              <span style="margin-right:28px;">年龄&nbsp;{{data.patient.age}}</span>
              <span>{{this.$moment(data.submmitTime).format('YYYY-MM-DD')}}</span>
            </div>
            <div class="form-row">
              <span>个人编号</span>
              <span style="margin-right:28px;">费用类别&nbsp;&nbsp;医疗保险</span>
              <span>科别&nbsp;&nbsp;二类疫苗接种</span>
            </div>
            <div
              class="form-row"
              v-if="data.diagnosis"
            >
              <span style="width:236px;">门诊号&nbsp;SA73027047</span>
              <span>临床诊断&nbsp;{{data.diagnosis.diagnosisName}}</span>
            </div>
            <div
              class="prescription-content"
              v-if="data.premedcine"
            >
              <div class="prescript-content-header">
                <span class="f2 font-bold">处方号</span>
                <span style="margin-left:10px;">{{data.prescriptionNumber}}</span>
              </div>
              <div class="content-list">
                <el-table
                  :data="data.premedcine"
                  style="width: 100%; background: transparent;"
                  class="table-data"
                >
                <el-table-column
                  type="index"
                  label="序号"
                >
                </el-table-column>
                <el-table-column
                  prop="medcineName"
                  label="药品名称">
                </el-table-column>
                <el-table-column
                  prop="route"
                  label="给药途径">
                </el-table-column>
                <el-table-column
                  prop="usage"
                  label="用法">
                </el-table-column>
                <el-table-column
                  prop="eachDose"
                  label="次用量">
                </el-table-column>
                <el-table-column
                  prop="specification"
                  label="规格">
                </el-table-column>
                <el-table-column
                  prop="total"
                  label="总数">
                </el-table-column>
                </el-table>
              </div>
              <div class="footer-tips">
                <div
                  v-if="data.hospitalstaff"
                  class="d-flex"
                  style="height:26px;"
                >
                  <span>审核调配：&nbsp;</span>
                  <div class="empty-box"></div>
                  <span class="flex-fill">核对发药：</span>
                  <span>医师： {{data.hospitalstaff.doctorName}}</span>
                </div>
                <p class="f2"><span>合计金额：{{data.totalFee}}</span></p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: ['isVisible', 'data'],
  data() {
    return {
      tableData: [{
        name: '通过',
        address: 'XXXXXXXXXX'
      }, {
        name: '通过',
        address: 'XXXXXXXXXX'
      }, {
        name: '通过',
        address: 'XXXXXXXXXX'
      }, {
        name: '通过',
        address: 'XXXXXXXXXX'
      }]
    }
  },
  methods: {
    hideModal() {
      this.$parent.isVisible = false
    }
  }
}
</script>

<style  lang="scss">
.prescription-wrap {
  width: 100%;

  h2 {
    color: #000;
    text-align: center;
    font-size: 16px;
  }
  .second-title {
    margin-top: 10px;
    display: flex;
    padding: 0 80px;
    justify-content: space-between;
  }
  .per-info {
    margin-top: 10px;
    line-height: 28px;
    .form-row {
      display: flex;
      span:first-child {
        width: 170px;
      }
    }
  }
  .prescription-content {
    display: flex;
    flex-direction: column;
    padding: 15px 10px;
    padding-bottom: 0;
    height: 460px;
    border: 2px solid #000;
    .prescript-content-header {
      display: flex;
      align-items: center;

      line-height: 26px;
    }
    .prescrpt-icon {
      width: 26px;
      height: 26px;
      margin-right: 10px;
      background-color: blue;
    }
    .content-list {
      flex: 1;
      .el-table th,
      .el-table tr {
        background-color: transparent !important;
        text-align: center;
        color: #606266;
      }
      .el-table td,
      .el-table th {
        padding: 3px 0;
        text-align: center;
      }
      .el-table--enable-row-hover .el-table__body tr:hover > td {
        background-color: transparent !important;
      }
      .el-table__header-wrapper {
        background-color: transparent !important;
      }
      .el-table td,
      .el-table th.is-leaf {
        border-bottom: 1px solid transparent;
      }
      .el-table::before {
        background: transparent;
      }
    }
    .footer-tips {
      justify-self: flex-end;
    }
  }
  .empty-box {
    display: inline-block;
    width: 26px;
    height: 26px;
    margin-right: 10px;
    background-color: blue;
  }
}
</style>
