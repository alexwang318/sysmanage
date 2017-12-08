/**
 * create for login page.
 */
function infoEncrypt(userName, password, checkCode) {
	var str1 = $.md5(password);
	var str2 = $.md5(str1 + userName);
	
	return str2;
}

var login = function () {
	
	return {
		
		// Initialization
		initialize: function () {
			
			// Validation first
			$('.login-form').validate({
				
				errorElemet: 'label',
				errorClass: 'help-inline',
				focusInvalid: false,
				
				rules: {
					userName: {
						required: true
					},
					password: {
						required: true
					},
					rememberMe: {
						required: false
					}
				},
				
				message: {
					userName: {
						required: "User Name is required."
					},
	                password: {
	                    required: "Password is required."
	                }
				},
				
				invalidHandler: function() {
					$('.alert-error', $('.login-form')).show();
				},
				
				highlight: function(element) {
					$(element).closest('.control-group').addClass('error');
				},
				
				success: function(label) {
					label.cloest('.control-group').removeClass('error');
					label.remove();
				},
				
				errorPlacement: function(error, element) {
					error.addClass('help-small no-left-padding').insertAfter(element.cloest('.input-icon'));
				},
				
				submitHandler: function(form) {
					var userName = $('#userName').val();
					var password = $('#password').val();
					
					console.log(userName);
					console.log(password);
					
					password = infoEncrypt(userName, password)
					

					console.log("Encrypted password: " + password);
					
					var data = {
						"userName" : userName,
						"password" : password,
					}
					$.ajax({
						type:"POST",
						url:"account/login",
						dataType:"json",
						contentType:"application/json",
						data:JSON.stringify(data),
						success: function(response) {
							if(response.result == 'error') {
								var errorMessage;
								var field;
								if(response.msg == "unknownAccount") {
									errorMessage = "Unknown User Name;"
									field = "userName";
								} else if (response.msg == "wrongCredentials") {
									errorMessage = "Wrong Password";
									field = "password";
									$('#password').val("");
								} else {
									errorMessage = "Remote Server busy, please try later";
									field = "password";
									$('#password').val("");
								}
								
								bv.updateMessage(field, 'callback', errorMessage);
								bv.updateStatus(field, 'INVALID', 'callback');
								
							} else {
								windows.location.href = "main";
							}
						},
						error: function(data) {
							console.log("submit error")
						}
					});					
				}
				
			});
		}
	}
}