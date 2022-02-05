	
var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";
app.controller('designationCtrl', [ '$scope', '$filter', '$http', '$window', '$location', '$timeout',
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
	
	var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getDesignation = data;
	}).error(function(data, status, headers, config) {
		$scope.getDesignation = "Response Fail";
	});
	
	$scope.next = function() {
		$scope.search = '';
		if($scope.pageSize == "All") {			
		} else {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;					
			var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDesignation= data;
			}).error(function(data, status, headers, config) {
				$scope.getDesignation = "Response Fail";
			});
		}
	}
	
	$scope.prev = function() {
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		
		var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getDesignation = data;
		}).error(function(data, status, headers, config) {
			$scope.getDesignation= "Response Fail";
		});
	}
	
	$scope.changePage = function() {
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		
		if($scope.pageSize == "All") {
			var link = baseUrl+'getDepartments';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDesignation= data;
			}).error(function(data, status, headers, config) {
				$scope.getDesignation= "Response Fail";
			});
		} else {
			var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDesignation = data;
			}).error(function(data, status, headers, config) {
				$scope.getDesignation= "Response Fail";
			});
		}
	}
	
	$scope.searchDesignation = function() {
		var search = $scope.search;
		if(search == undefined || search == "") {
			var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDesignation= data;
			}).error(function(data, status, headers, config) {
				$scope.getDesignation= "Response Fail";
			});
		} else {
			var link = baseUrl+'searchDesignations?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDesignation= data;
			}).error(function(data, status, headers, config) {
				$scope.getDesignation = "Response Fail";
			});
		}
	}
	
	$scope.setFlag = function() {
		$scope.errorDesignationName = 0;
		$scope.msgDesignationName = "";
		$scope.errorDepartmentcode = 0;
		$scope.msgdepartmentCode = "";
	}
	
	$scope.addDesignation = function() {
				
		if($scope.designationname==undefined || $scope.designationname=="") {			
			$scope.errorDesignationName = 1;
			$scope.msgDesignationName = "Please enter Designation name";
			document.getElementById("designationname").focus();
		} else {
			$scope.spin = 1;		
			var link = baseUrl+'addDesignation?designationname='+$scope.designationname;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.adddesignation = data;				
				$scope.spin = 0;
				if($scope.adddesignation == 'Success'){
					$scope.designationSubmitError = 0;
					$scope.designationSubmitSuccess = 1;
					$scope.successMsg = "Data added";
					$timeout(function(){
						$scope.designationSubmitSuccess = 0;
						$window.location.href = adminurl+'manage_designation';
					}, 2000);
				} 
				else {
					$scope.designationSubmitSuccess = 0;
					$scope.designationSubmitError = 1;
					$scope.errorMsg = $scope.adddesignation;						
				}
			}).error(function(data, status, headers, config) {
				$scope.adddesignation = data;				
				$scope.spin = 0;
				$scope.designationSubmitError = 1;
				$scope.msgError = $scope.adddesignation;
				$timeout(function(){
					$scope.designationSubmitError = 0; 				
				}, 5000);
			});				    			
		}
	}
	
	$scope.getDesignationById = function(designationid) {
		var link = baseUrl+'getDesignationDetailById?designationid='+designationid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getdesignationById = data;
			
			$scope.designationid = $scope.getdesignationById.designationId;
        	$scope.designationname = $scope.getdesignationById.designationName;
		}).error(function(data, status, headers, config) {
			$scope.getdesignationById = "Response Fail";
		});
	}
	
	
	$scope.deleteDesignation = function(designationid) {
		
	    
		var link = baseUrl+'deleteDesignation?designationid='+designationid;
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deletedesignation = data;
			$('#deleteModal').modal('hide');
			var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getDesignation = data;
			}).error(function(data, status, headers, config) {
				$scope.getDesignation= "Response Fail";
			});
		}).error(function(data, status, headers, config) {
			$scope.deletedesignation= "Response Fail";
		});	    
	    
	    
	}
	
	$scope.editDesignation= function(designationid) {
				
		if(!$scope.designationname) {				
			$scope.errorDesignationName = 1;
			$scope.msgDesignationName = "Please enter designation name";
			document.getElementById("designationname").focus();			
		} else {			
			$scope.spin = 1;
			var link = baseUrl+'editDesignation?designationid='+designationid+'&designationname='+$scope.designationname;		
			$http.post(link).success(function(data, status, headers, config) {				
				$scope.editdesignation = data;
				$scope.spin = 0;					
				if($scope.editdesignation == 'Success'){
					var link = baseUrl+'getDesignationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getDesignation = data;
						$scope.designationSubmitError = 0;
						$scope.designationSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.designationSubmitSuccess = 0;
							$('#editModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.getDesignation = "Response Fail";
					});		
					
				} else {
					$scope.designationSubmitSuccess = 0;
					$scope.designationSubmitError = 1;
					$scope.errorMsg = $scope.editdesignation;						
				}
			}).error(function(data, status, headers, config) {
				$scope.editdesignation = data;
				$scope.designationSubmitSuccess = 0;
				$scope.designationSubmitError = 1;
				$scope.errorMsg = $scope.editdesignation;
			});
		}
	}
	
}]);