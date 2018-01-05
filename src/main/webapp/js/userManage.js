search_user_role = 'all';
search_user_name = null;
search_status = null;
var cur_select_index = 0;


$(function() {
	userPageInit();
	
	superUserTableInit();
	userTableInit();

});


function enableUser(name, isEnable) {
	var state = 0;
	var successMsg = "disabled";
	
	if (isEnable == "enable") {
		state = 1;
		successMsg = "enabled";
	}

	var data = {
		"name" : name,
		"state" : state
	};
	
	if (confirm("Are you sure to "+ isEnable + " user: " + name)) {		
		$.ajax({
			type:"GET",
			url:"userManage/updateUserStatus",
			dataType:"json",
			contentType:"application/json",
			data:data,
			success: function(response) {
				if(response.result == 'error') {
					alert("Remote Server busy, please try later!");
				} else {
					alert(name + " is now " + successMsg);
					$('#userTable').bootstrapTable('refresh', {
						query : {}
					});
				}
			}
		});
	}
}

function userPageInit() {
	var reloadSupberUserTblBtn = $('#reloadSuperUserTable');


	/*********************************************************************************
	**
	** Supper User pages actions.
	**
	**********************************************************************************/	
	$('#addNewAdminRecord').click(function() {
		console.log("addNewAdminRecord button pressed");
    });
	
	$('#deleteAdminRecord').click(function() {
		console.log("deleteAdminRecord button pressed");
    });
	
	$('#modifyAdminRecord').click(function() {
		console.log("modifyAdminRecord button pressed");
    });
	
	reloadSupberUserTblBtn.click(function() {
		console.log("reloadSuperUserTable button pressed");
    	$('#superUserTable').bootstrapTable('refresh', {
    		query : {}
    	});
    });
	

	/*********************************************************************************
	**
	** User pages actions.
	**
	**********************************************************************************/	
	// reload button.
	var reloadUserTblBtn = $('#reloadUserTable');
	
	// three buttons on the top of normal user table
	var addNewUserRecordBtn = $('#addNewUserRecordBtn');
	var deleteUserRecordBtn = $('#deleteUserRecordBtn');
	var modifyUserRecordBtn = $('#modifyUserRecordBtn');
	
	
	// two block for add and edit.
	var normalUserBlock = $('#normalUserBlock');
	
	// Two forms here.
	var addNewUsers = $('#add_new_users');
	var modifyUserRecords = $('#modify_users');

	

	
	var newUserForm = $('#addNewUserForm');
	var newUserForm_error = $('.alert-error', newUserForm);
	var newUserForm_success = $('.alert-success', newUserForm);
	var newUserSubmitBtn = $('#add_new_user_submit_btn', newUserForm);
	var newUserCancelBtn = $('#add_new_user_cancel_btn', newUserForm);
	
	var modifyUserForm = $('#modifyUserForm');
	var modifyUserForm_error = $('.alert-error', modifyUserForm);
	var modifyUserForm_success = $('.alert-success', modifyUserForm);
	var modifyUserSubmitBtn = $('#modify_user_submit_btn', modifyUserForm);
	var modifyUserCancelBtn = $('#modify_user_cancel_btn', modifyUserForm);
	var modifyUserNextBtn = $('#modify_user_next_btn', modifyUserForm);

	reloadUserTblBtn.click(function() {
		console.log("reloadUserTable button pressed");
    	$('#userTable').bootstrapTable('refresh', {
    		query : {}
    	});
    });
	
	addNewUserRecordBtn.click(function() {
		console.log("addNewUserRecord button pressed");
		
		//show add new user form.
		normalUserBlock.removeClass("hide");
		addNewUsers.removeClass("hide");
		modifyUserRecords.addClass("hide");
		
		//Get select options from backend.
		var select_role = document.getElementById("new_user_role_select");
		var select_team = document.getElementById("new_user_team_select");
		
		//first, we need to clear all old options.
		for(var i = select_role.options.length -1; i >= 0; i--){
			if (select_role.options[i].value != "") {
				select_role.options[i] = null;
			}
		} 
		
		for(var i = select_team.options.length -1; i >= 0; i--){
			if (select_team.options[i].value != "") {
				select_team.options[i] = null;
			}
		} 
		
		$.ajax({
			type:"GET",
			url:"userManage/getRoleList",
			success: function(response) {
				if(response.result == 'error') {
					alert("Internal error, can't get the team list");
				} else {
					var data = response.data;
						
					for(i = 0; i < data.length; i++) {
						var option = document.createElement("OPTION");
						option.value=i;
						option.text=data[i];
						select_role.add(option);
						console.log("Add team option: " + option.text);
					}
				}
			}			
		});
		
		$.ajax({
			type:"GET",
			url:"userManage/getGroupList",
			success: function(response) {
				if(response.result == 'error') {
					alert("Internal error, can't get the team list");
				} else {
					var data = response.data;
						
					for(i = 0; i < data.length; i++) {
						var option = document.createElement("OPTION");
						option.value=i;
						option.text=data[i];
						select_team.add(option);
						console.log("Add team option: " + option.text);
					}
				}
			}			
		});
		
    });
	
	deleteUserRecordBtn.click(function() {
		var selections = $('#userTable').bootstrapTable('getSelections');
		if (selections.length < 1) {
			alert("Please choose at least one record!");
		} else {
			for (var i = 0; i < selections.length; i++) {
				if (confirm("Are you sure to delete user: " + selections[i].name)) {
					
					console.log("delete the record: " + selections[i].name + "from DB");
					
					var data = {
							"name" : selections[i].name
							};
					
					$.ajax({
						type:"GET",
						url:"userManage/deleteUser",
						dataType:"json",
						contentType:"application/json",
						data:data,
						success: function(response) {
							if(response.result == 'error') {
								var errorMessage;
								var field;
								if(response.msg == "noThisUser") {
									alert("Unknown User Name!");
								} else {
									alert("Remote Server busy, please try later!");
								}
							} else {
								$('#userTable').bootstrapTable('refresh', {
									query : {}
								});
							}
						}
					});
				}
			}
		}
    });
	
	modifyUserRecordBtn.click(function() {
		console.log("modifyUserRecord button pressed");
		normalUserBlock.removeClass("hide");
		addNewUsers.addClass("hide");
		modifyUserRecords.removeClass("hide");
		
		var selections = $('#userTable').bootstrapTable('getSelections');
		if (selections.length < 1) {
			alert("Please choose at least one record!");
		} else {
			cur_select_index = 0;
			
			document.getElementById('edit_username').value = selections[cur_select_index].userName;
			document.getElementById('edit_username').readOnly = true;
			document.getElementById('edit_password').value = selections[cur_select_index].password;
			document.getElementById('edit_confirm_password').value = selections[cur_select_index].password;
			document.getElementById('edit_email').value = selections[cur_select_index].email;
			
			var role = selections[cur_select_index].role;
			var team = selections[cur_select_index].group;
			
			//Get select options from backend.
			var select_role = document.getElementById('edit_user_role_select');
			var select_team = document.getElementById('edit_user_team_select');
			
			//first, we need to clear all old options.
			for(var i = select_role.options.length -1; i >= 0; i--){
				if (select_role.options[i].value != "") {
					select_role.options[i] = null;
				}
			} 
			
			for(var i = select_team.options.length -1; i >= 0; i--){
				if (select_team.options[i].value != "") {
					select_team.options[i] = null;
				}
			}
			
			$.ajax({
				type:"GET",
				url:"userManage/getRoleList",
				success: function(response) {
					if(response.result == 'error') {
						alert("Internal error, can't get the role list");
					} else {
						var data = response.data;
											
						for(i = 0; i < data.length; i++) {
							var option = document.createElement("OPTION");
							option.value=i;
							option.text=data[i];
							select_role.add(option);
							console.log("Add role option: " + option.text);
						}
						
						for(var i = 0; i < select_role.options.length; i++){
							console.log("text:[" + i + "]: " + select_role.options[i].text);
							if (select_role.options[i].text == role) {
								select_role.options[i].selected = true;
							}
						} 
					}
				}			
			});
			
			$.ajax({
				type:"GET",
				url:"userManage/getGroupList",
				success: function(response) {
					if(response.result == 'error') {
						alert("Internal error, can't get the team list");
					} else {
						var data = response.data;						
						
						for(i = 0; i < data.length; i++) {
							var option = document.createElement("OPTION");
							option.value=i;
							option.text=data[i];
							select_team.add(option);		
							console.log("Add team option: " + option.text);
						}
						
						for(var i = 0; i < select_team.options.length; i++){
							if (select_team.options[i].text == team) {
								select_team.options[i].selected = true;
							}
						}
					}
				}			
			});
			
			cur_select_index++;
		}
    });
	

	
	// actions of addNewUserForm
	newUserForm.validate({
		rules: {
			username: {
				required: true,
				minlength: 4,
				maxlength: 10,				
				remote: {
					type:"GET",
					url: "userManage/verifyUserName",
					data: {
						userName:function(){
							return $('#username').val();
						}
					}
				}
			},
			password: {
				required: true,
				minlength: 5				
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true				
			},
			role: {
				required: true				
			},
			team: {
				required: true
			}
		},
		messages: {
			username: {
				required: "Please enter a username",
				minlength: "Your username must consist of at least 4 characters",
				maxlength: "Your username must consist of not more than 10 characters",
				remote: "This user name is already used by others"
			},
			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
			},
			email: {
				required: "Please enter a valid email address"
			},
			role: {
				required: "Please choose a role for this user"
			},
			team: {
				required: "Please choose a team for this user"
			}
		},
		invalidHandler: function (event, validator) {
			newUserForm_success.hide();
			newUserForm_error.show();
		},
		highlight: function (element) {
			$(element).addClass('form-control-danger');
	        $(element).closest('.form-group').addClass('has-danger');
	    },
		unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-danger');
			$(element).removeClass('form-control-danger');
			$(element).addClass('form-control-success');
        },
		success: function (label) {
            label.addClass('valid').addClass('help-inline has-success').closest('.form-group').removeClass('has-danger').addClass('has-success');
        }
	});

	newUserSubmitBtn.click(function() {
		console.log("add_new_user_submit_btn button pressed");
		
		if (newUserForm.valid()) {
			var date = new Date();
					
			//var teamVal = $("#edit_user_team_select").find("option:selected").text();
			//var roleVal = $("#edit_user_role_select").find("option:selected").text();
			var teamVal="ILOM";
			var roleVal="user";
			
			if (confirm("Are you sure to add this new user: " + $('#username', newUserForm).val())) {
				var data = {
					name : $('#username', newUserForm).val(),
					pwd : $('#password', newUserForm).val(),
					email : $('#email', newUserForm).val(),
					role : roleVal,
					group : teamVal
				};
				
				console.log(JSON.stringify(data));
					
				$.ajax({
					type:"POST",
					url:"userManage/addUser",
					dataType:"json",
					contentType:"application/json",
					data:JSON.stringify(data),
					success: function(response){
						console.log("run success : " + response.result);
						var result = response.result;
						if (result == "success") {
							console.log("hehehehh");
							alert('Successfully add user');
							$(".portlet-body form input").each(function(){  
								$(this).val('');
								$(this).removeClass("form-control-success");
								$(this).closest('.form-group').removeClass('has-success');
							});  
							$(".portlet-body form select").each(function(){  
								$(this).val('');  
								$(this).removeClass("form-control-success");
								$(this).closest('.form-group').removeClass('has-success');
							});
							
							$('#userTable').bootstrapTable('refresh', {
									query : {}
							});
						} else {
							console.log("aaaaaaaaaaaaaa");
							alert("Internal error, please try later.");	
						}
					}			
				});
			}			
	    } else {
			console.log("form is not valid now");
		}		
    });
	
	newUserCancelBtn.click(function() {
		console.log("add_new_user_cancel_btn button pressed");
		if (confirm("Are you sure to discard all info?")) {
			$(".portlet-body form input").each(function(){  
                $(this).val('');
				$(this).removeClass("form-control-success");
				$(this).closest('.form-group').removeClass('has-success');
            });  
            $(".portlet-body form select").each(function(){  
                $(this).val('');  
				$(this).removeClass("form-control-success");
				$(this).closest('.form-group').removeClass('has-success');
            });	

			normalUserBlock.addClass("hide");
			addNewUsers.addClass("hide");
			modifyUserRecords.addClass("hide");
		}
    });
	

	//actions of modifyUserForm.
	
	//we can't change the user name after we create it, so
	//here we don't make user name changable.
	modifyUserForm.validate({
		rules: {
			edit_password: {
				required: true,
				minlength: 5				
			},
			edit_confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#edit_password"
			},
			edit_email: {
				required: true,
				email: true				
			},
			edit_role: {
				required: true,	
			},
			edit_team: {
				required: true
			}
		},
		messages: {
			edit_password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			edit_confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
			},
			edit_email: {
				required: "Please enter a valid email address"
			},
			edit_role: {
				required: "Please choose a role for this user"
			},
			edit_team: {
				required: "Please choose a team for this user"
			}
		},
		invalidHandler: function (event, validator) {
			console.log("form validiation found some failures");
			modifyUserForm_success.hide();
			modifyUserForm_error.show();
		},
		highlight: function (element) {
			console.log("highlight: " + $(element).attr("name"));
			$(element).addClass('form-control-danger');
	        $(element).closest('.form-group').addClass('has-danger');
	    },
		unhighlight: function (element) {
			console.log("unhighlight: " + $(element).attr("name"));
            $(element).closest('.form-group').removeClass('has-danger');
			$(element).removeClass('form-control-danger');
			$(element).addClass('form-control-success');
        },
		success: function (label) {
			console.log("success: " + $(label).attr("name"));
			modifyUserForm_success.show();
			modifyUserForm_error.hide();
            label.addClass('valid').addClass('help-inline has-success').closest('.form-group').removeClass('has-danger').addClass('has-success');
        },
	});

	modifyUserSubmitBtn.click(function() {
		console.log("modify_user_submit_btn button pressed");
    });
	
	modifyUserCancelBtn.click(function() {
		console.log("modify_user_cancel_btn button pressed");
    });
	
	modifyUserNextBtn.click(function() {
		console.log("modify_user_next_btn button pressed");
    });
	
	
    jQuery('body').on('click', '.portlet .tools .collapse, .portlet .tools .expand', function (e) {
        e.preventDefault();
        
        var el = jQuery(this).closest(".portlet").children(".portlet-body");
        if (jQuery(this).hasClass("collapse")) {
            jQuery(this).removeClass("collapse").addClass("expand");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("expand").addClass("collapse");
            el.slideDown(200);
        }
    });
}



function selectSuperUser(params) {
	var temp = {
		limit : params.limit,
		offset : params.offset,
		role: 'searchSuperUser',
		status : search_status,
	}
	return temp;
}

function superUserTableInit() {
	console.log("Get the super user list from backend, and create the super users Table");
	$('#superUserTable').bootstrapTable(
			{
				columns : [
						{
							checkbox: true
						},
						{
							field : 'name',
							title : 'User Name'
						},
						{
							field : 'pwd',
							title : 'Password'
						},
						{
							field : 'email',
							title : 'E-Mail'
						},
						{
							field : 'role',
							title : 'Role'
						},						
						{
							field : 'group',
							title : 'Team'
						},
						{
							field : 'firstLogin',
							title : 'First Login(Y/N)'
						},
						{
							field : 'lastLoginDate',
							title : 'Last Login Time',
						},
						{
							field : 'state',
							title : 'State',
							align: 'center',
							formatter : function(value, row, index) {
								if (value == 0) {
									return "Disabled";
								} else {
									return "Enabled"
								}
							}
						},
						{
							field : 'activeOps',
							title : 'Enable/Disable',
							align: 'center',
							formatter : function(value, row, index) {
								var enable = '<button class="btn green enable">enable</button>';
								var disable ='<button class="btn red disable">disable</button>';						
								if (row.state == 0) {
									return enable;
								} else {
									return disable;
								}
							},
							events : {
								'click .enable' : function(e, value, row, index) {
									console.log("click enable buttont to enable user now");
								},
								'click .disable' : function(e, value, row, index) {
									console.log("click disable buttont to disable user now");
								}
							}
						}
						],
				url : 'userManage/getUserList',
				onLoadError:function(status){
					//handleAjaxError(status);
				},
				method : 'GET',
				queryParams : selectSuperUser,
				sidePagination : "server",
				dataType : 'json',
				pagination : true,
				pageNumber : 1,
				pageSize : 5,
				pageList : [ 5, 10, 25, 50, 100 ],
				clickToSelect : false
			});
}

function selectUser(params) {
	var temp = {
		limit : params.limit,
		offset : params.offset,
		role: 'searchUser',
		status : search_status,
	}
	return temp;
}

function userTableInit() {
	console.log("Get the user list from backend, and create the users Table");
	$('#userTable').bootstrapTable(
			{
				columns : [
						{
							checkbox: true
						},
						{
							field : 'name',
							title : 'User Name'
						},
						{
							field : 'pwd',
							title : 'Password'
						},
						{
							field : 'email',
							title : 'E-Mail'
						},
						{
							field : 'role',
							title : 'Role'
						},
						{
							field : 'group',
							title : 'Team'
						},						
						{
							field : 'firstLogin',
							title : 'First Login(Y/N)'
						},
						{
							field : 'lastLoginDate',
							title : 'Last Login Time'
						},
						{
							field : 'state',
							title : 'state',
							align: 'center',
							formatter : function(value, row, index) {
								if (value == 0) {
									return "Disabled";
								} else {
									return "Enabled"
								}
							}
						},
						{
							field : 'activeOps',
							title : 'Enable/Disable',
							align: 'center',
							formatter : function(value, row, index) {
								var enable = '<button class="btn green enable">enable</button>';
								var disable ='<button class="btn red disable">disable</button>';						
								if (row.state == 0) {
									return enable;
								} else {
									return disable;
								}
							},
							events : {
								'click .enable': function(e, value, row, index) {
									console.log("click enable buttont to enable user: " + row.name);
									enableUser(row.name, "enable");
								},
								'click .disable': function(e, value, row, index) {
									console.log("click disable buttont to disable user: " + row.name);
									enableUser(row.name, "disable");
								}
							}
						}						
						],
				url : 'userManage/getUserList',
				onLoadError:function(status){
					//handleAjaxError(status);
				},
				method : 'GET',
				queryParams : selectUser,
				sidePagination : "server",
				dataType : 'json',
				pagination : true,
				pageNumber : 1,
				pageSize : 5,
				pageList : [ 5, 10, 25, 50, 100 ],
				clickToSelect : false
			});
}