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
  margin-top: 10px;
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

.ivu-table-wrapper {
  margin: 10px;
}

#bicycleTable {
  min-width: 580px;
}

.showCarStatistics {
  display: block;
}

.isShowCarStatistics {
  display: none;
}

.showBusinesStatistics {
  display: none;
}

.isShowBusinesStatistics {
  display: block;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="bicycleStatistics"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">单车统计</h5>
      <div style="display: flex;min-width: 600px;flex-direction: row;flex-wrap: wrap;align-items: center;justify-content: space-evenly;position: relative;margin-left: 70px;margin-top: 10px;">
        <div class="searchItem">
          <div class="labelName">车牌号码：</div>
          <Input
            v-model="plateNum"
            placeholder="请输入车牌号"
            class="plateNum"
            ref="plateNum"
          />
        </div>
        <div class="searchItem">
          <div class="labelName">统计省份：</div>
          <Select
            v-model="statisticalProvinces"
            filterable
            multiple
            style="width:240px;"
            ref="statisticalProvinces"
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
            ref="datePicker"
          ></DatePicker>
        </div>
        <div>
          <Button
            type="primary"
            @click="searchList"
          >查询</Button>
          <Button
            type="primary"
            @click="searchExportTable"
            :disabled="isButtonDisabled"
            style="margin-left:5px;"
          >导出
          </Button>
          <Button
            type="primary"
            @click="businessStatistics"
            style="margin-left:20px;"
          >{{businessFont}}</Button>
        </div>
      </div>
      <div
        style="padding-left: 10px;padding-right: 10px;"
        :class="{showCarStatistics:showCarStatistics,isShowCarStatistics:isShowCarStatistics}"
      >
        <Table
          :columns="columns"
          :data="tableData"
          id="bicycleTable"
          ref="bicycleTable"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
          style="margin-top:0px;"
        ></Page>
      </div>
      <div
        style="padding-left: 10px;padding-right: 10px;"
        :class="{showBusinesStatistics:showBusinesStatistics,isShowBusinesStatistics:isShowBusinesStatistics}"
      >
        <Table
          :columns="businessColumns"
          :data="businessTableData"
          id="motorcadeTable"
        ></Table>
        <Page
          :total="businessTableDataLength"
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
  name: "bicycleStatistics",
  data() {
    return {
      businessTableData: [],
      isButtonDisabled: false,
      provinceList: [],
      showCarStatistics: true,
      isShowCarStatistics: false,
      showBusinesStatistics: true,
      isShowBusinesStatistics: false,
      businessFont: "车队统计",
      plateNum: "",
      tempData: [],
      datePicker: [dayStart, dayNow],
      statisticalProvinces: [],
      columns: [
        {
          title: "车牌号码",
          key: "cph",
          // fixed: "left",
          minWidth: 100,
          align: "center"
        },
        {
          title: "车牌种类",
          key: "cpzlshow",
          minWidth: 100,
          align: "left"
        },
        {
          title: "总里程(km)",
          key: "lcs",
          minWidth: 100,
          align: "left"
        },
        {
          title: "总时长",
          key: "xssj",
          minWidth: 100,
          align: "left"
        },
        {
          title: "平均速度(km/h)",
          key: "pjsd",
          minWidth: 100,
          align: "left"
        },
        {
          title: "行程数",
          align: "center",
          children: [
            {
              title: "<20的行程",
              key: "lowlcsnumber",
              minWidth: 100,
              align: "left"
            },
            {
              title: "大于20 到 60的行程",
              key: "midlcsnumber",
              minWidth: 100,
              align: "left"
            },
            {
              title: "大于60的行程",
              key: "uplcsnumber",
              minWidth: 100,
              align: "left"
            }
          ]
        },
        {
          title: "出现区域",
          key: "sssfshow",
          align: "left",
          width: 200,
          // fixed: "right",
          tooltip: true
          // ellipsis:true,
        }
      ],
      businessColumns: [
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
      businessTableDataLength: 10,
      busnessTableData: [],
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
    this.getBusinessTableData(this.pageIndex);
    this.getProvinceList();
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
    if (window.innerHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }

    if (window.innerWidth < 900) {
      $("#bicycleStatistics").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $(".ivu-page").css("margin-top", "0px !important");
      $("#bicycleTable").css("height", (window.innerHeight * 450) / 980 + "px");
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
  },
  methods: {
    exportData: function() {
      let kssjValue = "";
      let jssjValue = "";
      if (this.datePicker.length > 0) {
        kssjValue =
          convertUTCTimeToLocalTime(this.datePicker[0]) == "-"
            ? ""
            : convertUTCTimeToLocalTime(this.datePicker[0]);
        jssjValue =
          convertUTCTimeToLocalTime(this.datePicker[1]) == "-"
            ? ""
            : convertUTCTimeToLocalTime(this.datePicker[1]);
      }

      if (window.outerHeight < 800) {
        this.pageSize = 5;
      } else {
        this.pageSize = 10;
      }

      this.$refs.bicycleTable.exportCsv({
        filename: "单车统计",
        columns: this.tempColumns,
        data: this.tempData
      });
    },
    getBusinessTableData(pageIndex) {
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
          vm.businessTableData = response.data.result.array;
          vm.businessTableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    businessStatistics() {
      if (this.businessFont == "车队统计") {
        this.$refs.plateNum.disabled = true;
        this.$refs.statisticalProvinces.disabled = true;
        this.isButtonDisabled = true;
        this.$refs.datePicker.disabled = true;
        this.businessFont = "单车统计";
        this.showCarStatistics = false;
        (this.isShowCarStatistics = true), (this.showBusinesStatistics = false);
        this.isShowBusinesStatistics = true;
      } else {
        this.businessFont = "车队统计";
        this.$refs.plateNum.disabled = false;
        this.$refs.statisticalProvinces.disabled = false;
        this.isButtonDisabled = false;
        this.$refs.datePicker.disabled = false;
        this.showCarStatistics = true;
        (this.isShowCarStatistics = false), (this.showBusinesStatistics = true);
        this.isShowBusinesStatistics = false;
      }
    },
    getProvinceList() {
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/interface/allprofweb",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          vm.provinceList = response.data.result;
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    changePage(index) {
      this.getTableData(index);
    },
    searchList() {
      this.getTableData(1);
    },
    searchExportTable: function() {
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
        jssj: jssjValue
      };

      let arrayString = "";
      vm.statisticalProvinces.forEach(element => {
        arrayString += element + ",";
      });

      let dataParams = {
        cph: vm.plateNum,
        sssf: arrayString,
        kssj: kssjValue,
        jssj: jssjValue
      };

      this.tempColumns = [
        {
          title: "车牌号码",
          key: "cph",
          // fixed: "left",
          minWidth: 100,
          align: "center"
        },
        {
          title: "车牌种类",
          key: "cpzlshow",
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
          title: "<20的行程",
          key: "lowlcsnumber",
          minWidth: 100,
          align: "center"
        },
        {
          title: "大于20 到 60的行程",
          key: "midlcsnumber",
          minWidth: 100,
          align: "center"
        },
        {
          title: "大于60的行程",
          key: "uplcsnumber",
          minWidth: 100,
          align: "center"
        },
        {
          title: "出现区域",
          key: "sssfshow",
          align: "center",
          width: 200,
          // fixed: "right",
          tooltip: true
          // ellipsis:true,
        }
      ];

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/singlecarcount",
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
          vm.tempData = response.data.result;
          // vm.tableDataLength = response.data.result.length;
          vm.exportData();
        })
        .catch(function(response) {
          console.log(response);
        });
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
        url: "/gm-data/api/interface/singlecarcount",
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



