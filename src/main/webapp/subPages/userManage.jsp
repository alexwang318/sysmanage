
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
				User can reserve the server, and can see some statistics about his/her past operations.		
			</div>
			<div class="portlet box yellow">

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
							<button id="addNewUserRecordBtn" class="btn green">
							<i class="fa fa-user-plus"></i> Add New 
							</button>
						</div>
						<div class="btn-group">
							<button id="deleteUserRecordBtn" class="btn red">
							 <i class="fa fa-trash"></i> Delete
							</button>
						</div>
						<div class="btn-group">
							<button id="modifyUserRecordBtn" class="btn purple">
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
					
		<div class="row hide" id="normalUserBlock">
			<div class="col-md-8 hide" id="add_new_users">
				<div class="portlet box green">

					<div class="portlet-title">
						<div class="caption"><i class="fa fa-reorder"></i>Add A New User</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
				
					<div class="portlet-body form">
						<form action="#" id="addNewUserForm" class="form-horizontal">	
								
								<div class="alert alert-danger hide">
									You have some form errors. Please check below.
								</div>
								
								<div class="alert alert-success hide">
									It's OK to add new User now!
								</div>
						
								<div class="form-group row">
									<label for="username" class="col-2 form-control-label">User Name<span class="required">*</span></label>
									<div class="col-md-6">
										<input class="form-control col-md-12" type="text" name="username" id="username" placeholder="">
									</div>
								</div>

								<div class="form-group row">
								  <label for="password" class="col-2 form-control-label">Password<span class="required">*</span></label>
								  <div class="col-md-6">
									<input class="form-control col-md-12" type="password" value="" name="password" id="password">
								  </div>
								</div>
								
								<div class="form-group row">
								  <label for="confirm_password" class="col-2 form-control-label">Confirm Password<span class="required">*</span></label>
								  <div class="col-md-6">
									<input class="form-control col-md-12" type="password" value="" name="confirm_password" id="confirm_password" >
								  </div>
								</div>		

								<div class="form-group row">
									<label for="email" class="col-2 form-control-label">Email<span class="required">*</span></label>
									<div class="col-md-6">
									  <input type="email" class="form-control col-md-12" name="email" id="email" placeholder="Email" >
									</div>
								</div>	
						
								<div class="form-group row">	
								  <label class="col-2 form-control-label">Team<span class="required">*</span></label>
								  <div class="col-md-6">
									<select class="form-control col-md-12" name="team" id="new_user_team_select" >
										<option value="">Select a Team</option>
									</select>
								  </div>
								</div>

								<div class="form-actions">	
									<button type="button" id="add_new_user_submit_btn" class="btn green">Add Now</button>
									<button type="button" id="add_new_user_cancel_btn" class="btn yellow">Cancel</button>	
								</div>

							</form>
					</div>							
				</div>			
			</div>
			
			<div class="col-md-8 hide" id="modify_users">
				<div class="portlet box green">

					<div class="portlet-title">
						<div class="caption"><i class="fa fa-reorder"></i>Edit User's Info</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
				
					<div class="portlet-body form">
						<form action="#" id="modifyUserForm" class="form-horizontal">	
								
								<div class="alert alert-danger hide">
									You have some form errors. Please check below.
								</div>
								
								<div class="alert alert-success hide">
									It's OK to add new User now!
								</div>
						
								<div class="form-group row">
									<label for="username" class="col-2 form-control-label">User Name<span class="required">*</span></label>
									<div class="col-md-6">
										<input class="form-control col-md-12" type="text" name="username" id="username" placeholder="">
									</div>
								</div>

								<div class="form-group row">
								  <label for="password" class="col-2 form-control-label">Password<span class="required">*</span></label>
								  <div class="col-md-6">
									<input class="form-control col-md-12" type="password" value="" name="password" id="password">
								  </div>
								</div>
								
								<div class="form-group row">
								  <label for="confirm_password" class="col-2 form-control-label">Confirm Password<span class="required">*</span></label>
								  <div class="col-md-6">
									<input class="form-control col-md-12" type="password" value="" name="confirm_password" id="confirm_password" >
								  </div>
								</div>		

								<div class="form-group row">
									<label for="email" class="col-2 form-control-label">Email<span class="required">*</span></label>
									<div class="col-md-6">
									  <input type="email" class="form-control col-md-12" name="email" id="email" placeholder="Email" >
									</div>
								</div>	
								
								<div class="form-group row">	
								  <label class="col-2 form-control-label">Role<span class="required">*</span></label>
								  <div class="col-md-6">
									<select class="form-control col-md-12" name="role" id="new_user_role_select" >
										<option value="">Select a Role</option>
									</select>
								  </div>
								</div>
								
								<div class="form-group row">	
								  <label class="col-2 form-control-label">Team<span class="required">*</span></label>
								  <div class="col-md-6">
									<select class="form-control col-md-12" name="team" id="new_user_team_select" >
										<option value="">Select a Team</option>
									</select>
								  </div>
								</div>

								<div class="form-actions">	
									<button type="button" id="modify_user_submit_btn" class="btn green-stripe">Update Now</button>
									<button type="button" id="modify_user_cancel_btn" class="btn yellow">Cancel</button>
									<button type="button" id="modify_user_next_btn" class="btn blue">Next Record</button>									
								</div>

							</form>
					</div>							
				</div>			
			</div>
		
			<div class="col-md-4" id="helpMessage">
				<div class="portlet box red">
					<div class="portlet-title">
						<div class="caption"><i class="icon-reorder"></i>About User</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>

					<div class="portlet-body">
						<dl>
							<dt>Description:</dt>
							<dd>'User' is a account for all members to reserve system</dd>
							<dd>'status' of a user that you just create is set to deactive(as default), Please active it in upper table if you want</dd>
							<dd>'role' means the authority of a user that you add. </dd>
							<dd>'team' means that this user only can reserve the systems that belong to that team</dd>
							<dt>Normally, this account can do:</dt>
							<dd>1. Reserve a server.</dd>
							<dd>2. Get overview and status of all server we have.</dd>
						</dl>
					</div>
				</div>
			</div>

		</div>
	
	</div>

</div>
