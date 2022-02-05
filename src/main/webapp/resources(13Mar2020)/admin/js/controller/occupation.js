	
var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";
app.controller('occupationCtrl', [ '$scope', '$filter', '$http', '$window', '$location', '$timeout',
	function($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	$scope.currentPage = 0;
	$scope.pageSize = 20;
	$scope.search = '';
	$scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    $scope.spin = 0;
    
    $scope.numberOfPages=function() {
		return Math.ceil($scope.getDepartments.length/$scope.pageSize);
	}
	
    var baseUrl = $location.protocol()+"://"+location.host+url;

	var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
	}).error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});
	
	var link = baseUrl+'getOccupationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getOccupations = data;
	}).error(function(data, status, headers, config) {
		$scope.getOccupations = "Response Fail";
	});
	
	$scope.next = function() {
		$scope.search = '';
		if($scope.pageSize == "All") {
				
		} else {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;					
			var link = baseUrl+'getOccupationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOccupations= data;
			}).error(function(data, status, headers, config) {
				$scope.getOccupations = "Response Fail";
			});
		}
	}
	
	$scope.prev = function() {
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		
		var link = baseUrl+'getOccupationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getOccupations = data;
		}).error(function(data, status, headers, config) {
			$scope.getOccupations= "Response Fail";
		});
	}
	
	$scope.changePage = function() {
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		
		if($scope.pageSize == "All") {
			var link = baseUrl+'getDepartments';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOccupations= data;
			}).error(function(data, status, headers, config) {
				$scope.getOccupations= "Response Fail";
			});
		} else {
			var link = baseUrl+'getOccupationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOccupations = data;
			}).error(function(data, status, headers, config) {
				$scope.getOccupations= "Response Fail";
			});
		}
	}
	
	$scope.searchDepartment = function() {
		var search = $scope.search;
		if(search == undefined || search == "") {
			var link = baseUrl+'getOccupationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOccupations= data;
			}).error(function(data, status, headers, config) {
				$scope.getOccupations= "Response Fail";
			});
		} else {
			var link = baseUrl+'searchDepartments?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getOccupations= data;
			}).error(function(data, status, headers, config) {
				$scope.getOccupations = "Response Fail";
			});
		}
	}
	
	$scope.setFlag = function() {
		$scope.errorOccupationName = 0;
		$scope.msgOccupationName = "";
		$scope.errorDepartmentcode = 0;
		$scope.msgdepartmentCode = "";
	}
	
	$scope.addOccupation = function() {
				
		if($scope.occupationname==undefined || $scope.occupationname=="") {			
			$scope.errorOccupationName = 1;
			$scope.msgOccupationName = "Please enter Occupation name";
			document.getElementById("occupationname").focus();
		} else {
			$scope.spin = 1;		
			var link = baseUrl+'addOccupation?occupationname='+$scope.occupationname;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addoccuptaion = data;				
				$scope.spin = 0;
				if($scope.addoccuptaion == 'Success'){
					$scope.occuptaionSubmitError = 0;
					$scope.occuptaionSubmitSuccess = 1;
					$scope.successMsg = "Data added";
					$timeout(function(){
						$scope.occuptaionSubmitSuccess = 0;
						$window.location.href = adminurl+'manage_occupation';
					}, 2000);
				} 
				else {
					$scope.occuptaionSubmitSuccess = 0;
					$scope.occuptaionSubmitError = 1;
					$scope.errorMsg = $scope.addoccuptaion;						
				}
			}).error(function(data, status, headers, config) {
				$scope.addoccuptaion = data;				
				$scope.spin = 0;
				$scope.occuptaionSubmitError = 1;
				$scope.msgError = $scope.addoccuptaion;
				$timeout(function(){
					$scope.occuptaionSubmitError = 0; 				
				}, 5000);
			});				    			
		}
	}
	
	$scope.getOccupationById = function(occupationid) {
		var link = baseUrl+'getOccupationDetailById?occupationid='+occupationid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getoccupationById = data;
			
			$scope.occupationid = $scope.getoccupationById.occupationId;
        	$scope.occupationname = $scope.getoccupationById.occupationName;
		}).error(function(data, status, headers, config) {
			$scope.getoccupationById = "Response Fail";
		});
	}
	
	
	$scope.deleteOccupation = function(occupationid) {
	    
		var link = baseUrl+'deleteOccupation?occupationid='+occupationid;
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deleteoccupation = data;
			$('#deleteModal').modal('hide');
		}).error(function(data, status, headers, config) {
			$scope.deleteoccupation= "Response Fail";
		});	    
	    
	    $scope.changePage();
	    var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
	}
	
	$scope.editOccupation= function(occupationid) {
				
		if(!$scope.occupationname) {				
			$scope.errorOccupationName = 1;
			$scope.msgOccupationName = "Please enter occupation name";
			document.getElementById("occupationname").focus();
		} else {
			$scope.spin = 1;
			var link = baseUrl+'editOccupation?occupationid='+occupationid+'&occupationname='+$scope.occupationname;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editoccupation = data;
				$scope.spin = 0;					
				if($scope.editoccupation == 'Success'){
					
					var link = baseUrl+'getOccupationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getOccupations = data;
						$scope.occupationSubmitError = 0;
						$scope.occupationSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.occupationSubmitSuccess = 0;
							$('#editModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.getOccupations = "Response Fail";
					});		
					
				} else {
					$scope.occupationSubmitSuccess = 0;
					$scope.occupationSubmitError = 1;
					$scope.errorMsg = $scope.editoccupation;						
				}
			}).error(function(data, status, headers, config) {
				$scope.editoccupation = data;
				$scope.occupationSubmitSuccess = 0;
				$scope.occupationSubmitError = 1;
				$scope.errorMsg = $scope.editoccupation;
			});
		}
	}
	
}]);