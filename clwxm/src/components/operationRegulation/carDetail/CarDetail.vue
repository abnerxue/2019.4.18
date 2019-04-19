<style lang="less" scoped>
.carDetailModal {
  z-index: 19891021;
  width: 575px;
  height: 420px;
  background-color: #fff;
  position: absolute;
  right: 57px;
  top: 194px;
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

.showOpenLineTitle {
  height: 40px;
  line-height: 40px;
  background: rgb(0, 132, 243);
  font-size: 14px;
  text-align: left;
  vertical-align: middle;
  padding-left: 15px;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: absolute;
  right: -1000px;
}

.displayShowCarDetailModal {
  display: none;
}

.showCarDetailModalBlock {
  display: block;
}

.showOpenModal {
  display: none;
}

.displayOpenModal {
  display: block;
}
</style>
<template>
  <div
    class="carDetailModal"
    id="carDetailModal"
    ref="carDetailModal"
    :class="{displayShowCarDetailModal:isDisplayShowCarDeailModal,showCarDetailModalBlock:isShowCarDeailModal}"
  >
    <div
      class="showLineTitle"
      id="showLineTitle"
    >
      <div><img
          src="/static/image/right_triangle.png"
          style="margin-right:20px;cursor:pointer;height:20px;"
          @click="resizeModal"
        />{{cph}}</div>
      <img
        alt
        src="/static/image/icon_close.png"
        style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
        @click="closeDetailModal"
      >
    </div>
    <div style="display:flex;justify-content:space-evenly;">
      <Tabs
        v-model="tabValue"
        @on-click="clickVehicleTracking"
      >
        <TabPane
          label="车辆详情"
          name="carItemDetail"
          ref="carItemDetail"
        >
          <car-item-detail
            :carNum="carNum"
            :cph="cph"
            :carStatus="carStatus"
            :carSpeed="carSpeed"
          ></car-item-detail>
        </TabPane>
        <TabPane
          label="行驶轨迹"
          name="driveLine"
        >
          <drive-line
            :carNum="carNum"
            :carStatus="carStatus"
            ref="driveLine"
          ></drive-line>
        </TabPane>
        <TabPane
          label="车辆视频"
          name="carVedio"
        >
          <car-vedio
            :carNum="carNum"
            :cph="cph"
          ></car-vedio>
        </TabPane>
        <TabPane
          label="车辆跟踪"
          name="vehicleTracking"
        >
          <vehicle-tracking
            ref="vehicleTracking"
            :carNum="carNum"
            :carStatus="carStatus"
            :cph="cph"
            :carSpeed="carSpeed"
            @changeSpeed="changeSpeed($event)"
          ></vehicle-tracking>
        </TabPane>
      </Tabs>
    </div>
    <div>
      <router-view></router-view>
    </div>

  </div>
</template>
<script>
import dragDown from "../../../../static/js/dragDownOne.js";
import CarItemDetail from "./CarItemDetail";
import CarVedio from "./CarVedio";
import DriveLine from "./DriveLine";
import VehicleTracking from "./VehicleTracking";

export default {
  props: ["cph", "carNum", "carStatus", "carSpeed"],
  components: {
    CarItemDetail,
    CarVedio,
    DriveLine,
    VehicleTracking
  },
  data() {
    return {
      isDisplayShowCarDeailModal: true,
      isShowCarDeailModal: false,
      tabValue: "carItemDetail",
      isShowOpenModal: true,
      isDisplayOpenModal: false
    };
  },
  mounted() {
    new dragDown.dragMover("showLineTitle");
  },
  updated() {
    if (this.tabValue == "vehicleTracking") {
      this.$refs.vehicleTracking.initMap();
    }
  },
  watch: {
    carSpeed(val, oldVal) {
      console.log(val, "iiiiiiiiiiiiiuuuuuu7777777");
      alert(val.carSpeed);
      this.$emit("changeFatherSpeed", val.carSpeed);
    }
  },
  methods: {
    changeSpeed: function(event) {
      this.carSpeed.carSpeed = event;
    },
    clickVehicleTracking: function(name) {
      if (name == "vehicleTracking") {
        if (this.carStatus == "在线") {
          this.$refs.vehicleTracking.initMap();
        } else {
          this.$Message.warning("当前车辆已停止行驶");
          //this.tabValue = "carItemDetail";
        }
      }
    },
    resizeModal: function() {
      $(".showOpenLineTitle").fadeIn();
      this.isShowOpenModal = false;
      this.isDisplayOpenModal = true;
      this.closeDetailModal();
    },
    openModal: function() {
      $(".showOpenLineTitle").fadeOut();
      this.isShowOpenModal = true;
      this.isDisplayOpenModal = false;
      this.addCarDetailModal();
    },
    closeResizeModal: function() {
      $(".showOpenLineTitle").fadeOut();
    },
    addCarDetailModal: function() {
      $(".carDetailModal").fadeIn();
      this.isDisplayShowCarDeailModal = false;
      this.isShowCarDeailModal = true;
    },
    closeDetailModal: function() {
      this.$refs.driveLine.closeLineModal();
      $(".carDetailModal").fadeOut();
      this.isDisplayShowCarDeailModal = true;
      this.isShowCarDeailModal = false;
    }
  }
};
</script>


