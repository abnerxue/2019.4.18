<style scoped lang="less">
.gress-box-out {
  width: 150px;
  display: inline-block;
  width: 200px;
  height: 10px;
  line-height: 10px;
  border-radius: 10px;
  background: #cfcfcf;
  vertical-align: middle;
  position: relative;
  left: 20px;
  text-align: left;
}
.gress-box-inner {
  display: inline-block;
  height: 10px;
  border-radius: 10px;
  background: #33a464;
  vertical-align: middle;
  text-align: right;
}

.icon-cricle {
  display: inline-block;
  height: 8px;
  width: 8px;
  border-radius: 50%;
  background: #fff;
  border: 1px solid #33a464;
  vertical-align: top;
  cursor: pointer;
}
.e-txt {
  position: absolute;
  font-weight: bold;
}
.win-content {
  width: 600px;
  height: 575px;
}

svg {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 0px;
  height: 0px;
}

.displayCarControl {
  display: none;
}

.carDisplay {
  z-index: 19891020;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.rk_menubox {
  width: 252px;
}
.rk_menuitem {
  line-height: 20px;
  cursor: pointer;
  padding: 0px 5px;
  width: 70px;
  color: #333333;
}
.rk_menuitem:hover {
  background: #f4f4f4;
  font-weight: bold;
  color: #0084f3;
}

.playBackTime {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-top: 10px;
}

.displayShowLineModal {
  display: none;
}

.displayshowTableModal {
  display: none;
}

.showLineModalBlock {
  display: block;
}

.showTableModalBlock {
  display: block;
}

.displaySearchBox {
  display: none;
}

.showSearchBox {
  display: block;
}

.showLineModal {
  z-index: 19891016;
  width: 560px;
  height: 570px;
  top: -3px;
  left: 715px;
  z-index: 19891020;
  background-color: #fff;
  position: absolute;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.searchBox {
  z-index: 19891016;
  width: 1080px;
  height: 700px;
  top: 20px;
  left: 130px;
  background-color: #fff;
  position: absolute;
  cursor: move;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.chargingStation {
  z-index: 19891021;
  width: 600px;
  height: 490px;
  top: 20px;
  left: 130px;
  background-color: #fff;
  position: absolute;
  cursor: move;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.showLineTitle {
  height: 40px;
  line-height: 40px;
  background: rgb(0, 132, 243);
  font-size: 14px;
  color: #fff;
  width: 252px;
  display: flex;
  justify-content: space-around;
}
#showTableModal {
  background: #fff;
  width: 410px;
  height: 430px;
  z-index: 19891020;
  position: absolute;
  left: 340px;
  top: 194px;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

#showBottomTableModal {
  background: #fff;
  width: 705px;
  height: 242px;
  z-index: 19891020;
  position: absolute;
  left: 340px;
  top: 649px;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.title {
  line-height: 24px;
  height: 24px;
  font-size: 16px;
  color: #fff;
  margin-left: 20px;
  border-left: 4px solid #34a0f8;
  position: relative;
  margin-bottom: 30px;
  margin-top: 20px;
  text-align: left;
  padding-left: 10px;
  top: 10px;
  width: 120px;
}

.switchStatus {
  position: absolute;
  z-index: 10;
  left: 50%;
  bottom: 5%;
}

.chargeItem {
  margin-left: 20px;
  min-width: 40px;
  line-height: 60px;
  display: flex;
  align-items: center;
  .chargeItemFont {
    font-size: 16px;
    font-weight: bold;
    color: #999999;
    width: 90px;
  }
  .chargeItemValue {
    font-size: 16px;
    color: #333333;
    font-weight: bold;
    margin-left: 30px;
    width: 270px;
    display: flex;
  }

  .displayShowFormModal {
    display: none;
  }

  .displayBottomShowFormModal {
    display: none;
  }

  .leftItemName {
    color: grey;
    margin-right: 10px;
    margin-left: -210px;
  }

  .leftSide {
    width: 45%;
  }

  .rightItemName {
    color: grey;
    margin-right: 10px;
  }

  .itemDiv {
    display: -webkit-inline-box;
  }

  .displayShowCarDetailModal {
    display: none;
  }

  .showCarDetailModalBlock {
    display: block;
  }
}
</style>
<style>
.realTimeMonitoring .BMap_pop img {
  top: 5px !important;
  left: 270px !important;
}

.imageControl {
  display: flex;
  flex-direction: row;
}
.ivu-table-wrapper {
  margin: 10px;
}

.showModalBlock {
  z-index: 19891020;
  display: block !important;
}

.showBottomModalBlock {
  z-index: 19891020;
  display: block !important;
}

.openInfoItem {
  width: 140px;
  display: flex;
}

.openInfoItem span {
  margin-left: 10px;
}
</style>
<template>
  <div
    style="width: calc(100vw - 330px);height: calc(100vh - 120px);margin-left: 20px; margin-top: 20px;display:flex;"
    class="realTimeMonitoring"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 25px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">实时监控</h5>
      <div
        id="myMap"
        style="width:97%;height:88%;border-radius: 4px;z-index: 19891020;margin-left:20px;"
      ></div>
    </div>
    <div><img
        :src="leftSideSrc"
        @click="showTableLeftTop"
        style="cursor:pointer;position: absolute;left: 340px;top: 365px;z-index: 19891021;height: 45px;width: 15px;"
      /></div>
    <div
      id="showTableModal"
      style="display:none;"
      :class="{displayShowFormModal:isDisplayShowFormModal,showModalBlock:isShowModal}"
    >
      <div style="display:flex;margin-top:20px;">
        <Select
          v-model="selectLabel"
          filterable
          clearable
          style="width:40%;margin-left:40px;"
        >
          <Option
            v-for="(item,index) in selectLabelList"
            :value="item.value"
            :key="index"
          >{{ item.label }}</Option>
        </Select>
        <Input
          v-model="selectValue"
          style="margin-left:20px;width:40%;"
          @on-enter="searchTableList"
        />
      </div>
      <div><Button
          type="primary"
          @click="searchTableList"
          style="margin-top:10px;margin-left:290px;"
        >查询</Button></div>
      <div>
        <Table
          height="300"
          :columns="columns"
          :data="tableData"
          id="table"
        ></Table>
      </div>
    </div>
    <div
      id="showBottomTableModal"
      style="display:none;"
      :class="{displayBottomShowFormModal:isDisplayShowLeftBottomModal,showBottomModalBlock:isShowLeftBottomModal}"
    >
      <Table
        :columns="leftBottomColumns"
        :data="leftBottomTableData"
        id="leftBottomTable"
        style="display:flex;align-item:center;"
        height=224
      ></Table>
    </div>
    <div><img
        :src="leftBottomSideSrc"
        @click="showTableLeftBottom"
        style="cursor:pointer;position: absolute;left: 340px;top: 751px;z-index: 19891021;height: 45px;width: 15px;"
      /></div>
    <div
      class="carDisplay rk_menubox"
      id="rk_menubox"
      ref="rk_menubox"
      style="display:none;"
      :class="{displayCarControl:isDisplayCarControl,carDisplay:displayCarControl}"
    >
      <div style="display:flex;flex-direction: column;margin-top:5px">
        <div class="showLineTitle">
          <div id="carNum">{{cph}}</div>
          <span
            style="color:#3ada32"
            v-if="carStatus== '在线'"
          >{{carStatus}}</span>
          <span
            style="color:#cccccc"
            v-else-if="carStatus== '离线'"
          >{{carStatus}}</span>
          <span
            style="color:#e9e003"
            v-else-if="carStatus== '临停'"
          >{{carStatus}}</span>
          定位
        </div>
        <div>
          <div>
            <div style="display:flex;margin-top:15px;">
              <div>
                <div class="openInfoItem">上报时间<span
                    :title="reportingTime"
                    style="width: 80px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
                  >{{reportingTime}}</span></div>
              </div>
              <div>
                <div class="openInfoItem">当前车速<span>{{carSpeed.carSpeed}}</span></div>
              </div>
            </div>
            <div style="display:flex;">
              <div>
                <div class="openInfoItem">当前告警<span>{{currentWarning}}</span></div>
              </div>
              <div>
                <div class="openInfoItem">停车时长<span
                    :title="parkingDuration"
                    style="width: 37px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
                  >{{parkingDuration}}</span></div>
              </div>
            </div>
            <div style="display:flex;">
              <div>
                <div class="openInfoItem">停车位置<span
                    :title="parkPosition"
                    style="width: 80px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
                  >{{parkPosition}}</span></div>
              </div>
              <div>
                <div class="openInfoItem">驾驶人员 <span>{{currentDriver}}</span></div>
              </div>
            </div>
            <!-- <div style="display:flex;">
             
              <div>
                <div class="openInfoItem">营运状态 <span>{{serviceCondition}}</span></div>
              </div>
            </div>
            <div>
              <div>
                <div class="openInfoItem">所属企业</div>{{belongEnterprise}}
              </div>
            </div> -->
          </div>
        </div>
        <div style="margin:0px;height:1px;border:0px;background:#D6D6D6;margin-top:10px;margin-bottom:10px;"></div>
        <div class="imageControl">
          <div
            style="cursor:pointer;margin-left:30px;"
            @click="displayDetail"
          ><img src="/static/image/icon_detail.png" />
            <div>详情</div>
          </div>
          <div
            style="cursor:pointer;margin-left:30px;"
            @click="displayLineDetail"
          ><img src="/static/image/icon_trail.png" />
            <div>轨迹</div>
          </div>
          <div
            style="cursor:pointer;margin-left:30px;"
            @click="displayVedioDetail"
          ><img src="/static/image/icon_vedio.png" />
            <div>视频</div>
          </div>
          <div
            style="cursor:pointer;margin-left:30px;"
            @click="displayTrailDetail"
          ><img src="/static/image/icon_following.png" />
            <div>跟踪</div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <car-detail
        ref="carDetailModal"
        :cph="cph"
        :carNum="carNum"
        :carStatus="carStatus"
        :carSpeed="carSpeed"
        @changeFatherSpeed="changeFatherSpeed($event)"
        :class="{displayShowCarDetailModal:isDisplayShowCarDeailModal,showCarDetailModalBlock:isShowCarDeailModal}"
      ></car-detail>
      <title-vue
        :cph="cph"
        v-on:displayDetailVue="displayDetail"
        keep-alive
      ></title-vue>
    </div>
  </div>
</template>
<script>
import echarts from "echarts";
import "echarts/map/js/china.js";
import $ from "jquery";
import dragDown from "../../../static/js/dragDown.js";
import axios from "axios";
import carDetail from "./carDetail/CarDetail";
import CarItemDetail from "./carDetail/CarItemDetail";
import CarVedio from "./carDetail/CarVedio";
import DriveLine from "./carDetail/DriveLine";
import VehicleTracking from "./carDetail/VehicleTracking";
import TitleVue from "./carDetail/TitleVue";

let mapDistribution = {
  isPlay: false, // 是否播放
  speed: 8,
  isPause: false,
  index: 0
}; // 播放速度};

let zIndex = 19891020;
let dayStart;
let dayNow;
let chargeDayNow;
let chargeDayStart;
let timeout;
let timeout_current;
var carMk = {};
let carMk_current = {};
let car = {};
let car_begin = {};
let carEnd = {};
let baiduMap;
var pointLine;
var pointLine_current;
let carList = [];
let provincenter;
let speed = 2;

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
  name: "mapDistribution",
  components: {
    carDetail,
    TitleVue
  },
  data() {
    return {
      leftSideSrc: "/static/image/rightSide.png",
      leftBottomSideSrc: "/static/image/rightSide.png",
      leftBottomTableData: [],
      currentDriver: "",
      isDisplayShowCarDeailModal: true,
      parkPosition: "",
      isShowCarDeailModal: false,
      detailStatus: false,
      videoStatus: false,
      isDisplayCarControl: true,
      displayCarControl: false,
      isShowLineModal: false,
      isDisplayShowFormModal: true,
      isDisplayShowLeftBottomModal: true,
      isShowModal: false,
      isShowLeftBottomModal: false,
      selectValue: "",
      parkingDuration: "",
      leftBottomColumns: [
        {
          title: "车牌号码",
          key: "cph",
          align: "center",
          minWidth: 100
        },
        {
          title: "里程数",
          key: "lcs",
          minWidth: 150,
          align: "center"
        },
        {
          title: "平均速度",
          key: "pjsd",
          minWidth: 150,
          align: "center"
        },
        {
          title: "告警状态",
          key: "gjlxshow",
          minWidth: 150,
          align: "center",
          render: (h, params) => {
            return h("div", {}, [
              h(
                "div",
                {
                  style: {
                    color: "red"
                  }
                },
                params.row.gjlxshow
              )
            ]);
          }
        }
      ],
      columns: [
        {
          title: "车牌号码",
          key: "cph",
          align: "center",
          minWidth: 100,
          render: (h, params) => {
            if (params.row.clzt == "1") {
              return h("div", {}, [
                h(
                  "div",
                  {
                    style: {
                      color: "#3ada32",
                      cursor: "pointer"
                    },
                    on: {
                      click: () => {
                        this.cph = params.row.cph;
                        this.searchCar(params.row.cph);
                        this.getLeftBottomData();
                        this.$refs.carDetailModal.closeDetailModal();
                        if($("#carVedioModal").find("video")!=undefined){
                            $("#carVedioModal").find("video").remove();
                        }
                      }
                    }
                  },
                  params.row.cph
                )
              ]);
            } else if (params.row.clzt == "0") {
              return h("div", {}, [
                h(
                  "div",
                  {
                    style: { color: "#d9d100", cursor: "pointer" },
                    on: {
                      click: () => {
                        this.cph = params.row.cph;
                        this.searchCar(params.row.cph);
                        this.getLeftBottomData();
                        this.$refs.carDetailModal.closeDetailModal();
                        if($("#carVedioModal").find("video")!=undefined){
                            $("#carVedioModal").find("video").remove();
                        }
                      }
                    }
                  },
                  params.row.cph
                )
              ]);
            } else {
              return h("div", {}, [
                h(
                  "div",
                  {
                    style: { color: "#999999", cursor: "pointer" },
                    on: {
                      click: () => {
                        this.cph = params.row.cph;
                        this.searchCar(params.row.cph);
                        this.getLeftBottomData();
                        this.$refs.carDetailModal.closeDetailModal();
                         if($("#carVedioModal").find("video")!=undefined){
                            $("#carVedioModal").find("video").remove();
                        }
                      }
                    }
                  },
                  params.row.cph
                )
              ]);
            }
          }
        },
        {
          title: "联系人手机",
          key: "lxdh",
          minWidth: 120,
          align: "center"
        },
        {
          title: "终端ID",
          key: "ccbh",
          minWidth: 150,
          align: "center"
        }
      ],
      selectLabelList: ["车牌号码", "联系人手机"],
      cdzmc: "",
      cdl: "",
      cph: "",
      dlwz: "",
      driveStatus: "",
      carStatus: "离线",
      tableDataLength: 5,
      tableLineDataLength: 5,
      pageSize: 5,
      pageIndex: 1,
      totalTableData: [],
      datePicker: [],
      province: "",
      carNum: 0,
      lineCarNum: 0,
      provinceCarList: [],
      basicTableData: [],
      lockTabelData: [],
      attibuteData: [],
      licenseTypeList: [],
      lastTime: "",
      licenseType: "",
      tableData: [],
      carNumInput: "",
      selectLabel: "车牌号码",
      carSpeed: { carSpeed: "" },
      reportingTime: "",
      currentWarning: "",
      parkingDuration: "",
      parkPosition: "",
      currentDriver: "",
      serviceCondition: "",
      belongEnterprise: ""
    };
  },
  beforeMount() {
    let vm = this;
    if (vm.selectLabelList) {
      vm.selectLabelList = vm.selectLabelList.map(function(item) {
        return { label: item, value: item };
      });
    }
  },
  mounted() {
    let vm = this;
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      this.getToken();
    }
    this.getProvince().then(resolve => {
      vm.initMap(resolve);
    });
    this.getTableData();
    this.getLeftBottomTableData();
    //this.getLeftBottomData();
  },
  beforeUpdate() {
    // this.searchCar(this.cph);
  },
  methods: {
    changeFatherSpeed: function(event) {
      this.carSpeed.carSpeed = event;
    },
    getLeftBottomData: function() {
      let vm = this;
      let dataJson = {
        cph: vm.cph
      };
      let dataParams = {
        cph: vm.cph
      };
      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/nowmonitor",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        data: dataJson,
        params: dataParams,
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          let item = resolve.data.result[0];
          vm.reportingTime = item.rksj;
          if (vm.carState !== "在线") {
            vm.carSpeed.carSpeed = 0 + "km/h";
          } else {
            vm.carSpeed.carSpeed = item.dqsd + "km/h";
          }
          if (item.clzt != "1") {
            vm.parkingDuration = item.bz;
            var myGeo = new BMap.Geocoder();
            myGeo.getLocation(new BMap.Point(item.bdjd, item.bdwd), function(
              result
            ) {
              if (result) {
                vm.parkPosition = result.address;
              }
            });
          }
          vm.currentDriver = item.lxr;
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    displayDetail: function(data) {
      this.$refs.carDetailModal.addCarDetailModal();
      this.$refs.carDetailModal.tabValue = "carItemDetail";
    },
    displayLineDetail: function() {
      this.$refs.carDetailModal.addCarDetailModal();
      this.$refs.carDetailModal.tabValue = "driveLine";
    },
    displayVedioDetail: function() {
      this.$refs.carDetailModal.addCarDetailModal();
      this.$refs.carDetailModal.tabValue = "carVedio";
    },
    displayTrailDetail: function() {
      this.$refs.carDetailModal.addCarDetailModal();
      this.$refs.carDetailModal.tabValue = "vehicleTracking";
    },
    searchTableList: function() {
      let vm = this;
      let dataJson = {};
      let dataParams = {};
      if (this.selectLabel && this.selectLabel == "车牌号码") {
        dataJson = {
          cph: vm.selectValue
        };
        dataParams = {
          cph: vm.selectValue
        };
      } else if (this.selectLabel && this.selectLabel == "联系人手机") {
        dataJson = {
          lxdh: vm.selectValue
        };
        dataParams = {
          lxdh: vm.selectValue
        };
      }

      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/upmonitor",
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
          if (response.data.result.length < 1) {
            vm.$Message.warning("未查询到该车辆！");
          }
          vm.tableData = response.data.result;
          // let baiduMap = this.$store.state.baiduMap;

          // let resultFirst = response.data.result[0];
          // let lon = resultFirst.bdjd;
          // let lat = resultFirst.bdwd;
          // baiduMap.centerAndZoom(new BMap.Point(lon, lat), 15);
          // vm.getCarNumByProvince().then(resolve => {
          //   vm.showCar(baiduMap, resolve, mapDistribution, 1);
          // });
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    showTableLeftBottom: function() {
      if (this.leftBottomSideSrc == "/static/image/rightSide.png") {
        this.leftBottomSideSrc = "/static/image/leftSide.png";
        this.isDisplayShowLeftBottomModal = false;
        this.isShowLeftBottomModal = true;
      } else {
        this.leftBottomSideSrc = "/static/image/rightSide.png";
        this.isDisplayShowLeftBottomModal = true;
        this.isShowLeftBottomModal = false;
      }
    },
    showTableLeftTop: function() {
      if (this.leftSideSrc == "/static/image/rightSide.png") {
        this.leftSideSrc = "/static/image/leftSide.png";
        this.isDisplayShowFormModal = false;
        this.isShowModal = true;
      } else {
        this.leftSideSrc = "/static/image/rightSide.png";
        this.isDisplayShowFormModal = true;
        this.isShowModal = false;
      }
    },
    hideTableLeftTop: function() {
      this.isDisplayShowFormModal = true;
      this.isShowModal = false;
    },
    hideTableLeftBottom: function() {
      this.isDisplayShowLeftBottomModal = true;
      this.isShowLeftBottomModal = false;
    },
    changePage(index) {
      this.getTableData(index);
    },
    addZindex: function(event) {
      $(event.target)
        .parent()
        .css("z-index", zIndex++);
    },
    searchCar: function(carNum) {
      let baiduMap = this.$store.state.baiduMonitorMap;
      baiduMap.clearOverlays();
      let vm = this;
      vm.cph = carNum;
      let dataJson = {
        cph: carNum
      };
      let dataParams = {
        cph: carNum
      };
      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/nowmonitor",
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
          if (response.data.result.length < 1) {
            vm.$Message.warning("未查询到该车辆！");
          }

          let resultFirst = response.data.result[0];
          vm.carNum = resultFirst.clbh;
          let lon = resultFirst.bdjd;
          let lat = resultFirst.bdwd;
          //初始化地图,选取第一个点为起始点

          let point = new BMap.Point(lon, lat);
          baiduMap.centerAndZoom(point, 15);
          if (resultFirst.clzt == "1") {
            vm.carStatus = "在线";
          } else if (resultFirst.clzt == "0") {
            vm.carStatus = "临停";
          } else {
            vm.carStatus = "离线";
          }

          var sContent = vm.$refs.rk_menubox;
          vm.driveStatus = resultFirst.clzt;

          vm.getCarNumByProvince().then(resolve => {
            vm.showCar(baiduMap, resolve, mapDistribution, 1);
            $(".rk_menubox").fadeIn();
            openInfo(sContent, point);
            function openInfo(sContent, e) {
              var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
              baiduMap.openInfoWindow(infoWindow, e); //开启信息窗口
            }
          });
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    playLine: function(params) {
      mapDistribution.isPlay = true;
      let xcbh = params.row.xcbh;
      this.$http({
        method: "post",
        url: "/gm-data/api/drivingloca",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        data: { xcbh: xcbh },
        params: { xcbh: xcbh },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false,
        async: false
      })
        .then(function(response) {
          let result = response.data.result;
          let resultArray = result.map(function(item) {
            return [item.bdjd, item.bdwd];
          });
          result = result.map(function(item) {
            return { lng: item.bdjd, lat: item.bdwd };
          });
          let paths = resultArray.length;
          let pointsLen = result.length;
          if (pointsLen == 0) {
            return;
          }

          let linePoints = [];
          // 创建标注对象并添加到地图
          for (let i = 0; i < pointsLen; i++) {
            linePoints.push(new BMap.Point(result[i].lng, result[i].lat));
          }

          //连接所有点
          baiduMap = mapDistribution.mapTarget;
          pointLine = new BMap.Polyline(linePoints, {
            strokeColor: "blue",
            strokeWeight: 3,
            strokeOpacity: 1
          });

          baiduMap.addOverlay(pointLine);
          //初始化地图,选取第一个点为起始点

          var myIconGreen = new BMap.Icon(
            "/static/image/location_green.png",
            new BMap.Size(25, 35)
          );
          let car_begin = new BMap.Marker(linePoints[0], {
            icon: myIconGreen
          });
          baiduMap.addOverlay(car_begin);

          var myIconRed = new BMap.Icon(
            "/static/image/location_red.png",
            new BMap.Size(25, 35)
          );
          let carEnd = new BMap.Marker(linePoints[linePoints.length - 1], {
            icon: myIconRed
          });
          baiduMap.addOverlay(carEnd);

          baiduMap.centerAndZoom(linePoints[0], 15);
          baiduMap.enableScrollWheelZoom();
          baiduMap.addControl(new BMap.NavigationControl());
          baiduMap.addControl(new BMap.ScaleControl());
          baiduMap.addControl(new BMap.OverviewMapControl({ isOpen: true }));

          var myIcon = new BMap.Icon(
            "/static/image/icon-car.png",
            new BMap.Size(41, 25)
          );
          carMk = new BMap.Marker(resultArray[0], {
            icon: myIcon
          });
          baiduMap.addOverlay(carMk);
          let i = 0;
          function resetMkPoint(i) {
            carMk.setPosition(linePoints[i]);
            baiduMap.centerAndZoom(linePoints[i], 15);
            if (i < paths) {
              timeout = setTimeout(function() {
                // 播放模式才继续走
                if (mapDistribution.isPlay) {
                  i++;
                }
                resetMkPoint(i);
              }, 600 / speed);
            }
          }
          setTimeout(function() {
            resetMkPoint(0);
          }, 500);
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    closeDetailModal: function() {
      $("#showTableModal").fadeOut();
    },
    closeChargingStationModal: function() {
      $("#chargingStation").fadeOut();
    },
    closeLineModal: function() {
      this.datePicker = [];
      this.tableData = [];
      this.tableLineDataLength = 1;
      clearInterval(timeout);
      clearInterval(timeout_current);
      mapDistribution.index = "";
      // let baiduMap = this.$store.state.driveLineMap;
      // if (baiduMap && baiduMap.getOverlays()) {
      //   baiduMap.clearOverlays();
      // }
      // carList.forEach(car => {
      //   baiduMap.addOverlay(car);
      // });
      $("#carDetaileModal").fadeOut();
    },
    searchLine: function(carNum, pageIndex) {
      let vm = this;
      let kssjValue = "";
      let jssjValue = "";
      if (vm.datePicker.length > 0) {
        kssjValue = convertUTCTimeToLocalTime(vm.datePicker[0]);
        jssjValue = convertUTCTimeToLocalTime(vm.datePicker[1]);
      }

      let dataJson = {
        clbh: carNum,
        kssj: kssjValue == "-" ? " " : kssjValue,
        jssj: jssjValue == "=" ? " " : jssj
      };

      let dataParams = {
        clbh: carNum,
        kssj: kssjValue,
        jssj: jssjValue
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/travelsearch/" + pageIndex,
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
          vm.tableLineDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    getTableData: function() {
      this.tableData = [];
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/upmonitor",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          vm.tableData = resolve.data.result;
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    getLeftBottomTableData: function() {
      this.leftBottomTableData = [];
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/downmonitor",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          vm.leftBottomTableData = resolve.data.result;
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    showCar: function(mapObj, carPoints, oper, num) {
      let vm = this;
      // $(".rk_menubox").fadeOut();
      // 创建小汽车
      if (!mapObj && !carPoints) return;
      //mapObj.clearOverlays(); // 清除地图覆盖物

      carPoints.forEach(function(point) {
        var myIcon = (myIcon = new BMap.Icon(
          "/static/image/icon-car.png",
          new BMap.Size(41, 25)
        ));

        if (point.clzt == 2) {
          myIcon = new BMap.Icon(
            "/static/image/car_gray.png",
            new BMap.Size(50, 35)
          );
        } else if (point.clzt == 0) {
          myIcon = new BMap.Icon(
            "/static/image/car_linting.png",
            new BMap.Size(50, 35)
          );
        } else {
          myIcon = new BMap.Icon(
            "/static/image/icon-car.png",
            new BMap.Size(50, 35)
          );
        }

        var pt = new BMap.Point(point.bdjd, point.bdwd);
        var carmaker = new BMap.Marker(pt, {
          icon: myIcon
        });

        carmaker.removeEventListener("click", false); //去除绑定
        carList.push(carmaker);

        var mak2 = {};
        var label = {};
        carmaker.addEventListener("mouseover", function() {
          let pointObj = new BMap.Point(point.bdjd, point.bdwd);
          var textIcon = new BMap.Icon(
            "/static/image/frame_car.png",
            new BMap.Size(100, 40),
            {
              anchor: new BMap.Size(50, 50),
              imageSize: new BMap.Size(100, 40)
            }
          );
          mak2 = new BMap.Marker(pointObj, {
            icon: textIcon
          });
          mapObj.addOverlay(mak2);
          //添加文字覆盖物
          var opts = {
            position: pointObj, // 指定文本标注所在的地理位置
            offset: new BMap.Size(-32, -45) //设置文本偏移量
          };
          label = new BMap.Label(point.cph, opts); // 创建文本标注对象
          label.setStyle({
            color: "#ffffff",
            fontSize: "12px",
            height: "20px",
            border: "",
            lineHeight: "20px",
            fontFamily: "微软雅黑",
            backgroundColor: "#0084F3"
          });
          mapObj.addOverlay(label);
        });

        carmaker.addEventListener("mouseout", function() {
          mapObj.removeOverlay(mak2);
          mapObj.removeOverlay(label);
        });
        var sContent = vm.$refs.rk_menubox;

        let baiduMap = vm.$store.state.baiduMonitorMap;

        carmaker.addEventListener("click", function(e) {
          vm.$refs.carDetailModal.closeDetailModal();
          if (
            timeout_current &&
            timeout_current != null &&
            timeout_current != ""
          ) {
            clearInterval(timeout_current);
            baiduMap.removeOverlay(car_begin);
            baiduMap.removeOverlay(carEnd);
            baiduMap.removeOverlay(pointLine_current);
            baiduMap.removeOverlay(carMk_current);
          }
          if (timeout != null) {
            clearInterval(timeout);
            baiduMap.removeOverlay(pointLine);
            baiduMap.removeOverlay(carMk);
          }
          vm.carNum = point.clbh;
          vm.cph = point.cph;
          if (point.clzt == "1") {
            vm.carStatus = "在线";
          } else if (point.clzt == "0") {
            vm.carStatus = "临停";
          } else {
            vm.carStatus = "离线";
          }

          vm.driveStatus = point.clzt;
          vm.getLeftBottomData();
          $(".rk_menubox").fadeIn();
          openInfo(sContent, e);
        });

        function openInfo(sContent, e) {
          var p = e.target;
          var pointBmap = new BMap.Point(
            p.getPosition().lng,
            p.getPosition().lat
          );
          var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
          mapObj.openInfoWindow(infoWindow, pointBmap); //开启信息窗口
        }

        $(carmaker).click();

        mapObj.addOverlay(carmaker);

        if (oper == "setCenter") {
          // 以当前坐标为中心点 且放大17倍
          mapObj.centerAndZoom(new BMap.Point(point[0], point[1]), 17);
        }
      });
    },
    getProvince: function() {
      let vm = this;
      return this.$http({
        method: "post",
        url: "/gm-data/api/interface/totalprocar",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          if (resolve.data.code == "10003") {
            localStorage.clear();
            axios.defaults.headers.common["USER_TOKEN"] = "";
            this.getToken();
          }
          vm.provinceCarList = resolve.data.result;
          return Promise.resolve(vm.provinceCarList);
        },
        reject => {
          console.log("请求失败", reject);
          return Promise.reject(reject);
        }
      );
    },
    getCarNumByProvince: function(city) {
      let dataJson = { sssfshow: city };
      let dataParams = { sssfshow: city };
      let result = [];
      return this.$http({
        method: "post",
        url: "/gm-data/api/interface/procarlocation",
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
          result = response.data.result;
          return Promise.resolve(result);
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    showCharge: function(mapObj, carPoints) {
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/charge",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          if (resolve.data.code == "10003") {
            localStorage.clear();
            axios.defaults.headers.common["USER_TOKEN"] = "";
            this.getToken();
          }
          let chargeList = resolve.data.result;
          chargeList.forEach(element => {
            var myGeo = new BMap.Geocoder();
            myGeo.getLocation(
              new BMap.Point(element.bdjd, element.bdwd),
              function(result) {
                if (result) {
                  vm.$set(element, "dlwz", result.address);
                }
              }
            );
          });
          vm.chargeDetailList = chargeList;
          let chargePoints = this.chargeDetailList;
          this.chargeDetailList.forEach(function(chargeDetail) {
            let chargeIcon = new BMap.Icon(
              "/static/image/chargeImg.png",
              new BMap.Size(41, 25)
            );
            var pt = new BMap.Point(chargeDetail.bdjd, chargeDetail.bdwd);
            var chargeMaker = new BMap.Marker(pt, {
              icon: chargeIcon
            });
            mapObj.addOverlay(chargeMaker);
            chargeMaker.addEventListener("click", function(e) {
              vm.cdzmc = chargeDetail.cdzmc;
              vm.cdl = chargeDetail.zcdl;
              vm.dlwz = chargeDetail.dlwz;
              if (
                chargeDetail.zzcd &&
                chargeDetail.zzcd !== null &&
                chargeDetail.zzcd !== ""
              ) {
                vm.cphs = chargeDetail.zzcd.replace(/,/g, "   ");
              } else {
                vm.cphs = "";
              }
            });
          });
          return Promise.resolve(vm.chargeDetailList);
        },
        reject => {
          console.log("请求失败", reject);
          return Promise.reject(reject);
        }
      );
    },
    initMap: function(resolve) {
      let vm = this;
      let baiduMap = new BMap.Map("myMap");
      this.$store.state.baiduMonitorMap = baiduMap;
      baiduMap.enableScrollWheelZoom();
      vm.getCarNumByProvince("").then(resolve => {
        if (resolve && resolve.length > 0) {
          baiduMap.centerAndZoom(
            new BMap.Point(resolve[0].bdjd, resolve[0].bdwd),
            11
          );
          vm.showCar(baiduMap, resolve, 0);
          vm.showCharge(baiduMap, resolve);
        } else {
          baiduMap.centerAndZoom("江苏", 11);
          vm.showCharge(baiduMap, resolve);
        }
      });
    }
  }
};
</script>


