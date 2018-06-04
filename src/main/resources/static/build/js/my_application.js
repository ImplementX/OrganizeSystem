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
				url: "student-api/get-my-application",
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
				        { data: 'type' },
				        { data: 'result' },
				        {data: 'applicationId',render: function(data, type, row) {
				        	if(row.applicationResult == 0)
				        		return '<a class="btn btn-danger btn-sm"  href="javascript:;" onclick="removeApplication(' +data+  ')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 取消申请</a>';
				        	else
				        		return '<a class="btn btn-danger btn-sm"  href="javascript:;" onclick="removeApplication(' +data+  ')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除记录</a>';
				        		
				        } }
				        ]
				} );
				}
			})
}
function removeApplication(id){
	$.ajax({
				type: 'Get',
				url: "student-api/cancel-application?applicationId="+id,
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
