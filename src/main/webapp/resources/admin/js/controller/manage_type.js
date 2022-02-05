var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('typeCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getType.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getType = data;
		}).error(function(data, status, headers, config) {
			$scope.getType = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getType = data;
				}).error(function(data, status, headers, config) {
					$scope.getType = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getType = data;
			}).error(function(data, status, headers, config) {
				$scope.getType = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getType';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getType = data;
				}).error(function(data, status, headers, config) {
					$scope.getType = "Response Fail";
				});
			} else {
				var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getType = data;
				}).error(function(data, status, headers, config) {
					$scope.getType = "Response Fail";
				});
			}
		}
		
		$scope.searchType = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getType = data;
				}).error(function(data, status, headers, config) {
					$scope.getType = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchType?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getType = data;
				}).error(function(data, status, headers, config) {
					$scope.getType = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorTypeTitle = 0;
			$scope.errorTypeCode = 0;
		}
		
		$scope.addType = function() {			
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}		
			if(!$scope.realeidadd) {			
				$scope.realeidadd = 0;
				
			} 
			if(!$scope.realsubidadd) {			
				$scope.realsubidadd = 0;
			} 
			
			if(!$scope.realestatetitleadd) {			
				$scope.errorTypeTitle = 1;
				$scope.msgTypeTitle = "Please enter title";
				document.getElementById("realestatetitleadd").focus();
			} else if(!$scope.realestatecodeadd) {			
				$scope.errorTypeCode = 1;
				$scope.msgTypeCode = "Please enter code";
				document.getElementById("realestatecodeadd").focus();
			} else {
				$scope.spin = 1;
								
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "&" , "$" );
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "#" , "~" );
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );
				
				var link = baseUrl+'addRealestate?realestateid='+$scope.realeidadd+'&realestatesubid='+$scope.realsubidadd+'&realestatetitle='+$scope.realestatetitleadd+'&realestatecode='+$scope.realestatecodeadd+'&description='+$scope.descriptionadd;				
				var formData=new FormData();
				formData.append("image",image3add.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addrealestate = data;					
	    			if($scope.addrealestate == "Success"){
	    				$scope.typeSpin = 0;
	    				$scope.submitSuccess = 1;
	    				$scope.msgSuccess = "Data added successfully";
	    				$timeout(function(){	    					
	    					$scope.submitSuccess = 0;
	    					$window.location.href = adminurl+'manage_type';
	    				}, 2000);
	    				
	    			} else {
	    				$scope.typeSpin = 0;    				
	    				$scope.submitError = 1;
	    				$scope.msgError = "Data not inserted! Duplicate entry for type code!";
	    				$timeout(function(){
	    					$scope.submitError = 0;				
	    				}, 3000);
	    			}
	    			
				}).error(function(data, status, headers, config) {
					$scope.addrealestate = data;				
					$scope.typeSpin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addrealestate;
					$timeout(function(){
						$scope.submitError = 0;						
					}, 5000);
					
				});    			
			}
		}
			
		$scope.getTypeById = function(id) {			
			var link = baseUrl+'getTypeDetailById?id='+id;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.typeById = data;
				
				$scope.typeid = $scope.typeById.realestateId;
            	$scope.realestatetitle = $scope.typeById.realestateTitle;
            	$scope.realestatecode = $scope.typeById.realestateCode;
            	$scope.description = $scope.typeById.description;
            	
			}).error(function(data, status, headers, config) {
				$scope.typeById = "Response Fail";
			});	
		}
		
		$scope.editType = function(id) {
			if(!$scope.description) {
				$scope.description = "";
			}
			
			if(!$scope.realeid) {			
				$scope.realeid = 0;				
			} 
			
			if(!$scope.realsubid) {			
				$scope.realsubid = 0;
			} 
			
			if(!$scope.realestatetitle) {			
				$scope.errorTypeTitle = 1;
				$scope.msgTypeTitle = "Please enter title";
				document.getElementById("realestatetitle").focus();
			} else if(!$scope.realestatecode) {			
				$scope.errorTypeCode = 1;
				$scope.msgTypeCode = "Please enter code";
				document.getElementById("realestatecode").focus();
			} else {
				$scope.typeSpin = 1;
								
				$scope.realestatetitle = tools_replaceAll($scope.realestatetitle, "&" , "$" );
				$scope.realestatetitle = tools_replaceAll($scope.realestatetitle, "#" , "~" );
				$scope.realestatetitle = tools_replaceAll($scope.realestatetitle, "%" , "!" );
				
				$scope.description = tools_replaceAll($scope.description, "&" , "$" );
				$scope.description = tools_replaceAll($scope.description, "#" , "~" );
				$scope.description = tools_replaceAll($scope.description, "%" , "!" );
				
				var link = baseUrl+'editRealestate?id='+id+'&realestateid='+$scope.realeid+'&realestatesubid='+$scope.realsubid+'&realestatetitle='+$scope.realestatetitle+'&realestatecode='+$scope.realestatecode+'&description='+$scope.description;				
				var formData=new FormData();
				formData.append("image",image3add.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editrealestate = data;					
	    			if($scope.editrealestate == "Success"){
	    				
	    				var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getType = data;
		    				$scope.typeSpin = 0;
		    				$scope.submitSuccess = 1;
		    				$scope.msgSuccess = "Data updated successfully";
		    				$timeout(function(){	    					
		    					$scope.submitSuccess = 0;
		    					$('#editModal').modal('hide');
		    				}, 2000);
		    			}).error(function(data, status, headers, config) {
		    				$scope.getType = "Response Fail";
		    			});    				
	    				
	    			} else {
	    				$scope.typeSpin = 0;    				
	    				$scope.submitError = 1;
	    				$scope.msgError = "Data not inserted! Duplicate entry for type code!";
	    				$timeout(function(){
	    					$scope.submitError = 0;				
	    				}, 3000);
	    			}
	    			
				}).error(function(data, status, headers, config) {
					$scope.editrealestate = data;				
					$scope.typeSpin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.editrealestate;
					$timeout(function(){
						$scope.submitError = 0;						
					}, 5000);					
				});    			
			}			
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getType, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteType = function() {		
		    angular.forEach($scope.getType, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteType?id='+item.realestateId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletetype = data;
		    			
		    			var link = baseUrl+'getTypeByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getType = data;
		    				$('#deleteModal').modal('hide');
		    			}).error(function(data, status, headers, config) {
		    				$scope.getType = "Response Fail";
		    			});
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletetype = "Response Fail";
		    		});
		    	}
		    });	    
		}		
}]);