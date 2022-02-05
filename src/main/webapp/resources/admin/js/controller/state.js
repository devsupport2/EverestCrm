var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('stateCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getStates.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		var link = baseUrl+'getCountries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCountries = data;
		}).error(function(data, status, headers, config) {
			$scope.getCountries = "Response Fail";
		});
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getStates = data;
		}).error(function(data, status, headers, config) {
			$scope.getStates = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
			
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;			
			var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getStates = data;
			}).error(function(data, status, headers, config) {
				$scope.getStates = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getStates';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			} else {
				var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			}
		}
		
		$scope.searchState = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchStates?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCountry = 0;
			$scope.errorStateName = 0;
		}
		
		$scope.addState = function() {			
			if(!$scope.statecodeadd) {
				$scope.statecodeadd = "";
			}

			if(!$scope.countrynameadd) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countrynameadd").focus();
			} else if(!$scope.statenameadd) {				
				$scope.errorStateName = 1;
				$scope.msgStateName = "Please enter state name";
				document.getElementById("statenameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addState?statename='+$scope.statenameadd+'&statecode='+$scope.statecodeadd+'&countryname='+$scope.countrynameadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addstate = data;
					$scope.spin = 0;					
					if($scope.addstate == 'Success'){
						$scope.stateSubmitError = 0;
						$scope.stateSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.stateSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_state';
						}, 2000);
					} else {
						$scope.stateSubmitSuccess = 0;
						$scope.stateSubmitError = 1;
						$scope.errorMsg = $scope.addstate;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addstate = data;
					$scope.stateSubmitSuccess = 0;
					$scope.stateSubmitError = 1;
					$scope.errorMsg = $scope.addstate;
				});
			}
		}
			
		$scope.getState = function(stateid) {
			for (i in $scope.getStates) {
                if ($scope.getStates[i].stateId == stateid) {
                	$scope.stateid = $scope.getStates[i].stateId;
                	$scope.countryname = $scope.getStates[i].countryId;
                	$scope.statename = $scope.getStates[i].stateName;
                	$scope.statecode = $scope.getStates[i].stateCode;
                }
			}
		}
		
		$scope.editState = function(stateid) {
			if(!$scope.statecode) {
				$scope.statecode = "";
			}

			if(!$scope.countryname) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countryname").focus();
			} else if(!$scope.statename) {				
				$scope.errorStateName = 1;
				$scope.msgStateName = "Please enter state name";
				document.getElementById("statename").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editState?stateid='+stateid+'&statename='+$scope.statename+'&statecode='+$scope.statecode+'&countryname='+$scope.countryname;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editstate = data;
					$scope.spin = 0;					
					if($scope.editstate == 'Success'){
						var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getStates = data;
							
							$scope.stateSubmitError = 0;
							$scope.stateSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.stateSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getStates = "Response Fail";
						});						
					} else {
						$scope.stateSubmitSuccess = 0;
						$scope.stateSubmitError = 1;
						$scope.errorMsg = $scope.editstate;						
					}				
				}).error(function(data, status, headers, config) {
					$scope.editstate = data;
					$scope.stateSubmitSuccess = 0;
					$scope.stateSubmitError = 1;
					$scope.errorMsg = $scope.editstate;
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getStates, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}	
		
		$scope.deleteState = function() {
			angular.forEach($scope.getStates, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteState?stateid='+item.stateId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletestate = data;
					}).error(function(data, status, headers, config) {
						$scope.deletestate = "Response Fail";
					});
				}
			});
			var link = baseUrl+'getStatesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getStates = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getStates = "Response Fail";
			});
		}	
}]);