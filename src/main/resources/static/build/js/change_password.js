var pwdVue = new Vue({
			el: "#password-modal",
			data: {
				userId: '',
				
			}
	})



function changePwd(){
	$.ajax({
		type:"post",
		url:"user/change-password",
		async:true,
		data:$("#password-form").serialize()+"&userId="+pwdVue.userId,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
					console.log($("#password-form").serialize()+"&userId="+pwdVue.userId);
					console.log(key);
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			 console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					
					
		}		
			
			
		
	});
}


function initPassword(userId){
	pwdVue.userId= userId;
}
