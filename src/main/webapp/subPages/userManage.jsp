<script src="${pageContext.request.contextPath}/js/userManage.js" type="text/javascript"></script>

<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title">
			Account Manage  
			 <small>User account Add/Modify/Delete</small>
			</h3>
		</div>
	</div>
	
	<hr>

	<div class="row-fluid">
		<div class="span12">
			<div class="alert alert-success">
				Super User has great power to manage the whole system, including add/modify/delete user, machines. This role also can change reserve rule, retire machines, etc.<br>
				<span class="label label-important">NOTE:</span>&nbsp;If you want to add/modify/delete an Admin account, please discuss within teams.		
			</div>
			<div class="portlet box purple">

				<div class="portlet-title">
					<div class="caption"><i class="fa fa-users"></i>Super User</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
						<a href="javascript:;" class="reload" id="reloadSuperUserTable"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="clearfix">
						<div class="btn-group">
							<button id="addNewAdminRecord" class="btn green">
							<i class="fa fa-user-plus"></i> Add New 
							</button>
						</div>
						<div class="btn-group">
							<button id="deleteAdminRecord" class="btn red">
							 <i class="fa fa-trash"></i> Delete
							</button>
						</div>
						<div class="btn-group">
							<button id="modifyAdminRecord" class="btn purple">
							 <i class="fa fa-pencil"></i> Edit
							</button>
						</div>

						<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">Tools
							</button>							
							<ul class="dropdown-menu pull-right">
								<li><a href="#"><i class="fa fa-print"></i> Print</a></li>
								<li><a href="#"><i class="fa fa-upload"></i> Export to Excel</a></li>
							</ul>
						</div>

					</div>

					<table class="table table-striped table-bordered table-hover" id="superUserTable">
					</table>
				</div>
			</div>

		</div>
	</div>
	
	<hr>
	
	<div class="row-fluid">
		<div class="span12">
			<div class="alert alert-success">
				Normal user can reserve the server, and can see some statistics about his/her past operations.		
			</div>
			<div class="portlet box green">

				<div class="portlet-title">
					<div class="caption"><i class="fa fa-users"></i>Users</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
						<a href="javascript:;" class="reload" id="reloadUserTable"></a>
					</div>
				</div>

				<div class="portlet-body">
					<div class="clearfix">
						<div class="btn-group">
							<button id="addNewUserRecord" class="btn green">
							<i class="fa fa-user-plus"></i> Add New 
							</button>
						</div>
						<div class="btn-group">
							<button id="deleteUserRecord" class="btn red">
							 <i class="fa fa-trash"></i> Delete
							</button>
						</div>
						<div class="btn-group">
							<button id="modifyUserRecord" class="btn purple">
							 <i class="fa fa-pencil"></i> Edit
							</button>
						</div>

						<div class="btn-group pull-right">
							<button class="btn dropdown-toggle" data-toggle="dropdown">Tools
							</button>							
							<ul class="dropdown-menu pull-right">
								<li><a href="#"><i class="fa fa-print"></i> Print</a></li>
								<li><a href="#"><i class="fa fa-upload"></i> Export to Excel</a></li>
							</ul>
						</div>

					</div>

					<table class="table table-striped table-bordered table-hover" id="userTable">
					</table>
				</div>
			</div>

		</div>
	</div>

</div>
