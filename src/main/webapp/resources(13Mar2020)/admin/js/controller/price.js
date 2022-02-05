var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('priceCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
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
			return Math.ceil($scope.getPrices.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getPrices = data;
		}).error(function(data, status, headers, config) {
			$scope.getPrices = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPrices = data;
				}).error(function(data, status, headers, config) {
					$scope.getPrices = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getPrices = data;
			}).error(function(data, status, headers, config) {
				$scope.getPrices = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllPrices';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPrices = data;
				}).error(function(data, status, headers, config) {
					$scope.getPrices = "Response Fail";
				});
			} else {
				var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPrices = data;
				}).error(function(data, status, headers, config) {
					$scope.getPrices = "Response Fail";
				});
			}
		}
		
		$scope.searchPrice = function() {
			if(!$scope.search) {
				var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPrices = data;
				}).error(function(data, status, headers, config) {
					$scope.getPrices = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchPrices?keyword='+$scope.search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPrices = data;
				}).error(function(data, status, headers, config) {
					$scope.getPrices = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorPriceLabel = 0;
		}
		
		$scope.addPrice = function() {
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.pricelabeladd) {
				$scope.errorPriceLabel = 1;
				$scope.msgPriceLabel = "Please enter price label";
				document.getElementById("pricelabeladd").focus();
			} else {
				$scope.spin = 1;
				var link = baseUrl+'addPriceLabel?pricelabel='+$scope.pricelabeladd+'&description='+$scope.descriptionadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addprice = data;
					$scope.spin = 0;
					if($scope.addprice == 'Success'){
						$scope.priceSubmitError = 0;
						$scope.priceSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.priceSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_price_label';
						}, 2000);
					} else {
						$scope.priceSubmitSuccess = 0;
						$scope.priceSubmitError = 1;
						$scope.errorMsg = $scope.addprice;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addprice = data;
					$scope.spin = 0;
					$scope.priceSubmitSuccess = 0;
					$scope.priceSubmitError = 1;
					$scope.errorMsg = $scope.addprice;
				});
			}
		}
			
		$scope.getPrice = function(priceid) {
			for (i in $scope.getPrices) {
				if ($scope.getPrices[i].priceLabelId == priceid) {
					$scope.priceid = $scope.getPrices[i].priceLabelId;
					$scope.pricelabel = $scope.getPrices[i].priceLabel;
					$scope.description = $scope.getPrices[i].description;
				}
			}
		}
		
		$scope.editPrice = function(priceid) {
			if(!$scope.description) {
				$scope.description = "";
			}
			if(!$scope.pricelabel) {
				$scope.errorPriceLabel = 1;
				$scope.msgPriceLabel = "Please enter price label";
				document.getElementById("pricelabel").focus();
			} else {
				$scope.spin = 1;
				var link = baseUrl+'editPriceLabel?priceid='+priceid+'&pricelabel='+$scope.pricelabel+'&description='+$scope.description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editprice = data;
					$scope.spin = 0;
					if($scope.editprice == 'Success'){
						$scope.priceSubmitError = 0;
						$scope.priceSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getPrices = data;
						}).error(function(data, status, headers, config) {
							$scope.getPrices = "Response Fail";
						});
						$timeout(function(){
							$scope.priceSubmitSuccess = 0;
							$('#editModal').modal('hide');
						}, 2000);
					} else {
						$scope.priceSubmitSuccess = 0;
						$scope.priceSubmitError = 1;
						$scope.errorMsg = $scope.editprice;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.editprice = data;
					$scope.spin = 0;
					$scope.priceSubmitSuccess = 0;
					$scope.priceSubmitError = 1;
					$scope.errorMsg = $scope.editprice;
				});
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getPrices, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deletePrice = function() {
			angular.forEach($scope.getPrices, function(item) {
				if (item.selected) {
					var link = baseUrl+'deletePrice?priceid='+item.priceLabelId;					
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteprice = data;
					}).error(function(data, status, headers, config) {
						$scope.deleteprice = "Response Fail";
					});
				}
			});
			var link = baseUrl+'getPricesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getPrices = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getPrices = "Response Fail";
			});		
		}		
}]);