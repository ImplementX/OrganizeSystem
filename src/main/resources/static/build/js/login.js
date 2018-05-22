

$(document).ready(function(){

	$("#submit_button").bind("click",submit);
	
})

function submit(){
	$.ajax({
		type:"post",
		url:"user/login",
		async:true,
		data:$("#form").serialize(),
		contentType:"application/x-www-form-urlencoded",
		success:function(data){
			data = $.parseJSON(data);
			if(data.page!=undefined){
//				alert(data.key);
				window.location.href = data.page;
				   sessionStorage.setItem('key', data.key);
				   sessionStorage.setItem('nickname', data.nickname);
				   alert(data.key)
			}else{
				
//				
				
				alert(data.message);
			}
				
			
			
		}
	});
}
