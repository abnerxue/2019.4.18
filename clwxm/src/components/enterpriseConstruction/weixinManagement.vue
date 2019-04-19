<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 80px;
  text-align: center;
  display: flex;
  align-items: center;
  margin-left: 20px;
}

.plateNum {
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
  margin-top: -10px;
  // margin-left: -300px;
}

.title {
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
  width: 80px;
}

.addCarModal {
  z-index: 19891016;
  width: 700px;
  height: 485px;
  background-color: #fff;
  position: absolute;
  // cursor: move;
  left: 300px;
  top: 165px;
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

.displayShowCarModal {
  display: none;
}

.showCarModalBlock {
  display: block;
}

.attibute {
  width: 100px;
}

#table {
  min-width: 580px;
}
</style>

<style lang="less">
.ivu-form-item-content {
  display: flex;
}

.ivu-form-item {
  margin-bottom: 40px;
}

.displayShowCarModal {
  display: none;
}

.showCarModalBlock {
  display: block;
}

.weixinBind {
  z-index: 19891016;
  width: 300px;
  height: 300px;
  background-color: #fff;
  position: absolute;
  // cursor: move;
  left: 300px;
  top: 165px;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.columnName {
  margin-left: 150px;
}
</style>

<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;"
    id="carManagementId"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">微信管理</h5>
      <div>
        <div style="display: flex;min-width: 600px;flex-direction: row;align-items: center;position: relative;margin-left: 85px;margin-top: 35px;margin-bottom: 30px;">
          <Button
            type="primary"
            @click="bindWeiXin"
            style="margin-left: 20px;"
          >绑定微信</Button>
        </div>
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="tableData"
          id="table"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
        ></Page>
      </div>
    </div>

    <div
      class="weixinBind"
      id="weixinBind"
      :class="{displayShowCarModal:isDisplayShowCarModal,showCarModalBlock:isShowCarModal}"
    >
      <div
        class="showLineTitle"
        id="showAddTitle"
      >
        <div>绑定微信</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeCarModal"
        >
      </div>
      <div
        id="weixinImg"
        style="margin-top:25px;"
      ></div>
    </div>
  </div>
</template>
<script>
import dragDown from "../../../static/js/dragDown.js";
import $ from "jquery";

function utf16to8(str) {
  //解决中文乱码
  var out, i, len, c;
  out = "";
  len = str.length;
  for (i = 0; i < len; i++) {
    c = str.charCodeAt(i);
    if (c >= 0x0001 && c <= 0x007f) {
      out += str.charAt(i);
    } else if (c > 0x07ff) {
      out += String.fromCharCode(0xe0 | ((c >> 12) & 0x0f));
      out += String.fromCharCode(0x80 | ((c >> 6) & 0x3f));
      out += String.fromCharCode(0x80 | ((c >> 0) & 0x3f));
    } else {
      out += String.fromCharCode(0xc0 | ((c >> 6) & 0x1f));
      out += String.fromCharCode(0x80 | ((c >> 0) & 0x3f));
    }
  }
  return out;
}

export default {
  name: "bicycleStatistics",
  data() {
    return {
      weixinUrl: "",
      selectArray: [],
      addTableData: [],
      addSearchPlateNum: "",
      addPageIndex: 1,
      addTableDataLength: 5,
      addPageSize: 5,
      isDisplayShowCarModal: true,
      isShowCarModal: false,
      plateNum: "",
      formInline: {
        carNum: "",
        carType: "",
        licensePlate: "",
        licenseType: "",
        contactPerson: "",
        contactPhone: ""
      },
      ruleInline: {
        user: [
          {
            required: true,
            message: "Please fill in the user name",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "Please fill in the password.",
            trigger: "blur"
          },
          {
            type: "string",
            min: 6,
            message: "The password length cannot be less than 6 bits",
            trigger: "blur"
          }
        ]
      },
      currentId: 0,
      addCarNum: "",
      addClbh: "",
      cllxshow: "",
      columns: [
        {
          title: "绑定时间",
          key: "rksj",
          align: "center"
        },
        {
          title: "用户昵称",
          key: "wxh",
          align: "left",
          width: 100
        },
        {
          title: "操作",
          key: "action",
          minWidth: 150,
          align: "center",
          // fixed: "right",
          render: (h, params) => {
            let vm = this;
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      vm.$Modal.confirm({
                        title: "请确认",
                        content: "<p>是否解绑</p>",
                        onOk: () => {
                          vm.deleteCar(params.row.wxh);
                        },
                        onCancel: () => {
                          //this.$Message.info('Clicked cancel');
                        }
                      });
                    }
                  }
                },
                "解绑"
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
      carTypeList: [],
      carLicenseTypeList: []
    };
  },
  mounted() {
    if (window.innerHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }

    if (window.innerWidth < 900) {
      $("#carManagementId").css("max-width", "620px");
    }
    this.getTableData(this.pageIndex);
    this.getAddTableData(this.addPageIndex);
    new dragDown.dragMover("showAddTitle");
    new dragDown.dragMover("showLineTitle");
    this.getCarType();
    this.getCarLicenceType();
    if (window.innerHeight < 900) {
      $("#table").css("height", (window.innerHeight * 450) / 980 + "px");
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
  },
  methods: {
    closeCarModal: function() {
      $("#weixinBind").fadeOut();
      this.isDisplayShowCarModal = true;
      this.isShowCarModal = false;
    },
    bindWeiXin: function() {
      $("#weixinImg").html("");
      let vm = this;
      vm.$http({
        method: "post",
        url: "/gm-data/api/fweb/wechartaddress",
        headers: {
          DTS: new Date().getTime()
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          vm.weixinUrl = utf16to8(response.data.result).trim();
          jQuery("#weixinImg").qrcode({
            width: 200,
            height: 200,
            marginTop: 20,
            render: "canvas", //设置渲染方式 table canvas
            typeNumber: -1, //计算模式
            correctLevel: 0, //纠错等级
            background: "#ffffff", //背景颜色
            foreground: "#000000", //前景颜色
            text: vm.weixinUrl
          });
          $(".weixinBind").fadeIn();
          vm.isDisplayShowCarModal = false;
          vm.isShowCarModal = true;
          vm.getTableData(1);
        })
        .catch(function(response) {
          vm.$Message.error("");
        });
    },
    selectAllData: function(event) {
      this.selectArray = event;
    },
    selectData: function(event) {
      this.selectArray = event;
    },
    searchCarList: function() {
      this.getAddTableData(1);
    },
    getCarType: function() {
      let vm = this;
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/codes/cllx",
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
          vm.carTypeList = result.map(function(item) {
            return { label: item.dmsm1, value: item.dmz };
          });
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    getCarLicenceType: function() {
      let vm = this;
      vm.$http({
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
          vm.carLicenseTypeList = result.map(function(item) {
            return { label: item.dmsm2, value: item.dmz };
          });
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    addCar: function() {
      this.getAddTableData(this.addPageIndex);
      $(".addCarModal").fadeIn();
      this.isDisplayShowCarModal = false;
      this.isShowCarModal = true;
    },
    deleteCar(carNum) {
      let vm = this;
      let dataJson = {
        wxh: carNum
      };
      vm.$http({
        method: "post",
        url: "/gm-data/api/wechat/123",
        headers: {
          DTS: new Date().getTime(),
          http_method: "delete"
        },
        data: dataJson,
        params: dataJson,
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          vm.getTableData(vm.pageIndex);
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    changePage(index) {
      this.getTableData(index);
    },
    addChangePage(index) {
      this.getAddTableData(index);
    },
    searchList() {
      this.getTableData(1);
    },
    getTableData(pageIndex) {
      let vm = this;
      let dataJson = {
        pageIndex: pageIndex,
        pageSize: vm.pageSize
      };
      let dataParams = {
        pageIndex: pageIndex,
        pageSize: vm.pageSize
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/wechat",
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
          vm.tableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    },
    getAddTableData(pageIndex) {
      let vm = this;
      let dataJson = {
        cph: vm.addSearchPlateNum
      };
      let dataParams = {
        cph: vm.addSearchPlateNum
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/fwebactivecar/" + pageIndex,
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
          vm.addTableData = response.data.result.array;
          vm.addTableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    }
  }
};
</script>



