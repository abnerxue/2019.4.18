<template>
  <div class="search-detail">
    <!-- 患者及处方信息 -->
    <el-card
      class="box-card"
      style="height: 100%;"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <el-row>
          <span style="font-weight: 900;">患者及处方信息</span>
        </el-row>
        <el-row class="patient-detail">
          <el-col :span="4">
            <span>患者姓名：{{ preDetail.detail.patient.patientName }}</span>
          </el-col>
          <el-col :span="4">
            <span>患者性别：{{ preDetail.detail.patient.gender === 0 ? '男' : '女' }}</span>
          </el-col>
          <el-col :span="4">
            <span>年龄：{{ preDetail.detail.patient.age }}</span>
          </el-col>
          <el-col :span="4">
            <span>出生日期：{{ preDetail.detail.patient.birthday }}</span>
          </el-col>
          <el-col :span="4">
            <span>身高：{{ preDetail.detail.patient.height }}</span>
          </el-col>
          <el-col :span="4">
            <span>体重(kg)：{{ preDetail.detail.patient.weight }}</span>
          </el-col>
        </el-row>
        <el-row class="patient-detail">
          <el-col :span="12">
            <span>特殊人群：{{ preDetail.detail.patient.specialGroup }}</span>
          </el-col>
          <el-col :span="12">
            <span>过敏史：{{ preDetail.detail.patient.allergyHistory }}</span>
          </el-col>
        </el-row>
      </div>
      <div class="pre-detail">
        <el-row>
          <el-col :span="6">
            <span>医院名称：{{ preDetail.detail.unitname.unitName }}</span>
          </el-col>
          <el-col :span="5">
            <span>审方中心：{{ preDetail.detail.auditcentersee.auditCenterName }}</span>
          </el-col>
          <el-col :span="5">
            <span>就诊号：{{ preDetail.detail.visitNumber }}</span>
          </el-col>
          <el-col :span="5">
            <span>处方编号：{{ preDetail.detail.prescriptionNumber }}</span>
          </el-col>
          <el-col :span="3">
            <span>{{ preDetail.detail.submmitTime }}</span>
          </el-col>
        </el-row>
        <el-row>
          <span>诊断：</span>
          <span v-for="(item, index) in preDetail.detail.diagnosis" :key="index">
            {{ item.diagnosisName }}&nbsp;&nbsp;&nbsp;
          </span>
        </el-row>
        <el-table
          :data="preDetail.detail.premedcine"
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
            label="药品名称"
          >
          </el-table-column>
          <el-table-column
            prop="route"
            label="给药途径"
          >
          </el-table-column>
          <el-table-column
            prop="usage"
            label="用法"
          >
          </el-table-column>
          <el-table-column
            prop="eachDose"
            label="次用量"
          >
          </el-table-column>
          <el-table-column
            prop="specification"
            label="规格"
          >
          </el-table-column>
          <el-table-column
            prop="total"
            label="总数"
          >
          </el-table-column>
        </el-table>
        <hr>
        <el-row class="pre-dept">
          <span>科室：{{ preDetail.detail.medicaldept.deptName }}</span>
          <span style="margin-left: 80px;">处方医生：{{ preDetail.detail.hospitalstaff.doctorName }}</span>
        </el-row>
      </div>
    </el-card>

    <!-- 医院审核结果 -->
    <el-card
      class="box-card hospital-card"
      style="height: 100%;"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <el-row>
          <span>医院审核结果</span>
        </el-row>
      </div>
      <el-table
        :data="preDetail.detail.problemcodehos"
        style="width: 100%; background: transparent;"
        class="table-data"
      >
        <el-table-column
          prop="action"
          label="问题级别"
        >
        </el-table-column>
        <el-table-column
          prop="problemCode"
          label="问题代码"
        >
        </el-table-column>
        <el-table-column
          prop="problemName"
          label="问题简述"
        >
        </el-table-column>
        <el-table-column
          prop="pharmacistRemark"
          label="药师备注"
        >
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 区域审核 -->
    <el-card
      class="box-card region-card"
      style="height: 100%;"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <el-row>
          <span>区域审核结果</span>
        </el-row>
      </div>
      <el-table
        :data="preDetail.detail.problemcode"
        style="width: 100%; background: transparent;"
        class="table-data"
      >
        <el-table-column
          prop="action"
          label="问题级别"
        >
        </el-table-column>
        <el-table-column
          prop="problemCode"
          label="问题代码"
        >
        </el-table-column>
        <el-table-column
          prop="problemName"
          label="问题简述"
        >
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 操作 -->
    <!-- <el-card
      class="box-card handle-card"
      style="height: 100%;"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <el-row>
          <span>操作</span>
        </el-row>
      </div>
      <el-row>操作记录</el-row>
      <el-row class="handle-row">
        <el-col :span="4">
          <span>2019年3月1日 13:10:52</span>
        </el-col>
        <el-col :span="5">
          <span>操作：系统</span>
        </el-col>
        <el-col :span="5">
          <span>审核结果：人工审核</span>
        </el-col>
        <el-col :span="5">
          <span>备注：双签</span>
        </el-col>
      </el-row>
    </el-card> -->

    <!-- 历史处方 -->
    <el-card
      class="box-card history-card"
      style="height: 100%;"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <el-row>
          <span>历史处方</span>
        </el-row>
      </div>
      <el-row>
        <el-col :span="5">
          <el-popover
            placement="bottom"
            width="425"
            trigger="click"
          >
            <div v-if="calendar.isShowCalendar">
              <calendar
                @choseDay="clickDay"
                :agoDayHide="calendar.startTime"
                :futureDayHide='calendar.endTime'
                :markDate="calendar.problemList"
                ref="Calendar"
              ></calendar>
            </div>
            <el-input
              placeholder="请选择日期"
              @focus="showCalendar"
              class="select-input"
              slot="reference"
              v-model="searchTime"
              clearable
            >
            </el-input>
          </el-popover>
        </el-col>
        <el-col :span="2">
          <el-button
            type="primary"
            class="search-btn"
            @click="getprehisbyid"
          >查询</el-button>
        </el-col>
      </el-row>
      <el-tabs
        v-model="activeList"
        type="card"
        v-if="preDetail.list.length > 0"
      >
        <el-tab-pane
          v-for="(item, index) in preDetail.list"
          :label="'处方' + (index + 1)"
          :name="'' + index"
          :key="index"
        >
          <el-row style="margin-bottom: 15px;">
            <span>诊断：</span>
            <span v-for="(i, index) in item.diagnosis" :key="index">
              {{ i.diagnosisName }}&nbsp;&nbsp;&nbsp;
            </span>
          </el-row>
          <el-table
            :data="item.prescriptionMedcine"
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
              label="药品名称"
            >
            </el-table-column>
            <el-table-column
              prop="route"
              label="给药途径"
            >
            </el-table-column>
            <el-table-column
              prop="usage"
              label="用法"
            >
            </el-table-column>
            <el-table-column
              prop="eachDose"
              label="次用量"
            >
            </el-table-column>
            <el-table-column
              prop="specification"
              label="规格"
            >
            </el-table-column>
            <el-table-column
              prop="total"
              label="总数"
            >
            </el-table-column>
          </el-table>
          <el-button
            type="primary"
            @click="getPreHisDetail(item.id)"
          >查看处方</el-button>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    <PrescriptionModal
      :isVisible='isVisible'
      :data="detail"
    />
  </div>
</template>

<script>
// todo 原型图修改，添加操作模块
import PrescriptionModal from '@/components/PrescriptionModal'
import { Prehisbyid } from '@/api/search'

export default {
  name: 'detail',
  components: { PrescriptionModal },
  data() {
    return {
      isVisible: false,
      detail: {},
      // 处方详情
      preDetail: {
        checkday: "",
        detail: {
          patient: {
            age: "", // 患者年龄
            allergyHistory: "", // 过敏史
            birthday: "", // 患者出生日期
            gender: "", // 患者性别
            height: "", // 身高
            patientName: "", // 患者姓名
            specialGroup: "", // 特殊人群
            weight: "" // 体重
          },
          unitname: {
            unitName: "" // 医院名称
          },
          auditcentersee: {
            auditCenterName: "" // 审方中心
          },
          prescriptionNumber: "", // 处方号
          visitNumber: "", // 就诊号
          submmitTime: "", // 处方开具时间
          diagnosis: [], // 诊断
          premedcine: [], // 用药
          medicaldept: {
            deptName: "" // 科室
          },
          hospitalstaff: {
            doctorName: "" // 处方医生
          },
          problemcode: [], // 区域审核结果
          problemcodehos: [] // 医院审核结果
        },
        list: [] // 历史处方
      },

      // 历史处方选项
      activeList: '0',

      // 历史处方查询时间
      searchTime: "",

      // vue-calendar-component 时间组件
      calendar: {
        startTime: "",
        endTime: "",
        problemList: [], // 问题处方
        submmitTime: "",  // 处方详情时间，用于设置日历组件
        isShowCalendar: false // 是否显示日历组件
      }
    }
  },
  async created() {
    await this.getprehisbyid();
    // 初始化日历时间范围
    await this.setCalendarTime();
  },
  methods: {
    // 选择日期，绑定设置历史处方查询时间
    clickDay(data) {
      var reg = /\//g;
      data = data.replace(reg, '-');
      this.searchTime = data;
    },

    // 初始化日历
    async showCalendar() {
      let submmitTime = new Date(this.calendar.submmitTime);
      let checkday = this.preDetail.checkday;
      let _submmitTime = submmitTime.getTime();
      let _checkday = checkday * 24 * 60 * 60 * 1000;
      let _days = _submmitTime - _checkday;
      this.calendar.startTime = _days.toString().substring(0, 10);
      this.calendar.endTime = _submmitTime.toString().substring(0, 10);
      this.calendar.submmitTime = this.preDetail.detail.submmitTime;
      await this.changeShowCalendar();
      await this.setChoseMonth();
    },

    // 显示日历
    changeShowCalendar() {
      this.calendar.isShowCalendar = true;
    },

    // 初始化日历显示哪一页
    setChoseMonth() {
      this.$refs.Calendar.ChoseMonth(this.calendar.submmitTime.substring(0, 10), false);
    },

    // 初始化日历时间范围
    setCalendarTime() {
      var reg = /-/g;
      this.calendar.problemList = [];
      if (this.preDetail.list.length > 0) {
        this.preDetail.list.map(item => {
          if (item.isProblem == 1) {
            let time = item.auditTime.replace(reg, '/');
            this.calendar.problemList.push(time);
          }
        })
      }
      this.calendar.submmitTime = this.preDetail.detail.submmitTime;
    },

    // 获取处方详情
    async getprehisbyid() {
      let args = {};
      if (this.searchTime == "") {
        args = {
          id: this.$route.query.id
        }
      } else {
        args = {
          id: this.$route.query.id,
          dateTime: this.searchTime
        }
      }
      const res = await Prehisbyid(args);
      if (res.code === 0) {
        this.preDetail.checkday = res.result.checkday;
        this.preDetail.detail = res.result.detail[0];
        this.preDetail.list = res.result.list;
        this.activeList = '0';
      } else {
        this.$message.error(res.msg)
      }
    },

    // 查看处方
    async getPreHisDetail(id) {
      const res = await Prehisbyid({
        id: id
      });
      if (res.code === 0) {
        this.detail = res.result.detail[0];
        this.isVisible = true;
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>
<style lang="less">
.search-detail {
  .box-card {
    .clearfix {
      .patient-detail {
        font-size: 14px;
        margin-top: 10px;
      }
    }
    .pre-detail {
      .el-row {
        font-size: 14px;
        margin-bottom: 15px;
      }
      .pre-dept {
        margin: 15px 0 0;
      }
    }
    .el-table th,
    .el-table tr {
      background-color: transparent !important;
      text-align: center;
      color: #303133;
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
  .hospital-card,
  .region-card,
  .history-card,
  .handle-card {
    margin-top: 20px;
    .el-row {
      font-size: 14px;
    }
  }
  .history-card {
    .el-date-editor {
      margin-bottom: 10px;
    }
    .el-button.el-button--primary {
      padding: 0;
      height: 28px;
      width: 85px;
      margin-top: 20px;
    }
    .el-button.search-btn.el-button--primary {
      padding: 0;
      height: 32px;
      width: 85px;
      margin-top: 0;
    }
    .el-tabs__item {
      font-weight: inherit;
    }
    .el-input {
      width: 215px;
      height: 32px;
      line-height: 32px;
      margin-bottom: 10px;
      .el-input__inner {
        width: 215px;
        height: 32px;
        line-height: 32px;
      }
    }
  }
  .handle-card {
    .handle-row {
      margin-top: 10px;
    }
  }
}

// 全局样式，el-popover弹出框内，vue-calendar-component 时间组件的样式
.wh_content_all {
  background-color: #fff !important;
  .wh_item_date,
  .wh_top_tag {
    color: #303133;
  }
  .wh_top_changge {
    .wh_content_li {
      color: #303133;
    }
    .wh_jiantou1 {
      border-top: 2px solid #303133;
      border-left: 2px solid #303133;
    }
    .wh_jiantou2 {
      border-top: 2px solid #303133;
      border-right: 2px solid #303133;
    }
  }
  .wh_item_date.wh_isMark {
    background: transparent !important;
    color: red;
  }
  .wh_item_date:hover {
    background-color: transparent;
    color: #409eff;
  }
  .wh_item_date.wh_want_dayhide:hover {
    color: #bfbfbf;
  }
  .wh_item_date.wh_chose_day {
    color: #fff;
    background-color: #409eff;
  }
  .wh_item_date.wh_isMark.wh_chose_day {
    color: #fff;
    background-color: #409eff !important;
  }
}
</style>
