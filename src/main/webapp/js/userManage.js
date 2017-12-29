search_user_role = 'all'
search_user_name = null
search_status = null


$(function() {
	userPageInit();
	
	superUserTableInit();
	userTableInit();

});

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
	var normalUserBlock = $('#normalUserBlock');
	var modifyUserRecord = $('#modifyUserRecord');
	var reloadUserTblBtn = $('#reloadUserTable');
	var addNewUserRecordBtn = $('#addNewUserRecordBtn');
	var deleteUserRecordBtn = $('#deleteUserRecordBtn');
	var modifyUserRecordBtn = $('#modifyUserRecordBtn');
	var addNewUsers = $('#add_new_users');
	var modifyUserRecords = $('#modify_users');

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
		
		//Get role and group select options from backend.
		var select_role = document.getElementById("new_user_role_select");
		var select_team = document.getElementById("new_user_team_select");
		
		$.ajax({
			type:"GET",
			url:"userManage/getGroupList",
			success: function(response) {
				if(response.result == 'error') {
					alert("Internal error, can't get the team list");
				} else {
					var data = response.data;
					
					select.empty();
						
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
				if (confirm("Are you sure to delete user: " + selections[i].userName)) {
					
					console.log("delete the record: " + selections[i].userName + "from DB");
					
					var data = {
							"userName" : selections[i].userName
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
							}
						}
					});
				}
			}
			
			$('#userTable').bootstrapTable('refresh', {
	    		query : {}
	    	});
			
		}
    });
	
	modifyUserRecordBtn.click(function() {
		console.log("modifyUserRecord button pressed");
		normalUserBlock.removeClass("hide");
		addNewUsers.addClass("hide");
		modifyUserRecords.removeClass("hide");
		
		// Need to do some action here.
    });
	

	
	// actions of addNewUserForm
	var newUserForm = $('#addNewUserForm');
	var newUserForm_error = $('.alert-error', newUserForm);
	var newUserForm_success = $('.alert-success', newUserForm);
	var newUserSubmitBtn = $('#add_new_user_submit_btn', newUserForm);
	var newUserCancelBtn = $('#add_new_user_cancel_btn', newUserForm);
	
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
			console.log("form validiation found some failures");
			newUserForm_success.hide();
			newUserForm_error.show();
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
            label.addClass('valid').addClass('help-inline has-success').closest('.form-group').removeClass('has-danger').addClass('has-success');
        },
	});

	newUserSubmitBtn.click(function() {
		console.log("add_new_user_submit_btn button pressed");
		
		if (newUserForm.valid()) {
			var userName = $('#username', newUserForm).val();
			var password = $('#password', newUserForm).val();
			var confirm_password = $('#confirm_password', newUserForm).val();
			var email = $('#email', newUserForm).val();
			var role = $('#role', newUserForm).val();
			var team = $('#team', newUserForm).val();
			var firstLogin = 1;
			var accessIP = "";
			var lastLogDate="";
			var status = 0;
			
			console.log(userName + "," + password + "," + confirm_password + "," + email + "," + role);
			
	    }		
    });
	
	newUserCancelBtn.click(function() {
		console.log("add_new_user_cancel_btn button pressed");
		if (confirm("Are you sure to discard all info?")) {
			newUserForm.resetForm();
		}
    });
	

	//actions of modifyUserForm.
	var modifyUserForm = $('#modifyUserForm');
	var modifyUserForm_error = $('.alert-error', modifyUserForm);
	var modifyUserForm_success = $('.alert-success', modifyUserForm);
	var modifyUserUpdateSubmitBtn = $('#modify_user_submit_btn', modifyUserForm);
	var modifyUserUpdateCancelBtn = $('#modify_user_cancel_btn', modifyUserForm);
	var modifyUserUpdateNextBtn = $('#modify_user_next_btn', modifyUserForm);
	
	//we can't change the user name after we create it, so
	//here we don't make user name changable.
	modifyUserForm.validate({
		rules: {
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
			team: {
				required: true
			}
		},
		messages: {
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
			team: {
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

	modifyUserUpdateSubmitBtn.click(function() {
		console.log("modify_user_submit_btn button pressed");
    });
	
	modifyUserUpdateCancelBtn.click(function() {
		console.log("modify_user_cancel_btn button pressed");
    });
	
	modifyUserUpdateNextBtn.click(function() {
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
							field : 'userName',
							title : 'User Name'
						},
						{
							field : 'password',
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
							field : 'status',
							title : 'Status',
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
				clickToSelect : true
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
							field : 'userName',
							title : 'User Name'
						},
						{
							field : 'password',
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
							field : 'status',
							title : 'Status'
						},
						{
							field : 'activeOps',
							title : 'Active/De-active',
							formatter : function(value, row, index) {
								var active = '<button class="btn btn-info btn-sm"><span>Active</span></button>';
								var deactive ='<button class="btn btn-info btn-sm"><span>Deactive</span></button>';
								var selections = $('#userTable').bootstrapTable('getSelections');
								if (selections[row].status == 0) {
									return active;
								} else {
									return deactive;
								}
							},
							events : {
								'click .active': function(e, value, row, index) {
									console.log("click active buttont to active user now");
								},
								'click .deactive': function(e, value, row, index) {
									console.log("click deactive buttont to deactive user now");
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
				clickToSelect : true
			});
}