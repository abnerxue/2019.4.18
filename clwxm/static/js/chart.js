/**
 * 图表控制器
 */
var Chart = {
    //入口
    init: function () {
        this.fullScreen();
        var self = this;
        $(window).resize(function () {
            self.fullScreen();
        });


        //各省扶贫投入分布情况
        this.createMapChart();

        //折线图
        this.createLineChart();

        //柱状图
        this.createColumnChart();

        //饼状图
        this.createPieChart();
    },
    /**
     * 创建 中国地图
     */
    createMapChart: function () {
        window.myChart = echarts.init(document.getElementById("myChart"));

        var geoCoordMap = {
            '上海': [121.4648, 31.2891],
            '东莞': [113.8953, 22.901],
            '东营': [118.7073, 37.5513],
            '中山': [113.4229, 22.478],
            '临汾': [111.4783, 36.1615],
            '临沂': [118.3118, 35.2936],
            '丹东': [124.541, 40.4242],
            '丽水': [119.5642, 28.1854],
            '乌鲁木齐': [87.9236, 43.5883],
            '佛山': [112.8955, 23.1097],
            '保定': [115.0488, 39.0948],
            '兰州': [103.5901, 36.3043],
            '包头': [110.3467, 41.4899],
            '苏州': [116.4551, 40.2539],
            '北海': [109.314, 21.6211],
            '南京': [118.8062, 31.9208],
            '南宁': [108.479, 23.1152],
            '南昌': [116.0046, 28.6633],
            '南通': [121.1023, 32.1625],
            '厦门': [118.1689, 24.6478],
            '台州': [121.1353, 28.6688],
            '合肥': [117.29, 32.0581],
            '呼和浩特': [111.4124, 40.4901],
            '咸阳': [108.4131, 34.8706],
            '哈尔滨': [127.9688, 45.368],
            '唐山': [118.4766, 39.6826],
            '嘉兴': [120.9155, 30.6354],
            '大同': [113.7854, 39.8035],
            '大连': [122.2229, 39.4409],
            '天津': [117.4219, 39.4189],
            '太原': [112.3352, 37.9413],
            '威海': [121.9482, 37.1393],
            '宁波': [121.5967, 29.6466],
            '宝鸡': [107.1826, 34.3433],
            '宿迁': [118.5535, 33.7775],
            '常州': [119.4543, 31.5582],
            '广州': [113.5107, 23.2196],
            '廊坊': [116.521, 39.0509],
            '延安': [109.1052, 36.4252],
            '张家口': [115.1477, 40.8527],
            '徐州': [117.5208, 34.3268],
            '德州': [116.6858, 37.2107],
            '惠州': [114.6204, 23.1647],
            '成都': [103.9526, 30.7617],
            '扬州': [119.4653, 32.8162],
            '承德': [117.5757, 41.4075],
            '拉萨': [91.1865, 30.1465],
            '无锡': [120.3442, 31.5527],
            '日照': [119.2786, 35.5023],
            '昆明': [102.9199, 25.4663],
            '杭州': [119.5313, 29.8773],
            '枣庄': [117.323, 34.8926],
            '柳州': [109.3799, 24.9774],
            '株洲': [113.5327, 27.0319],
            '武汉': [114.3896, 30.6628],
            '汕头': [117.1692, 23.3405],
            '江门': [112.6318, 22.1484],
            '沈阳': [123.1238, 42.1216],
            '沧州': [116.8286, 38.2104],
            '河源': [114.917, 23.9722],
            '泉州': [118.3228, 25.1147],
            '泰安': [117.0264, 36.0516],
            '泰州': [120.0586, 32.5525],
            '济南': [117.1582, 36.8701],
            '济宁': [116.8286, 35.3375],
            '海口': [110.3893, 19.8516],
            '淄博': [118.0371, 36.6064],
            '淮安': [118.927, 33.4039],
            '深圳': [114.5435, 22.5439],
            '清远': [112.9175, 24.3292],
            '温州': [120.498, 27.8119],
            '渭南': [109.7864, 35.0299],
            '湖州': [119.8608, 30.7782],
            '湘潭': [112.5439, 27.7075],
            '滨州': [117.8174, 37.4963],
            '潍坊': [119.0918, 36.524],
            '烟台': [120.7397, 37.5128],
            '玉溪': [101.9312, 23.8898],
            '珠海': [113.7305, 22.1155],
            '盐城': [120.2234, 33.5577],
            '盘锦': [121.9482, 41.0449],
            '石家庄': [114.4995, 38.1006],
            '福州': [119.4543, 25.9222],
            '秦皇岛': [119.2126, 40.0232],
            '绍兴': [120.564, 29.7565],
            '聊城': [115.9167, 36.4032],
            '肇庆': [112.1265, 23.5822],
            '舟山': [122.2559, 30.2234],
            '苏州': [120.6519, 31.3989],
            '莱芜': [117.6526, 36.2714],
            '菏泽': [115.6201, 35.2057],
            '营口': [122.4316, 40.4297],
            '葫芦岛': [120.1575, 40.578],
            '衡水': [115.8838, 37.7161],
            '衢州': [118.6853, 28.8666],
            '西宁': [101.4038, 36.8207],
            '西安': [109.1162, 34.2004],
            '贵阳': [106.6992, 26.7682],
            '连云港': [119.1248, 34.552],
            '邢台': [114.8071, 37.2821],
            '邯郸': [114.4775, 36.535],
            '郑州': [113.4668, 34.6234],
            '鄂尔多斯': [108.9734, 39.2487],
            '重庆': [107.7539, 30.1904],
            '金华': [120.0037, 29.1028],
            '铜川': [109.0393, 35.1947],
            '银川': [106.3586, 38.1775],
            '镇江': [119.4763, 31.9702],
            '长春': [125.8154, 44.2584],
            '长沙': [113.0823, 28.2568],
            '长治': [112.8625, 36.4746],
            '阳泉': [113.4778, 38.0951],
            '青岛': [120.4651, 36.3373],
            '韶关': [113.7964, 24.7028]
        };

        //各省份对应的简称
        var provinceToSimpleName = {
            "北京": "京",
            "天津": "津",
            "河北": "冀",
            "山西": "晋",
            "内蒙古": "蒙",
            "辽宁": "辽",
            "吉林": "吉",
            "黑龙江": "黑",
            "上海": "沪",
            "江苏": "苏",
            "浙江": "浙",
            "安徽": "皖",
            "福建": "闽",
            "江西": "赣",
            "山东": "鲁",
            "河南": "豫",
            "湖北": "鄂",
            "湖南": "湘",
            "广东": "粤",
            "广西": "桂",
            "海南": "琼",
            "重庆": "渝",
            "四川": "蜀",
            "贵州": "黔",
            "云南": "滇",
            "西藏": "藏",
            "陕西": "陕",
            "甘肃": "甘",
            "青海": "青",
            "宁夏": "宁",
            "新疆": "新",
            "香港": "港",
            "澳门": "澳",
            "台湾": "台"
        };

        //各省份对应的范围坐标
        var provinceToRangePoint = {
            "北京": [
                [116.100058, 39.801033],
                [116.741089, 40.040085]
            ],
            "天津": [
                [116.884693, 38.981122],
                [117.529748, 39.223031]
            ],
            "河北": [
                [109.499719, 42.238129],
                [119.489451, 37.107118]
            ],
            "黑龙江": [
                [134.723294, 48.366754],
                [124.847461, 45.546103]
            ],
            "吉林": [
                [130.403445, 46.256748],
                [123.489451, 40.912697]
            ],
            "辽宁": [
                [125.499719, 41.238129],
                [120.089451, 40.207118]
            ],
            "内蒙古": [
                [121.499719, 53.238129],
                [101.489451, 38.107118]
            ],
            "新疆": [
                [88.163719, 49.038129],
                [79.489451, 34.107118]
            ],
            "西藏": [
                [89.708719, 36.068129],
                [86.089451, 28.107118]
            ],
            "青海": [
                [98.760719, 39.038129],
                [95.929451, 31.707118]
            ],
            "甘肃": [
                [97.030719, 42.238129],
                [104.589451, 32.868018]
            ],
            "四川": [
                [106.559719, 33.308129],
                [96.749451, 27.247118]
            ],
            "重庆": [
                [109.619719, 31.758129],
                [106.749451, 28.247118]
            ],
            "云南": [
                [105.069719, 27.4848129],
                [99.109451, 23.167118]
            ],
            "广西": [
                [111.309719, 26.318129],
                [108.219451, 21.767118]
            ],
            "海南": [
                [110.389719, 20.048129],
                [109.099451, 18.417118]
            ],
            "广东": [
                [114.749719, 25.338129],
                [110.019451, 20.577118]
            ],
            "香港": [
                [114.449719, 22.548129],
                [113.879451, 22.167118]
            ],
            "澳门": [
                [113.579719, 22.248129],
                [113.549451, 22.097118]
            ],
            "福建": [
                [119.649719, 27.588129],
                [117.059451, 23.997118]
            ],
            "江西": [
                [118.019719, 29.578129],
                [114.079451, 25.577118]
            ],
            "湖南": [
                [113.599719, 29.698129],
                [111.599451, 24.717118]
            ],
            "湖北": [
                [114.559719, 31.598129],
                [109.219451, 29.367118]
            ],
            "陕西": [
                [111.029719, 39.458129],
                [107.949451, 32.257118]
            ],
            "宁夏": [
                [106.739719, 39.348129],
                [106.129451, 35.417118]
            ],
            "山西": [
                [114.279719, 40.618129],
                [110.579451, 34.707118]
            ],
            "河南": [
                [115.239719, 36.088129],
                [114.169451, 31.927118]
            ],
            "山东": [
                [122.249719, 37.538129],
                [116.289451, 34.717118]
            ],
            "安徽": [
                [116.649719, 34.398129],
                [116.269451, 30.007118]
            ],
            "浙江": [
                [121.219719, 30.728129],
                [119.159451, 27.557118]
            ],
            "江苏": [
                [119.199719, 35.098129],
                [119.619451, 31.217118]
            ],
            "上海": [
                [121.939719, 31.568129],
                [121.299451, 30.777118]
            ],
            "台湾": [
                [121.929719, 25.118129],
                [120.599451, 22.437118]
            ],
            "贵州": [
                [108.499719, 28.238129],
                [104.489451, 25.107118]
            ],
        };

        // var BJData = [
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '上海',
        //         value: 95
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '广州',
        //         value: 90
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '大连',
        //         value: 80
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '南宁',
        //         value: 70
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '南昌',
        //         value: 60
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '拉萨',
        //         value: 50
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '长春',
        //         value: 40
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '包头',
        //         value: 30
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '重庆',
        //         value: 20
        //     }],
        //     [{
        //         name: '苏州'
        //     }, {
        //         name: '常州',
        //         value: 10
        //     }]
        // ];

        var planePath = "path://M917.965523 917.331585c0 22.469758-17.891486 40.699957-39.913035 40.699957-22.058388 0-39.913035-18.2302-39.913035-40.699957l-0.075725-0.490164-1.087774 0c-18.945491-157.665903-148.177807-280.296871-306.821991-285.4748-3.412726 0.151449-6.751774 0.562818-10.240225 0.562818-3.450589 0-6.789637-0.410346-10.202363-0.524956-158.606321 5.139044-287.839661 127.806851-306.784128 285.436938l-1.014096 0 0.075725 0.490164c0 22.469758-17.854647 40.699957-39.913035 40.699957s-39.915082-18.2302-39.915082-40.699957l-0.373507-3.789303c0-6.751774 2.026146-12.903891 4.91494-18.531052 21.082154-140.712789 111.075795-258.241552 235.432057-312.784796C288.420387 530.831904 239.989351 444.515003 239.989351 346.604042c0-157.591201 125.33352-285.361213 279.924387-285.361213 154.62873 0 279.960203 127.770012 279.960203 285.361213 0 97.873098-48.391127 184.15316-122.103966 235.545644 124.843356 54.732555 215.099986 172.863023 235.808634 314.211285 2.437515 5.290493 4.01443 10.992355 4.01443 17.181311L917.965523 917.331585zM719.822744 346.679767c0-112.576985-89.544409-203.808826-199.983707-203.808826-110.402459 0-199.944821 91.232864-199.944821 203.808826s89.542362 203.808826 199.944821 203.808826C630.278335 550.488593 719.822744 459.256752 719.822744 346.679767z";
        var convertData = function (data) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var dataItem = data[i];
                var fromCoord = geoCoordMap[dataItem[0].name];
                var toCoord = geoCoordMap[dataItem[1].name];
                if (fromCoord && toCoord) {
                    res.push({
                        fromName: dataItem[0].name,
                        toName: dataItem[1].name,
                        coords: [fromCoord, toCoord]
                    });
                }
            }
            return res;
        };

        var color = ["#ff5929", "#00cdd9", "#036bff"];
        var series = [],
            //省份名称
            province = ["北京", "天津", "上海", "重庆", "河北", "河南", "云南", "辽宁", "黑龙江", "湖南", "安徽", "山东", "新疆", "江苏", "浙江", "江西", "湖北", "广西", "甘肃", "山西", "内蒙古", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏", "四川", "宁夏", "海南", "台湾", "香港", "澳门"],
            city = ["北京", "天津", "上海", "重庆", "", "河南", "云南", "辽宁", "黑龙江", "湖南", "安徽", "山东", "新疆", "江苏", "浙江", "江西", "湖北", "广西", "甘肃", "山西", "内蒙古", "陕西", "吉林", "福建", "贵州", "广东", "青海", "西藏", "四川", "宁夏", "海南", "台湾", "香港", "澳门"];


        /**
         * 随机生成地图数据
         */
        function getRandomData() {
            function GetRandomNum(Min, Max) {
                var Range = Max - Min;
                var Rand = Math.random();
                return (Min + Math.round(Rand * Range));
            }

            var seriesData = [];
            province.forEach(function (item) {
                seriesData.push({
                    name: item.toString(),
                    value: GetRandomNum(0, 100)
                });
            });

            return seriesData;
        }

        //区域染色的配置
        series.push({
            name: '',
            type: 'map',
            zlevel: 3,
            geoIndex: 0,
            rippleEffect: {
                brushType: 'stroke'
            },
            label: {
                normal: {
                    show: true,
                    position: 'right',
                    formatter: '{b}'
                }
            },
            symbolSize: function (val) {
                return val[2] / 8;
            },
            itemStyle: {
                normal: {
                    color: '#fff000'
                }
            },
            data: getRandomData()
        });


        //线条走势数据
        // [
        //     ['苏州', BJData]
        // ].forEach(function(item, i) {
        //     series.push({ //线
        //         name: item[0],
        //         //                    name: item[0] + ' Top10',
        //         type: 'lines',
        //         zlevel: 1,
        //         effect: {
        //             show: true,
        //             period: 6,
        //             trailLength: 0.7,
        //             color: '#fff',
        //             symbolSize: 3
        //         },
        //         lineStyle: {
        //             normal: {
        //                 color: color[i],
        //                 width: 0,
        //                 curveness: 0.2
        //             }
        //         },
        //         data: convertData(item[1])
        //     }, { //移动 点
        //         name: item[0],
        //         type: 'lines',
        //         zlevel: 2,
        //         effect: {
        //             show: true,
        //             period: 6,
        //             trailLength: 0,
        //             symbol: planePath,
        //             symbolSize: 15
        //         },
        //         lineStyle: {
        //             normal: {
        //                 color: color[i],
        //                 width: 1,
        //                 opacity: 0.4,
        //                 curveness: 0.2
        //             }
        //         },
        //         data: convertData(item[1])
        //     }, { //省份圆点
        //         name: item[0],
        //         type: 'effectScatter',
        //         coordinateSystem: 'geo',
        //         zlevel: 2,
        //         rippleEffect: {
        //             brushType: 'stroke'
        //         },
        //         label: {
        //             normal: {
        //                 show: true,
        //                 position: 'right',
        //                 formatter: '{b}'
        //             }
        //         },
        //         symbolSize: function(val) {
        //             return val[2] / 4;
        //         },
        //         itemStyle: {
        //             normal: {
        //                 color: color[i]
        //             }
        //         },
        //         data: item[1].map(function(dataItem) {
        //             return {
        //                 name: dataItem[1].name,
        //                 value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
        //             };
        //         })
        //     });
        // });

        var option = {
            backgroundColor: '#0c0c28',
            title: {
                text: '',
                subtext: '',
                left: 'center',
                textStyle: {
                    color: '#fff'
                }
            },
            visualMap: {
                show: true,
                min: 0,
                max: 100,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],
                seriesIndex: [0],
                // inRange: {
                //     color: ['#f3d999', '#ea7e53'] //有值地图块颜色区间
                // },
                calculable: true,
                textStyle: {
                    color: "#fff"
                },
                realtime: true,
                hoverLink: true
            },
            tooltip: {
                trigger: 'item',
                formatter: function (params) {
                    if (params.seriesIndex == 2 || params.seriesIndex == 5 || params.seriesIndex == 8) {
                        return params.name + '<br>' + params.seriesName + ':' + params.data.value[2] + ' 人次';
                    } else if (params.seriesIndex == 1 || params.seriesIndex == 4 || params.seriesIndex == 7) {
                        return params.data.fromName + '→' + params.data.toName;
                    }
                }
            },
            legend: {
                show: false,
                orient: 'vertical',
                top: 'bottom',
                left: 'right',
                data: ['苏州'],
                textStyle: {
                    color: '#fff'
                },
                selectedMode: 'single'
            },
            geo: {
                map: 'china',
                label: {
                    emphasis: {
                        show: true,
                        textStyle: {
                            color: "#fff"
                        }
                    },
                    normal: {
                        show: true,
                        textStyle: {
                            color: "#fff"
                        },
                        formatter: function (v) {
                            if (v) {
                                return provinceToSimpleName[province[v.name]];
                            }
                        }
                    }
                },
                roam: false,
                // itemStyle: {
                //     normal: {
                //         areaColor: '#0c0c28',
                //         borderColor: '#fff'
                //     },
                //     emphasis: {
                //         areaColor: '#0c0c28'
                //     }
                // }
            },
            series: series
        };

        myChart.setOption(option);

        //地图点击事件
        myChart.on("click", function (params) {
            //console.log(params);
            layer.ready(function () {
                layer.open({
                    type: 1,
                    title: params.name + "全省地图",
                    maxmin: true,
                    area: ['650px', '500px'],
                    content: '<div id="myMap" style="height:100%;width:100%;"></div>',
                    success: function () {
                        // 百度地图API功能
                        var map = new BMap.Map("myMap");
                        map.enableScrollWheelZoom();
                        var city = params.name;
                        if (city === '吉林') {
                            city = '吉林省'
                        };
                        if (city != "") {
                            map.centerAndZoom(city, 7); // 用城市名设置地图中心点

                            function getBoundary() {
                                var bdary = new BMap.Boundary();
                                bdary.get(city, function (rs) { //获取行政区域                                    
                                    map.clearOverlays(); //清除地图覆盖物       
                                    var count = rs.boundaries.length; //行政区域的点有多少个
                                    if (count === 0) {
                                        alert('未能获取当前输入行政区域');
                                        return;
                                    }
                                    var pointArray = [];
                                    for (var i = 0; i < count; i++) {
                                        var ply = new BMap.Polygon(rs.boundaries[i], {
                                            strokeWeight: 2,
                                            strokeColor: "#ff0000"
                                        }); //建立多边形覆盖物
                                        map.addOverlay(ply); //添加覆盖物
                                        pointArray = pointArray.concat(ply.getPath());
                                    }
                                });
                            }

                            //设置显示范围
                            var points = provinceToRangePoint[city];
                            if (points) {
                                var b = new BMap.Bounds(new BMap.Point(points[0][0], points[0][1]), new BMap.Point(points[1][0], points[1][1]));
                                try {
                                    BMapLib.AreaRestriction.setBounds(map, b);
                                } catch (e) {
                                    alert(e);
                                }
                            } else {
                                console.info(city + "未设置坐标范围");
                            }

                            setTimeout(function () {
                                getBoundary();
                            }, 1);
                        }
                    }
                });
            });
        });

        //随机变化地图染色
        setInterval(function () {
            var seriesData = getRandomData();
            var optionNew = myChart.getOption();
            optionNew.series[0].data = seriesData;
            myChart.setOption(optionNew);
        }, 5000); //5秒钟更换一次
    },
    /**
     *  曲线图
     */
    createLineChart: function () {
        var myChart = echarts.init(document.getElementById("myXMQS"));
        var option = {
            color: ["#ff5929", "#00cdd9", "#036bff"],
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['车辆总数', '新增客户数'],
                y: 'bottom',
                textStyle: {
                    color: "#fff"
                }
            },
            toolbox: {
                show: false,
                feature: {
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    magicType: {
                        show: true,
                        type: ['line', 'bar']
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            xAxis: [{
                type: 'category',
                data: ['2017-Q1', '2017-Q2', '2017-Q3', '2017-Q4'],
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: "#fff"
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: "#fff"
                    }
                },
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: "#333"
                    }
                }
            }],
            grid: {
                y: 15,
                x: 50,
                x2: 0,
                y2: 50
            },
            series: [{
                    name: '车辆总数',
                    type: 'line',
                    smooth: true,
                    data: [100, 600, 1200, 1800]
                },
                {
                    name: '新增客户数',
                    type: 'line',
                    smooth: true,
                    data: [300, 700, 1300, 2000]
                }
            ]
        };
        myChart.setOption(option);
    },
    /**
     * 创建柱状图
     */
    createColumnChart: function () {
        var myChart = echarts.init(document.getElementById("myXMZJ"));
        var option = {
            color: ["#00cdd9"],
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['营业额'],
                y: 'bottom',
                textStyle: {
                    color: "#fff"
                }
            },
            toolbox: {
                show: false,
                feature: {
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    magicType: {
                        show: true,
                        type: ['line', 'bar']
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            xAxis: [{
                type: 'category',
                data: ['1百万一下', '1百万-5百万', '5百万-1千万', '1千万-5千万', '5千万以上'],
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    textStyle: {
                        color: "#fff",
                        fontSize: 10
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: "#fff"
                    }
                },
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: "#333"
                    }
                }
            }],
            grid: {
                y: 15,
                x: 50,
                x2: 0,
                y2: 50
            },
            series: [{
                name: '营业额',
                type: 'bar',
                barWidth: 20,
                data: [900, 400, 800, 300, 400]
            }]
        };
        myChart.setOption(option);
    },
    /**
     * 创建饼状图
     */
    createPieChart: function () {
        var myChart = echarts.init(document.getElementById("myLSQK"));

        function getData(percent) {
            return [{
                value: percent,
                name: percent,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#00B2EE'
                        }, {
                            offset: 1,
                            color: '#00DDE6'
                        }])
                    }
                }
            }, {
                value: 1 - percent,
                itemStyle: {
                    normal: {
                        color: 'transparent'
                    }
                }
            }];
        }

        placeHolderStyle = {
            normal: {
                label: {
                    show: false,
                },
                labelLine: {
                    show: false,
                }


            }
        };

        var option = {
            color: ["#97eeff", "#00c8dc", "#006afe"],
            tooltip: {
                trigger: 'item',
                formatter: "{b}:{d}%"
            },
            legend: {
                data: ['项目未完成', '项目计划中', '项目完成'],
                textStyle: {
                    color: '#fff'
                },
                selectedMode: true,
                y: "bottom"
            },
            series: [{
                name: '落实情况',
                type: 'pie',
                radius: ['50%', '70%'],
                startAngle: 180,
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '14'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                        value: 10,
                        name: '项目未完成'
                    },
                    {
                        value: 15,
                        name: '项目计划中'
                    },
                    {
                        value: 75,
                        name: '项目完成'
                    }
                ]
            }]
        };
        myChart.setOption(option);
    },
    /**
     * 自动调整图表大小 让其占满整个屏幕
     */
    fullScreen: function () {
        var h = $(window).height(),
            itemH = (h - 35 * 3 - 80) * 0.33333,
            mapH = (h - 60 - 79 - 60);
        $(".chart").css("height", itemH + "px");
        $(".map-chart").css("height", mapH + "px");
    }
};
$(document).ready(function () {
    Chart.init();

    $(window).resize(function () {
        if (myChart) {
            myChart.resize();
        }
    });
})

const chart = {};

export default chart;