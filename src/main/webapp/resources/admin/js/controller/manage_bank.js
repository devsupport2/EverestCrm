var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('bankCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getCategory.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getBank = data;
		}).error(function(data, status, headers, config) {
			$scope.getBank = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBank = data;
				}).error(function(data, status, headers, config) {
					$scope.getBank = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getBank = data;
			}).error(function(data, status, headers, config) {
				$scope.getBank = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllBank';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBank = data;
				}).error(function(data, status, headers, config) {
					$scope.getBank = "Response Fail";
				});
			} else {
				var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBank = data;
				}).error(function(data, status, headers, config) {
					$scope.getBank = "Response Fail";
				});
			}
		}
		
		$scope.searchCategory = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBank = data;
				}).error(function(data, status, headers, config) {
					$scope.getBank = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchBank?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getBank = data;
				}).error(function(data, status, headers, config) {
					$scope.getBank = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCategoryTitle = 0;
			$scope.errorCategoryCode = 0;
		}
		
		$scope.addBank = function() {
			
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.bankcodeadd) {
				$scope.bankcodeadd = "";
			}
			
			if(!$scope.banktitleadd) {			
				$scope.errorBankTitle = 1;
				$scope.msgBankTitle = "Please enter title";
				document.getElementById("banktitleadd").focus();
			} else {
				$scope.Spin = 1;	
				
				$scope.banktitleadd = tools_replaceAll($scope.banktitleadd, "&" , "$" );
				$scope.banktitleadd = tools_replaceAll($scope.banktitleadd, "#" , "~" );
				$scope.banktitleadd = tools_replaceAll($scope.banktitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );				
				
				var link = baseUrl+'addBank?banktitle='+$scope.banktitleadd+'&bankcode='+$scope.bankcodeadd+'&description='+$scope.descriptionadd;
				var formData=new FormData();
				formData.append("image",imageadd.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addbank = data;				
					
					if($scope.addbank == "Success"){
						$scope.Spin = 0;
						$scope.submitSuccess = 1;
						$scope.msgSuccess = "Data added successfully";
						$timeout(function(){							
							$scope.submitSuccess = 0;
							$window.location.href = adminurl+'manage_bank';
						}, 3000);
						
					} else {
						$scope.Spin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addbank = data;				
					$scope.Spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addbank;
					$timeout(function(){												
						$scope.submitError = 0;
					}, 3000);
					
				});    			
			}
		}
			
		$scope.getBankById = function(id) {			
			var link = baseUrl+'getBankDetailById?id='+id;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.bankById = data;
				
				$scope.bankid = $scope.bankById.bankId;
            	$scope.banktitle = $scope.bankById.bankName;
            	$scope.bankcode = $scope.bankById.bankCode;
            	$scope.description = $scope.bankById.description;
            	$scope.imageedit = $scope.bankById.image;
            	
			}).error(function(data, status, headers, config) {
				$scope.categoryById = "Response Fail";
			});	
		}
		
		$scope.editBank = function(id) {
			if(!$scope.description) {
				$scope.description = "";
			}
			if(!$scope.bankcode) {
				$scope.bankcode = "";
			}
			
			if(!$scope.banktitle) {
				$scope.errorBankTitle = 1;
				$scope.msgBankTitle = "Please enter bank title";
				document.getElementById("banktitle").focus();
			} else {
				$scope.Spin = 1;	
				
				$scope.banktitle = tools_replaceAll($scope.banktitle, "&" , "$" );
				$scope.banktitle = tools_replaceAll($scope.banktitle, "#" , "~" );
				$scope.banktitle = tools_replaceAll($scope.banktitle, "%" , "!" );
				
				$scope.description = tools_replaceAll($scope.description, "&" , "$" );
				$scope.description = tools_replaceAll($scope.description, "#" , "~" );
				$scope.description = tools_replaceAll($scope.description, "%" , "!" );				
				
				var link = baseUrl+'editBank?bankid='+id+'&banktitle='+$scope.banktitle+'&bankcode='+$scope.bankcode+'&description='+$scope.description+'&imageedit='+$scope.imageedit;				
				var formData=new FormData();
				formData.append("image",image.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editbank = data;				
					
					if($scope.editbank == "Success"){
						var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getBank = data;
							$scope.Spin = 0;
							$scope.submitSuccess = 1;
							$scope.msgSuccess = "Data updated successfully";
							$timeout(function(){							
								$scope.submitSuccess = 0;	
								$('#editModal').modal('hide');
							}, 3000);
						}).error(function(data, status, headers, config) {
							$scope.getBank = "Response Fail";
						});					
					} else {
						$scope.Spin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.editbank = data;				
					$scope.Spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.editbank;
					$timeout(function(){												
						$scope.submitError = 0;
					}, 3000);
					
				});    			
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;
			angular.forEach($scope.getBank, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteBank = function() {		
		    angular.forEach($scope.getBank, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteBank?id='+item.bankId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletebank = data;
		    			
		    			var link = baseUrl+'getBankByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getBank = data;
		    				$('#deleteModal').modal('hide');
		    			}).error(function(data, status, headers, config) {
		    				$scope.getBank = "Response Fail";
		    			});
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletebank = "Response Fail";
		    		});
		    	}
		    });	    
		}		
}]);