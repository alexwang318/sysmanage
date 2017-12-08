<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css">
<link rel="sytlesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="login">

	<!--  BEGIN LOGO -->
	<div class="logo">
		<img src="${pageContext.request.contextPath}/image/logo-big.png" alt="">
	</div>
	
	<div class="content">
		<form class="form-vertical login-form">
		<h3 class="form-title">Sign In</h3>
		<div class="alert alert-error hide">
			<button class="close" data-dismiss="alert"></button>
			<span>Enter user name and password.</span>
		</div>
		
		<div class="control-group">
			<label class="control-label visible-ie8 visible-ie9">User Name</label>
			<div class="controls">
				<div class="input-icon left">
					<i class="icon-user"></i>
					<input class="m-wrap placeholder-no-fix" type="text" placeholder="User Name" name="userName"/>
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label visible-ie8 visible-ie9">Password</label>
			<div class="controls">
				<div class="input-icon left">
					<i class="icon-lock"></i>
					<input class="m-wrap placeholder-no-fix" type="password" placeholder="Password" name="password"/>
				</div>
			</div>
		</div>
		
		<div class="form-actions">
			<label class="checkbox">
				<input type="checkbox" name="rememberMe" value="1"/> Remember me
			</label>
			<button type="submit" class="btn green pull-right">
				Login <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
		
		</form>
	</div>
	
	<div class="copyright">
		2017 &copy; Oracle X86 System China SP team.
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.md5.js"></script>

<script>
jQuery(document).ready(function() {     
  Login.init();
});
</script>


</body>
</html>