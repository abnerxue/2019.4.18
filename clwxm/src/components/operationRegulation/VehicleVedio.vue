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

.showLineTitle {
  height: 40px;
  line-height: 40px;
  background: rgb(0, 132, 243);
  font-size: 14px;
  text-align: left;
  vertical-align: middle;
  padding-left: 15px;
  color: #fff;
  cursor: move;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#vehicleVedioModal {
  z-index: 19891016;
  width: 560px;
  height: 500px;
  top: 135px;
  left: 200px;
  z-index: 19891020;
  background-color: #fff;
  position: absolute;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.ivu-table-wrapper {
  margin: 10px;
}

#vehicleTable {
  min-width: 580px;
}

.isDisplayVehicleVedio {
  display: none;
}

.isShowVehicleVedio {
  display: block;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="actionStatistics"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 25px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">车辆视频</h5>
      <div style="display: flex;min-width: 600px;flex-direction: row;flex-wrap: wrap;align-items: center;justify-content: space-around;position: relative;margin-top: 30px;">
        <div class="searchItem">
          <div class="labelName">车牌号码：</div>
          <Input
            v-model="plateNum"
            placeholder="请输入车牌号"
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
          id="vehicleTable"
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
    <div
      id="vehicleVedioModal"
      :class="{isDisplayVehicleVedio:isDisplayVedio,isShowVehicleVedio:isShowVedio}"
    >
      <div
        class="showLineTitle"
        id="showLineTitle"
      >
        <div>车辆视频</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeVehicleVedioModal"
        >
      </div>

      <video
        :src="src"
        controls="controls"
        id="vehicleVedio"
        width="500"
        height="400"
        autoplay
        style="margin-top:0px;"
      >
      </video>
    </div>
  </div>
</template>
<script>
import dragDown from "../../../static/js/dragDown.js";
import $ from "jquery";

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
  name: "actionStatistics",
  data() {
    return {
      isDisplayVedio: true,
      isShowVedio: false,
      plateNum: "",
      actionDetailList: [],
      datePicker: [dayStart, dayNow],
      columns: [
        {
          title: "车牌号码",
          key: "cph",
          // fixed: "left",
          minWidth: 100,
          align: "center"
        },
        {
          title: "视频编号",
          key: "videoRecordId",
          minWidth: 100,
          align: "center"
        },
        {
          title: "开始时间",
          key: "milliseconds",
          minWidth: 100,
          align: "center"
        },
        {
          title: "视频时长",
          key: "videoTimeLength",
          width: 100,
          align: "left"
        },
        {
          title: "操作",
          key: "action",
          minWidth: 150,
          align: "center",
          // fixed: "right",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "info",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      $("#vehicleVedioModal").fadeOut();
                      this.src = params.row.wjwz;
                      $("#vehicleVedioModal").fadeIn();
                      this.src = "http://172.16.10.29:8080/" + params.row.wjwz;
                      //"http://172.16.10.29:8080/gm/vedio/20190116/00030EA0D74F8376_VD_2019-01-15_170350.mp4_-1.mp4";
                      document.getElementById("vehicleVedio").play();
                    }
                  }
                },
                "播放"
              )
            ]);
          }
        }
      ],
      tableData: [],
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: [],
      src: ""
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

    new dragDown.dragMover("showLineTitle");

    if (window.innerWidth < 900) {
      $("#actionStatistics").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $(".ivu-page").css("margin-top", "0px !important");
      $("#vehicleTable").css("height", (window.innerHeight * 450) / 980 + "px");
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
  },
  methods: {
    closeVehicleVedioModal: function() {
      document.getElementById("vehicleVedio").pause();
      $("#vehicleVedioModal").fadeOut();
      this.isDisplayVedio = true;
      this.isShowVedio = false;
    },
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
        cph: vm.cph,
        kssj: kssjValue,
        jssj: jssjValue,
        pageSize: vm.pageSize
      };

      let dataParams = {
        cph: vm.cph,
        kssj: kssjValue,
        jssj: jssjValue,
        pageSize: vm.pageSize
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/videoinfos/" + pageIndex,
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



