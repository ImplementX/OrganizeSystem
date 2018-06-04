var adminVue =  new Vue({
	el: "#admin-modal",
	data: {
		datas: [],
		flag:false
	},mounted: function() {
		this.initTable(); 	
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {
		
		initTable:function(){
			$.ajax({
				type: 'Get',
				url: "admin-api/admin-list",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					var data =  $.parseJSON(datajson);
				
					$('#datatable').DataTable( {
				    data : data,
				    destroy:true,
				    columns: [ 
				        { data: 'adminName' },
				        { data: 'adminGender' },
				        { data: 'adminAge' },
				        { data: 'adminTel' },
				        { data: 'adminMail' },		        
				        { data: 'adminWechat' },
				        { data: 'adminQq' },
				        {data: 'adminId',render: function(data, type, row) {return '<a data-toggle="modal" data-target="#admin-modal" class="btn  btn-info btn-sm" href="javascript:;" onclick="updateAdmin('+data+')"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改信息</a>' +
				        '<a data-toggle="modal" data-target="#password-modal" class="btn  btn-warning btn-sm" onclick="initPassword('+row.userId+')" href="javascript:;" > <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改密码</a>' +
				        																	'<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteAdmin(' +data+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除管理员</a>'; } }
				        ]
				} );
				}
			})
		}
	}
});


function deleteAdmin(adminId){
$.ajax({
				type: 'Get',
				url: "admin-api/remove-admin?adminId="+adminId,
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
					adminVue.initTable();
				}
		}) 
}


function submitAdmin( adminId ){
		
	url = "admin-api/add-admin";
	if(adminVue.flag == true){
		url = "admin-api/update-admin";
	}
	console.log(url);
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#admin-form").serialize()+"&adminId="+adminId,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
			console.log($("#admin-form").serialize()+"&adminId="+adminId);
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			 console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					adminVue.initTable();
					adminVue.datas = [];
					
		}		
			
			
		
	});
}



function initAdmin(){
	
adminVue.flag = false;
	
}



function updateAdmin(adminId){
	
	$.ajax({
				type: 'Get',
				url: "admin-api/get-admin?adminId="+adminId,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					data = $.parseJSON(datajson);
					adminVue.datas = data;
					
					initAdmin(); 
					adminVue.flag = true;
				}
		}) 
}