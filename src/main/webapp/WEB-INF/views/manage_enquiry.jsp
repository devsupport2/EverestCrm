<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Enquiry </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
			
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular-filter.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/enquiry.js"></script>	
		<script	src="<%=request.getContextPath()%>/resources/admin/js/FileSaver.js"	type="text/javascript"></script>	
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		<script>
			var d = new Date();
		</script>
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
	<%-- <body ng-app="MyApp" ng-controller="enquiryCtrl" ng-cloak class="skin-blue sidebar-collapse sidebar-mini" ng-init="getAllEnquiryDetailsByName('<%= request.getParameter("projectname") %>')"> --%>
	<body ng-app="MyApp" ng-controller="enquiryCtrl" ng-cloak class="skin-blue sidebar-collapse sidebar-mini" ng-init="getAllEnquiryListByName('<%= request.getParameter("projectid") %>', '<%= session.getAttribute("usertypeidadmin") %>')">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Enquiry 
					</h1>					
					<ol class="breadcrumb">
				
						<li><button class="btn btn-success" data-toggle="modal" data-target="#todayFollowupModal">Today's Follow-up ({{currentdate | date:'dd-MM-yyyy'}})</button></li>
						<li><button class="btn btn-success" data-toggle="modal" data-target="#comingFollowupModal">Coming up Follow-ups</button></li>						
						<li><button class="btn btn-success" data-toggle="modal" data-target="#importModal">Import Enquiry</button></li>
						
						<li><button class="btn btn-success" ng-click="exportData()" ng-show="UserAdminCheck">Export Enquiry</button></li>
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Enquiry</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Enquiry</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<form>						
							<div class="box-body">
								<div class="row">
									<div class="col-md-2">
										<label>Enquiry Date<font color="red" size="3">*</font></label>
										<div class="input-group">
											<input class="KendoDate" id="datepicker" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;" ng-change="setFlag()"/>
											<p ng-show="errorEnquiryDate == 1" style="color: red;">{{msgEnquiryDate}}</p>
										</div>
									</div>
									<div class="col-md-2">
										<label>Enquiry Via<font color="red" size="3">*</font></label>										
										<div class="form-group">
											<select id="enquiryviaadd" name="enquiryviaadd" ng-model="enquiryviaadd" class="form-control" ng-change="setFlag()">
												<option value="">Enquiry Via</option>
												<option value="Call">Call</option>
												<option value="Import">Import From File</option>
												<option value="Personal Meeting">Personal Meeting</option>
												<option value="Reference">Reference</option>
												<option value="Walk-In">Walk-In</option>
												<option value="Web">Web</option>												
											</select>
											<p ng-show="errorEnquiryVia == 1" style="color: red;">{{msgEnquiryVia}}</p>																	
										</div>
									</div>
									<div class="col-md-2">
										<label>Reference Type</label>
										<div class="input-group">
											<select id="usertypeidadd" name="usertypeidadd" ng-model="usertypeidadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="onChangeUSerType() || setFlag()" class="form-control">
												<option value="">Reference Type</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New Client"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>								
									<div class="col-md-3">
										<label>Reference By</label>
										<div class="input-group">
											<select id="referenceidadd" name="referenceidadd" ng-model="referenceidadd" class="select2" style="width: 100%;">
												<option value="">Reference By</option>
												<option ng-repeat="item in getReferenceNames" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Client"><i class="fa fa-plus"></i></button>
											</span>
										</div>
									</div>
									<div class="col-md-3">
										<label>Client<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="clientidadd" name="clientidadd" ng-model="clientidadd" ng-change="setFlag()" class="select2" style="width: 100%;">
												<option value="">Client</option>
												<option ng-repeat="item in getClientNames" value="{{item.userId}}">{{item.firstName}} {{item.lastName}} -{{item.mobileNumber}} - {{item.userCompanyName}}</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Client"><i class="fa fa-plus"></i></button>
											</span>											
										</div>
										<p ng-show="errorEnquiryClient == 1" style="color: red;">{{msgEnquiryClient}}</p>
									</div>																							
								</div>
								<div class="row form-group">
									<div class="col-md-3">
										<label>Occupation</label>
										<div class="input-group">
											<select id="occupationidadd" name="occupationidadd" ng-model="occupationidadd" class="form-control" ng-change="setFlag()">
												<option value="">Occupation</option>
												<option ng-repeat="item in alloccupation" value="{{item.occupationId}}">{{item.occupationName}}</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#occupationModal" title="Add New Occupation"><i class="fa fa-plus"></i></button>
											</span>
										</div>
										<p ng-show="errorEnquiryClient == 1" style="color: red;">{{msgEnquiryClient}}</p>
									</div>
									<div class="col-md-3">
										<label>Designation</label>
										<div class="input-group">
											<select id="designationidadd" name="designationidadd" ng-model="designationidadd" class="form-control" ng-change="setFlag()">
												<option value="">Designation</option>
												<option ng-repeat="item in alldesignation" value="{{item.designationId}}">{{item.designationName}}</option>
											</select>
											<span class="input-group-btn">
												<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#designationModal" title="Add New Designation"><i class="fa fa-plus"></i></button>
											</span>
										</div>
										<p ng-show="errorEnquiryClient == 1" style="color: red;">{{msgEnquiryClient}}</p>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label></label>
										</div>
									</div>
									<div class="col-md-3"><br>
										<div class="form-group text-right">
											<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseEnquiry" aria-expanded="false" aria-controls="collapseExample">More Details</button>
										</div>
									</div>
								</div>
								<div class="row">	
									<div class="collapse" id="collapseEnquiry">
										<div class="row form-group" style="margin-right: 0;margin-left: 0;">
											<div class="col-md-3">
												<div class="form-group">
													<label>Choose Option</label>
													<div class="form-group">
														<select id="chooseoptionadd" name="chooseoptionadd" ng-model="chooseoptionadd" class="form-control">
															<option value="">Choose Option</option>
															<option value="For Own Use">For Own Use</option>
															<option value="For Investment">For Investment</option>
															<option value="For Relative or Parent">For Relative or Parent</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>What's your budget?</label>
													<div class="form-group">
														<select id="budgetadd" name="budgetadd" ng-model="budgetadd" class="form-control">
															<option value="">Select Budget</option>
															<option ng-repeat="r in allrange" value="{{r.rangeId}}">{{r.rangeFrom}} - {{r.rangeTo}} {{r.unitName}}</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<label> Which features are most important to you and your family for perfect home? </label>
													<textarea rows="4" name="featuresadd" id="featuresadd" ng-model="featuresadd" class="form-control" placeholder="Remark*..."></textarea>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Are you considering any other project?</label>
													<div class="form-group">
														<select id="consideringprojectadd" name="consideringprojectadd" ng-model="consideringprojectadd" class="form-control">
															<option value="">Select</option>
															<option value="yes">Yes</option>
															<option value="no">No</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>By what time are you expecting to finalized?</label>
													<div class="form-group">
														<select id="expectingtimeadd" name="expectingtimeadd" ng-model="expectingtimeadd" class="form-control">
															<option value="">Select</option>
															<option value="1">15 Days</option>
															<option value="2">15 - 45 Days</option>
															<option value="3">more then 3 months</option>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<div class="panel-group">
											<div class="panel panel-default">
												<div class="panel-heading">
													 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign to</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-10">
															<div class="input-group">
																<select id="employeeidadd" name="employeeidadd" ng-model="employeeidadd" class="form-control" ng-change="setFlag()">
																	<option value="">Employee</option>
																	<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																</select>
																<span class="input-group-btn">
																	<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Employee"><i class="fa fa-plus"></i></button>
																</span>
															</div>
															<p ng-show="errorEmpName == 1" style="color: red;">{{msgEmpName}}</p>
														</div>	
														<div class="col-md-2">
															<div class="form-group">
																<a href="#" ng-click="addAssignRow()" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus"></span></a>
															</div>
														</div>								
													</div>													
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Employee Name </th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in assignlist">
																	<td> {{item.firstName}} {{item.lastName}} </td>																															
																	<td>
																		<a href="#" ng-click="removeAssignRow(item)" class="delete" data-toggle="modal">
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
													 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property Enquiry</h4>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">
																<select id="realeidadd" name="realeidadd" ng-model="realeidadd" class="form-control" ng-change="onChangeCategory(realeidadd) || setFlag()">
																	<option value="">Category</option>
																	<option ng-repeat="item in getAllCategory" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
																</select>
																<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>																
															</div>															
														</div>									
														<div class="col-md-3">
															<div class="form-group">
																<select id="realsubidadd" name="realsubidadd" ng-model="realsubidadd" class="form-control">
																	<option value="">Subcategory</option>
																	<option ng-repeat="item2 in projectSubcategory" value="{{item2.realestateSubcategoryId}}">{{item2.subcategoryTitle}}</option>
																</select>
																<p ng-show="errorRealestateSubcategoryTitle == 1" style="color: red;">{{msgRealestateSubType}}</p>																
															</div>															
														</div>	
														<div class="col-md-3">
															<div class="form-group">
																<select id="realtypeidadd" name="realtypeidadd" ng-model="realtypeidadd" class="form-control" ng-change="setFlag()">
																	<option value="">Type</option>
																	<option ng-repeat="items in getRealestateType" value="{{items.realestateId}}">{{items.realestateTitle}}</option>
																</select>
																<p ng-show="errorRealestateTypeTitle == 1" style="color: red;">{{msgRealestateTypeTitle}}</p>
															</div>															
														</div>
														<div class="col-md-3">															
															<div class="form-group">
																<select name="unitmasteridadd" id="unitmasteridadd" ng-model="unitmasteridadd" class="form-control">
																	<option value="">Unit Name</option>
																	<option ng-repeat="items in projectUnitNameList" value="{{items.unitMasterId}}">{{items.unitName}}</option>
																</select>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">																
																<select id="projectidadd" name="projectidadd" ng-model="projectidadd" class="form-control" ng-change="onChangeProject(projectidadd, realeidadd, realsubidadd) || setFlag()">
																	<option value="">Project</option>
																	<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
																</select>
																<p ng-show="errorProjectType == 1" style="color: red;">{{msgProjectType}}</p>										
															</div>									
														</div>
														<div class="col-md-3">															
															<div class="form-group">
																<select id="propertyidadd" name="propertyidadd" ng-model="propertyidadd" class="form-control" ng-change="setFlag()">
																	<option value="">Property Number</option>
																	<option ng-repeat="item in propertyNumberList" value="{{item.propertyId}}">{{item.propertyTitle}}</option>
																</select>
																<p ng-show="errorPropertyNumber == 1" style="color: red;">{{msgPropertyNumber}}</p>																
															</div>															
														</div>
														<div class="col-md-1">
															<div class="form-group">
																<button ng-disabled="propertyspin == 1" ng-click="addPropertyForEnquiryRow()" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="propertyspin == 1"></i><span  ng-if="propertyspin != 1" class="fa fa-plus"></span></button>
															</div>
														</div>																																													
													</div>																									
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Category </th>
																	<th> Sub-category </th>
																	<th> Type </th>
																	<th> Unit Name</th>
																	<th> Project </th>
																	<th> Property No.</th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in propertyforenquirylist">
																	<td> {{item.title}} </td>																	
																	<td> {{item.subcategTitle}} </td>
																	<td> {{item.realstatetitle}} </td>
																	<td> {{item.unitName}} </td>
																	<td> {{item.projectName}} </td>																	
																	<td> {{item.propertyTitle}} </td>														
																	<td>
																		<a href="#" ng-click="removePropertyForEnquiryRow(item)" class="delete" data-toggle="modal">
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
										<div class="form-group">
											<label>Remarks</label>
											<textarea rows="4" id="remarksadd" name="remarksadd" ng-model="remarksadd" placeholder="Remarks" class="form-control"></textarea>										
										</div>
									</div>								
								</div>
							</div>
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="success == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{message}}</strong>						
										<strong ng-show="info == 1" style="color: red;"><span class="fa fa-info-circle"></span> {{message}}</strong>									
									</div>
									<div class="col-md-4 text-right">
										<button type="submit" ng-click="addEnquiry()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="row">
						<div class="col-md-8 text-right">
							<div class="row">
								<div class="col-md-6 text-left">
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getEnquiries.length+startindex}}</b> out of <b>{{allcounts.enquiryCount}}</b> entries</div>
								</div>
								<div class="col-md-6 text-right">
									<div class="hint-text"><i class="fa fa-square" aria-hidden="true" style="color: #202020"></i> - New &nbsp; &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: blue"></i> - Working &nbsp; &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: green"></i> - Won &nbsp; &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: #f39c12"></i> - Hold &nbsp; &nbsp; <i class="fa fa-square" aria-hidden="true" style="color: red"></i> - Lost </div>
								</div>
							</div>							
							<div class="box box-tgsc" id="admin-table" name="admin-table">
								<div class="box-header with-border" style="width: 130%;">
									<div class="row">
										
										<div class="col-md-2 text-left"">
											<div class="form-group">
												<input id="startdate" class="KendoDate" title="Select From Date"/>
											</div>
										</div>
										<div class="col-md-2 text-left"">
											<div class="form-group">																						
												<input id="enddate" class="KendoDate" title="Select To Date"/>
											</div>
										</div>
										<div class="col-md-3">											
											<div class="input-group">
												<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchEnquiry() : null"/>
												<span class="input-group-btn">
													<button type="button" name="search" id="search-btn" ng-click="searchEnquiry('<%= request.getParameter("projectid") %>')" class="btn btn-flat"><i class="fa fa-search"></i></button>
												</span>
											</div>
										</div>	
										<div class="col-md-1 text-right">
											<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage('<%= request.getParameter("projectid") %>')" style="width: auto; display: inline;"></select>
										</div>									
										<div class="col-md-1">											
											<div class="box-tools pull-right">
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
										</div>
									</div>
								</div>
								<div class="box-header with-border">
									<div class="row">										
										<div class="col-md-2">
											<div class="form-group">
												<select ng-model="inquirystatus" title="Filter by status" class="form-control">
													<option value="">Open</option>
													<option value="y">New</option>
													<option value="l">Lost</option>
													<option value="o">Working</option>
													<option value="h">Hold</option>
													<option value="w">Won</option>
													<option value="c">Close</option>
													<option value="a">All</option>													
												</select>												
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<select ng-model="country" title="Filter by country" class="form-control">
													<option value="">Country</option>
													<option ng-repeat="item in getCountries" value="{{item.countryName}}">{{item.countryName}}</option>
												</select>												
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<%-- <select ng-model="project" id="project" title="Filter by project" class="form-control" ng-init="project = '<%= request.getParameter("projectname") %>'; changeProjectName('<%= request.getParameter("projectname") %>')"> --%>
												<select ng-model="project" id="project" title="Filter by project" class="form-control" ng-change="getAllEnquiryListByName(project)">
													<option value="">Project</option>
													<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>													
												</select>
											</div>
										</div>										
										<div class="col-md-2">
											<div class="form-group">
												<select id="usertypeid" name="usertypeid" ng-model="usertypeid" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="getUserByUserType(usertypeid)" class="form-control">
													<option value="">Reference Type</option>
												</select>												
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<select ng-model="referenceby" title="Filter by reference by" class="form-control">
													<option value="">Reference By</option>
													<option ng-repeat="item in getUserNameByUserType" value="{{item.firstName}} {{item.lastName}}">{{item.userCompanyName}} - {{item.firstName}} {{item.lastName}}</option>
												</select>												
											</div>
										</div>										
										<div class="col-md-2">
											<div class="form-group">
												<select ng-model="subcategory" title="Filter by subcategory" class="form-control">
													<option value="">Subcategory</option>
													<option ng-repeat="item2 in getSubcategory" value="{{item2.subcategoryTitle}}">{{item2.subcategoryTitle}}</option>
												</select>												
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<select ng-model="realestate" title="Filter by type" class="form-control">
													<option value="">Type</option>
													<option ng-repeat="items in getRealestateType" value="{{items.realestateTitle}}">{{items.realestateTitle}}</option>
												</select>												
											</div>
										</div>
										
										<div class="col-md-1 text-left"">
											<div class="form-group">													
												<a href="#" ng-click="getEnquiryListByDate()" class="btn btn-success">
													<i class="fa fa-ok" aria-hidden="true" ng-if="spin == 0"></i>
													<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Go
												</a>
											</div>
										</div>
										
										
										<div class="col-md-12 text-left">
											<div class="form-group">
												<label>Filtered With </label>:<span> {{country}} <span ng-if="country">,</span> {{project}} <span ng-if="project">,</span> {{referenceby}} <span ng-if="referenceby">,</span> {{subcategory}} <span ng-if="subcategory">,</span> {{realestate}} <span ng-if="realestate">,</span></span>											
											</div>
										</div>
									</div>																			
								</div>
								<div class="box-body">
									<div class="table-responsive" id="exportable">
										<table class="table no-margin">
											<thead>
												<tr>
													<th class="text-left" >#</th>
													<th class="text-left">Client</th>
													<th class="text-left">Email</th>
													<th class="text-left"> Property type</th>
													<!-- <th class="text-left">Reference By</th> -->
													<!--  <th class="text-left">Property</th>  -->
													<th class="text-right"><a href="#deleteModal" ng-show="UserAdminCheck"  data-toggle="modal" title="Delete" style="color: red;"><i class="fa fa-trash-o" aria-hidden="true" ng-click="checkRecordSelectForDelete()"></i></a></th>				
												</tr>
											</thead>
											<tbody>
												<tr class="text-center" ng-if="getEnquiries == ''">
													<td colspan="8"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
												</tr>
												<tr class="text-center" ng-if="enquiryLoader">
													<td colspan="7" style="font-style: italic;">Loading data... Please Wait...<i class="fa fa-spinner fa-pulse"></i></td>
												</tr>
												<tr ng-repeat="item in (filterResult = (getEnquiries | filter:country | filter:{status:inquirystatus} | filter:project | filter:subcategory | filter:realestate | filter:referenceby))" ng-show="!enquiryLoader" style="cursor:pointer; cursor:hand;" ng-style="(item.status == 'w'  || item.status == 'cw') && {'color':'green'} || item.status == 'h' && {'color':'#f39c12'} || (item.status == 'l' || item.status == 'cl') && {'color':'red'} || item.status == 'o' && {'color':'blue'} || {'color': '#202020'}">
													<td ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal" class="text-left">{{item.enquiryNo}}<br>{{item.enquiryDate}}</td>												
													<td ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal"  class="text-left">{{item.firstName}} {{item.lastName}}   	<span ng-if="country.length!=0"></span>, {{item.countryName}}</span><br>{{item.userCompanyName}}</td>
													<td ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal"  class="text-left">{{item.email}}<br>{{item.mobileNumber}}</td>
													<td ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal"  class="text-left"><span ng-repeat="items in item.enquiryPropertyList">{{items.categoryTitle}},{{items.subcategoryTitle}}<br>{{items.unitName}}<span ng-if="items.projectTitle">,{{items.projectTitle}}</span></span></td>
													<!-- <td ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" class="text-left">{{item.referenceBy}}</td> -->
													 <!-- <td ng-click="getEnquiryDetail(item.enquiryId)" ng-repeat="getEnquiryPropertyByEnquiryId(item.enquiryId)"title="Edit Record" data-toggle="modal" data-target="#editModal" class="text-left">{{item.projectTitle}} {{item.unitName}} {{item.propertyTitle}} {{item.subcategoryTitle}} {{item.realestateTitle}}</td> --> 																
													<td title="Delete">
														<input type="checkbox" ng-model="item.selected"  value="{{item.enquiryId}}" ng-show="UserAdminCheck" >
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="box-footer">
									<div class="row">
										<div class="col-md-6 text-left">
											<div class="hint-text">Showing <b>{{startindex+1}}-{{getEnquiries.length+startindex}}</b> out of <b>{{allcounts.enquiryCount}}</b> entries</div>
										</div>										
										<div class="col-md-6 text-right">
											<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev('<%= request.getParameter("projectid") %>')">
												<i class="fa fa-step-backward"></i>
											</button>
											{{currentPage+1}}
											<button type="submit" class="btn btn-primary" ng-disabled="getEnquiries.length+startindex == allcounts.enquiryCount" ng-click="next('<%= request.getParameter("projectid") %>')">
												<i class="fa fa-step-forward"></i>
											</button>
										</div>
									</div>
								</div>
							</div>					
						</div>	
						
						<div class="col-md-4">
							<div class="hint-text">&nbsp;</div>
							<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-8 text-left">
											<h6 class="box-title ng-binding">Today's Follow-up (09-10-2021)</h6>
										</div>
										<div class="col-md-4 text-right">
											<div class="box-tools pull-right">
												<button class="btn btn-success" data-toggle="modal" data-target="#toDoModal">Add New</button>
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
										</div>
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin">											
											<tbody>												
												<tr ng-show="errorFollowupStatus == 1" class="ng-hide">
													<td colspan="3" class="text-center"><p style="color: red;" class="ng-binding"></p></td>
												</tr>
												<!-- ngRepeat: item in todayFollowupEnquiries -->
											</tbody>
										</table>
									</div>
								</div>								
							</div>
							<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-12 text-left">
											<div class="box-tools pull-right">
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
											<h6 class="box-title">Coming up Follow-ups</h6>
											<div class="input-group">
												<span class="k-widget k-datepicker k-header KendoDate ng-pristine ng-valid" style="width: 40%;"><span class="k-picker-wrap k-state-default"><input class="KendoDate ng-pristine ng-valid k-input" id="fromdate" ng-model="fromdate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;" data-role="datepicker" type="text" role="combobox" aria-expanded="false" aria-owns="fromdate_dateview" aria-disabled="false" autocomplete="off"><span class="k-icon k-i-warning"></span><span unselectable="on" class="k-select" aria-label="select" role="button" aria-controls="fromdate_dateview"><span class="k-icon k-i-calendar"></span></span></span></span> to
												<span class="k-widget k-datepicker k-header KendoDate ng-pristine ng-valid" style="width: 40%;"><span class="k-picker-wrap k-state-default"><input class="KendoDate ng-pristine ng-valid k-input" id="todate" ng-model="todate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;" data-role="datepicker" type="text" role="combobox" aria-expanded="false" aria-owns="todate_dateview" aria-disabled="false" autocomplete="off"><span class="k-icon k-i-warning"></span><span unselectable="on" class="k-select" aria-label="select" role="button" aria-controls="todate_dateview"><span class="k-icon k-i-calendar"></span></span></span></span>
												&nbsp;<a href="#" ng-click="getEnquiryFollowupsByDate()" class="btn btn-success">
													<!-- ngIf: spin == 0 --><i class="fa fa-ok ng-scope" aria-hidden="true" ng-if="spin == 0"></i><!-- end ngIf: spin == 0 -->
													<!-- ngIf: spin == 1 -->Go
												</a>
											</div>																						
										</div>										
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin">											
											<tbody>												
												<!-- ngRepeat: item in followupEnquiriesByDate -->
											</tbody>
										</table>
									</div>
								</div>								
							</div>
							<div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-12 text-left">
											<div class="box-tools pull-right">
												<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>										
											</div>
											<h6 class="box-title">Incomplete Follow-up</h6>
										</div>										
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin" style="table-layout: fixed; width: 97%">											
											<tbody>												
												<!-- ngRepeat: item in incompleteFollowupEnquiries -->
											</tbody>
										</table>
									</div>
								</div>								
							</div>
							<!-- <div class="box box-tgsc">
								<div class="box-header with-border">
									<div class="row">
										<div class="col-md-12 text-left">
											<h6 class="box-title">Incomplete Follow-up</h6>
										</div>
									</div>
								</div>
								<div class="box-body">
									<div class="table-responsive">
										<table class="table no-margin">											
											<tbody>												
												<tr ng-show="errorFollowupStatus == 1">
													<td colspan="3" class="text-center"><p style="color: red;">{{msgFollowupStatus}}</p></td>
												</tr>
												<tr ng-repeat="item in incompleteFollowupEnquiries" style="cursor:pointer; cursor:hand; border-left: rgb(195, 13, 13); background-color:#ff000042; border-radius: 3px;" ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC	'} || {'border-color': 'green'}">
													<td width="25%" ng-click="getEnquiryDetail(item.enquiryId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#logModal" style="line-height: 45px;">{{item.followupTime}}</td>
													<td width="70%" ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.remark}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</span></td>
													<td width="5%">
														<i class="fa fa-check-circle-o" aria-hidden="true" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" ng-if="item.followupStatus == 'y'" title="Click mark complete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>														
														<i class="fa fa-check-circle" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" title="Marked as completed" aria-hidden="true" ng-if="item.followupStatus == 'c'" style="color: green; font-size: 20px; line-height: 45px;"></i>
														<br>
														<i class="fa fa-close" aria-hidden="true" ng-click="deleteFollowup(item.followupId)" ng-if="item.followupStatus == 'y'" title="Click to delete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>
														<br>
														<i class="fa fa-pencil-square-o" aria-hidden="true" ng-click="getFollowupDetails(item.followupId)" ng-if="item.followupStatus == 'y'" data-toggle="modal" data-target="#toDoModalEdit" title="Click to edit this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>
													</td>													
												</tr>
											</tbody>
										</table>
									</div>
								</div>								
							</div> -->
						</div>					
					</div>
				</section>
			</div>
		</div>
		<div class="modal fade" id="editModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">					
					<div class="modal-body">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs pull-right" style="margin-right: 20px;">								
								<li>
									<a href="#tab4" data-toggle="tab">User</a>
								</li>
								<li>
									<a href="#tab3" data-toggle="tab">Change Enquiry Status</a>
								</li>								
								<li>	
									<a href="#tab2" data-toggle="tab">Logs & Follow-ups</a>
								</li>
								<li class="active">
									<a href="#tab1" data-toggle="tab">Edit Enquiry</a>
								</li>
								<li class="pull-left header">
									<i class="fa fa-user"></i> 
									Enquiry - <span ng-show="getEnquiryDetailById.userCompanyName">{{getEnquiryDetailById.userCompanyName}} -</span> {{getEnquiryDetailById.firstName}} {{getEnquiryDetailById.lastName}} 
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<div class="box-body">
										<div class="row">
											<div class="col-md-3">
												<label>Enquiry Date<font color="red" size="3">*</font></label>
												<div class="input-group">
													<input class="KendoDate" id="datepicker1" ng-model="enquirydate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
												</div>
											</div>
											<div class="col-md-3">
												<label>Enquiry Via<font color="red" size="3">*</font></label>
												<div class="form-group">
													<select id="enquiryvia" name="enquiryvia" ng-model="enquiryvia" class="form-control">
														<option value="">Enquiry Via</option>
														<option value="Call">Call</option>
														<option value="Import">Import From File</option>
														<option value="Personal Meeting">Personal Meeting</option>
														<option value="Reference">Reference</option>
														<option value="Walk-In">Walk-In</option>
														<option value="Web">Web</option>										
													</select>																	
												</div>
											</div>
											<div class="col-md-3">
												<label>Reference Type</label>
												<div class="input-group">
													<select id="usertypeid" name="usertypeid" ng-model="usertypeid" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-change="getUserByUserType(usertypeid)" class="form-control">
														<option value="">Reference Type</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userTypeModal" title="Add New Client"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-3">
												<label>Reference By</label>
												<div class="input-group">
													<select id="referenceid" name="referenceid" ng-model="referenceid" ng-options="item.userId as item.userCompanyName+' - '+item.firstName+' '+item.middleName+' '+item.lastName for item in getUserNameByUserType" class="form-control">
														<option value="">Reference By</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Client"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
										</div>
										<div class="row form-group">
											<div class="col-md-3">
												<label>Client<font color="red" size="3">*</font></label>
												<div class="input-group">
													<select id="clientid" name="clientid" ng-model="clientid" ng-options="item.userId as item.userCompanyName+' - '+item.firstName+' '+item.lastName+'-'+item.mobileNumber for item in getClientNames" class="form-control">
														<option value="">Client</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#userModal" title="Add New Client"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-3">
												<label>Occupation</label>
												<div class="input-group">
													<select id="occupationid" name="occupationid" ng-model="occupationid" class="form-control" ng-change="setFlag()">
														<option value="">Occupation</option>
														<option ng-repeat="item in alloccupation" value="{{item.occupationId}}">{{item.occupationName}}</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#occupationModal" title="Add New Occupation"><i class="fa fa-plus"></i></button>
													</span>
												</div>
												<p ng-show="errorEnquiryClient == 1" style="color: red;">{{msgEnquiryClient}}</p>
											</div>
											<div class="col-md-3">
												<label>Designation</label>
												<div class="input-group">
													<select id="designationid" name="designationid" ng-model="designationid" class="form-control" ng-change="setFlag()">
														<option value="">Designation</option>
														<option ng-repeat="item in alldesignation" value="{{item.designationId}}">{{item.designationName}}</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#designationModal" title="Add New Designation"><i class="fa fa-plus"></i></button>
													</span>
												</div>
												<p ng-show="errorEnquiryClient == 1" style="color: red;">{{msgEnquiryClient}}</p>
											</div>
											<div class="col-md-3"><br>
												<div class="form-group text-right">
													<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseEnquiry1" aria-expanded="false" aria-controls="collapseExample">More Details</button>
												</div>
											</div>
										</div>
										<div class="row">	
											<div class="collapse" id="collapseEnquiry1">
												<div class="row form-group" style="margin-right: 0;margin-left: 0;">
													<div class="col-md-3">
														<div class="form-group">
															<label>Choose Option</label>
															<div class="form-group">
																<select id="chooseoption" name="chooseoption" ng-model="chooseoption" class="form-control">
																	<option value="">Choose Option</option>
																	<option value="For Own Use">For Own Use</option>
																	<option value="For Investment">For Investment</option>
																	<option value="For Relative or Parent">For Relative or Parent</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">
															<label>What's your budget?</label>
															<div class="form-group">
																<select id="budget" name="budget" ng-model="budget" class="form-control">
																	<option value="">Select Budget</option>
																	<option ng-repeat="r in allrange" value="{{r.rangeId}}">{{r.rangeFrom}} - {{r.rangeTo}} {{r.unitName}}</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-12">
														<div class="form-group">
															<label> Which features are most important to you and your family for perfect home? </label>
															<textarea rows="4" name="features" id="features" ng-model="features" class="form-control" placeholder="Remark*..."></textarea>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label>Are you considering any other project?</label>
															<div class="form-group">
																<select id="consideringproject" name="consideringproject" ng-model="consideringproject" class="form-control">
																	<option value="">Select</option>
																	<option value="yes">Yes</option>
																	<option value="no">No</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label>By what time are you expecting to finalized?</label>
															<div class="form-group">
																<select id="expectingtime" name="expectingtime" ng-model="expectingtime" class="form-control">
																	<option value="">Select</option>
																	<option value="1">15 Days</option>
																	<option value="2">15 - 45 Days</option>
																	<option value="3">more then 3 months</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign to</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-10">
																	<select id="employeeid" name="employeeid" ng-model="employeeid" class="form-control" ng-change="setFlag()">
																		<option value="">Employee</option>
																		<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
																	</select>												
																	<p ng-show="errorEmpName == 1" style="color: red;">{{msgEmpName}}</p>
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<a href="#" ng-click="addAssignRowEdit(enquiryid)" ng-disabled="spinEmp == 1" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus" ng-show="spinEmp != 1"></span><i class="fa fa-refresh fa-spin" ng-if="spinEmp == 1"></i></a>
																	</div>
																</div>								
															</div>													
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> Employee Name </th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in getenquiryassignlist">
																			<td> {{item.firstName}} {{item.lastName}} </td>																															
																			<td>
																				<a href="#" ng-click="removeAssignRowEdit(item.enquiryAssignId, enquiryid)" class="delete" data-toggle="modal">
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
															 <h4 class="panel-title"><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;Property type </h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-4">													
																	<div class="form-group">
																		<select id="categoryid" name="categoryid" ng-model="categoryid" class="form-control" ng-change="onChangeCategory(categoryid) || setFlag()">
																			<option value="">Category</option>
																			<option ng-repeat="item in getAllCategory" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>
																		</select>														
																	</div>
																	<p ng-show="errorRealestateCategoryTitle == 1" style="color: red;">{{msgCategoryType}}</p>
																</div>									
																<div class="col-md-4">													
																	<div class="form-group">
																		<select id="subcategoryid" name="subcategoryid" ng-model="subcategoryid" class="form-control">
																			<option value="">Subcategory</option>
																			<option ng-repeat="item2 in projectSubcategory" value="{{item2.realestateSubcategoryId}}">{{item2.subcategoryTitle}}</option>
																		</select>
																		<p ng-show="errorRealestateSubcategoryTitle == 1" style="color: red;">{{msgRealestateSubType}}</p>														
																	</div>													
																</div>
																<div class="col-md-4">													
																	<div class="form-group">
																		<select id="realestateid" name="realestateid" ng-model="realestateid" class="form-control">
																			<option value="">Type</option>
																			<option ng-repeat="items in getRealestateType" value="{{items.realestateId}}">{{items.realestateTitle}}</option>
																		</select>
																		<p ng-show="errorRealestateTypeTitle == 1" style="color: red;">{{msgRealestateTypeTitle}}</p>														
																	</div>													
																</div>
															</div>
															<div class="row">
																<div class="col-md-3">															
																	<div class="form-group">
																		<select name="unitmasterid" id="unitmasterid" ng-model="unitmasterid" class="form-control">
																			<option value="">Unit Name</option>
																			<option ng-repeat="items in projectUnitNameList" value="{{items.unitMasterId}}">{{items.unitName}}</option>
																		</select>																											
																	</div>										
																</div>
																<div class="col-md-3">
																	<div class="form-group">														
																		<select id="projectidedit" name="projectidedit" ng-model="projectidedit" class="form-control" ng-change="onChangeProject(projectidedit, categoryid, subcategoryid) || setFlag()">
																			<option value="">Project</option>
																			<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
																		</select>
																		<p ng-show="errorProjectType == 1" style="color: red;">{{msgProjectType}}</p>										
																	</div>									
																</div>
																<div class="col-md-3">
																	<div class="form-group">
																		<select id="propertyid" name="propertyid" ng-model="propertyid" class="form-control" ng-change="setFlag()">
																			<option value="">Property Number</option>
																			<option ng-repeat="item in propertyNumberList" value="{{item.propertyId}}">{{item.propertyTitle}}</option>
																		</select>
																		<p ng-show="errorPropertyNumber == 1" style="color: red;">{{msgPropertyNumber}}</p>																
																	</div>													
																</div>
																<div class="col-md-1">
																	<div class="form-group">
																		<button ng-disabled="enqspin == 1" ng-click="editEnquiryProperty(enquiryid)" class="btn btn-primary btn-sm" title="Add Property Types"><i class="fa fa-refresh fa-spin" ng-if="enqspin == 1"></i><span  ng-if="enqspin != 1" class="fa fa-plus"></span></button>
																	</div>
																</div>																																													
															</div>																									
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> Category </th>
																			<th> Sub-category </th>
																			<th> Type </th>
																			<th> Unit Name</th>
																			<th> Project </th>
																			<th> Property No.</th>
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in getenquiryproperty">
																			<td> {{item.categoryTitle}} </td>
																			<td> {{item.subcategoryTitle}} </td>
																			<td> {{item.realestateTitle}} </td>
																			<td> {{item.unitName}} </td>
																			<td> {{item.projectTitle}} </td>
																			<td> {{item.propertyTitle}} </td>
																			<td>
																				<a href="#" ng-click="removeEnquiryProperties(item.enquiryPropertyId,enquiryid)" class="delete" data-toggle="modal">
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
												<div class="form-group">
													<label>Remarks</label>
													<textarea rows="4" id="remarks" name="remarks" ng-model="remarks" placeholder="Remarks" class="form-control"></textarea>										
												</div>
											</div>								
										</div>
										<div class="row">							
											<div class="col-md-6 text-left">
												<strong ng-show="success == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{message}}</strong>								
												<strong ng-show="info == 1" style="color: red;"><span class="fa fa-info-circle"></span> {{message}}</strong>							
											</div>
											<div class="col-md-6 text-right">								
												<button type="submit" ng-click="editEnquiry(enquiryid)" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Update </button>
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tab2">
									<div class="box-body">
										<div class="row form-group">	
											<div class="col-md-6">								
												<h4 class="modal-title"> <i class="fa fa-info-circle" aria-hidden="true"></i> Enquiry Log </h4>								
											</div>
											<div class="col-md-6">												
												<h4 class="modal-title"> <i class="fa fa-info-circle" aria-hidden="true"></i> Enquiry Follow-up </h4>								
											</div>														
										</div>
										<div class="row form-group">
											<div class="col-md-6">
												<div class="col-md-12">
													<div class="form-group">
														<label>Log Date Time<span class="red-star">*</span></label>
														<input class="KendoDateTime" id="datetimepicker" title="Date & Time Picker" style="width: 100%"/>
													</div>
												</div>
												<div class="col-md-10">
													<div class="form-group">
														<textarea rows="4" name="enquirylog" id="enquirylog" ng-model="enquirylog" class="form-control" placeholder="Log text..." ng-change="setFlag()"></textarea>
														<p ng-show="errorEnquiryLog == 1" style="color: red;">{{msgEnquiryLog}}</p> 
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<button ng-disabled="spinLog == 1" ng-click="addEnquiryLog(enquiryid)" class="btn btn-primary btn-lg" title="Add Enquiry Log"><i class="fa fa-refresh fa-spin" ng-if="spinLog == 1"></i><span ng-show="spinLog != 1" class="fa fa-plus"></span></button>
													</div>
												</div>
												<div class="col-md-12">
													<div class="col-md-4 text-right">
														<div class="form-group">
															<input type="checkbox" id="notifylogviaemail" name="notifylogviaemail" ng-model="notifylogviaemail">&nbsp;E-Mail																		
														</div>
													</div>
													<div class="col-md-4 text-right">
														<div class="form-group">									
															<input type="checkbox" id="notifylogviasms" name="notifylogviasms" ng-model="notifylogviasms">&nbsp;SMS									
														</div>
													</div>								
												</div>
											</div>
											<div class="col-md-6">
												<div class="col-md-12">
													<div class="form-group">
														<label>Next Follow-Up Date Time<span class="red-star">*</span></label>
														<input class="KendoDateTime" id="datetimepicker1" title="Date & Time Picker" style="width: 100%"/>
														<p ng-show="errorFollowupDate == 1" style="color: red;">{{msgFollowupDate}}</p>
													</div>
												</div>
												<div class="col-md-10">
													<div class="form-group">
														<textarea rows="4" name="followupremark" id="followupremark" ng-model="followupremark" class="form-control" placeholder="Remark..."></textarea>										 
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<button ng-disabled="spinFollowup == 1" ng-click="addEnquiryFollowup(enquiryid)" class="btn btn-primary btn-lg" title="Add Enquiry Log"><i class="fa fa-refresh fa-spin" ng-if="spinFollowup == 1"></i><span ng-show="spinFollowup != 1" class="fa fa-plus"></span></button>
													</div>
												</div>
												<div class="col-md-12">
													<div class="col-md-4 text-right">
														<div class="form-group">
															<input type="checkbox" id="notifyfollowupviaemail" name="notifyfollowupviaemail" ng-model="notifyfollowupviaemail">&nbsp;E-Mail																		
														</div>
													</div>
													<div class="col-md-4 text-right">
														<div class="form-group">									
															<input type="checkbox" id="notifyfollowupviasms" name="notifyfollowupviasms" ng-model="notifyfollowupviasms">&nbsp;SMS									
														</div>
													</div>								
												</div>
											</div>							
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="box box-warning direct-chat direct-chat-warning" style="border-color: #f4f4f4;">
													<div class="box-header with-border">
														<h3 class="box-title">Inquiry Logs</h3>
														<div class="box-tools pull-right">										
															<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>							
														</div>
													</div>
													<div class="box-body">
														<div ng-show="logLoader" class="direct-chat-msg">																				
															<div class="direct-chat-text text-center" style="margin: 5px 0 0 0; font-size: 16px; background-color: #e3e7f0; position: unset;">
																Loading data... Please Wait...<i class="fa fa-spinner fa-pulse"></i>
															</div>											
								                        </div>										
														<div ng-show="!logLoader" class="direct-chat-msg" ng-repeat="item in getEnquiryLog">																				
															<div class="direct-chat-text" style="margin: 5px 0 0 0; font-size: 16px; background-color: #e3e7f0; position: unset;">
																{{item.statusName}}
															</div>
															<div class="direct-chat-info clearfix">
																<span class="direct-chat-timestamp pull-left">{{item.firstName}} {{item.lastName}}</span>
																<span class="direct-chat-timestamp pull-right"><span>{{item.logDateTime}}</span> <span ng-if="item.logDateTime == null">{{item.createdDate}}</span></span>
															</div>
								                        </div>
								                    </div>
								                 </div>	
											</div>
											<div class="col-md-6">
												<div class="box box-warning direct-chat direct-chat-warning" style="border-color: #f4f4f4;">
													<div class="box-header with-border">
														<h3 class="box-title">Inquiry Followups</h3>
														<div class="box-tools pull-right">										
															<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>							
														</div>
													</div>
													<div class="box-body">	
														<div ng-show="followupLoader" class="direct-chat-msg right">																				
															<div class="direct-chat-text text-center" style="margin: 5px 0 0 0; font-size: 16px; position: unset;">
																Loading data... Please Wait...<i class="fa fa-spinner fa-pulse"></i>
															</div>											
								                        </div>									
														<div ng-show="!followupLoader" class="direct-chat-msg right" ng-repeat="item in getEnquiryFollowup">									
															<div class="direct-chat-text" style="margin: 5px 0 0 0; font-size: 16px; position: unset;">
																{{item.remark}}
															</div>
															<div class="direct-chat-info clearfix">
																<span class="direct-chat-timestamp pull-right">{{item.firstName}} {{item.lastName}}</span>
																<span class="direct-chat-timestamp pull-left">{{item.followupDateTime}}</span>
															</div>
								                        </div>
								                    </div>
								                 </div>								
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tab3">
									<div class="box-body">
										<div class="row">
											<div class="col-md-4">								
												<div class="form-group">
													<select id="enquirystatus" name="enquirystatus" ng-model="enquirystatus" class="form-control" ng-change="setFlag()">
														<option value="">Enquiry Status*</option>
														<option value="o">Working</option>
														<option value="l">Lost</option>
														<option value="w">Won</option>
														<option value="h">Hold</option>
													</select>
													<p ng-show="errorEnquiryStatus == 1" style="color: red;">{{msgEnquiryStatus}}</p>																				
												</div>				
											</div>
											<div class="col-md-8">
												<div class="input-group">
													<select id="statusreason" name="statusreason" ng-model="statusreason" class="form-control">
														<option value="">Select Reason</option>
														<option ng-repeat="item in getStatusReason" value="{{item.reasonId}}">{{item.statusReason}}</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#statusReasonModal" title="Add New Product"><i class="fa fa-plus"></i></button>
													</span>
												</div>										
											</div>
										</div>
										<div class="row" ng-show="enquirystatus == 'l' || enquirystatus == 'w'">
											<div class="col-md-12">
												<label class="checkbox-inline">
											      <input type="checkbox" ng-model="statusclose" id="statusclose" value="cw">Close Enquiry
											    </label>
											</div>
										</div>
										<div class="row">															
											<div class="col-md-6 text-left">
												<strong ng-show="statusSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{statusSuccessMsg}}</strong>
												<strong ng-show="statusSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{statusErrorMsg}}</strong>								
											</div>
											<div class="col-md-6 text-right">
												<button type="submit" ng-click="updateEnquiryStatus(enquiryid)" ng-disabled="spinStatus == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinStatus == 1"></i> Submit</button>					
												<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">										
											</div>								
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tab4">
									<div class="box-body">
										<div class="row">								
											<div class="col-md-3">
												<div class="form-group">
													<label>Company Name</label>
													<input type="text" id="companyname" name="companyname" ng-model="companyname" placeholder="Company Name" class="form-control" autofocus>										
												</div>									
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>First Name<font color="red" size="3">*</font></label>
													<input type="text" id="firstname" name="firstname" ng-model="firstname" placeholder="First Name" class="form-control">
													<p ng-show="errorFirstName == 1" style="color: red;">{{msgFirstName}}</p>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>Middle Name</label>
													<input type="text" id="middlename" name="middlename" ng-model="middlename" placeholder="Middle Name" class="form-control">
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>Last Name</label>
													<input type="text" id="lastname" name="lastname" ng-model="lastname" placeholder="Last Name" class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">
												<div class="form-group">
													<label>Gender</label>
													<div class="form-group">
														<select id="gender" name="gender" ng-model="gender" class="form-control">
															<option value="">Gender</option>
															<option value="m">Male</option>
															<option value="f">Female</option>
															<option value="o">Other</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>User Type<font color="red" size="3">*</font></label>
													<div class="form-group">
														<select id="usertypename" name="usertypename" ng-model="usertypename" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" class="form-control">
															<option value="">User Type</option>
														</select>
														<p ng-show="errorUserType == 1" style="color: red;">{{msgUserType}}</p>
													</div>
												</div>
											</div>								
											<div class="col-md-4">
												<div class="form-group">
													<label> Profile Picture </label>
													<input type="file" id="profilepicture" name="profilepicture" class="form-control">
												</div>
											</div>
											<div class="col-md-3 text-center">
												<img src="{{profilepicture1}}" class="img-responsive">
												<br ng-if="profilepicture1 != ''">
												<a ng-click="deleteProfilePicture()" class="btn btn-danger text-center" ng-if="profilepicture1 != ''" data-toggle="tooltip" title="Remove Profile Picture">
													<span class="fa fa-trash-o"></span>
												</a>
											</div>
										</div>
										<div class="row">								
											<div class="col-md-3">
												<div class="form-group">
													<label> Date Of Birth</label>
													<div class="form-group">
														<input class="KendoDate" id="datepicker1" ng-model="dateofbirth" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label> Aadhar Number </label>
													<input type="text" id="aadharnumber" name="aadharnumber" ng-model="aadharnumber" class="form-control" placeholder="0000-0000-0000" data-mask="9999-9999-9999" />
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label> Passport Number </label>
													<input type="text" id="passportnumber" name="passportnumber" ng-model="passportnumber" class="form-control" placeholder="Passport Number" />
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>PAN Number </label>
													<input type="text" id="pannumber" name="pannumber" ng-model="pannumber" class="form-control" placeholder="PAN Number" />
												</div>
											</div>
										</div>
										<div class="row">								
											<div class="col-md-4">
												<div class="form-group">
													<label> Address-1 </label>
													<input type="text" id="address1" name="address1" ng-model="address1" class="form-control" placeholder="Address Line-1" />
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label> Address-2 </label>
													<input type="text" id="address2" name="address2" ng-model="address2" class="form-control" placeholder="Address Line-2" />
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label> Address-3 </label>
													<input type="text" id="address3" name="address3" ng-model="address3" class="form-control" placeholder="Address Line-3" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3">
												<label>Select Country</label>
												<div class="input-group">
													<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countryname)" class="form-control">
														<option value="">Country</option>
													</select>
													<span class="input-group-btn">
														<button class="btn btn-info btn-flat" type="button" data-toggle="modal" data-target="#countryModal" title="Add New Country"><i class="fa fa-plus"></i></button>
													</span>
												</div>
											</div>
											<div class="col-md-3">
												<label>Select State</label>
												<div class="input-group">
													<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getStates" class="form-control">
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
													<input type="text" id="cityname" name="cityname" ng-model="cityname" class="form-control" placeholder="City Name">
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label> Pincode </label>
													<input type="text" id="pincode" name="pincode" ng-model="pincode" class="form-control" placeholder="Pincode">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">
												<div class="form-group">
													<label> Mobile Number </label>
													<input type="text" id="mobilenumber" name="mobilenumber" ng-model="mobilenumber" class="form-control" placeholder="Mobile Number">
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<label>Landline Number </label>
													<input type="text" id="landlinenumber" name="landlinenumber" ng-model="landlinenumber" class="form-control" placeholder="Landline Number">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label> Email </label>
													<input type="text" id="email" name="email" ng-model="email" ng-change="checkEmailAddressEdit()" class="form-control" placeholder="Email">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Password </label>
													<input type="text" id="password" name="password" ng-model="password" class="form-control" placeholder="Password">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3">
												<div class="form-group">
													<label> GST Number </label>
													<input type="text" id="gstnoedit" name="gstnoedit" ng-model="gstnoedit" class="form-control" placeholder="GST Number" />
												</div>
											</div>
										</div>
										<div class="row" ng-show="usertypename == 2">
											<div class="col-md-5">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign Projects</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-10">
																	<div class="form-group">																
																		<select id="projectid" name="projectid" ng-model="projectid" class="form-control" ng-change="setFlag()">
																			<option value="">Project</option>
																			<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
																		</select>
																		<p ng-show="errorProject == 1" style="color: red;">{{msgProject}}</p>										
																	</div>															
																</div>	
																<div class="col-md-2">
																	<div class="form-group">
																		<button type="button" ng-click="editProjectRow(userid)" class="btn btn-primary btn-sm" title="Assign Project" ng-disabled="spinEmployeeProject == 1"><span class="fa fa-plus" ng-show="spinEmployeeProject != 1"></span><i class="fa fa-refresh fa-spin" ng-show="spinEmployeeProject == 1"></i></button>
																	</div>
																</div>								
															</div>													
															<div class="table-responsive table-bordered">
																<table class="table">
																	<thead>
																		<tr>
																			<th> Project Name </th>														
																			<th> Action </th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="item in userDetailById.employeeProjectList">
																			<td> {{item.projectTitle}} </td>																															
																			<td>
																				<a href="#" ng-click="deleteProjectRow(item.employeeProjectId, userid)" class="delete" data-toggle="modal">
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
											<div class="col-md-5">
												<div class="panel-group">
													<div class="panel panel-default">
														<div class="panel-heading">
															 <h4 class="panel-title"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Assign Access</h4>
														</div>
														<div class="panel-body">
															<div class="row">
																<div class="col-md-10">
																	<div class="form-check form-check-inline">
																		<input type="checkbox" id="accessprojectedit" ng-model="accessprojectedit" class="form-check-input"> 
																		<label class="form-check-label">Project</label>
																	</div>
																	<div class="form-check form-check-inline">
																		<input type="checkbox" id="accesspropertyedit" ng-model="accesspropertyedit" class="form-check-input"> 
																		<label class="form-check-label">Property</label>
																	</div>
																	<div class="form-check form-check-inline">
																		<input type="checkbox" id="accessenquiryedit" ng-model="accessenquiryedit" class="form-check-input"> 
																		<label class="form-check-label" >Enquiry</label>
																	</div>
																	<div class="form-check form-check-inline">
																		<input type="checkbox" id="accesspaymentedit" ng-model="accesspaymentedit" class="form-check-input"> 
																		<label class="form-check-label">Payment Status</label>
																	</div>
																	<div class="form-check form-check-inline">
																		<input type="checkbox" id="accessuseredit" ng-model="accessuseredit" class="form-check-input"> 
																		<label class="form-check-label">User</label>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-8 text-left">
												<strong ng-show="userSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
												<strong ng-show="userSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
											</div>						
											<div class="col-md-4 text-right">
												<button ng-click="editUser(userid);" type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
											</div>
										</div>
									</div>
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
						<div class="row">	
							<div class="col-md-12">
								<h4 class="modal-title" style="color: red;"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Enquiry </h4>								
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteEnquiry()">
					</div>
				</div>
			</div>
		</div>
		<div id="importModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-6">
								<h4 class="modal-title"> <i class="fa fa-cloud-download" aria-hidden="true"></i> Import Enquiry </h4>								
							</div>
							<div class="col-md-6 text-right">
								<h5><a href="<%=request.getContextPath() %>/resources/admin/Sample/ImportSampleFile.xls" download> <i class="fa fa-download" aria-hidden="true"></i> Download Sample Sheet</a></h5>
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<input type="file" class="form-control" name="enquiryfile" id="enquiryfile" ng-model="enquiryfile" accept=".xls">
									<p ng-show="errorFile == 1" style="color: red; font-size: 14px;">{{msgFile}}</p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-6 text-left">
								<label style="color: green" ng-if="submitSuccess == 1">{{msgSuccess}}</label>
								<label style="color: red" ng-if="submitError == 1">{{msgError}}</label>
							</div>
							<div class="col-md-6 text-right">
								<button type="submit" class="btn btn-success" ng-click="importEnquiry()"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>Submit</button>
								<input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancel">	
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>	
		<div id="statusReasonModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-12">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title"> <i class="fa fa-question-circle" aria-hidden="true"></i> Add Status Reason</h4>								
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">								
								<div class="form-group">
									<label> Status Reason<span class="red-star">*</span> </label>
									<input type="text" id="reason" name="reason" ng-model="reason"  class="form-control" placeholder="Reason" ng-change="setFlag()">
									<p ng-show="errorStatusReason == 1" style="color: red;">{{msgStatusReason}}</p>
								</div>												
							</div>							
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-6 text-left">
								<strong ng-show="reasonSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{reasonSuccessMsg}}</strong>
								<strong ng-show="reasonSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{reasonErrorMsg}}</strong>
							</div>					
							<div class="col-md-6 text-right">
								<button type="submit" ng-click="addStatusReason()" ng-disabled="spinReason == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spinReason == 1"></i> Submit</button>
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Close">																										
							</div>								
						</div>					
					</div>					
				</div>
			</div>
		</div>
		<div id="toDoModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">	
							<div class="col-md-12">
								<h4 class="modal-title"> <i class="fa fa-info-circle" aria-hidden="true"></i> Add New Follow-up </h4>								
							</div>														
						</div>					
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Follow-Up Date Time<span class="red-star">*</span></label>
									<input class="KendoDateTime" id="datetimepicker2" title="Date & Time Picker" style="width: 100%"/>
									<p ng-show="errorFollowupDate == 1" style="color: red;">{{msgFollowupDate}}</p>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<textarea rows="4" name="followupremark" id="followupremark" ng-model="followupremark" class="form-control" placeholder="Remark*..."></textarea>
									<p ng-show="errorFollowupRemark == 1" style="color: red;">{{msgFollowupRemark}}</p>										 
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-3">
									<div class="form-group">
										<input type="checkbox" id="notifyfollowupviaemail" name="notifyfollowupviaemail" ng-model="notifyfollowupviaemail">&nbsp;E-Mail																		
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">									
										<input type="checkbox" id="notifyfollowupviasms" name="notifyfollowupviasms" ng-model="notifyfollowupviasms">&nbsp;SMS									
									</div>
								</div>								
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<select id="employeeidadd" name="employeeidadd" ng-model="employeeidadd" class="form-control" ng-change="setFlag()">
										<option value="">Employee</option>
										<option ng-repeat="item in getEmployees" value="{{item.userId}}">{{item.firstName}} {{item.lastName}}</option>
									</select>									
								</div>
								<p ng-show="errorEmpName == 1" style="color: red;">{{msgEmpName}}</p>
							</div>	
							<div class="col-md-2">
								<div class="form-group">
									<a href="#" ng-click="addEmpRow()" class="btn btn-primary btn-sm" title="Add URL"><span class="fa fa-plus"></span></a>
								</div>
							</div>								
						</div>													
						<div class="table-responsive table-bordered" ng-if="emplist.length > 0">
							<table class="table">
								<thead>
									<tr>
										<th> Employee Name </th>														
										<th> Action </th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in emplist">
										<td> {{item.firstName}} {{item.lastName}} </td>																															
										<td>
											<a href="#" ng-click="removeEmpRow(item)" class="delete" data-toggle="modal">
												<i class="fa fa-trash" aria-hidden="true" data-toggle="tooltip" title="Delete"></i>
											</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">						
						<button type="submit" ng-disabled="spinFollowup == 1"  class="btn btn-success" ng-click="addNewFollowup()"><i class="fa fa-refresh fa-spin" ng-if="spinFollowup == 1"></i>Submit</button>
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					</div>
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
		<div class="modal fade" id="userModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Add User</h4>
					</div>
					<form ng-submit="addUser()">
						<div class="modal-body">
							<div class="row">								
								<div class="col-md-3">
									<div class="form-group">
										<label>Company Name</label>
										<input type="text" id="companynameadd" name="companynameadd" ng-model="companynameadd" placeholder="Company Name" class="form-control" autofocus>											
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
								<div class="col-md-3">
									<div class="form-group">
										<label>User Type<font color="red" size="3">*</font></label>
										<div class="input-group">
											<select id="usertypenameadd" name="usertypenameadd" ng-model="usertypenameadd" ng-options="item.userTypeId as item.userTypeName for item in getUserTypes" ng-init="usertypenameadd=3" ng-change="setFlag()" class="form-control">
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
										<label> Date Of Birth </label>
										<div class="form-group">
											<input class="KendoDate" id="dateofbirth" title="datepicker" placeholder="DD/MM/YYYY" style="width: 100%;"/>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Mobile Number </label>
										<input type="text" id="mobilenumberadd" name="mobilenumberadd" ng-model="mobilenumberadd" class="form-control" placeholder="Mobile Number">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label> Email </label>
										<input type="text" id="emailadd" name="emailadd" ng-model="emailadd" ng-change="checkEmailAddress()" class="form-control" placeholder="Email">
									</div>
								</div>								
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
										<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" class="form-control">
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
										<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" ng-init="statenameadd = 1" class="form-control">
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
							<div class="row"><br>
								<div class="col-md-6">
									<div class="form-group">
										<label></label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group text-right">
										<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">More Details</button>
									</div>
								</div>
							</div>
							<div class="row">	
								<div class="collapse" id="collapseExample">
									<div class="row form-group" style="margin-right: 0;margin-left: 0;">
										<div class="col-md-2">
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
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label> Profile Picture </label>
												<input type="file" id="profilepictureadd" name="profilepictureadd" class="form-control">
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
												<label>Password </label>
												<input type="text" id="passwordadd" name="passwordadd" ng-model="passwordadd" class="form-control" placeholder="Password">
											</div>
										</div>
									</div>
									<div class="row form-group" style="margin-right: 0;margin-left: 0;">								
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
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label>PAN Number </label>
												<input type="text" id="pannumberadd" name="pannumberadd" ng-model="pannumberadd" class="form-control" placeholder="PAN Number" />
											</div>
										</div>
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
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>					
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="todayFollowupModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">							
							<div class="col-md-8 text-left">
								<h4 class="modal-title"> <i class="fa fa-commenting" aria-hidden="true"></i> Today's Follow-up ({{currentdate | date:'dd-MM-yyyy'}}) </h4>
							</div>
							<div class="col-md-4 text-right">
								<div class="box-tools pull-right">
									<!-- <button class="btn btn-success" data-toggle="modal" data-target="#toDoModal" style="margin-right: 10px;">Add New</button> -->
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>																			
								</div>
							</div>							
						</div>											
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="table-responsive">
									<table class="table no-margin">											
										<tbody>												
											<tr ng-show="errorFollowupStatus == 1">
												<td colspan="3" class="text-center"><p style="color: red;">{{msgFollowupStatus}}</p></td>
											</tr>
											<tr ng-repeat="item in todayFollowupEnquiries" style="cursor:pointer; cursor:hand; border-left: 8px solid #eee; border-radius: 3px;" ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC	'} || {'border-color': 'green'}">
												<td width="25%" ng-click="getEnquiryDetail(item.enquiryId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#logModal" style="line-height: 45px;">{{item.followupTime}}</td>
												<td width="70%" ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.remark}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</span></td>
												<td width="5%">
													<i class="fa fa-check-circle-o" aria-hidden="true" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" ng-if="item.followupStatus == 'y'" title="Click mark complete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>														
													<i class="fa fa-check-circle" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" title="Marked as completed" aria-hidden="true" ng-if="item.followupStatus == 'c'" style="color: green; font-size: 20px; line-height: 45px;"></i>
													<br>
													<i class="fa fa-close" aria-hidden="true" ng-click="deleteFollowup(item.followupId)" ng-if="item.followupStatus == 'y'" title="Click to delete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>
												</td>													
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-6 text-left">
								<label style="color: green" ng-if="submitSuccess == 1">{{msgSuccess}}</label>
								<label style="color: red" ng-if="submitError == 1">{{msgError}}</label>
							</div>
							<div class="col-md-6 text-right">								
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Close">	
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>
		<div id="comingFollowupModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<div class="row">
							<div class="col-md-12 text-left">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>								
								<h4 class="modal-title"> <i class="fa fa-comment-o" aria-hidden="true"></i> Comingup Followups </h4>
								<div class="input-group">
									<input class="KendoDate" id="fromdate" ng-model="fromdate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 40%;"/> to
									<input class="KendoDate" id="todate" ng-model="todate" title="datepicker" placeholder="DD/MM/YYYY" style="width: 40%;"/>
									&nbsp;<a href="#" ng-click="getEnquiryFollowupsByDate()" class="btn btn-success">
										<i class="fa fa-ok" aria-hidden="true" ng-if="spin == 0"></i>
										<i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i>Go
									</a>
								</div>																						
							</div>										
						</div>											
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="table-responsive">
									<table class="table no-margin">											
										<tbody>												
											<tr ng-repeat="item in followupEnquiriesByDate" style="cursor:pointer; cursor:hand; border-left: 8px solid #eee; border-radius: 3px;" ng-style="item.followupStatus == 'y' && {'border-color': '#DCDCDC'} || {'border-color': 'green'}">
												<td width="25%" ng-click="getEnquiryDetail(item.enquiryId)" title="Enquiry Log & Follow-Up" data-toggle="modal" data-target="#logModal">{{item.followupDate}}<br>{{item.followupTime}}</td>
												<td width="70%" ng-click="getEnquiryDetail(item.enquiryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.remark}}<span ng-if="item.firstName != null"><br>{{item.firstName}} {{item.lastName}} - {{item.userCompanyName}}</span></td>
												<td width="5%">
													<i class="fa fa-check-circle-o" aria-hidden="true" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" ng-if="item.followupStatus == 'y'" title="Click mark complete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>														
													<i class="fa fa-check-circle" ng-click="markCompleteFollowup(item.followupId, item.followupStatus)" title="Marked as completed" aria-hidden="true" ng-if="item.followupStatus == 'c'" style="color: green; font-size: 20px; line-height: 45px;"></i>
													<br>
													<i class="fa fa-close" aria-hidden="true" ng-click="deleteFollowup(item.followupId)" ng-if="item.followupStatus == 'y'" title="Click to delete this followup" style="color: #808080; font-size: 20px; line-height: 22px;"></i>
												</td>													
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-md-6 text-left">
								
							</div>
							<div class="col-md-6 text-right">								
								<input type="button" class="btn btn-default" data-dismiss="modal" value="Close">	
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>	
		<script src="<%=request.getContextPath() %>/resources/admin/js/select2.full.min.js"></script>		
		<script>
		$(document).ready(function () {			 		         
	         $("#datepicker,#datepicker1,#datepicker2,#datepicker3").kendoDatePicker({
	       		format: "dd/MM/yyyy",
				dateInput: true,
				value: new Date()
	         });
	    });
		
		$(document).ready(function () {			 		         
	         $("#fromdate,#todate,#dateofbirth").kendoDatePicker({
	       		format: "dd/MM/yyyy",
				dateInput: true					
	         });
	         $("#startdate").kendoDatePicker({
	        	 format: "dd/MM/yyyy",
	        	 dateInput: true,
	        	 value: new Date(d.getFullYear(), d.getMonth(), 1)
	         });
	         $("#enddate").kendoDatePicker({
	        	 format: "dd/MM/yyyy",
	        	 dateInput: true,
	        	 value: new Date(d.getFullYear(), d.getMonth() + 1, 0)
	         });
	    });
		$(".KendoDate").bind("focus", function(){
  			$(this).data("kendoDatePicker").open(); 
		});
		$(".KendoDateTime").bind("focus", function(){
			$(this).data("kendoDateTimePicker").open(); 
		});
		$("#datetimepicker, #datetimepicker1, #datetimepicker2").kendoDateTimePicker({
          	format: "dd/MM/yyyy hh:mm tt",
			value: new Date(),					
           dateInput: true
       });
		$(function () {
			$(".select2").select2();
		});
			$(function () {
				$(".select2").select2();
			});
			document.getElementById("manage").classList.add("active");
			document.getElementById("enquiry").classList.add("active");
		</script>
		
	
				
	</body>
</html>