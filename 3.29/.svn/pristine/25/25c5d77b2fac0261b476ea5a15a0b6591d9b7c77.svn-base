<template>
  <div class="home-index">
    <!-- <img width="100%" src="../../assets/images/homebg.jpeg"> -->
    <el-row :gutter="20">
      <p class="home-title">审方日报  {{ date.year }}年{{ date.month }}月{{ date.date }}日</p>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="16" class="left-col">
        <el-row :gutter="20" class="left-row">
          <el-col :span="12" class="audit-col">
            <el-row class="audit-row">
              <div class="audit-top">
                <img src="../../assets/images/left-top.png" alt="" width="100%" class="top-img">
              </div>
              <p>各医院处方审核情况</p>
              <el-table
                :data="auditDetail.data"
                class="table-data"
              >
                <el-table-column
                  prop="hospitalName"
                  label="医院名称"
                >
                </el-table-column>
                <el-table-column
                  prop="total"
                  label="处方总数">
                </el-table-column>
                <el-table-column
                  prop="checkTotal"
                  label="审核数">
                </el-table-column>
                <el-table-column
                  prop="noPass"
                  label="未通过数">
                </el-table-column>
              </el-table>
              <div class="audit-bottom">
                <img src="../../assets/images/left-bottom.png" alt="" width="100%" class="bottom-img">
              </div>
            </el-row>
          </el-col>
          <el-col :span="12" class="prescription-col">
            <div class="prescription-top">
              <img src="../../assets/images/left-top.png" alt="" width="100%" class="top-img">
            </div>
            <el-row class="hospital-row">
              <el-col :span="12" class="hospital-col">
                <div class="hospital-total">
                  <p>医院总数</p>
                  <p>4</p>
                </div>
              </el-col>
              <el-col :span="12">
                <p>处方总数：35507</p>
                <p>处方审核总数：35874</p>
                <p>处方审核率：97.99%</p>
                <p>处方通过率：92.81%</p>
                <p>处方未通过率：5.18%</p>
              </el-col>
            </el-row>
            <div class="prescription-bottom">
                <img src="../../assets/images/left-bottom.png" alt="" width="100%" class="bottom-img">
            </div>
            <el-row class="map-row">
              <div class="hospital-map">
                <!-- <img src="../../assets/images/homemap.png" alt="" width="100%" height="100%"> -->
                <div
                  v-for="(item, index) in auditDetail.data"
                  :key="index"
                >
                  <div :id="'detail' + item.hospitalId">
                    <p>医院名称：{{ item.hospitalName }}</p>
                    <p>处方总数：{{ item.total }}</p>
                    <p>未通过数：{{ item.noPass }}</p>
                  </div>
                  <div :id="'h' + item.hospitalId"></div>     
                </div>
              </div>
            </el-row>
          </el-col>        
        </el-row>
        <el-row :gutter="20" class="line-row">
          <el-col :span="12">
            <chart
              id="outpatientCharts"
              :options="outpatientCharts"
            />           
          </el-col>
          <el-col :span="12">
            <chart
              id="failCharts"
              :options="failCharts"
            />
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="8" class="daily-col">
        <div class="daily-top">
          <img src="../../assets/images/right-top.png" alt="" width="100%" class="top-img">
        </div>
        <el-row class="daily-row">
          <el-col :span="12">
            <span>审方总数：35874</span>
          </el-col>
          <el-col :span="12">
            <span>审方总率：97.99%</span>
          </el-col>
        </el-row>
        <el-row class="daily-row">
          <el-col :span="12">
            <span>通过数：33976</span>
          </el-col>
          <el-col :span="12">
            <span>通过率：94.71%</span>
          </el-col>
        </el-row>        
        <el-row class="daily-row">
          <el-col :span="12">
            <span>人工审核数：275</span>
          </el-col>
          <el-col :span="12">
            <span>人工审核率：0.77%</span>
          </el-col>
        </el-row>            
        <el-row class="daily-row">
          <el-col :span="12">
            <span>打回数：97</span>
          </el-col>
          <el-col :span="12">
            <span>打回率：0.27%</span>
          </el-col>
        </el-row>                       
        <el-row class="daily-row">
          <el-col :span="12">
            <span>警告数：1526</span>
          </el-col>
          <el-col :span="12">
            <span>警告率：4.25%</span>
          </el-col>
        </el-row>
        <div class="daily-bottom">
          <img src="../../assets/images/right-bottom.png" alt="" width="100%" class="bottom-img">
        </div>
        <el-row>
          <chart
            id="ratioCharts"
            :options="ratioCharts"
          />
        </el-row>        
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { HospitalDataList } from '@/api/home'

export default {
  name: 'home',
  data() {
    return {
      date: {
        year: "",
        month: "",
        date: ""
      },

      // 各医院处方审核情况
      auditDetail: {
        data: [
          {
            checkTotal: 11426, // 审核数
            hospitalId: 1,
            hospitalName: "苏大附一院", // 医院名称
            noPass: 584, // 未通过数
            total: 11659, // 处方总数
          },
          {
            checkTotal: 8326, // 审核数
            hospitalId: 2,
            hospitalName: "苏大附二院", // 医院名称
            noPass: 418, // 未通过数
            total: 8496, // 处方总数
          },
          {
            checkTotal: 7791, // 审核数
            hospitalId: 3,
            hospitalName: "苏大附儿院", // 医院名称
            noPass: 393, // 未通过数
            total: 7951, // 处方总数
          },
          {
            checkTotal: 8331, // 审核数
            hospitalId: 4,
            hospitalName: "苏州市立医院", // 医院名称
            noPass: 503, // 未通过数
            total: 8501, // 处方总数
          },
        ],
        total: 0
      },

      // 处方总数
      total: 0,

      // hospitalDataList分页
      page: {
        pageNo: 1,
        pageSize: 10,
        isPage: true
      },

      // 各医院处方审核情况定时器
      hospitalListTime: ""
    }
  },
  async created() {
    await this.getNowTime();
    // await this.getHospitalDataList();
    await this.setTime();
    
  },
  async mounted() {
    await this.sumTotal();
    await this.forSet();
  },
  beforeDestroy() {
    clearInterval(this.hospitalListTime);
  },
  computed: {
    // 各时段门诊量
    outpatientCharts() {
      var base = +new Date(1968, 9, 3);
      var oneDay = 24 * 3600 * 1000;
      var date = [];
      var data = [Math.random() * 300];
      for (var i = 1; i < 20000; i++) {
        var now = new Date(base += oneDay);
        date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
        data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
      }
      return {
        tooltip: {
          trigger: 'axis',
          position: function (pt) {
            return [pt[0], '10%'];
          }
        },
        title: {
          left: 'center',
          text: '各时段门诊量',
          textStyle: {
            color: '#fff',
            fontWeight: 'normal' 
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: date,
          axisLabel: {
            textStyle: {
              color: '#fff'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#fff',//左边线的颜色
            }
          }
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%'],
          axisLabel: {
            textStyle: {
              color: '#fff'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#fff',//左边线的颜色
            }
          }
        },
        dataZoom: [{
          type: 'inside',
          start: 0,
          end: 10
        }, {
          start: 0,
          end: 10,
          handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
          handleSize: '80%',
          handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
          },
          textStyle: {
            color: '#fff'
          },
          height: '15px'
        }],
        series: [
          {
            name:'门诊量',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                color: '#03a8cb'
            },
            areaStyle: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: '#03a8cb'
                }, {
                    offset: 1,
                    color: '#03a8cb'
                }])
            },
            data: data
          }
        ]
      };
    },
    
    // 各时段处方审核未通过数
    failCharts() {
      var base = +new Date(1968, 9, 3);
      var oneDay = 24 * 3600 * 1000;
      var date = [];

      var data = [Math.random() * 300];

      for (var i = 1; i < 20000; i++) {
        var now = new Date(base += oneDay);
        date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
        data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
      }
    //   console.log(date);
    //   date = [];
    //   date = ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'];
      return {
        tooltip: {
          trigger: 'axis',
          position: function (pt) {
            return [pt[0], '10%'];
          }
        },
        title: {
          left: 'center',
          text: '各时段处方审核未通过数',
          textStyle: {
            color: '#fff',
            fontWeight: 'normal' 
          },
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: date,
          axisLabel: {
            textStyle: {
              color: '#fff'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#fff',//左边线的颜色
            }
          }
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%'],
          axisLabel: {
            textStyle: {
              color: '#fff'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#fff',//左边线的颜色
            }
          }
        },
        dataZoom: [{
          type: 'inside',
          start: 0,
          end: 10
        }, {
          start: 0,
          end: 10,
          handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
          handleSize: '80%',
          handleStyle: {
            color: '#fff',
            shadowBlur: 3,
            shadowColor: 'rgba(0, 0, 0, 0.6)',
            shadowOffsetX: 2,
            shadowOffsetY: 2
          },
          textStyle: {
            color: '#fff'
          },
          height: '15px'
        }],
        series: [
          {
            name:'审核未通过数',
            type:'line',
            smooth:true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                color: '#03a8cb'
            },
            areaStyle: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: '#03a8cb'
                }, {
                    offset: 1,
                    color: '#03a8cb'
                }])
            },
            data: data
          }
        ]
      };        
    },

    // 各情况比例
    ratioCharts() {
      return {
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          x: 'left',
          data:['人工审核率','打回率','警告率','通过率'],
          textStyle: {
            color: '#fff'
          }
        },
        color: ['#40a4f7', '#f97e31', '#15d0ff', '#f76f95'],
        series: [
          {
            name:'比率',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center'
              },
              emphasis: {
                show: false,
                textStyle: {
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data:[
              {value: 0.77, name: '人工审核率'},
              {value: 0.27, name: '打回率'},
              {value: 4.25, name:'警告率'},
              {value: 94.71, name:'通过率'}
            ]
          }
        ]
      }
    }
  },
  methods: {
    // 计算处方总数，正式取接口的返回的处方总数
    sumTotal() {
      this.auditDetail.data.map(item => {
        this.total += item.total;
      });
      this.auditDetail.data.map(item => {
        item.percentage = item.total / this.total;
      })
    },
    // 实现动态地图处方圆
    setRadius(data) {
      let h = document.getElementById('h' + data.hospitalId);
      let detail = document.getElementById('detail' + data.hospitalId);
      detail.setAttribute('style', 'display: none;');
      let myW = Math.round(data.percentage * 50) + 'px';
      h.setAttribute('style', 'width: ' + myW + '; height: ' + myW + '; border-radius: ' + myW + ';');
      h.onmouseover = () => {
        let overW = Math.round(data.percentage * 50 + 2) + 'px';
        h.setAttribute('style', 'width: ' + overW + '; height: ' + overW + '; border-radius: ' + overW + '; box-shadow: 2px 2px 5px #ccc;');
        let left = h.offsetLeft + Math.round(data.percentage * 50 + 2) + 'px';
        let top = h.offsetTop + Math.round(data.percentage * 50 + 2) + 'px';
        detail.setAttribute('style', 'display: block; left: ' + left + '; top: ' + top + ';');
      }
      h.onmouseout = () => {
        h.setAttribute('style', 'width: ' + myW + '; height: ' + myW + '; border-radius: ' + myW + ';');
        detail.setAttribute('style', 'display: none;');
      };
    },
    // 循环，根据数据设置圆
    forSet() {
      if (this.auditDetail.data.length > 0) {
        this.auditDetail.data.map(item => {
          this.setRadius(item);
        })
      }
    },
    getNowTime() {
        let mydate = new Date();
        this.date.year = mydate.getFullYear();
        this.date.month = mydate.getMonth() + 1;
        this.date.date = mydate.getDate();
    },

    // 各医院数据列表
    async getHospitalDataList () {
      const res = await HospitalDataList({
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize,
        isPage: this.page.isPage
      })
      if (res.code === 0) {
        this.auditDetail.data = res.result;
        this.auditDetail.total = res.total;
      } else {
        this.$message.error(res.msg)
      }
    },

    // 3-5秒刷新
    setTime() {
        let _this = this;
        // this.hospitalListTime = setInterval(() => {
        //     this.getHospitalDataList();
        // }, 3000);
    },
  }
}
</script>
<style lang="less">
.home-index {
  width: 100%;
  // height: 100%;
  min-height: 640px;
  background: url("../../assets/images/homebg.jpeg") no-repeat;
  background-size: 100% 100%;
  .home-title {
    font-size: 18px;
    font-weight: 900;
    text-align: center;
    margin-top: 20px;
    color: #fff;
  }
  .el-row {
    margin-left: 10px !important;
    margin-right: 10px !important;
  }
  .left-row.el-row {
    margin-left: -15px !important;
  }
  .left-col.el-col {
    padding-right: 0 !important;
    padding-left: 0 !important;
    .line-row.el-row {
      margin-bottom: 35px;
      .el-col {
        padding: 0 !important;
      }
    }
  }
  .audit-col,
  .prescription-col,
  .daily-col {
    margin-top: 20px;
    p {
      font-size: 20px;
      font-weight: 900;
      color: #fff;
    }
  }
  .prescription-col {
    border-right: 2px dashed #777;
    border-left: 2px dashed #777;
  }
  .audit-col {
    padding-left: 10px !important;
    padding-right: 0 !important;
    text-align: center;
    color: #fff;
    .audit-row.el-row {
      background: url("../../assets/images/left-middle.png") repeat;
      background-size: 100%;
    }
    .audit-top,
    .audit-bottom {
      position: relative;
      .top-img {
        position: absolute;
        top: 0;
        left: 0;
      }
      .bottom-img {
        position: absolute;
        bottom: 0;
        left: 0;
      }
    }
    p {
      margin-bottom: 10px;
      margin-top: 20px;
    }
    .table-data {
      margin-bottom: 20px;
      width: 100%;
      background: transparent;
    }
    .el-table__body-wrapper {
      .el-table__row {
        .cell {
          font-size: 14px;
        }
      }
    }
    .el-table th,
    .el-table tr {
      background-color: transparent !important;
      color: #fff;
      text-align: center;
    }
    .el-table td,
    .el-table th {
      padding: 3px 0;
      text-align: center;
    }
    .el-table--enable-row-hover .el-table__body tr:hover > td {
      background-color: transparent !important;
    }

    .el-table__header-wrapper {
      background-color: transparent !important;
    }
    .el-table td,
    .el-table th.is-leaf {
      border-bottom: 1px solid transparent;
    }
    .cell {
      font-size: 14px;
      font-weight: 900;
    }
    .el-table::before {
      background: transparent;
    }
  }
  .prescription-col {
    .el-row {
      display: flex;
      align-content: center;
      justify-content: center;
    }
    .hospital-row {
      background: url("../../assets/images/left-middle.png") repeat;
      background-size: 100%;     
    }
    .hospital-row.el-row {
      margin-left: 0 !important;
      margin-right: 0 !important;
      padding-top: 10px;
      padding-bottom: 10px;
    }
    .prescription-top,
    .prescription-bottom {
      position: relative;
      .top-img {
        position: absolute;
        top: 0;
        left: 0;
      }
      .bottom-img {
        position: absolute;
        bottom: 0;
        left: 0;
      }
    } 
    .hospital-col {
      text-align: center;
      position: relative;
      .hospital-total {
        text-align: center;
        position: absolute;
        top: 0;
        bottom: 0;
        margin: auto;
        height: 60px;
        width: 100%;
      }
    }
    p {
      font-size: 16px;
      margin-bottom: 10px;
    }
    .map-row {
      margin-top: 50px;
    }
  }
  .daily-col {
    padding: 0 !important;
    .daily-row {
      background: url("../../assets/images/right-middle.png") repeat;
      background-size: 100%;     
    }
    .daily-row.el-row {
      margin-left: 0 !important;
      margin-right: 0 !important;
      padding: 20px 10px;
    }
    .daily-top,
    .daily-bottom {
      position: relative;
      .top-img {
        position: absolute;
        top: -5;
        left: 0;
      }
      .bottom-img {
        position: absolute;
        bottom: -5px;
        left: 0;
      }
    }
    .el-col {
      span {
        color: #fff;
        font-size: 16px;
      }
    }
  }
  #outpatientCharts,
  #failCharts {
    width: 100%;
    height: 300px;
    overflow-x: auto;
    overflow-y: hidden;
    background: #fff;
    margin-bottom: 20px;
  }
  #failCharts {
    margin-left: 20px;
  }
  #ratioCharts {
    width: 100%;
    height: 250px;
    margin-right: 100px;
  }
  .echarts {
    background: transparent !important;
    margin-top: 25px;
    width: 100%;
    canvas {
      width: 100%;
    }
  }

  // 地图圆
  .hospital-map {
    background: url('../../assets/images/homemap.png') no-repeat;
    background-size: 340px 400px;
    width: 100%;
    height: 400px;
    position: relative;
    #h1,
    #h2,
    #h3,
    #h4 {
      background: blue;
      position: absolute;
    }
    #h1 {
      left: 180px;
      top: 230px;  
    }
    #h2 {
      left: 171px;
      top: 245px;
    }
    #h3 {
      left: 208px;
      top: 242px;
    }
    #h4 {
      left: 193px;
      top: 225px;
    }
    #detail1,
    #detail2,
    #detail3,
    #detail4 {
      position: absolute;
      background: #303133;
      color: #fff;
      width: 200px;
      height: auto;
      z-index: 1; 
      font-size: 12px;
      font-weight: initial;
      padding: 10px;
      p {
        margin: 0;
      }
    }
  }
}
</style>
