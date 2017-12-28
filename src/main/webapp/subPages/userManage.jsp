
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
					
		<div class="row hide"  id="add_new_user">
			<div class="col-md-8 ">
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
								  <label class="col-2 form-control-label">Role<span class="required">*</span></label>
								  <div class="col-md-6">
									<select class="col-md-12 m-warp" name="role" id="role" >
										<option value="1">Super User</option>
										<option value="2">User</option>
									</select>
								  </div>
								</div>

								<div class="form-actions">	
									<button type="button" id="add_new_user_submit_btn" class="btn red">Add Now</button>
									<button type="button" id="add_new_user_cancel_btn" class="btn">Cancel</button>	
								</div>

							</form>
					</div>							
				</div>			
			</div>
		
			<div class="col-md-4">
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
							<dt>Normally, this account can do:</dt>
							<dd>1. Reserve a server.</dd>
							<dd>2. Get overview and status of all server we have.</dd>
						</dl>
					</div>
				</div>
			</div>

		</div>
	

		<div class="row hide"  id="modify_user">
			<div class="col-md-8">
				<div class="portlet box purple">

					<div class="portlet-title">
						<div class="caption"><i class="fa fa-reorder"></i>Update A New User's Info</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
				
					<div class="portlet-body form">
						<form action="#" id="updateUserForm" class="form-horizontal">	
								<div class="form-group row">
									<label for="update_user_name" class="col-2 form-control-label">Name<span class="required">*				  
								  </span></label>
									<div class="col-sm-10">
									<input class="form-control col-md-6" type="text" id="update_user_name" placeholder="Gloabl ID..." disabled>
									</div>
								</div>

								<div class="form-group row">
								  <label for="update_user_password" class="col-2 form-control-label">Password<span class="required">*				  
								  </span></label>
								  <div class="col-sm-10">
									<input class="form-control col-md-6" type="password" value="" id="update_user_password">
								  </div>
								</div>
								
								<div class="form-group row">
								  <label for="update_user_repassword" class="col-2 form-control-label">Confirm Password<span class="required">*				  
								  </span></label>
								  <div class="col-sm-10">
									<input class="form-control col-md-6" type="password" value="" id="update_user_repassword">
								  </div>
								</div>		

								<div class="form-group row">
									<label for="update_user_email" class="col-2 form-control-label">Email<span class="required">*				  
								  </span></label>
									<div class="col-sm-10">
									  <input type="email" class="form-control col-md-6" id="update_user_email" placeholder="Email">
									</div>
								</div>	
								
								<div class="form-group row">	
								  <label class="col-2 form-control-label">Role<span class="required">*				  
								  </span></label>
								  <div class="col-sm-10">
									<select class="col-md-6 m-warp">
										<option value="">Select a Role...</option>
										<option value="superUser">Super User</option>
										<option value="user">User</option>
									</select>
								   </div>
								</div>
								
								<div class="form-group row">	
								  <label class="col-2 form-control-label">Role<span class="required">*				  
								  </span></label>
								  <div class="col-sm-10">
									<select class="col-md-6 m-warp">
										<option value="">Select the status...</option>
										<option value="1">enable</option>
										<option value="0">disable</option>
									</select>
								   </div>
								</div>

								<div class="form-actions">	
									<button type="button" id="update_user_submit_btn" class="btn red">Update Now</button>
									<button type="button" id="update_user_cancel_btn" class="btn">Cancel</button>	
								</div>

							</form>
					</div>							
				</div>
			</div>
		
					
			<div class="col-md-4">
				<div class="portlet box red">
					<div class="portlet-title">
						<div class="caption"><i class="icon-reorder"></i>Note</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>

					<div class="portlet-body">
						<dl>
							<dt>Description:</dt>
							<dd>Till now, User Name can't be change after this account created.</dd>
						</dl>
					</div>
				</div>
			</div>

		</div>
	</div>

</div>
