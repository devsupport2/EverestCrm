var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('measurementUnitCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getMeasurementUnits.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMeasurementUnits = data;
		}).error(function(data, status, headers, config) {
			$scope.getMeasurementUnits = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMeasurementUnits = data;
				}).error(function(data, status, headers, config) {
					$scope.getMeasurementUnits = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMeasurementUnits = data;
			}).error(function(data, status, headers, config) {
				$scope.getMeasurementUnits = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getMeasurementUnits';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMeasurementUnits = data;
				}).error(function(data, status, headers, config) {
					$scope.getMeasurementUnits = "Response Fail";
				});
			} else {
				var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMeasurementUnits = data;
				}).error(function(data, status, headers, config) {
					$scope.getMeasurementUnits = "Response Fail";
				});
			}
		}
		
		$scope.searchMeasurementUnit = function() {
						
			if(!$scope.search) {
				var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMeasurementUnits = data;
				}).error(function(data, status, headers, config) {
					$scope.getMeasurementUnits = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchMeasurementUnits?keyword='+$scope.search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getMeasurementUnits = data;
				}).error(function(data, status, headers, config) {
					$scope.getMeasurementUnits = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorUnitName = 0;						
		}
		
		$scope.addMeasurementUnit = function() {					
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.measurementunitnameadd) {				
				$scope.errorUnitName = 1;
				$scope.msgUnitName = "Please enter measurement unit name";
				document.getElementById("measurementunitnameadd").focus();
			} else {
				$scope.spin = 1;
				
				$scope.measurementunitnameadd = tools_replaceAll($scope.measurementunitnameadd, "&" , "$" );
				$scope.measurementunitnameadd = tools_replaceAll($scope.measurementunitnameadd, "#" , "~" );
				$scope.measurementunitnameadd = tools_replaceAll($scope.measurementunitnameadd, "%" , "!" );				
				
				var link = baseUrl+'addMeasurementUnit?measurementunitname='+$scope.measurementunitnameadd+'&description='+$scope.descriptionadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addmeasurementunit = data;
					$scope.spin = 0;
					
					if($scope.addmeasurementunit == 'Success'){
						$scope.unitSubmitError = 0;
						$scope.unitSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.unitSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_measurement_unit';
						}, 2000);
					} else {
						$scope.unitSubmitSuccess = 0;
						$scope.unitSubmitError = 1;
						$scope.errorMsg = $scope.addmeasurementunit;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addmeasurementunit = data;
					$scope.unitSubmitSuccess = 0;
					$scope.unitSubmitError = 1;
					$scope.errorMsg = $scope.addmeasurementunit;	
				});
			}
		}
			
		$scope.getMeasurementUnit = function(measurementunitid) {
			for (i in $scope.getMeasurementUnits) {
                if ($scope.getMeasurementUnits[i].measurementUnitId == measurementunitid) {
                	$scope.measurementunitid = $scope.getMeasurementUnits[i].measurementUnitId;
                	$scope.measurementunitname = $scope.getMeasurementUnits[i].measurementUnitName;
                	$scope.description = $scope.getMeasurementUnits[i].description;
                }
			}
		}
		
		$scope.editMeasurementUnit = function(measurementunitid) {
			if(!$scope.description) {
				$scope.description = "";
			}
			if(!$scope.measurementunitname) {				
				$scope.errorUnitName = 1;
				$scope.msgUnitName = "Please enter measurement unit name";
				document.getElementById("measurementunitname").focus();
			} else {
				$scope.spin = 1;
				
				$scope.measurementunitname = tools_replaceAll($scope.measurementunitname, "&" , "$" );
				$scope.measurementunitname = tools_replaceAll($scope.measurementunitname, "#" , "~" );
				$scope.measurementunitname = tools_replaceAll($scope.measurementunitname, "%" , "!" );
				
				var link = baseUrl+'editMeasurementUnit?measurementunitid='+measurementunitid+'&measurementunitname='+$scope.measurementunitname+'&description='+$scope.description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editmeasurementunit = data;
					$scope.spin = 0;
					if($scope.editmeasurementunit == 'Success'){						
						var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getMeasurementUnits = data;
							$scope.unitSubmitError = 0;
							$scope.unitSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.unitSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
						}).error(function(data, status, headers, config) {
							$scope.getMeasurementUnits = "Response Fail";
						});					
					} else {
						$scope.unitSubmitSuccess = 0;
						$scope.unitSubmitError = 1;
						$scope.errorMsg = $scope.editmeasurementunit;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.editmeasurementunit = data;
					$scope.unitSubmitSuccess = 0;
					$scope.unitSubmitError = 1;
					$scope.errorMsg = $scope.editmeasurementunit;
				});
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;			
			angular.forEach($scope.getMeasurementUnits, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteMeasurementUnit = function() {
			angular.forEach($scope.getMeasurementUnits, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteMeasurementUnit?measurementunitid='+item.measurementUnitId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletemeasurementunit = data;
					}).error(function(data, status, headers, config) {
						$scope.deletemeasurementunit = "Response Fail";
					});
				}
			});
			var link = baseUrl+'getMeasurementUnitsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getMeasurementUnits = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getMeasurementUnits = "Response Fail";
			});
		}		
}]);