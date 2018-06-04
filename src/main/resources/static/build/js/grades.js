var gradesVue =  new Vue({
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
				url: "teacher-api/students-grade?courseId="+this.courseModel,
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
				        { data: 'studentMajor' },
				        { data: 'studentDepartment' },
				        { data: 'grade' ,render: function(data, type, row) {return '<input class="form-control" id="grade-number" name="gradeNumber" value="'+data+'" disabled="disabled">'} },
				        {data: 'studentId',render: function(data, type, row) {return '<a id="submit-button" class="btn  btn-success btn-sm" href="javascript:;" onclick="updateGrade('+data+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 解除锁定</a>'; } }
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
					gradesVue.datas = data;
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


function updateGrade(studentId){
	if($("#grade-number").attr("disabled")=="disabled"){
		$("#grade-number").removeAttr("disabled");
		$("#submit-button").text("提交修改");
	
	}else{
		$("#grade-number").attr("disabled","disabled");
		url = "teacher-api/update-grade";
		$("#submit-button").text("解除锁定");
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:"gradeNumber="+$("#grade-number").val()+"&studentId="+studentId+"&courseId="+gradesVue.courseModel,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			gradesVue.initTable();
			
		}		
			
			
		
	});
	}
}
