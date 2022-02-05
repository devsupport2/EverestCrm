var app = angular.module("MyApp", ['angular.filter']);

var url = "/everest/";
var adminurl = "/everest/";
app.filter('to_trusted', ['$sce', function($sce)
    {
		return function(text)
		{
			return $sce.trustAsHtml(text);
		};
	}]);

app.controller('workstatusCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
		
		var range = [];
		for(var i=0;i<=100;i++) {
			range.push(i);
		}
				
		$scope.floors= range;
		
		$scope.sequence = range;	
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getProjects.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getWorkstatusByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getWorkStatus = data;
		}).error(function(data, status, headers, config) {
			$scope.getWorkStatus = "Response Fail";
		});
		
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getWorkstatusByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getWorkStatus = data;
				}).error(function(data, status, headers, config) {
					$scope.getWorkStatus = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getWorkstatusByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getWorkStatus = data;
			}).error(function(data, status, headers, config) {
				$scope.getWorkStatus = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllWorkstatus';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getWorkStatus = data;
				}).error(function(data, status, headers, config) {
					$scope.getWorkStatus = "Response Fail";
				});
			} else {
				var link = baseUrl+'getWorkstatusByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getWorkStatus = data;
				}).error(function(data, status, headers, config) {
					$scope.getWorkStatus = "Response Fail";
				});
			}
		}
		
		$scope.searchProject = function() {					
			if(!$scope.search) {
				var link = baseUrl+'getWorkstatusByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getWorkStatus = data;
				}).error(function(data, status, headers, config) {
					$scope.getWorkStatus = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchProject?keyword='+$scope.search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getWorkStatus = data;
				}).error(function(data, status, headers, config) {
					$scope.getWorkStatus = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getProjectName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProjectName = data;
		}).error(function(data, status, headers, config) {
			$scope.getProjectName = "Response Fail";
		});
		
		$scope.setFlag = function() {
			$scope.errorTitle1 = 0;
			$scope.errorProjectName = 0;
			
			$scope.errorImage = 0;
			$scope.errorTitle = 0;
			
			
		}
		
		$scope.imagelist = [];	
		var formData1 = new FormData();
		$scope.addImageRow = function() {
								
			if(!$scope.imagedescription){
				$scope.imagedescription="";
			}
			if(!$scope.imagetitle) {				
				$scope.errorTitle = 1;
				$scope.msgTitle = "Please enter title!";
				document.getElementById("imagetitle").focus();
			} else if(document.getElementById("imageadd").value == undefined || document.getElementById("imageadd").value == "") {				
				$scope.errorImage = 1;
				$scope.msgImage = "Please choose image!";
				document.getElementById("imageadd").focus();
				$timeout(function(){
					$scope.errorImage = 0;
				}, 2000);
			} else {
				
				var valuex = document.getElementById("valuex").value;
				var valuey = document.getElementById("valuey").value;
				var valuew = document.getElementById("valuew").value;
				var valueh = document.getElementById("valueh").value;
				
				$scope.imagelist.push({'imageTitle' : $scope.imagetitle, 'imageDescription' : $scope.imagedescription, 'valuex' : valuex, 'valuey' : valuey, 'valuew' : valuew, 'valueh' : valueh});
				formData1.append("imageadd",imageadd.files[0]);
				$scope.imagetitle = "";
				$scope.imagedescription = "";
			}
		}
		
		$scope.removeImageRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.imagelist.length; i++) {
				if($scope.imagelist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.imagelist.splice(index, 1);
		};
		
		
		$scope.addWorkStatus = function() {
					
			if(!$scope.subtitleadd)	{
				$scope.subtitleadd = "";
			}
			if(!$scope.adddate)	{
				$scope.adddate = "";
			}
			if(!$scope.descriptionadd)	{
				$scope.descriptionadd = "";
			}
			
			if(!$scope.idadd) {				
				$scope.errorProjectName = 1;
				$scope.msgProjectName = "Please select project name";
				document.getElementById("idadd").focus();
			} else if(!$scope.titleadd) {				
				$scope.errorTitle1 = 1;
				$scope.msgTitle1 = "Please enter title";
				document.getElementById("titleadd").focus();
			} else {
				
				$scope.spin = 1;		
				var link = baseUrl+'addWorkStatus?id='+$scope.idadd+'&title='+$scope.titleadd+'&subtitle='+$scope.subtitleadd+'&adddate='+$scope.adddate+'&description='+$scope.descriptionadd;
				$http.post(link).success(function(data, status, headers, config) {				
					$scope.addworkstatus = data;
					if($scope.imagelist.length == 0 && $scope.addworkstatus == 'Success') {
						$scope.spin = 0;								
						$scope.submitError = 0;
						$scope.submitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.submitSuccess = 0;
							$window.location.href = adminurl+'manage_workstatus';
						}, 2000);
					}
					if($scope.addworkstatus == 'Success' && $scope.imagelist.length > 0){
						
						$scope.imagetitlelist = [];
						$scope.imagesubtitlelist = [];
						
						$scope.valuex = [];
						$scope.valuey = [];
						$scope.valuew = [];
						$scope.valueh = [];
						
						angular.forEach($scope.imagelist, function(item) {
							$scope.imagetitlelist.push(item.imageTitle);
							$scope.imagesubtitlelist.push(item.imageDescription);
							
							$scope.valuex.push(item.valuex);
							$scope.valuey.push(item.valuey);
							$scope.valuew.push(item.valuew);
							$scope.valueh.push(item.valueh);
						});
						
						var link = baseUrl+'addImage?imagetitle='+$scope.imagetitlelist+'&imagesubtitle='+$scope.imagesubtitlelist+'&valuex='+$scope.valuex+'&valuey='+$scope.valuey+'&valuew='+$scope.valuew+'&valueh='+$scope.valueh;
						$http({method : 'POST', url : link, headers : { 'Content-Type' : undefined }, data : formData1, transformRequest : function(data,headersGetterFunction) { return data; }}).success(function(data,status,headers,config) {
							$scope.addimage = data;
							if($scope.imagelist.length != 0 && $scope.addworkstatus == 'Success') {
								$scope.spin = 0;
								$scope.submitError = 0;
								$scope.submitSuccess = 1;
								$scope.successMsg = "Data added";
								$timeout(function(){
									$scope.submitSuccess = 0;
									$window.location.href = adminurl+'manage_workstatus';
								}, 2000);
							}
						}).error(function(data, status, headers, config) {
							$scope.addimage = "Response Fail";
						});
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addworkstatus = data;
					$scope.spin = 0;
					$scope.submitSuccess = 0;
					$scope.submitError = 1;
					$scope.errorMsg = "Something went wrong!";
				});			
			}
		}
			
		
		$scope.getWorkstatusDetails = function(id){
			var link = baseUrl+'getWorkstatusDetailsById?id='+id;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getWorkStatusDetails = data;
				$scope.id = $scope.getWorkStatusDetails.workstatusId;
				$scope.idedit = $scope.getWorkStatusDetails.projectId;
				$scope.subtitleedit = $scope.getWorkStatusDetails.subtitle;
				$scope.editdate = $scope.getWorkStatusDetails.date;
				$scope.titleedit = $scope.getWorkStatusDetails.title;
				$scope.description = $scope.getWorkStatusDetails.description;
				
			}).error(function(data, status, headers, config) {
				$scope.getWorkStatusDetails = "Response Fail";
			});
		}
		
		$scope.editWorkStatus = function(id) {
			
			if(!$scope.subtitleedit)	{
				$scope.subtitleedit = "";
			}
			if(!$scope.editdate)	{
				$scope.editdate = "";
			}
			if(!$scope.description)	{
				$scope.description = "";
			}
			
			if(!$scope.idedit) {				
				$scope.errorProjectName = 1;
				$scope.msgProjectName = "Please select project name";
				document.getElementById("idedit").focus();
			} else if(!$scope.titleedit) {				
				$scope.errorTitle1 = 1;
				$scope.msgTitle1 = "Please enter title";
				document.getElementById("titleedit").focus();
			} else {
				
				$scope.spin = 1;		
				var link = baseUrl+'editWorkStatus?wid='+id+'&id='+$scope.idedit+'&title='+$scope.titleedit+'&subtitle='+$scope.subtitleedit+'&adddate='+$scope.editdate+'&description='+$scope.description;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editworkstatus = data;
					if($scope.editworkstatus == 'Success') {
						$scope.spin = 0;								
						$scope.submitError = 0;
						$scope.submitSuccess = 1;
						$scope.successMsg = "Data updated successfully";
						$timeout(function(){
							$scope.submitSuccess = 0;
							$window.location.href = adminurl+'manage_workstatus';
						}, 2000);
					}
				}).error(function(data, status, headers, config) {
					$scope.editworkstatus = data;
					$scope.spin = 0;
					$scope.submitSuccess = 0;
					$scope.submitError = 1;
					$scope.errorMsg = "Something went wrong!";
				});			
			}
		}
		
		$scope.editImageRow = function(id){
			
			if(!$scope.imagedescriptionedit){
				$scope.imagedescriptionedit="";
			}
			if(!$scope.imagetitleedit) {				
				$scope.errorTitle = 1;
				$scope.msgTitle = "Please enter title!";
				document.getElementById("imagetitleedit").focus();
			} else if(document.getElementById("imageedit").value == undefined || document.getElementById("imageedit").value == "") {				
				$scope.errorImage = 1;
				$scope.msgImage = "Please choose image!";
				document.getElementById("imageedit").focus();
				$timeout(function(){
					$scope.errorImage = 0;
				}, 2000);
			} else {
				var valuex = document.getElementById("valuex2").value;
				var valuey = document.getElementById("valuey2").value;
				var valuew = document.getElementById("valuew2").value;
				var valueh = document.getElementById("valueh2").value;
												
				$scope.wspin = 1;				
				var link = baseUrl+'editWorkstatusImage?wid='+id+'&imagetitle='+$scope.imagetitleedit+'&imagesubtitle='+$scope.imagedescriptionedit+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
				
				var formData=new FormData();
				formData.append("imageedit",imageedit.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editimage = data;
					$scope.wspin = 0;
					if($scope.editimage == 'Success'){
						$scope.getWorkstatusDetails(id);
					} else {
						$scope.errorMsg = $scope.editimage;
					}
				}).error(function(data, status, headers, config) {
					$scope.editimage = data;
					$scope.errorMsg = $scope.editimage;	
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getWorkStatus, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteWorkstatus = function() {
		    angular.forEach($scope.getWorkStatus, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deleteWorkstatus?wid='+item.workstatusId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletestatus = data;
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletestatus = "Response Fail";
		    		});
		    	}
		    });
		    $timeout(function(){
				$window.location.href = url+'manage_workstatus';
			}, 2000);
		}
		
		$scope.removeImageRowEdit = function(workstatusImageId) {
			var link = baseUrl+'deleteWorkstatusImage?workstatusImageId='+workstatusImageId;
    		$http['delete'](link).success(function(data, status, headers, config) {
    			$scope.deleteimagestatus = data;
    			$scope.getWorkstatusDetails($scope.id);
    		}).error(function(data, status, headers, config) {
    			$scope.deleteimagestatus = "Response Fail";
    		});
		}
		
}]);

