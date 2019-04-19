<style lang="less" scoped>
.title {
  line-height: 15px;
  height: 15px;
  font-size: 12px;
  font-weight: bold;
  color: #000;
  margin-left: 20px;
  border-left: 3px solid #34a0f8;
  position: relative;
  margin-bottom: 20px;
  text-align: left;
  padding-left: 10px;
  top: 10px;
  width: 120px;
}

.leftItemName {
  color: grey;
  margin-right: 10px;
  margin-left: -277px;
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
</style>
<template>
  <div
    id="carItemDetail"
    class="carItemDetail"
  >
    <div>
      <h5 class="title">车辆基本信息</h5>
      <div style="display:flex;flex-direction:row;">
        <div style="margin-left: 35px;">
          <div>车牌号码</div>
          <div>车辆状态</div>
          <div>当前告警</div>
        </div>
        <div style="text-align: left;margin-left: 20px;">
          <div>{{cph}}</div>
          <div>{{carState}}</div>
          <div>{{currentWarning}}</div>
        </div>
        <div style="margin-left: 100px;">
          <div>上报时间</div>
          <div>当前车速</div>
        </div>
        <div style="text-align: left;margin-left: 20px;">
          <div>{{reportingTime}}</div>
          <div>{{carSpeed.carSpeed}}</div>
        </div>
      </div>
    </div>
    <div>
      <h5 class="title">驾驶员信息</h5>
      <div style="display:flex;flex-direction:row;">
        <div style="margin-left: 35px;">
          <div>驾驶人员</div>
        </div>
        <div style="text-align: left;margin-left: 20px;">
          <div>{{driver}}</div>
        </div>
        <div style="position: absolute;left: 260px;">
          <div>联系电话</div>
        </div>
        <div style="text-align: left;position: absolute;left: 325px;">
          <div>{{driverPhone}}</div>
        </div>
      </div>

    </div>
  </div>
</template>
<script>
export default {
  props: ["carNum", "cph", "carStatus", "carSpeed"],
  data() {
    return {
      reportingTime: "",
      carState: "",
      driver: "",
      driverName: "",
      currentWarning: "",
      affiliatedEnterprise: "",
      enterpriseAddress: "",
      contact: "",
      contactNumber: "",
      driverPhone: "",
      enterpriseName: ""
    };
  },
  beforeUpdate() {
    this.getLeftBottomData();
  },
  methods: {
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
          vm.driver = item.lxr;
          vm.carState = vm.carStatus;
          vm.driverPhone = item.lxdh;
        },
        reject => {
          console.log("请求失败", reject);
        }
      );
    }
  }
};
</script>