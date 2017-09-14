function TipsBox(option){
	this.successMsg = option.successMsg || "";
	this.errorMsg = option.errorMsg||"";
	this.tipsMsg = option.tipsMsg||"";
	this.confirmCallBack = option.confirmCallBack;
	this.cancelCallBack = option.cancelCallBack;
	this.target = option.target;
	this.init();
}
TipsBox.prototype = {
	init:function(){
		this.initEvents();
	},
	initEvents:function(){
		var _self = this;
		if(_self.successMsg){
			console.log(1)
		}else if(_self.errorMsg){
			console.log(2)
		}else if(_self.tipsMsg){
			var html = '<div class="ui-tipsBox">\
							<div class="ui-tipsBox-tipsBoxIcon">！</div><p class="ui-tipsBpx-content">'+_self.tipsMsg+'</p>\
							<div  class="ui-tipsBox-confirm ui-tipsBox-tipsBoxBtn">确定</div><div class="ui-tipsBox-cancel ui-tipsBox-tipsBoxBtn">取消</div>\
						</div>';
			$("body").append(html);
			$(".ui-tipsBox-confirm").on("click",function(){
				_self.hide();
				_self.confirmCallBack();
			})
			$(".ui-tipsBox-cancel").on("click",function(){
				_self.hide();
				_self.cancelCallBack();
			})
		}
	},
	show:function(){
		$(".ui-tipsBox").show();
	},
	hide:function(){
		$(".ui-tipsBox").remove();
	}

}