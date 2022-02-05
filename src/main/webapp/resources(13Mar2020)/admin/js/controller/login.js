var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('loginCtrl', function($scope, $http, $window, $filter, $location, $timeout) {
			var baseUrl = $location.protocol()+"://"+location.host+url;
			
			$scope.info = 0;
			$scope.success = 0;
			$scope.fail = 0;
												
			$scope.checkLogin = function()
			{
				var email = $scope.email;
				var password = $scope.password;
							
				if(email==undefined || email=="")
				{
					document.getElementById("email").focus();
					$scope.info = 1;
					$scope.message = "Please Enter Email Id.";
					$timeout(function(){
						$scope.info = 0;
					}, 2000);
				}
				else if(password==undefined || password=="")
				{
					document.getElementById("password").focus();
					$scope.info = 1;
					$scope.message = "Please Enter Password.";
					$timeout(function(){
						$scope.info = 0;
					}, 2000);
				}
				else
				{
					$scope.info = 1;
					$scope.message = "Validating... Please Wait...";
					
					var link = baseUrl+'checkAdminLogin?email='+email+'&password='+password;
					$http.post(link).success(
							function(data, status, headers, config)
							{
								$scope.checkadminlogin = data;

								if($scope.checkadminlogin.length != 0)
								{
									$scope.info = 0;
									$scope.success = 1;
									$scope.message = "Login Successful.";
									$timeout(function(){
										$scope.success = 0;
										$window.location.href = adminurl+'home';
									}, 3000);
								}
								else
								{
									$scope.info = 0;
									$scope.fail = 1;
									$scope.message = "Invalid Username or Password.";
									$timeout(function(){
										$scope.fail = 0;
									}, 3000);
								}
							}).
							error(function(data, status, headers, config)
							{
								$scope.checkadminlogin = "Response Fail";
							});
				}
			}
		});