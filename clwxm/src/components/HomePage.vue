<style lang="less" scoped>
.dateAndTime {
  letter-spacing: 0.1em;
  font-size: 16px;
  color: white;
  float: left;
  margin-left: 30px;
  margin-top: 30px;
}

.header {
  height: 75px;
  line-height: 75px;
  width: 33%;
  background: url(/static/image/fweb_title.png) no-repeat;
  background-size: 100% 100%;
  background-position: 50% 50%;
  position: absolute;
  left: 33.5%;
}

.dataShow {
  float: right;
  margin-right: 20px;
  user-select: none;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
.quit {
  float: right;
  margin-right: 20px;
  margin-top: 30px;
  user-select: none;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.menuList {
  background-color: #24252a;
  width: 280px;
}

.addBackground {
  background-color: rgb(0, 132, 243);
}

.route_a {
  width: 280px;
  height: 49px;
  position: absolute;
  top: 0;
  left: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.ivu-table-wrapper {
  margin-left: 20px;
  margin-right: 20px;
  // width: calc(100vw - 360px);
}
</style>
<style>
.ivu-menu-vertical.ivu-menu-light:after {
  width: 0 !important;
}

.menuList li {
  background: #24252a;
  font-size: 18px;
  /* font-weight: bold; */
  color: white;
}

a,
a:active,
a:hover {
  color: white;
  background-color: "";
}

.ivu-menu-vertical .ivu-menu-item,
.ivu-menu-vertical .ivu-menu-submenu-title {
  /* border-bottom: 1px #141519 solid;
  border-top: 1px #141519 solid; */
}

.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
  font-size: 16px;
}

.ivu-menu-vertical .ivu-menu-item:hover,
.ivu-menu-vertical .ivu-menu-submenu-title:hover {
  background: rgb(0, 132, 243);
}

.addBackground .ivu-menu-item-active {
  background: rgb(0, 132, 243) !important;
}

.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
  color: white;
}
/* color: #2d8cf0; */
li .ivu-menu-item:hover {
  font-size: 14px;
  font-weight: bold;
}

.ivu-menu-submenu-title {
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.ivu-menu {
  display: block;
  margin: 0;
  padding: 0;
  outline: 0;
  list-style: none;
  color: #fff !important;
  font-size: 14px;
  position: inherit !important;
  z-index: 900;
}

.ivu-menu-submenu-title:hover {
  background-color: #0084f3 !important;
}
li .ivu-menu-item:hover {
  background-color: #0084f3 !important;
}
.addBackground .ivu-menu-item-active {
  background-color: #0084f3 !important;
}

.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
  color: white !important;
  border-right: 2px solid #2d8cf0;
  z-index: 2;
}

.ivu-menu-vertical .ivu-menu-item:hover,
.ivu-menu-vertical .ivu-menu-submenu-title:hover {
  color: #fff !important;
}

.ivu-page {
  margin-top: 20px;
}
</style>


<template>
  <div>
    <div style="background: rgb(40, 44, 53);position:relative;height:75px; font-size: 14px;">
      <div style="position:absolute;height:100%;width:33.5%;left:0px;background-color:#24252a;">
        <div class="dateAndTime">{{date}} {{weekday}} {{time}}</div>
      </div>
      <div class="header"></div>
      <div style="position:absolute;height:100%;width:33.5%;left:66.5%;background-color:#24252a;">
        <div class="quit">
          <div
            @click="locationHref"
            style="margin-right: 30px;"
          >
            <img
              alt
              src="/static/image/icon_data.png"
              style="margin-right: 10px;"
            >大数据展示
          </div>
          <div @click="exit">
            <img
              alt
              src="/static/image/background/quit.png"
              style="margin-right: 10px;"
            />退出
          </div>
        </div>
      </div>
    </div>
    <div style="display:flex;height:calc(100vh - 75px);min-height:800px;">
      <div
        class="menuList"
        style="height:calc(100vh - 75px);min-height:800px;"
      >
        <Menu
          width="280px"
          active-name="指挥中心"
          ref="menu"
          accordion
        >
          <div
            v-for="(menu,index) in menuList"
            :key="index"
          >
            <Submenu :name="menu.cdmc">
              <template slot="title">
                <img
                  :src="imgSrc(menu.tblx)"
                  style="margin-right: 0px;display: inline-block;
                vertical-align: middle;"
                >
                {{menu.cdmc}}
                <div v-if="menu.cdmc =='指挥中心'">
                  <router-link
                    to="/homePage/controlCenter/mapDistribution"
                    class="route_a"
                  ></router-link>
                  <div style="float:right;width: 38px;"></div>
                </div>
                <div v-else></div>
              </template>
              <div v-if="menu.children">
                <div
                  v-for="(child,childIndex) in menu.children"
                  :key="childIndex"
                  class="addBackground"
                >
                  <MenuItem :name="child.cdmc">
                  {{child.cdmc}}
                  <div v-if="child.cdmc =='echarts'">
                    <router-link
                      to="/homePage/test/echarts"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '车辆行驶统计'">
                    <router-link
                      to="/homePage/vehicleStatistics/bicycleStatistics"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '企业车辆统计'">
                    <router-link
                      to="/homePage/vehicleStatistics/motorcadeStatistics"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '充电站统计'">
                    <router-link
                      to="/homePage/vehicleStatistics/chargeStatistics"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '车辆管理'">
                    <router-link
                      to="/homePage/enterpriseConstruction/carManagement"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '人员录入'">
                    <router-link
                      to="/homePage/enterpriseConstruction/personalEntry"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '实时监控'">
                    <router-link
                      to="/homePage/operationRegulation/realTimeMonitoring"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '车辆监控'">
                    <router-link
                      to="/homePage/operationRegulation/vehicleMonitoring"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '告警监控'">
                    <router-link
                      to="/homePage/operationRegulation/alarmMonitor"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '车辆视频'">
                    <router-link
                      to="/homePage/operationRegulation/vehicleVedio"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '车辆行为'">
                    <router-link
                      to="/homePage/operationRegulation/bicycleAction"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '告警明细'">
                    <router-link
                      to="/homePage/alarmManagement/alarmDetail"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '角色管理'">
                    <router-link
                      to="/homePage/userManagement/roleManagement"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '账号管理'">
                    <router-link
                      to="/homePage/userManagement/userManagement"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else-if="child.cdmc == '微信管理'">
                    <router-link
                      to="/homePage/enterpriseConstruction/weixinManagement"
                      class="route_a"
                    ></router-link>
                  </div>
                  <div v-else></div>
                  </MenuItem>
                </div>
              </div>
            </Submenu>
          </div>
        </Menu>
      </div>
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";
import vuex from "vuex";

export default {
  name: "homePage",
  data() {
    return {
      date: "",
      time: "",
      weekday: "",
      menuList: [],
      username: ""
    };
  },
  mounted() {
    let vm = this;
    window.onresize = function() {
      this.username = window.localStorage.username;
      $("body").css({ height: window.innerHeight, width: window.innerWidth });
    };
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
    vm.getMenuData();
    setInterval(function() {
      vm.getDateTime();
    }, 1000);
    setTimeout(() => {
      if ($(".menuList").find("li.ivu-menu-submenu i")[0]) {
        $(".menuList")
          .find("li.ivu-menu-submenu i")[0]
          .remove();
      }
    }, 500);
  },
  methods: {
    locationHref: function() {
      window.open("http://172.16.10.28:8084/gm-screen", "_blank");
    },
    addBackground: function(event) {
      $("li").removeClass("addBackground");
      $(event.target).addClass("addBackground");
    },
    exit: function() {
      localStorage.clear();
      axios.defaults.headers.common["USER_TOKEN"] = "";
      this.$router.push({ path: "/" });
    },
    imgSrc: function(src) {
      var arr = new Array();
      arr.push("/static/image/");
      arr.push(src);
      arr.push(".png");
      var str = arr.join("");
      return str;
    },
    getMenuData() {
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/interface/menulist",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search",
          http_mod: "S001"
        },
        data: {
          yhzh:
            window.localStorage.username == ""
              ? this.username
              : window.localStorage.username
        },
        params: {
          yhzh:
            window.localStorage.username == ""
              ? this.username
              : window.localStorage.username
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(response => {
          if (response.data.code == "10003") {
            let vm = this;
            vm.getToken();
          }
          vm.getToken();
          vm.menuList = response.data.result;
        })
        .catch(response => {
          let vm = this;
          vm.getToken();
          console.log(response);
        });
    },
    getDateTime: function() {
      let myDate = new Date();
      let year = myDate.getFullYear();
      let month = myDate.getMonth() + 1;
      let date = myDate.getDate();
      let hours = myDate.getHours();
      let mins = myDate.getMinutes();
      let sec = myDate.getSeconds();
      if (mins < 10) {
        mins = "0" + mins;
      }
      if (sec < 10) {
        sec = "0" + sec;
      }

      var d = new Date();
      var weekday = new Array(7);
      weekday[0] = "星期日";
      weekday[1] = "星期一";
      weekday[2] = "星期二";
      weekday[3] = "星期三";
      weekday[4] = "星期四";
      weekday[5] = "星期五";
      weekday[6] = "星期六";
      this.time = hours + ":" + mins + ":" + sec;
      this.date = year + "-" + month + "-" + date;
      this.weekday = weekday[d.getDay()];
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

