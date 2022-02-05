var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('taxCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.info = 0;
		$scope.success = 0;
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getTaxes.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getTaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getTaxes = data;
		}).error(function(data, status, headers, config) {
			$scope.getTaxes = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getTaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTaxes = data;
				}).error(function(data, status, headers, config) {
					$scope.getTaxes = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getTaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getTaxes = data;
			}).error(function(data, status, headers, config) {
				$scope.getTaxes = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getTaxes';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTaxes = data;
				}).error(function(data, status, headers, config) {
					$scope.getTaxes = "Response Fail";
				});
			} else {
				var link = baseUrl+'getTaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTaxes = data;
				}).error(function(data, status, headers, config) {
					$scope.getTaxes = "Response Fail";
				});
			}
		}
		
		$scope.searchTax = function() {					
			if(!$scope.search) {
				var link = baseUrl+'getTaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTaxes = data;
				}).error(function(data, status, headers, config) {
					$scope.getTaxes = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchTaxes?keyword='+$scope.search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTaxes = data;
				}).error(function(data, status, headers, config) {
					$scope.getTaxes = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorTaxName = 0;			
		}
		
		$scope.addTax = function() {				
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}

			if(!$scope.taxnameadd) {				
				$scope.errorTaxName = 1;
				$scope.msgTaxName = "Please enter tax name";
				document.getElementById("taxnameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addTax?taxname='+$scope.taxnameadd+'&description='+$scope.descriptionadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addtax = data;
					$scope.spin = 0;
					if($scope.addtax == 'Success'){
						$scope.taxSubmitError = 0;
						$scope.taxSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.taxSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_tax';
						}, 2000);
					} else {
						$scope.taxSubmitSuccess = 0;
						$scope.taxSubmitError = 1;
						$scope.errorMsg = $scope.addtax;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addtax = data;
					$scope.spin = 0;
					$scope.taxSubmitSuccess = 0;
					$scope.taxSubmitError = 1;
					$scope.errorMsg = $scope.addtax;
				});
			}
		}
			
		$scope.getTax = function(taxid) {
			for (i in $scope.getTaxes) {
				if ($scope.getTaxes[i].taxId == taxid) {
					$scope.taxid = $scope.getTaxes[i].taxId;
					$scope.taxname = $scope.getTaxes[i].taxName;
					$scope.description = $scope.getTaxes[i].description;
				}
			}
		}
		
		$scope.editTax = function(taxid) {
			if(!$scope.description) {
				$scope.description = "";
			}
			if(!$scope.taxname) {				
				$scope.errorTaxName = 1;
				$scope.msgTaxName = "Please enter tax name";
				document.getElementById("taxname").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editTax?taxid='+taxid+'&taxname='+$scope.taxname+'&description='+$scope.description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.edittax = data;
					$scope.spin = 0;
					if($scope.edittax == 'Success'){
						$scope.taxSubmitError = 0;
						$scope.taxSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.taxSubmitSuccess = 0;
							$('#editModal').modal('hide');
						}, 2000);
					} else {
						$scope.taxSubmitSuccess = 0;
						$scope.taxSubmitError = 1;
						$scope.errorMsg = $scope.edittax;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.edittax = data;
					$scope.spin = 0;
					$scope.taxSubmitSuccess = 0;
					$scope.taxSubmitError = 1;
					$scope.errorMsg = $scope.edittax;
				});
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getTaxes, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteTax = function() {
			angular.forEach($scope.getTaxes, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteTax?taxid='+item.taxId;					
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletetax = data;
					}).error(function(data, status, headers, config) {
						$scope.deletetax = "Response Fail";
					});
				}
			});
			
			var link = baseUrl+'getTaxesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getTaxes = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getTaxes = "Response Fail";
			});		
		}		
}]);