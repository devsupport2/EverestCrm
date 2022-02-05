<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> State </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/state.js"></script>
		<style type="text/css">
			.box-body select option { padding: 16px !important;}
		</style>			
	</head>	
	<body ng-app="MyApp" ng-controller="stateCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage State
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managetgsc/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">State</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add State</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<form ng-submit="addState()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label>Select Country<font color="red" size="3">*</font></label>										
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
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="stateSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="stateSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
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
									<h3 class="box-title">State List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchState() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchState()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th width="10%">State Id</th>
											<th width="40%">State Name</th>
											<th width="15%">State Code</th>
											<th width="30%">Country Name</th>
											<th width="5%">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getStates == ''">
											<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getStates" style="cursor:pointer;cursor:hand">
											<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.stateId}}</td>
											<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.stateName}}</td>
											<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.stateCode}}</td>
											<td ng-click="getState(item.stateId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.countryName}}</td>
											<td title="Delete" class="text-center">
												<input type="checkbox" ng-model="item.selected" value="{{item.stateId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getStates != ''">
										<tr>
											<td colspan="3">											
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getStates.length+startindex}}</b> out of <b>{{allcounts.stateCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getStates.length+startindex == allcounts.stateCount" ng-click="next()">
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
						<h4 class="modal-title">Edit State</h4>
					</div>
					<form ng-submit="editState(stateid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="input-group">
										<label>Select Country<font color="red" size="3">*</font></label>
										<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" autofocus>
											<option value="">Country</option>
										</select>
										<p ng-show="errorCountry == 1" style="color: red;">{{msgCountry}}</p>										
									</div>									
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label>State Name<font color="red" size="3">*</font></label>
										<input type="text" id="statename" name="statename" ng-model="statename" placeholder="State Name" class="form-control" autofocus>
										<p ng-show="errorStateName == 1" style="color: red;">{{msgStateName}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>State Code</label>
										<input type="text" id="statecode" name="statecode" ng-model="statecode" placeholder="State Code" maxlength="2" capitalize class="form-control">
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
								<div class="col-md-4">
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
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete State </h4>						
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteState()">
					</div>
				</div>
			</div>
		</div>	
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("state").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		
	</body>
</html>