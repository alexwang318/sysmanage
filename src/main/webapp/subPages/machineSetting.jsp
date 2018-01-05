<script src="${pageContext.request.contextPath}/js/jquery.fancybox.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/machineSetting.js" type="text/javascript"></script>

<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title">
			Machine Management Setting  
			 <small>Lab, Machine Type management</small>
			</h3>
		</div>
	</div>
	
	<hr>
	
	<div class="row-fluid">
		<div class="machine-type-page">
			<div class="row">
				<div class="col-md-9">
					<div class="row-fluid">
						<h1> Current Support Types</h1>
						<button type="button" id="add_new_btn" class="btn green">Add a Type <i class="fa fa-plus"></i></button>
					</div>
					<hr>
					<div class="" id="serverTypeBlock">
						
					</div>
					<div class="col-md-12">
					  <ul class="pagination pull-right">
						<li class="page-item"><a class="page-link" href="#"><i class="fa fa-double-angle-left"></i>Previous</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">Next<i class="fa fa-double-angle-right"></i></a></li>
					  </ul>
					</div>
				</div>
				<div class="col-md-3">
					<div class="top-news">
						<a herf="#" class="btn red">
						<span>Recent Activities</span>
						<i class="fa fa-globe top-news-icon"></i>
						</a>

						<a href="#" class="btn green">
						<span>Top Week</span>
						<em>Posted on: April 15, 2013</em>
						<em>
						<i class="fa fa-tags"></i>
						Internet, Music, People
						</em>
						<i class="fa fa-music top-news-icon"></i>                             


						</a>
						<a href="#" class="btn blue">
						<span>Gold Price Falls</span>
						<em>Posted on: April 14, 2013</em>
						<em>
						<i class="fa fa-tags"></i>
						USA, Business, Apple
						</em>
						<i class="fa fa-globe top-news-icon"></i>                             
						</a>


						<a href="#" class="btn yellow">
						<span>Study Abroad</span>
						<em>Posted on: April 13, 2013</em>
						<em>
						<i class="fa fa-tags"></i>
						Education, Students, Canada
						</em>
						<i class="fa fa-book top-news-icon"></i>                              
						</a>

						<a href="#" class="btn purple">
						<span>Top Destinations</span>
						<em>Posted on: April 12, 2013</em>
						<em>
						<i class="fa fa-tags"></i>
						Places, Internet, Google Map
						</em>
						<i class="fa fa-bolt top-news-icon"></i>                              
						</a>
					</div>
					
					
				</div>
			</div>			
		</div>
	</div>
	
	<div class="row-fluid">
	<div class="row">
		<div class="col-md-9">
			<div class="portlet box green">

				<div class="portlet-title">
					<div class="caption"><i class="fa fa-users"></i>Add Type here ....</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>

				<div class="portlet-body form">
					
						<form action="#" id="typeEditForm" class="form-horizontal">
							<div class="row">
							<div class="col-md-4">
							
								<div class="col-md-12 item" id="imgPreview">
									<a href="${pageContext.request.contextPath}/image/placeholder.gif" data-fancybox data-caption="IAAS">
										<div class="zoom">
											<img class="type-picture" src="${pageContext.request.contextPath}/image/server_type_pic/placeholder.gif">
											<div class="zoom-icon"></div>
										</div>
									</a>
								</div>
								
								<div class="btn green fileinput-button col-md-10">									
									<input type="file" name="picture" id="picture" onchange='PreviewImage(this)'>
									<i class="fa fa-plus"></i> Add a picture...
								</div>
								
							</div>
							
							<div class="col-md-8">							
								<div class="form-group row">
									<label class="col-3 form-control-label">Type Name<span class="required">*</span></label>
									<div class="col-md-6">
										<input class="form-control col-md-12" type="text" name="typename" id="typename" placeholder="">
									</div>
								</div>
								<div class="form-group row">
									<label class="col-3 form-control-label">URL<span class="required">*</span></label>
									<div class="col-md-6">
										<input class="form-control col-md-12" type="text" name="url" id="url" placeholder="Product website...">
									</div>
								</div>

								<div class="form-group row">
								  <label for="description" class="col-3 form-control-label">Description<span class="required">*</span></label>
								  <div class="col-md-9">
								  <textarea class="form-control col-md-12" name="description" id="description" placeholder="Please add the detailed description about this type..."></textarea>
								  </div>
								</div>
							</div>	
							</div>
						</form>
						
						<div class="form-actions">	
							<button type="button" id="submit_btn" class="btn green">Add Now</button>
							<button type="button" id="cancel_btn" class="btn yellow">Cancel</button>							
						</div>
										
			
				</div>
			</div>
	
		</div>
	</div>
	</div>
	
</div>