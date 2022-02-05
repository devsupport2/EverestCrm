var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('financialYearCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getFinancialYear.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getFinancialYears = data;
		}).error(function(data, status, headers, config) {
			$scope.getFinancialYears = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getFinancialYears = data;
				}).error(function(data, status, headers, config) {
					$scope.getFinancialYears = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;			
			var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFinancialYears = data;
			}).error(function(data, status, headers, config) {
				$scope.getFinancialYears = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getFinancialYears';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getFinancialYears = data;
				}).error(function(data, status, headers, config) {
					$scope.getFinancialYears = "Response Fail";
				});
			} else {
				var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getFinancialYears = data;
				}).error(function(data, status, headers, config) {
					$scope.getFinancialYears = "Response Fail";
				});
			}
		}
		
		$scope.searchFinancialYear = function() {			
			if(!$scope.search) {
				var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getFinancialYears = data;
				}).error(function(data, status, headers, config) {
					$scope.getFinancialYears = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchFinancialYears?keyword='+$scope.search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getFinancialYears = data;
				}).error(function(data, status, headers, config) {
					$scope.getFinancialYears = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorStartDate = 0;
			$scope.errorEndDate = 0;
		}
		
		$scope.checkEndDate = function() {			
			var startdate = document.getElementById("startdate").value;
			var enddate = document.getElementById("enddate").value;			
			if(new Date(startdate) > new Date(enddate)) {				
				$scope.errorEndDate = 1;
				$scope.msgEndDate = "End Date should be greater than start date";
				document.getElementById("enddate").focus();
			} else {								
				var startcode = startdate.substring(8,10);
				var endcode = enddate.substring(8,10);
				$scope.financialyearcodeadd = startcode+endcode;
			}
		}		
		
		$scope.addFinancialYear = function() {
			$scope.financialyearstartdate = document.getElementById("startdate").value;
			$scope.financialyearenddate = document.getElementById("enddate").value;			
			
			if(!$scope.financialyearstartdate) {				
				$scope.errorStartDate = 1;
				$scope.msgStartDate = "Please enter start date";
				document.getElementById("startdate").focus();
			} else if(!$scope.financialyearenddate) {				
				$scope.errorEndDate = 1;
				$scope.msgEndDate = "Please enter end date";
				document.getElementById("enddate").focus();
			} else {
				$scope.spin = 1;
				var link = baseUrl+'addFinancialYear?financialyearstartdate='+$scope.financialyearstartdate+'&financialyearenddate='+$scope.financialyearenddate+'&financialyearcode='+$scope.financialyearcodeadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addfinancialyear = data;
					$scope.spin = 0;
					if($scope.addfinancialyear == 'Success'){
						$scope.yearSubmitError = 0;
						$scope.yearSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.yearSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_financial_year';
						}, 2000);
					} else {
						$scope.yearSubmitSuccess = 0;
						$scope.yearSubmitError = 1;
						$scope.errorMsg = $scope.addfinancialyear;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addfinancialyear = data;
					$scope.yearSubmitSuccess = 0;
					$scope.yearSubmitError = 1;
					$scope.errorMsg = $scope.addfinancialyear;	
				});			
			}
		}	
			
		$scope.getFinancialYear = function(financialyearid) {
			for (i in $scope.getFinancialYears) {
                if ($scope.getFinancialYears[i].financialYearId == financialyearid) {
                	$scope.financialyearid = $scope.getFinancialYears[i].financialYearId;
                	$scope.financialyearstartdate1 = $scope.getFinancialYears[i].financialYearStartDate;
                	$scope.startdate = $filter('date')($scope.financialyearstartdate1, 'dd/MM/yyyy');
                	$scope.financialyearenddate1 = $scope.getFinancialYears[i].financialYearEndDate;
                	$scope.enddate = $filter('date')($scope.financialyearenddate1, 'dd/MM/yyyy');
                	$scope.financialyearcode = $scope.getFinancialYears[i].financialYearCode;
                }
			}
		}
		
		$scope.checkEndDateEdit = function() {
			var startdate = document.getElementById("financialyearstartdate").value;
			var enddate = document.getElementById("financialyearenddate").value;			
			if(new Date(startdate) > new Date(enddate)) {
				document.getElementById("financialyearenddate").focus();
				$scope.info = 1;
				$scope.message = "End Date should be greater than start date";
				$timeout(function(){
					$scope.info = 0;
				}, 2000);
			} else {
				financialstartdate = $filter('date')(startdate, 'dd/MM/yyyy');
				financialenddate = $filter('date')(enddate, 'dd/MM/yyyy');
				
				var startcode = financialstartdate.substring(8,10);
				var endcode = financialenddate.substring(8,10);
				$scope.financialyearcode = startcode+endcode;
			}
		}
		
		$scope.editFinancialYear = function(financialyearid) {
			$scope.financialyearstartdate = document.getElementById("startdate1").value;
			$scope.financialyearenddate = document.getElementById("enddate1").value;			
			
			if(!$scope.financialyearstartdate) {				
				$scope.errorStartDate = 1;
				$scope.msgStartDate = "Please enter start date";
				document.getElementById("startdate").focus();
			} else if(!$scope.financialyearenddate) {				
				$scope.errorEndDate = 1;
				$scope.msgEndDate = "Please enter end date";
				document.getElementById("enddate").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editFinancialYear?financialyearid='+financialyearid+'&financialyearstartdate='+$scope.financialyearstartdate+'&financialyearenddate='+$scope.financialyearenddate+'&financialyearcode='+$scope.financialyearcode;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editfinancialyear = data;
					$scope.spin = 0;
					if($scope.editfinancialyear == 'Success'){
						$scope.yearSubmitError = 0;
						$scope.yearSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.yearSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_financial_year';
						}, 2000);
					} else {
						$scope.yearSubmitSuccess = 0;
						$scope.yearSubmitError = 1;
						$scope.errorMsg = $scope.editfinancialyear;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.editfinancialyear = data;
					$scope.yearSubmitSuccess = 0;
					$scope.yearSubmitError = 1;
					$scope.errorMsg = $scope.editfinancialyear;	
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getFinancialYears, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteCurrency = function() {		
		    angular.forEach($scope.getFinancialYears, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteFinancialYear?financialyearid='+item.financialYearId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletefinancialyear = data;
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletefinancialyear = "Response Fail";
		    		});
		    	}
		    });
		    
		    var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFinancialYears = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getFinancialYears = "Response Fail";
			});	    	    
		}		
		
		$scope.defaultFinancialYear = function(financialyearid) {
			var link = baseUrl+'addDefaultFinancialYear?financialyearid='+financialyearid;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.adddefaultfinancialyear = data;				
				if($scope.adddefaultfinancialyear == 'Success'){
					var link = baseUrl+'getFinancialYearsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getFinancialYears = data;						
					}).error(function(data, status, headers, config) {
						$scope.getFinancialYears = "Response Fail";
					});	
				} else {
					$scope.yearSubmitSuccess = 0;
					$scope.yearSubmitError = 1;
					$scope.errorMsg = $scope.adddefaultfinancialyear;						
				}				
			}).error(function(data, status, headers, config) {
				$scope.editfinancialyear = data;
				$scope.yearSubmitSuccess = 0;
				$scope.yearSubmitError = 1;
				$scope.errorMsg = $scope.adddefaultfinancialyear;	
			});			
		}
}]);