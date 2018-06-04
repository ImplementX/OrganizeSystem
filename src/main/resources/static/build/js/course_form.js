var formVue = new Vue({
	el: "#form-modal",
	data: {
		datas: [],
		organizations:[],
		tags:[],
		organizationSelected:'',
		tagSelected:'',
		radioSelected:'',
		flag:false
	}
	
});

function initForm(){
	formVue.$set(formVue.$data,"datas",[]);
	formVue.$set(formVue.$data,"organizationSelected",'1');
	formVue.$set(formVue.$data,"tagSelected",'1');
	formVue.$set(formVue.$data,"flag",false);
	$.ajax({
				type: 'Get',
				url: "teacher-api/course-form",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					console.log(data);
					data = $.parseJSON(data);
					//radio默认选中
					$("[name='is-full']:eq(1)").prop("checked",true);
					formVue.$set(formVue.$data,"organizations",$.parseJSON(data.organizations));
					formVue.$set(formVue.$data,"tags",$.parseJSON(data.tags));
					
					
				}
			})
}


function submit_form(courseId){
	console.log($("#form").serialize());
	url = "teacher-api/new-course";
	if(formVue.flag == true){
		url = "teacher-api/update-course";
	}
	console.log(url);
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#form").serialize()+"&courseId="+courseId,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			
			data = $.parseJSON(data);
			$("#message").text(data.message);
			$('#form-modal').modal('hide');
			vmm.showData();
			$('#modal').modal({});
			
		}		
			
			
		
	});
	
}


function getForm(courseId){
	
	$.ajax({
		type:"get",
		url:"teacher-api/get-course?courseId="+courseId,
		async:true,
		beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
		},
		success:function(data){
			initForm();
			console.log(data);
			dataobj = $.parseJSON(data);
			$("#message").text(dataobj.message);
			formVue.$set(formVue.$data,"datas",dataobj);
			formVue.$set(formVue.$data,"organizationSelected",dataobj.organizationId);
			formVue.$set(formVue.$data,"tagSelected",dataobj.courseTag);
			formVue.$set(formVue.$data,"radioSelected",dataobj.courseIsFull);
			formVue.$set(formVue.$data,"flag",true);
			$('#form-modal').modal({});
		}		
			
			
		
	});
}