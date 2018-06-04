var studentlistVue =  new Vue({
	el: "#course",
	data: {
		datas: [],
		courseModel:''
		
	},mounted: function() {
		this.showData(); 	
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {
		
		initTable:function(){
			console.log(this.courseModel);
			$.ajax({
				type: 'Get',
				url: "teacher-api/student-list?courseId="+this.courseModel,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					
					$('#datatable').DataTable( {
				    data : $.parseJSON(datajson),
				    destroy:true,
				    columns: [  { data: 'studentNumber' },
				         { data: 'studentName' },
				        { data: 'studentGender' },
				        { data: 'studentAge' },
				        { data: 'studentTel' },
				        { data: 'studentMail' },
				        { data: 'studentQq' },
				        { data: 'studentWechat' },
				        { data: 'studentMajor' },
				        { data: 'studentDepartment' },
				        {data: 'studentId',render: function(data, type, row) {return '<a data-toggle="modal" data-target="#password-modal" class="btn  btn-warning btn-sm" onclick="initPassword('+row.userId+')" href="javascript:;" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改密码</a>' +
				        	'<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteStudent(' +data+','+studentlistVue.courseModel+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 移除学生</a>'; } }
				        ]
				} );
				}
			})
		},
		showData: function() {
			$.ajax({
				type: 'Get',
				url: "teacher-api/get-course-list",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					data = $.parseJSON(datajson);
					studentlistVue.datas = data;
//					this.initTable();
				}
			})
		}
	},
	watch: {
    courseModel: function(courseId){
    	this.initTable(courseId);
    }
  }	
	
});


function deleteStudent(studentId,courseId){
$.ajax({
				type: 'Get',
				url: "teacher-api/remove-student?studentId="+studentId+"&courseId="+courseId,
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
					studentlistVue.initTable();
				}
		}) 
}
