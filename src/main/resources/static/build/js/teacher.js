var vmm = new Vue({
	el: "#datatable",
	data: {
		datas: []
		

	},
	mounted: function() {
		this.showData();
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {
		showData: function() {
			$.ajax({
				type: 'Get',
				url: "teacher-api/get-my-course",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					vmm.datas = $.parseJSON(datajson);
					$('#datatable').DataTable( {
				    data : $.parseJSON(datajson),
				    destroy:true,
				    columns: [  { data: 'courseName' ,render: function(data, type, row) { return '<a  href="javascript:;" onclick="getCourseDetails(' +row.courseId+  ')"> '+data+'</a>'; }},
				         { data: 'tagName' },
				        { data: 'organizationName' },
				        { data: 'teacherName' },
				        { data: 'teacherTel' },
				        { data: 'teacherTel' },
				        { data: 'courseId',render: function(data, type, row) {return '<a data-toggle="modal" data-target="#schedule-modal" class="btn btn-success btn-sm" href="javascript:;" onclick="getSchedule(' +data+  ')"> <span class="glyphicon  glyphicon-search" aria-hidden="true"></span> 课程安排</a>'+
				        															'<a class="btn btn-info btn-sm" href="javascript:;" onclick="getForm(' +data+  ')"> <span class="glyphicon  glyphicon-pencil" aria-hidden="true"></span> 修改课程信息</a>'+
				        															'<a class="btn btn-danger btn-sm" href="javascript:;" onclick="deleteCourse(' +data+  ')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除此课程</a>'
				        													; } }
				        ]
				} );
				}
			})
		}
	}
});

function deleteCourse(id){
	$.ajax({
				type: 'Get',
				url: "teacher-api/remove-course?courseId="+id,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					console.log(data);
					$("#message").text($.parseJSON(data).message);
					vmm.showData();
					$('#modal').modal({
					});
				}
			})
}



