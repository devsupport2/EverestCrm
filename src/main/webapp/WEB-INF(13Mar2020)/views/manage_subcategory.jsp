<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Subcategory </title>
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/manage_subcategory.js"></script>					
	</head>	
	<body ng-app="MyApp" ng-controller="subcategoryCtrl" ng-cloak class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Manage Subcategory
					</h1>
					<ol class="breadcrumb">
						<li><a href="<%=request.getContextPath() %>/managetgsc/home"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Subategory</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-tgsc collapsed-box">
						<div class="box-header with-border" data-widget="collapse" style="cursor: pointer;">
							<h3 class="box-title">Add Subcategory</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>								
							</div>
						</div>
						<form ng-submit="addSubcategory()">
							<div class="box-body">
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
							<div class="box-footer">
								<div class="row">								
									<div class="col-md-6 text-left">
										<strong ng-show="submitSuccess == 1" style="color: green;"><span class="fa fa-check-circle"></span> {{msgSuccess}}</strong>
										<strong ng-show="submitError == 1" style="color: red;"><span class="fa fa-times-circle"></span> {{msgError}}</strong>
									</div>
									<div class="col-md-6 text-right">
										<button type="submit" ng-disabled="subcategorySpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="subcategorySpin == 1"></i> Save</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	
									</div>
								</div>			
							</div>
						</form>
					</div>
					<div class="box box-tgsc">
						<div class="box-header with-border">
							<div class="row">
								<div class="col-md-3">
									<h3 class="box-title">Subcategory List</h3>
								</div>
								<div class="col-md-5">
									<div class="input-group">
										<input type="text" id="search" name="search" ng-model="search" class="form-control" placeholder="Search" ng-keyup="$event.keyCode == 13 ? searchSubcategory() : null"/>
										<span class="input-group-btn">
											<button type="button" name="search" id="search-btn" ng-click="searchSubcategory()" class="btn btn-flat"><i class="fa fa-search"></i></button>
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
											<th>Category</th>
											<th>Subcategory Name</th>
											<th>Subcategory Code</th>											
											<th class="text-right">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr class="text-center" ng-if="getSubcategory == ''">
											<td colspan="4"><span class="label label-default" style="font-size: 15px;">Sorry... No data found.</span></td>
										</tr>
										<tr ng-repeat="item in getSubcategory" style="cursor:pointer;cursor:hand">
											<td ng-click="getSubcategoryById(item.realestateSubcategoryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.realestateTypeName}}</td>
											<td ng-click="getSubcategoryById(item.realestateSubcategoryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.subcategoryTitle}}</td>
											<td ng-click="getSubcategoryById(item.realestateSubcategoryId)" title="Edit Record" data-toggle="modal" data-target="#editModal">{{item.subcategoryCode}}</td>											
											<td title="Delete" class="text-right">
												<input type="checkbox" ng-model="item.selected" value="{{item.realestateSubcategoryId}}">
											</td>
										</tr>
									</tbody>
									<tfoot ng-if="getSubcategory != ''">
										<tr>
											<td colspan="3"></td>
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
									<div class="hint-text">Showing <b>{{startindex+1}}-{{getSubcategory.length+startindex}}</b> out of <b>{{allcounts.subcategoryCount}}</b> entries</div>
								</div>
								<div class="col-md-7 text-right">
									<button type="submit" class="btn btn-primary" ng-disabled="currentPage <= 0" ng-click="prev()">
										<i class="fa fa-step-backward"></i>
									</button>
									{{currentPage+1}}
									<button type="submit" class="btn btn-primary" ng-disabled="getSubcategory.length+startindex == allcounts.subcategoryCount" ng-click="next()">
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
						<h4 class="modal-title">Edit Subcategory</h4>
					</div>
					<form ng-submit="editSubcategory(subcategoryid)">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Category <font color="red" size="3">*</font></label>
										<select id="realestatetypeid" name="realestatetypeid" ng-model="realeid" class="form-control" ng-change="setFlag()">
											<option value="">Category</option>
											<option ng-repeat="item in getRealestateName" value="{{item.realestateTypeId}}">{{item.realestateTypeName}}</option>											
										</select>
										<p ng-show="errorCategoryTitle == 1" style="color: red; font-size: 14px;">{{msgCategoryTitle}}</p>									
									</div>									
								</div>								
								<div class="col-md-4">
									<div class="form-group">
										<label>Subcategory Title<font color="red" size="3">*</font></label>
										<input type="text" id="realestatesubtitle" name="realestatesubtitle" ng-model="realestatesubtitle" placeholder="Realestate Subcategory Title" class="form-control" ng-change="setFlag()">
										<p ng-show="errorRealSubTitle == 1" style="color: red; font-size: 14px;">{{msgSubcategoryTitle}}</p>
									</div>									
								</div>								
								<div class="col-md-4">
									<div class="form-group">
										<label>Code<font color="red" size="3">*</font></label>
										<input type="text" id="realestatecode" name="realestatecode" ng-model="realestatecode" placeholder="Code" maxlength="5" class="form-control" ng-change="setFlag()">
										<p ng-show="errorRealSubCode == 1" style="color: red; font-size: 14px;">{{msgSubcategoryCode}}</p>
									</div>									
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Logo</label>
										<input type="file" id="image1" name="image1" ng-model="image1" class="form-control">										
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
									<button type="submit" ng-disabled="subcategorySpin == 1" class="btn btn-success"><i class="fa fa-refresh fa-spin" ng-if="subcategorySpin == 1"></i> Save</button>
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
						<h4 class="modal-title"> <i class="fa fa-trash-o" aria-hidden="true"></i> Delete Subcategory </h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p ng-if="d == 0">Please select at least one record to delete.</p>
						<p ng-if="d != 0">Are you sure to delete selected Record(s)?</p>
						<p class="text-warning" ng-if="d != 0"><small>This action cannot be undone.</small></p>						
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" ng-if="d != 0" class="btn btn-danger" value="Delete" ng-click="deleteSubcategory()">
					</div>
				</div>
			</div>
		</div>		
		<script>
			document.getElementById("master").classList.add("active");
			document.getElementById("subcategory").classList.add("active");
		</script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>		
	</body>
</html>