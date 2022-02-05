var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('rangeCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.currentPage = 0;
	$scope.pageSize = 20;
	$scope.search = '';
	$scope.startindex = $scope.currentPage;	    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    
    
    /*var link = baseUrl+'getRangeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;    
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getRange = data;
	}).error(function(data, status, headers, config) {
		$scope.getRange = "Response Fail";
	});*/
	
	var link = baseUrl+'getMeasurementUnits';    
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getMeasurementUnits = data;
	}).error(function(data, status, headers, config) {
		$scope.getMeasurementUnits = "Response Fail";
	});
	
	var link = baseUrl+'getAllRanges';    
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getRange = data;
	}).error(function(data, status, headers, config) {
		$scope.getRange = "Response Fail";
	});
	
	$scope.setFlag = function(){
		$scope.errorRangeFrom = 0;
		$scope.errorRangeTo = 0;
		$scope.errorUnitId = 0;
	}
	
	$scope.addRange = function(){
		if(!$scope.description){
			$scope.description = "";
		}
		if(!$scope.rangefrom){
			$scope.errorRangeFrom = 1;
			$scope.msgRangeFrom = "Please enter range from";
			document.getElementById("rangefrom").focus();
		} else if(!$scope.rangeto){
			$scope.errorRangeTo = 1;
			$scope.msgRangeTo = "Please enter range to";
			document.getElementById("rangeto").focus();
		} else if(!$scope.unitid){
			$scope.errorUnitId = 1;
			$scope.msgUnitId = "Please select unit";
			document.getElementById("unitid").focus();
		} else {
			$scope.spin = 1;
			var link = baseUrl+'addRange?rangefrom='+$scope.rangefrom+'&rangeto='+$scope.rangeto+'&unitid='+$scope.unitid+'&description='+$scope.description;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addrange = data;
				$scope.spin = 0;
				if($scope.addrange == 'Success'){
					$scope.rangeSubmitError = 0;
					$scope.rangeSubmitSuccess = 1;
					$scope.successMsg = "Data added";
					$timeout(function(){
						$scope.rangeSubmitSuccess = 0;
						$window.location.href = adminurl+'manage_range';
					}, 2000);
				} else {
					$scope.rangeSubmitSuccess = 0;
					$scope.rangeSubmitError = 1;
					$scope.errorMsg = $scope.addrange;						
				}
				
			}).error(function(data, status, headers, config) {	
				$scope.spin = 0;
				$scope.rangeSubmitSuccess = 0;
				$scope.rangeSubmitError = 1;
				$scope.errorMsg = "Something went wrong!";	
			});
		}
		
		
	}
	
	$scope.getRangeDetails = function(rangeid){
		var link = baseUrl+'getRangeDetailById?rangeid='+rangeid;    
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getRangeDetails = data;
			
			$scope.rangeid = $scope.getRangeDetails.rangeId;
			$scope.rangefromedit = $scope.getRangeDetails.rangeFrom;
			$scope.rangetoedit = $scope.getRangeDetails.rangeTo;
			$scope.unittype = $scope.getRangeDetails.unitId;
			$scope.descriptionedit = $scope.getRangeDetails.description;
			
		}).error(function(data, status, headers, config) {
			$scope.getRangeDetails = "Response Fail";
		});
	}
		
		$scope.editRange = function(rangeid) {
			if(!$scope.descriptionedit){
				$scope.descriptionedit = "";
			}
			
			if(!$scope.rangefromedit){
				$scope.errorRangeFrom = 1;
				$scope.msgRangeFrom = "Please enter range from";
				document.getElementById("rangefromedit").focus();
			} else if(!$scope.rangetoedit){
				$scope.errorRangeTo = 1;
				$scope.msgRangeTo = "Please enter range to";
				document.getElementById("rangetoedit").focus();
			} else if(!$scope.unittype){
				$scope.errorUnitId = 1;
				$scope.msgUnitId = "Please select unit";
				document.getElementById("unittype").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editRange?rangeid='+rangeid+'&rangefrom='+$scope.rangefromedit+'&rangeto='+$scope.rangetoedit+'&unitid='+$scope.unittype+'&description='+$scope.descriptionedit;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editrange = data;
					$scope.spin = 0;
					if($scope.editrange == 'Success'){
						$scope.rangeSubmitError = 0;
						$scope.rangeSubmitSuccess = 1;
						$scope.successMsg = "Data Updated";
						$timeout(function(){
							$scope.rangeSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_range';
						}, 2000);
					} else {
						$scope.rangeSubmitSuccess = 0;
						$scope.rangeSubmitError = 1;
						$scope.errorMsg = $scope.editrange;						
					}
					
				}).error(function(data, status, headers, config) {	
					$scope.spin = 0;
					$scope.rangeSubmitSuccess = 0;
					$scope.rangeSubmitError = 1;
					$scope.errorMsg = "Something went wrong!";	
				});	    
			}
		}
		
		$scope.deleteRange = function(rangeid) {
			var link = baseUrl+'deleteRange?rangeid='+rangeid;
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleterange = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.deleterange= "Response Fail";
			});	    
		    
			var link = baseUrl+'getAllRanges';    
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getRange = data;
			}).error(function(data, status, headers, config) {
				$scope.getRange = "Response Fail";
			});
		}
		
	
	}]);