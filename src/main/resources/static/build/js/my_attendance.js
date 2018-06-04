key = sessionStorage.getItem('key');
var vmm = new Vue({
	el: "#datatable",
	data: {
		datas: []

	},
	mounted: function() {
		initTable();
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	
});

function initTable(){
	$.ajax({
				type: 'Get',
				url: "student-api/get-my-attendance",
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
				    columns: [   { data: 'courseName' ,render: function(data, type, row) { return '<a  href="javascript:;" onclick="getCourseDetails(' +row.courseId+  ')"> '+data+'</a>'; }},
				        
				        { data: 'teacherName' },
				        { data: 'teacherTel' },
				    
				        { data: 'scheduleDate' },
				        { data: 'schedulePlace' }
				        
				        ]
				} );
				}
			})
}
