var attendanceVue =  new Vue({
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
				url: "teacher-api/attendance-list?courseId="+this.courseModel,
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
				    columns: [ 
				        { data: 'time' },
				        { data: 'place' },
				        { data: 'studentNumber' },
				        { data: 'studentName' },
				        { data: 'studentGender' },
				        { data: 'studentTel' },
				        { data: 'studentMajor' },
				        { data: 'studentDepartment' },
				        {data: 'attendanceId',render: function(data, type, row) {return '<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteAttendance(' +data+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 移除记录</a>'; } }
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
					attendanceVue.datas = data;
					this.courseModel = data[0].courseId;
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


function deleteAttendance(attendanceId){
$.ajax({
				type: 'Get',
				url: "teacher-api/remove-attendance?attendanceId="+attendanceId,
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
					attendanceVue.initTable();
				}
		}) 
}


var attendanceModel =  new Vue({
	el: "#attendance-modal",
	data: {
		students:[],
		schedules: [],
		schedule:'',
		student:''
	}
})



function initAttendance(){
	$.ajax({
				type: 'Get',
				url: "teacher-api/init-attendance?courseId="+attendanceVue.courseModel,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					console.log(data);
					obj = $.parseJSON(data);
					attendanceModel.students = $.parseJSON(obj.students);
					attendanceModel.schedules = $.parseJSON(obj.schedules);
				
				}
		}) 
}


function addAttendance(){
		url = "teacher-api/add-attendance";
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#attendance-form").serialize()+"&courseId="+attendanceVue.courseModel,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			$("#schedule-date").val('');
			$("#schedule-place").val('');
			console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					attendanceVue.initTable();
		}		
			
			
		
	});
}
