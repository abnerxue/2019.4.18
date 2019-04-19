<style lang="less" scoped>
</style>
<style>
</style>

<template>
  <div class="currentLineModal">
    <div
      id="currentLineMap"
      style="width:535px;margin-top: 0px;height:440px;border-radius: 4px;z-index: 19891020;margin-left:20px;"
    ></div>
    <div style="display:flex;flex-direction: row;margin-top: 30px;">
      <div style="text-align:left;margin-left:20px;">
        <div>上报时间</div>
        <div>当前车速</div>
        <div>当前位置</div>
      </div>
      <div style="text-align:left;margin-left:20px;">
        <div>{{reportingTime}}</div>
        <div>{{carSpeed.carSpeed}}</div>
        <div>{{curPosition}}</div>
      </div>
    </div>
  </div>
</template>
<script>
let mapDistribution = {
  isPlay: false,
  speed: 8,
  isPause: false,
  index: 0
};

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
    return str > 9 ? str : "0" + str;
  }
  var date2 = new Date(UTCDateString);
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
  props: ["carNum", "carStatus", "cph", "carSpeed"],
  data() {
    return {
      tableData: [],
      tableLineDataLength: 5,
      pageIndex: 1,
      pageSize: 5,
      // carNum: this.carNum,
      datePicker: [dayStart, dayNow],
      reportingTime: "",
      curPosition: ""
    };
  },
  mounted() {
    let vm = this;
    vm.initMap();
  },
  // beforeUpdate() {
  //   let vm = this;
  //   vm.updatePosition();
  // },
  methods: {
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
    initMap: function() {
      let vm = this;
      if (vm.carStatus != "离线") {
        vm.getLeftBottomData();
        setInterval(() => {
          vm.updatePosition();
        }, 10000);
      } else {
        //vm.$Message.warning("当前车辆停止行驶！");
        vm.getLeftBottomData();
        //return;
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
    },
    updatePosition: function() {
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
      }).then(resolve => {
        let item = resolve.data.result[0];
        vm.reportingTime = item.rksj;
        vm.carSpeed.carSpeed = item.dqsd;
        vm.$emit("changeSpeed", vm.carSpeed.carSpeed);
        var myGeo = new BMap.Geocoder();
        myGeo.getLocation(new BMap.Point(item.bdjd, item.bdwd), function(
          result
        ) {
          if (result) {
            vm.curPosition = result.address;
          }
        });
        let linePoint = new BMap.Point(item.bdjd, item.bdwd);
        console.log(item.bdjd, item.bdwd, "当前经纬度");
        console.log(vm.curPosition, "[[[[[[[[[[[[");
        carMk.setPosition(linePoint);
        let baiduMap = this.$store.state.vehicleTrackingMap;
        baiduMap.centerAndZoom(linePoint, 15);
      });
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
          let baiduMap = new BMap.Map("currentLineMap");
          vm.$store.state.vehicleTrackingMap = baiduMap;
          //baiduMap.enableScrollWheelZoom();
          baiduMap.centerAndZoom(new BMap.Point(item.bdjd, item.bdwd), 15);

          vm.reportingTime = item.rksj;
          vm.carSpeed.carSpeed = item.dqsd;
          var myGeo = new BMap.Geocoder();
          myGeo.getLocation(new BMap.Point(item.bdjd, item.bdwd), function(
            result
          ) {
            if (result) {
              vm.curPosition = result.address;
            }
          });

          if (vm.carStatus == "在线") {
            var myIcon = new BMap.Icon(
              "/static/image/icon-car.png",
              new BMap.Size(41, 25)
            );
            carMk = new BMap.Marker(new BMap.Point(item.bdjd, item.bdwd), {
              icon: myIcon
            });
          } else {
            var myIcon = new BMap.Icon(
              "/static/image/car_gray.png",
              new BMap.Size(41, 25)
            );
            carMk = new BMap.Marker(new BMap.Point(item.bdjd, item.bdwd), {
              icon: myIcon
            });
          }

          baiduMap.addOverlay(carMk);
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    }
  }
};
</script>




