<template>
  <p class="f4">正在开发中...</p>
  <!-- <div id="report">
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
    >
      <el-form-item>
        <el-input placeholder="单位名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input placeholder="审方中心"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input placeholder="处方号/就诊号"></el-input>
      </el-form-item>
      <el-form-item label="退回级别">
        <el-select v-model="formInline.region">
          <el-option
            label="人工审核"
            value="0"
          ></el-option>
          <el-option
            label="打回"
            value="1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间选择">
        <el-date-picker v-model="formInline.beginDatetime"></el-date-picker>
      </el-form-item>
      <el-form-item label="打回对象">
        <el-select v-model="formInline.daHuiDuiXiang">
          <el-option
            label="医生"
            value="0"
          ></el-option>
          <el-option
            label="药师"
            value="1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="打回等级">
        <el-select v-model="formInline.daHuiDengJi">
          <el-option
            label="提醒"
            value="0"
          ></el-option>
          <el-option
            label="驳回"
            value="1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="onSubmit"
        >查询</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        prop="xh"
        label="序号"
        width="50"
      ></el-table-column>
      <el-table-column
        prop="jydwmc"
        label="就医单位名称"
      ></el-table-column>
      <el-table-column
        prop="sfzxmc"
        label="审方中心名称"
      ></el-table-column>
      <el-table-column
        prop="cfh"
        label="处方号/就诊号"
      ></el-table-column>
      <el-table-column
        prop="hzxm"
        label="患者姓名"
        width="80"
      ></el-table-column>
      <el-table-column
        prop="jzks"
        label="就诊科室"
        width="80"
      ></el-table-column>
      <el-table-column
        prop="ysxm"
        label="医师姓名"
        width="80"
      ></el-table-column>
      <el-table-column
        prop="shysxm"
        label="审核药师姓名"
        width="80"
      ></el-table-column>
      <el-table-column
        prop="cfkjsj"
        label="处方开具时间"
        width="180"
      ></el-table-column>
      <el-table-column
        prop="cfshsj"
        label="处方审核时间"
        width="180"
      ></el-table-column>
      <el-table-column
        prop="shzt"
        label="审核状态"
      ></el-table-column>
      <el-table-column
        prop="sfjg"
        label="审方结果"
      ></el-table-column>
      <el-table-column
        prop="wtjb"
        label="问题级别"
      ></el-table-column>
      <el-table-column
        prop="dhyy"
        label="打回原因"
      ></el-table-column>
      <el-table-column
        prop="dhdx"
        label="打回对象"
      ></el-table-column>
      <el-table-column
        prop="dhdj"
        label="打回等级"
      ></el-table-column>
    </el-table>

    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="page.pageNo"
        :page-size="10"
        layout="prev, pager, next, jumper"
        :total.sync="page.totalCount"
      ></el-pagination>
    </div>
  </div> -->
</template>

<script>
export default {
  data() {
    return {
      page: {},
      formInline: {
        user: "",
        region: "",
        beginDatetime: "",
        daHuiDuiXiang: "",
        daHuiDengJi: ""
      },
      tableData: [
        {
          xh: "1",
          jydwmc: "苏州市立医院本部",
          sfzxmc: "苏州市审方中心",
          cfh: "1111",
          hzxm: "张三",
          jzks: "内科",
          ysxm: "李四",
          shysxm: "张三2",
          cfkjsj: "2019-2-16 09:00:00",
          cfshsj: "2019-2-16 09:01:00",
          sfzt: "已审",
          sfjg: "通过",
          wtjb: "禁用",
          dhyy: "",
          dhdx: "系统",
          dhdj: "初审"
        },
        {
          xh: "2",
          jydwmc: "苏州市立医院本部",
          sfzxmc: "苏州市审方中心",
          cfh: "1111",
          hzxm: "张三",
          jzks: "内科",
          ysxm: "李四",
          shysxm: "张三2",
          cfkjsj: "2019-2-16 09:00:00",
          cfshsj: "2019-2-16 09:01:00",
          sfzt: "已审",
          sfjg: "通过",
          wtjb: "禁用",
          dhyy: "",
          dhdx: "系统",
          dhdj: "初审"
        },
        {
          xh: "3",
          jydwmc: "苏州市立医院本部",
          sfzxmc: "苏州市审方中心",
          cfh: "1111",
          hzxm: "张三",
          jzks: "内科",
          ysxm: "李四",
          shysxm: "张三2",
          cfkjsj: "2019-2-16 09:00:00",
          cfshsj: "2019-2-16 09:01:00",
          sfzt: "已审",
          sfjg: "通过",
          wtjb: "禁用",
          dhyy: "",
          dhdx: "系统",
          dhdj: "初审"
        },
        {
          xh: "4",
          jydwmc: "苏州市立医院本部",
          sfzxmc: "苏州市审方中心",
          cfh: "1111",
          hzxm: "张三",
          jzks: "内科",
          ysxm: "李四",
          shysxm: "张三2",
          cfkjsj: "2019-2-16 09:00:00",
          cfshsj: "2019-2-16 09:01:00",
          sfzt: "已审",
          sfjg: "通过",
          wtjb: "禁用",
          dhyy: "",
          dhdx: "系统",
          dhdj: "初审"
        }
      ],
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
};
</script>

