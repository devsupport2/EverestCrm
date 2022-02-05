<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="icon" href="<%=request.getContextPath() %>/resources/admin/images/favicon.ico" type="image/ico" />	
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/AdminLTE.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/blue.css">
		<script src="<%=request.getContextPath() %>/resources/admin/js/angular.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/conf.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/controller/login.js"></script>						
	</head>
	<body class="login-page" ng-app="MyApp" ng-controller="loginCtrl" ng-cloak>
		<div class="login-box">			
			<div class="login-box-body">
				<div class="login-logo">
					<%-- <img class="img-responsive" style="display: unset;" src="<%=request.getContextPath() %>/resources/admin/images/Logo.png" alt="The Great Stuff. Co."> --%>
					<label>Everest Group</label>
				</div>
				<p class="login-box-msg">Sign in to start your session</p>
				<form ng-submit="checkLogin()">
					<div class="form-group has-feedback">
						<input type="email" id="email" name="email" ng-model="email" class="form-control" placeholder="Email" autofocus>
						<span class="fa fa-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" id="password" name="password" ng-model="password" class="form-control" placeholder="Password">
						<span class="fa fa-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<!-- <div class="col-xs-8">
							<div class="checkbox icheck">
								<label>
									<input type="checkbox"> &nbsp;Remember Me
								</label>
							</div>
						</div> -->
						<div class="col-xs-12 form-group">
							<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
						</div>
						<div class="col-xs-12">
							<a href="<%=request.getContextPath() %>/forget_password">Forgot Password?</a>
						</div>
					</div><br>
					<div class="alert alert-success" ng-show="success == 1">
						<strong><span class="fa fa-check-circle"></span> {{message}}</strong>
					</div>
					<div class="alert alert-danger" ng-show="fail == 1">
						<strong><span class="fa fa-times-circle"></span> {{message}}</strong>
					</div>
					<div class="alert alert-info" ng-show="info == 1">
						<strong><span class="fa fa-info-circle"></span> {{message}}</strong>
					</div>
				</form>
				<!-- <a href="#">I forgot my password</a><br>
				<a href="register.html" class="text-center">Register a new membership</a> -->
			</div>
		</div>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/jQuery-2.1.4.min.js"></script>		
		<script src="<%=request.getContextPath() %>/resources/admin/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/admin/js/icheck.min.js"></script>
		<script>
			$(function () {
				$('input').iCheck({
					checkboxClass: 'icheckbox_square-blue',
					radioClass: 'iradio_square-blue',
					increaseArea: '20%' // optional
				});
			});
		</script>
	</body>	
</html>

