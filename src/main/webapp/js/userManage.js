search_user_role = 'all'
search_user_name = null
search_status = null


$(function() {
	userPageInit();
	
	superUserTableInit();
	userTableInit();

});


function userPageInit() {
	   
	$('#reloadSuperUserTable').click(function() {
		console.log("reloadSuperUserTable button pressed");
    	$('#superUserTable').bootstrapTable('refresh', {
    		query : {}
    	});
    });
	
	$('#reloadUserTable').click(function() {
		console.log("reloadUserTable button pressed");
    	$('#userTable').bootstrapTable('refresh', {
    		query : {}
    	});
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