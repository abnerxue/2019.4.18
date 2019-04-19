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
  width: 100px;
  position: absolute;
  /* background: #fff; */
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

.displayShowCarModal {
  display: none;
}

.showLineModalBlock {
  display: block;
}

.showCarModalBlock {
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
  text-align: left;
  vertical-align: middle;
  padding-left: 15px;
  color: #fff;
  cursor: move;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.showCarModal {
  background: #fff;
  width: 450px;
  height: 550px;
  z-index: 19891020;
  position: absolute;
  right: 15px;
  top: 230px;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.title {
  line-height: 24px;
  height: 24px;
  font-size: 16px;
  color: #000;
  margin-left: 20px;
  border-left: 4px solid #34a0f8;
  position: relative;
  margin-bottom: 20px;
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
}
</style>
<style>
.ivu-table-wrapper {
  margin: 10px;
}
</style>
<template>
  <div
    id="mapAndCharts"
    style="background: #282c35;height:calc(100vh - 75px);width: calc(100vw - 290px);position: absolute;top: 75px;"
  >
    <div style="width: calc(100vw - 330px);height: calc(100vh - 120px);margin-left: 20px; margin-top: 20px;display:flex;">
      <div
        id="mapDistributionDiv"
        style="min-height:750px;min-width:300px;"
      >
        <div>
          <i-switch
            v-model="switchStatus"
            @on-change="change"
            class="switchStatus"
          />
        </div>
        <div
          id="mapDistribution"
          style="width: calc(75vw - 320px);height: calc(100vh - 110px);background:  #18191D !important;min-height:750px;"
        ></div>
      </div>
      <div style="width:25vw;margin-left:30px;">
        <charts></charts>
      </div>
    </div>
    <div
      class="carDisplay rk_menubox"
      id="rk_menubox"
      ref="rk_menubox"
      style="display:none;"
      :class="{displayCarControl:isDisplayCarControl,carDisplay:displayCarControl}"
    >
      <div>
        <img src="/static/image/image.png">
        <div id="carNum">{{cph}}</div>
      </div>
      <div style="display:flex;margin-left: 5px;flex-direction: column;justify-content:center;">
        <div
          class="rk_menuitem"
          @click="showCarDetail(carNum)"
        >车辆详情</div>
        <div
          class="rk_menuitem"
          @click="showLineModal(carNum)"
        >轨迹展示</div>
      </div>
    </div>

    <div
      class="showCarModal"
      id="showCarModal"
      :class="{displayShowCarModal:isDisplayShowCarModal,showCarModalBlock:isShowCarModal}"
    >
      <div
        class="showLineTitle"
        id="showCarTitle"
        @click="addZindex($event)"
      >
        <div>车辆详情</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeDetailModal"
        >
      </div>
      <div>
        <div>
          <h5 class="title">基本信息</h5>
        </div>
        <Table
          :columns="basicTableColumns"
          :data="basicTableData"
          width="450px"
        ></Table>
      </div>
      <div>
        <div>
          <h5 class="title">锁定设备信息</h5>
        </div>
        <Table
          :columns="lockTabelColumns"
          :data="lockTabelData"
          width="450px"
        ></Table>
      </div>
      <div>
        <div>
          <h5 class="title">属性信息</h5>
        </div>
        <Table
          :columns="attibuteColumns"
          :data="attibuteData"
          width="450px"
        ></Table>
      </div>
    </div>

    <div
      class="showLineModal"
      id="showLineModal"
      :class="{displayShowLineModal:isDisplayShowLineModal,showLineModalBlock:isShowLineModal}"
    >
      <div
        class="showLineTitle"
        id="showLineTitle"
        @click="addZindex($event)"
      >
        <div>轨迹展示</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeLineModal"
        >
      </div>
      <div class="playBackTime">
        <div style="margin-left: -70px;color: black;">回放时间：</div>
        <div style="margin-left:-150px;">
          <DatePicker
            type="datetimerange"
            v-model="datePicker"
            format="yyyy-MM-dd HH:mm"
            placeholder="Select date and time(Excluding seconds)"
            style="width: 300px"
          ></DatePicker>
          <Button
            type="primary"
            style="margin-left: 25px;"
            @click="searchLine(carNum,1)"
          >查询</Button>
        </div>
      </div>
      <div>
        <div style="margin-top: 10px;margin-left: 350px;">
          <div style="position:absolute;left:16px;top:108px">
            <span class="s-label">播放速度：</span>
            <span
              class="gress-box-out"
              @click="changeSpeed"
            >
              <span
                class="gress-box-inner"
                style="width: 20%;"
              >
                <i class="icon-cricle"></i>
              </span>
              <em class="e-txt">2X</em>
            </span>
          </div>
          <Button
            style="margin-left: 10px;"
            ref="showCurrentLineButton"
            @click.native="showCurrentLine(carNum,driveStatus)"
          >当前轨迹</Button>
        </div>
      </div>
      <div style="font-size: 14px;font-weight: bold;color: #4c4c4c;margin-top: 50px;margin-left: 20px;">查询结果</div>
      <div style="width:540px;margin-left: 15px;margin-top: 20px;">
        <Table
          :columns="columns"
          :data="tableData"
        ></Table>
        <Page
          :total="tableLineDataLength"
          :current="pageIndex"
          @on-change="changePage($event,carNum)"
          :page-size="pageSize"
        ></Page>
      </div>
    </div>

    <div
      class="searchBox"
      id="searchBox"
      :class="{displaySearchBox:isDisplayShowSearchBox,showSearchBox:isShowSearchBox}"
    >
      <div
        class="showLineTitle"
        id="showMapTitle"
        @click="addZindex($event)"
      >
        <div>{{province}}全省地图</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeMapModal"
        >
      </div>
      <div style="display: flex;align-items: center;color: black;padding:10px;">
        <div style="margin-left:20px;min-width: 40px;">车牌:</div>
        <Input
          id="no"
          v-model="carNumInput"
          style="width:240px;margin-left:10px;margin-right:50px;"
          @on-enter="searchCar"
        />
        <Button
          type="primary"
          @click="searchCar(searchCity)"
        >查询</Button>
      </div>
      <div
        id="myMap"
        style="width:1080px;height:600px;border-radius: 4px;z-index: 19891020"
      ></div>
    </div>

    <div
      class="chargingStation"
      id="chargingStation"
      style="display:none;"
    >
      <div
        class="showLineTitle"
        id="showChargingTitle"
        @click="addZindex($event)"
      >
        <div>基本信息</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeChargingStationModal"
        />
      </div>
      <div style="display: flex;flex-direction:column;align-items: center;color: black;padding:10px;">
        <div class="chargeItem">
          <div class="chargeItemFont">充电站:</div>
          <div class="chargeItemValue">{{cdzmc}}</div>
        </div>
        <div class="chargeItem">
          <div class="chargeItemFont">位置:</div>
          <div class="chargeItemValue">{{dlwz}}</div>
        </div>
        <div class="chargeItem">
          <div class="chargeItemFont">总充电量:</div>
          <div class="chargeItemValue">{{cdl}}</div>
        </div>
        <div class="chargeItem">
          <div class="chargeItemFont">正在充电:</div>
          <div class="chargeItemValue">{{cphs}}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import echarts from "echarts";
import "echarts/map/js/china.js";
import $ from "jquery";
import charts from "./Charts";
import dragDown from "../../static/js/dragDown.js";
import axios from "axios";

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
    return "";
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
  data() {
    return {
      searchCity: "",
      cdzmc: "",
      cdl: "",
      cphs: "",
      dlwz: "",
      chargeDetailList: [],
      chargeDatePicker: [chargeDayStart, chargeDayNow],
      switchStatus: false,
      driveStatus: "",
      cph: "",
      tableDataLength: 10,
      tableLineDataLength: 5,
      pageSize: 5,
      pageIndex: 1,
      totalTableData: [],
      datePicker: [],
      province: "",
      isDisplayShowSearchBox: true,
      isShowSearchBox: false,
      carNum: 0,
      lineCarNum: 0,
      provinceCarList: [],
      isDisplayCarControl: true,
      isShowLineModal: false,
      displayCarControl: false,
      isDisplayShowLineModal: true,
      isDisplayShowCarModal: true,
      isShowLineModal: false,
      isShowCarModal: false,
      basicTableData: [],
      lockTabelData: [],
      attibuteData: [],
      licenseTypeList: [],
      lastTime: "",
      licenseType: "",
      basicTableColumns: [
        {
          title: "车牌号码",
          key: "cph"
        },
        {
          title: "车牌种类",
          key: "cllxshow"
        },
        {
          title: "联系人",
          key: "lxr"
        },
        {
          title: "联系电话",
          key: "lxdh",
          width: 120
        }
      ],
      lockTabelColumns: [
        {
          title: "设备号",
          key: "sbbh"
        },
        {
          title: "设备类型",
          key: "sblxshow"
        },
        {
          title: "设备描述",
          key: "sbmc"
        }
      ],
      attibuteColumns: [
        {
          title: "出现区域",
          key: "sssfshow"
        },
        {
          title: "所属集团",
          key: "cdmc"
        }
      ],
      columns: [
        {
          title: "时间",
          key: "travletime",
          width: 160,
          render: (h, params) => {
            return h("div", {}, [
              h(
                "div",
                {
                  style: {
                    marginRight: "5px"
                  }
                },
                params.row.kssj
              ),
              h(
                "div",
                {
                  style: {
                    marginRight: "5px"
                  }
                },
                params.row.jssj
              )
            ]);
          }
        },
        {
          title: "里程",
          key: "lcs",
          width: 70
        },
        {
          title: "状态",
          key: "xsztshow",
          width: 100
        },
        {
          title: "播放操作",
          key: "action",
          width: 180,
          align: "center",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "success",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      let curIndex = params.index;
                      if (
                        (timeout_current &&
                          timeout_current != null &&
                          timeout_current != "") ||
                        timeout != null
                      ) {
                        let baiduMap = mapDistribution.mapTarget;
                        clearInterval(timeout_current);
                        if (curIndex != mapDistribution.index) {
                          clearInterval(timeout);
                        }

                        baiduMap.removeOverlay(car_begin);
                        baiduMap.removeOverlay(carEnd);
                        baiduMap.removeOverlay(pointLine_current);
                        baiduMap.removeOverlay(carMk_current);
                      }
                      if (
                        mapDistribution.isPlay &&
                        curIndex == mapDistribution.index
                      ) {
                        this.$Message.error("轨迹正在播放或已播放，请点击回放");
                        return;
                      } else if (
                        mapDistribution.isPlay &&
                        curIndex != mapDistribution.index
                      ) {
                        clearInterval(timeout);
                      }

                      if (
                        curIndex == mapDistribution.index &&
                        mapDistribution.isPlay
                      ) {
                      } else if (
                        curIndex == mapDistribution.index &&
                        mapDistribution.isPlay == false
                      ) {
                        //mapDistribution.isPlay = true;
                      } else {
                        mapDistribution.index = curIndex;
                        let baiduMap = mapDistribution.mapTarget;
                        baiduMap.clearOverlays();
                        this.playLine(params);
                      }
                      mapDistribution.isPlay = true;
                    }
                  }
                },
                "播放"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "warning",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      let curIndex = params.index;
                      if (curIndex != mapDistribution.index) {
                        this.$Message.warning("请先播放");
                        return;
                      }
                      mapDistribution.isPlay = false;
                    }
                  }
                },
                "暂停"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      let curIndex = params.index;
                      if (curIndex == mapDistribution.index) {
                        clearInterval(timeout);
                        let baiduMap = mapDistribution.mapTarget;
                        baiduMap.clearOverlays();
                        this.playLine(params);
                        mapDistribution.isPlay = true;
                      } else {
                        this.$Message.warning("请先播放");
                      }
                    }
                  }
                },
                "回放"
              )
            ]);
          }
        }
      ],
      tableData: [],
      carNumInput: ""
    };
  },
  components: {
    charts
  },
  beforeMount() {
    this.getLicenseType();
  },
  mounted() {
    let vm = this;
    new dragDown.dragMover("showCarTitle");
    new dragDown.dragMover("showLineTitle");
    new dragDown.dragMover("showMapTitle");
    new dragDown.dragMover("showChargingTitle");

    if (!localStorage.getItem("token")) {
      this.getToken();
    }
    $(".searchBox").css("height", window.innerHeight * 0.7);
    $(".searchBox").css("width", window.innerWidth * 0.6);
    $("#myMap").css("height", window.innerHeight * 0.6);
    $("#myMap").css("width", window.innerWidth * 0.6);
    // if (window.innerHeight < 800) {
    //   $(".switchStatus").css("bottom", ((-0.06 * 800) / 980) * 800);
    // } else {
    //   $(".switchStatus").css(
    //     "bottom",
    //     ((0.08 * window.innerHeight) / 980) * window.innerHeight
    //   );
    // }
    // $(".switchStatus").css(
    //   "left",
    //   ((0.53 * window.innerWidth) / 1920) * window.innerWidth
    // );

    window.onresize = function() {
      $(".searchBox").css("height", window.innerHeight * 0.7);
      $(".searchBox").css("width", window.innerWidth * 0.6);
      $("#myMap").css("height", window.innerHeight * 0.6);
      $("#myMap").css("width", window.innerWidth * 0.6);
      // $(".switchStatus").css(
      //   "left",
      //   ((0.53 * window.innerWidth) / 1920) * window.innerWidth
      // );
      // $(".switchStatus").css(
      //   "bottom",
      //   ((0.08 * window.innerHeight) / 980) * window.innerHeight
      // );
      //location.reload();
      $(body).css({ height: window.innerHeight, width: window.innerWidth });
    };

    this.getProvince().then(resolve => {
      vm.initMap(resolve);
    });
  },
  methods: {
    searchChargeTable: function() {
      console.log("充电桩");
    },
    change(status) {
      if (!localStorage.getItem("token")) {
        localStorage.clear();
        axios.defaults.headers.common["USER_TOKEN"] = "";
        this.getToken();
      }
      let vm = this;
      if (status === false) {
        let provinceList = [
          "北京",
          "天津",
          "上海",
          "重庆",
          "河北",
          "山西",
          "辽宁",
          "吉林",
          "黑龙江",
          "江苏",
          "浙江",
          "安徽",
          "福建",
          "江西",
          "山东",
          "河南",
          "湖北",
          "湖南",
          "广东",
          "海南",
          "四川",
          "贵州",
          "云南",
          "陕西",
          "甘肃",
          "青海",
          "台湾",
          "内蒙古",
          "广西",
          "西藏",
          "宁夏",
          "新疆",
          "香港",
          "澳门"
        ];
        status = true;
        this.getProvince().then(resolve => {
          var optionNew = mapDistribution.getOption();
          optionNew.series[1].data = [];
          optionNew.series[2].data = [];
          optionNew.visualMap[0].show = true;
          mapDistribution.setOption(optionNew);
          let province = resolve !== "" ? resolve : [];
          var seriesData1 = [];
          if (resolve.length > 0) {
            for (var i = 0; i < provinceList.length; i++) {
              let obj = {};
              if (
                province.filter(
                  item => item.sssfshow.indexOf(provinceList[i]) !== -1
                ) != ""
              ) {
                obj.name = provinceList[i];
                obj.value = province.filter(
                  item => item.sssfshow.indexOf(provinceList[i]) !== -1
                )[0].countnumber;
                seriesData1.push(obj);
              } else {
                obj.name = provinceList[i];
                obj.value = "";
                seriesData1.push(obj);
              }
            }
          }
          optionNew.series[0].data = seriesData1;
          mapDistribution.setOption(optionNew);
        });
      } else {
        status = false;
        var optionNew = mapDistribution.getOption();
        optionNew.visualMap[0].show = false;
        let provinces = [
          "北京",
          "天津",
          "上海",
          "重庆",
          "河北",
          "山西",
          "辽宁",
          "吉林",
          "黑龙江",
          "江苏",
          "浙江",
          "安徽",
          "福建",
          "江西",
          "山东",
          "河南",
          "湖北",
          "湖南",
          "广东",
          "海南",
          "四川",
          "贵州",
          "云南",
          "陕西",
          "甘肃",
          "青海",
          "台湾",
          "内蒙古",
          "广西",
          "西藏",
          "宁夏",
          "新疆",
          "香港",
          "澳门"
        ];

        let provinceArray = [];
        for (let index = 0; index < provinces.length; index++) {
          let element = provinces[index];
          let seriesDataTemp = {};
          seriesDataTemp.name = element;
          seriesDataTemp.value = "";
          provinceArray.push(seriesDataTemp);
        }

        var resPupple = [];
        var resYellow = [];
        vm.getCarNumByProvince("").then(resolve => {
          for (var i = 0; i < resolve.length; i++) {
            let valueArray = [resolve[i].bdjd, resolve[i].bdwd];
            if (valueArray) {
              if (resolve[i].xszt == 1) {
                let valueArray = [resolve[i].bdjd, resolve[i].bdwd];
                resPupple.push({
                  value: valueArray.concat(resolve[i].cph)
                });
              } else {
                let valueArray = [resolve[i].bdjd, resolve[i].bdwd];
                resYellow.push({
                  value: valueArray.concat(resolve[i].cph)
                });
              }
            }
          }
          optionNew.series[1].data = resPupple;
          optionNew.series[2].data = resYellow;
          mapDistribution.setOption(optionNew);
          optionNew.series[0].data = provinceArray;
          mapDistribution.setOption(optionNew);
        });
      }
    },
    addZindex: function(event) {
      $(event.target)
        .parent()
        .css("z-index", zIndex++);
    },
    changeSpeed: function(event) {
      var left = event.offsetX;
      if (left <= 25) {
        $(".gress-box-inner").css("width", "12.5%");
        $(".gress-box-out .e-txt").text("2X");
        speed = 2;
      } else if (left <= 50) {
        $(".gress-box-inner").css("width", "25%");
        $(".gress-box-out .e-txt").text("4X");
        speed = 4;
      } else if (left <= 100) {
        $(".gress-box-inner").css("width", "50%");
        $(".gress-box-out .e-txt").text("8X");
        speed = 8;
      } else if (left <= 200) {
        $(".gress-box-inner").css("width", "100%");
        $(".gress-box-out .e-txt").text("16X");
        speed = 16;
      }
    },
    getLicenseType: function() {
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/interface/codes/cpzl",
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
          vm.licenseTypeList = result.map(function(item) {
            return { label: item.dmsm1, value: item.dmz + "" };
          });
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    changePage(index, carNum) {
      this.searchLine(carNum, index);
    },
    searchCar: function(city) {
      let baiduMap = mapDistribution.mapTarget;
      baiduMap.centerAndZoom(city, 11);
      baiduMap.clearOverlays();
      let vm = this;
      let dataJson = {
        cph: vm.carNumInput,
        cpzl: vm.licenseType == "" ? "" : vm.licenseType
      };
      let dataParams = {
        cph: vm.carNumInput,
        cpzl: vm.licenseType == "" ? "" : vm.licenseType
      };
      this.$http({
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
          if (response.data.result.length < 1) {
            vm.$Message.warning("未查询到该车辆！");
          }
          let resultFirst = response.data.result[0];
          let lon = resultFirst.bdjd;
          let lat = resultFirst.bdwd;
          let baiduMap = mapDistribution.mapTarget;

          //初始化地图,选取第一个点为起始点

          baiduMap.centerAndZoom(new BMap.Point(lon, lat), 15);
          vm.getCarNumByProvince().then(resolve => {
            vm.showCar(baiduMap, resolve, mapDistribution, 1);
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
                console.log(mapDistribution.isPlay, "0000000000000");
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
    closeMapModal: function() {
      $(".searchBox").fadeOut();
      this.tableLineDataLength = 1;
      this.isDisplayShowSearchBox = true;
      this.isShowSearchBox = false;
      this.closeDetailModal();
      this.closeLineModal();
    },
    closeDetailModal: function() {
      $("#showCarModal").fadeOut();
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
      let baiduMap = mapDistribution.mapTarget;
      if (baiduMap && baiduMap.getOverlays()) {
        baiduMap.clearOverlays();
      }
      carList.forEach(car => {
        baiduMap.addOverlay(car);
      });
      $("#showLineModal").fadeOut();
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
        kssj: kssjValue,
        jssj: jssjValue
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
    getBasicTableData: function(carNum) {
      this.basicTableData = [];
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/carinfo/" + carNum,
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          vm.basicTableData.push(resolve.data.result);
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    getLockTabelData: function(carNum) {
      this.lockTabelData = [];
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/carequip/" + carNum,
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          vm.lockTabelData.push(resolve.data.result);
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    getAttibuteData: function(carNum) {
      this.attibuteData = [];
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/interface/belongfleet/" + carNum,
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          vm.attibuteData = resolve.data.result;
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    showCurrentLine: function(carNum, driveStatus) {
      let baiduMap = mapDistribution.mapTarget;
      baiduMap.clearOverlays();
      let vm = this;

      if (driveStatus == 2) {
        this.$Message.error("当前车辆已停止行驶");
        return;
      }
      vm.$refs.showCurrentLineButton.disabled = true;
      setTimeout(function() {
        vm.$refs.showCurrentLineButton.disabled = false;
      }, 30000);
      if (timeout_current && timeout_current != null && timeout_current != "") {
        clearInterval(timeout_current);
        let baiduMap = mapDistribution.mapTarget;
        baiduMap.removeOverlay(car_begin);
        baiduMap.removeOverlay(carEnd);
        baiduMap.removeOverlay(pointLine_current);
        baiduMap.removeOverlay(carMk_current);
      }
      if (timeout != null) {
        clearInterval(timeout);
        let baiduMap = mapDistribution.mapTarget;
        baiduMap.clearOverlays();
        carList.forEach(car => {
          baiduMap.addOverlay(car);
        });
      }
      mapDistribution.isPlay = true;

      let points = [];
      this.$http({
        method: "post",
        url: "/gm-data/api/interface/nowloca/",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        data: { clbh: carNum, rksj: vm.lastTime, xszt: 1 },
        params: { clbh: carNum, rksj: vm.lastTime, xszt: 1 },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false,
        async: false
      }).then(
        resolve => {
          let results = resolve.data.result;
          vm.lastTime = results[0].rksj;
          points = results.map(function(item) {
            return { lng: item.bdjd, lat: item.bdwd };
          });

          let resultArray = results.map(function(item) {
            return [item.bdjd, item.bdwd];
          });
          let pointsLen = resultArray.length;
          if (pointsLen == 0) {
            return;
          }

          let linePoints = [];
          // 创建标注对象并添加到地图
          for (let i = 0; i < pointsLen; i++) {
            linePoints.push(new BMap.Point(points[i].lng, points[i].lat));
          }
          //连接所有点
          let baiduMap = mapDistribution.mapTarget;

          pointLine_current = new BMap.Polyline(linePoints, {
            strokeColor: "blue",
            strokeWeight: 3,
            strokeOpacity: 1
          });
          baiduMap.addOverlay(pointLine_current);

          //初始化地图,选取第一个点为起始点

          baiduMap.centerAndZoom(linePoints[0], 15);
          baiduMap.enableScrollWheelZoom();
          baiduMap.addControl(new BMap.NavigationControl());
          baiduMap.addControl(new BMap.ScaleControl());
          baiduMap.addControl(new BMap.OverviewMapControl({ isOpen: true }));

          //显示小车子
          let label = new BMap.Label("", { offset: new BMap.Size(-20, -20) });
          var myIconGreen = new BMap.Icon(
            "/static/image/location_green.png",
            new BMap.Size(25, 35)
          );
          car_begin = new BMap.Marker(linePoints[0], {
            icon: myIconGreen
          });
          baiduMap.addOverlay(car_begin);

          var myIconRed = new BMap.Icon(
            "/static/image/location_red.png",
            new BMap.Size(25, 35)
          );
          carEnd = new BMap.Marker(linePoints[linePoints.length - 1], {
            icon: myIconRed
          });
          baiduMap.addOverlay(carEnd);
          var myIcon = new BMap.Icon(
            "/static/image/icon-car.png",
            new BMap.Size(41, 25)
          );
          carMk_current = new BMap.Marker(resultArray[0], {
            icon: myIcon
          });
          baiduMap.addOverlay(carMk_current);
          let i = 0;
          function resetMkPoint(i) {
            carMk_current.setPosition(linePoints[i]);
            baiduMap.centerAndZoom(linePoints[i], 15);
            if (i < pointsLen) {
              timeout_current = setTimeout(function() {
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
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    },
    showLineModal: function(lineCarNum) {
      $(".showLineModal").fadeOut();
      this.isDisplayShowLineModal = false;
      this.isShowLineModal = true;
      $(".showLineModal").fadeIn();
      this.searchLine(this.carNum, 1);
      $(".showLineModal").css("z-index", zIndex);
    },
    showCarDetail: function(carNum) {
      $(".showCarModal").fadeOut();
      this.getBasicTableData(carNum);
      this.getLockTabelData(carNum);
      this.getAttibuteData(carNum);
      this.isDisplayShowCarModal = false;
      this.isShowCarModal = true;
      $(".showCarModal").fadeIn();
      $(".showCarModal").css("z-index", zIndex);
    },
    showCharge: function(mapObj, carPoints, mapDistribution, oper) {
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
              $("#chargingStation").fadeIn();
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
    showCar: function(mapObj, carPoints, mapDistribution, oper, num) {
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
        } else if (point.clzt == 1) {
          myIcon = new BMap.Icon(
            "/static/image/icon-car.png",
            new BMap.Size(50, 35)
          );
        } else {
          myIcon = new BMap.Icon(
            "/static/image/car_linting.png",
            new BMap.Size(50, 35)
          );
        }

        var pt = new BMap.Point(point.bdjd, point.bdwd);
        var carmaker = new BMap.Marker(pt, {
          icon: myIcon
        });
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
        if (num === "1") {
          carmaker.click();
        }
        carmaker.addEventListener("click", function(e) {
          let baiduMap = mapDistribution.mapTarget;
          if (
            timeout_current &&
            timeout_current != null &&
            timeout_current != ""
          ) {
            clearInterval(timeout_current);
            let baiduMap = mapDistribution.mapTarget;
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
          vm.driveStatus = point.xszt;
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
        mapObj.addOverlay(carmaker);
        mapDistribution.mapTarget = mapObj;

        if (oper == "setCenter") {
          // 以当前坐标为中心点 且放大17倍
          mapDistribution.mapTarget.centerAndZoom(
            new BMap.Point(point[0], point[1]),
            17
          );
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
    initMap: function(resolve) {
      mapDistribution = {};
      let vm = this;
      mapDistribution = echarts.init(
        document.getElementById("mapDistribution")
      );
      var series = [];

      let provinceList = [
        "北京",
        "天津",
        "上海",
        "重庆",
        "河北",
        "山西",
        "辽宁",
        "吉林",
        "黑龙江",
        "江苏",
        "浙江",
        "安徽",
        "福建",
        "江西",
        "山东",
        "河南",
        "湖北",
        "湖南",
        "广东",
        "海南",
        "四川",
        "贵州",
        "云南",
        "陕西",
        "甘肃",
        "青海",
        "台湾",
        "内蒙古",
        "广西",
        "西藏",
        "宁夏",
        "新疆",
        "香港",
        "澳门"
      ];
      let province = resolve !== "" ? resolve : [];

      var seriesData = [];

      if (resolve.length > 0) {
        for (var i = 0; i < provinceList.length; i++) {
          let obj = {};
          if (
            province.filter(
              item => item.sssfshow.indexOf(provinceList[i]) !== -1
            ) != ""
          ) {
            obj.name = provinceList[i];
            obj.value = province.filter(
              item => item.sssfshow.indexOf(provinceList[i]) !== -1
            )[0].countnumber;
            seriesData.push(obj);
          } else {
            obj.name = provinceList[i];
            obj.value = "";
            seriesData.push(obj);
          }
        }
      }

      let option = {};
      // 区域染色的配置
      series.push({
        name: "各省车辆分布",
        type: "map",
        mapType: "china",
        roam: false, //是否开启鼠标缩放和平移漫游
        zlevel: 3,
        geoIndex: 0,
        rippleEffect: {
          brushType: "stroke"
        },
        label: {
          normal: {
            show: true,
            position: "right",
            formatter: "{b}"
          }
        },
        symbolSize: function(val) {
          return val[2] / 8;
        },
        itemStyle: {
          //地图区域的多边形 图形样式
          normal: {
            //是图形在默认状态下的样式
            label: {
              show: true, //是否显示标签
              textStyle: {
                color: "rgb(249, 249, 249)"
              }
            }
          },
          emphasis: {
            //是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
            label: { show: true }
          }
        },
        data: seriesData
      });
      series.push({
        name: "",
        type: "effectScatter",
        zlevel: 21,
        coordinateSystem: "geo",
        showEffectOn: "render",
        rippleEffect: {
          brushType: "stroke"
        },
        // geoIndex: 0,
        hoverAnimation: true,
        // symbol: "image://static/image/car.gif",
        // symbolSize: 15,
        symbolSize: 10,
        itemStyle: {
          normal: {
            color: "#2D8CF0",
            shadowBlur: 10,
            shadowColor: "#333",
            opacity: 0.5
          }
        }
      });

      series.push({
        name: "",
        type: "effectScatter",
        zlevel: 20,
        coordinateSystem: "geo",
        showEffectOn: "",
        rippleEffect: {
          brushType: "stroke"
        },
        geoIndex: 0,
        hoverAnimation: true,
        // symbol: "image://static/image/car.gif",
        // symbolSize: 15,
        symbolSize: 10,
        itemStyle: {
          normal: {
            color: "#F0FFF0",
            shadowBlur: 10,
            shadowColor: "#333",
            opacity: 0.5
          }
        }
      });
      option = {};
      option = {
        backgroundColor: "#18191d",
        color: "#000",
        title: {
          text: "全国各省市车辆分布情况",
          // subtext: "data from PM25.in",
          // sublink: "http://www.pm25.in",
          x: "center",
          textStyle: {
            fontSize: 20,
            align: "center",
            color: "#fff"
          },
          top: "5%"
        },
        visualMap: {
          show: true,
          itemHeight: 120,
          min: 0,
          max: 200,
          left: "10%",
          bottom: "5%",
          text: ["高", "低"],
          inRange: {
            color: ["#33c5f2", "#f4a700", "#15bc6c", "#efe63c"]
            // 有值地图块颜色区间
          },
          calculable: true,
          textStyle: {
            color: "#fff"
          },
          realtime: true,
          hoverLink: true
        },
        geo: {
          map: "china",
          zlevel: 3,
          label: {
            normal: {
              show: true,
              textStyle: {
                color: "#fff"
              }
            },
            emphasis: {
              color: "#ffffff" //鼠标悬浮文字颜色
            }
          },
          itemStyle: {
            normal: {
              areaColor: "#282d35",
              borderColor: "#eeeeee"
            },
            emphasis: {
              areaColor: "" //鼠标悬浮省图颜色
              //opacity: 0.8
            }
          }
        },
        series: series,
        tooltip: {
          trigger: "item",
          formatter: params => {
            if (!isNaN(params.value)) {
              return (
                params.seriesName + "<br/>" + params.name + " : " + params.value
              );
            } else if (params.seriesName == "") {
              return "车牌号:" + "<br/>" + params.value[2];
            } else if (params.value == "") {
              return params.seriesName + "<br/>" + params.name + " : 无车辆";
            } else {
              return "";
            }
          }
        }
      };
      mapDistribution.setOption(option);

      $("#mapDistribution").css(
        "height",
        $("#mapDistributionDiv").height() + "px"
      );
      $("#mapDistribution").css(
        "width",
        $("#mapDistributionDiv").width() + "px"
      );
      mapDistribution.resize();

      mapDistribution.on("click", function(params) {
        vm.searchCity = params.name;
        // $(".searchBox").fadeOut();
        vm.isDisplayShowSearchBox = false;
        vm.isShowSearchBox = true;
        $(".searchBox").fadeIn();
        vm.province = params.name;
        let baiduMap = new BMap.Map("myMap");
        mapDistribution.mapTarget = baiduMap;
        baiduMap.enableScrollWheelZoom();
        let city = params.name;
        provincenter = city;
        if (city != "") {
          //baiduMap.centerAndZoom(city, 11); // 用城市名设置地图中心点！！！！！
          vm.getCarNumByProvince(city).then(resolve => {
            if (resolve && resolve.length > 0) {
              baiduMap.centerAndZoom(
                new BMap.Point(resolve[0].bdjd, resolve[0].bdwd),
                11
              );
              vm.showCar(baiduMap, resolve, mapDistribution, 0);
              vm.showCharge(baiduMap, resolve, mapDistribution);
            } else {
              baiduMap.centerAndZoom(city, 11);
              vm.showCharge(baiduMap, resolve, mapDistribution);
            }
          });
        } else {
          vm.getCarNumByProvince().then(resolve => {
            if (resolve && resolve.length > 0) {
              baiduMap.centerAndZoom(
                new BMap.Point(resolve[0].bdjd, resolve[0].bdwd),
                11
              );
              vm.showCar(baiduMap, resolve, mapDistribution, 0);
              vm.showCharge(baiduMap, resolve, mapDistribution);
            } else {
              baiduMap.centerAndZoom(city, 11);
              vm.showCharge(baiduMap, resolve, mapDistribution);
            }
          });
        }
      });
    }
  }
};
</script>


