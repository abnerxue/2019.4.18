<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 80px;
  text-align: center;
  display: flex;
  align-items: center;
  margin-left: 30px;
}

.plateNum {
  width: 12.5vw;
  margin-top: 5px;
  border-radius: 4px;
  font-size: 14px;
  color: #333333;
  height: 38px;
}

.searchItem {
  display: flex;
  align-items: center;
  // margin-left: -150px;
}

.title {
  width: 80px;
  line-height: 24px;
  height: 24px;
  font-size: 16px;
  color: #fff;
  margin-left: 20px;
  border-left: 4px solid #0084f3;
  position: relative;
  top: 20px;
  margin-bottom: 20px;
  text-align: left;
  padding-left: 10px;
}

#motorcadeTable {
  min-width: 580px;
}
</style>

<style>
.ivu-table-wrapper {
  margin: 10px;
}

.ivu-page {
  margin-top: 20px !important;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="monitorStatistics"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">车队统计</h5>
      <div style="display: flex;min-width: 600px;flex-direction: row;flex-wrap: wrap;align-items: center;justify-content: space-evenly;position: relative;margin-left: 70px;margin-top: 30px;">
        <div class="searchItem">
          <div class="labelName">车队名称：</div>
          <Input
            v-model="plateNum"
            placeholder="请输入车队名称"
            class="plateNum"
          />
        </div>
        <div class="searchItem">
          <div class="labelName">统计省份：</div>
          <Select
            v-model="statisticalProvinces"
            filterable
            multiple
            style="width:240px;"
          >
            <Option
              v-for="item in provinceList"
              :value="item.value"
              :key="item.value"
            >{{ item.label }}</Option>
          </Select>
        </div>
        <div class="searchItem">
          <div class="labelName">统计时间：</div>
          <DatePicker
            type="datetimerange"
            placeholder="选择开始时间和结束时间"
            style="width: 325px;color: #000"
            v-model="datePicker"
          ></DatePicker>
        </div>
        <Button
          type="primary"
          @click="searchList"
        >查询</Button>
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="tableData"
          id="motorcadeTable"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
          style="margin-top:0px;"
        ></Page>
      </div>
    </div>
  </div>
</template>
<script>
let dayStart;
let dayNow;

function convertUTCTimeToLocalTime(UTCDateString) {
  if (!UTCDateString) {
    return "-";
  }
  function formatFunc(str) {
    //格式化显示
    return str > 9 ? str : "0" + str;
  }
  var date2 = new Date(UTCDateString); //这步是关键
  var year = date2.getFullYear();
  var mon = formatFunc(date2.getMonth() + 1);
  var day = formatFunc(date2.getDate());
  var hour = date2.getHours();
  hour = formatFunc(hour);
  var min = formatFunc(date2.getMinutes());
  var sec = formatFunc(date2.getSeconds());
  var dateStr =
    year + "-" + mon + "-" + day + " " + hour + ":" + min + ":" + sec;
  return dateStr;
}
export default {
  name: "motorcadeStatistics",
  data() {
    return {
      plateNum: "",
      datePicker: [dayStart, dayNow],
      statisticalProvinces: [],
      provinceList: [
        {
          value: "北京",
          label: "北京"
        },
        {
          value: "天津",
          label: "天津"
        },
        {
          value: "上海",
          label: "上海"
        },
        {
          value: "重庆",
          label: "重庆"
        },
        {
          value: "河北省",
          label: "河北省"
        },
        {
          value: "山西省",
          label: "山西省"
        },
        {
          value: "辽宁省",
          label: "辽宁省"
        },
        {
          value: "吉林省",
          label: "吉林省"
        },
        {
          value: "黑龙江省",
          label: "黑龙江省"
        },
        {
          value: "江苏省",
          label: "江苏省"
        },
        {
          value: "浙江省",
          label: "浙江省"
        },
        {
          value: "安徽省",
          label: "安徽省"
        },
        {
          value: "福建省",
          label: "福建省"
        },
        {
          value: "江西省",
          label: "江西省"
        },
        {
          value: "山东省",
          label: "山东省"
        },
        {
          value: "河南省",
          label: "河南省"
        },
        {
          value: "湖北省",
          label: "湖北省"
        },
        {
          value: "湖南省",
          label: "湖南省"
        },
        {
          value: "广东省",
          label: "广东省"
        },
        {
          value: "海南省",
          label: "海南省"
        },
        {
          value: "四川省",
          label: "四川省"
        },
        {
          value: "贵州省",
          label: "贵州省"
        },
        {
          value: "云南省",
          label: "云南省"
        },
        {
          value: "陕西省",
          label: "陕西省"
        },
        {
          value: "甘肃省",
          label: "甘肃省"
        },
        {
          value: "青海省",
          label: "青海省"
        },
        {
          value: "台湾省",
          label: "台湾省"
        },
        {
          value: "内蒙古自治区",
          label: "内蒙古"
        },
        {
          value: "广西壮族自治区",
          label: "广西壮族自治区"
        },
        {
          value: "西藏",
          label: "西藏"
        },
        {
          value: "宁夏",
          label: "宁夏"
        },
        {
          value: "新疆",
          label: "新疆"
        },
        {
          value: "香港",
          label: "香港"
        },
        {
          value: "澳门",
          label: "澳门"
        }
      ],
      columns: [
        {
          title: "企业名称",
          key: "cdmc",
          // fixed: "left",
          minWidth: 100,
          align: "center"
        },
        {
          title: "总里程(km)",
          key: "lcs",
          minWidth: 100,
          align: "center"
        },
        {
          title: "总时长",
          key: "xssj",
          minWidth: 100,
          align: "center"
        },
        {
          title: "平均速度(km/h)",
          key: "pjsd",
          minWidth: 100,
          align: "center"
        },
        {
          title: "出现区域",
          key: "sssfshow",
          width: 200,
          // fixed: "right",
          align: "center",
          tooltip: true
        }
      ],
      tableData: [],
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: []
    };
  },
  beforeCreate() {
    function getNowFormatDate() {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate =
        date.getFullYear() +
        seperator1 +
        month +
        seperator1 +
        strDate +
        " " +
        date.getHours() +
        seperator2 +
        date.getMinutes() +
        seperator2 +
        date.getSeconds();
      dayNow = currentdate;

      date.setMonth(date.getMonth() - 1);
      var startDate = date.getDate();
      if (startDate >= 0 && startDate <= 9) {
        startDate = "0" + startDate;
      }

      month = date.getMonth() + 1;
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      var startdate =
        date.getFullYear() +
        seperator1 +
        month +
        seperator1 +
        startDate +
        " " +
        "00" +
        seperator2 +
        "00" +
        seperator2 +
        "00";
      dayStart = startdate;
    }
    getNowFormatDate();
  },
  mounted() {
    this.getTableData(this.pageIndex);
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
    // $("#motorcadeTable").css(
    //   "height",
    //   document.body.clientHeight * 500 / 980 + "px"
    // );
    // $(".ivu-table-body").css(
    //   "height",
    //   $("#motorcadeTable").height() - 40 + "px"
    // );
    if (window.innerHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }

    if (window.innerWidth < 900) {
      $("#monitorStatistics").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $(".ivu-page").css("margin-top", "0px !important");
      $("#motorcadeTable").css(
        "height",
        (window.innerHeight * 450) / 980 + "px"
      );
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
  },
  methods: {
    changePage(index) {
      this.getTableData(index);
    },
    searchList() {
      this.getTableData(1);
    },
    getTableData(pageIndex) {
      let vm = this;
      let kssjValue = "";
      let jssjValue = "";
      if (vm.datePicker.length > 0) {
        kssjValue =
          convertUTCTimeToLocalTime(vm.datePicker[0]) == "-"
            ? ""
            : convertUTCTimeToLocalTime(vm.datePicker[0]);
        jssjValue =
          convertUTCTimeToLocalTime(vm.datePicker[1]) == "-"
            ? ""
            : convertUTCTimeToLocalTime(vm.datePicker[1]);
      }
      let dataJson = {
        cph: vm.plateNum,
        sssf: vm.statisticalProvinces,
        kssj: kssjValue,
        jssj: jssjValue,
        pageIndex: pageIndex,
        pageSize: vm.pageSize
      };

      let arrayString = "";
      vm.statisticalProvinces.forEach(element => {
        arrayString += element + ",";
      });

      let dataParams = {
        cph: vm.plateNum,
        sssf: arrayString,
        kssj: kssjValue,
        jssj: jssjValue,
        pageIndex: pageIndex,
        pageSize: vm.pageSize
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/fleetcarcount",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        data: dataJson,
        params: dataParams,
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          vm.tableData = response.data.result.array;
          vm.tableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    }
  }
};
</script>



