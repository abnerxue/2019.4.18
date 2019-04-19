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
  margin-top: 10px;
}

.searchItem {
  display: flex;
  align-items: center;
  margin-top: 0px;
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
</style>

<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;"
    id="carManagementId"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 15px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">车辆管理</h5>
      <div>
        <div style="display: flex;min-width: 600px;flex-direction: row;align-items: center;justify-content: space-around;position: relative;margin-left: -30px;margin-top: 30px;margin-bottom: 30px;">
          <div class="searchItem">
            <div class="labelName">车牌号码：</div>
            <Input
              v-model="plateNum"
              placeholder="请输入车牌号"
              class="plateNum"
            />
          </div>
          <div style="display:flex;justify-content:center;">
            <div class="labelName">车辆状态：</div>
            <Select
              v-model="carStatus"
              filterable
              clearable
              placeholder="请输入车牌号"
              style="width:240px;"
              class="plateNum"
            >
              <Option
                v-for="item in carStatusList"
                :value="item.value"
                :key="item.value"
              >{{ item.label }}</Option>
            </Select>
          </div>
          <div class="buttonList">
            <Button
              type="primary"
              @click="addCar"
            >添加</Button>
            <Button
              type="primary"
              @click="searchList"
              style="margin-left: 20px;"
            >查询</Button>
          </div>
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
      <div
        class="addCarModal"
        id="addCarModal"
        :class="{displayShowCarModal:isDisplayShowCarModal,showCarModalBlock:isShowCarModal}"
      >
        <div
          class="showLineTitle"
          id="showAddTitle"
        >
          <div>添加车辆</div>
          <img
            alt
            src="/static/image/icon_close.png"
            style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
            @click="closeCarModal"
          >
        </div>
        <div style="display: flex;flex-direction: row;align-items: center;justify-content: space-evenly;position: relative;">
          <div style="display:flex;justify-content:center;">
            <div
              class="labelName"
              style="color: #000;"
            >车牌号码：</div>
            <Input
              v-model="addSearchPlateNum"
              placeholder="请输入车牌号"
              class="plateNum"
            />
          </div>
          <div style="display:flex;justify-content:center;">
            <Button
              type="primary"
              @click="searchCarList()"
              style="margin-right:20px;"
            >查询</Button>
            <Button
              type="primary"
              @click="addCarToEnterprise()"
            >添加</Button>
          </div>
        </div>
        <Table
          :columns="addColumns"
          :data="addTableData"
          @on-select-all="selectAllData($event)"
          @on-select="selectData($event)"
          id="addTableList"
          style="width:650px;height:300px;margin-left: 25px;"
        ></Table>
        <Page
          :total="addTableDataLength"
          :current="addPageIndex"
          @on-change="addChangePage"
          :page-size="addPageSize"
        ></Page>
      </div>
    </div>
  </div>
</template>
<script>
import dragDown from "../../../static/js/dragDown.js";
import $ from "jquery";
export default {
  name: "bicycleStatistics",
  data() {
    return {
      carStatus: "",
      carStatusList: [],
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
      addColumns: [
        {
          title: "选择",
          key: "action",
          width: 50,
          align: "center",
          type: "selection"
          // render: (h, params) => {
          //   let id = params.index;
          //   let defaultS = false;
          //   let vm = this;
          //   if (this.currentId === id) {
          //     defaultS = true;
          //     vm.addCarNum = params.row.cph;
          //     vm.addClbh = params.row.clbh;
          //     vm.cllxshow = params.row.cllxshow;
          //   } else {
          //     defaultS = false;
          //   }

          //   return h("div", [
          //     h("Radio", {
          //       props: {
          //         value: defaultS
          //       },
          //       nativeOn: {
          //         click: () => {
          //           vm.currentId = id;
          //           vm.addCarNum = params.row.cph;
          //           vm.addClbh = params.row.clbh;
          //           vm.cllxshow = params.row.cllxshow;
          //         }
          //       }
          //     })
          //   ]);
          // },
        },
        {
          title: "车辆编号",
          key: "clbh",
          align: "center"
        },
        {
          title: "车辆类型",
          key: "cllxshow",
          align: "center"
        },
        {
          title: "车牌号",
          key: "cph",
          align: "center"
        }
      ],
      columns: [
        {
          title: "车辆编号",
          key: "clbh",
          align: "center",
          minWidth: 150
          // fixed: "left"
        },
        {
          title: "车辆类型",
          key: "cllxshow",
          align: "left",
          minWidth: 100
        },

        {
          title: "车牌号码",
          key: "cph",
          align: "left",
          minWidth: 100
        },
        {
          title: "车牌类型",
          key: "cpzlshow",
          minWidth: 100,
          align: "left"
        },
        // {
        //   title: "设备编号",
        //   key: "sbbh",
        //   minWidth: 100,
        //   align: "center"
        // },
        {
          title: "联系人",
          key: "lxr",
          minWidth: 100,
          align: "left"
        },
        {
          title: "联系方式",
          key: "lxdh",
          minWidth: 100,
          align: "left"
        },
        {
          title: "车辆状态",
          key: "clztshow",
          align: "center",
          minWidth: 100
          // fixed: "left"
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
                        content: "<p>是否删除</p>",
                        onOk: () => {
                          vm.deleteCar(params.row.clbh);
                        },
                        onCancel: () => {
                          //this.$Message.info('Clicked cancel');
                        }
                      });
                    }
                  }
                },
                "删除"
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
    this.selectStatus();
  },
  methods: {
    selectStatus: function() {
      let vm = this;
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/codes/clzt",
        headers: {
          DTS: new Date().getTime(),
          http_method: "insert"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          let result = response.data.result;
          vm.carStatusList = result.map(function(item) {
            return { label: item.dmsm1, value: item.dmz };
          });
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    selectAllData: function(event) {
      this.selectArray = event;
    },
    selectData: function(event) {
      this.selectArray = event;
    },
    addCarToEnterprise: function(carNum) {
      let vm = this;
      let dataJson = "";
      let arrayList = vm.selectArray;
      if (vm.selectArray.length < 1) {
        this.$Message.error("请选择添加项");
        return;
      }
      if (arrayList && arrayList.length > 0) {
        for (let i = 0; i < arrayList.length; i++) {
          if (i === 0) {
            dataJson = "[" + JSON.stringify(arrayList[0]) + ",";
          } else if (i < arrayList.length - 1) {
            dataJson += JSON.stringify(arrayList[i]) + ",";
          } else {
            dataJson += JSON.stringify(arrayList[i]) + "]";
          }
        }
      }
      if (arrayList.length == 1) {
        dataJson = "[" + JSON.stringify(arrayList[0]) + "]";
      }

      console.log(dataJson, typeof dataJson);
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/fwebcarinfo",
        headers: {
          DTS: new Date().getTime(),
          http_method: "insert"
        },
        data: { fwebcar: dataJson },
        params: { fwebcar: dataJson },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          let result = response.data.result;
          if (result) {
            vm.$Message.success("添加成功");
          }

          vm.closeCarModal();
          vm.getTableData(1);
        })
        .catch(function(response) {
          vm.$Message.error();
        });
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
    closeCarModal: function() {
      $(".addCarModal").fadeOut();
      this.isDisplayShowCarModal = true;
      this.isShowCarModal = false;
    },
    deleteCar(carNum) {
      let vm = this;
      let dataJson = {
        clbh: carNum
      };
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/fwebcarinfo",
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
    handleSubmit(name) {
      let vm = this;
      let dataJson = {
        clbh: vm.formInline.carNum,
        cllx: vm.formInline.carType,
        cph: vm.formInline.licensePlate,
        cpzl: vm.formInline.licenseType,
        lxr: vm.formInline.contactPerson,
        lxdh: vm.formInline.contactPhone
      };
      this.$refs[name].validate(valid => {
        if (valid) {
          vm.$Message.success("Success!");
          vm.$http({
            method: "post",
            url: "/gm-data/api/interface/fwebcarinfo",
            headers: {
              DTS: new Date().getTime(),
              http_method: "insert"
            },
            data: dataJson,
            params: dataJson,
            dataType: "json",
            crossDomain: true, //== !(document.all),
            cache: false
          })
            .then(function(response) {
              vm.closeCarModal();
            })
            .catch(function(response) {
              vm.$Message.error();
            });
        } else {
          this.$Message.error("Fail!");
        }
      });
    },
    handleReset(name) {
      this.$refs[name].resetFields();
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
        cph: vm.plateNum,
        pageIndex: pageIndex,
        pageSize: vm.pageSize,
        clzt: vm.carStatus
      };
      let dataParams = {
        cph: vm.plateNum,
        pageIndex: pageIndex,
        pageSize: vm.pageSize,
        clzt: vm.carStatus
      };
      this.$http({
        method: "post",
        url: "/gm-data/api/interface/fwebcarinfo",
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



