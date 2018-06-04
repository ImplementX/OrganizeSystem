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
				url: "teacher-api/get-my-application",
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
				        
				        { data: 'studentName' },
				        { data: 'studentTel' },
				        { data: 'type' },
				        { data: 'result' },
				        {data: 'applicationId',render: function(data, type, row) {return '<a class="btn btn-success btn-sm"  href="javascript:;" onclick="permitApplication(' +data+  ')"> <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 允许</a>'+
				        																'<a class="btn btn-danger btn-sm"  href="javascript:;" onclick="refuseApplication(' +data+  ')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 拒绝</a>'
				        																
				        ; } }
				        ]
				} );
				}
			})
}
function permitApplication(id){
	$.ajax({
				type: 'Get',
				url: "teacher-api/permitApplication?applicationId="+id,
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
					initTable();
				}
		}) 
}
function refuseApplication(id){
	$.ajax({
				type: 'Get',
				url: "teacher-api/refuseApplication?applicationId="+id,
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
					initTable();
				}
		}) 
}
