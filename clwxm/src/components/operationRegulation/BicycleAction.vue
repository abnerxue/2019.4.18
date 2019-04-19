<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 100px;
  text-align: center;
  display: flex;
  align-items: center;
}

.cdzmc {
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
  width: 100px;
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

#actionTable {
  min-width: 580px;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="actionStatistics"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 25px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">车辆行为</h5>
      <div style="display: flex;min-width: 600px;flex-direction: row;flex-wrap: wrap;align-items: center;justify-content: space-around;position: relative;margin-top: 30px;">
        <div class="searchItem">
          <div class="labelName">车牌号码：</div>
          <Input
            v-model="plateNum"
            placeholder="请输入车牌号码"
            class="cdzmc"
          />
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
          id="actionTable"
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
    //��ʽ����ʾ
    return str > 9 ? str : "0" + str;
  }
  var date2 = new Date(UTCDateString); //�ⲽ�ǹؼ�
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
  name: "actionStatistics",
  data() {
    return {
      plateNum: "",
      actionDetailList: [],
      datePicker: [dayStart, dayNow],
      columns: [
        {
          title: "车牌号码",
          key: "cph",
          // fixed: "left",
          minWidth: 150,
          align: "center"
        },
        {
          title: "车牌种类",
          key: "cpzlshow",
          minWidth: 100,
          align: "left"
        },
        {
          title: "行为类型",
          key: "xwlxshow",
          minWidth: 100,
          align: "left"
        },
        {
          title: "行为时间",
          key: "rksj",
          minWidth: 150,
          align: "center"
        },
        {
          title: "行为位置",
          key: "xwwz",
          minWidth: 100,
          align: "left"
        }
      ],
      tableData: [],
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: []
    };
  },
  mounted() {
    this.getTableData(this.pageIndex);
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
      $("#actionStatistics").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $(".ivu-page").css("margin-top", "0px !important");
      $("#actionTable").css("height", (window.innerHeight * 450) / 980 + "px");
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
        kssj: kssjValue,
        jssj: jssjValue,
        pageSize: vm.pageSize
      };

      let dataParams = {
        cph: vm.plateNum,
        kssj: kssjValue,
        jssj: jssjValue,
        pageSize: vm.pageSize
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/caraction/" + pageIndex,
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
          if (response.data.code == "10003") {
            localStorage.clear();
            axios.defaults.headers.common["USER_TOKEN"] = "";
            this.getToken();
          }
          let tableList = response.data.result.array;
          tableList.forEach(element => {
            var myGeo = new BMap.Geocoder();
            myGeo.getLocation(
              new BMap.Point(element.bdjd, element.bdwd),
              function(result) {
                if (result) {
                  vm.$set(element, "xwwz", result.address);
                }
              }
            );
          });
          vm.tableData = tableList;
          vm.tableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    }
  }
};
</script>



