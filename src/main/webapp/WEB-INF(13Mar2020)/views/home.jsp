<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">		
		<title> Dashboard </title>		
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.common-material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.min.css" />
		<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.3.1026/styles/kendo.material.mobile.min.css" />				
	</head>
	<style>
		.info-box-icon {
		    height: 62px !important;
		    width: 90px;
		    text-align: center;
		    font-size: 45px;
		    line-height: 64px  !important;
		    background: rgba(0,0,0,0.2);
		}
		.info-box {
		    min-height: 62px  !important;
		    background: #fff;
		    width: 100%;
		    box-shadow: 0 1px 1px rgba(0,0,0,0.1);
		    border-radius: 2px;
		    margin-bottom: 15px;
		}
	</style>
	<body ng-app="MyApp" ng-controller="dashboardCtrl" ng-cloak ng-init="getDashbroadDetails()" class="skin-blue sidebar-mini">
		<div class="wrapper">		
			<%@include file="header.jsp" %>
			<%@include file="sidebar.jsp" %>
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						Admin Dashboard						
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
						<li class="active">Dashboard</li>
					</ol>
				</section>
				<section class="content">
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12" ng-repeat="p in getDashBoard.productList">
							<div class="info-box" >
								<span style="background:white;" ng-if="p.projectLogo=='undefined'" class="info-box-icon"><i style="color:#872175;" class="ion ion-ios-gear-outline"></i></span>
								<a href="<%=request.getContextPath() %>/manage_project?projectid={{p.projectId}}"><span style="background:white;" ng-if="p.projectLogo !='undefined'" class="info-box-icon"><img style="max-width: 69%;" class="img-responsive" src="{{p.projectLogo}}"></span></a>
								<div class="info-box-content">
									<span class="info-box-text">{{p.projectTitle}}</span>
									<a href="<%=request.getContextPath() %>/manage_enquiry?projectname={{p.projectTitle}}"><span class="info-box-number"> {{p.projectEnquiryCount}}</span></a>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="box box-default chart ">
										<div class="box-header with-border">
											<h3 class="box-title"><strong> Inquiries </strong> </h3>
										</div>
										<div class="box-body">
											<div class="row">
												<div class="col-md-9">
													<div class="chart-responsive mb-15 mt-15">
														<canvas id="pie" class="chart chart-pie" chart-data="data" chart-options="options" chart-labels="labels"></canvas>
													</div>
												</div>
												<div class="col-md-3">
													<ul class="chart-legend clearfix" style="margin-left: -60px; margin-top: 10px;">
														<li><i class="fa fa-circle-o text-yellow"></i> Total : {{totalCount}} </li>
														<li><i class="fa fa-circle-o text-black"></i> New : {{allcounts.newEnquiryCount}} </li>
														<li><i class="fa fa-circle-o text-blue"></i> Working : {{allcounts.workingEnquiryCount}} </li>
														<li><i class="fa fa-circle-o text-orange"></i> Hold : {{allcounts.holdEnquiryCount}} </li>
														<li><i class="fa fa-circle-o text-green"></i> Won : {{allcounts.wonEnquiryCount}} </li>
														<li><i class="fa fa-circle-o text-red"></i> Lost : {{allcounts.lostEnquiryCount}} </li>
														<li><i class="fa fa-circle-o text-aqua"></i> Closed : {{allcounts.closeEnquiryCount}} </li>
													</ul>
												</div>
											</div>
										</div>
										<div class="box-footer text-center">
											<a href="<%=request.getContextPath() %>/manage_enquiry" class="uppercase">View All Inquiries</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="box box-default chart ">
										<div class="box-header with-border">
											<h3 class="box-title"><strong>Country Wise Inquiries </strong> </h3>
										</div>
										<div class="box-body">
											<div class="row">
												<div class="col-md-9">
													<div class="chart-responsive mb-15 mt-15">
														<canvas id="pie" class="chart chart-pie" chart-data="enquirydata" chart-options="options" chart-labels="enquirylabels"></canvas>
													</div>
												</div>
												<div class="col-md-3">
													<ul class="chart-legend clearfix" style="margin-left: -60px; margin-top: 10px;">
														<li ng-repeat="ecw in getDashBoard.enquiryCountryWiseList"><i class="fa fa-circle-o"></i> {{ecw.countryName}} : {{ecw.countryEnquiryCount}} </li>
													</ul>
												</div>
											</div>
										</div>
										<div class="box-footer text-center">
											<a href="<%=request.getContextPath() %>/manage_enquiry" class="uppercase">View All Inquiries</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="box box-default chart ">
										<div class="box-header with-border">
											<h3 class="box-title"><strong>Project Work Status </strong> </h3>
										</div>
										<div class="box-body">
											<div class="row">
												<div class="col-md-9">
													<div class="chart-responsive mb-15 mt-15">
														<canvas id="pie" class="chart chart-pie" chart-data="projectdata" chart-options="options" chart-labels="projectlabels"></canvas>
													</div>
												</div>
												<div class="col-md-3">
													<ul class="chart-legend clearfix" style="margin-left: -60px; margin-top: 10px;">
														<li><i class="fa fa-circle-o"></i> Ongoing : {{allcounts.ongoingProjectCount}} </li>
														<li><i class="fa fa-circle-o"></i> Completed : {{allcounts.completedProjectCount}} </li>
													</ul>
												</div>
											</div>
										</div>
										<div class="box-footer text-center">
											<a href="<%=request.getContextPath() %>/manage_project" class="uppercase">View All Projects</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			</section>					
			</div>				
		</div>	
		<script>
			document.getElementById("manage").classList.add("active");				
		</script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/dashboard.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="https://kendo.cdn.telerik.com/2017.3.1026/js/kendo.all.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/app.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/jquery.slimscroll.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular-chart.min.js"></script>
	</body>
</html>