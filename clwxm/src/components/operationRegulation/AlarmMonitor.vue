<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 80px;
  text-align: center;
  display: flex;
  align-items: center;
  margin-left: 20px;
}

.plateNum {
  width: 12.5vw;
  margin-top: 5px;
  border-radius: 4px;
  font-size: 14px;
  height: 38px;
}

.searchItem {
  display: flex;
  align-items: center;
  // margin-left: -185px;
}

.title {
  line-height: 24px;
  width: 80px;
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

#alarmTable {
  min-width: 580px;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="alarmMonitor"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">告警监控</h5>
      <div>
        <div style="display: flex;min-width: 600px;flex-direction: row;align-items: center;justify-content: space-around;position: relative;margin-top: 30px;margin-bottom: 15px;">
          <div class="searchItem">
            <div class="labelName">车牌号码：</div><Input
              v-model="plateNum"
              placeholder="请输入车牌号"
              class="plateNum"
            />
            <div class="labelName">告警类型：</div><Select
              v-model="alarmType"
              clearable
              filterable
              style="width:240px;"
            >
              <Option
                v-for="item in alarmTypeList"
                :value="item.value"
                :key="item.value"
              >{{ item.label }}</Option>
            </Select>
          </div>
          <div class="buttonList">
            <Button
              type="primary"
              @click="searchList"
            >{{monitorControl}}</Button>
          </div>
        </div>
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="tableData"
          id="alarmTable"
          height="500"
        ></Table>
        <!-- <Page  :total="tableDataLength"  :current="pageIndex" @on-change="changePage" :page-size="pageSize" ></Page> -->
      </div>
    </div>
  </div>
</template>
<script>
let timeOut;
function getNowFormatDate() {
  var date = new Date();
  var seperator1 = "-";
  var seperator2 = ":";
  //前十分钟时间
  var minutes = parseInt("10");

  var interTimes = minutes * 60 * 1000;

  var interTimes = parseInt(interTimes);
  date = new Date(Date.parse(date) - interTimes);

  var month = date.getMonth() + 1;
  var strDate = date.getDate();
  var hour = date.getHours();
  var minutes = date.getMinutes();
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  if (hour >= 0 && hour <= 9) {
    hour = "0" + hour;
  }
  if (minutes >= 0 && minutes <= 9) {
    minutes = "0" + minutes;
  }
  var currentdate =
    date.getFullYear() +
    seperator1 +
    month +
    seperator1 +
    strDate +
    " " +
    hour +
    seperator2 +
    minutes +
    seperator2 +
    date.getSeconds();
  return currentdate;
}
export default {
  name: "alarmMonitoring",
  data() {
    return {
      monitorControl: "开始监控",
      plateNum: "",
      alarmType: "",
      columns: [
        {
          title: "车牌号码",
          key: "cph",
          // fixed: "left",
          minWidth: 100,
          align: "center"
        },
        {
          title: "所属企业",
          key: "ssqy",
          minWidth: 100,
          align: "center"
        },

        {
          title: "告警类型",
          key: "gjlxshow",
          minWidth: 100,
          align: "center"
        },
        {
          title: "告警级别",
          key: "gjjbshow",
          minWidth: 100,
          align: "center"
        },
        {
          title: "行驶速度(km/h)",
          key: "dqsd",
          minWidth: 100,
          align: "center"
        },
        {
          title: "行驶时间(h)",
          key: "xssj",
          minWidth: 100,
          align: "center"
        },
        {
          title: "告警时间",
          key: "rksj",
          align: "center",
          minWidth: 100
          // fixed: "right"
        }
      ],
      tableData: [],
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: [],
      alarmTypeList: []
    };
  },
  mounted() {
    // this.getTableData(this.pageIndex);
    this.getAlarmType();
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
      $("#alarmMonitor").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $("#alarmTable").css("height", (window.innerHeight * 600) / 980 + "px");
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
  },
  methods: {
    stopMonitor: function() {
      clearInterval(timeOut);
    },
    getAlarmType: function() {
      let vm = this;
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/codes/gjlx",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          let result = response.data.result;
          vm.alarmTypeList = result.map(function(item) {
            return { label: item.dmsm1, value: item.dmz };
          });
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    changePage(index) {
      this.getTableData(index);
    },
    searchList() {
      if (!localStorage.getItem("time")) {
        let time = getNowFormatDate();
        localStorage.setItem("time", time);
      }
      if (this.monitorControl == "开始监控") {
        this.monitorControl = "停止监控";
        this.getTableData(1);
      } else {
        this.monitorControl = "开始监控";
        this.stopMonitor();
        localStorage.removeItem("time");
      }
    },
    getTableData(pageIndex) {
      let time = localStorage.getItem("time");

      let vm = this;
      let dataJson = {
        cph: vm.plateNum,
        gjlx: vm.alarmType,
        rksj: time
      };
      let dataParams = {
        cph: vm.plateNum,
        gjlx: vm.alarmType,
        rksj: time
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/alarmmonitor",
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
          vm.tableData = response.data.result;
          vm.tableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
      timeOut = setTimeout(this.getTableData, 100000);
    }
  }
};
</script>



