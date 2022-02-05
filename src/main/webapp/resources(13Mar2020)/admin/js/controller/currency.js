var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('currencyCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getCurrencies.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCurrencies = data;
		}).error(function(data, status, headers, config) {
			$scope.getCurrencies = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCurrencies = data;
				}).error(function(data, status, headers, config) {
					$scope.getCurrencies = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;			
			var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCurrencies = data;
			}).error(function(data, status, headers, config) {
				$scope.getCurrencies = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			if($scope.pageSize == "All") {
				var link = baseUrl+'getCurrencies';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCurrencies = data;
				}).error(function(data, status, headers, config) {
					$scope.getCurrencies = "Response Fail";
				});
			} else {
				var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCurrencies = data;
				}).error(function(data, status, headers, config) {
					$scope.getCurrencies = "Response Fail";
				});
			}
		}
		
		$scope.searchCurrency = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCurrencies = data;
				}).error(function(data, status, headers, config) {
					$scope.getCurrencies = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchCurrencies?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCurrencies = data;
				}).error(function(data, status, headers, config) {
					$scope.getCurrencies = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCurrencyName = 0;						
		}
		
		$scope.addCurrency = function() {		
			if(!$scope.currencycodeadd) {
				$scope.currencycodeadd = "";
			}
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.currencynameadd) {				
				$scope.errorCurrencyName = 1;
				$scope.msgCurrencyName = "Please enter currency name";
				document.getElementById("currencynameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addCurrency?currencyname='+$scope.currencynameadd+'&currencycode='+$scope.currencycodeadd+'&description='+$scope.descriptionadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addcurrency = data;
					$scope.spin = 0;
					if($scope.addcurrency == 'Success'){
						$scope.currencySubmitError = 0;
						$scope.currencySubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.currencySubmitSuccess = 0;
							$window.location.href = adminurl+'manage_currency';
						}, 2000);
					} else {
						$scope.currencySubmitSuccess = 0;
						$scope.currencySubmitError = 1;
						$scope.errorMsg = $scope.addcurrency;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addcurrency = data;
					$scope.currencySubmitSuccess = 0;
					$scope.currencySubmitError = 1;
					$scope.errorMsg = $scope.addcurrency;	
				});
			}
		}
			
		$scope.getCurrency = function(currencyid) {
			for (i in $scope.getCurrencies) {
                if ($scope.getCurrencies[i].currencyId == currencyid) {
                	$scope.currencyid = $scope.getCurrencies[i].currencyId;
                	$scope.currencyname = $scope.getCurrencies[i].currencyName;
                	$scope.currencycode = $scope.getCurrencies[i].currencyCode;                	
                	$scope.description = $scope.getCurrencies[i].description;
                }
			}
		}	
		
		$scope.editCurrency = function(currencyid) {
			if(!$scope.currencycode) {
				$scope.currencycode = "";
			}
			if(!$scope.description) {
				$scope.description = "";
			}
			if(!$scope.currencyname) {				
				$scope.errorCurrencyName = 1;
				$scope.msgCurrencyName = "Please enter currency name";
				document.getElementById("currencyname").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editCurrency?currencyid='+currencyid+'&currencyname='+$scope.currencyname+'&currencycode='+$scope.currencycode+'&description='+$scope.description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editcurrency = data;
					$scope.spin = 0;
					if($scope.editcurrency == 'Success'){
						
						var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getCurrencies = data;
							
							$scope.currencySubmitError = 0;
							$scope.currencySubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.currencySubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getCurrencies = "Response Fail";
						});					
					} else {
						$scope.currencySubmitSuccess = 0;
						$scope.currencySubmitError = 1;
						$scope.errorMsg = $scope.editcurrency;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.editcurrency = data;
					$scope.currencySubmitSuccess = 0;
					$scope.currencySubmitError = 1;
					$scope.errorMsg = $scope.editcurrency;	
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getCurrencies, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteCurrency = function() {		
		    angular.forEach($scope.getCurrencies, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteCurrency?currencyid='+item.currencyId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletecurrency = data;
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletecurrency = "Response Fail";
		    		});
		    	}
		    });
		    
		    var link = baseUrl+'getCurrenciesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCurrencies = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getCurrencies = "Response Fail";
			});	    
		}		
}]);