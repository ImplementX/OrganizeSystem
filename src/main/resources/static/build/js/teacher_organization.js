var organizationVue =  new Vue({
	el: "#organization-modal",
	data: {
		datas: [],
		organization:'',
		tags:[],
		flag:false
	},mounted: function() {
		this.initTable(); 	
		//需要执行的方法可以在mounted中进行触发，其获取的数据可以赋到data中后，可以放在前面进行渲染
	},
	methods: {
		
		initTable:function(){
			$.ajax({
				type: 'Get',
				url: "teacher-api/organization-list",
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
				        { data: 'organizationName' },
				        { data: 'organizationDescription' },
				        { data: 'organizationTag' },
				        { data: 'organizationOwner' },
				        { data: 'organizationViceOwner' },
				        {data: 'organizationId',render: function(data, type, row) {return '<a data-toggle="modal" data-target="#organization-modal" class="btn  btn-info btn-sm" href="javascript:;" onclick="updateOrganization('+data+')"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改信息</a>' +
				        																	'<a class="btn  btn-danger btn-sm" href="javascript:;" onclick="deleteOrganization(' +data+')"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除社团</a>'; } }
				        ]
				} );
				}
			})
		}
	}
});


function deleteOrganization(organizationId){
$.ajax({
				type: 'Get',
				url: "teacher-api/remove-organization?organizationId="+organizationId,
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
					organizationVue.initTable();
				}
		}) 
}


function submitOrganization( organizationId ){
		
	url = "teacher-api/add-organization";
	if(organizationVue.flag == true){
		url = "teacher-api/update-organization";
	}
	console.log(url);
	$.ajax({
		type:"post",
		url:url,
		async:true,
		data:$("#organization-form").serialize()+"&organizationId="+organizationId,
		contentType:"application/x-www-form-urlencoded",
		beforeSend: function(request) {
			console.log($("#organization-form").serialize()+"&organizationId="+organizationId);
					request.setRequestHeader("JWTKey", key);
				},
		success:function(data){
			 console.log(data);
					data = $.parseJSON(data);
					$("#message").text(data.message);
					$('#modal').modal({
					});
					organizationVue.initTable();
					organizationVue.datas = [];
					
		}		
			
			
		
	});
}



function initOrganization(){
	
organizationVue.flag = false;
	$.ajax({
				type: 'Get',
				url: "teacher-api/tag-list",
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
					
				},
				success: function(datajson) {
					console.log(datajson);
					data = $.parseJSON(datajson);
					organizationVue.tags = data;
				}
		}) 
}



function updateOrganization(organizationId){
	
	$.ajax({
				type: 'Get',
				url: "teacher-api/get-organization?organizationId="+organizationId,
				// data:{type:type,ext:ext},
				datatype: "json",
				beforeSend: function(request) {
					request.setRequestHeader("JWTKey", key);
				},
				success: function(datajson) {
					console.log(datajson);
					data = $.parseJSON(datajson);
					organizationVue.datas = data;
					
					initOrganization();
					organizationVue.flag = true;
				}
		}) 
}