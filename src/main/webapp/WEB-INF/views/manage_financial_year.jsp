<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Financial Year </title>		
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/financial_year.js"></script>
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />	
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>	
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>					
	</head>	
	<body ng-app="MyApp" ng-controller="financialYearCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Financial Year						
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Financial Year</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Financial Year</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<form ng-submit="addFinancialYear()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Start Date<font color="red" size="3">*</font></label>
											<div class="input-group">
												<input class="KendoDate" id="startdate" title="Select start date" style="width: 100%;" ng-blur='checkEndDate()'/>
											</div>
											<p ng-show="errorStartDate == 1" style="color: red;">{{msgStartDate}}</p>
										</div>									
									</div>								
									<div class="col-md-4">
										<div class="form-group">
											<label>End Date<font color="red" size="3">*</font></label>
											<div class="input-group">
												<input class="KendoDate" id="enddate" title="Select end date" style="width: 100%;" ng-blur='checkEndDate()'/>
											</div>	
											<p ng-show="errorEndDate == 1" style="color: red;">{{msgEndDate}}</p>									
										</div>									
									</div>
									<div class="col-md-4">
										<label> Financial Year Code </label>
										<input type="text" id="financialyearcodeadd" name="financialyearcodeadd" ng-model="financialyearcodeadd" class="form-control" placeholder="Code" disabled>
									</div>
								</div>
							</div>
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="yearSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="yearSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
									</div>
									<div class="col-md-4 text-right">
										<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Financial Year List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchFinancialYear() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchFinancialYear()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th width="40%">Start Date</th>
											<th width="40%">End Date</th>
											<th width="10%">Code</th>
											<th width="5%">Default</th>
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getFinancialYears == ''">
											<td colspan="5"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getFinancialYears" style="cursor:pointer;cursor:hand">
											<td ng-click="getFinancialYear(item.financialYearId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.financialYearStartDate | date:'dd-MMM-yyyy'}}</td>
											<td ng-click="getFinancialYear(item.financialYearId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.financialYearEndDate | date:'dd-MMM-yyyy'}}</td>
											<td ng-click="getFinancialYear(item.financialYearId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.financialYearCode}}</td>
											<td> <input type="radio" ng-model="item.radioselected" value="{{item.financialYearId}}" ng-change="defaultFinancialYear(item.financialYearId)" ng-if="item.defaultt == 'n'"></td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.brandId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getFinancialYears != ''">
										<tr>
											<td colspan="4">
												<div class="alert alert-info" ng-show="infodelete == 1">
													<strong><span class="fa fa-info-circle"></span> {{messagedelete}}</strong>
												</div>
											</td>
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getFinancialYears.length+startindex}}</b> out of <b>{{allcounts.financialYearCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getFinancialYears.length+startindex == allcounts.financialYearCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Financial Year</h4>
					</div>
					<form ng-submit="editFinancialYear(financialyearid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Start Date<font color="red" size="3">*</font></label>
										<div class="input-group">
											<input class="KendoDate" id="startdate1" ng-model="startdate" title="Select start date" style="width: 100%;" ng-blur='checkEndDateEdit()'/>
										</div>
										<p ng-show="errorStartDate == 1" style="color: red;">{{msgStartDate}}</p>
									</div>									
								</div>								
								<div class="col-md-4">
									<div class="form-group">
										<label>End Date<font color="red" size="3">*</font></label>
										<div class="input-group">
											<input class="KendoDate" id="enddate1" ng-model="enddate" title="Select end date" style="width: 100%;" ng-blur='checkEndDateEdit()'/>
										</div>	
										<p ng-show="errorEndDate == 1" style="color: red;">{{msgEndDate}}</p>									
									</div>									
								</div>
								<div class="col-md-4">
									<label> Financial Year Code </label>
									<input type="text" id="financialyearcode" name="financialyearcode" ng-model="financialyearcode" class="form-control" placeholder="Code" disabled>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="yearSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="yearSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Financial Year </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0 && defaultt == 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0 && defaultt == 0"><small>This action cannot be undone.</small></p>
						<p ng-if="d != 0 && defaultt != 0">Sorry... You can not delete default financial year.</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0 && defaultt == 0" class="btn btn-danger" value="Delete" ng-click="deleteFinancialYear()">
					</div>
				</div>
			</div>
		</div>		
		<script>
			$(document).ready(function () {			 		         
		         $("#startdate,#enddate,#startdate1,#enddate1").kendoDatePicker({
		       		format: "dd/MM/yyyy",
					dateInput: true,
					value: new Date()
		         });		         
		    });
			$(".KendoDate").bind("focus", function(){
	  			$(this).data("kendoDatePicker").open(); 
			});
			document.getElementById("master").classList.add("active");
			document.getElementById("financial_year").classList.add("active");
		</script>
	</body>
</html>