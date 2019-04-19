<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 100px;
  text-align: center;
  display: flex;
  align-items: center;
}

.cdzmc {
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
  margin-top: 5px;
}

.title {
  width: 100px;
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
}

.ivu-table-wrapper {
  margin: 10px;
}

#chargeTable {
  min-width: 580px;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="chargeStatistics"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">充电站统计</h5>
      <div style="display: flex;min-width: 600px;flex-direction: row;flex-wrap: wrap;align-items: center;justify-content: space-around;position: relative;margin-left: -30px;;margin-top: 15px;">
        <div class="searchItem">
          <div class="labelName">充电站名称：</div>
          <Input
            v-model="cdzmc"
            placeholder="请输入充电站名称"
            class="cdzmc"
          />
        </div>
        <Button
          type="primary"
          @click="searchList"
        >查询</Button>
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="chargeDetailList"
          id="chargeTable"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
          style="margin-top:0px;"
        ></Page>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "chargeStatistics",
  data() {
    return {
      cdzmc: "",
      chargeDetailList: [],
      columns: [
        {
          title: "充电站位置",
          key: "dlwz",
          // fixed: "left",
          minWidth: 100,
          align: "left"
        },
        {
          title: "充电站名称",
          key: "cdzmc",
          minWidth: 100,
          align: "left"
        },
        {
          title: "总充电量",
          key: "zcdl",
          minWidth: 100,
          align: "left"
        },
        {
          title: "正在充电",
          key: "zzcd",
          minWidth: 100,
          align: "left"
        }
      ],
      tableData: [],
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: []
    };
  },
  mounted() {
    this.getChargeData(this.pageIndex);
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
    if (window.innerHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }

    if (window.innerWidth < 900) {
      $("#chargeStatistics").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $(".ivu-page").css("margin-top", "0px !important");
      $("#chargeTable").css("height", (window.innerHeight * 450) / 980 + "px");
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
  },
  methods: {
    changePage(index) {
      this.getChargeData(index);
    },
    searchList() {
      this.getChargeData(1);
    },
    getChargeData: function(pageIndex) {
      let dataJson = { cdzmc: this.cdzmc, pageSize: this.pageSize };
      let dataParams = { cdzmc: this.cdzmc, pageSize: this.pageSize };
      let vm = this;
      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/charge/" + pageIndex,
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        data: dataJson,
        params: dataParams,
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          if (resolve.data.code == "10003") {
            localStorage.clear();
            axios.defaults.headers.common["USER_TOKEN"] = "";
            this.getToken();
          }
          let chargeList = resolve.data.result.array;
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
          vm.tableDataLength = resolve.data.result.count;
          return Promise.resolve(vm.chargeDetailList);
        },
        reject => {
          console.log("请求失败", reject);
          return Promise.reject(reject);
        }
      );
    }
  }
};
</script>



