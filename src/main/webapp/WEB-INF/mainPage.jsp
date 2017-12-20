<!DOCTYPE html>
<head>

	<meta charset="utf-8" />
	<title>Main Page</title>
	<meta content="width=device-width, height=device-height, initial-scale=1.0" name="viewport" />
	<meta content="Main page after login" name="description" />
    <meta content="alex wang" name="author" />

    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/style-shsp.css" rel="stylesheet" type="text/css"/>
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
	<link href="css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="css/uniform.default.css" rel="stylesheet" type="text/css"/>
</head>

<body class="page-header-fixed">

    <!-- Header Begins -->
    <div class="header navbar navbar-dark bg-dark nav-fixed-top">

        <div class="navbar-inner">
            <div class="container-fluid">

                <a class="navbar-brand" href="mainPage.html">
                    <img src="image/O-x86Servers-icon-mid.png" alt="logo"/>
                </a>

                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
					<img src="image/menu-toggler.png" alt="" />
				</a>          
				<!-- END RESPONSIVE MENU TOGGLER -->  

                <ul class="nav pull-right">
                
                    <li class="dropdown" id="header_notification_bar">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-warning"></i>
                            <span class="badge">6</span>
                        </a>

                        <ul class="dropdown-menu extended notification">
                            <li>
                                <p> You have "Alex Message" new notification</p>
                            </li>
                            <li>
                                <a class="dropdown-tiem" href="#">
                                    <span class="label label-success">
                                        <i class="icon-plus"></i>
                                    </span>
                                    Alex Message 1
                                    <span class="time">Alex fix</span>
                                </a>
                            </li>
                            
                            <li>
                                <a calss="dropdown-item" href="#">
                                    <span class="label label-success">
                                        <i class="icon-plus"></i>
                                    </span>
                                    Alex Message 2
                                    <span class="time">Alex fix</span>
                                </a>
                            </li>
                        </ul>
                        
                    </li>
                    
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown user">
						<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
						<img alt="" src="image/oracle-icon-small.png" />
						<span class="username">Bin Wang</span>
						</a>

						<ul class="dropdown-menu">
							<li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a></li>
							<li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>
							<li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox(3)</a></li>
							<li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>
							<div class="dropdown-divide"></div>
							<li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->

				</ul>
                
            </div>
        </div>

		
    </div>
    <!-- Header Ends -->


	<div class="page-container">

		<!-- sidebar begins -->
        <div class="page-sidebar nav-collapse collapse show">
		
            <ul class="page-sidebar-menu"> 

			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			<li>
				<div class="sidebar-toggler"></div>
			</li>
			<li>
				<form class="sidebar-search">
					<div class="input-box">
						<div class="text">Welcome!<div>
					</div>
				</form>
			</li>
			
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				
				<li class="start active">
					<a href="javascript:;">
						<i class="fa fa-home"></i> 
						<span class="title">Dashboard</span>
					</a> 
				</li>
				
				<li class="">
					<a href="javascript:;">
						<i class="fa fa-cubes"></i> 
						<span class="title">System Setting</span>
						<span class="arrow "></span>
					</a>

					<ul class="sub-menu">
						<li class="roleManage">
							<a href="javascript:;" class="menu_item" name="subPages/roleManage.html">
							Role Management</a>
						</li>

						<li class="authManage">
							<a href="javascript:;" class="menu_item" name="subPages/authManage.jsp">
							Authority Management </a>
						</li>
					</ul>

				</li>
				
				<li class="">
					<a href="javascript:;">
						<i class="fa fa-users"></i> 
						<span class="title">User Management</span>
						<span class="arrow "></span>
					</a>

					<ul class="sub-menu">
						<li>
							<a href="javascript:;" class="menu_item" name="subPages/accountManage.jsp">
							Account Management</a>
						</li>

						<li>
							<a href="javascript:;" class="menu_item" name="subPages/userStatus.jsp">
							Users Status</a>
						</li>
						<li>
							<a href="javascript:;" class="menu_item" name="subPages/roleSetting.jsp">
							Users Role Setting</a>
						</li>
					</ul>

				</li>
				
				<li class="">
					<a href="javascript:;">
						<i class="fa fa-server"></i> 
						<span class="title">Server Management</span>
						<span class="arrow "></span>
					</a>

					<ul class="sub-menu">
						<li>
							<a href="javascript:;" class="menu_item" name="subPages/addMachineType.jsp">
							Add Server Type</a>
						</li>
						<li>
							<a href="javascript:;" class="menu_item" name="subPages/machineAddDel.jsp">
							Machine Add/Delete</a>
						</li>
						<li>
							<a href="javascript:;" class="menu_item" name="subPages/machineStatus.jsp">
							Machine Status</a>
						</li>						
						
					</ul>

				</li>
				
				<li class="">
					<a href="javascript:;">
						<i class="fa fa-tag"></i> 
						<span class="title">Machine Reserve</span>
						<span class="arrow "></span>
					</a>

					<ul class="sub-menu">
						<li>
							<a href="javascript:;" class="menu_item" name="subPages/reserveMachine.jsp">
							Reserve A Server</a>
						</li>
					</ul>

				</li>
            
			</ul>
        </div>
		<!-- sidebar ends -->

		<!-- Page content gegins -->
		<div class="page-content">
			<div class="panel">
			</div>
		</div>
		<!-- Page content ends -->
	
	</div>
	
	<!-- BEGIN FOOTER -->
	<div class="footer">
		
		<div class="footer-inner">
			<img src="image/o-Signature-footer.png"/>
			2017 &copy; Oracle X86 Shanghai SP.
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="fa fa-angle-double-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->


	<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/popper.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/jquery.md5.js" type="text/javascript"></script>
	<script src="js/mainPage.js" type="text/javascript"></script>   
</body>