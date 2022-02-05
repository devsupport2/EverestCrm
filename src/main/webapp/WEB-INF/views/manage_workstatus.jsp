<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Manage Work Status </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
			
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular-filter.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/workstatus.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/jquery.Jcrop.css" type="text/css" />					
	</head>
	<style>
		input[type=number]::-webkit-inner-spin-button, 
		input[type=number]::-webkit-outer-spin-button { 
		    -webkit-appearance: none;
		    -moz-appearance: none;
		    appearance: none;
		    margin: 0; 
		}
	</style>
	<body ng-app="MyApp" ng-controller="workstatusCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Work Status
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Work Status</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Work Status</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Name<font color="red" size="3">*</font></label>									
										<select class="form-control" name="idadd" id="idadd" ng-model="idadd">
											<option value="">Select</option>
											<option ng-repeat="item in getProjectName" value="{{item.projectId}}" >{{item.projectTitle}}</option>
											<p ng-show="errorProjectName == 1" style="color: red;">{{msgProjectName}}</p>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Title<font color="red" size="3">*</font></label>
										<input type="text" class="form-control" id="titleadd" name="titleadd" ng-model="titleadd" placeholder="Title">
										<p ng-show="errorTitle == 1" style="color: red;">{{msgTitle}}</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Subtitle</label>
										<input type="text" class="form-control" id="subtitleadd" name="subtitleadd" ng-model="subtitleadd" placeholder="Subtitle">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Date</label>
										<input type="date" class="form-control" id="adddate" name="adddate" ng-model="adddate">									
									</div>								
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">								
									<div class="form-group">
										<label>Description</label>
										<textarea rows="5" class="form-control" id="descriptionadd" name="descriptionadd" ng-model="descriptionadd"></textarea>									
									</div>								
								</div>														
							</div>
							<div class="row">
								<div class="col-md-12" align="center">
									<div class="panel" style="background-color: #C0C0C0;">
									<div class="panel-heading" style="padding: 0px 15px;">
										<h4>Images</h4>
									</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">								
									<div class="form-group">
										<label>Image Title</label>
										<input type="text" class="form-control" id="imagetitle" name="imagetitle" ng-model="imagetitle" placeholder="Title" ng-change="setFlag()">
										<p ng-show="errorTitle == 1" style="color: red;">{{msgTitle}}</p>									
									</div>								
								</div>
								<div class="col-md-4">								
									<div class="form-group">
										<label>Image Description</label>
										<input type="text" class="form-control" id="imagedescription" name="imagedescription" ng-model="imagedescription"  placeholder="Description">									
									</div>								
								</div>
								<div class="col-md-4">								
									<div class="form-group">
										<label>Image</label>
										<input type="file" class="form-control" id="imageadd" name="imageadd" ng-model="imageadd" ng-change="setFlag()">
										<p ng-show="errorImage == 1" style="color: red;">{{msgImage}}</p>									
									</div>								
								</div>
								<div class="col-md-1">								
									<div class="form-group">
										<label>&nbsp;</label>
										<a type="submit" id="Add" value="Add" name="Add" ng-click="addImageRow()" class="btn btn-primary btn-sm"><i class="fa fa-plus-circle" aria-hidden="true"></i>&nbsp;Add</a>									
									</div>								
								</div>														
							</div>
							<div class="row" align="center">
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
							<div class="row">
								<div class="col-md-12">
									<table class="table table-striped" width="100%">
										<tr>
											<th>Image Title</th>
											<th>Image Description</th>																	
											<th></th>								
										</tr>
										<tr ng-repeat="item in imagelist">
											<td>{{item.imageTitle}}</td>								
											<td>{{item.imageDescription}}</td>
											<td><a class="btn btn-danger btn-sm" ng-click="removeImageRow(item.imageTitle)" ng-if="item.imageTitle != null"/><i class="fa fa-trash" aria-hidden="true"></i></a></td>								
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<div class="row">									
								<div class="col-md-8 text-left">
									<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-click="addWorkStatus()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-2" ng-show="projectid ==null">
									<h3 class="box-title">Work Status List</h3>
								</div>
								<div class="col-md-4"  ng-show="projectid ==null">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchProperty() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchProperty()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;"></select>
								</div>
								<div class="col-md-1">
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
									</div>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th>Project Title</th>
											<th>Title</th>
											<th>Subtitle</th>
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getWorkStatus == ''">
											<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getWorkStatus" style="cursor:pointer;cursor:hand">
											<td ng-click="getWorkstatusDetails(item.workstatusId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.projectTitle}}</td>
											<td ng-click="getWorkstatusDetails(item.workstatusId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.title}}</td>
											<td ng-click="getWorkstatusDetails(item.workstatusId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.subtitle}}</td>
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.workstatusId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getWorkStatus != ''">
										<tr>											
											<td colspan="4" class="text-right">
												<a href="#deleteModal" data-toggle="modal" ng-click="checkRecordSelectForDelete()" style="color: #fff;" class="btn btn-danger">
													<i style="margin: 0 0px;" class="fa fa-trash-o" aria-hidden="true"></i>
												</a>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="box-footer">
							<div class="row">
								<div class="col-md-5">
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getWorkStatus.length+startindex}}</b> out of <b>{{allcounts.workstatusCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getWorkStatus.length+startindex == allcounts.workstatusCount" ng-click="next()">
										<i class="fa fa-step-forward"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Work Status</h4>
					</div>
					
					<div class="box-body">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Project Name<font color="red" size="3">*</font></label>									
									<select class="form-control" name="idedit" id="idedit" ng-model="idedit">
										<option value="">Select</option>
										<option ng-repeat="item in getProjectName" value="{{item.projectId}}" >{{item.projectTitle}}</option>
										<p ng-show="errorProjectName == 1" style="color: red;">{{msgProjectName}}</p>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Title<font color="red" size="3">*</font></label>
									<input type="text" class="form-control" id="titleedit" name="titleedit" ng-model="titleedit" placeholder="Title">
									<p ng-show="errorTitle == 1" style="color: red;">{{msgTitle}}</p>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Subtitle</label>
									<input type="text" class="form-control" id="subtitleedit" name="subtitleedit" ng-model="subtitleedit" placeholder="Subtitle">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Date</label>
									<input type="date" class="form-control" id="editdate" name="editdate" ng-model="editdate">									
								</div>								
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">								
								<div class="form-group">
									<label>Description</label>
									<textarea rows="5" class="form-control" id="description" name="description" ng-model="description"></textarea>									
								</div>								
							</div>														
						</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="panel" style="background-color: #C0C0C0;">
								<div class="panel-heading" style="padding: 0px 15px;">
									<h4>Images</h4>
								</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">								
								<div class="form-group">
									<label>Image Title</label>
									<input type="text" class="form-control" id="imagetitleedit" name="imagetitleedit" ng-model="imagetitleedit" placeholder="Title" ng-change="setFlag()">
									<p ng-show="errorTitle == 1" style="color: red;">{{msgTitle}}</p>									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Image Description</label>
									<input type="text" class="form-control" id="imagedescriptionedit" name="imagedescription" ng-model="imagedescriptionedit"  placeholder="Description">									
								</div>								
							</div>
							<div class="col-md-4">								
								<div class="form-group">
									<label>Image</label>
									<input type="file" class="form-control" id="imageedit" name="imageedit" ng-model="imageedit" ng-change="setFlag()">
									<p ng-show="errorImage == 1" style="color: red;">{{msgImage}}</p>									
								</div>								
							</div>
							<div class="col-md-1">								
								<div class="form-group">
									<label>&nbsp;</label>
									<a type="submit" ng-disabled="wspin == 1" id="Add" value="Add" name="Add" ng-click="editImageRow(id)" class="btn btn-primary btn-sm"><i class="fa fa-refresh fa-spin" ng-if="wspin == 1"></i><i class="fa fa-plus-circle" aria-hidden="true"></i>&nbsp;Add</a>									
								</div>								
							</div>														
						</div>
						<div class="row" align="center">
							<div class="col-md-12">
								<img src="" id="target2"/>
								<form name="myForm2">
									<input type="hidden" name="x2" id="valuex2" ng-model="valuex2" />
									<input type="hidden" name="y2" id="valuey2" ng-model="valuey2" />
									<input type="hidden" name="w2" id="valuew2" ng-model="valuew2" />
									<input type="hidden" name="h2" id="valueh2" ng-model="valueh2" />
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<table class="table table-striped" width="100%">
									<tr>
										<th>Image Title</th>
										<th>Image Description</th>
										<th>Image </th>																	
										<th></th>								
									</tr>
									<tr ng-repeat="item in getWorkStatusDetails.imageList">
										<td>{{item.title}}</td>								
										<td>{{item.subtitle}}</td>
										<td><img style="width:70px;height:50px;" src="{{item.image}}"></td>
										<td><a class="btn btn-danger btn-sm" ng-click="removeImageRowEdit(item.workstatusImageId)" ng-if="item.title != null"/><i class="fa fa-trash" aria-hidden="true"></i></a></td>								
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="box-footer">
						<div class="row">									
							<div class="col-md-8 text-left">
								<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
								<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
							</div>
							<div class="col-md-4 text-right">
								<button type="submit" ng-click="editWorkStatus(id)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Work Status </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteWorkstatus()">
					</div>
				</div>
			</div>
		</div>
		<script src="<%= request.getContextPath() %>/resources/admin/js/jquery.Jcrop.js"></script>
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
								$('#target').attr('src', e.target.result);
								$('#target').Jcrop({
									aspectRatio : 1.5 / 1,
									boxWidth : 600,
									boxHeight : 400,																			
									setSelect : [ 100, 100, 400, 400 ],
									onChange : setCoordinates,
									onSelect : setCoordinates
								});						       
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
			
			jQuery(function($) {
				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							if ($('#target2').data('Jcrop')) {
							    $('#target2').data('Jcrop').destroy();
							    $('#target2').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        $('#target2').attr('src', e.target.result);
								$('#target2').Jcrop({
									aspectRatio : 1.5 / 1,
									boxWidth : 600,
									boxHeight : 400,																			
									setSelect : [ 100, 100, 400, 400 ],
									onChange : setCoordinates2,
									onSelect : setCoordinates2
								});						        
						    };						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imageedit").change(function() {
					readURL(this);
				});
				$("#imageedit").click(function() {
					this.value = null;
				});
			});
			
			function setCoordinates2(c) {				
				document.myForm2.x2.value = Math.round(c.x);
				document.myForm2.y2.value = Math.round(c.y);
				document.myForm2.w2.value = Math.round(c.w);
				document.myForm2.h2.value = Math.round(c.h);
			};
			
			function checkCoordinates2() {
				if (document.myForm2.x2.value == "" || document.myForm2.y2.value == "") {
					alert("Please select a crop region");
					return false;
				} else {
					return true;
				}
			};
				
			document.getElementById("manage").classList.add("active");
			document.getElementById("workstatus").classList.add("active");
		</script>		
				
	</body>
</html>