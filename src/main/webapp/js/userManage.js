search_user_role = 'all'
search_user_name = null
search_status = null


$(function() {
	userPageInit();
	
	//adminTableInit();
	userTableInit();

});


function selectUsers(params) {
	var temp = {
		limit : params.limit,
		offset : params.offset,
		role: 'admin',
		status : search_status,
	}
	return temp;
}

function userPageInit() {
	   
	$('#reloadAdminTable').click(function() {
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

function userTableInit() {
	console.log("Get the user list from backend, and create the users Table");
	$('#adminTable').bootstrapTable(
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
							field : 'email',
							title : 'E-Mail'
						},
						{
							field : 'lastLoginTime',
							title : 'Last Login Time',
						},
						{
							field : 'status',
							title : 'Status',
							visible : false
						}
						],
				url : 'userManage/getUserList',
				onLoadError:function(status){
					//handleAjaxError(status);
				},
				method : 'GET',
				queryParams : selectUsers,
				sidePagination : "server",
				dataType : 'json',
				pagination : true,
				pageNumber : 1,
				pageSize : 5,
				pageList : [ 5, 10, 25, 50, 100 ],
				clickToSelect : true
			});
}