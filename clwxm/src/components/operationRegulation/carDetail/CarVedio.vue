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
  margin-left: 20px;
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
</style>
<style>
.carDetailModal {
  height: 700px !important;
}
</style>

<template>
  <div id="carVedioModal">
    <div>
      <div class="searchItem">
        <!-- <div class="labelName">车牌号码：</div>
        <Input
          v-model="plateNum"
          placeholder="请输入车牌号"
          class="cdzmc"
        /> -->
      </div>
      <div class="searchItem">
        <div class="labelName">统计时间：</div>
        <DatePicker
          type="datetimerange"
          placeholder="选择开始时间和结束时间"
          style="width: 325px;color: #000"
          v-model="datePicker"
        ></DatePicker>
        <Button
          type="primary"
          @click="searchList"
          style="margin-left:30px;"
        >查询</Button>
      </div>
    </div>
    <div>
      <Table
        :columns="columns"
        :data="tableData"
        id="vehicleTable"
        height="200"
      ></Table>
      <Page
        :total="tableDataLength"
        :current="pageIndex"
        @on-change="changePage"
        :page-size="pageSize"
        id="tablePage"
        style="margin-top:0px;"
      ></Page>
    </div>
    <!-- <video
      :src="src"
      controls="controls"
      width="320"
      height="240"
      autoplay
      id="carVedio"
    >
    </video> -->
  </div>
</template>
<script>
import axios from "axios";
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
  props: ["carNum", "cph"],
  data() {
    return {
      src: "",
      plateNum: "",
      actionDetailList: [],
      datePicker: [dayStart, dayNow],
      columns: [
        {
          title: "视频编号",
          key: "videoRecordId",
          width: 150,
          align: "center"
          // fixed: "left"
        },
        {
          title: "开始时间",
          key: "milliseconds",
          width: 100,
          align: "center"
        },
        {
          title: "视频时长",
          key: "videoTimeLength",
          width: 100,
          align: "center"
        },
        {
          title: "操作",
          key: "action",
          align: "center",
          minWidth: 100,
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
                      this.src = "http://172.16.10.29:8080/" + params.row.wjwz;
                      if ($("#carVedioModal").children("video:last-child")) {
                        $("#carVedioModal")
                          .children("video:last-child")
                          .remove();
                      }

                      let video = document.createElement("video");
                      video.setAttribute("width", "300"); //设置视频播放器的宽高
                      video.setAttribute("height", "240");
                      video.setAttribute("src", this.src);
                      // video.setAttribute("position", "absolute");
                      // video.setAttribute("top", "400");
                      video.setAttribute("controls", "controls"); //设置播放按钮
                      document
                        .getElementById("carVedioModal")
                        .appendChild(video); //添加到文档中
                      video.play();
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
      tableDataLength: 5,
      pageSize: 5,
      pageIndex: 1,
      totalTableData: []
    };
  },
  mounted() {
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
  },
  beforeUpdate() {
    this.searchList();
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






