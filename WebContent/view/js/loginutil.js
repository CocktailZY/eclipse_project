function tologin(){
	$.ajax({
		type : "post",
		async : false,
		url : "../login/login.action",
		dataType : "json",
		data : {
			username:$('#uname').val(),
			password:$('#pwd').val()
		},
		success : function(data) {
			console.log(data);
			if (data.status == 'success') {
				Util.setSession('login_user',JSON.stringify(data.user));
				$('.w3l_login').empty().html('<div style="height:50px;line-height: 50px;font-size: large;">欢迎您! '+data.user.uName+'&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-sm btn-info" onclick="loginout()">注销</button></div>');
				$('.w3l_logo').css('margin-left','15em');
				$('#myModal88').modal('hide');
			} else {
				//没有数据或服务器错误
			}
		}
	});
}
function loginout(){
	Util.clearSessionStorage();
	$('.w3l_login').empty().html('<a href="#" data-toggle="modal" data-target="#myModal88"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>');
	$('.w3l_logo').css('margin-left','21em');
}