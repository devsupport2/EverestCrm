<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Property </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/property.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular-filter.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	</head>
	<style>
		input[type=number]::-webkit-inner-spin-button,
		input[type=number]::-webkit-outer-spin-button {
		    -webkit-appearance: none;
		    -moz-appearance: none;
		    appearance: none;
		    margin: 0;
		}
		.dropdown-menu>li>a {
			padding: 3px 0px !important;
		}
	</style>
	<body ng-app="MyApp" ng-controller="propertyCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Property
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Property</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Property</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<form>
							<div class="box-body">
								<div class="row">									
									<div class="col-md-3">
										<div class="form-group">
											<label>Project<font color="red" size="3">*</font></label>
											<select id="projectidadd" name="projectidadd" ng-model="projectidadd" class="form-control" ng-change="onChangeProject(projectidadd) || setFlag()">
												<option value="">Project</option>
												<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
											</select>
											<p ng-show="errorProjectTitle == 1" style="color: red;">{{msgProjectType}}</p>										
										</div>									
									</div>
									<div class="col-md-3">
										<label>Category<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="categoryidadd" name="categoryidadd" ng-model="categoryidadd" class="form-control" ng-change="onChangeCategory(projectidadd, categoryidadd) || setFlag()">
												<option value="">Category</option>
												<option ng-repeat="item in projectRealestateCategory  | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
											</select>
											<!-- <span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Location"><i class="fa fa-plus"></i></button>
											</span> -->
										</div>
										<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>
									</div>									
									<div class="col-md-3">
										<label>Subcategory<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="subcategoryidadd" name="subcategoryidadd" ng-model="subcategoryidadd" class="form-control" ng-change="onChangeSubcategory(projectidadd, categoryidadd, subcategoryidadd) || setFlag()">
												<option value="">Subcategory</option>
												<option ng-repeat="item in projectSubcategory" value="{{item.subcategoryId}}">{{item.subcategoryTitle}}</option>
											</select>
											<!-- <span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateSubModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
											</span> -->
										</div>
										<p ng-show="errorRealestateSubcategoryTitle == 1" style="color: red;">{{msgRealestateSubType}}</p>
									</div>	
									<div class="col-md-3">
										<label>Type<font color="red" size="3">*</font></label>
										<div class="form-group">
											<select id="projecttypeidadd" name="projecttypeidadd" ng-model="projecttypeidadd" class="form-control" ng-change="onChangeType(projectidadd, categoryidadd, subcategoryidadd, projecttypeidadd) || setFlag()">
												<option value="">Type</option>
												<option ng-repeat="items in projectRealestateType" value="{{items.realestaeId}}">{{items.realestateTitle}}</option>
											</select>
											<!-- <span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateTypeModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
											</span> -->
										</div>
										<p ng-show="errorRealestateTypeTitle == 1" style="color: red;">{{msgRealestateTypeTitle}}</p>
									</div>																									
								</div>
								<div class="row">
									<div class="col-md-3">
										<label>Unit Name</label>
										<div class="form-group">
											<select id="projectunitidadd" name="projectunitidadd" ng-model="projectunitidadd" class="form-control" ng-change="onChangeUnitName(projectidadd, categoryidadd, subcategoryidadd, projecttypeidadd, projectunitidadd)">
												<option value="">Unit Name</option>
												<option ng-repeat="items in projectUnitNameList" value="{{items.unitMasterId}}">{{items.unitName}}</option>
											</select>											
										</div>										
									</div>									
									<div class="col-md-2">
										<div class="form-group">
											<label>Floor</label>
											<select class="form-control" id="floor" name="floor" ng-model="floor" ng-options="item for item in floor">
												<option value=""> Floor </option>										
											</select>																						
										</div>
									</div>							
									<div class="col-md-3">
										<div class="form-group">
											<label>Property Number<font color="red" size="3">*</font></label>
											<input type="text" id="propertytitle" name="propertytitle" ng-model="propertytitle" placeholder="Property Number" class="form-control" ng-change="setFlag()">
											<p ng-show="errorUnits == 1" style="color: red;">{{msgUnits}}</p>											
										</div>
									</div>
									<div class="col-md-3">
										<label>Furnishing</label>
										<div class="form-group">											
											<select id="furnishingadd" name="furnishingadd" ng-model="furnishingadd" class="form-control" ng-change="setFlag()">
												<option value="">Furnishing</option>
												<option value="Furnished">Furnished</option>
												<option value="Un-Furnished">Un-Furnished</option>
												<option value="Semi Furnished">Semi Furnished</option>																						
											</select>																					
										</div>
									</div>									
								</div>								
								<div class="row">																
									<div class="col-md-3">
										<label>Reserved Parking</label>
										<div class="form-group">											
											<select id="parkingadd" name="parkingadd" ng-model="parkingadd" class="form-control" ng-change="setFlag()">
												<option value="">Reserved Parking</option>
												<option value="Y">Yes</option>
												<option value="N">No</option>																																		
											</select>																					
										</div>
									</div>
									<div class="col-md-3">
										<label>Property Availability</label>
										<div class="form-group">											
											<select id="availabilityadd" name="availabilityadd" ng-model="availabilityadd" class="form-control" ng-change="setFlag()">
												<option value="">Property Availability</option>
												<option value="Ready Possession"> Ready Possession</option>
												<option value="Under Construction">Under Construction</option>																																		
											</select>																					
										</div>
									</div>									
									<div class="col-md-6">
										<div class="form-group">
											<label>Property Availability Description</label>
											<textarea rows="1" id="descriptionadd" name="descriptionadd" ng-model="descriptionadd" placeholder="Property Availability Description..." class="form-control"></textarea>
										</div>
									</div>									
								</div>																
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label>Property Description</label>
											<textarea rows="4" id="pdescriptionadd" name="pdescriptionadd" ng-model="pdescriptionadd" placeholder="Description..." class="form-control"></textarea>
										</div>
									</div>
								</div>
								<div class="row">																		
									<div class="col-md-12">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Area</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-3">															
															<div class="input-group">
																<select id="areatypeidadd" name="areatypeidadd" ng-model="areatypeidadd" class="form-control" ng-change="setFlag()">
																	<option value="">Area Title</option>
																	<option ng-repeat="items in getAreaName" value="{{items.areaTypeId}}">{{items.areaTypeTitle}}</option>
																</select>
																<span class="input-group-btn">
																	<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#areaTypeModal" title="Add New Area"><i class="fa fa-plus"></i></button>
																</span>
															</div>
															<p ng-show="errorArea == 1" style="color: red;">{{msgArea}}</p>
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
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="areavalueadd" name="areavalueadd" ng-model="areavalueadd" placeholder="Value" class="form-control" ng-change="setFlag()">
																<p ng-show="errorAreaV == 1" style="color: red;">{{msgAreaV}}</p>
															</div>
														</div>																												
														<div class="col-md-1">
															<div class="form-group">
																<button ng-disabled="spin == 1" ng-click="addAreaRow()" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
															</div>
														</div>
													</div>																									
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Title </th>										
																	<th> Unit </th>
																	<th> Value </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in projectArea">
																	<td> {{item.areaTypeTitle}} </td>																																		
																	<td> {{item.measurementUnitName}} </td>	
																	<td> {{item.areaValue}} </td>													
																	<td>
																		<a href="#" ng-click="removeAreaRow(item)" class="delete" data-toggle="modal">
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
													 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Price</h4>
												</div>
												<div class="panel-body">
													<div class="row">														
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="pricelableadd" name="pricelableadd" ng-model="pricelableadd" placeholder="Lable" class="form-control" ng-change="setFlag()">
																<p ng-show="errorPriceL == 1" style="color: red;">{{msgPriceL}}</p>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="pricevalueadd" name="pricevalueadd" ng-model="pricevalueadd" placeholder="Value" class="form-control" ng-change="setFlag()">
																<p ng-show="errorPriceV == 1" style="color: red;">{{msgPriceV}}</p>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">																
																<select id="areaunittypeadd" name="areaunittypeadd" ng-model="areaunittypeadd" class="form-control" ng-change="setFlag()">
																	<option value="">Unit Type</option>
																	<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
																</select>
																<p ng-show="errorPriceU == 1" style="color: red;">{{msgPriceU}}</p>																										
															</div>									
														</div>														
														<div class="col-md-1">
															<div class="form-group">
																<button ng-disabled="spin == 1" ng-click="addPriceDetailRow()" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
															</div>
														</div>
													</div>																									
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Label </th>																	
																	<th class="text-right"> Amount </th>
																	<th> Unit Name </th>																						
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in projectUnitPrice">
																	<td> {{item.priceLable}} </td>
																	<td class="text-right"> {{item.priceValue | number:2}}</td>
																	<td> {{item.unitName}} </td>																																	
																	<td>
																		<a href="#" ng-click="removePriceDetailRow(item)" class="delete" data-toggle="modal">
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
													 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Room</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-3">															
															<div class="input-group">
																<select id="roomtypeidadd1" name="roomtypeidadd1" ng-model="roomtypeidadd1" class="form-control" ng-change="setFlag()">
																	<option value="">Room Title</option>
																	<option ng-repeat="items in getRoomName" value="{{items.roomTitleId}}">{{items.roomTitle}}</option>
																</select>
																<span class="input-group-btn">
																	<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#roomTypeModal" title="Add New Area"><i class="fa fa-plus"></i></button>
																</span>
															</div>
															<p ng-show="errorRoomTitle == 1" style="color: red;">{{msgTitle}}</p>
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<input type="text" id="roomlengthadd" name="roomlengthadd" ng-model="roomlengthadd" placeholder="Room Length" class="form-control" ng-change="setFlag()">
																<p ng-show="errorRoomLength == 1" style="color: red;">{{msgLength}}</p>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">																
																<select id="roomlengthunit" name="roomlengthunit" ng-model="roomlengthunit" class="form-control" ng-change="setFlag()">
																	<option value="">Unit Type</option>
																	<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
																</select>
																<p ng-show="errorRoomLunit == 1" style="color: red;">{{msgLunit}}</p>
															</div>									
														</div>													
														<div class="col-md-3">
															<div class="form-group">
																<input type="text" id="roombreadthadd" name="roombreadthadd" ng-model="roombreadthadd" placeholder="Room Breadth" class="form-control" ng-change="setFlag()">
																<p ng-show="errorRoomBreadth == 1" style="color: red;">{{msgBreadth}}</p>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">																
																<select id="roombreadthunit" name="roombreadthunit" ng-model="roombreadthunit" class="form-control" ng-change="setFlag()">
																	<option value="">Unit Type</option>
																	<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
																</select>
																<p ng-show="errorRoomBunit == 1" style="color: red;">{{msgBunit}}</p>
															</div>									
														</div>														
														<div class="col-md-3">
															<div class="form-group">
																<input type="text" id="roomheightadd" name="roomheightadd" ng-model="roomheightadd" placeholder="Room Height" class="form-control" ng-change="setFlag()">
																<p ng-show="errorRoomHeight == 1" style="color: red;">{{msgHeight}}</p>
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">																
																<select id="roomheightunit" name="roomheightunit" ng-model="roomheightunit" class="form-control" ng-change="setFlag()">
																	<option value="">Unit Type</option>
																	<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
																</select>
																<p ng-show="errorRoomHunit == 1" style="color: red;">{{msgHunit}}</p>
															</div>									
														</div>														
														<div class="col-md-3">
															<div class="form-group">
																<button ng-disabled="spin == 1" ng-click="addRoomRow()" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
															</div>
														</div>
													</div>																									
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Title </th>																	
																	<th> Length (Unit) </th>
																	<th> Breadth (Unit) </th>
																	<th> Height (Unit) </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in roomlist">
																	<td> {{item.title}} </td>
																	<td> {{item.length}}({{item.lengthunit}}) </td>
																	<td> {{item.breadth}}({{item.breadthunit}}) </td>
																	<td> {{item.height}}({{item.heightunit}}) </td>																	
																	<td>
																		<a href="#" ng-click="removeRoomRow(item)" class="delete" data-toggle="modal">
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
													 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Payment Schedule</h4>
												</div>
												<div class="panel-body">
													<div class="row ">														
														<div class="col-md-3">
															<div class="form-group">
																<input type="number" id="sequenceadd" name="sequenceadd" ng-model="sequenceadd" placeholder="Sequence *" class="form-control" ng-change="setFlag()">
																<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>
															</div>															
														</div>
														<div class="col-md-3">
															<div class="form-group">
																<input type="text" id="sequencetitleadd" name="sequencetitleadd" ng-model="sequencetitleadd" placeholder="Sequence Title" class="form-control" ng-change="setFlag()">
																<p ng-show="errorSequenceTitle == 1" style="color: red;">{{msgTitle}}</p>
															</div>															
														</div>
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
													</div>
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">																
																<select id="unittypeadd" name="unittypeadd" ng-model="unittypeadd" class="form-control" ng-change="setFlag()">
																	<option value="">Unit Type *</option>
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
																	<th>Seq Title</th>																	
																	<th>Label</th>																	
																	<th>Value</th>
																	<th>Unit Type</th>
																	<th>Action</th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in projectPaymentSchedule">
																	<td> {{item.sequence}} </td>
																	<td> {{item.sequenceTitle}} </td>
																	<td> {{item.paymentLable}} </td>
																	<td> {{item.paymentValue | number:2}} </td>
																	<td> {{item.unitName}} </td>
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
							</div>
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="propertySubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="propertySubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
									</div>
									<div class="col-md-4 text-right">
										<button type="submit" ng-click="addProperty()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-2">
									<h3 class="box-title">Property List</h3>
								</div>
								<div class="col-md-4"  ng-show="projectid ==null">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchProperty() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchProperty()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<select id="projectid" name="projectid" ng-model="projectid" class="form-control" ng-change="getPropertyByProjectId(projectid) | onChangeProject(projectid)">
											<option value="">Project</option>
											<option ng-repeat="p in getProjectName" value="{{p.projectId}}">{{p.projectTitle}}</option>
										</select>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<select id="pcategoryid" name="pcategoryid" ng-model="pcategoryid" class="form-control" ng-change="onChangeCategory(projectidadd, categoryidadd) || setFlag()">
											<option value="">Category</option>
											<option ng-repeat="item in projectRealestateCategory  | unique:'categoryId'" value="{{item.categoryId}}">{{item.categoryTitle}}</option>
										</select>
										<!-- <span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Location"><i class="fa fa-plus"></i></button>
										</span> -->
									</div>
								</div>
								<div class="col-md-2" ng-show="projectid !=null">
									<div class="form-group">
										<select id="projectunitid" name="projectunitid" ng-model="projectunitid" class="form-control" ng-change="getPropertyById(projectid, projectunitid)">
											<option value="">Unit Name</option>
											<option ng-repeat="items in projectUnitNameList" value="{{items.unitMasterId}}">{{items.unitName}}</option>
										</select>											
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
											<th>Unit Name</th>
											<th>Property Number</th>
											<th>Project Title</th>
											<th>Category</th>
											<th>Sub-category</th>
											<th>Type of Realestate</th>
											<th>Clone </th>
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getProperty == ''">
											<td colspan="8"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getProperty | filter:projectid" style="cursor:pointer;cursor:hand">
											<td ng-click="getPropertyDetails(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.towerTitle}}</td>
											<td ng-click="getPropertyDetails(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.propertyTitle}}</td>
											<td ng-click="getPropertyDetails(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.projectTitle}}</td>
											<td ng-click="getPropertyDetails(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.realestateTypeName}}</td>
											<td ng-click="getPropertyDetails(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.subcategoryTitle}}</td>
											<td ng-click="getPropertyDetails(item.propertyId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.realestateTitle}}</td>
											<td ng-click="createCloneProperty(item.propertyId)"><i class="fa fa-clone" aria-hidden="true"></i></td>
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.propertyId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getProperty != ''">
										<tr>											
											<td colspan="8" class="text-right">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getProperty.length+startindex}}</b> out of <b>{{allcounts.propertyCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getProperty.length+startindex == allcounts.propertyCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Property</h4>
					</div>
					<form id="editproperty">
						<div class="box-body">
							<div class="row">									
								<div class="col-md-3">
									<div class="form-group">
										<label>Project<font color="red" size="3">*</font></label>
										<select id="projectidedit" name="projectidedit" ng-model="projectidedit" class="form-control" ng-change="setFlag()" ng-disabled="true">
											<option value="">Project</option>
											<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
										</select>
										<p ng-show="errorProjectTitle == 1" style="color: red;">{{msgProjectType}}</p>										
									</div>									
								</div>
								<div class="col-md-3">
									<label>Category<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="categoryid" name="categoryid" ng-model="categoryid" class="form-control" ng-change="onChangeRealestateedit() || setFlag()" ng-disabled="true">
											<option value="">Category</option>
											<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
										</select>
										<!-- <span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Location"><i class="fa fa-plus"></i></button>
										</span> -->
									</div>
									<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>
								</div>									
								<div class="col-md-3">
									<label>Subcategory<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="subcategoryid" name="subcategoryid" ng-model="subcategoryid" ng-options="item.realestateSubcategoryId as item.subcategoryTitle for item in getSubcategoryTitles" class="form-control" ng-change="onChangeRealestateTypeedit() || setFlag()" ng-disabled="true">
											<option value="">Subcategory</option>
											<!-- <option ng-repeat="items in getSubcategoryTitles" value="{{items.realestateSubcategoryId}}">{{items.subcategoryTitle}}</option> -->
										</select>
										<!-- <span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateSubModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
										</span> -->
									</div>
									<p ng-show="errorRealestateSubcategoryTitle == 1" style="color: red;">{{msgRealestateSubType}}</p>
								</div>	
								<div class="col-md-3">
									<label>Type<font color="red" size="3">*</font></label>
									<div class="form-group">
										<select id="realestateid" name="realestateid" ng-model="realestateid" ng-options="item.realestateId as item.realestateTitle for item in getRealestateTitles" class="form-control" ng-change="setFlag()" ng-disabled="true">
											<option value="">Type</option>											
										</select>
										<!-- <span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateTypeModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
										</span> -->
									</div>
									<p ng-show="errorRealestateTypeTitle == 1" style="color: red;">{{msgRealestateTypeTitle}}</p>
								</div>																									
							</div>
							<div class="row">							
								<div class="col-md-3">
									<label>Unit Name</label>
									<div class="form-group">
										<select id="projectunitid" name="projectunitid" ng-model="projectunitid" class="form-control">
											<option value="">Unit Name</option>
											<option ng-repeat="item in unitNameList" value="{{item.unitMasterId}}">{{item.unitName}}</option>
										</select>											
									</div>										
								</div>								
								<div class="col-md-2">
									<div class="form-group">
										<label>Floor</label>
										<select class="form-control" id="flooredit" name="flooredit" ng-model="flooredit">
											<option value=""> Floor </option>
											<option ng-repeat="item in floor" value="{{item}}">{{item}}</option>									
										</select>											
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Property Number<font color="red" size="3">*</font></label>
										<input type="text" id="propertytitleedit" name="propertytitleedit" ng-model="propertytitleedit" placeholder="Property Number" class="form-control" ng-change="setFlag()">
										<p ng-show="errorUnits == 1" style="color: red;">{{msgUnits}}</p>											
									</div>
								</div>
								<div class="col-md-3">
									<label>Furnishing</label>
									<div class="form-group">											
										<select id="furnishingedit" name="furnishingedit" ng-model="furnishingedit" class="form-control" ng-change="setFlag()">
											<option value="">Furnishing</option>
											<option value="Furnished">Furnished</option>
											<option value="Un-Furnished">Un-Furnished</option>
											<option value="Semi Furnished">Semi Furnished</option>																						
										</select>																					
									</div>
								</div>
							</div>								
							<div class="row">																			
								<!-- <div class="col-md-3">
									<label>Water Source</label>
									<div class="form-group">											
										<select id="watersourceedit" name="watersourceedit" ng-model="watersourceedit" class="form-control" ng-change="setFlag()">
											<option value="">Water Source</option>
											<option value="Municipal">Municipal</option>
											<option value="Borewell">Borewell</option>
											<option value="Well">Well</option>																						
										</select>																					
									</div>
								</div>									
								<div class="col-md-3">
									<label>Drainage</label>
									<div class="form-group">											
										<select id="drainageedit" name="drainageedit" ng-model="drainageedit" class="form-control" ng-change="setFlag()">
											<option value="">Drainage</option>
											<option value="Private">Private</option>
											<option value="Municipal">Municipal</option>																																		
										</select>																					
									</div>
								</div> -->								
								<div class="col-md-3">
									<label>Reserved Parking</label>
									<div class="form-group">											
										<select id="reservedparkingedit" name="reservedparkingedit" ng-model="reservedparkingedit" class="form-control" ng-change="setFlag()">
											<option value="">Reserved Parking</option>
											<option value="Y">Yes</option>
											<option value="N">No</option>																																		
										</select>																					
									</div>
								</div>
								<div class="col-md-3">
									<label>Property Availability</label>
									<div class="form-group">											
										<select id="propertyavailabilityedit" name="propertyavailabilityedit" ng-model="propertyavailabilityedit" class="form-control" ng-change="setFlag()">
											<option value="">Property Availability</option>
											<option value="Ready Possession"> Ready Possession</option>
											<option value="Under Construction">Under Construction</option>																																		
										</select>																					
									</div>
								</div>									
								<div class="col-md-6">
									<div class="form-group">
										<label>Property Availability Description</label>
										<textarea rows="1" id="propertyavailabilitydes" name="propertyavailabilitydes" ng-model="propertyavailabilitydes" placeholder="Property Availability Description..." class="form-control"></textarea>
									</div>
								</div>
								<!-- <div class="col-md-3">
									<label>Maintenance Charges</label>
									<div class="form-group">											
										<select id="maintenancechargesedit" name="maintenancechargesedit" ng-model="maintenancechargesedit" class="form-control" ng-change="setFlag()">
											<option value="">Maintenance Charges</option>
											<option value="Lifetime Charge">Lifetime Charge</option>
											<option value="Monthly Charge">Monthly Charge</option>																																		
										</select>																				
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Maintenance Amount</label>
										<input type="text" id="maintenanceamountedit" name="maintenanceamountedit" ng-model="maintenanceamountedit" placeholder="Maintenance Amount" class="form-control" ng-change="setFlag()">																						
									</div>
								</div> -->							
							</div>
							<!-- <div class="row">																			
								<div class="col-md-3">
									<label>Property Availability</label>
									<div class="form-group">											
										<select id="propertyavailabilityedit" name="propertyavailabilityedit" ng-model="propertyavailabilityedit" class="form-control" ng-change="setFlag()">
											<option value="">Property Availability</option>
											<option value="Ready Possession"> Ready Possession</option>
											<option value="Under Construction">Under Construction</option>																																		
										</select>																					
									</div>
								</div>									
								<div class="col-md-9">
									<div class="form-group">
										<label>Property Availability Description</label>
										<textarea rows="1" id="propertyavailabilitydes" name="propertyavailabilitydes" ng-model="propertyavailabilitydes" placeholder="Property Availability Description..." class="form-control"></textarea>
									</div>
								</div>
							</div> -->							
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Property Description</label>
										<textarea rows="4" id="propertydescriptionedit" name="propertydescriptionedit" ng-model="propertydescriptionedit" placeholder="Description..." class="form-control"></textarea>
									</div>
								</div>
							</div>
							<div class="row">																		
								<div class="col-md-12">
									<div class="panel-group">
										<div class="panel panel-default">
											<div class="panel-heading">
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Area</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="areatypeidedit" name="areatypeidedit" ng-model="areatypeidedit" class="form-control" ng-change="setFlag()">
																<option value="">Area Title</option>
																<option ng-repeat="items in getAreaName" value="{{items.areaTypeId}}">{{items.areaTypeTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#areaTypeModal" title="Add New Area"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorArea == 1" style="color: red;">{{msgArea}}</p>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="areavalueedit" name="areavalueedit" ng-model="areavalueedit" placeholder="Value" class="form-control" ng-change="setFlag()">
															<p ng-show="errorAreaV == 1" style="color: red;">{{msgAreaV}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="areaunittypeedit" name="areaunittypeedit" ng-model="areaunittypeedit" class="form-control" ng-change="setFlag()">
																<option value="">Unit Type</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorAreaU == 1" style="color: red;">{{msgAreaU}}</p>																										
														</div>									
													</div>													
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editAreaDetail(propertyid)" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Title </th>																	
																<th> Value </th>
																<th> Unit Type </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in propertyArea">
																<td> {{item.areaTypeTitle}} </td>
																<td> {{item.areaValue}} </td>																	
																<td> {{item.unitTitle}} </td>														
																<td>
																	<a href="#" ng-click="removeAreaDetails(item.propertyAreaId,propertyid)" class="delete" data-toggle="modal">
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
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Price</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<!-- <div class="col-md-3">															
														<div class="input-group">
															<select id="areatypeidadd" name="areatypeidadd" ng-model="areatypeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Area Title</option>
																<option ng-repeat="items in getAreaName" value="{{items.areaTypeId}}">{{items.areaTypeTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#priceTypeModal" title="Add New Price Label"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorArea == 1" style="color: red;">{{msgArea}}</p>
													</div> -->
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="pricelableedit" name="pricelableedit" ng-model="pricelableedit" placeholder="Lable" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPriceL == 1" style="color: red;">{{msgPriceL}}</p>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="pricevalueedit" name="pricevalueedit" ng-model="pricevalueedit" placeholder="Value" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPriceV == 1" style="color: red;">{{msgPriceV}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="areaunittypeedit" name="areaunittypeedit" ng-model="areaunittypeedit" class="form-control" ng-change="setFlag()">
																<option value="">Unit Type</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorPriceU == 1" style="color: red;">{{msgPriceU}}</p>																										
														</div>									
													</div>														
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editPropertyPrice(propertyid)" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Label </th>																	
																<th class="text-right"> Amount </th>
																<th> Unit Name </th>																						
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in propertyPriceInfo">
																<td> {{item.propertyPriceLable}} </td>
																<td class="text-right"> {{item.propertyPriceValue}}</td>
																<td> {{item.unitTitle}} </td>																																	
																<td>
																	<a href="#" ng-click="removePropertyPriceDetails(item.propertyPriceInfoId, propertyid)" class="delete" data-toggle="modal">
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
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Room</h4>
											</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-3">															
														<div class="input-group">
															<select id="roomtypeidadd" name="roomtypeidadd" ng-model="roomtypeidadd" class="form-control" ng-change="setFlag()">
																<option value="">Room Title</option>
																<option ng-repeat="items in getRoomName" value="{{items.roomTitleId}}">{{items.roomTitle}}</option>
															</select>
															<span class="input-group-btn">
																<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#roomTypeModal" title="Add New Area"><i class="fa fa-plus"></i></button>
															</span>
														</div>
														<p ng-show="errorRoomTitle == 1" style="color: red;">{{msgTitle}}</p>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="roomlengthadd" name="roomlengthadd" ng-model="roomlengthadd" placeholder="Room Length" class="form-control" ng-change="setFlag()">
														</div>
														<p ng-show="errorRoomLength == 1" style="color: red;">{{msgLength}}</p>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="roomlengthunit" name="roomlengthunit" ng-model="roomlengthunit" class="form-control" ng-change="setFlag()">
																<option value="">Unit Type</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>	
															<p ng-show="errorRoomLunit == 1" style="color: red;">{{msgLunit}}</p>																									
														</div>									
													</div>													
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="roombreadthadd" name="roombreadthadd" ng-model="roombreadthadd" placeholder="Room Breadth" class="form-control" ng-change="setFlag()">
															<p ng-show="errorRoomBreadth == 1" style="color: red;">{{msgBreadth}}</p>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3">
														<div class="form-group">																
															<select id="roombreadthunit" name="roombreadthunit" ng-model="roombreadthunit" class="form-control" ng-change="setFlag()">
																<option value="">Unit Type</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorRoomBunit == 1" style="color: red;">{{msgBunit}}</p>																										
														</div>									
													</div>
													
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="roomheightadd" name="roomheightadd" ng-model="roomheightadd" placeholder="Room Height" class="form-control" ng-change="setFlag()">
															<p ng-show="errorRoomHeight == 1" style="color: red;">{{msgHeight}}</p>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<select id="roomheightunit" name="roomheightunit" ng-model="roomheightunit" class="form-control" ng-change="setFlag()">
																<option value="">Unit Type</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>	
															<p ng-show="errorRoomHunit == 1" style="color: red;">{{msgHunit}}</p>																									
														</div>									
													</div>														
													<div class="col-md-3">
														<div class="form-group">
															<button ng-disabled="roomspin == 1" ng-click="editRoomDetail(propertyid)" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="roomspin == 1"></i><span  ng-if="roomspin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Title </th>																	
																<th> Length ( Unit) </th>
																<th> Breadth ( Unit) </th>
																<th> Height ( Unit) </th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in propertyRoom">
																<td> {{item.roomTitle}} </td>
																<td> {{item.roomLength}}({{item.lengthUnit}}) </td>
																<td> {{item.roomBreadth}}({{item.breadthUnit}}) </td>
																<td ng-if="item.roomHeight ==0"> -(-) </td>
																<td ng-if="item.roomHeight !=0"> {{item.roomHeight}}({{item.heightUnit}}) </td>																	
																<td>
																	<a href="#" ng-click="removeRoomDetails(item.propertyRoomId,propertyid)" class="delete" data-toggle="modal">
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
												 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Payment Schedule</h4>
											</div>
											<div class="panel-body">
												<div class="row ">														
													<div class="col-md-3">
														<div class="form-group">
															<input type="number" id="sequenceedit" name="sequenceedit" ng-model="sequenceedit" placeholder="Sequence *" class="form-control" ng-change="setFlag()">
															<p ng-show="errorSequence == 1" style="color: red;">{{msgSequence}}</p>
														</div>															
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<input type="text" id="sequencetitleedit" name="sequencetitleedit" ng-model="sequencetitleedit" placeholder="Sequence Title" class="form-control" ng-change="setFlag()">
															<p ng-show="errorSequenceTitle == 1" style="color: red;">{{msgTitle}}</p>
														</div>															
													</div>
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
																<option value="">Unit Type *</option>
																<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
															</select>
															<p ng-show="errorUnit == 1" style="color: red;">{{msgUnittype}}</p>																										
														</div>									
													</div>																											
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="projectspin == 1" ng-click="editPropertyPaymentSchedule(propertyid)" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="projectspin == 1"></i><span  ng-if="projectspin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th>Seq </th>
																<th>Seq Title</th>																	
																<th>Lable</th>																	
																<th>Value</th>
																<th>Unit Type</th>
																<th>Action</th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in propertyPaymentSchedule">
																<td> {{item.sequence}} </td>
																<td> {{item.sequenceTitle}} </td>																	
																<td> {{item.paymentLable}} </td>
																<td> {{item.paymentValue}} </td>
																<td> {{item.unitName}} </td>
																<td>
																	<a href="#" ng-click="removePropertyPaymentSchedule(item.propertyPaymentScheduleId, propertyid)" class="delete" data-toggle="modal">
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
									<strong ng-show="propertySubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="propertySubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-click="editProperty(propertyid)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
								</div>
							</div>			
						</div>
					</form>
				</div>
			</div>
		</div>	
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Project </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteProperty()">
					</div>
				</div>
			</div>
		</div>	
		<div class="modal fade" id="realestateModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Realestate Category Type</h4>
					</div>
					<form ng-submit="addRealestateType()" id="realestatecategory">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Realestate Title<font color="red" size="3">*</font></label>
										<input type="text" id="realestatetitleadd" name="realestatetitleadd" ng-model="realestatetitleadd" placeholder="Realestate Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-6">
									<div class="form-group">
										<label>Realestate Code</label>
										<input type="text" id="realestatecodeadd" name="realestatecodeadd" ng-model="realestatecodeadd" placeholder="Real Code" maxlength="3" capitalize class="form-control">
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
					<form ng-submit="addRealestateSubcategory()" id="realestateSubcategory">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Realestate Type <font color="red" size="3">*</font></label>
										<select id="realestatetypeidadd" name="realestatetypeidadd" ng-model="realeidadd" class="form-control" ng-change="setFlag()">
											<option value="">Realestate Type</option>
											<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>											
										</select>
										<p ng-show="errorRealSubcategoryTitle == 1" style="color: red; font-size: 14px;">{{msgSubcategoryType}}</p>									
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
										<label>Code</label>
										<input type="text" id="realestatecodeadd" name="realestatecodeadd" ng-model="realestatecodeadd" placeholder="Real Code" maxlength="3" capitalize class="form-control">
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
					<form ng-submit="addRealestate()" id="realestatetype">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<label>Category of Realestate  <font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="realeidadd" name="realeidadd" ng-model="realeidadd" class="form-control" ng-change="onChangeRealestate() || setFlag()">
											<option value="">Category of Realestate</option>
											<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateModal" title="Add New Location"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorRealCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>
								</div>									
								<div class="col-md-3">
									<label>Subcategory of Realestate <font color="red" size="3">*</font></label>
									<div class="input-group">
										<select id="realsubidadd" name="realsubidadd" ng-model="realsubidadd" class="form-control" ng-change="setFlag()">
											<option value="">Subcategory of Realestate</option>
											<option ng-repeat="items in getSubcategoryTitles" value="{{items.realestateSubcategoryId}}">{{items.subcategoryTitle}}</option>
										</select>
										<span class="input-group-btn">
											<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#realestateSubModal" title="Add New Realestate"><i class="fa fa-plus"></i></button>
										</span>
									</div>
									<p ng-show="errorRealSubcategoryTitle == 1" style="color: red;">{{msgSubcategoryType}}</p>
								</div>									
								<div class="col-md-3">
									<div class="form-group">
										<label>Realestate Title<font color="red" size="3">*</font></label>
										<input type="text" id="realestatetitleadd" name="realestatetitleadd" ng-model="realestatetitleadd" placeholder="Realestate Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorRealSubTitle == 1" style="color: red; font-size: 14px;">{{msgSubcategoryTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-3">
									<div class="form-group">
										<label>Code</label>
										<input type="text" id="realestatecodeadd" name="realestatecodeadd" ng-model="realestatecodeadd" placeholder="Real Code" maxlength="3" capitalize class="form-control">
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
		<div class="modal fade" id="areaTypeModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Area Title Type</h4>
					</div>
					<form ng-submit="addAreaTitle()" id="areatitle">
						<div class="modal-body">
							<div class="row">															
								<div class="col-md-8">
									<div class="form-group">
										<label>Area Title<font color="red" size="3">*</font></label>
										<input type="text" id="areatitleadd" name="areatitleadd" ng-model="areatitleadd" placeholder="Area Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>
									</div>									
								</div>							
							</div>						
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="areaSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="areaSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="Spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="Spin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>	
		<div class="modal fade" id="priceTypeModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Payment Title Type</h4>
					</div>
					<form ng-submit="addPriceTitle()" id="pricetitle">
						<div class="modal-body">
							<div class="row">															
								<div class="col-md-8">
									<div class="form-group">
										<label>Price Label<font color="red" size="3">*</font></label>
										<input type="text" id="labeladd" name="labeladd" ng-model="labeladd" placeholder="Price Label" class="form-control" ng-change="setFlag()">
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>
									</div>									
								</div>							
							</div>						
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="areaSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="areaSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="Spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="Spin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="roomTypeModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Room Title Type</h4>
					</div>
					<form ng-submit="addRoomTitle()" id="roomtitle">
						<div class="modal-body">
							<div class="row">															
								<div class="col-md-8">
									<div class="form-group">
										<label>Room Title<font color="red" size="3">*</font></label>
										<input type="text" id="roomtitleadd" name="roomtitleadd" ng-model="roomtitleadd" placeholder="Room Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorRoomTitle == 1" style="color: red; font-size: 14px;">{{msgRoomTitle}}</p>
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
									<strong ng-show="roomSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="roomSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="Spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="Spin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="towerNameModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add Unit Title</h4>
					</div>
					<form ng-submit="addUnitTitle()" id="unittitle">
						<div class="modal-body">
							<div class="row">															
								<div class="col-md-8">
									<div class="form-group">
										<label>Unit Title<font color="red" size="3">*</font></label>
										<input type="text" id="unittitleadd" name="unittitleadd" ng-model="unittitleadd" placeholder="Unit Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorUnitTitle == 1" style="color: red; font-size: 14px;">{{msgUnitTitle}}</p>
									</div>									
								</div>							
							</div>						
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="unitSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="unitSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">
									<button type="submit" ng-disabled="unitSpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="unitSpin == 1"></i> Save changes</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="deletetowerNameModal">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Delete Tower Name</h4>
					</div>
					<form id="deleteunittitle">
						<div class="modal-body">
							<div class="row">															
								<div class="table-responsive table-bordered">
									<table class="table">
										<thead>
											<tr>
												<th> Title </th>																										
												<th> Action </th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="item in getTowerName">
												<td> {{item.towerTitle}} </td>
												<td>
													<a href="#" ng-click="removeTowerName(item.towerNameId)" class="delete" data-toggle="modal">
														<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
													</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>						
							</div>						
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-6 text-left">
									<strong ng-show="unitSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="unitSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-6 text-right">									
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<script>		
		    /* $(function () {
		        $('#projectid').multiselect({
		            includeSelectAllOption: true
		        });
		    }); */
		    /* $(function () {
		        $('#projectid').multiselect({
		            width: 200,
		            nonSelectedText: 'Select Project',
		            includeSelectAllOption: true,
		            nSelectedText: 'Projects Selected',
		        });
		    }); */
     
			$(function () {
				$(".select2").select2();
			});
			document.getElementById("manage").classList.add("active");
			document.getElementById("property").classList.add("active");
		</script>		
				
	</body>
</html>