var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('headerCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout)
	{
		var baseUrl = $location.protocol()+"://"+location.host+url;

		/*var link = baseUrl+'getCategoriesForFrontEnd';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getCategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getCategories = "Response Fail";
				});
		
		var link = baseUrl+'getSubcategories';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.getSubcategories = data;
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSubcategories = "Response Fail";
				});*/
		
		var link = baseUrl+'getCartProduct';
		$http.get(link).success(
				function(data, status, headers, config)
				{
					$scope.cartproduct = data;
					
					$scope.totalamount = 0;
					
					for(i in $scope.cartproduct)
					{
						$scope.totalamount = $scope.totalamount + ($scope.cartproduct[i].orderProductPrice * $scope.cartproduct[i].orderProductQuantity);
					}
				}).
				error(function(data, status, headers, config)
				{
					$scope.cartproduct = "Response Fail";
				});
		
		
		$scope.redirectToCategoryPage = function(categoryid, categoryname)
		{
			var link = baseUrl+'categoryredirect?categoryid='+categoryid;
			$http.post(link).success(
					function(data, status, headers, config)
					{
						$window.location.href = url+"category?cname="+categoryname;
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSubcategories = "Response Fail";
					});
		}
		
		$scope.checkFrontLogin = function()
		{
			var email = $scope.emailheader;
			var password = $scope.passwordheader;

			if (email == undefined || email == "")
			{
				$window.alert("Please enter email address");
				document.getElementById("emailheader").focus();
				return;
			}
			else if (password == undefined || password == "")
			{
				$window.alert("Please enter password");
				document.getElementById("passwordheader").focus();
				return;
			}
			else
			{
				var link = baseUrl + 'checkFrontLogin?email=' + email + '&password=' + password;
				$http.post(link).success(function(data, status, headers,config) 
				{
					if (data == "")
					{
						$window.alert("Email or Password Incorrect...Try Again");
					}
					else
					{
						$window.location.href = url;				
					}
				}).error(function(data, status, headers,config)
				{
					$window.alert("Some thing wrong...Try again");
				});
			}
		}
		
		$scope.forgotPassword = function()
		{
			var email = $scope.emailheader;
			
			if(email==undefined || email=="")
			{
				$window.alert("Please enter email address!");
				document.getElementById("emailheader").focus();
				return;
			}
			else
			{
				var a = 0;
				$scope.spin = 1;
				
				var link = baseUrl+'getUsers';
				$http.get(link).success(
						function(data, status, headers, config)
						{
							$scope.users = data;
							
							for(i in $scope.users)
							{
								if($scope.users[i].email == email)
								{
									a = 1;
									$scope.passwordx = $scope.users[i].password;
									$scope.firstnamex = $scope.users[i].firstName;
									$scope.lastnamex = $scope.users[i].lastName;
								}
							}
							
							if(a == 1)
							{
								$scope.x = 1;

								var link = baseUrl+'forgotPassword?email='+email+'&password1='+$scope.passwordx+'&firstname='+$scope.firstnamex+'&lastname='+$scope.lastnamex;
								$http.post(link).success(
										function(data, status, headers, config)
										{
											$scope.forgotpassword = data;
											$scope.x = 0;
											$scope.spin = 0;
											$window.alert("Password Send To Your Registered Email Id...");
											$window.location.href = url;
										}).
										error(function(data, status, headers, config)
										{
											$scope.forgotpassword = "Response Fail";
										});
							}
							else
							{
								$window.alert("Email address is not registered with us! Please Enter Another Email Address!");
							}
							
						}).
						error(function(data, status, headers, config)
						{
							$scope.users = "Response Fail";
						});
			}
		}
		
		
		
		
		
		
		
}]);