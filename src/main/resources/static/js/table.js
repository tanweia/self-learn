$.fn.datetimepicker.dates['zh'] = {  
                days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],  
                daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],  
                daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],  
                months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],  
                monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],  
                meridiem:    ["上午", "下午"],  
                today:       "今天"  
        }; 

var fullDate = new Date();
var fullYesterday = new Date(fullDate.getTime()-86400000);
var yesterdayTime = fullDate.getTime()-86400000;
var fullPrevDate = new Date(fullDate.getTime()-604800000);
var fullHalfMonthDate = new Date(fullDate.getTime()-1296000000);
var fullMonthDate = new Date(fullDate.getTime()-2592000000);

var timerDate = {};
timerDate.date = fullDate.getFullYear()+"-"+(fullDate.getMonth()+1)+"-"+fullDate.getDate();
timerDate.yesterday = fullYesterday.getFullYear()+"-"+(fullYesterday.getMonth()+1)+"-"+fullYesterday.getDate();
timerDate.prevDate = fullPrevDate.getFullYear()+"-"+(fullPrevDate.getMonth()+1)+"-"+fullPrevDate.getDate();
timerDate.halfMonthDate = fullHalfMonthDate.getFullYear()+"-"+(fullHalfMonthDate.getMonth()+1)+"-"+fullHalfMonthDate.getDate();
timerDate.monthDate = fullMonthDate.getFullYear()+"-"+(fullMonthDate.getMonth()+1)+"-"+fullMonthDate.getDate();

var timer1 = "<div class='timer-picker-wrap-1 timer-picker-wrap'><h3>日期范围:</h3>" +
        "<input type='text' class='timer-link timer-link-prev' readOnly='true' value=''/><span>-</span><input type='text' class='timer-link timer-link-now' readOnly='true' value=''/>" +
        "<h3>快捷日期:</h3>" +
        "<ul class='timer-link-fastList'><li data-date='yesterday'>昨天</li><li class='chose' data-date='prevDate'>过去7天</li><li data-date='halfMonthDate'>过去15天</li><li data-date='monthDate'>过去30天</li></ul>" +
        "<a href='javascript:void(0)' class='btn-primary timer-opreate timer-comfirm'>确认</a><a href='javascript:void(0)' class='btn-white timer-opreate timer-cancel'>取消</a>"+
        "</div>";
var timer2 = "<div class='timer-picker-wrap-2 timer-picker-wrap'><h3>日期范围:</h3>" +
        "<input type='text' class='timer-link timer-link-prev' readOnly='true' value=''/><span>-</span><input type='text' readOnly='true' class='timer-link timer-link-now' value=''/>" +
        "<h3>快捷日期:</h3>" +
        "<ul class='timer-link-fastList'><li data-date='yesterday'>昨天</li><li class='chose' data-date='prevDate'>过去7天</li><li data-date='halfMonthDate'>过去15天</li><li data-date='monthDate'>过去30天</li></ul>" +
        "<a href='javascript:void(0)' class='btn-primary timer-opreate timer-comfirm'>确认</a><a href='javascript:void(0)' class='btn-white timer-opreate timer-cancel'>取消</a>"+
        "</div>";
$("body").append(timer1);
$("body").append(timer2);

$(".timer-link-now").val(timerDate.yesterday);
$(".title-link-now").text(timerDate.yesterday);
$(".timer-link-prev").val(timerDate.prevDate);
$(".title-link-prev").text(timerDate.prevDate);
$(".timer-comfirm").attr("data-startDate",timerDate.prevDate);
$(".timer-comfirm").attr("data-endDate",timerDate.date);

$('.timer-link').datetimepicker({
    minView: "month", 
    language:  'zh',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1,
    todayHighlight:true,
    setStartDate: '2016',
    bootcssVer:3
});
$("body").on("click",function(){
    timerClose();
});
$(".timer-cancel").on("click",function(){
    timerClose();
})
$(".timer-picker-wrap").click(function(e){
    e.stopPropagation();
});

$(".timer-picker-wrap-1").find(".timer-link-fastList").find("li").on("click",function(){
    var prevInput = $(".timer-picker-wrap-1").find(".timer-link-prev");
    var nowInput = $(".timer-picker-wrap-1").find(".timer-link-now");
    prevInput.val(timerDate[$(this).attr("data-date")]);
    nowInput.val(timerDate["yesterday"]);
});
$(".timer-picker-wrap-2").find(".timer-link-fastList").find("li").on("click",function(){
    var prevInput = $(".timer-picker-wrap-2").find(".timer-link-prev");
    var nowInput = $(".timer-picker-wrap-2").find(".timer-link-now");
    prevInput.val(timerDate[$(this).attr("data-date")]);
    nowInput.val(timerDate["yesterday"]);
});
$(".timer-picker-wrap-1").find(".timer-link-fastList").find("li").on("click",function(){
    $(".timer-picker-wrap-1").find(".timer-link-fastList").find("li").removeClass("chose");
    $(this).addClass("chose");
});
$(".timer-picker-wrap-2").find(".timer-link-fastList").find("li").on("click",function(){
    $(".timer-picker-wrap-2").find(".timer-link-fastList").find("li").removeClass("chose");
    $(this).addClass("chose");
});

var timerPicker1 = $(".timer-picker-wrap-1");
$(".table-title-wrap-1").click(function(e){
    e.stopPropagation();
    $(this).addClass("timerClick");
    timerPicker1.css("left",$(this).offset().left+30);
    timerPicker1.css("top",$(this).offset().top+30);
    timerPicker1.toggleClass("animated bounceIn").show();
});

var timerPicker2 = $(".timer-picker-wrap-2");
$(".table-title-wrap-2").click(function(e){
    e.stopPropagation();
    $(this).addClass("timerClick");
    timerPicker2.css("left",$(this).offset().left+30);
    timerPicker2.css("top",$(this).offset().top+30);
    timerPicker2.toggleClass("animated bounceIn").show();
});

$(".table-search").on("keyup",function(e){
    if(e.keyCode==13){
        $(".table-search-submit").trigger("click");
    }
});

$(".goTopage").on("keyup",function(e){
    if(e.keyCode==13){
        $(".gotoBtn").trigger("click");
    }
});

$(".table-search").on("focus blur",function(){
    $(this).toggleClass("animated bounce");
});

$(".dx-question-sign").hover(function(){
    var position = $(this).position();
    var left = position.left;
    var top= position.top;
    $(".hoverTips").css("left",left+15);
    $(".hoverTips").css("top",top-55);
    $(".hoverTips").toggle();
});

function timerClose(){
    $(".timer-picker-wrap").removeClass("animated bounceIn").hide();
    $(".table-title-wrap").removeClass("timerClick");
};
function returnTime(str){
    return new Date(str.replace(/-/g,'/')).getTime();
};
//选取时间做限制
function compareTime(startDate,endDate){
    var prevDate =  new Date(startDate).getTime();
    var nowDate = new Date(endDate).getTime();
    if(prevDate>nowDate){
        new tips({errorMsg:"所选择的时间不合法"});
        return false;
    };
    if(prevDate>yesterdayTime||nowDate>yesterdayTime){
        new tips({errorMsg:"所选时间不能超过昨天"});
        return false;
    }
    return true;
};

window.onresize = function(){
    $(".timer-picker-wrap-1").find(".timer-comfirm").trigger("click");
};