key = sessionStorage.getItem('key');
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
				url: "student-api/get-my-course",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(data) {
					console.log(data);
					vmm.datas = $.parseJSON(data);

				}
			})
		}
	}
});