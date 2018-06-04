var teacherVue =  new Vue({
	el: "#teacher-modal",
	data: {
		datas: [],
		flag:false
	},mounted: function() {
		this.initTable(); 	
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {
		
		initTable:function(){
			$.ajax({
				type: 'Get',
				url: "admin-api/teacher-list",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					var data =  $.parseJSON(datajson);
				
					$('#datatable').DataTable( {
				    data : data,
				    destroy:true,
				    columns: [ 
				    	{ data: 'teacherNumber' },
				        { data: 'teacherName' },
				        { data: 'teacherGender' },
				        { data: 'teacherAge' },
				        { data: 'teacherTel' },
				        { data: 'teacherMail' },		        
				        { data: 'teacherWechat' },
				        { data: 'teacherQq' },
				     
				        {data: 'teacherId',render: function(data, type, row) {return '<a data-toggle="modal" data-target="#teacher-modal" class="btn  btn-info btn-sm" href="javascript:;" onclick="updateTeacher('+data+')"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改信息</a>' +
				        '<a data-toggle="modal" data-target="#password-modal" class="btn  btn-warning btn-sm" onclick="initPassword('+row.userId+')" href="javascript:;" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改密码</a>' +
				        																	'<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteTeacher(' +data+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除学生</a>'; } }
				        ]
				} );
				}
			})
		}
	}
});


function deleteTeacher(teacherId){
$.ajax({
				type: 'Get',
				url: "admin-api/remove-teacher?teacherId="+teacherId,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					teacherVue.initTable();
				}
		}) 
}


function submitTeacher( teacherId ){
		
	url = "admin-api/add-teacher";
	if(teacherVue.flag == true){
		url = "admin-api/update-teacher";
	}
	console.log(url);
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#teacher-form").serialize()+"&teacherId="+teacherId,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
			console.log($("#teacher-form").serialize()+"&teacherId="+teacherId);
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			 console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					teacherVue.initTable();
					teacherVue.datas = [];
					
		}		
			
			
		
	});
}



function initTeacher(){
	
teacherVue.flag = false;
	
}



function updateTeacher(teacherId){
	
	$.ajax({
				type: 'Get',
				url: "admin-api/get-teacher?teacherId="+teacherId,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				}, 
				success: function(datajson) {
					console.log(datajson);
					data = $.parseJSON(datajson);
					teacherVue.datas = data;
					
					initTeacher(); 
					teacherVue.flag = true;
				}
		}) 
}