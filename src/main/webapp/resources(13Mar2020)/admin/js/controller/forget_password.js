var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('forgetPasswordCtrl', function($scope, $http, $window, $filter, $location, $timeout) {
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.setFlag = function() {
		$scope.errorEmail = 0;
		$scope.msgEmail = "";
		$scope.submitSuccess = 0;
		$scope.submitError = 0;
	}
	
	$scope.forgetPassword = function() {	
		if(!$scope.email) {			
			$scope.errorEmail = 1;
			$scope.msgEmail = "Please enter your registered email";
			document.getElementById("email").focus();
		}  else {
			$scope.spin=1;		
			
			var link = baseUrl+'forgetPassword?email='+$scope.email;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.forgetpassword = data;
				
				if($scope.forgetpassword == "Success"){
					$scope.spin = 0;
					$scope.submitSuccess=1;
					$scope.msgSuccess="Please check your email. We have sent you an email for reset your password.";					
				} else {
					$scope.spin = 0;
					$scope.submitError=1;
					$scope.msgError="Sorry! Email not found! Please register with us.";
				}				
			}).error(function(data, status, headers, config) {
				$scope.spin = 0;
				$scope.submitError=1;
				$scope.msgError="Sorry! Something went wrong! Please try after sometime.";
			});
		}
	}
	
});