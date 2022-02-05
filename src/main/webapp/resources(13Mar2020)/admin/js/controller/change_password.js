var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('changePasswordCtrl', function($scope, $http, $window, $filter, $location, $timeout) {
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.setFlag = function() {
		$scope.errorCurrentPassword = 0;
		$scope.msgCurrentPassword = "";
		$scope.errorNewPassword = 0;
		$scope.msgNewPassword = "";
		$scope.errorConfirmPassword = 0;
		$scope.msgConfirmPassword = "";
		$scope.submitSuccess = 0;
		$scope.submitError = 0;
	}
	
	$scope.changePassword = function(userid) {		
		if(!$scope.currentpassword) {			
			$scope.errorCurrentPassword = 1;
			$scope.msgCurrentPassword = "Please enter your current password";
			document.getElementById("currentpassword").focus();
		} else if(!$scope.newpassword){
			$scope.errorNewPassword = 1;
			$scope.msgNewPassword = "Please enter your new password";
			document.getElementById("newpassword").focus();
		} else if(!$scope.confirmpassword){
			$scope.errorConfirmPassword = 1;
			$scope.msgConfirmPassword = "Please re-enter your new password";
			document.getElementById("confirmpassword").focus();
		} else if($scope.confirmpassword != $scope.newpassword){
			$scope.errorConfirmPassword = 1;
			$scope.msgConfirmPassword = "Password not matched!";
			document.getElementById("confirmpassword").focus();
		} else {
			$scope.spin=1;			
			var link = baseUrl+'changePassword?userid='+userid+'&currentpassword='+$scope.currentpassword+'&newpassword='+$scope.newpassword;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.resetpassword = data;
				
				if($scope.resetpassword == "Success"){
					$scope.spin = 0;
					$scope.submitSuccess=1;
					$scope.msgSuccess="Your password has been change! Please login to continue.";
					$timeout(function(){
						$scope.submitSuccess = 0;
						$window.location.href = adminurl+"logoutadmin";
					}, 2000);
				} else {
					$scope.spin = 0;
					$scope.submitError=1;
					$scope.msgError="Oops... It seems like you are not registred with us! Please register with us or contact support team.";
				}				
			}).error(function(data, status, headers, config) {
				$scope.spin = 0;
				$scope.submitError=1;
				$scope.msgError="Sorry! Something went wrong! Please try after sometime.";
			});
		}
	}
	
});