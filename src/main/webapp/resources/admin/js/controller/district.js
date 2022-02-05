var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('districtCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getDistricts.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		var link = baseUrl+'getCountries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCountries = data;
		}).error(function(data, status, headers, config) {
			$scope.getCountries = "Response Fail";
		});
		
		var link = baseUrl+'getStateByCountryId?countryid='+1;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getStates = data;
		}).error(function(data, status, headers, config) {
			$scope.getStates = "Response Fail";
		});	
		
		$scope.getStateByCountryId = function(countryname) {
			if(countryname == "" || countryname == undefined) {
				$scope.statenameadd = "";
				$scope.statename = "";
				$scope.getStates = "";
			} else {
				var link = baseUrl+'getStateByCountryId?countryid='+countryname;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getDistricts = data;
		}).error(function(data, status, headers, config) {
			$scope.getDistricts = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
			
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;			
			var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDistricts = data;
			}).error(function(data, status, headers, config) {
				$scope.getDistricts = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getDistricts';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			} else {
				var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			}
		}
		
		$scope.searchDistrict = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchDistricts?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCountry = 0;
			$scope.errorState = 0;
			$scope.errorDistrictName = 0;
		}
		
		$scope.addDistrict = function() {			
			if(!$scope.districtcodeadd) {
				$scope.districtcodeadd = "";
			}

			if(!$scope.countrynameadd) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countrynameadd").focus();
			} else if(!$scope.statenameadd) {				
				$scope.errorState = 1;
				$scope.msgState = "Please enter state name";
				document.getElementById("statenameadd").focus();
			} else if(!$scope.districtnameadd) {				
				$scope.errorDistrictName = 1;
				$scope.msgDistrictName = "Please enter district name";
				document.getElementById("districtnameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addDistrict?countryname='+$scope.countrynameadd+'&statename='+$scope.statenameadd+'&districtname='+$scope.districtnameadd+'&districtcode='+$scope.districtcodeadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.adddistrict = data;
					$scope.spin = 0;					
					if($scope.adddistrict == 'Success'){
						$scope.districtSubmitError = 0;
						$scope.districtSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.districtSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_district';
						}, 2000);
					} else {
						$scope.districtSubmitSuccess = 0;
						$scope.districtSubmitError = 1;
						$scope.errorMsg = $scope.adddistrict;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.adddistrict = data;
					$scope.districtSubmitSuccess = 0;
					$scope.districtSubmitError = 1;
					$scope.errorMsg = $scope.adddistrict;
				});
			}
		}
			
		$scope.getDistrict = function(districtid) {
			
			var link = baseUrl+'getDistrictDetailById?districtid='+districtid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.districtDetailById = data;
				
				$scope.districtid = $scope.districtDetailById.districtId;
				$scope.statename = $scope.districtDetailById.stateId;
            	$scope.countryname = $scope.districtDetailById.countryId;
            	$scope.districtname = $scope.districtDetailById.districtName;
            	$scope.districtcode = $scope.districtDetailById.districtCode;
            	
			}).error(function(data, status, headers, config) {
				$scope.districtDetailById = "Response Fail";
			});		
		}
		
		$scope.editDistrict = function(districtid) {
			if(!$scope.districtcode) {
				$scope.districtcode = "";
			}

			if(!$scope.countryname) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countryname").focus();
			} else if(!$scope.statename) {				
				$scope.errorState = 1;
				$scope.msgState = "Please enter state name";
				document.getElementById("statename").focus();
			} else if(!$scope.districtname) {				
				$scope.errorDistrictName = 1;
				$scope.msgDistrictName = "Please enter district name";
				document.getElementById("districtname").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editDistrict?districtid='+districtid+'&countryname='+$scope.countryname+'&statename='+$scope.statename+'&districtname='+$scope.districtname+'&districtcode='+$scope.districtcode;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editdistrict = data;
					$scope.spin = 0;					
					if($scope.editdistrict == 'Success'){
						var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getDistricts = data;
							
							$scope.districtSubmitError = 0;
							$scope.districtSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.districtSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getDistricts = "Response Fail";
						});						
					} else {
						$scope.districtSubmitSuccess = 0;
						$scope.districtSubmitError = 1;
						$scope.errorMsg = $scope.editdistrict;						
					}				
				}).error(function(data, status, headers, config) {
					$scope.editdistrict = data;
					$scope.districtSubmitSuccess = 0;
					$scope.districtSubmitError = 1;
					$scope.errorMsg = $scope.editdistrict;
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getDistricts, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}	
		
		$scope.deleteDistrict = function() {
			angular.forEach($scope.getDistricts, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteDistrict?districtid='+item.districtId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletedistrict = data;
					}).error(function(data, status, headers, config) {
						$scope.deletedistrict = "Response Fail";
					});
				}
				var link = baseUrl+'getDistrictsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			});			
		}	
}]);