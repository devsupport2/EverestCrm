var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('testimonialCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout',
	function ($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.currentPage = 0;
	$scope.pageSize = 20;
	$scope.search = '';
	$scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
    
    $scope.numberOfPages=function() {
		return Math.ceil($scope.getTestimonial.length/$scope.pageSize);
	}
	
	var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
	}).error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});
	
	var link = baseUrl+'getFeedbackByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getFeedback = data;
	}).error(function(data, status, headers, config) {
		$scope.getFeedback = "Response Fail";
	});
	
	$scope.next = function() {
		$scope.search = '';
		if($scope.pageSize == "All") {
				
		} else {
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;				
			var link = baseUrl+'getFeedbackByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFeedback = data;
			}).error(function(data, status, headers, config) {
				$scope.getFeedback = "Response Fail";
			});
		}
	}
	
	$scope.prev = function() {
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		
		var link = baseUrl+'getFeedbackByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getFeedback = data;
		}).error(function(data, status, headers, config) {
			$scope.getFeedback = "Response Fail";
		});
	}
	
	$scope.changePage = function() {
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		
		if($scope.pageSize == "All") {
			var link = baseUrl+'getAllFeedback';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFeedback = data;
			}).error(function(data, status, headers, config) {
				$scope.getFeedback = "Response Fail";
			});
		} else {
			var link = baseUrl+'getFeedbackByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFeedback = data;
			}).error(function(data, status, headers, config) {
				$scope.getFeedback = "Response Fail";
			});
		}
	}
	
	$scope.searchFeedback = function() {
		var search = $scope.search;		
		if(search == undefined || search == "") {
			var link = baseUrl+'getFeedbackByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFeedback = data;
			}).error(function(data, status, headers, config) {
				$scope.getFeedback = "Response Fail";
			});
		} else {
			var link = baseUrl+'searchFeedback?keyword='+search;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getFeedback = data;
			}).error(function(data, status, headers, config) {
				$scope.getFeedback = "Response Fail";
			});
		}
	}
	
	var link = baseUrl+'getUsers';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getUsersName = data;		
	}).error(function(data, status, headers, config) {
		$scope.getUsersName = "Response Fail";
	});
	
	$scope.setFlag = function() {
		$scope.errorUserName = 0;
		$scope.msgUserName = "";
		$scope.errorCity = 0;
		$scope.msgCity = "";
		$scope.errorProgram = 0;
		$scope.errorUser = 0;
		$scope.errorFeedback = 0;
	}
	
	$scope.addFeedback = function() {
		if($scope.lastnameadd==undefined) {
			$scope.lastnameadd = "";
		}
		if($scope.orgnaizationadd==undefined) {
			$scope.orgnaizationadd = "";
		}
		if($scope.firstnameadd==undefined || $scope.firstnameadd=="") {
			$scope.firstnameadd = "";
		}
		if($scope.useridadd==undefined || $scope.useridadd=="") {
			$scope.useridadd = 0;
		}
		
		var valuex = document.getElementById("valuex").value;
		var valuey = document.getElementById("valuey").value;
		var valuew = document.getElementById("valuew").value;
		var valueh = document.getElementById("valueh").value;
		
		if(valuex == ''){
			valuex = 0;
		}
		if(valuey == ''){
			valuey = 0;
		}
		if(valuew == ''){
			valuew = 0;
		}
		if(valueh == ''){
			valueh = 0;
		}
				
		if(!$scope.useridadd && !$scope.firstnameadd) {			
			$scope.errorUser = 1;
			$scope.msgUser = "Please select user or enter first name ";
			document.getElementById("useridadd").focus();
		} else if($scope.messageadd==undefined || $scope.messageadd=="") {			
			$scope.errorFeedback = 1;
			$scope.msgFeedback = "Please enter feedback";
			document.getElementById("messageadd").focus();
		} else {
			$scope.spin = 1;				
			
			$scope.messageadd = $scope.messageadd.replace("&","$");
			$scope.messageadd = $scope.messageadd.replace("#","~");
			$scope.messageadd = $scope.messageadd.replace("%","!");
			
			var link = baseUrl+'addFeedback?userid='+$scope.useridadd+'&firstname='+$scope.firstnameadd+'&lastname='+$scope.lastnameadd+'&orgnaization='+$scope.orgnaizationadd+'&feedbackdis='+$scope.messageadd+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;		
			var formData=new FormData();
			formData.append("image",imageadd.files[0]);
			$http({method: 'POST',url: link,headers: {'Content-Type': undefined},data: formData,transformRequest: function(data, headersGetterFunction) {return data;}}).success(function(data, status, headers, config)	{
				$scope.addfeedback = data;
				$scope.spin = 0;				
				if($scope.addfeedback == 'Success'){
					$scope.submitSuccess = 1;
					$scope.submitError = 0;
					$scope.msgSuccess = "Data added";
					$timeout(function(){
						$scope.submitSuccess = 0;
						$window.location.href = adminurl+'manage_feedback';
					}, 2000);
				} else {
					$scope.submitSuccess = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addfeedback;						
				}
				
			}).error(function(data, status, headers, config) {
				$scope.addfeedback = data;
				$scope.submitSuccess = 0;
				$scope.submitError = 1;
				$scope.msgError = $scope.addfeedback;	
			});			   			
		}
	}
	
	$scope.getFeedbackDetail = function(feedbackid) {
		var link = baseUrl+'getFeedbackById?feedbackid='+feedbackid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getFeedbackById = data;
			$scope.feedbackid = $scope.getFeedbackById.feedbackId;
			$scope.programidedit = $scope.getFeedbackById.programId;
			$scope.useridedit = $scope.getFeedbackById.userId;
			$scope.firstnameedit = $scope.getFeedbackById.userFirstName;
			$scope.lastnameedit = $scope.getFeedbackById.userLastName;
			$scope.orgnaizationnameedit = $scope.getFeedbackById.orgnaizationName;
			$scope.feedbackedit = $scope.getFeedbackById.feedback;
			$scope.oldimage = $scope.getFeedbackById.image;
		}).error(function(data, status, headers, config) {
			$scope.getFeedbackById = "Response Fail";
		});
	}
	
	$scope.editFeedback = function(feedbackid) {
		if($scope.lastnameedit==undefined) {
			$scope.lastnameedit = "";
		}
		if($scope.orgnaizationnameedit==undefined) {
			$scope.orgnaizationnameedit = "";
		}
		if($scope.firstnameedit==undefined || $scope.firstnameedit=="") {
			$scope.firstnameedit = "";
		}
		if($scope.useridedit==undefined || $scope.useridedit=="") {
			$scope.useridedit = 0;
		}
		
		var valuex = document.getElementById("valuex1").value;
		var valuey = document.getElementById("valuey1").value;
		var valuew = document.getElementById("valuew1").value;
		var valueh = document.getElementById("valueh1").value;
		
		if(valuex == ''){
			valuex = 0;
		}
		if(valuey == ''){
			valuey = 0;
		}
		if(valuew == ''){
			valuew = 0;
		}
		if(valueh == ''){
			valueh = 0;
		}
		
		if(!$scope.useridedit && !$scope.firstnameedit) {			
			$scope.errorUser = 1;
			$scope.msgUser = "Please select user or enter first name ";
			document.getElementById("useridedit").focus();
		} else if($scope.feedbackedit==undefined || $scope.feedbackedit=="") {			
			$scope.errorFeedback = 1;
			$scope.msgFeedback = "Please enter feedback";
			document.getElementById("feedbackedit").focus();
		} else {
			$scope.spin = 1;
			
			$scope.feedbackedit = $scope.feedbackedit.replace("&","$");
			$scope.feedbackedit = $scope.feedbackedit.replace("#","~");
			$scope.feedbackedit = $scope.feedbackedit.replace("%","!");
			
			var link = baseUrl+'editFeedback?feedbackid='+$scope.feedbackid+'&userid='+$scope.useridedit+'&firstname='+$scope.firstnameedit+'&lastname='+$scope.lastnameedit+'&orgnaization='+$scope.orgnaizationnameedit+'&feedbackdis='+$scope.feedbackedit+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&oldimage='+$scope.oldimage;
			var formData=new FormData();
			formData.append("image",imageedit.files[0]);
			$http({method: 'POST',url: link,headers: {'Content-Type': undefined},data: formData,transformRequest: function(data, headersGetterFunction) {return data;}}).success(function(data, status, headers, config) {
				$scope.editfeedback = data;
				$scope.spin = 0;
				
				if($scope.editfeedback == 'Success'){
					$scope.submitSuccess = 1;
					$scope.submitError = 0;
					$scope.msgSuccess = "Data update successfully";
					$timeout(function(){
						$scope.submitSuccess = 0;
						$window.location.href = adminurl+'manage_feedback';
					}, 2000);
				} else {
					$scope.submitSuccess = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.editfeedback;
				}
				
			}).error(function(data, status, headers, config) {
				$scope.editfeedback = data;
				$scope.submitSuccess = 0;
				$scope.submitError = 1;
				$scope.msgError = $scope.editfeedback;	
			});    			
		}
	}
	
	$scope.checkRecordSelectForDelete = function() {
		$scope.d = 0;
		angular.forEach($scope.getFeedback,function(item) {
			if (item.selected) {
				$scope.d = $scope.d + 1
			}
		});
	}
	
	$scope.deleteFeedback = function() {
		angular.forEach($scope.getFeedback, function(item) {
			if (item.selected) {
				var link = baseUrl+'deleteFeedback?feedbackid='+item.feedbackId;
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deletefeedback = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.deletefeedback = "Response Fail";
				});
			}
		});
		var link = baseUrl+'getFeedbackByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getFeedback = data;
		}).error(function(data, status, headers, config) {
			$scope.getFeedback = "Response Fail";
		});
	}
}]);