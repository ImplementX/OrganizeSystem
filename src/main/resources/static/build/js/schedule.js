var schedule = new Vue({
	el: "#schedule",
	data: {
		datas: [],
		course:''

	}
	
	
});


function getSchedule(id){
	$.ajax({
				type: 'Get',
				url: "teacher-api/get-schedule?courseId="+id,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					console.log(data);
					schedule.datas = $.parseJSON(data);
					schedule.course = id;
					
				}
			})
}


function addSchedule(){
	url = "teacher-api/add-schedule";
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#schedule-form").serialize()+"&courseId="+schedule.course,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			getSchedule(schedule.course);
			$("#schedule-date").val('');
			$("#schedule-place").val('');
		}		
			
			
		
	});
}

function removeSchedule(id){
$.ajax({
				type: 'Get',
				url: "teacher-api/remove-schedule?scheduleId="+id,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					getSchedule(schedule.course);
				}
			})
}