var studentVue =  new Vue({
	el: "#student-modal",
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
				url: "admin-api/student-list",
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
				    	{ data: 'studentNumber' },
				        { data: 'studentName' },
				        { data: 'studentGender' },
				        { data: 'studentAge' },
				        { data: 'studentTel' },
				        { data: 'studentMail' },		        
				        { data: 'studentWechat' },
				        { data: 'studentQq' },
				        { data: 'studentMajor' },
				        { data: 'studentDepartment' },
				        {data: 'studentId',render: function(data, type, row) {return '<a data-toggle="modal" data-target="#student-modal" class="btn  btn-info btn-sm" href="javascript:;" onclick="updateStudent('+data+')"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改信息</a>' +
				        '<a data-toggle="modal" data-target="#password-modal" class="btn  btn-warning btn-sm" onclick="initPassword('+row.userId+')" href="javascript:;" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改密码</a>' +
				        																	'<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteStudent(' +data+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除学生</a>'; } }
				        ]
				} );
				}
			})
		}
	}
});


function deleteStudent(studentId){
$.ajax({
				type: 'Get',
				url: "admin-api/remove-student?studentId="+studentId,
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
					studentVue.initTable();
				}
		}) 
}


function submitStudent( studentId ){
		
	url = "admin-api/add-student";
	if(studentVue.flag == true){
		url = "admin-api/update-student";
	}
	console.log(url);
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#student-form").serialize()+"&studentId="+studentId,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
			console.log($("#student-form").serialize()+"&studentId="+studentId);
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			 console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					studentVue.initTable();
					studentVue.datas = [];
					
		}		
			
			
		
	});
}



function initStudent(){
	
studentVue.flag = false;
	
}



function updateStudent(studentId){
	
	$.ajax({
				type: 'Get',
				url: "admin-api/get-student?studentId="+studentId,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				}, 
				success: function(datajson) {
					console.log(datajson);
					data = $.parseJSON(datajson);
					studentVue.datas = data;
					
					initStudent(); 
					studentVue.flag = true;
				}
		}) 
}