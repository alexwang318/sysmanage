$(function() {
	adminTableInit();
	userTableInit();
	
	clickEventInit();
});

function accountPageInit() {
	
	//Init the reload event
    jQuery('body').on('click', '.portlet .tools a.reload', function (e) {
        console.log("click reload button");

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

function adminTableInit() {
	console.log("Get the admin list from backend, and create the admin Table");
}

function userTableInit() {
	console.log("Get the user list from backend, and create the users Table");
}


function clickEventInit() {
	
}