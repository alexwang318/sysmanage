$(function() {
	
	getServerTypeList();
	pageInit();
});

function PreviewImage(imgFile) {
	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
	
	if (!pattern.test(imgFile.value)) { 
      alert("Only support jpg/jpeg/png/gif/bmp format now！");  
      imgFile.focus(); 
    } else {
		var path;
		
		if(document.all){ 
			// for IE
			imgFile.select(); 
			path = document.selection.createRange().text;
			document.getElementById("imgPreview").innerHTML=""; 
			document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";
       }else{
			path = URL.createObjectURL(imgFile.files[0]);
			document.getElementById("imgPreview").innerHTML = 
			"<a href='" + path + "'" + " data-fancybox data-caption='" + $('#typename').val() + "'>" + 
			"<div class='zoom'>" +
			"<img class='type-picture' src='" + path + "'/>" +
			"<div class='zoom-icon'></div>" +
			"</div>" +
			"</a>"; 
       }
		
	}
}

function getRootPath() {
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(projectName);	
}

function processServerTypeBlock(rows) {
	var projectName=getRootPath();
	var innerHtml = "";
	var tempHtml;
	var serverTypeDir="/image/server_type_pic/";
	
	console.log("root Path: " + projectName);
	
	for (i = 0; i < rows.length; i++) {
		tempHtml = "<div class='row-fluid'>" +
						"<div class='row'>" +
							"<div class='col-md-4 item'>" +
								"<a href='" + projectName + serverTypeDir + rows[i].name + "'" + " data-fancybox data-caption='" + rows[i].name + "'>" +
									"<div class='zoom'>" +
										"<img class='type-picture' src='" + projectName + serverTypeDir + rows[i].name + "'>" +
										"<div class='zoom-icon'></div>" +
									"</div>" +
								"</a>" +
							"</div>" +
							"<div class='col-md-8'>" +
								"<h2 class='server_type'>" + rows[i].name + "</h2>" +
								"<p>" + rows[i].description + "</p>" +
								"<a class='btn blue' href='" + rows[i].url + "' target='_blank'>" +
									"More info <i class='fa fa-tags'></i>" +
								"</a>" +
								"<button type='button' id='delete_btn_" + rows[i].name + "' class='btn red pull-right'><i class='fa fa-trash'></i> Delete</button>" +
							"</div>" +
						"</div>" +
					"</div>" +
					"<hr>";
					
		innerHtml += tempHtml;
		

	}	
		
	document.getElementById("serverTypeBlock").innerHTML = innerHtml;
	
	for (i = 0; i < rows.length; i++) {
		document.getElementById('delete_btn_' + rows[i].name).onclick = function(){
			console.log("delete button was clicked!");
			console.log($(this).parent().children('h2').text());
		}
	}
}

function getServerTypeList() {
	var data = {
		offset: -1,
		limit: -1
	};
	
	$.ajax({
		type: "GET",
		url:"machineManage/getServerTypeList",
		dataType: 'json',
		data:data,
		success: function(response) {
				if (response.result == 'success') {
					processServerTypeBlock(response.rows);
				}
		}
	});
}

function pageInit() {
	
	$('#add_new_btn').click( function() {
		jQuery('html,body').animate({
            scrollTop: $('.form-horizontal').offset().top
        }, 'slow');
	});
	

	
	
	
	
	$('#submit_btn').click(function() {
		console.log($('#picture').val());
		
		if(confirm("Do you want to add this type?")) {
			var formData = new FormData();
			
			formData.append("name", $('#typename').val());
			formData.append("url", $('#url').val());
			formData.append("description", $('#description').val());
			formData.append("picture", $('#picture').get(0).files[0]);
			
			console.log($('#picture').get(0).files[0]);
			
			
			$.ajax({
				type: "POST",
				url:"machineManage/addServerType",
				processData:false,
				contentType:false,
				async: false,
				cache: false,
				data:formData,
				success: function(response) {
					var result = response.result;
					if (result == "success") {
						$(".form-horizontal input").each(function(){  
							$(this).val('');
							$(this).removeClass("form-control-success");
							$(this).closest('.form-group').removeClass('has-success');
						});

						$(".form-horizontal textarea").each(function(){  
							$(this).val('');
							$(this).removeClass("form-control-success");
							$(this).closest('.form-group').removeClass('has-success');
						});
					} else {
						alert("Internal error, please try later.");
					}
				}
			});
		}
	});

	$('#cancel_btn').click(function() {
		console.log("cancel_btn button pressed");
		if (confirm("Are you sure to discard all info?")) {
			$(".form-horizontal input").each(function(){  
                $(this).val('');
				$(this).removeClass("form-control-success");
				$(this).closest('.form-group').removeClass('has-success');
            });

			$(".form-horizontal textarea").each(function(){  
                $(this).val('');
				$(this).removeClass("form-control-success");
				$(this).closest('.form-group').removeClass('has-success');
            });
		}
	});

	$('#typeEditForm').validate({
		rules: {
			typename: {
				required: true,
				minlength: 4,
				maxlength: 20,				
				remote: {
					type:"GET",
					url: "machineManage/verifyServerTypeName",
					data: {
						name:function(){
							return $('#typename').val();
						}
					}
				}
			},
			url: {
				required: true	
			},
			description: {
				minlength: 10,
				maxlength: 1024	
			}
		},
		messages: {
			typename: {
				required: "Please enter a type name",
				minlength: "Your username must consist of at least 4 characters",
				maxlength: "Your username must consist of not more than 20 characters",
				remote: "This type name is already added by others"
			},
			url: {
				required: "Please provide a url for this type."
			},
			description: {
				required: "Please provide the description about this type",
				minlength: "Description must be at least 10 characters long",
				maxlength: "Description must consist of not more than 1024 characters"
			}
		},
		invalidHandler: function (event, validator) {
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
