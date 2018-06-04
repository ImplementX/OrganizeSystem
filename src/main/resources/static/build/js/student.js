key = sessionStorage.getItem('key');
nickname = sessionStorage.getItem('nickname');
var vmm = new Vue({
	el: "#datatable",
	data: {
		datas: [],

	},
	mounted: function() {
		this.showData();
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {
		showData: function() {
			$.ajax({
				type: 'Get',
				url: "student-api/get-my-course",
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
				        {data: 'courseId',render: function(data, type, row) {return '<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteCourse(' +data+  ')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 申请退课</a>'; } }
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
				url: "student-api/apply-remove-course?courseId="+id,
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
				}
			})
}
