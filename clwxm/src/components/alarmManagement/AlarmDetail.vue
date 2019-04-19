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
  // margin-left: -185px;
}

.title {
  width: 80px;
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

#alarmDetailTable {
  min-width: 580px;
}
</style>
<template>
  <div style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;">
    <div style="background: #18191D;width: calc(100vw - 320px);max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">告警监控</h5>
      <div>
        <div style="display: flex;min-width: 600px;flex-direction: row;align-items: center;justify-content: space-around;position: relative;margin-left: -30px;margin-top: 25px;margin-bottom: 30px;">
          <div class="searchItem">
            <div class="labelName">车牌号码：</div>
            <Input
              v-model="plateNum"
              placeholder="请输入车牌号"
              class="plateNum"
            />
            <div class="labelName">告警类型：</div>
            <Select
              v-model="alarmType"
              filterable
              clearable
              style="width:240px;"
            >
              <Option
                v-for="item in alarmTypeList"
                :value="item.value"
                :key="item.value"
              >{{ item.label }}</Option>
            </Select>
          </div>
          <div class="buttonList">
            <Button
              type="primary"
              @click="searchList"
            >查询</Button>
          </div>
        </div>
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="tableData"
          id="alarmDetailTable"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
        ></Page>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";

export default {
  name: "alarmMonitoring",
  data() {
    return {
      alarmType: "",
      plateNum: "",
      columns: [
        {
          title: "车牌号码",
          key: "cph",
          align: "left",
          minWidth: 100
          // fixed: "left"
        },
        // {
        //   title: "车牌种类",
        //   key: "cpzlshow",
        //   minWidth: 100,
        //   align: "center"
        // },
        {
          title: "告警类型",
          key: "gjlxshow",
          minWidth: 100,
          align: "left"
        },
        {
          title: "告警级别",
          key: "gjjbshow",
          minWidth: 100,
          align: "left"
        },
        {
          title: "行驶速度(km/h)",
          key: "dqsd",
          minWidth: 100,
          align: "left"
        },
        {
          title: "行驶时间(h)",
          key: "xssj",
          minWidth: 100,
          align: "left"
        },
        {
          title: "告警时间",
          key: "rksj",
          minWidth: 150,
          align: "center"
          // fixed: "right"
        }
      ],
      tableData: [],
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: [],
      alarmTypeList: []
    };
  },
  mounted() {
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      this.getToken();
    }
    let windowHeight = window.innerHeight;
    if (windowHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }
    if (window.innerHeight < 900) {
      $("#alarmDetailTable").css(
        "height",
        (window.innerHeight * 450) / 980 + "px"
      );
      $(".ivu-table-body").css(
        "height",
        $("#alarmDetailTable").height() - 40 + "px"
      );
    }
    this.getTableData(this.pageIndex);
    this.getAlarmType();
  },
  methods: {
    getAlarmType: function() {
      let vm = this;
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/codes/gjlx",
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
          vm.alarmTypeList = result.map(function(item) {
            return { label: item.dmsm1, value: item.dmz };
          });
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    changePage(index) {
      this.getTableData(index);
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
        gjlx: vm.alarmType
      };
      let dataParams = {
        cph: vm.plateNum,
        pageIndex: pageIndex,
        pageSize: vm.pageSize,
        gjlx: vm.alarmType
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/alarminfo",
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
    }
  }
};
</script>



