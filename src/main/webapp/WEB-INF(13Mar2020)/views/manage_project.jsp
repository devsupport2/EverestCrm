<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title> Project </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/admin/css/jquery.Jcrop.css" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
	</head>
	<style>
		#editModal {
			overflow-y: scroll
		}
		input[type=number]::-webkit-inner-spin-button,
		input[type=number]::-webkit-outer-spin-button {
		    -webkit-appearance: none;
		    -moz-appearance: none;
		    appearance: none;
		    margin: 0; 
		}
	</style>
	<body ng-app="MyApp" ng-controller="projectCtrl" ng-cloak class="skin-blue sidebar-mini" ng-init="getProjectDetailsById('<%= request.getParameter("projectid") %>')">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Project
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Project</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Project</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-md-3">
									<label>Developer Company</label>
									<div class="input-group">
										<select id="developercompanyidadd" name="developercompanyidadd" ng-model="developercompanyidadd" class="select2" style="width: 100%;">
											<option value="">Developer Company</option>
											<option ng-repeat="item in getUserName" ng-if="item.userCompanyName" value="{{item.userId}}">{{item.userCompanyName}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#companyModal" title="Add New Developer Company"><i class="fa fa-plus"></i></button>
										</span>
									</div>										
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Title<font color="red" size="3">*</font></label>
										<input type="text" id="projecttitleadd" name="projecttitleadd" ng-model="projecttitleadd" placeholder="Project Title" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorProjectTitle == 1" style="color: red;">{{msgProjectTitle}}</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Subtitle</label>
										<input type="text" id="projectsubtitleadd" name="projectsubtitleadd" ng-model="projectsubtitleadd" placeholder="Project Subtitle" class="form-control">											
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Code<font color="red" size="3">*</font></label>
										<input type="text" id="projectcodeadd" name="projectcodeadd" ng-model="projectcodeadd" placeholder="Project Code" class="form-control" maxlength="5" ng-change="setFlag()">
										<p ng-show="errorProjectCode == 1" style="color: red;">{{msgProjectCode}}</p>											
									</div>
								</div>																		
							</div>
							<div class="row form-group">
								<div class="col-md-3">
									<label>Location<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="locationidadd" name="locationidadd" ng-model="locationidadd" class="select2" style="width: 100%;">
											<option value="">Location</option>
											<option ng-repeat="item in getLocationName" value="{{item.locationId}}">{{item.locationName}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#locationModal" title="Add New Location"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorLocation == 1" style="color: red;">{{msgLocation}}</p>
								</div>
								<div class="col-md-3">
									<label>Architect</label>
									<div class="input-group">
										<select id="architectidadd" name="architectidadd" ng-model="architectidadd" class="select2" style="width: 100%;">
											<option value="">Architect</option>
											<option ng-repeat="item in getArchitectName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New User"><i class="fa fa-plus"></i></button>
										</span>
									</div>										
								</div>
								<div class="col-md-3">
									<label>Structural Designer</label>
									<div class="input-group">
										<select id="structuraldesigneridadd" name="structuraldesigneridadd" ng-model="structuraldesigneridadd" class="select2" style="width: 100%;">
											<option value="">Structural Designer</option>
											<option ng-repeat="item in getDesignerName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}} </option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New User"><i class="fa fa-plus"></i></button>
										</span>
									</div>										
								</div>
								<div class="col-md-3">
									<label>Legal Advisor</label>
									<div class="input-group">
										<select id="legaladvisoridadd" name="legaladvisoridadd" ng-model="legaladvisoridadd" class="select2" style="width: 100%;">
											<option value="">Legal Advisor</option>
											<option ng-repeat="item in getAdvisorName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}} </option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New User"><i class="fa fa-plus"></i></button>
										</span>
									</div>										
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label>Project Logo</label>
									<input type='file' id="projectlogo" class="form-control"/>
								</div>
								<div class="col-md-3">
									<label>Property Ownership Type</label>
									<div class="input-group">
										<select id="propertytypeidadd" name="propertytypeidadd" ng-model="propertytypeidadd" class="form-control" ng-change="setFlag()">
											<option value="">Property Ownership Type</option>
											<option ng-repeat="items in getPropertyName" value="{{items.propertyTypeId}}">{{items.propertyTypeTitle}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#propertytypeModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorPropertyTitle == 1" style="color: red;">{{msgPropertyTitle}}</p>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>RERA Approved?</label>
										<select id="rereapprovedadd" name="rereapprovedadd" ng-model="rereapprovedadd" class="form-control">
											<option value="">RERA Approved?</option>
											<option value="y">Yes</option>
											<option value="n">No</option>
										</select>										
									</div>									
								</div>
								<div class="col-md-3" ng-show="rereapprovedadd == 'y'">
									<div class="form-group">
										<label>RERA#</label>
										<input type="text" id="reranoadd" name="reranoadd" ng-model="reranoadd" placeholder="RERA No." class="form-control">											
									</div>
								</div>									
							</div>
							<div class="row">
								<div class="col-md-4">
									<label>Project Layout</label>
									<input type='file' id="projectlayout" class="form-control"/>
								</div>
								<div class="col-md-3">
									<label>Water Source</label>
									<div class="form-group">											
										<select id="watersourceadd" name="watersourceadd" ng-model="watersourceadd" class="form-control" ng-change="setFlag()">
											<option value="">Water Source</option>
											<option value="Municipal">Municipal</option>
											<option value="Borewell">Borewell</option>
											<option value="Borewell & Municipal">Borewell & Municipal</option>
											<option value="Well">Well</option>
										</select>																					
									</div>
								</div>									
								<div class="col-md-3">
									<label>Drainage</label>
									<div class="form-group">
										<select id="drainageadd" name="drainageadd" ng-model="drainageadd" class="form-control" ng-change="setFlag()">
											<option value="">Drainage</option>
											<option value="Private">Private</option>
											<option value="Municipal">Municipal</option>
										</select>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Work Status</label>
										<select id="workstatusadd" name="workstatusadd" ng-model="workstatusadd" class="form-control" ng-init="workstatusadd='Ongoing'">
											<!-- <option value="">Work Status</option> -->
											<option value="Ongoing">Ongoing</option>
											<option value="Completed">Completed</option>
										</select>
									</div>
								</div>
							</div>
							<!-- Code added to add Main Project Image -->
							<div class="row">
								<div class="col-md-4">
									<label>Project Main Image</label>
									<input type="file" id="projectimageadd" name="projectimageadd" class="form-control"><br>
									<p style="font-size:13px; margin-top:-10px;">Upload minimum 600 * 500 size <br> image for better appereance</p>
								</div>
								<div class="col-md-3">
									<label>Project PDF</label>
									<input type="file" id="projectpdfadd" name="projectpdfadd" class="form-control">
								</div>
								<div class="col-md-3">
									<label>Site link</label>
									<input type="text" id="projectsitelinkadd" name="projectsitelinkadd" ng-model="projectsitelinkadd" class="form-control">
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Sequence<font color="red" size="3">*</font></label>
										<input type="number" id="sequenceadd" name="sequenceadd" ng-model="sequenceadd" placeholder="Sequence" class="form-control" ng-change="checkSequence(sequenceadd) || setFlag()" autofocus>
										<p ng-show="errorSeq == 1" style="color: red;">{{msgSeq}}</p>
									</div>									
								</div>
							</div>
							<div class="row text-center">
								<div class="col-md-12">
									<img style="width:100%" src="" id="target1"/>
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
										<textarea rows="4" id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Description..." class="form-control"></textarea>
									</div>
								</div>
							</div>								
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Information</h4>
											</div>
											<div class="panel-body">
												<div class="row form-group">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="realestatecategoryidadd" name="realestatecategoryidadd" ng-model="realestatecategoryidadd" class="form-control" ng-change="onChangeRealestateCategory(realestatecategoryidadd) || setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Location"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>
													</div>									
													<div class="col-md-3">															
														<div class="input-group">
															<select id="realestatesubcategoryidadd" name="realestatesubcategoryidadd" ng-model="realestatesubcategoryidadd" class="form-control" ng-change="onChangeRealestateSubcategory() || setFlag()">
																<option value="">Subcategory</option>
																<option ng-repeat="items in getSubcategoryTitles" value="{{items.realestateSubcategoryId}}">{{items.subcategoryTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateSubModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRealestateSubCategoryTitle == 1" style="color: red;">{{msgSubCategoryType}}</p>
													</div>															
													<div class="col-md-3">															
														<div class="input-group">
															<select id="realestatetypeidadd" name="realestatetypeidadd" ng-model="realestatetypeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Type</option>
																<option ng-repeat="items in getRealestateTitles" value="{{items.realestateId}}">{{items.realestateTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateTypeModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRealestateTitle == 1" style="color: red;">{{msgType}}</p>
													</div>
													<div class="col-md-3">															
														<div class="input-group">
															<select id="towertitleadd" name="towertitleadd" ng-model="towertitleadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit Name</option>
																<option ng-repeat="unit in unitNameList" value="{{unit.unitMasterId}}">{{unit.unitName}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitMasterModal" title="Add New Unit"><i class="fa fa-plus"></i></button>
															</span>
														</div>															
													</div>														
												</div>
												<div class="row">	
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="numberofunits" name="numberofunits" ng-model="numberofunits" placeholder="Number of units" class="form-control">
														</div>
													</div>														
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="addProjectInfoRow()" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Subategory</th>																	
																<th>Type</th>
																<th>Unit Name</th>
																<th class="text-right">Units</th>
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectdetaillist">
																<td> {{item.categoryTitle}} </td>
																<td> {{item.subcategoryTitle}} </td>														
																<td> {{item.typeTitle}} </td>
																<td> {{item.unitTitle}} </td>
																<td class="text-right"> {{item.totalUnit}} </td>
																<td class="text-right">
																	<a href="#" ng-click="removeProjectInfoRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Floor Wise Layout </h4>
											</div>
											<div class="panel-body">
												<div class="row form-group">
													<div class="col-md-2">															
														<div class="form-group">
															<select id="layouttowertitleadd" name="layouttowertitleadd" ng-model="layouttowertitleadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit Name</option>
																<option ng-repeat="item in projectdetaillist" value="{{item.unitId}}">{{item.unitTitle}}</option>
															</select>
															<p ng-show="errorTower == 1" style="color: red;">{{msgTower}}</p>
														</div>															
													</div>
													<div class="col-md-3">															
														<div class="form-group">
															<select id="layoutrealestatecategoryidadd" name="layoutrealestatecategoryidadd" ng-model="layoutrealestatecategoryidadd" class="form-control" ng-change="setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in projectdetaillist | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType1}}</p>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<select class="form-control" id="projectfloor" name="projectfloor" ng-model="projectfloor" ng-options="item for item in floors" ng-change="setFlag()">
																<option value=""> Floor </option>										
															</select>
															<p ng-show="errorFloor == 1" style="color: red;">{{msgFloor}}</p>
														</div>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<input type="file" id="filelayoutadd" name="filelayoutadd" class="form-control" ng-change="setFlag()">
														</div>
														<p ng-show="errorImage == 1" style="color: red;">{{msgImage}}</p>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<input type="text" id="layoutnumberofunits" name="layoutnumberofunits" ng-model="layoutnumberofunits" placeholder="Number of units" class="form-control" ng-change="setFlag()">
														</div>
													</div>														
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="addProjectFloorLayoutRow()" class="btn btn-primary btn-sm" title="Add Project Floor Layout"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Tower Name</th>
																<th>Floor</th>
																<th>Image</th>
																<th class="text-right">Units</th>
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectfloorlayoutlist">
																<td> {{item.categoryTitle}} </td>
																<td> {{item.unitTitle}} </td>														
																<td> {{item.floorNumber}} </td>
																<td> <i class="fa fa-file-image-o" aria-hidden="true"></i> </td>
																<td class="text-right"> {{item.totalUnit}} </td>
																<td class="text-right">
																	<a href="#" ng-click="removeProjectFloorLayoutRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Property Type Area</h4>
											</div>
											<div class="panel-body">
												<div class="row form-group">
													<div class="col-md-3">															
														<div class="form-group">
															<select id="propertyareacategoryidadd" name="propertyareacategoryidadd" ng-model="propertyareacategoryidadd" class="form-control" ng-change="setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in projectdetaillist | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorAreaCategoryId == 1" style="color: red;">{{msgAreaCategoryId}}</p>
													</div>									
													<div class="col-md-3">															
														<div class="form-group">
															<select id="propertyareasubcategoryidadd" name="propertyareasubcategoryidadd" ng-model="propertyareasubcategoryidadd" class="form-control" ng-change="setFlag()">
																<option value="">Subcategory</option>
																<option ng-repeat="items in projectdetaillist | unique : 'subcategoryId'" value="{{items.subcategoryId}}">{{items.categoryTitle}} - {{items.subcategoryTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorAreaSubcategoryId == 1" style="color: red;">{{msgAreaSubcategoryId}}</p>
													</div>															
													<div class="col-md-3">															
														<div class="form-group">
															<select id="propertyareatypeidadd" name="propertyareatypeidadd" ng-model="propertyareatypeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Type</option>
																<option ng-repeat="items in projectdetaillist | unique: 'typeId'" value="{{items.typeId}}">{{items.subcategoryTitle}} - {{items.typeTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorAreaTypeId == 1" style="color: red;">{{msgAreaTypeId}}</p>
													</div>
													<div class="col-md-3">															
														<div class="form-group">
															<select id="unitmasteridadd" name="unitmasteridadd" ng-model="unitmasteridadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit Name</option>
																<option ng-repeat="item in projectdetaillist" value="{{item.unitId}}">{{item.typeTitle}} - {{item.unitTitle}}</option>
															</select>																
														</div>															
													</div>														
												</div>
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="propertyareaidadd" name="propertyareaidadd" ng-model="propertyareaidadd" class="form-control" ng-change="setFlag()">
																<option value="">Area Title</option>
																<option ng-repeat="items in getAreaName" value="{{items.areaTypeId}}">{{items.areaTypeTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#areaTypeModal" title="Add New Area"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorAreaId == 1" style="color: red;">{{msgAreaId}}</p>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="propertyareaunitidadd" name="propertyareaunitidadd" ng-model="propertyareaunitidadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorAreaUnitId == 1" style="color: red;">{{msgAreaUnitId}}</p>																										
														</div>									
													</div>	
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="areavalueadd" name="areavalueadd" ng-model="areavalueadd" placeholder="Area Value" class="form-control" ng-change="setFlag()">
															<p ng-show="errorAreaValue == 1" style="color: red;">{{msgAreaValue}}</p>
														</div>															
													</div>																																																							
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="addProjectAreaRow()" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Sub Category</th>
																<th>Type</th>
																<th>Unit Name</th>																																
																<th>Area Type</th>
																<th>Unit</th>
																<th class="text-right">Value</th>
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectarealist">																	
																<td> {{item.categoryTitle}} </td>
																<td> {{item.subcategoryTitle}} </td>																	
																<td> {{item.typeTitle}} </td>
																<td> {{item.unitName}} </td>
																<td> {{item.areaTitle}} </td>
																<td> {{item.unitTitle}} </td>
																<td class="text-right"> {{item.areaValue}} </td>
																<td class="text-right">
																	<a href="#" ng-click="removeProjectAreaRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Price Info</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-3">															
														<div class="form-group">
															<select id="realeidadd" name="realeidadd" ng-model="realeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in projectdetaillist | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryTypes}}</p>
													</div>									
													<div class="col-md-3">															
														<div class="form-group">
															<select id="realsubidadd" name="realsubidadd" ng-model="realsubidadd" class="form-control" ng-change="setFlag()">
																<option value="">Subcategory</option>
																<option ng-repeat="items in projectdetaillist | unique : 'subcategoryId'" value="{{items.subcategoryId}}">{{items.categoryTitle}} - {{items.subcategoryTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorRealestateSubCategoryTitle == 1" style="color: red;">{{msgSubCategoryTypes}}</p>
													</div>															
													<div class="col-md-3">															
														<div class="form-group">
															<select id="realtypeidadd" name="realtypeidadd" ng-model="realtypeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Type</option>
																<option ng-repeat="items in projectdetaillist | unique: 'typeId'" value="{{items.typeId}}">{{items.subcategoryTitle}} - {{items.typeTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorRealestateTitle == 1" style="color: red;">{{msgTypes}}</p>
													</div>
													<div class="col-md-3">															
														<div class="form-group">
															<select id="priceinfounitmasteridadd" name="priceinfounitmasteridadd" ng-model="priceinfounitmasteridadd" class="form-control">
																<option value="">Unit Name</option>
																<option ng-repeat="item in projectdetaillist" value="{{item.unitId}}">{{item.typeTitle}} - {{item.unitTitle}}</option>
															</select>																
														</div>															
													</div>														
												</div>
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="pricelableadd" name="pricelableadd" ng-model="pricelableadd" class="form-control" ng-change="setFlag()">
																<option value="">Price Lable</option>
																<option ng-repeat="item in getPricesLabel | unique:'priceLabelId'" value="{{item.priceLabel}}">{{item.priceLabel}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#priceLabelModal" title="Add New Price Label"><i class="fa fa-plus"></i></button>
															</span>
														</div>
													</div>	
													<!-- <div class="col-md-3">
														<div class="form-group">
															<input type="text" id="pricelableadd" name="pricelableadd" ng-model="pricelableadd" placeholder="Price Lable" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPriceLable == 1" style="color: red;">{{msgLable}}</p>
														</div>															
													</div> -->
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="pricevalueadd" name="pricevalueadd" ng-model="pricevalueadd" placeholder="Price Value" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPriceValue == 1" style="color: red;">{{msgValue}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="areaunittypeadd" name="areaunittypeadd" ng-model="areaunittypeadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit Type</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorAreaU == 1" style="color: red;">{{msgAreaU}}</p>																										
														</div>									
													</div>																											
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="addProjectPriceInfoRow()" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category </th>
																<th>Sub Category</th>
																<th>Type</th>
																<th>Unit Name</th>																																
																<th>Label</th>
																<th>Unit</th>
																<th class="text-right">Value</th>																	
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectpricedetaillist">																	
																<td> {{item.title}} </td>
																<td> {{item.subcategTitle}} </td>																	
																<td> {{item.realstatetitle}} </td>
																<td> {{item.unitTitle}} </td>
																<td> {{item.pricelable}} </td>
																<td> {{item.unit}} </td>
																<td class="text-right"> {{item.pricevalue | number:2}}  </td>																	
																<td class="text-right">
																	<a href="#" ng-click="removeProjectPriceInfoRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Payment Schedule</h4>
											</div>
											<div class="panel-body">
												<div class="row ">
													<div class="col-md-2">
														<div class="form-group">																
															<select class="form-control" id="sequenceadd" name="sequenceadd" ng-model="sequenceadd" ng-options="item for item in sequence">
																<option value=""> Sequence* </option>										
															</select>
															<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>																						
														</div>
													</div>									
													<!-- <div class="col-md-3">
														<div class="form-group">
															<input type="text" id="sequencetitleadd" name="sequencetitleadd" ng-model="sequencetitleadd" placeholder="Sequence Title" class="form-control" ng-change="setFlag()">
															<p ng-show="errorSequenceTitle == 1" style="color: red;">{{msgTitle}}</p>
														</div>															
													</div> -->
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="lableadd" name="lableadd" ng-model="lableadd" placeholder="Lable *" class="form-control" ng-change="setFlag()">
															<p ng-show="errorLable == 1" style="color: red;">{{msgLables}}</p>
														</div>															
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="valueadd" name="valueadd" ng-model="valueadd" placeholder="Value *" class="form-control" ng-change="setFlag()">
															<p ng-show="errorValue == 1" style="color: red;">{{msgValues}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="unittypeadd" name="unittypeadd" ng-model="unittypeadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit*</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorUnit == 1" style="color: red;">{{msgUnittype}}</p>																										
														</div>									
													</div>																											
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="addPaymentScheduleRow()" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																																						
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Seq </th>
																<!-- <th>Seq Title</th> -->
																<th>Lable</th>
																<th>Value</th>
																<th>Unit Type</th>
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in paymentschedulelist">
																<td> {{item.sequence}} </td>
																<!-- <td> {{item.sequencetitle}} </td> -->
																<td> {{item.lable}} </td>
																<td> {{item.value}} </td>
																<td> {{item.unit}} </td>
																<td>
																	<a href="#" ng-click="removePaymentScheduleRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Specifications</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="titleadd" name="titleadd" ng-model="titleadd" placeholder="Title" class="form-control">
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="subtitleadd" name="subtitleadd" ng-model="subtitleadd" placeholder="Subtitle" class="form-control">
														</div>
													</div>														
													<div class="col-md-4">
														<div class="form-group">
															<input type="file" id="sfileadd" name="sfileadd" ng-model="sfileadd" class="form-control">
														</div>
													</div>																					
												</div>
												<div class="row">
													<div class="col-md-10">
														<div class="form-group">
															<textarea cols="80" rows="10" data-sample-short id="specdescriptionadd" name="specdescriptionadd" ng-model="specdescriptionadd" placeholder="Description..." class="form-control"></textarea>
															<p ng-show="errorSpecDescription == 1" style="color: red;">{{msgSpecDescription}}</p>
														</div>
													</div>
													<div class="col-md-1">
														<div class="form-group">
															<a href="#" ng-click="addSpecificationRow()" class="btn btn-primary btn-sm" title="Add Specification"><span class="fa fa-plus"></span></a>
														</div>
													</div>
												</div>												
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Title </th>
																<th> Subtitle </th>
																<th> File </th>
																<th> Description </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in specificationlist" ng-show="item.title != null || item.title != null">
																<td> {{item.title}} </td>
																<td> {{item.subtitle}} </td>																	
																<td> <i class="fa fa-file-o" aria-hidden="true"></i> </td>
																<td ng-bind-html="item.description | to_trusted"></td>														
																<td>
																	<a href="#" ng-click="removeSpecificationRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Amenities</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="titleadd" name="titleadd" ng-model="titleadd" placeholder="Title" class="form-control">
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="subtitleadd" name="subtitleadd" ng-model="subtitleadd" placeholder="Subtitle" class="form-control">
														</div>
													</div>														
													<div class="col-md-4">
														<div class="form-group">
															<input type="file" id="afileadd" name="afileadd" ng-model="afileadd" class="form-control">
														</div>
													</div>																					
												</div>
												<div class="row">
													<div class="col-md-10">
														<div class="form-group">
															<textarea cols="80" rows="10" data-sample-short id="amenitydescriptionadd" name="amenitydescriptionadd" ng-model="amenitydescriptionadd" placeholder="Description..." class="form-control" ng-change="setFlag()"></textarea>
															<p ng-show="errorAmenityDescription == 1" style="color: red;">{{msgAmenityDescription}}</p>
														</div>
													</div>
													<div class="col-md-1">
														<div class="form-group">
															<a href="#" ng-click="addAmenityRow()" class="btn btn-primary btn-sm" title="Add Specification"><span class="fa fa-plus"></span></a>
														</div>
													</div>
												</div>												
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Title </th>
																<th> Subtitle </th>
																<th> File </th>
																<th> Description </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in amenitylist" ng-show="item.title != null || item.title != null">
																<td> {{item.title}} </td>
																<td> {{item.subtitle}} </td>																	
																<td> <i class="fa fa-file-o" aria-hidden="true"></i> </td>
																<td ng-bind-html="item.description | to_trusted"></td>														
																<td>
																	<a href="#" ng-click="removeAmenityRow(item)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<div class="row">									
								<div class="col-md-8 text-left">
									<strong ng-show="projectSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="projectSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-click="addProject()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
						<!-- </form> -->
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Project List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchProject() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchProject()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th>Sequence</th>
											<th>Project Title</th>
											<th>Project Subtitle</th>
											<th>Project Code</th>
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getProjects == ''">
											<td colspan="5"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getProjects" style="cursor:pointer;cursor:hand">
											<td ng-click="getProject(item.projectId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.sequence}}</td>
											<td ng-click="getProject(item.projectId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.projectTitle}}</td>
											<td ng-click="getProject(item.projectId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.projectSubtitle}}</td>
											<td ng-click="getProject(item.projectId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.projectCode}}</td>
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.projectId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getProjects != ''">
										<tr>											
											<td colspan="5" class="text-right">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getProjects.length+startindex}}</b> out of <b>{{allcounts.projectCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getProjects.length+startindex == allcounts.projectCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Project</h4>
					</div>
					<!-- <form> -->
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Developer Company</label>									
										<select id="developercompanyid" name="developercompanyid" ng-model="developercompanyid" class="form-control">
											<option value="">Developer Company</option>
											<option ng-repeat="item in getUserName" ng-if="item.userCompanyName" value="{{item.userId}}">{{item.userCompanyName}} </option>
										</select>										
									</div>										
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Title<font color="red" size="3">*</font></label>
										<input type="text" id="projecttitle" name="projecttitle" ng-model="projecttitle" placeholder="Project Title" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorProjectTitle == 1" style="color: red;">{{msgProjectTitle}}</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Subtitle</label>
										<input type="text" id="projectsubtitle" name="projectsubtitle" ng-model="projectsubtitle" placeholder="Project Subtitle" class="form-control">											
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Project Code</label>
										<input type="text" id="projectcode" name="projectcode" ng-model="projectcode" placeholder="Project Code" class="form-control">											
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Location<font color="red" size="3">*</font></label>
										<select id="locationid" name="locationid" ng-model="locationid" class="form-control">
											<option value="">Location</option>
											<option ng-repeat="item in getLocationName" value="{{item.locationId}}">{{item.locationName}}</option>
										</select>										
									</div>
									<p ng-show="errorLocation == 1" style="color: red;">{{msgLocation}}</p>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Architect</label>									
										<select id="architectid" name="architectid" ng-model="architectid" class="form-control">
											<option value="">Architect</option>
											<option ng-repeat="item in getArchitectName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}} </option>
										</select>										
									</div>										
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Structural Designer</label>									
										<select id="structuraldesignerid" name="structuraldesignerid" ng-model="structuraldesignerid" class="form-control">
											<option value="">Structural Designer</option>
											<option ng-repeat="item in getDesignerName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}} </option>
										</select>										
									</div>										
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Legal Advisor</label>									
										<select id="legaladvisorid" name="legaladvisorid" ng-model="legaladvisorid" class="form-control">
											<option value="">Structural Designer</option>
											<option ng-repeat="item in getAdvisorName" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}} </option>
										</select>										
									</div>										
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Work Status</label>
										<select id="workstatus" name="workstatus" ng-model="workstatus" ng-init="workstatus='Ongoing'" class="form-control">
											<!-- <option value="">Work Status</option> -->
											<option value="Ongoing">Ongoing</option>
											<option value="Completed">Completed</option>
										</select>										
									</div>									
								</div>
								<div class="col-md-3">
									<label>Property Ownership Type</label>
									<div class="input-group">
										<select id="propertytypeid" name="propertytypeid" ng-model="propertytypeid" ng-options="item.propertyTypeId as item.propertyTypeTitle for item in getPropertyName" class="form-control" ng-change="setFlag()">
											<option value="">Property Ownership Type</option>
											<!-- <option ng-repeat="item in getPropertyName" value="{{item.propertyTypeId}}">{{item.propertyTypeTitle}}</option> -->
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#propertytypeModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorPropertyTitle == 1" style="color: red;">{{msgPropertyTitle}}</p>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>RERA Approved?</label>
										<select id="rereapproved" name="rereapproved" ng-model="rereapproved" class="form-control">
											<option value="">RERA Approved?</option>
											<option value="y">Yes</option>
											<option value="n">No</option>
										</select>
									</div>
								</div>
								<div class="col-md-2" ng-show="rereapproved == 'y'">
									<div class="form-group">
										<label>RERA#</label>
										<input type="text" id="rerano" name="rerano" ng-model="rerano" placeholder="RERA No." class="form-control">
									</div>
								</div>
								<div class="col-md-2">
									<label>Water Source</label>
									<div class="form-group">											
										<select id="watersourceedit" name="watersourceedit" ng-model="watersourceedit" class="form-control" ng-change="setFlag()">
											<option value="">Water Source</option>
											<option value="Municipal">Municipal</option>
											<option value="Borewell">Borewell</option>
											<option value="Borewell & Municipal">Borewell & Municipal</option>
											<option value="Well">Well</option>
										</select>																					
									</div>
								</div>
								<div class="col-md-2">
									<label>Drainage</label>
									<div class="form-group">
										<select id="drainageedit" name="drainageedit" ng-model="drainageedit" class="form-control" ng-change="setFlag()">
											<option value="">Drainage</option>
											<option value="Private">Private</option>
											<option value="Municipal">Municipal</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Project Logo</label>
										<input type='file' id="projectlogoedit" class="form-control"/>
									</div>
								</div>
								<div class="col-md-2" ng-if="projectlogo">
									<div class="form-group">
										<label>&nbsp;</label><br>
										<a href="{{projectlogo}}" target="_blank">Project Logo</a>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Project Layout</label>
										<input type='file' id="projectlayoutedit" class="form-control"/>
									</div>
								</div>
								<div class="col-md-2" ng-if="projectlayout">
									<div class="form-group">
										<label>&nbsp;</label><br>
										<a href="{{projectlayout}}" target="_blank">Project Layout</a>
									</div>
								</div>
							</div>
							<!-- Added to take Image of Main Project 17-Feb-2020 -->
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Project Main Image</label>
										<input type="file" id="imageedit" name="projectmainimageedit" class="form-control"><br>
									</div>
								</div>
								<div class="col-md-2" ng-if="projmainimg">
									<div class="form-group">
										<label>&nbsp;</label><br>
										<a href="{{projmainimg}}" target="_blank">Image</a>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Project PDF</label>
										<input type="file" id="pdfedit" name="pdfedit" class="form-control"><br>
									</div>
								</div>
								<div class="col-md-2" ng-if="projectPDF">
									<div class="form-group">
										<label>&nbsp;</label><br>
										<a href="{{projectPDF}}" target="_blank">PDF</a>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Site Link</label>
										<input type="text" id="sitelinkedit" name="sitelinkedit" ng-model="sitelinkedit" class="form-control"><br>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Sequence<font color="red" size="3">*</font></label>
										<input type="number" id="sequence" name="sequence" ng-model="sequence" placeholder="Sequence" class="form-control" autofocus ng-change="checkSequence(sequence)|| setFlag()">
										<p ng-show="errorSeq == 1" style="color: red;">{{msgSeq}}</p>
									</div>									
								</div>
							</div>
							<div class="row text-center">
								<div class="col-md-12">
									<img style="width:100%" src="" id="target" />
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
										<label>Description</label>
										<textarea rows="4" id="description" name="description" ng-model="description" placeholder="Description..." class="form-control"></textarea>
									</div>
								</div>
							</div>							
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Information</h4>
											</div>
											<div class="panel-body">
												<div class="row form-group">
													<div class="col-md-3">														
														<div class="input-group">
															<select id="categoryid" name="categoryid" ng-model="categoryid" class="form-control" ng-change="onChangeRealestateedit() || setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Location"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>
													</div>									
													<div class="col-md-3">														
														<div class="input-group">
															<select id="subcategoryid" name="subcategoryid" ng-model="subcategoryid" ng-options="item.realestateSubcategoryId as item.subcategoryTitle for item in getSubcategoryTitles" class="form-control" ng-change="onChangeRealestateTypeedit() || setFlag()">
																<option value="">Subcategory</option>
																<!-- <option ng-repeat="items in getSubcategoryTitles" value="{{items.realestateSubcategoryId}}">{{items.subcategoryTitle}}</option> -->
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateSubModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRealestateSubCategoryTitle == 1" style="color: red;">{{msgSubCategoryType}}</p>
													</div>													
													<div class="col-md-3">														
														<div class="input-group">
															<select id="realestateid" name="realestateid" ng-model="realestateid" ng-options="item.realestateId as item.realestateTitle for item in getRealestateTitles" class="form-control" ng-change="setFlag()">
																<option value="">Type</option>
																<!-- <option ng-repeat="items in getRealestateTitles" value="{{items.realestateId}}">{{items.realestateTitle}}</option> -->
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateTypeModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRealestateTitle == 1" style="color: red;">{{msgType}}</p>
													</div>
													<div class="col-md-3">															
														<div class="input-group">
															<select id="towertitle" name="towertitle" ng-model="towertitle" class="form-control" ng-change="setFlag()">
																<option value="">Unit Name</option>
																<option ng-repeat="unit in unitNameList" value="{{unit.unitMasterId}}">{{unit.unitName}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#unitMasterModal" title="Add New Unit"><i class="fa fa-plus"></i></button>
															</span>
														</div>															
													</div>														
												</div>
												<div class="row">	
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="numberofunit" name="numberofunit" ng-model="numberofunit" placeholder="Number of units" class="form-control">
														</div>
													</div>														
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editProjectInfo(projectid)" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Sub Category</th>																
																<th>Type</th>
																<th>Unit Name</th>
																<th class="text-right">Total Units</th>
																<th class="text-right"> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectDetails">
																<td> {{item.categoryTitle}} </td>
																<td> {{item.subcategoryTitle}} </td>															
																<td> {{item.realestateTitle}} </td>
																<td> {{item.unitName}} </td>
																<td class="text-right"> {{item.numberOfUnits}} </td>
																<td class="text-right">
																	<a href="#" ng-click="removeProjectDetails(item.projectDetailId,projectid)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Floor Wise Layout </h4>
											</div>
											<div class="panel-body">
												<div class="row form-group">
													<div class="col-md-2">															
														<div class="form-group">
															<select id="layouttowertitleedit" name="layouttowertitleedit" ng-model="layouttowertitleedit" class="form-control" ng-change="setFlag()">
																<option value="">Unit Name</option>
																<option ng-repeat="items in projectDetails" value="{{items.unitMasterId}}">{{items.unitName}}</option>
															</select>
															<p ng-show="errorTower == 1" style="color: red;">{{msgTower}}</p>
														</div>															
													</div>
													<div class="col-md-3">															
														<div class="form-group">
															<select id="layoutrealestatecategoryidedit" name="layoutrealestatecategoryidedit" ng-model="layoutrealestatecategoryidedit" class="form-control" ng-change="setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in projectDetails | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
															</select>																
														</div>
														<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType1}}</p>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<select class="form-control" id="projectflooredit" name="projectflooredit" ng-model="projectflooredit" ng-options="item for item in floors" ng-change="setFlag()">
																<option value=""> Floor </option>										
															</select>
															<p ng-show="errorFloor == 1" style="color: red;">{{msgFloor}}</p>
														</div>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<input type="file" id="filelayoutedit" name="filelayoutedit" class="form-control" ng-change="setFlag()">
														</div>
														<p ng-show="errorImage == 1" style="color: red;">{{msgImage}}</p>
													</div>
													<div class="col-md-2">
														<div class="form-group">
															<input type="text" id="layoutnumberofunitsedit" name="layoutnumberofunitsedit" ng-model="layoutnumberofunitsedit" placeholder="Number of units" class="form-control" ng-change="setFlag()">
														</div>
													</div>														
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="projectinfospin == 1" ng-click="editProjectFloorLayoutRow()" class="btn btn-primary btn-sm" title="Add Project Floor Layout"><i class="fa fa-refresh fa-spin" ng-if="projectinfospin == 1"></i><span  ng-if="projectinfospin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Tower Name</th>
																<th>Floor</th>
																<th>Image</th>
																<th class="text-right">Units</th>
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectFloorLayout">
																<td> {{item.categoryName}} </td>
																<td> {{item.unitName}} </td>														
																<td> {{item.floorNumber}} </td>
																<td><a target="_blank" href="{{item.image}}"> <i class="fa fa-file-image-o" aria-hidden="true"></i></a> </td>
																<td class="text-right"> {{item.totalUnit}} </td>
																<td class="text-right">
																	<a href="#" ng-click="removeProjectFloorLayoutRow(item.projectFloorLayoutId)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Property Type Area</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-3">															
														<div class="form-group">
															<select id="propertyareacategoryidadd" name="propertyareacategoryidadd" ng-model="propertyareacategoryidadd" class="form-control" ng-change="setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in projectDetails | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
															</select>															
														</div>
														<p ng-show="errorAreaCategoryId == 1" style="color: red;">{{msgAreaCategoryId}}</p>
													</div>									
													<div class="col-md-3">															
														<div class="form-group">
															<select id="propertyareasubcategoryidadd" name="propertyareasubcategoryidadd" ng-model="propertyareasubcategoryidadd" class="form-control" ng-change="setFlag()">
																<option value="">Subcategory</option>
																<option ng-repeat="items in projectDetails | unique : 'subcategoryId'" value="{{items.subcategoryId}}">{{items.subcategoryTitle}}</option>
															</select>															
														</div>
														<p ng-show="errorAreaSubcategoryId == 1" style="color: red;">{{msgAreaSubcategoryId}}</p>
													</div>															
													<div class="col-md-3">															
														<div class="form-group">
															<select id="propertyareatypeidadd" name="propertyareatypeidadd" ng-model="propertyareatypeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Type</option>
																<option ng-repeat="items in projectDetails | unique: 'realestaeId'" value="{{items.realestaeId}}">{{items.subcategoryTitle}} - {{items.realestateTitle}}</option>
															</select>															
														</div>
														<p ng-show="errorAreaTypeId == 1" style="color: red;">{{msgAreaTypeId}}</p>
													</div>
													<div class="col-md-3">															
														<div class="form-group">
															<select id="unitmasteridadd" name="unitmasteridadd" ng-model="unitmasteridadd" class="form-control">
																<option value="">Unit Name</option>
																<option ng-repeat="items in projectDetails" value="{{items.unitMasterId}}">{{items.realestateTitle}} - {{items.unitName}}</option>
															</select>															
														</div>														
													</div>														
												</div>
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="propertyareaidadd" name="propertyareaidadd" ng-model="propertyareaidadd" class="form-control" ng-change="setFlag()">
																<option value="">Area Title</option>
																<option ng-repeat="items in getAreaName" value="{{items.areaTypeId}}">{{items.areaTypeTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#areaTypeModal" title="Add New Area"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorAreaId == 1" style="color: red;">{{msgAreaId}}</p>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="propertyareaunitidadd" name="propertyareaunitidadd" ng-model="propertyareaunitidadd" class="form-control" ng-change="setFlag()">
																<option value="">Unit</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorAreaUnitId == 1" style="color: red;">{{msgAreaUnitId}}</p>																										
														</div>									
													</div>	
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="areavalueadd" name="areavalueadd" ng-model="areavalueadd" placeholder="Area Value" class="form-control" ng-change="setFlag()">
															<p ng-show="errorAreaValue == 1" style="color: red;">{{msgAreaValue}}</p>
														</div>															
													</div>																																																							
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editProjectAreaRow(projectid)" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Subcategory</th>
																<th>Type</th>
																<th>Unit Name</th>																																
																<th>Area Type</th>
																<th>Unit</th>
																<th class="text-right">Value</th>
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectArea">																	
																<td> {{item.realestateTypeName}} </td>
																<td> {{item.subcategoryTitle}} </td>																	
																<td> {{item.realestateTitle}} </td>
																<td> {{item.unitName}} </td>
																<td> {{item.areaTypeTitle}} </td>
																<td> {{item.measurementUnitName}} </td>
																<td class="text-right"> {{item.areaValue}} </td>
																<td class="text-right">
																	<a href="#" ng-click="deleteProjectAreaRow(item.projectAreaId, projectid)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Price Info</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-3">														
														<div class="form-group">
															<select id="categoryid" name="categoryid" ng-model="categoryid" class="form-control" ng-change="setFlag()">
																<option value="">Category</option>
																<option ng-repeat="item in projectDetails | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
															</select>															
														</div>
														<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryTypes}}</p>
													</div>									
													<div class="col-md-3">														
														<div class="form-group">
															<select id="subcategoryid" name="subcategoryid" ng-model="subcategoryid" class="form-control" ng-change="setFlag()">
																<option value="">Subcategory</option>
																<option ng-repeat="items in projectDetails | unique : 'subcategoryId'" value="{{items.subcategoryId}}">{{items.subcategoryTitle}}</option>																
															</select>															
														</div>
														<p ng-show="errorRealestateSubCategoryTitle == 1" style="color: red;">{{msgSubCategoryTypes}}</p>
													</div>													
													<div class="col-md-3">														
														<div class="form-group">
															<select id="realestateid" name="realestateid" ng-model="realestateid" class="form-control" ng-change="setFlag()">
																<option value="">Type</option>
																<option ng-repeat="items in projectDetails | unique: 'realestaeId'" value="{{items.realestaeId}}">{{items.subcategoryTitle}} - {{items.realestateTitle}}</option>																
															</select>															
														</div>
														<p ng-show="errorRealestateTitle == 1" style="color: red;">{{msgTypes}}</p>
													</div>
													<div class="col-md-3">															
														<div class="form-group">
															<select id="priceinfounitmasterid" name="priceinfounitmasterid" ng-model="priceinfounitmasterid" class="form-control">
																<option value="">Unit Name</option>
																<option ng-repeat="items in projectDetails" value="{{items.unitMasterId}}">{{items.realestateTitle}} - {{items.unitName}}</option>
															</select>															
														</div>														
													</div>										
												</div>
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="pricelableedit" name="pricelableedit" ng-model="pricelableedit" class="form-control" ng-change="setFlag()">
																<option value="">Price Lable</option>
																<option ng-repeat="item in getPricesLabel | unique:'priceLabelId'" value="{{item.priceLabel}}">{{item.priceLabel}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#priceLabelModal" title="Add New Price Label"><i class="fa fa-plus"></i></button>
															</span>
														</div>
													</div>	
													<!-- <div class="col-md-3">
														<div class="form-group">
															<input type="text" id="pricelableedit" name="pricelableedit" ng-model="pricelableedit" placeholder="Price Lable" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPriceLable == 1" style="color: red;">{{msgLable}}</p>
														</div>															
													</div> -->
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="pricevalueedit" name="pricevalueedit" ng-model="pricevalueedit" placeholder="Price Value" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPriceValue == 1" style="color: red;">{{msgValue}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="areaunittypeedit" name="areaunittypeedit" ng-model="areaunittypeedit" class="form-control" ng-change="setFlag()">
																<option value="">Unit</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorAreaU == 1" style="color: red;">{{msgAreaU}}</p>																										
														</div>									
													</div>																											
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="projectpricespin == 1" ng-click="editProjectPriceInfo(projectid)" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="projectpricespin == 1"></i><span  ng-if="projectpricespin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Category</th>
																<th>Sub Category</th>
																<th>Type</th>
																<th>Unit Name</th>
																<th>Lable</th>
																<th>Unit</th>
																<th class="text-right">Value</th>																
																<th class="text-right">Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectPriceDetails">
																<td> {{item.categoryTitle}} </td>
																<td> {{item.subcategoryTitle}} </td>																	
																<td> {{item.realestateTitle}} </td>
																<td> {{item.unitMasterName}} </td>															
																<td> {{item.priceLable}} </td>
																<td> {{item.unitName}} </td>
																<td class="text-right"> {{item.priceValue | number:2}} </td>															
																<td class="text-right">
																	<a href="#" ng-click="removeProjectPriceDetails(item.projectPriceInfoId,projectid)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Payment Schedule</h4>
											</div>
											<div class="panel-body">
												<div class="row ">														
													<div class="col-md-2">
														<div class="form-group">
															<select class="form-control" id="sequenceedit" name="sequenceedit" ng-model="sequenceedit" ng-change="setFlag()">
																<option value=""> Sequence </option>
																<option ng-repeat="item in sequence" value="{{item}}">{{item}}</option>									
															</select>															
															<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>
														</div>															
													</div>
													<!-- <div class="col-md-3">
														<div class="form-group">
															<input type="text" id="sequencetitleedit" name="sequencetitleedit" ng-model="sequencetitleedit" placeholder="Sequence Title" class="form-control" ng-change="setFlag()">
															<p ng-show="errorSequenceTitle == 1" style="color: red;">{{msgTitle}}</p>
														</div>															
													</div> -->
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="lableedit" name="lableedit" ng-model="lableedit" placeholder="Lable *" class="form-control" ng-change="setFlag()">
															<p ng-show="errorLable == 1" style="color: red;">{{msgLables}}</p>
														</div>															
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="valueedit" name="valueedit" ng-model="valueedit" placeholder="Value *" class="form-control" ng-change="setFlag()">
															<p ng-show="errorValue == 1" style="color: red;">{{msgValues}}</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">																
															<select id="unittypeedit" name="unittypeedit" ng-model="unittypeedit" class="form-control" ng-change="setFlag()">
																<option value="">Unit*</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorUnit == 1" style="color: red;">{{msgUnittype}}</p>																										
														</div>									
													</div>																											
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="projectspin == 1" ng-click="editProjectPaymentSchedule(projectid)" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="projectspin == 1"></i><span  ng-if="projectspin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Seq </th>
																<!-- <th>Seq Title</th> -->
																<th>Lable</th>					
																<th>Unit Type</th>
																<th class="text-right">Value</th>
																<th class="text-right"> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectPaymentSchedule">
																<td> {{item.sequence}} </td>
																<!-- <td> {{item.sequenceTitle}} </td> -->																	
																<td> {{item.paymentLable}} </td>																
																<td> {{item.unitName}} </td>
																<td class="text-right"> {{item.paymentValue | number:2}} </td>
																<td class="text-right">
																	<a href="#" ng-click="removePaymentSchedule(item.projectPaymentScheduleId, projectid)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Specifications</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="titleadd" name="titleadd" ng-model="titleadd" placeholder="Title" class="form-control">
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="subtitleadd" name="subtitleadd" ng-model="subtitleadd" placeholder="Subtitle" class="form-control">
														</div>
													</div>														
													<div class="col-md-4">
														<div class="form-group">
															<input type="file" id=sfileedit name="sfileedit" ng-model="sfileedit" class="form-control">
														</div>
													</div>																					
												</div>
												<div class="row">
													<div class="col-md-10">
														<div class="form-group">
															<textarea cols="80" rows="10" data-sample-short id="specdescriptionedit" name="specdescriptionedit" ng-model="specdescriptionedit" placeholder="Description..." class="form-control"></textarea>
															<p ng-show="errorSpecDescription == 1" style="color: red;">{{msgSpecDescription}}</p>
														</div>
													</div>
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editSpecificationRow(projectid)" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>												
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Title </th>
																<th> Subtitle </th>
																<th> File </th>
																<th> Description </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectSpecification" ng-show="item.title != null || item.title != null">
																<td> {{item.title}} </td>
																<td> {{item.subtitle}} </td>																	
																<td><a href="{{item.attachment}}" target="_blank"><i class="fa fa-file-o" aria-hidden="true"></i></a> </td>
																<td ng-bind-html="item.description | to_trusted"> {{item.description}} </td>														
																<td>
																	<a ng-click="deleteSpecificationRow(item.projectId, item.projectSpecificationId)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Project Amenities</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="title" name="title" ng-model="title" placeholder="Title" class="form-control">
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="subtitle" name="subtitle" ng-model="subtitle" placeholder="Subtitle" class="form-control">
														</div>
													</div>														
													<div class="col-md-4">
														<div class="form-group">
															<input type="file" id="afileedit" name="afileedit" ng-model="afileedit" class="form-control">
														</div>
													</div>																					
												</div>
												<div class="row">
													<div class="col-md-10">
														<div class="form-group">
															<textarea cols="80" rows="10" data-sample-short id="amenitydescriptionedit" name="amenitydescriptionedit" ng-model="amenitydescriptionedit" placeholder="Description..." class="form-control" ng-change="setFlag()"></textarea>
															<p ng-show="errorAmenityDescription == 1" style="color: red;">{{msgAmenityDescription}}</p>
														</div>
													</div>
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editAmenityRow(projectid)" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>												
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Title </th>
																<th> Subtitle </th>
																<th> File </th>
																<th> Description </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in projectAmenity" ng-show="item.title != null || item.title != null">
																<td> {{item.title}} </td>
																<td> {{item.subtitle}} </td>																	
																<td><a href="{{item.attachment}}" target="_blank"><i class="fa fa-file-o" aria-hidden="true"></i></a> </td>
																<td ng-bind-html="item.description | to_trusted"></td>														
																<td>
																	<a ng-click="deleteAmenityRow(item.projectId, item.projectAmenityId)" class="delete" data-toggle="modal">
																		<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
																	</a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Payment Schedule</h4>
											</div>
											<div class="panel-body">																								
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Payment Label </th>																	
																<th> Payment Schedule </th>
																<th> Payment Percentage(%)</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in paymentById">
																<td> {{item.paymentLabel}} </td>
																<td> {{item.paymentSchedule}} </td>																	
																<td> {{item.percentage}} </td>																	
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div> -->						
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="projectSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="projectSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-click="editProject(projectid)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					<!-- </form> -->
				</div>
			</div>
		</div>
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Project</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<h4 ng-if="d != 0" style="color:red"> Property will be removed for this project.</h4>
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteProject()">
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="locationModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Location Type</h4>
					</div>
					<form ng-submit="addLocation()" id="locationtype">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Location Name<font color="red" size="3">*</font></label>
										<input type="text" id="locationnameadd" name="locationnameadd" ng-model="locationnameadd" placeholder="Location Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorLocationName == 1" style="color: red;">{{msgLocationName}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Location Code</label>
										<input type="text" id="locationcodeadd" name="locationcodeadd" ng-model="locationcodeadd" placeholder="Location Code" maxlength="5" capitalize class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Select Country<font color="red" size="3">*</font></label>										
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" autofocus >
											<option value="">Country</option>
										</select>
										<p ng-show="errorCountry == 1" style="color: red;">{{msgCountry}}</p>											
									</div>										
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>State<font color="red" size="3">*</font></label>
										<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" class="form-control" ng-change="getDistrictByStateId(statenameadd)">
											<option value="">State</option>
										</select>
										<p ng-show="errorState == 1" style="color: red;">{{msgState}}</p>											
									</div>
								</div>									
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>District<font color="red" size="3">*</font></label>
										<select id="districtnameadd" name="districtnameadd" ng-model="districtnameadd" ng-options="item.districtId as item.districtName for item in getDistricts" class="form-control" ng-change="getTalukaByDistrictId(districtnameadd)">
											<option value="">District</option>
										</select>
										<p ng-show="errorDistrict == 1" style="color: red;">{{msgDistrict}}</p>											
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Taluka</label>
										<select id="talukanameadd" name="talukanameadd" ng-model="talukanameadd" ng-options="item.talukaId as item.talukaName for item in getTalukas" class="form-control">
											<option value="">Taluka</option>
										</select>																						
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>City / Village</label>
										<input type="text" id="cityvillageadd" name="cityvillageadd" ng-model="cityvillageadd" placeholder="City / Village" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Moje</label>
										<input type="text" id="mojeadd" name="mojeadd" ng-model="mojeadd" placeholder="Moje" class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Locality / Area</label>
										<input type="text" id="areaadd" name="areaadd" ng-model="areaadd" placeholder="Locality / Area" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Zip</label>
										<input type="text" id="zipadd" name="zipadd" ng-model="zipadd" placeholder="Zip" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>City Survey</label>
										<input type="text" id="citysurveyadd" name="citysurveyadd" ng-model="citysurveyadd" placeholder="City Survey" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>TP</label>
										<input type="text" id="tpadd" name="tpadd" ng-model="tpadd" placeholder="TP" class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Google Map Link</label>
										<input type="text" id="gmaplinkadd" name="gmaplinkadd" ng-model="gmaplinkadd" placeholder="Google Map Link" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Latitude</label>
										<input type="text" id="latitudeadd" name="latitudeadd" ng-model="latitudeadd" placeholder="Latitude" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Longitude</label>
										<input type="text" id="longitudeadd" name="longitudeadd" ng-model="longitudeadd" placeholder="Longitude" class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label>Location Map</label>
									<input type='file' id="locationmap" class="form-control"/>
								</div>
							</div>	
						</div>
						<div class="modal-footer">
							<div class="row">									
								<div class="col-md-8 text-left">
									<strong ng-show="locationSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="locationSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>				
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="userModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add User Details</h4>
					</div>
					<form ng-submit="addUser()" id="usermodule" name="usermodule">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-3">
									<div class="form-group">
										<label>Company Name</label>
										<input type="text" id="companynameadd" name="companynameadd" ng-model="companynameadd" placeholder="Company Name" class="form-control">											
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>First Name<font color="red" size="3">*</font></label>
										<input type="text" id="firstnameadd" name="firstnameadd" ng-model="firstnameadd" placeholder="First Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorFirstName == 1" style="color: red;">{{msgFirstName}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Middle Name</label>
										<input type="text" id="middlenameadd" name="middlenameadd" ng-model="middlenameadd" placeholder="Middle Name" class="form-control">
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Last Name</label>
										<input type="text" id="lastnameadd" name="lastnameadd" ng-model="lastnameadd" placeholder="Last Name" class="form-control">
									</div>									
								</div>
							</div>
							<div class="row">
								<!-- <div class="col-md-2">
									<div class="form-group">
										<label>Gender</label>
										<div class="form-group">
											<select id="genderadd" name="genderadd" ng-model="genderadd" class="form-control">
												<option value="">Gender</option>
												<option value="m">Male</option>
												<option value="f">Female</option>
												<option value="o">Other</option>
											</select>
										</div>
									</div>
								</div> -->
								<div class="col-md-3">
									<div class="form-group">
										<label>User Type<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="usertypenameadd" name="usertypenameadd" ng-model="usertypenameadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="setFlag()" class="form-control">
												<option value="">User Type</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New User Type"><i class="fa fa-plus"></i></button>
											</span>
										</div>
										<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>											
									</div>
								</div>																		
								<div class="col-md-3">
									<div class="form-group">
										<label> Profile Picture </label>
										<input type="file" id="profilepictureadd" name="profilepictureadd" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> GST Number </label>
										<input type="text" id="gstnoadd" name="gstnoadd" ng-model="gstnoadd" class="form-control" placeholder="GST Number" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>PAN Number </label>
										<input type="text" id="pannumberadd" name="pannumberadd" ng-model="pannumberadd" class="form-control" placeholder="PAN Number" />
									</div>
								</div>
							</div>								
							<div class="row">
								<!-- <div class="col-md-3">
									<div class="form-group">
										<label> Date Of Birth </label>
										<div class="form-group">
											<input class="KendoDate" id="datepicker" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Aadhar Number </label>
										<input type="text" id="aadharnumberadd" name="aadharnumberadd" ng-model="aadharnumberadd" class="form-control" placeholder="0000-0000-0000" data-mask="9999-9999-9999" />
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Passport Number </label>
										<input type="text" id="passportnumberadd" name="passportnumberadd" ng-model="passportnumberadd" class="form-control" placeholder="Passport Number" />
									</div>
								</div> -->
							</div>
							<div class="row">									
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-1 </label>
										<input type="text" id="address1add" name="address1add" ng-model="address1add" class="form-control" placeholder="Address Line-1" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-2 </label>
										<input type="text" id="address2add" name="address2add" ng-model="address2add" class="form-control" placeholder="Address Line-2" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-3 </label>
										<input type="text" id="address3add" name="address3add" ng-model="address3add" class="form-control" placeholder="Address Line-3" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label>Country</label>
									<div class="input-group">
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" class="form-control" autofocus>
											<option value="">Country</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<label>State</label>
									<div class="input-group">
										<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-init="statenameadd = 12" class="form-control" autofocus>
											<option value="">State</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New State"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> City </label>
										<input type="text" id="citynameadd" name="citynameadd" ng-model="citynameadd" class="form-control" placeholder="City Name">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Pincode </label>
										<input type="text" id="pincodeadd" name="pincodeadd" ng-model="pincodeadd" class="form-control" placeholder="Pincode">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label> Mobile Number </label>
										<input type="text" id="mobilenumberadd" name="mobilenumberadd" ng-model="mobilenumberadd" class="form-control" placeholder="Mobile Number">
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Landline Number </label>
										<input type="text" id="landlinenumberadd" name="landlinenumberadd" ng-model="landlinenumberadd" class="form-control" placeholder="Landline Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Email </label>
										<input type="text" id="emailadd" name="emailadd" ng-model="emailadd" ng-change="checkEmailAddress()" class="form-control" placeholder="Email">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Password </label>
										<input type="text" id="passwordadd" name="passwordadd" ng-model="passwordadd" class="form-control" placeholder="Password">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">									
								<div class="col-md-8 text-left">
									<strong ng-show="userSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="userSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>				
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="companyModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Developer Company Details</h4>
					</div>
					<form ng-submit="addDeveloperCompany()" id="usermodule" name="usermodule">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-3">
									<div class="form-group">
										<label>Company Name<font color="red" size="3">*</font></label>
										<input type="text" id="dcompanynameadd" name="dcompanynameadd" ng-model="dcompanynameadd" placeholder="Company Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCompanyName == 1" style="color: red;">{{msgCompanyName}}</p>											
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>User Type<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="dusertypenameadd" name="dusertypenameadd" ng-model="dusertypenameadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="setFlag()" class="form-control">
												<option value="">User Type</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New User Type"><i class="fa fa-plus"></i></button>
											</span>
										</div>
										<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>											
									</div>
								</div>																		
								<div class="col-md-3">
									<div class="form-group">
										<label> Profile Picture </label>
										<input type="file" id="dprofilepictureadd" name="dprofilepictureadd" class="form-control">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>PAN Number </label>
										<input type="text" id="dpannumberadd" name="dpannumberadd" ng-model="dpannumberadd" class="form-control" placeholder="PAN Number" />
									</div>
								</div>
								<!-- <div class="col-md-3">
									<div class="form-group">
										<label> GST Number </label>
										<input type="text" id="gstnumberadd" name="gstnumberadd" ng-model="gstnumberadd" class="form-control" placeholder="GST Number" />
									</div>
								</div> -->
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-1 </label>
										<input type="text" id="daddress1add" name="daddress1add" ng-model="daddress1add" class="form-control" placeholder="Address Line-1" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-2 </label>
										<input type="text" id="daddress2add" name="daddress2add" ng-model="daddress2add" class="form-control" placeholder="Address Line-2" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Address-3 </label>
										<input type="text" id="daddress3add" name="daddress3add" ng-model="daddress3add" class="form-control" placeholder="Address Line-3" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label>Country</label>
									<div class="input-group">
										<select id="dcountrynameadd" name="dcountrynameadd" ng-model="dcountrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(dcountrynameadd)" ng-init="countrynameadd = 1" class="form-control" autofocus>
											<option value="">Country</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<label>State</label>
									<div class="input-group">
										<select id="dstatenameadd" name="dstatenameadd" ng-model="dstatenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-init="dstatenameadd = 12" class="form-control" autofocus>
											<option value="">State</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#stateModal" title="Add New State"><i class="fa fa-plus"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> City </label>
										<input type="text" id="dcitynameadd" name="dcitynameadd" ng-model="dcitynameadd" class="form-control" placeholder="City Name">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Pincode </label>
										<input type="text" id="dpincodeadd" name="dpincodeadd" ng-model="dpincodeadd" class="form-control" placeholder="Pincode">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<div class="form-group">
										<label> Mobile Number </label>
										<input type="text" id="dmobilenumberadd" name="dmobilenumberadd" ng-model="dmobilenumberadd" class="form-control" placeholder="Mobile Number">
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<label>Landline Number </label>
										<input type="text" id="dlandlinenumberadd" name="dlandlinenumberadd" ng-model="dlandlinenumberadd" class="form-control" placeholder="Landline Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label> Email </label>
										<input type="text" id="demailadd" name="demailadd" ng-model="demailadd" ng-change="checkEmailAddress()" class="form-control" placeholder="Email">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Password </label>
										<input type="text" id="dpasswordadd" name="dpasswordadd" ng-model="dpasswordadd" class="form-control" placeholder="Password">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">									
								<div class="col-md-8 text-left">
									<strong ng-show="userSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="userSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>				
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="userTypeModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add User Type</h4>
					</div>
					<form ng-submit="addUserType()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>User Type Name<font color="red" size="3">*</font></label>
										<input type="text" id="usertypename" name="usertypename" ng-model="usertypename" placeholder="User Type Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="userTypeSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{userTypeSuccessMsg}}</strong>
									<strong ng-show="userTypeSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{userTypeErrorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userTypeSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userTypeSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="priceLabelModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Price Label</h4>
					</div>
					<form ng-submit="addPrice()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Price Label<font color="red" size="3">*</font></label>
										<input type="text" id="pricelabeladd" name="pricelabeladd" ng-model="pricelabeladd" placeholder="Price Label" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorPriceLabel == 1" style="color: red;">{{msgPriceLabel}}</p>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Description" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="priceSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="priceSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="countryModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Country</h4>
					</div>
					<form ng-submit="addCountry()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Country Name<font color="red" size="3">*</font></label>
										<input type="text" id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" placeholder="Country Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorCountryName == 1" style="color: red;">{{msgCountryName}}</p>
									</div>									
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Country Code</label>
										<input type="text" id="countrycodeadd" name="countrycodeadd" ng-model="countrycodeadd" class="form-control">										
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Dialling Code</label>
										<input type="text" id="countrydialingcodeadd" name="countrydialingcodeadd" ng-model="countrydialingcodeadd" class="form-control">										
									</div>									
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="countrySubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="countrySubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spinCountry == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinCountry == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="propertytypeModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Property Type</h4>
					</div>
					<form ng-submit="addPropertyType()" id="propertytype">
						<div class="modal-body">
							<div class="row">																
								<div class="col-md-6">
									<div class="form-group">
										<label>Property Type Title<font color="red" size="3">*</font></label>
										<input type="text" id="propertytitleadd" name="propertytitleadd" ng-model="propertytitleadd" placeholder="Property Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-6">
									<div class="form-group">
										<label>Code</label>
										<input type="text" id="propertycodeadd" name="propertycodeadd" ng-model="propertycodeadd" placeholder="Property Code" maxlength="3" capitalize class="form-control">
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Logo</label>
										<input type="file" id="image2add" name="image2add" ng-model="image2add" class="form-control">										
									</div>									
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
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userTypeSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userTypeSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="realestateModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Realestate Category</h4>
					</div>
					<form ng-submit="addCategory()" id="realestatecategory">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Category Title<font color="red" size="3">*</font></label>
										<input type="text" id="realestatetitleadd" name="realestatetitleadd" ng-model="realestatetitleadd" placeholder="Category Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-6">
									<div class="form-group">
										<label>Category Code<font color="red" size="3">*</font></label>
										<input type="text" id="realestatecodeadd" name="realestatecodeadd" ng-model="realestatecodeadd" placeholder="Category Code" maxlength="5" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryCode == 1" style="color: red; font-size: 14px;">{{msgCategoryCode}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Logo</label>
										<input type="file" id="imageadd" name="imageadd" ng-model="imageadd" class="form-control">										
									</div>									
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
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userTypeSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userTypeSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>	
		
		<div class="modal fade" id="realestateSubModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Realestate Sub-category Type</h4>
					</div>
					<form ng-submit="addSubcategory()" id="realestateSubcategory">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Category <font color="red" size="3">*</font></label>
										<select id="realestatetypeidadd" name="realestatetypeidadd" ng-model="realeidadd" class="form-control" ng-change="setFlag()">
											<option value="">Category</option>
											<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>											
										</select>
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>									
									</div>									
								</div>								
								<div class="col-md-4">
									<div class="form-group">
										<label>Subcategory Title<font color="red" size="3">*</font></label>
										<input type="text" id="realestatesubtitleadd" name="realestatesubtitleadd" ng-model="realestatesubtitleadd" placeholder="Realestate Subcategory Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorRealSubTitle == 1" style="color: red; font-size: 14px;">{{msgSubcategoryTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-4">
									<div class="form-group">
										<label>Code<font color="red" size="3">*</font></label>
										<input type="text" id="realestatecodeadd" name="realestatecodeadd" ng-model="realestatecodeadd" placeholder="Code" maxlength="5" class="form-control" ng-change="setFlag()">
										<p ng-show="errorRealSubCode == 1" style="color: red; font-size: 14px;">{{msgSubcategoryCode}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Logo</label>
										<input type="file" id="image1add" name="image1add" ng-model="image1add" class="form-control">										
									</div>									
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
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userTypeSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userTypeSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="realestateTypeModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Realestate Type</h4>
					</div>
					<form ng-submit="addType()" id="realestatetype">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<label>Category<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="realeidadd" name="realeidadd" ng-model="realeidadd" class="form-control" ng-change="onChangeRealestateCategory(realeidadd) || setFlag()">
											<option value="">Category</option>
											<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Category"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorCategoryTitle == 1" style="color: red;">{{msgCategoryTitle}}</p>
								</div>									
								<div class="col-md-3">
									<label>Subcategory<font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="realsubidadd" name="realsubidadd" ng-model="realsubidadd" class="form-control" ng-change="setFlag()">
											<option value="">Subcategory of Realestate</option>
											<option ng-repeat="items in getSubcategoryTitles" value="{{items.realestateSubcategoryId}}">{{items.subcategoryTitle}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateSubModal" title="Add New Subcategory"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorRealSubTitle == 1" style="color: red;">{{msgSubcategoryTitle}}</p>
								</div>									
								<div class="col-md-3">
									<div class="form-group">
										<label>Type Title<font color="red" size="3">*</font></label>
										<input type="text" id="realestatetitleadd" name="realestatetitleadd" ng-model="realestatetitleadd" placeholder="Type Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorTypeTitle == 1" style="color: red; font-size: 14px;">{{msgTypeTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Code<font color="red" size="3">*</font></label>
										<input type="text" id="realestatecodeadd" name="realestatecodeadd" ng-model="realestatecodeadd" placeholder="Code" maxlength="5" class="form-control" ng-change="setFlag()">
										<p ng-show="errorTypeCode == 1" style="color: red; font-size: 14px;">{{msgTypeCode}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Logo</label>
										<input type="file" id="image3add" name="image3add" ng-model="image3add" class="form-control">										
									</div>									
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
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
									<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="userTypeSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="userTypeSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>		
		<div class="modal fade" id="stateModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add State</h4>
					</div>
					<form ng-submit="addState()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Country<font color="red" size="3">*</font></label>									
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" autofocus ng-change="setFlag()">
											<option value="">Country</option>
										</select>
										<p ng-show="errorCountry == 1" style="color: red;">{{msgCountry}}</p>										
									</div>									
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label>State Name<font color="red" size="3">*</font></label>
										<input type="text" id="statenameadd" name="statenameadd" ng-model="statenameadd" placeholder="State Name" class="form-control" ng-change="setFlag()">
										<p ng-show="errorStateName == 1" style="color: red;">{{msgStateName}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>State Code</label>
										<input type="text" id="statecodeadd" name="statecodeadd" ng-model="statecodeadd" placeholder="State Code" maxlength="3" capitalize class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="stateSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="stateSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spinState == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinState == 1"></i> Submit</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="unitMasterModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add New Unit</h4>
					</div>
					<form ng-submit="addUnitMaster()">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Unit Name<font color="red" size="3">*</font></label>
										<input type="text" id="unitname" name="unitname" ng-model="unitname" placeholder="Unit Name" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorUnitName == 1" style="color: red;">{{msgUnitName}}</p>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="unitSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{unitSuccessMsg}}</strong>
									<strong ng-show="unitSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{unitErrorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="unitSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="unitSpin == 1"></i> Save </button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular-filter.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/project.js"></script>
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		<script src="https://cdn.ckeditor.com/4.12.1/basic/ckeditor.js"></script>
		<script src="<%=request.getContextPath()%>/resources/admin/js/jquery.Jcrop.js"></script>
		<script>
			$(document).ready(function () {			 		         
		         $("#datepicker,#datepicker1").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true
					
		         });
		    });
			$(".KendoDate").bind("focus", function(){
	  			$(this).data("kendoDatePicker").open(); 
			});
			$(function () {
				$(".select2").select2();
			});

			CKEDITOR.replace('descriptionadd', {
				height: 150
			});
			
			CKEDITOR.replace('description', {
				height: 150
			});
			
			CKEDITOR.replace('specdescriptionadd', {
				height: 150
			});
			
			CKEDITOR.replace('specdescriptionedit', {
				height: 150
			});
			CKEDITOR.replace('amenitydescriptionadd', {
				height: 150
			});
			
			CKEDITOR.replace('amenitydescriptionedit', {
				height: 150
			});
			
			document.getElementById("manage").classList.add("active");
			document.getElementById("project").classList.add("active");
		
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
										aspectRatio : 4.1 / 3.2,
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
				$("#imageedit").change(function() {
					readURL(this);
				});
				$("#imageedit").click(function() {
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
										aspectRatio : 4.1 / 3.2,
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
				$("#projectimageadd").change(function() {
					readURL(this);
				});
				$("#projectimageadd").click(function() {
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