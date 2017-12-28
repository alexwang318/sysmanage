<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>Login Page</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="SP Team Login Page" name="description" />
	<meta content="Alex Wang" name="author" />
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>	<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/style-shsp.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${pageContext.request.contextPath}/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>

</head>
<body class="login">
	<div class="logo">
		<img src="${pageContext.request.contextPath}/image/Oracle-log-small.png" alt="" /> 
	</div>
	<div class="content">
		<form class="form-vertical login-form">
			<h3 class="form-title">Login to your account</h3>
			<div class="alert alert-danger hide">
				<button class="close" data-dismiss="alert"></button>
				<span>Enter any username and password.</span>
			</div>
			<div class="form-group">				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" id="username" name="username"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-lock"></i>
						<input class="m-wrap placeholder-no-fix" type="password" placeholder="Password" id="password" name="password"/>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				<input type="checkbox" name="remember" value="1"/> Remember me
				</label>
				<button type="submit" class="btn green pull-right">
				Login <i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
				</button>
			</div>
			<div class="forget-password">
				<h4>Forgot your password ?</h4>
				<p>					Click <a href="javascript:;" class="" id="forget-password">here</a>					to reset your password.
				</p>
			</div>
			<div class="create-account">
				<p>
					Don't have an account yet ?&nbsp; 
					<a href="javascript:;" id="register-btn" class="">Create an account</a>
				</p>
			</div>
		</form>
		<form class="form-vertical forget-form">
			<h3 class="">Forget Password ?</h3>
			<p>Enter your e-mail address below to reset your password.</p>
			<div class="form-group">
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-envelope"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				<button type="button" id="back-btn" class="btn">
				<i class="fa fa-arrow-circle-left"></i> Back
				</button>
				<button type="submit" class="btn green pull-right">
				Submit <i class="fa fa-arrow-circle-right"></i>
				</button>
			</div>
		</form>
		<form class="form-vertical register-form">
			<h3 class="">Sign Up</h3>
			<p>Enter your account details below:</p>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" name="username"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="controls">					<div class="input-icon left">
						<i class="fa fa-lock"></i>
						<input class="m-wrap placeholder-no-fix" type="password" id="register_password" placeholder="Password" name="password"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-check"></i>
						<input class="m-wrap placeholder-no-fix" type="password" placeholder="Re-type Your Password" name="rpassword"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Email</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="fa fa-envelope"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="controls">
					<label class="checkbox">
					<input type="checkbox" name="tnc"/> I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>
					</label>  
					<div id="register_tnc_error"></div>
				</div>
			</div>
			<div class="form-actions">
				<button id="register-back-btn" type="button" class="btn">
				<i class="fa fa-arrow-circle-left"></i>  Back
				</button>
				<button type="submit" id="register-submit-btn" class="btn green pull-right">
				Sign Up <i class="fa fa-arrow-circle-right"></i>
				</button>
			</div>
		</form>
	</div>
	<div class="copyright">
		2018 &copy; X86 Shanghai SP Team.
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/js/tether.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/js/popper.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/js/jquery.md5.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/login.js" type="text/javascript"></script>      
	<script>		
	jQuery(document).ready(function() { 
			  Login.init();
			});	
	</script>
	</body>
</html>