var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('countryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getCountries.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCountries = data;
		}).error(function(data, status, headers, config) {
			$scope.getCountries = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCountries = data;
				}).error(function(data, status, headers, config) {
					$scope.getCountries = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCountries = data;
			}).error(function(data, status, headers, config) {
				$scope.getCountries = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getCountries';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCountries = data;
				}).error(function(data, status, headers, config) {
					$scope.getCountries = "Response Fail";
				});
			} else {
				var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCountries = data;
				}).error(function(data, status, headers, config) {
					$scope.getCountries = "Response Fail";
				});
			}
		}
		
		$scope.searchCountry = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCountries = data;
				}).error(function(data, status, headers, config) {
					$scope.getCountries = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchCountries?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCountries = data;
				}).error(function(data, status, headers, config) {
					$scope.getCountries = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCountryName = 0;						
		}
		
		$scope.addCountry = function() {			
			if(!$scope.countrycodeadd) {
				$scope.countrycodeadd = "";
			}			
			if(!$scope.countrydialingcodeadd) {
				$scope.countrydialingcodeadd = "";
			}
			if(!$scope.countrynameadd) {				
				$scope.errorCountryName = 1;
				$scope.msgCountryName = "Please enter country name";
				document.getElementById("countrynameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addCountry?countryname='+$scope.countrynameadd+'&countrycode='+$scope.countrycodeadd+'&countrydialingcode='+$scope.countrydialingcodeadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addcountry = data;
					$scope.spin = 0;
					if($scope.addcountry == 'Success'){
						$scope.countrySubmitError = 0;
						$scope.countrySubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.countrySubmitSuccess = 0;
							$window.location.href = adminurl+'manage_country';
						}, 2000);
					} else {
						$scope.countrySubmitSuccess = 0;
						$scope.countrySubmitError = 1;
						$scope.errorMsg = $scope.addcountry;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addcountry = data;
					$scope.countrySubmitSuccess = 0;
					$scope.countrySubmitError = 1;
					$scope.errorMsg = $scope.addcountry;	
				});
			}
		}
			
		$scope.getCountry = function(countryid) {			
			var link = baseUrl+'getCountryDetailById?countryid='+countryid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.countryById = data;
				
				$scope.countryid = $scope.countryById.countryId;
            	$scope.countryname = $scope.countryById.countryName;
            	$scope.countrycode = $scope.countryById.countryCode;
            	$scope.countrydialingcode = $scope.countryById.countryDialingCode;
            	
			}).error(function(data, status, headers, config) {
				$scope.countryById = "Response Fail";
			});	
		}
		
		$scope.editCountry = function(countryid) {
			if(!$scope.countrycode) {
				$scope.countrycode = "";
			}			
			if(!$scope.countrydialingcode) {
				$scope.countrydialingcode = "";
			}
			if(!$scope.countryname) {				
				$scope.errorCountryName = 1;
				$scope.msgCountryName = "Please enter country name";
				document.getElementById("countryname").focus();
			} else {
				$scope.spin = 1;
				
				var link = baseUrl+'editCountry?countryid='+countryid+'&countryname='+$scope.countryname+'&countrycode='+$scope.countrycode+'&countrydialingcode='+$scope.countrydialingcode;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editcountry = data;
					$scope.spin = 0;					
					if($scope.editcountry == 'Success'){
						
						var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getCountries = data;
							$scope.countrySubmitError = 0;
							$scope.countrySubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.countrySubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
						}).error(function(data, status, headers, config) {
							$scope.getCountries = "Response Fail";
						});		
						
					} else {
						$scope.countrySubmitSuccess = 0;
						$scope.countrySubmitError = 1;
						$scope.errorMsg = $scope.editcountry;						
					}
				}).error(function(data, status, headers, config) {
					$scope.editcountry = data;
					$scope.countrySubmitSuccess = 0;
					$scope.countrySubmitError = 1;
					$scope.errorMsg = $scope.editcountry;
				});
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getCountries, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteCountry = function() {		
		    angular.forEach($scope.getCountries, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteCountry?countryid='+item.countryId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletecountry = data;
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletecountry = "Response Fail";
		    		});
		    	}
		    });
		    
		    var link = baseUrl+'getCountriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCountries = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getCountries = "Response Fail";
			});
		}		
}]);