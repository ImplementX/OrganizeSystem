var courseId = 1;
var course = new Vue({
			el: "#coursemodal",
			data: {
				datas: {},
				times:[]

			}
	})


function getCourseDetails(courseId) {
	$.ajax({
						type: 'Get',
						url: "student-api/get-course-details?courseId=" + courseId,
						// data:{type:type,ext:ext},
						datatype: "json",
						beforeSend: function(request) {
							request.setRequestHeader("JWTKey", key);
						},
						success: function(datajson) {
//							console.log(datajson);
//							course.datas = ;
//							course.times = $.parseJSON(course.datas.time);
							course.$set(course.$data,"datas",$.parseJSON(datajson));
							course.$set(course.$data,"times",$.parseJSON(course.datas.time));
							console.log(JSON.stringify(course.$data));
							$('#coursemodal').modal({
					});
						}
					});
	
}


