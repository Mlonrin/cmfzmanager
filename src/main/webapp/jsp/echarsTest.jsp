<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/china.js"></script>
</head>
<body>


    <div id="main" style="width: 600px;height:400px;"></div>

    <div id="main1" style="width: 600px;height:400px;"></div>

    <div id="china" style="width: 600px;height:400px;"></div>


</body>

<script type="text/javascript">
    //============================男女数目-============================开始===========
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户男女人数'
        },
        tooltip: {},
        legend: {
            data:['数量']
        },
        xAxis: {
            data: ["男","女"]
        },
        yAxis: {}
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    //============================男女数目-============================结束===========

    //============================近三周注册人数-============================开始===========
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('main1'));

    // 指定图表的配置项和数据
    var option1 = {
        title: {
            text: '近三周注册用户数'
        },
        tooltip: {},
        legend: {
            data:['注册数量']
        },
        xAxis: {
            data: ["近三周","近两周","近一周"]
        },
        yAxis: {}
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);

    //============================近三周注册人数-============================结束===========

    //============================ 地图展示开始-=======================================

    // 基于准备好的dom，初始化echarts实例
    var myChina = echarts.init(document.getElementById('china'));

    var option3 = {
        title : {
            text: '用户地区分布',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['用户人数']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        }
    };
    myChina.setOption(option3)

    //============================ 地图展示结束-=======================================

    $(function () {

        /*$.ajax({
            url:"/user/getCountBySex",
            type:"get",
            dateType:"json",
            success:function (date) {
                var option = {
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: [date.men, date.women]
                    }]
                }
                myChart.setOption(option);
            }
        })

        $.ajax({
            url:"/user/selectByDate",
            type:"get",
            dateType:"json",
            success:function (date) {
                //console.log(date)
                var option1 = {
                    series: [{
                        name: '注册数量',
                        type: 'line',
                        data: date
                    }]
                }
                myChart1.setOption(option1);
            }
        })


        $.ajax({
            url:"/user/selectProvince",
            type:"get",
            dateType:"json",
            success:function (date) {
                console.log(date)
                var option3 = {
                    series : [
                        {
                            name: '用户人数',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:date
                        }
                    ]
                }
                myChina.setOption(option3);
            }
        })*/

        $.ajax({
            url:"${pageContext.request.contextPath}/user/getAll",
            type:"get",
            dateType:"json",
            success:function (date) {
                //console.log(date)

                var option = {
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: [date.sex.men, date.sex.women]
                    }]
                }
                myChart.setOption(option);

                var option1 = {
                    series: [{
                        name: '注册数量',
                        type: 'line',
                        data: date.count
                    }]
                }
                myChart1.setOption(option1);

                var option3 = {
                    series : [
                        {
                            name: '用户人数',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:date.province
                        }
                    ]
                }
                myChina.setOption(option3);
            }
        })

    })
</script>

</html>
