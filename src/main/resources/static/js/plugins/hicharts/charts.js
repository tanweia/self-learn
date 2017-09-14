function charts(titleName,seriesName,seriesData){
    var credits = {
        enabled:false
    };
    var chart = {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false
    };
    var title = {
        text: titleName,
        verticalAlign:'middle',
        useHTML:true,
        x:-45,
        y:0,
        style:{
            fontFamily:'microsoft YaHei',
            fontSize:'18px',
            textAlign:'center',
        }
    };
    var tooltip = {
        
    };
    var plotOptions = {
        pie: {
            innerSize: '60%',
            showInLegend: true,
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '{point.name}:{point.y}<b>({point.percentage:.1f} %)',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                    fontFamily:'microsoft YaHei',
                    fontSize:'14px',
                    fontWeight:'100',
                    textAlign:'center'
                }
            }
        }
    };
    var series= [{
        type: 'pie',
        name: seriesName,
        data: seriesData
    }];
    var legend = {
        layout:'vertical',
        align:'right',
        verticalAlign:'middle',
        itemMarginBottom:30
    };
    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.series = series;
    json.plotOptions = plotOptions;
    json.credits = credits;
    json.legend = legend;
    $('#pie-container').highcharts(json,function(c) {
        // 环形图圆心
	    var centerX = c.series[0].center[0];
	   	var centerY = c.series[0].center[1];
	   	
	   	window.pieCenterX = centerX;
	   	window.pieCenterY = centerY;

	   	
	    // 标题字体大小，返回类似 16px ，所以需要 parseInt 处理
	    var titleHeight = parseInt(c.title.styles.fontSize);   
	    
	    // 设置图表偏移
	    $(".highcharts-title").css("top",centerY-30);
	    $(".highcharts-title").css("left",centerX-27);
    });
}