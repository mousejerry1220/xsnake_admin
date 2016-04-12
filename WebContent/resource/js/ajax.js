function ajaxPost(options){
	var form = options.form;
	var _success = options.success;
	var _error = options.error;
	$(form).ajaxSubmit({
		type: "post",
	    url: $(form).attr('action'),
	    success : function(result){ //以return 方式出来的信息或者异常
	    	if(result.status === "error" && typeof(_error)=="function"){
	    		_error(result.message);
	    	}
	    	if(result.status === "success" && typeof(_success)=="function"){
	    		_success(result.message);
	    	}
	    },
	    error : function(error){ //以throw 方式出来的异常
	    	if(error.status<500){//客户端异常
	    		errorTips("请检查网络连接是否正常");
	    	}else if(error.responseJSON.message){//服务端异常
	    		var data = error.responseJSON.message;
	    		errorTips(data);
	    	}else{
	    		errorTips("未知错误!");
	    	}
	    }
	});
}

function errorTips(errorMessage){
	var errDiv = $("#errorContent");
	if(errDiv.length > 0){  //判断页面是否存在错误输出区域
		var err = $("<article ></article >");	
		if(errDiv){
			errDiv.removeClass("hide");
			var errMessage = "<div class='alert alert-danger fade in'>	<button class='close' data-dismiss='alert'> × </button> <i class='fa-fw fa fa-times'></i> <strong>错误!</strong>"+errorMessage+"</div>";
			err.html(errMessage);
			errDiv.html(err);
		}
	}else{  //将错误信息弹出来显示
		var errorDialog = $("<div class='hide center'><h1> "+errorMessage+" </h1> </div>");
		errorDialog.removeClass('hide').dialog({
			modal: true,
			draggable :false,
			closeOnEscape :true
		});
	}
}