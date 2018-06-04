key = sessionStorage.getItem('key');
nickname = sessionStorage.getItem('nickname');
var vmm = new Vue({
	el: "#datatable",
	data: {
		datas: [],

	},
	mounted: function() {
		this.initTables();
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {

		initTables: function() {
			$.ajax({
				type: 'Get',
				url: "student-api/get-new-course",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					vmm.datas = $.parseJSON(datajson);
					$('#datatable').DataTable({
						data: $.parseJSON(datajson),
						destroy: true,
						columns: [{
								data: 'courseName',
								render: function(data, type, row) {
									return '<a  href="javascript:;" onclick="getCourseDetails(' + row.courseId + ')"> ' + data + '</a>';
								}
							},
							{
								data: 'tagName'
							},
							{
								data: 'organizationName'
							},
							{
								data: 'teacherName'
							},
							{
								data: 'teacherTel'
							},
							{
								data: 'time'
							},
							{
								data: 'courseId',
								render: function(data, type, row) {
									return '<a class="btn  btn-info btn-sm" href="javascript:;" onclick="applyCourse(' + data + ')"> <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 申请上课</a>';
								}
							}
						]
					});
				}
			})
		}

	}
});

function applyCourse(id) {
	$.ajax({
		type: 'Get',
		url: "student-api/apply-new-course?courseId=" + id,
		// data:{type:type,ext:ext},
		datatype: "json",
		beforeSend: function(request) {
			request.setRequestHeader("JWTKey", key);
		},
		success: function(data) {
			console.log(data);
			data = $.parseJSON(data);
			$("#message").text(data.message);
			$('#modal').modal({});
			vmm.initTables();
		}
	})
}