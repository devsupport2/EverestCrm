<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Payment Schedule </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/payment.js"></script>
					
	</head>	
	<body ng-app="MyApp" ng-controller="paymentCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Payment Schedule
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managetgsc/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Payment Schedule</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Payment Schedule</h3>
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
											<select id="projectidadd" name="projectidadd" ng-model="projectidadd" class="form-control" ng-change="setFlag()">
												<option value="">Project</option>
												<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
											</select>
											<p ng-show="errorProjectTitle == 1" style="color: red;">{{msgProjectTitle}}</p>										
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
														<div class="col-md-4">
															<div class="form-group">
																<input type="text" id="paymentlabeladd" name="paymentlabeladd" ng-model="paymentlabeladd" placeholder="Payment Label" class="form-control" autofocus ng-change="setFlag()">
																<p ng-show="errorPaymentLabel == 1" style="color: red;">{{msgPaymentLabel}}</p>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group">																
																<input type="text" id="paymentscheduleadd" name="paymentscheduleadd" ng-model="paymentscheduleadd" placeholder="Payment Schedule" class="form-control">
																<p ng-show="errorPaymentSchedule == 1" style="color: red;">{{msgPaymentSchedule}}</p>										
															</div>
														</div>
														<div class="col-md-3">
															<div class="form-group">																
																<input type="text" id="paymentpercentageadd" name="paymentpercentageadd" ng-model="paymentpercentageadd" placeholder="Payment percentage" class="form-control">
																<p ng-show="errorPaymentPercentage == 1" style="color: red;">{{msgPaymentPercen}}</p>										
															</div>
														</div>
														<!-- <div class="col-md-3">
															<div class="form-group">
																<input type="text" id="areaunittypeadd" name="areaunittypeadd" ng-model="areaunittypeadd" placeholder="Unit Type" class="form-control">
															</div>
														</div> -->
														<div class="col-md-1">
															<div class="form-group">
																<button ng-disabled="spin == 1" ng-click="addPaymentScheduleRow()" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
															</div>
														</div>
													</div>																									
													<div class="table-responsive table-bordered">
														<table class="table">
															<thead>
																<tr>
																	<th> Payment Label </th>																	
																	<th> Payment Schedule </th>
																	<th> Payment Percentage(%)</th>														
																	<th> Action </th>
																</tr>
															</thead>
															<tbody>
																<tr ng-repeat="item in paymentlist">
																	<td> {{item.paymentlabel}} </td>
																	<td> {{item.paymentschedule}} </td>																	
																	<td> {{item.paymentpercentage}} </td>														
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
										<strong ng-show="paymentSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="paymentSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
									</div>
									<div class="col-md-4 text-right">
										<button type="submit" ng-click="addPayment()" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Submit</button>
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Payment Schedule List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchPayment() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchPayment()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;"></select>
								</div>
								<div class="col-md-2">
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
										<!-- <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button> -->
									</div>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th width="45%">Project Name</th>
											<!-- <th width="30%">Payment Schedule</th>
											<th width="20%">Percentage(%)</th> -->
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getPayment == ''">
											<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getPayment" style="cursor:pointer;cursor:hand">
											<td ng-click="getPayments(item.projectId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.projectTitle}}</td>
											<!-- <td ng-click="getPayments(item.paymentId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.paymentSchedule}}</td>
											<td ng-click="getPayments(item.paymentId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.percentage}}</td> -->
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.paymentId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getCountries != ''">
										<tr>
											<td colspan="1">
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getPayment.length+startindex}}</b> out of <b>{{allcounts.paymentCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getPayment.length+startindex == allcounts.paymentCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Payment Schedule</h4>
					</div>
					<form>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Project<font color="red" size="3">*</font></label>
										<select id="projectidedit" name="projectidedit" ng-model="projectidedit" class="form-control" ng-change="setFlag()">
											<option value="">Project</option>
											<option ng-repeat="item in getProjectName" value="{{item.projectId}}">{{item.projectTitle}}</option>											
										</select>
										<p ng-show="errorProjectTitle == 1" style="color: red;">{{msgProjectTitle}}</p>										
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
													<div class="col-md-4">
														<div class="form-group">
															<input type="text" id="paymentlabeledit" name="paymentlabeledit" ng-model="paymentlabeledit" placeholder="Payment Label" class="form-control" autofocus ng-change="setFlag()">
															<p ng-show="errorPaymentLabel == 1" style="color: red;">{{msgPaymentLabel}}</p>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">																
															<input type="text" id="paymentscheduleedit" name="paymentscheduleedit" ng-model="paymentscheduleedit" placeholder="Payment Schedule" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPaymentSchedule == 1" style="color: red;">{{msgPaymentSchedule}}</p>										
														</div>
													</div>
													<div class="col-md-3">
														<div class="form-group">																
															<input type="text" id="paymentpercentageedit" name="paymentpercentageedit" ng-model="paymentpercentageedit" placeholder="Payment percentage" class="form-control" ng-change="setFlag()">
															<p ng-show="errorPaymentPercentage == 1" style="color: red;">{{msgPaymentPercen}}</p>										
														</div>
													</div>
													<!-- <div class="col-md-3">
														<div class="form-group">
															<input type="text" id="areaunittypeadd" name="areaunittypeadd" ng-model="areaunittypeadd" placeholder="Unit Type" class="form-control">
														</div>
													</div> -->
													<div class="col-md-1">
														<div class="form-group">
															<button ng-disabled="spin == 1" ng-click="editPayment(paymentid)" class="btn btn-primary btn-sm" title="Add Specification"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i><span  ng-if="spin != 1" class="fa fa-plus"></span></button>
														</div>
													</div>
												</div>																									
												<div class="table-responsive table-bordered">
													<table class="table">
														<thead>
															<tr>
																<th> Payment Label </th>																	
																<th> Payment Schedule </th>
																<th> Payment Percentage(%)</th>														
																<th> Action </th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="item in paymentById">
																<td> {{item.paymentLabel}} </td>
																<td> {{item.paymentSchedule}} </td>																	
																<td> {{item.percentage}} </td>														
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
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="paymentSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="paymentSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
								</div>
								<div class="col-md-4 text-right">
									<!-- <button type="submit" ng-disabled="spin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="spin == 1"></i> Save</button> -->
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Country </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deletePayment()">
					</div>
				</div>
			</div>
		</div>		
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("payment_schedule").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
	</body>
</html>