var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('achievementCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getAchivements.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAchivements = data;
		}).error(function(data, status, headers, config) {
			$scope.getAchivements = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getAchivements = data;
				}).error(function(data, status, headers, config) {
					$scope.getAchivements = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getAchivements = data;
			}).error(function(data, status, headers, config) {
				$scope.getAchivements = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAchievement';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getAchivements = data;
				}).error(function(data, status, headers, config) {
					$scope.getAchivements = "Response Fail";
				});
			} else {
				var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getAchivements = data;
				}).error(function(data, status, headers, config) {
					$scope.getAchivements = "Response Fail";
				});
			}
		}
		
		$scope.searchAchievement = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getAchivements = data;
				}).error(function(data, status, headers, config) {
					$scope.getAchivements = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchAchievement?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getAchivements = data;
				}).error(function(data, status, headers, config) {
					$scope.getAchivements = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCategoryTitle = 0;
			$scope.errorCategoryCode = 0;
		}
		
		$scope.addAchivement = function() {
			
			if($scope.achievementsubtitleadd==undefined) {
				$scope.achievementsubtitleadd = "";
			}
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}
			
			var valuex1 = document.getElementById("valuex1").value;
			var valuey1 = document.getElementById("valuey1").value;
			var valuew1 = document.getElementById("valuew1").value;
			var valueh1 = document.getElementById("valueh1").value;
			
			if(valuex1 == ''){
				valuex1 = 0;
			}
			if(valuey1 == ''){
				valuey1 = 0;
			}
			if(valuew1 == ''){
				valuew1 = 0;
			}
			if(valueh1 == ''){
				valueh1 = 0;
			}
			
			if($scope.achievementtitleadd==undefined || $scope.achievementtitleadd=="") {
				$scope.errorAchievementTitle = 1;
				$scope.msgAchievementTitle = "Please enter achievement title";
				document.getElementById("achievementtitleadd").focus();
			} else {
				$scope.spin = 1;
				
				$scope.achievementtitleadd = tools_replaceAll($scope.achievementtitleadd, "&" , "$" );
				$scope.achievementtitleadd = tools_replaceAll($scope.achievementtitleadd, "#" , "~" );
				$scope.achievementtitleadd = tools_replaceAll($scope.achievementtitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );				
				
				var link = baseUrl+'addAchivement?achievementtitle='+$scope.achievementtitleadd+'&achievementsubtitle='+$scope.achievementsubtitleadd+'&description='+$scope.descriptionadd+'&valuex='+valuex1+'&valuey='+valuey1+'&valuew='+valuew1+'&valueh='+valueh1;
				var formData=new FormData();
				formData.append("image",imageadd.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addachievement = data;
					
					if($scope.addachievement == "Success"){
						$scope.spin = 0;
						$scope.submitSuccess = 1;
						$scope.msgSuccess = "Data added successfully";
						$timeout(function(){							
							$scope.submitSuccess = 0;
							$window.location.href = adminurl+'manage_achievement';
						}, 3000);
						
					} else {
						$scope.spin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addachievement = data;				
					$scope.spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addachievement;
					$timeout(function(){												
						$scope.submitError = 0;
					}, 3000);
					
				});    			
			}
		}
			
		$scope.getAchievementDetailsById = function(id) {			
			var link = baseUrl+'getAchievementDetailById?id='+id;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.achievementById = data;
				
				$scope.achievementid = $scope.achievementById.achievementId;
            	$scope.achievementtitleedit = $scope.achievementById.title;
            	$scope.achievementsubtitleedit = $scope.achievementById.subtitle;
            	$scope.imageedit = $scope.achievementById.image;
            	$scope.description = $scope.achievementById.description;
            	
			}).error(function(data, status, headers, config) {
				$scope.achievementById = "Response Fail";
			});	
		}
		
		$scope.editAchievement = function(id) {
			if(!$scope.achievementsubtitleedit) {
				$scope.achievementsubtitleedit = "";
			}
			if(!$scope.description) {
				$scope.description = "";
			}
			
			var valuex1 = document.getElementById("valuex").value;
			var valuey1 = document.getElementById("valuey").value;
			var valuew1 = document.getElementById("valuew").value;
			var valueh1 = document.getElementById("valueh").value;
			
			if(valuex1 == ''){
				valuex1 = 0;
			}
			if(valuey1 == ''){
				valuey1 = 0;
			}
			if(valuew1 == ''){
				valuew1 = 0;
			}
			if(valueh1 == ''){
				valueh1 = 0;
			}
			
			if(!$scope.achievementtitleedit) {			
				$scope.errorAchievementTitle = 1;
				$scope.msgAchievementTitle = "Please enter achievement title";
				document.getElementById("achievementtitleedit").focus();
			} else {
				$scope.spin = 1;	
				
				$scope.achievementtitleedit = tools_replaceAll($scope.achievementtitleedit, "&" , "$" );
				$scope.achievementtitleedit = tools_replaceAll($scope.achievementtitleedit, "#" , "~" );
				$scope.achievementtitleedit = tools_replaceAll($scope.achievementtitleedit, "%" , "!" );
				
				$scope.description = tools_replaceAll($scope.description, "&" , "$" );
				$scope.description = tools_replaceAll($scope.description, "#" , "~" );
				$scope.description = tools_replaceAll($scope.description, "%" , "!" );				
				
				var link = baseUrl+'editAchivement?id='+id+'&achievementtitle='+$scope.achievementtitleedit+'&achievementsubtitle='+$scope.achievementsubtitleedit+'&description='+$scope.description+'&valuex='+valuex1+'&valuey='+valuey1+'&valuew='+valuew1+'&valueh='+valueh1+'&imageedit='+$scope.imageedit;
				var formData=new FormData();
				formData.append("imagedit",imagedit.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editachievement = data;
					if($scope.editachievement == "Success"){
						var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getAchivements = data;
							$scope.categorySpin = 0;
							$scope.submitSuccess = 1;
							$scope.msgSuccess = "Data updated successfully";
							$timeout(function(){							
								$scope.submitSuccess = 0;	
								$('#editModal').modal('hide');
							}, 3000);
						}).error(function(data, status, headers, config) {
							$scope.getAchivements = "Response Fail";
						});					
					} else {
						$scope.categorySpin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.editachievement = data;				
					$scope.categorySpin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.editachievement;
					$timeout(function(){												
						$scope.submitError = 0;
					}, 3000);
					
				});    			
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getAchivements, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteAchievement = function() {		
		    angular.forEach($scope.getAchivements, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteAchievement?id='+item.achievementId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deleteachievement = data;
		    			
		    			var link = baseUrl+'getAchievementByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getAchivements = data;
		    				$('#deleteModal').modal('hide');
		    			}).error(function(data, status, headers, config) {
		    				$scope.getAchivements = "Response Fail";
		    			});
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.deleteachievement = "Response Fail";
		    		});
		    	}
		    });	    
		}		
}]);