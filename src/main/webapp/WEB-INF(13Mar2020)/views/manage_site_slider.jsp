<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Project Slider </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/manage_Site_Slider.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
	</head>
	<body ng-app="MyApp" ng-controller="SiteSliderCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Project Slider
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active"> Project Slider</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div data-toggle="modal" data-target="#AddSlider" class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Slider</h3>
							<div class="box-tools pull-right">
							<a href="#" class="btn btn-success btn-default"><i class="fa fa-plus" aria-hidden="true"></i></a>
							</div>
						</div>
					</div>
					<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="mytable" class="table table-bordred table-striped">
								<thead>
									<th width="20%">Project Name</th>
									<th width="60%">Slider Title</th>
									<th width="10%">Image</th>
									<!-- <th width="5%" class="text-right">A/I</th> -->
									<th width="5%" class="text-right">All <input type="checkbox" ng-model="selectedAll" ng-click="checkAll()"></th>
								</thead>
								<tbody>
									<tr ng-repeat="item in getSliders" style="cursor:pointer;">
										<td data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'>{{item.projectTitle}}</td>
										<td data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'>{{item.sliderTitle}}</td>
										<td data-toggle="modal" data-target="#EditSlider" ng-click='getSlider(item.sliderId)'><img src="{{item.image}}" class="img-responsive"></td>
										<!-- <td class="text-right">
											<input type="checkbox" ng-model="item.selected1" value="{{item.sliderId}}" ng-click="setActive(item.sliderId, item.active)" ng-if="item.active == 'y'" ng-init="item.selected1 = true">
											<input type="checkbox" ng-model="item.selected1" value="{{item.sliderId}}" ng-click="setActive(item.sliderId, item.active)" ng-if="item.active == 'n'">
										</td> -->
										<td class="text-right"><input type="checkbox" ng-model="item.selected" value="{{item.sliderId}}"></td>
									</tr>
									<tr>
										<td colspan="5" class="text-right">
											<a href="" ng-click="deleteProjectSlider()" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-5">
						<div class="hint-text">Showing <b>{{startindex+1}}-{{getSliders.length+startindex}}</b> out of <b>{{allcounts.taxCount}}</b> entries</div>
					</div>
					<div class="col-md-7">
						<button type="submit" class="btn btn-primary btn-default" ng-disabled="currentPage <= 0" ng-click="prev()">
							<i class="fa fa-step-backward"></i>
						</button>
						{{currentPage+1}}
						<button type="submit" class="btn btn-primary" ng-disabled="getSliders.length+startindex == allcounts.sliderCount" ng-click="next()">
							<i class="fa fa-step-forward"></i>
						</button>
					</div>
				</div>
				</section>
				<div class="container">
					<div class="modal fade" id="AddSlider" role="dialog" tabindex="-1">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Add Project Slider</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label>Select Project</label>
												<select id="projectidadd" name="projectidadd" ng-model="projectidadd" class="form-control" ng-change="onChangeProject(projectidadd) || setFlag()">
														<option value="">Project</option>
														<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
												</select>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Slider Title</label>
												<input type="text" id="slidertitleadd" name="slidertitleadd" ng-model="slidertitleadd" placeholder="Slider Title" class="form-control">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Slider Image <font style="color: red;">*</font></label>
												<input type="file" id="imageadd" name="imageadd" class="form-control"><br>
												<p style="font-size:13px; margin-top:-10px;">Upload minimum 1400 * 800 size <br> image for better appereance</p>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group checkbox">
												<label>&nbsp;</label><br>
												<label><input type="checkbox" id="activeadd" name="activeadd" ng-model="activeadd" ng-init="activeadd=true">Active?</label>
											</div>
										</div>
									</div>
									<div class="row text-center">
										<div class="col-md-12">
											<img src="" id="target"/>
											<form name="myForm">
												<input type="hidden" name="x" id="valuex" ng-model="valuex" />
												<input type="hidden" name="y" id="valuey" ng-model="valuey" />
												<input type="hidden" name="w" id="valuew" ng-model="valuew" />
												<input type="hidden" name="h" id="valueh" ng-model="valueh" />
											</form>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-success" ng-click="addProjectSlider()">Submit
										<i class="fa fa-plus" aria-hidden="true" ng-if="spin == 0"></i>
										<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
									</button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="modal fade" id="EditSlider" role="dialog" tabindex="-1">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Update Project Slider</h4>
								</div>
								<div class="modal-body">
									<div class="row">
									<div class="col-md-3">
											<div class="form-group">
												<label>Select Project</label>
												<select id="projectidedit" name="projectidedit" ng-model="projectidedit" class="form-control" ng-change="onChangeProject(projectidadd) || setFlag()">
														<option value="">Project</option>
														<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
												</select>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>Slider Title</label>
												<input type="text" id="slidertitle" name="slidertitle" ng-model="slidertitle" placeholder="Slider Title" class="form-control">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Slider Image <font style="color: red;">*</font></label>
												<input type="file" id="image" name="image" class="form-control"><br>
												<p style="font-size:13px;margin-top:-10px;">Upload minimum 1400 * 800 size <br> image for better appereance</p>
											</div>
										</div>
										<div class="col-md-2 text-center">
											<div class="form-group">
												<image src="{{sliderimage}}" class="img-responsive">
												<br ng-if="sliderimage != ''">
												<a ng-click="deleteImage()" class="btn btn-danger" ng-if="sliderimage != ''" data-toggle="tooltip" title="Remove Slider">
													<i class="fa fa-trash" aria-hidden="true"></i>
												</a>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group checkbox">
												<label>&nbsp;</label><br>
												<label><input type="checkbox" id="active" name="active" ng-model="active">Active?</label>
											</div>
										</div>
									</div>
									<div class="row text-center">
										<div class="col-md-12">
											<img src="" id="target1" />
											<form name="myForm1">
												<input type="hidden" name="x1" id="valuex1" ng-model="valuex1" />
												<input type="hidden" name="y1" id="valuey1" ng-model="valuey1" />
												<input type="hidden" name="w1" id="valuew1" ng-model="valuew1" />
												<input type="hidden" name="h1" id="valueh1" ng-model="valueh1" />
											</form>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-success" ng-click="editSlider(sliderid)"> Update
										<i class="fa fa-plus" aria-hidden="true" ng-if="spin == 0"></i>
										<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>
									</button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Category </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteCategory()">
					</div>
				</div>
			</div>
		</div>		
		<script>
			document.getElementById("manage").classList.add("active");
			document.getElementById("projectslider").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	
		<script src="<%=request.getContextPath()%>/resources/admin/js/jquery.Jcrop.js"></script>
	
	<script>
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target').data('Jcrop')) {
							    $('#target').data('Jcrop').destroy();
							    $('#target').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						       /*  if(img.width < 1400 || img.height < 800)
						        {
						        	alert("Minimum image size should be 1400px X 800px");
						        	$('#target').attr('src', "");
						        	document.getElementById("imageadd").value = null;
						        }
						        else
						        { */
						        	$('#target').css("min-height", "208px");
								    $('#target').css("min-width", "337px");
									
									$('#target').attr('src', e.target.result);
									$('#target').Jcrop({
										aspectRatio : 2 / 1,
										boxWidth : 840,
										//boxHeight : 400,
										minSize : [100, 100],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates,
										onSelect : setCoordinates
									});
						       /*  } */
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imageadd").change(function() {
					readURL(this);
				});
				$("#imageadd").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates(c) {
				document.myForm.x.value = Math.round(c.x);
				document.myForm.y.value = Math.round(c.y);
				document.myForm.w.value = Math.round(c.w);
				document.myForm.h.value = Math.round(c.h);
			};
			
			function checkCoordinates() {
				if (document.myForm.x.value == "" || document.myForm.y.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
		
		<!-- For Edit -->
		<script>
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target1').data('Jcrop')) {
							    $('#target1').data('Jcrop').destroy();
							    $('#target1').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        /* if(img.width < 1400 || img.height < 800)
						        {
						        	alert("Minimum image size should be 1400px X 800px");
						        	$('#target1').attr('src', "");
						        	document.getElementById("image").value = null;
						        }
						        else
						        { */
						        	$('#target1').css("min-height", "208px");
								    $('#target1').css("min-width", "337px");
									
									$('#target1').attr('src', e.target.result);
									$('#target1').Jcrop({
										aspectRatio : 2 / 1,
										boxWidth : 840,
										//boxHeight : 400,
										minSize : [100, 100],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates1,
										onSelect : setCoordinates1
									});
						        /* } */
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#image").change(function() {
					readURL(this);
				});
				$("#image").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates1(c) {
				document.myForm1.x1.value = Math.round(c.x);
				document.myForm1.y1.value = Math.round(c.y);
				document.myForm1.w1.value = Math.round(c.w);
				document.myForm1.h1.value = Math.round(c.h);
			};
			
			function checkCoordinates1() {
				if (document.myForm1.x1.value == "" || document.myForm1.y1.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
		</script>
	</body>
</html>