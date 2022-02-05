var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('paymentCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getCountries.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getPaymentByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getPayment = data;
		}).error(function(data, status, headers, config) {
			$scope.getPayment = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getPaymentByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPayment = data;
				}).error(function(data, status, headers, config) {
					$scope.getPayment = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getPaymentByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getPayment = data;
			}).error(function(data, status, headers, config) {
				$scope.getPayment = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getPayments';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPayment = data;
				}).error(function(data, status, headers, config) {
					$scope.getPayment = "Response Fail";
				});
			} else {
				var link = baseUrl+'getPaymentByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPayment = data;
				}).error(function(data, status, headers, config) {
					$scope.getPayment = "Response Fail";
				});
			}
		}
		
		$scope.searchPayment = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getPaymentByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPayment = data;
				}).error(function(data, status, headers, config) {
					$scope.getPayment = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchPayments?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getPayment = data;
				}).error(function(data, status, headers, config) {
					$scope.getPayment = "Response Fail";
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
			$scope.errorPaymentLabel = 0;
			$scope.errorPaymentSchedule = 0;
			$scope.errorPaymentPercentage = 0;
			$scope.errorProjectTitle = 0;
		}
		
		
		
		$scope.paymentlist = [];		
		$scope.addPaymentScheduleRow = function() {
									
			 if(!$scope.paymentlabeladd) {				
				$scope.errorPaymentLabel = 1;
				$scope.msgPaymentLabel = "Please enter payment label";
				document.getElementById("paymentlabeladd").focus();
			} else if(!$scope.paymentscheduleadd) {				
				$scope.errorPaymentSchedule = 1;
				$scope.msgPaymentSchedule = "Please enter payment schedule";
				document.getElementById("paymentscheduleadd").focus();
			} else if(!$scope.paymentpercentageadd) {				
				$scope.errorPaymentPercentage = 1;
				$scope.msgPaymentPercen = "Please enter payment percentage";
				document.getElementById("paymentpercentageadd").focus();
			} else 	{			
				$scope.paymentlist.push({'paymentlabel' : $scope.paymentlabeladd, 'paymentschedule' : $scope.paymentscheduleadd, 'paymentpercentage' : $scope.paymentpercentageadd});
			}
		}
		
		$scope.removePaymentScheduleRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.paymentlist.length; i++) {
				if($scope.paymentlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.paymentlist.splice(index, 1);
		};
		
		
		
		
		$scope.addPayment = function() {			
			
			if(!$scope.projectidadd) {				
				$scope.errorProjectTitle = 1;
				$scope.msgProjectTitle = "Please select project name";
				document.getElementById("projectidadd").focus();
			} else {
				var a = 0;
				$scope.spin = 1;				
				/*var link = baseUrl+'addPayment?paymentlabel='+$scope.paymentlabeladd+'&paymentschedule='+$scope.paymentscheduleadd+'&paymentpercentage='+$scope.paymentpercentageadd;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addpayment = data;*/
					
					angular.forEach($scope.paymentlist,function(item) {												
						var link = baseUrl+'addPayment?projectid='+$scope.projectidadd+'&paymentlabel='+item.paymentlabel+'&paymentschedule='+item.paymentschedule+'&paymentpercentage='+item.paymentpercentage;
						
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addpayment = data;
							a = a + 1;
							
							if($scope.paymentlist.length == a ) {
								$scope.spin = 0;
								$scope.paymentSubmitError = 0;
								$scope.paymentSubmitSuccess = 1;
								$scope.successMsg = "Data added";
								$timeout(function(){
									$scope.paymentSubmitSuccess = 0;
									$window.location.href = adminurl+'manage_payment_schedule';
								}, 2000);
							}
						}).error(function(data, status, headers, config) {
							$scope.addpayment = "Response Fail";
						});
					});					
			}
		}
			
		$scope.getPayments = function(projectid) {
			var link = baseUrl+'getPaymentDetailById?projectid='+projectid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.paymentById = data;
			 	
			}).error(function(data, status, headers, config) {
				$scope.paymentById = "Response Fail";
			});
			
			var link = baseUrl+'getProjectIdById?projectid='+projectid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectIdById = data;
				$scope.projectidedit = $scope.projectIdById.projectId;
			}).error(function(data, status, headers, config) {
				$scope.projectIdById = "Response Fail";
			});
		}
		
		$scope.editPayment = function(projectid) {			
			if(!$scope.projectidedit) {				
				$scope.errorProjectTitle = 1;
				$scope.msgProjectTitle = "Please select project name";
				document.getElementById("projectidedit").focus();
			} else if(!$scope.paymentlabeledit) {				
				$scope.errorPaymentLabel = 1;
				$scope.msgPaymentLabel = "Please enter payment label";
				document.getElementById("paymentlabeledit").focus();
			} else if(!$scope.paymentscheduleedit) {				
				$scope.errorPaymentSchedule = 1;
				$scope.msgPaymentSchedule = "Please enter payment schedule";
				document.getElementById("paymentscheduleedit").focus();
			} else if(!$scope.paymentpercentageedit) {				
				$scope.errorPaymentPercentage = 1;
				$scope.msgPaymentPercen = "Please enter payment percentage";
				document.getElementById("paymentpercentageedit").focus();
			} else {
				$scope.spin = 1;
				
				var link = baseUrl+'editPayments?projectid='+$scope.projectidedit+'&paymentlabel='+$scope.paymentlabeledit+'&paymentschedule='+$scope.paymentscheduleedit+'&paymentpercentage='+$scope.paymentpercentageedit;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editpayment = data;
					$scope.spin = 0;					
					if($scope.editpayment == 'Success'){					
						$scope.getPayment = data;
						
						var link = baseUrl+'getPaymentDetailById?projectid='+$scope.projectidedit;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.paymentById = data;
						}).error(function(data, status, headers, config) {
							$scope.paymentById = "Response Fail";
						});
						
						$scope.paymentSubmitError = 0;
						$scope.paymentSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.paymentSubmitSuccess = 0;
							/*$('#editModal').modal('hide');*/
						}, 2000);						
					} else {
						$scope.paymentSubmitSuccess = 0;
						$scope.paymentSubmitError = 1;
						$scope.errorMsg = $scope.editpayment;						
					}
				}).error(function(data, status, headers, config) {
					$scope.editpayment = data;
					$scope.countrySubmitSuccess = 0;
					$scope.countrySubmitError = 1;
					$scope.errorMsg = $scope.editpayment;
				});
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getPayment, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deletePayment = function() {
		    angular.forEach($scope.getPayment, function(item) {
		    	if(item.selected) {
		    		var link = baseUrl+'deletePayment?paymentid='+item.paymentId;
		    		$http['delete'](link).success(function(data, status, headers, config) {
		    			$scope.deletepayment = data;
		    		}).error(function(data, status, headers, config) {
		    			$scope.deletepayment = "Response Fail";
		    		});
		    	}
		    });		    
		    var link = baseUrl+'getPaymentByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getPayment = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getPayment = "Response Fail";
			});
		}		
}]);