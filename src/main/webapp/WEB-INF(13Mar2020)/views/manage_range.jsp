<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Range </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/range.js"></script>
					
	</head>	
	<body ng-app="MyApp" ng-controller="rangeCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Range
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Range</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Range</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
							</div>
						</div>
						<form ng-submit="addRange()">
							<div class="box-body">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label>Range From<font color="red" size="3">*</font></label>
											<input type="number" id="rangefrom" name="rangefrom" ng-model="rangefrom" placeholder="Range From" class="form-control" autofocus ng-change="setFlag()">
											<p ng-show="errorRangeFrom == 1" style="color: red;">{{msgRangeFrom}}</p>
										</div>									
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Range To<font color="red" size="3">*</font></label>
											<input type="number" id="rangeto" name="rangeto" ng-model="rangeto" placeholder="Range To" class="form-control" ng-change="setFlag()">
											<p ng-show="errorRangeTo == 1" style="color: red;">{{msgRangeTo}}</p>
										</div>									
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Unit<font color="red" size="3">*</font></label>
											<select id="unittypeadd" name="unittypeadd" ng-model="unittypeadd" class="form-control" ng-change="setFlag()">
												<option value="">Unit*</option>
												<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
											</select>
											<p ng-show="errorUnitId == 1" style="color: red;">{{msgUnitId}}</p>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Description</label>
											<textarea id="description" name="description" ng-model="description" placeholder="Description" class="form-control"></textarea>
										</div>
									</div>									
								</div>								
							</div>
							<div class="box-footer">
								<div class="row">									
									<div class="col-md-8 text-left">
										<strong ng-show="rangeSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
										<strong ng-show="rangeSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
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
									<h3 class="box-title">Range List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchRange() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchRange()" class="btn btn-flat"><i class="fa fa-search"></i></button>
										</span>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<!-- <select id="pageSize" name="pageSize" ng-model="pageSize" ng-options="item for item in pages" class="form-control" ng-change="changePage()" style="width: auto; display: inline;"></select> -->
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
											<th>Range</th>																																	
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getRange == ''">
											<td colspan="2"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="r in getRange" style="cursor:pointer;cursor:hand">
											<td ng-click="getRangeDetails(r.rangeId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{r.rangeFrom}} - {{r.rangeTo}} {{r.unitName}}</td>																						
											<td title="Delete" class="text-right" ng-click="deleteRange(r.rangeId)">
												<i style="font-size:25px;" class="fa fa-trash" aria-hidden="true"></i>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<%-- <div class="box-footer">
							<div class="row">
								<div class="col-md-5">
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getRange.length+startindex}}</b> out of <b>{{allcounts.rangeCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getRange.length+startindex == allcounts.rangeCount" ng-click="next()">
										<i class="fa fa-step-forward"></i>
									</button>
								</div>
							</div>
						</div> --%>
					</div>
				</section>
			</div>
		</div>		
		<div class="modal fade" id="editModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Edit Range</h4>
					</div>
					<form ng-submit="editRange(rangeid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Range From<font color="red" size="3">*</font></label>
										<input type="number" id="rangefromedit" name="rangefromedit" ng-model="rangefromedit" placeholder="Range From" class="form-control" autofocus ng-change="setFlag()">
										<p ng-show="errorRangeFrom == 1" style="color: red;">{{msgRangeFrom}}</p>
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Range To</label><font color="red" size="3">*</font></label>
										<input type="number" id="rangetoedit" name="rangetoedit" ng-model="rangetoedit" placeholder="Range To" maxlength="3" capitalize class="form-control">
									</div>									
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Unit</label><font color="red" size="3">*</font></label>
										<select id="unittype" name="unittype" ng-model="unittype" class="form-control" ng-change="setFlag()">
											<option value="">Unit*</option>
											<option ng-repeat="item in getMeasurementUnits" value="{{item.measurementUnitId}}">{{item.measurementUnitName}}</option>											
										</select>	
									</div>									
								</div>												
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Description</label>
										<textarea id="descriptionedit" name="descriptionedit" ng-model="descriptionedit" placeholder="Description" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">								
								<div class="col-md-8 text-left">
									<strong ng-show="rangeSubmitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{successMsg}}</strong>
									<strong ng-show="rangeSubmitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{errorMsg}}</strong>
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Currency </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteCurrency()">
					</div>
				</div>
			</div>
		</div>		
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("range").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
	</body>
</html>