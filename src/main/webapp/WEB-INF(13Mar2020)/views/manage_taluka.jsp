<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Taluka </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/taluka.js"></script>
		<style type="text/css">
			.box-body select option { padding: 16px !important;}
		</style>			
	</head>	
	<body ng-app="MyApp" ng-controller="talukaCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Taluka
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managetgsc/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Taluka</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Taluka</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>						
						<form ng-submit="addTaluka()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label>Select Country<font color="red" size="3">*</font></label>										
											<select id="countrynameadd" name="countrynameadd" ng-model="countrynameadd" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" ng-change="getStateByCountryId(countrynameadd)" ng-init="countrynameadd = 1" autofocus >
												<option value="">Country</option>
											</select>
											<p ng-show="errorCountry == 1" style="color: red;">{{msgCountry}}</p>											
										</div>										
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>State<font color="red" size="3">*</font></label>
											<select id="statenameadd" name="statenameadd" ng-model="statenameadd" ng-options="item.stateId as item.stateName for item in getStates" class="form-control" autofocus ng-change="getDistrictByStateId(statenameadd)">
												<option value="">State</option>
											</select>
											<p ng-show="errorState == 1" style="color: red;">{{msgState}}</p>											
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>District<font color="red" size="3">*</font></label>
											<select id="districtnameadd" name="districtnameadd" ng-model="districtnameadd" ng-options="item.districtId as item.districtName for item in getDistricts" class="form-control" autofocus ng-change="setFlag()">
												<option value="">District</option>
											</select>
											<p ng-show="errorDistrict == 1" style="color: red;">{{msgDistrict}}</p>											
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Taluka Name<font color="red" size="3">*</font></label>
											<input type="text" id="talukanameadd" name="talukanameadd" ng-model="talukanameadd" placeholder="Taluka Name" class="form-control" ng-change="setFlag()">
											<p ng-show="errorTalukaName == 1" style="color: red;">{{msgTalukaName}}</p>
										</div>									
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label>Taluka Code</label>
											<input type="text" id="talukacodeadd" name="talukacodeadd" ng-model="talukacodeadd" placeholder="Taluka Code" maxlength="3" capitalize class="form-control">
										</div>
									</div>
								</div>
							</div>
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="talukaSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="talukaSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
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
									<h3 class="box-title">Taluka List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchTaluka() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchTaluka()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th>Taluka Name</th>
											<th>Taluka Code</th>											
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getTalukas == ''">
											<td colspan="3"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getTalukas" style="cursor:pointer;cursor:hand">
											<td ng-click="getTaluka(item.talukaId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.talukaName}}</td>
											<td ng-click="getTaluka(item.talukaId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.talukaCode}}</td>											
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.talukaId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getTalukas != ''">
										<tr>
											<td colspan="2">											
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getTalukas.length+startindex}}</b> out of <b>{{allcounts.talukaCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getTalukas.length+startindex == allcounts.talukaCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Taluka</h4>
					</div>
					<form ng-submit="editTaluka(talukaid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Country<font color="red" size="3">*</font></label>										
										<select id="countryname" name="countryname" ng-model="countryname" ng-options="item.countryId as item.countryName for item in getCountries" class="form-control" ng-change="getStateByCountryId(countryname)" ng-init="countryname = 1" autofocus >
											<option value="">Country</option>
										</select>
										<p ng-show="errorCountry == 1" style="color: red;">{{msgCountry}}</p>											
									</div>										
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>State<font color="red" size="3">*</font></label>
										<select id="statename" name="statename" ng-model="statename" ng-options="item.stateId as item.stateName for item in getStates" class="form-control" autofocus ng-change="getDistrictByStateId(statename)">
											<option value="">State</option>
										</select>
										<p ng-show="errorState == 1" style="color: red;">{{msgState}}</p>											
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>District<font color="red" size="3">*</font></label>
										<select id="districtname" name="districtname" ng-model="districtname" ng-options="item.districtId as item.districtName for item in getDistricts" class="form-control" autofocus ng-change="setFlag()">
											<option value="">District</option>
										</select>
										<p ng-show="errorDistrict == 1" style="color: red;">{{msgDistrict}}</p>											
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Taluka Name<font color="red" size="3">*</font></label>
										<input type="text" id="talukaname" name="talukaname" ng-model="talukaname" placeholder="Taluka Name" class="form-control" autofocus>
										<p ng-show="errorTalukaName == 1" style="color: red;">{{msgTalukaName}}</p>
									</div>									
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label>Taluka Code</label>
										<input type="text" id="talukacode" name="talukacode" ng-model="talukacode" placeholder="Taluka Code" maxlength="3" capitalize class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="talukaSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="talukaSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Taluka </h4>						
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteTaluka()">
					</div>
				</div>
			</div>
		</div>	
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("taluka").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
	</body>
</html>