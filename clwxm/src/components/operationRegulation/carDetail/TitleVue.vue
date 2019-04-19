<style lang="less" scoped>
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
  right: 70px;
  top: 205px;
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
    class="showOpenLineTitle"
    :class="{showOpenModal:isShowOpenModal,displayOpenModal:isDisplayOpenModal}"
  ><img
      src="/static/image/left_triangle.png"
      style="margin-right:10px;height:20px;cursor:pointer"
      @click="openModal"
    />{{cph}}
    <img
      alt
      src="/static/image/icon_close.png"
      style="margin-right: 10px;width:10px;height:10px;cursor:pointer;margin-left: 20px;"
      @click="closeResizeModal"
    />
  </div>

</template>
<script>
import $ from "jquery";

export default {
  props: ["cph"],
  data() {
    return {
      isShowOpenModal: true,
      isDisplayOpenModal: false
    };
  },
  mounted() {},
  methods: {
    openModal: function() {
      $(".showOpenLineTitle").fadeOut();
      this.isShowOpenModal = true;
      this.isDisplayOpenModal = false;
      this.$emit("displayDetailVue", "");
    },
    closeResizeModal: function() {
      $(".showOpenLineTitle").fadeOut();
    }
  }
};
</script>


