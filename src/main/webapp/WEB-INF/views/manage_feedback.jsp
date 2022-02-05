<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Testimonial </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/feedback.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
	</head>
	<body ng-app="MyApp" ng-controller="testimonialCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Testimonial
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Testimonial</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Testimonial</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label>User<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="useridadd" name="useridadd" ng-model="useridadd" ng-change="setFlag()" class="form-control">
												<option value="">User</option>
												<option ng-repeat="item in getUsersName" ng-if="item.firstName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
											</select>												
										</div>
										<p ng-show="errorUser == 1" style="color: red;">{{msgUser}}</p>											
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>User First Name<font color="red" size="3">*</font></label>
										<input ng-disabled="useridadd != null" type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="User First Name" class="form-control" ng-change="setFlag()">											
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>User Last Name</label>
										<input type="text" id="lastnameadd" name="lastnameadd" ng-model="lastnameadd" placeholder="User Last Name" class="form-control" ng-change="setFlag()" ng-disabled="useridadd != null">											
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd" name="imageadd" placeholder="Image" class="form-control" ng-change="setFlag()">											
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Organization Name</label>
										<input type="text" id="orgnaizationadd" name="orgnaizationadd" ng-model="orgnaizationadd" placeholder="Organaization Name" class="form-control" ng-change="setFlag()">											
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
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>FeedBack</label>
										<textarea id="messageadd" name="messageadd" ng-model="messageadd" rows="7" placeholder="Feedback Text" class="form-control" ng-change="setFlag()"></textarea>
										<p ng-show="errorFeedback == 1" style="color: red; font-size: 14px;">{{msgFeedback}}</p>											
									</div>									
								</div>									
							</div>								
						</div>
						<div class="box-footer">
							<div class="row">									
								<div class="col-md-8">
									<p ng-show="submitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
									<p ng-show="submitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>	
								</div>
								<div class="col-md-4 text-right">
									<button ng-click="addFeedback()" type="submit" class="btn btn-tgsc"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Testimonial List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchFeedback() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchFeedback()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;"></select>
								</div>
								<div class="col-md-2">
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
											<th>User</th>										
											<th>Orgnaization</th>											
											<th class="text-center">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getFeedback == ''">
											<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getFeedback" style="cursor:pointer; cursor:hand">
											<td ng-click="getFeedbackDetail(item.feedbackId)" ng-show="item.userId == 0" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.userFirstName}} {{item.userLastName}}</td>
											<td ng-click="getFeedbackDetail(item.feedbackId)" ng-show="item.userId != 0" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.firstName}} {{item.lastName}}</td>											
											<td ng-click="getFeedbackDetail(item.feedbackId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.orgnaizationName}}</td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.testimonialId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getFeedback != ''">
										<tr>
											<td colspan="2"></td>
											<td  class="text-center">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getTestimonial.length+startindex}}</b> out of <b>{{allcounts.testimonialCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getTestimonial.length+startindex == allcounts.testimonialCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Testimonial</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-2">
								<div class="form-group">
									<label>User<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="useridedit" name="useridedit" ng-model="useridedit"  ng-disabled="firstnameedit != ''" ng-change="setFlag()" class="form-control">
											<option value="">User</option>
											<option ng-repeat="item in getUsersName" ng-if="item.firstName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
										</select>												
									</div>
									<p ng-show="errorUser == 1" style="color: red;">{{msgUser}}</p>											
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>User First Name<font color="red" size="3">*</font></label>
									<input type="text" id="firstnameedit" name="firstnameedit" ng-model="firstnameedit" placeholder="User First Name" class="form-control" ng-change="setFlag()" ng-disabled="useridedit != 0">											
								</div>									
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>User Last Name</label>
									<input type="text" id="lastnameedit" name="lastnameedit" ng-model="lastnameedit" placeholder="User Last Name" class="form-control" ng-change="setFlag()" ng-disabled="useridedit != 0">											
								</div>									
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Image</label>
									<input type="file" id="imageedit" name="imageedit" placeholder="Image" class="form-control" ng-change="setFlag()">											
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<img src="{{oldimage}}" style="width:60px;height:70px;"/>											
								</div>									
							</div>
						</div>
						<div class="row">	
							<div class="col-md-3">
								<div class="form-group">
									<label>Organization Name</label>
									<input type="text" id="orgnaizationnameedit" name="orgnaizationnameedit" ng-model="orgnaizationnameedit" placeholder="Orgnaization Name" class="form-control" ng-change="setFlag()">											
								</div>									
							</div>
						</div>
						<div class="row text-center">
							<div class="col-md-10">
								<img src="" id="target1" />
								<form name="myForm1">
									<input type="hidden" name="x1" id="valuex1" ng-model="valuex1" />
									<input type="hidden" name="y1" id="valuey1" ng-model="valuey1" />
									<input type="hidden" name="w1" id="valuew1" ng-model="valuew1" />
									<input type="hidden" name="h1" id="valueh1" ng-model="valueh1" />
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>FeedBack</label>
									<textarea id="feedbackedit" name="feedbackedit" ng-model="feedbackedit" rows="7" placeholder="Feedback Text" class="form-control" ng-change="setFlag()"></textarea>
									<p ng-show="errorFeedback == 1" style="color: red; font-size: 14px;">{{msgFeedback}}</p>											
								</div>									
							</div>									
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">									
							<div class="col-md-8 text-left">
								<p ng-show="submitSuccess == 1" style="color: green; font-size: 18px;"><b>{{msgSuccess}}</b></p>
								<p ng-show="submitError == 1" style="color: red; font-size: 18px;"><b>{{msgError}}</b></p>	
							</div>
							<div class="col-md-4 text-right">
								<button ng-click="editFeedback(feedbackid)" type="submit" class="btn btn-tgsc"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Testimonial </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteFeedback()">
					</div>
				</div>
			</div>
		</div>
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("feedback").classList.add("active");
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
										aspectRatio : 1 / 1.2,
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
										aspectRatio : 1 / 1.3,
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
				$("#imageedit").change(function() {
					readURL(this);
				});
				$("#imageedit").click(function() {
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