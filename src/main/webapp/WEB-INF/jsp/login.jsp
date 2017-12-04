<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="wrap">
            
                <p class="form-title">Sign In</p>
                    
                <form id="login_form" class="login">
	                <input type="text" placeholder="Username" id="userName" name="userName"/>
	                <input type="password" placeholder="Password" id="password" name="password"/>
	                <input type="submit" value="Sign In" class="btn btn-success btn-sm" />
                </form>
                
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.md5.js"></script>

<script>
$(function() {
	validationInit();
});

function infoEncrypt(userName, password, checkCode) {
	var str1 = $.md5(password);
	var str2 = $.md5(str1 + userName);
	
	return str2;
}

function validationInit() {
	$('#login_form').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh',			
		},
		fields: {
			userName : {
				validators : {
					notEmpty : {
						message : 'The username cannot be empty'
					},
					callback : {}
				}
			},
			password: {
				validators : {
					notEmpty : {
						message : 'The password cannot be empty'
					},
					callback : {}
				}
			},
		}
	})
	.on('success.form.bv', function(e) {
		e.preventDefault();
		
		var $form = $(e.target);
		var bv = $form.data('bootstrapValidator');
		
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
					windows.location.href = "sysmanage";
				}
			},
			error: function(data) {
				
			}
		});
	});
}


</script>


</body>
</html>