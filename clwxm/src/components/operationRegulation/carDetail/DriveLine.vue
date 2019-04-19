<style lang="less" scoped>
.playBackTime {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin-top: 10px;
  font-size: 14px;
  color: #000;
  margin-left: -30px;
}

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
</style>
<style>
.playBackTime {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-top: 10px;
}
</style>

<template>
  <div class="driveTrail">
    <div class="playBackTime">
      <div style="color: black;">回放时间</div>
      <div>
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
      <div>
        <div class="playBackTime">
          <span
            class="s-label"
            style="margin-left: -20px;"
          >播放速度</span>
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
          <Button
            ref="showDriveCurrentLineButton"
            @click.native="showCurrentLine(carNum,carStatus)"
            style="margin-right: -15px;"
          >当前轨迹</Button>
        </div>
      </div>
    </div>
    <div style="width:532px;margin-left: 10px;margin-top: 20px;">
      <Table
        :columns="columns"
        :data="tableData"
        height="200"
      ></Table>
      <Page
        :total="tableLineDataLength"
        :current="pageIndex"
        @on-change="changePage($event,carNum)"
        :page-size="pageSize"
      ></Page>
    </div>
    <div
      id="driveLineMap"
      style="width:510px;margin-top: 20px;height:200px;border-radius: 4px;z-index: 19891020;margin-left:20px;"
    ></div>
  </div>
</template>
<script>
import axios from "axios";

let mapDistribution = {
  isPlay: false, // 是否播放
  speed: 8,
  isPause: false,
  index: 0,
  isCurrentPlay: false
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
  props: ["carNum", "carStatus"],
  data() {
    return {
      showDriveCurrentLineButton: "",
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
                      let vm = this;
                      if (
                        (timeout_current &&
                          timeout_current != null &&
                          timeout_current != "") ||
                        timeout != null
                      ) {
                        let baiduMap = vm.$store.state.driveLineMap;
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
                      } else {
                        mapDistribution.index = curIndex;
                        let baiduMap = vm.$store.state.driveLineMap;
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
                        let baiduMap = vm.$store.state.driveLineMap;
                        baiduMap.clearOverlays();
                        vm.playLine(params);
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
      tableLineDataLength: 5,
      pageIndex: 1,
      pageSize: 5,
      // carNum: this.carNum,
      datePicker: [dayStart, dayNow],
      driveStatus: ""
    };
  },
  mounted() {
    let vm = this;
    if (this.carStatus == "在线") {
      this.driveStatus = 1;
    } else if (this.carStatus == "离线") {
      this.driveStatus = 2;
    } else {
      this.driveStatus = 0;
    }
    this.getProvince().then(resolve => {
      vm.initMap(resolve);
    });
    //this.closeLineModal();
  },
  watch: {
    cph(val, oldVal) {
      console.log(val, "iiiiiiiiiiiiiuuuuuu7777777");
      alert(val);
      this.closeLineModal();
    }
  },
  updated() {
    let vm = this;
    if (this.carStatus == "在线") {
      this.driveStatus = 1;
    } else if (this.carStatus == "离线") {
      this.driveStatus = 2;
    } else {
      this.driveStatus = 0;
    }
    this.getProvince().then(resolve => {
      vm.initMap(resolve);
    });
  },
  methods: {
    playLine: function(params) {
      let vm = this;
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
          let baiduMap = vm.$store.state.driveLineMap;
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
    closeLineModal: function() {
      $(".gress-box-inner").css("width", "12.5%");
      $(".gress-box-out .e-txt").text("2X");
      this.datePicker = [];
      this.tableData = [];
      this.tableLineDataLength = 1;
      clearInterval(timeout);
      clearInterval(timeout_current);
      mapDistribution.index = "";
      mapDistribution.isPlay = false;
      let baiduMap = this.$store.state.driveLineMap;
      if (baiduMap && baiduMap.getOverlays()) {
        baiduMap.clearOverlays();
      }

      $(window).scrollTop(1000);
      // carList.forEach(car => {
      //   baiduMap.addOverlay(car);
      // });
      //$("#showLineModal").fadeOut();
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
    showCurrentLine: function(carNum, driveStatus) {
      let baiduMap = this.$store.state.driveLineMap;
      baiduMap.clearOverlays();
      let vm = this;
      if (driveStatus == "离线") {
        this.$Message.error("当前车辆已停止行驶");
        return;
      }
      // vm.$refs.showDriveCurrentLineButton.disabled = true;
      // setTimeout(function() {
      //   vm.$refs.showDriveCurrentLineButton.disabled = false;
      // }, 30000);
      if (timeout_current && timeout_current != null && timeout_current != "") {
        clearInterval(timeout_current);
        baiduMap.removeOverlay(car_begin);
        baiduMap.removeOverlay(carEnd);
        baiduMap.removeOverlay(pointLine_current);
        baiduMap.removeOverlay(carMk_current);
      }
      if (timeout != null) {
        clearInterval(timeout);
        baiduMap.clearOverlays();
        carList.forEach(car => {
          baiduMap.addOverlay(car);
        });
      }
      mapDistribution.isCurrentPlay = true;

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
                if (mapDistribution.isCurrentPlay) {
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

        if (point.xszt == 2) {
          myIcon = new BMap.Icon(
            "/static/image/car_gray.png",
            new BMap.Size(50, 35)
          );
        } else if (point.xszt == 1) {
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
          if (vm.xszt == "0") {
            vm.carStatus = "在线";
          } else if (vm.xszt == "1") {
            vm.carStatus = "临停";
          } else {
            vm.carStatus = "离线";
          }
          vm.driveStatus = point.clzt;
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
        if (oper == "setCenter") {
          // 以当前坐标为中心点 且放大17倍
          mapObj.centerAndZoom(new BMap.Point(point[0], point[1]), 17);
        }
      });
    },
    initMap: function(resolve) {
      let vm = this;
      let baiduMap = new BMap.Map("driveLineMap");

      this.$store.state.driveLineMap = baiduMap;
      //baiduMap.enableScrollWheelZoom(false);
      vm.getCarNumByProvince("").then(resolve => {
        if (resolve && resolve.length > 0) {
          baiduMap.centerAndZoom(
            new BMap.Point(resolve[0].bdjd, resolve[0].bdwd),
            11
          );
          //vm.showCar(baiduMap, resolve, 0);
          vm.showCharge(baiduMap, resolve);
        } else {
          baiduMap.centerAndZoom("江苏", 11);
          vm.showCharge(baiduMap, resolve);
        }
      });
      $(window).scrollTop(0);
    },
    changePage(index, carNum) {
      this.searchLine(carNum, index);
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
    }
  }
};
</script>


