<style lang="less" scoped>
</style>

<template>
  <div style="display:flex;flex-direction: column;height:100%;width:25vw;min-height:750px;margin-left: 0px;min-width:260px;" id="charts">
      <div id="lineChartsDiv" style="width:100%;height:28%;margin-bottom:20px;background: rgb(24, 25, 29);" ><div id="lineCharts"></div></div>
      <div id="barChartsDiv" style="width:100%;height:28%;margin-bottom:20px;background: rgb(24, 25, 29);"><div id="barCharts"></div></div>
      <div id="pieChartsDiv" style="width:100%;height:40%;background: rgb(24, 25, 29);"><div id="pieCharts"></div></div>
  </div>
</template>

<script>
import echarts from "echarts";
import $ from "jquery";

export default {
  name: "charts",
  data() {
    return {
      totalCarNum: [],
      activeCarNum: [],
      newCarNum: [],
      everyMileageNum: [],
      activeCarNumList: [],
      activeCarList: []
    };
  },
  mounted() {
    let vm = this;
    this.getCarNum();
    this.getBarData().then(resolve => {
      vm.initBarChart();
    });
    this.getPieData().then(resolve => {
      vm.initPieCharts();
    });
  },
  methods: {
    getCarNum: function() {
      let vm = this;
      let p1 = this.$http({
        method: "post",
        url: "/gm-data/api/interface/countcarweek",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          let result = resolve.data.result;
          result = result.map(item => {
            return item.countnumber;
          });
          vm.totalCarNum = result;
          //return Promise.resolve(result);
        },
        reject => {
          console.log("请求失败", reject);
          //return Promise.reject(reject);
        }
      );
      let p2 = vm
        .$http({
          method: "post",
          url: "/gm-data/api/interface/activecarday",
          headers: {
            DTS: new Date().getTime(),
            http_method: "search"
          },
          dataType: "json",
          crossDomain: true, //== !(document.all),
          cache: false
        })
        .then(
          resolve => {
            let activeResult = resolve.data.result;
            activeResult = activeResult.map(item => {
              return item.countnumber;
            });
            vm.activeCarNum = activeResult;
            // return Promise.resolve(activeResult);
          },
          reject => {
            console.log("请求失败", reject);
            // return Promise.reject(reject);
          }
        );

      let p3 = vm
        .$http({
          method: "post",
          url: "/gm-data/api/interface/newcarday",
          headers: {
            DTS: new Date().getTime(),
            http_method: "search"
          },
          dataType: "json",
          crossDomain: true, //== !(document.all),
          cache: false
        })
        .then(
          resolve => {
            let newResult = resolve.data.result;
            newResult = newResult.map(item => {
              return item.countnumber;
            });
            vm.newCarNum = newResult;
            return Promise.resolve(newResult);
          },
          reject => {
            console.log("请求失败", reject);
            return Promise.reject(reject);
          }
        );
      Promise.all([p1, p2, p3]).then(result => {
        vm.initLineChart();
      });
    },
    getDate: function(dateNum) {
      var currentDate = new Date();
      var date = new Date(currentDate);
      date.setDate(currentDate.getDate() + dateNum);
      var time = date.getMonth() + 1 + "-" + date.getDate();
      return time;
    },
    initLineChart: function() {
      let vm = this;
      var lineCharts = echarts.init(document.getElementById("lineCharts"));
      let option = {
        // color: ["orangered", "yellow", "lightskyblue"],
        title: {
          text: "每日车辆数统计",
          textStyle: {
            fontSize: 14,
            align: "center",
            color: "#fff"
          },
          left: "left"
        },
        tooltip: {
          trigger: "axis"
        },
        legend: {
          data: ["车辆总数", "活跃车辆数", "新增车辆数"],
          top: "10%",
          textStyle: {
            fontSize: 14,
            align: "center",
            color: "#fff"
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        // toolbox: {
        //   feature: {
        //     saveAsImage: {}
        //   }
        // },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [
            vm.getDate(-6),
            vm.getDate(-5),
            vm.getDate(-4),
            vm.getDate(-3),
            vm.getDate(-2),
            vm.getDate(-1),
            vm.getDate(0)
          ],
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: "#fff"
            }
          }
        },
        yAxis: {
          type: "value",
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: "#fff"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#333"
            }
          }
        },
        series: [
          {
            name: "车辆总数",
            type: "line",
            smooth: true,
            data: vm.totalCarNum
          },
          {
            name: "活跃车辆数",
            type: "line",
            smooth: true,
            data: vm.activeCarNum
          },
          {
            name: "新增车辆数",
            type: "line",
            smooth: true,
            data: vm.newCarNum
          }
        ]
      };
      lineCharts.setOption(option);
      $("#lineCharts").css("height", $("#lineChartsDiv").height() + "px");
      $("#lineCharts").css("width", $("#lineChartsDiv").width() + "px");
      lineCharts.resize();

      // window.onresize = function() {
      //   $("#charts").css("width", (window.innerWidth - 320) * 0.3);
      //   $("#lineCharts").css("height", $("#lineChartsDiv").height() + "px");
      //   $("#lineCharts").css("width", $("#pieChartsDiv").width() + "px");
      //   lineCharts.resize();
      // };
    },
    getBarData: function() {
      let vm = this;
      return this.$http({
        method: "post",
        url: "/gm-data/api/interface/totallcsday",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          let result = resolve.data.result;
          result = result.map(item => {
            return item.lcs;
          });
          vm.everyMileageNum = result;
          return Promise.resolve(result);
        },
        reject => {
          console.log("请求失败", reject);
          return Promise.reject(reject);
        }
      );
    },
    initBarChart: function() {
      let vm = this;
      var barCharts = echarts.init(
        document.getElementById("barCharts"),
        "light"
      );
      let option = {
        title: {
          text: "每日里程统计",
          textStyle: {
            fontSize: 14,
            align: "center",
            color: "#fff"
          },
          left: "left"
        },
        color: ["#3398DB"],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        xAxis: [
          {
            type: "category",
            data: [
              vm.getDate(-6),
              vm.getDate(-5),
              vm.getDate(-4),
              vm.getDate(-3),
              vm.getDate(-2),
              vm.getDate(-1),
              vm.getDate(0)
            ],
            axisTick: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#fff"
              }
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            axisTick: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#fff"
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "#333"
              }
            }
          }
        ],
        series: [
          {
            name: "在线车辆当天总里程数（KM）",
            type: "bar",
            barWidth: "60%",
            data: vm.everyMileageNum
          }
        ]
      };
      barCharts.setOption(option);
      $("#barCharts").css("height", $("#barChartsDiv").height() + "px");
      $("#barCharts").css("width", $("#barChartsDiv").width() + "px");
      barCharts.resize();
      // window.onresize = function() {
      //   $("#charts").css("width", (window.innerWidth - 320) * 0.3);
      //   $("#barCharts").css("height", $("#barChartsDiv").height() + "px");
      //   $("#barCharts").css("width", $("#barChartsDiv").width() + "px");
      //   barCharts.resize();
      // };
    },
    getPieData: function() {
      let vm = this;
      return this.$http({
        method: "post",
        url: "/gm-data/api/interface/activecarpro",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      }).then(
        resolve => {
          let result = resolve.data.result;
          vm.activeCarList = result.map(item => {
            return { value: item.countnumber, name: item.sssfshow };
          });
          result = result.map(item => {
            return item.sssfshow;
          });
          vm.activeCarNumList = result;
          return Promise.resolve(result);
        },
        reject => {
          console.log("请求失败", reject);
          return Promise.reject(reject);
        }
      );
    },
    initPieCharts: function() {
      let vm = this;
      var pieCharts = echarts.init(
        document.getElementById("pieCharts"),
        "light"
      );
      let option = {
        title: {
          text: "一周省份车辆活跃排名",
          textStyle: {
            fontSize: 14,
            align: "center",
            color: "#fff"
          },
          left: "left"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          data: vm.activeCarNumList,
          textStyle: {
            color: "#fff"
          },
          selectedMode: true,
          y: "bottom"
        },
        series: [
          {
            name: "省份活跃车辆",
            type: "pie",
            radius: ["40%", "60%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: "30",
                  fontWeight: "bold"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: vm.activeCarList
          }
        ]
      };
      pieCharts.setOption(option);
      $("#pieCharts").css("height", $("#pieChartsDiv").height() + "px");
      $("#pieCharts").css("width", $("#pieChartsDiv").width() + "px");
      pieCharts.resize();

      // window.onresize = function() {
      //   $("#charts").css("width", (window.innerWidth - 320) * 0.3);
      //   $("#pieCharts").css("height", $("#pieChartsDiv").height() + "px");
      //   $("#pieCharts").css("width", $("#pieChartsDiv").width() + "px");
      //   pieCharts.resize();
      // };
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

