var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('categoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
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
		
		var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCategory = data;
		}).error(function(data, status, headers, config) {
			$scope.getCategory = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getCategory = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getCategory = data;
			}).error(function(data, status, headers, config) {
				$scope.getCategory = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getCategory';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getCategory = "Response Fail";
				});
			} else {
				var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getCategory = "Response Fail";
				});
			}
		}
		
		$scope.searchCategory = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getCategory = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchCategory?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getCategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getCategory = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCategoryTitle = 0;
			$scope.errorCategoryCode = 0;
		}
		
		$scope.addCategory = function() {
			
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}		
			
			if($scope.realestatetitleadd==undefined || $scope.realestatetitleadd=="") {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please enter category title";
				document.getElementById("realestatetitleadd").focus();
			} else if($scope.realestatecodeadd==undefined || $scope.realestatecodeadd=="") {			
				$scope.errorCategoryCode = 1;
				$scope.msgCategoryCode = "Please enter category code";
				document.getElementById("realestatecodeadd").focus();
			} else {
				$scope.categorySpin = 1;	
				
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "&" , "$" );
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "#" , "~" );
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );				
				
				var link = baseUrl+'addRealestateType?realestatetitle='+$scope.realestatetitleadd+'&realestatecode='+$scope.realestatecodeadd+'&description='+$scope.descriptionadd;
				
				var formData=new FormData();
				formData.append("image",imageadd.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addrealestatetype = data;				
					
					if($scope.addrealestatetype == "Success"){
						$scope.categorySpin = 0;
						$scope.submitSuccess = 1;
						$scope.msgSuccess = "Data added successfully";
						$timeout(function(){							
							$scope.submitSuccess = 0;
							$window.location.href = adminurl+'manage_category';
						}, 3000);
						
					} else {
						$scope.categorySpin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted! Duplicate entry for category code!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addrealestatetype = data;				
					$scope.categorySpin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addcategory;
					$timeout(function(){												
						$scope.submitError = 0;
					}, 3000);
					
				});    			
			}
		}
			
		$scope.getCategoryById = function(id) {			
			var link = baseUrl+'getCategoryDetailById?id='+id;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.categoryById = data;
				
				$scope.categoryid = $scope.categoryById.realestateTypeId;
            	$scope.realestatetitle = $scope.categoryById.realestateTypeName;
            	$scope.realestatecode = $scope.categoryById.realestateCode;
            	$scope.description = $scope.categoryById.description;
            	
			}).error(function(data, status, headers, config) {
				$scope.categoryById = "Response Fail";
			});	
		}
		
		$scope.editCategory = function(id) {
			if(!$scope.description) {
				$scope.description = "";
			}		
			
			if(!$scope.realestatetitle) {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please enter category title";
				document.getElementById("realestatetitleadd").focus();
			} else if(!$scope.realestatecode) {			
				$scope.errorCategoryCode = 1;
				$scope.msgCategoryCode = "Please enter category code";
				document.getElementById("realestatecode").focus();
			} else {
				$scope.categorySpin = 1;	
				
				$scope.realestatetitle = tools_replaceAll($scope.realestatetitle, "&" , "$" );
				$scope.realestatetitle = tools_replaceAll($scope.realestatetitle, "#" , "~" );
				$scope.realestatetitle = tools_replaceAll($scope.realestatetitle, "%" , "!" );
				
				$scope.description = tools_replaceAll($scope.description, "&" , "$" );
				$scope.description = tools_replaceAll($scope.description, "#" , "~" );
				$scope.description = tools_replaceAll($scope.description, "%" , "!" );				
				
				var link = baseUrl+'editRealestateType?id='+id+'&realestatetitle='+$scope.realestatetitle+'&realestatecode='+$scope.realestatecode+'&description='+$scope.description;				
				var formData=new FormData();
				formData.append("image",image.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editrealestatetype = data;				
					
					if($scope.editrealestatetype == "Success"){
						var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getCategory = data;
							$scope.categorySpin = 0;
							$scope.submitSuccess = 1;
							$scope.msgSuccess = "Data updated successfully";
							$timeout(function(){							
								$scope.submitSuccess = 0;	
								$('#editModal').modal('hide');
							}, 3000);
						}).error(function(data, status, headers, config) {
							$scope.getCategory = "Response Fail";
						});					
					} else {
						$scope.categorySpin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted! Duplicate entry for category code!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.editrealestatetype = data;				
					$scope.categorySpin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.editrealestatetype;
					$timeout(function(){												
						$scope.submitError = 0;
					}, 3000);
					
				});    			
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getCategory, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteCategory = function() {		
		    angular.forEach($scope.getCategory, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteCategory?id='+item.realestateTypeId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletecategory = data;
		    			
		    			var link = baseUrl+'getCategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getCategory = data;
		    				$('#deleteModal').modal('hide');
		    			}).error(function(data, status, headers, config) {
		    				$scope.getCategory = "Response Fail";
		    			});
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletecategory = "Response Fail";
		    		});
		    	}
		    });	    
		}		
}]);