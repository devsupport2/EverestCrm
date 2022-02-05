var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('subcategoryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getSubcategory.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getRealestateName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getRealestateName = data;
		}).error(function(data, status, headers, config) {
			$scope.getRealestateName = "Response Fail";
		});
		
		var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getSubcategory = data;
		}).error(function(data, status, headers, config) {
			$scope.getSubcategory = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategory = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getSubcategory = data;
			}).error(function(data, status, headers, config) {
				$scope.getSubcategory = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getSubcategory';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategory = "Response Fail";
				});
			} else {
				var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategory = "Response Fail";
				});
			}
		}
		
		$scope.searchSubcategory = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategory = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchSubcategory?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategory = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategory = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCategoryTitle = 0;
			$scope.errorRealSubTitle = 0;
			$scope.errorRealSubCode = 0;
		}
		
		$scope.addSubcategory = function() {
			
			if($scope.realestatecodeadd==undefined) {
				$scope.realestatecodeadd = "";
			}
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}		
			
			if($scope.realeidadd==undefined || $scope.realeidadd=="") {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please select realestate type!";
				document.getElementById("realestatetypeidadd").focus();
			} else if($scope.realestatesubtitleadd==undefined || $scope.realestatesubtitleadd=="") {			
				$scope.errorRealSubTitle = 1;
				$scope.msgSubcategoryTitle = "Please enter title";
				document.getElementById("realestatesubtitleadd").focus();
			} else if($scope.realestatecodeadd==undefined || $scope.realestatecodeadd=="") {			
				$scope.errorRealSubCode = 1;
				$scope.msgSubcategoryCode = "Please enter code";
				document.getElementById("realestatecodeadd").focus();
			} else {
				$scope.spin = 1;			
				
				$scope.realestatesubtitleadd = tools_replaceAll($scope.realestatesubtitleadd, "&" , "$" );
				$scope.realestatesubtitleadd = tools_replaceAll($scope.realestatesubtitleadd, "#" , "~" );
				$scope.realestatesubtitleadd = tools_replaceAll($scope.realestatesubtitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );
				
				
				var link = baseUrl+'addRealestateSubcategoryType?realestateid='+$scope.realeidadd+'&realestatesubtitle='+$scope.realestatesubtitleadd+'&realestatesubcode='+$scope.realestatecodeadd+'&description='+$scope.descriptionadd;
				
				var formData=new FormData();
				formData.append("image",image1add.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addrealestatesubcategory = data;					
					if($scope.addrealestatesubcategory == "Success"){
						$scope.spin = 0;
						$scope.submitSuccess = 1;
						$scope.msgSuccess = "Data added successfully";
						$timeout(function(){							
							$scope.submitSuccess = 0;	
							$window.location.href = adminurl+'manage_subcategory';
						}, 3000);
						
					} else {
						$scope.spin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted! Duplicate entry for subcategory code!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 5000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addrealestatesubcategory = data;				
					$scope.spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addrealestatesubcategory;
					$timeout(function(){						
						$scope.submitError = 0;
					}, 5000);
					
				});    			
			}
		}
			
		$scope.getSubcategoryById = function(id) {			
			var link = baseUrl+'getSubcategoryDetailById?id='+id;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.subcategoryById = data;
				
				$scope.subcategoryid = $scope.subcategoryById.realestateSubcategoryId;
            	$scope.realestatesubtitle = $scope.subcategoryById.subcategoryTitle;
            	$scope.realestatecode = $scope.subcategoryById.subcategoryCode;
            	$scope.description = $scope.subcategoryById.description;
            	$scope.realeid = $scope.subcategoryById.realestateTypeId;
            	
			}).error(function(data, status, headers, config) {
				$scope.subcategoryById = "Response Fail";
			});	
		}
		
		$scope.editSubcategory = function(id) {			
			if(!$scope.description) {
				$scope.description = "";
			}		
			
			if(!$scope.realeid) {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please select realestate type!";
				document.getElementById("realestatetypeid").focus();
			} else if(!$scope.realestatesubtitle) {			
				$scope.errorRealSubTitle = 1;
				$scope.msgSubcategoryTitle = "Please enter title";
				document.getElementById("realestatesubtitle").focus();
			} else if(!$scope.realestatecode) {			
				$scope.errorRealSubCode = 1;
				$scope.msgSubcategoryCode = "Please enter code";
				document.getElementById("realestatecode").focus();
			} else {
				$scope.subcategorySpin = 1;			
				
				$scope.realestatesubtitle = tools_replaceAll($scope.realestatesubtitle, "&" , "$" );
				$scope.realestatesubtitle = tools_replaceAll($scope.realestatesubtitle, "#" , "~" );
				$scope.realestatesubtitle = tools_replaceAll($scope.realestatesubtitle, "%" , "!" );
				
				$scope.description = tools_replaceAll($scope.description, "&" , "$" );
				$scope.description = tools_replaceAll($scope.description, "#" , "~" );
				$scope.description = tools_replaceAll($scope.description, "%" , "!" );				
				
				var link = baseUrl+'editRealestateSubcategoryType?id='+id+'&realestateid='+$scope.realeid+'&realestatesubtitle='+$scope.realestatesubtitle+'&realestatesubcode='+$scope.realestatecode+'&description='+$scope.description;				
				var formData=new FormData();
				formData.append("image",image1add.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editsubcategory = data;					
					if($scope.editsubcategory == "Success"){
						var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getSubcategory = data;
		    				
		    				$scope.subcategorySpin = 0;
							$scope.submitSuccess = 1;
							$scope.msgSuccess = "Data added successfully";
							$timeout(function(){
								$scope.submitSuccess = 0;
								$('#editModal').modal('hide');						
							}, 3000);
							
		    			}).error(function(data, status, headers, config) {
		    				$scope.getSubcategory = "Response Fail";
		    			});												
					} else {
						$scope.subcategorySpin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted! Duplicate entry for subcategory code!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 5000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.editsubcategory = data;				
					$scope.subcategorySpin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.editsubcategory;
					$timeout(function(){
						$scope.submitError = 0;
					}, 5000);
					
				});    			
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getSubcategory, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteSubcategory = function() {		
		    angular.forEach($scope.getSubcategory, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteSubcategory?id='+item.realestateSubcategoryId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletesubcategory = data;
		    			
		    			var link = baseUrl+'getSubcategoryByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getSubcategory = data;
		    				$('#deleteModal').modal('hide');
		    			}).error(function(data, status, headers, config) {
		    				$scope.getSubcategory = "Response Fail";
		    			});
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletesubcategory = "Response Fail";
		    		});
		    	}
		    });	    
		}		
}]);