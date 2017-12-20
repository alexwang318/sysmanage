$(function() {
	var responsiveHandlers = [];

	handleSidebarState();
	adjustSidebarAndContentHeight();
	handleSidebarMenu();
	handleSidebarToggler();
	handleGoTop();
	
	handleDropdowns();

	handleSidebarClick();
});


var adjustSidebarAndContentHeight = function() {
        var content = $('.page-content');
        var sidebar = $('.page-sidebar');
        var body = $('body');
        var height;

        if (body.hasClass("page-footer-fixed") === true && body.hasClass("page-sidebar-fixed") === false) {
            var available_height = $(window).height() - $('.footer').height();
            if (content.height() <  available_height) {
                content.attr('style', 'min-height:' + available_height + 'px !important');
            }
        } else {
            if (body.hasClass('page-sidebar-fixed')) {
                height = _calculateFixedSidebarViewportHeight();
            } else {
                height = sidebar.height() + 20;
            }
            if (height >= content.height()) {
                content.attr('style', 'min-height:' + height + 'px !important');
            } 
        } 
}

var handleSidebarState = function () {
    // remove sidebar toggler if window width smaller than 900(for table and phone mode)
    if ($(window).width() < 980) {
        $('body').removeClass("page-sidebar-closed");
    }
}

var runResponsivefunc = function () {
        // reinitialize other subscribed elements
        for (var i in responsiveHandlers) {
            var each = responsiveHandlers[i];
            each.call();
        }
    }

var handleDropdowns = function () {
    $('body').on('click', '.dropdown-menu.hold-on-click', function(e){
        e.stopPropagation();
    })
}

var scrollTo = function (el, offeset) {
    pos = el ? el.offset().top : 0;
    jQuery('html,body').animate({
            scrollTop: pos + (offeset ? offeset : 0)
        }, 'slow');
}

var handleGoTop = function () {
    /* set variables locally for increased performance */
    jQuery('.footer').on('click', '.go-top', function (e) {
            scrollTo();
            e.preventDefault();
        });
}

var handleSidebarToggler = function () {
	console.log("handleSidebarToggler");
	
	$('.page-sidebar').on('click', '.sidebar-toggler', function (e) {
		var body= $('body');
		var sidebar = $('page-sidebar');

        if ((body.hasClass("page-sidebar-hover-on")) || sidebar.hasClass('page-sidebar-hovering')) {
                body.removeClass('page-sidebar-hover-on');
				console.log("add show to sidebar");
                sidebar.css('width', '').hide().show();
                e.stopPropagation();
                runResponsivefunc();
                return;
            }

            if (body.hasClass("page-sidebar-closed")) {
                body.removeClass("page-sidebar-closed");
                if (body.hasClass('page-sidebar-fixed')) {
                    sidebar.css('width', '');
                }
				$(".sidebar-search", sidebar).show();
            } else {
                body.addClass("page-sidebar-closed");
				$(".sidebar-search", sidebar).hide();
            }
            runResponsivefunc();
		});
}

var handleSidebarClick = function() {
	$(".menu_item").click(function() {
		var url = $(this).attr("name");
		console.log("start to load"+name);
		//$('page-content .panel').mLoading('show');
		$('.page-content').load(url);
	});
}

var handleSidebarMenu = function () {
	
    jQuery('.page-sidebar').on('click', 'li > a', function (e) {
            if ($(this).next().hasClass('sub-menu') == false) {
                if ($('.btn-navbar').hasClass('collapsed') == false) {
					console.log("btn-navbar has collapsed");
                    $('.btn-navbar').click();
                } else {
                	console.log("btn-navbar hs no collapsed class");
                }
                return;
            }

            var parent = $(this).parent().parent();

            parent.children('li.open').children('a').children('.arrow').removeClass('open');
            parent.children('li.open').children('.sub-menu').slideUp(200);
            parent.children('li.open').removeClass('open');

            var sub = jQuery(this).next();
            if (sub.is(":visible")) {
                jQuery('.arrow', jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(200, function () {
                        adjustSidebarAndContentHeight();
                    });
            } else {
                jQuery('.arrow', jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
				parent.children('li.open').children('.sub-menu').css('margin-left', '-40px');
                sub.slideDown(200, function () {
						adjustSidebarAndContentHeight();
                    });
            }

            e.preventDefault();
        });
}

