key = sessionStorage.getItem('key');
$(document).ready(function(){
	initTable();
})


function initTable(){
	$.ajax({
				type: 'Get',
				url: "student-api/get-my-grade",
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
				    columns: [   { data: 'courseName' },
				        
				        { data: 'teacherName' },
				        { data: 'teacherTel' },
				        { data: 'grade' },
				        { data: 'result' }]
				} );
				}
			})
}