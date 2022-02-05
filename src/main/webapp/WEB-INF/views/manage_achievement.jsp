<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Achievement </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/manage_achievement.js"></script>					
	</head>
	<body ng-app="MyApp" ng-controller="achievementCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Achievement
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Achievement</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Achievement</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Achievement Title<font color="red" size="3">*</font></label>
										<input type="text" id="achievementtitleadd" name="achievementtitleadd" ng-model="achievementtitleadd" placeholder="Achievement Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorAchievementTitle == 1" style="color: red; font-size: 14px;">{{msgAchievementTitle}}</p>
									</div>
								</div>								
								<div class="col-md-8">
									<div class="form-group">
										<label>Achievement Subtitle<font color="red" size="3">*</font></label>
										<input type="text" id="achievementsubtitleadd" name="achievementsubtitleadd" ng-model="achievementsubtitleadd" placeholder="Achievement Subtitle" class="form-control" ng-change="setFlag()">
										<p ng-show="errorAchievementSubtitle == 1" style="color: red; font-size: 14px;">{{msgAchievementSubtitle}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Image</label>
										<input type="file" id="imageadd" name="imageadd" ng-model="imageadd" class="form-control">										
									</div>
								</div>
							</div>
							<div class="row text-center">
								<div class="col-md-12">
									<img src="" id="target1"/>
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
										<label>Description</label>
										<textarea id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Description" class="form-control"></textarea>											
									</div>									
								</div>									
							</div>
						</div>
						<div class="box-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button ng-click="addAchivement()" type="submit" ng-disabled="categorySpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="categorySpin == 1"></i> Save</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>			
						</div>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Achievement List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchAchievement() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchAchievement()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th>Achievement Title</th>
											<th>Achievement Subtitle</th>											
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getAchivements == ''">
											<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getAchivements" style="cursor:pointer;cursor:hand">
											<td ng-click="getAchievementDetailsById(item.achievementId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.title}}</td>
											<td ng-click="getAchievementDetailsById(item.achievementId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.subtitle}}</td>											
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.achivementId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getAchivements != ''">
										<tr>
											<td colspan="2"></td>
											<td class="text-right">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getAchivements.length+startindex}}</b> out of <b>{{allcounts.achievementCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getAchivements.length+startindex == allcounts.achievementCount" ng-click="next()">
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
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Achievement</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Achievement Title<font color="red" size="3">*</font></label>
									<input type="text" id="achievementtitleedit" name="achievementtitleedit" ng-model="achievementtitleedit" placeholder="Achievement Title" class="form-control" ng-change="setFlag()">
									<p ng-show="errorAchievementTitle == 1" style="color: red; font-size: 14px;">{{msgAchievementTitle}}</p>
								</div>									
							</div>								
							<div class="col-md-6">
								<div class="form-group">
									<label>Achievement Subtitle<font color="red" size="3">*</font></label>
									<input type="text" id="achievementsubtitleedit" name="achievementsubtitleedit" ng-model="achievementsubtitleedit" placeholder="Achievement Subtitle" class="form-control" ng-change="setFlag()">
									<p ng-show="errorCategoryCode == 1" style="color: red; font-size: 14px;">{{msgCategoryCode}}</p>
								</div>									
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label>image</label>
									<input type="file" id="imagedit" name="imagedit" class="form-control">										
								</div>									
							</div>
							<div class="col-md-2" ng-if="imageedit">
								<div class="form-group">
									<img src="{{imageedit}}" style="width:70px;height:60px;">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<div class="form-group">
									<div  >
										<img id="target"/>
									</div>
									<form name="myForm">
										<input type="hidden" name="x" id="valuex" ng-model="valuex" />
										<input type="hidden" name="y" id="valuey" ng-model="valuey" />
										<input type="hidden" name="w" id="valuew" ng-model="valuew" />
										<input type="hidden" name="h" id="valueh" ng-model="valueh" />
									</form>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Description</label>
									<textarea id="description" name="description" ng-model="description" placeholder="Description" class="form-control"></textarea>											
								</div>									
							</div>									
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">								
							<div class="col-md-6 text-left">
								<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
								<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
							</div>
							<div class="col-md-6 text-right">
								<button ng-click="editAchievement(achievementid)" type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Achievement </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteAchievement()">
					</div>
				</div>
			</div>
		</div>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/js/jquery.Jcrop.js"></script>
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("achievement").classList.add("active");
			
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
						        //if(img.width < 1400 || img.height < 800)
						       /* if(img.width < 600 || img.height < 500)
						        {
						        	alert("Minimum image size should be 600px X 500px");
						        	$('#target').attr('src', "");
						        	document.getElementById("imageadd").value = null;
						        }
						        else
						        { */
						        	$('#target').css("min-height", "208px");
								    $('#target').css("min-width", "337px");
									
									$('#target').attr('src', e.target.result);
									$('#target').Jcrop({
										//aspectRatio : 5.7 / 2.8,
										aspectRatio : 2 / 1.3,
										boxWidth : 840,
										//boxHeight : 400,
										minSize : [100, 100],
										setSelect : [ 100, 100, 400, 400 ],
										onChange : setCoordinates,
										onSelect : setCoordinates
									});
						        //}
						    };
						        
						    img.src = u;
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				$("#imagedit").change(function() {
					readURL(this);
				});
				$("#imagedit").click(function() {
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
							if ($('#target1').data('Jcrop')) {
							    $('#target1').data('Jcrop').destroy();
							    $('#target1').removeAttr('style');
							}
							
							var u = URL.createObjectURL(input.files[0]);
						    var img = new Image;
						    img.onload = function() {
						        /* if(img.width < 625 || img.height < 500)
						        {
						        	alert("Minimum image size should be 625px X 500px");
						        	$('#target1').attr('src', "");
						        	document.getElementById("imageedit").value = null;
						        }
						        else
						        { */
						        	/* $('#target1').css("min-height", "208px");
								    $('#target1').css("min-width", "337px"); */
									
									$('#target1').attr('src', e.target.result);
									$('#target1').Jcrop({
										aspectRatio : 2 / 1.3,
										//aspectRatio : 5.7 / 2.8,
										//boxWidth : 840,
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
				$("#imageadd").change(function() {
					readURL(this);
				});
				$("#imageadd").click(function() {
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