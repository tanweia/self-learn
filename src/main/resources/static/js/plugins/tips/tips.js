function loadstyle(css){
	this.cssFile = css;
    this.init();
};
loadstyle.prototype={
    init:function(){
        this.initEvents();
    },
    initEvents:function(){
        var _self =this;
    	$("head").append(" <link rel='stylesheet' href='"+_self.cssFile+"'>")
    },
};

new loadstyle("/js/plugins/tips/tips.css");
function tips(json){
	this.errorMsg = json.errorMsg;
	this.successMsg = json.successMsg;
    this.init();
};
tips.prototype={
    init:function(){
        this.initEvents();
    },
    initEvents:function(){
    	var _self = this;
    	if(_self.successMsg){
    		$("body").append("<div class='ui-messagebox success'><div class='ui-messagebox-icon'></div><p class='ui-messagebox-tips'>"+_self.successMsg+"</p></div>")
    	}else if(_self.errorMsg){
    		$("body").append("<div class='ui-messagebox error'><div class='ui-messagebox-icon'></div><p class='ui-messagebox-tips'>"+_self.errorMsg+"</p></div>")
    	}
    	_self.showTips();   	
    },
    showTips:function(){
    	$(".ui-messagebox").show(function(){
    		$(this).animate({top:'30%'},'100');
    		var _selfTarget = $(this);
    		setTimeout(function(){_selfTarget.remove();},2000);
    	});
    },
};