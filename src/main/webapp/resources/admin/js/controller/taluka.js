var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('talukaCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getTalukas.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		var link = baseUrl+'getCountries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCountries = data;
		}).error(function(data, status, headers, config) {
			$scope.getCountries = "Response Fail";
		});
		
		var link = baseUrl+'getStateByCountryId?countryid='+1;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getStates = data;
		}).error(function(data, status, headers, config) {
			$scope.getStates = "Response Fail";
		});	
		
		$scope.getStateByCountryId = function(countryname) {
			if(!countryname) {
				$scope.statenameadd = "";
				$scope.statename = "";
				$scope.getStates = "";
			} else {
				var link = baseUrl+'getStateByCountryId?countryid='+countryname;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getStates = data;
				}).error(function(data, status, headers, config) {
					$scope.getStates = "Response Fail";
				});
			}
		}
		
		$scope.getDistrictByStateId = function(statename) {
			if(!statename) {
				$scope.districtnameadd = "";
				$scope.districtname = "";
				$scope.getDistricts = "";
			} else {
				var link = baseUrl+'getDistrictByStateId?stateid='+statename;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getTalukas = data;
		}).error(function(data, status, headers, config) {
			$scope.getTalukas = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
			
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;			
			var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getTalukas = data;
			}).error(function(data, status, headers, config) {
				$scope.getTalukas = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getTalukas';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			} else {
				var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			}
		}
		
		$scope.searchTaluka = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchTalukas?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorCountry = 0;
			$scope.errorState = 0;
			$scope.errorDistrict = 0;
			$scope.errorTalukaName = 0;
		}
		
		$scope.addTaluka = function() {			
			if(!$scope.talukacodeadd) {
				$scope.talukacodeadd = "";
			}

			if(!$scope.countrynameadd) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countrynameadd").focus();
			} else if(!$scope.statenameadd) {				
				$scope.errorState = 1;
				$scope.msgState = "Please enter state name";
				document.getElementById("statenameadd").focus();
			} else if(!$scope.districtnameadd) {				
				$scope.errorDistrict = 1;
				$scope.msgDistrict = "Please enter district name";
				document.getElementById("districtnameadd").focus();
			} else if(!$scope.talukanameadd) {				
				$scope.errorTalukaName = 1;
				$scope.msgTalukaName = "Please enter taluka name";
				document.getElementById("talukanameadd").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addTaluka?countryname='+$scope.countrynameadd+'&statename='+$scope.statenameadd+'&districtname='+$scope.districtnameadd+'&talukaname='+$scope.talukanameadd+'&talukacode='+$scope.talukacodeadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addtaluka = data;
					$scope.spin = 0;					
					if($scope.addtaluka == 'Success'){
						$scope.talukaSubmitError = 0;
						$scope.talukaSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.talukaSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_taluka';
						}, 2000);
					} else {
						$scope.talukaSubmitSuccess = 0;
						$scope.talukaSubmitError = 1;
						$scope.errorMsg = $scope.addtaluka;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addtaluka = data;
					$scope.talukaSubmitSuccess = 0;
					$scope.talukaSubmitError = 1;
					$scope.errorMsg = $scope.addtaluka;
				});
			}
		}
			
		$scope.getTaluka = function(talukaid) {
			
			var link = baseUrl+'getTalukaDetailById?talukaid='+talukaid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.talukaDetailById = data;
				
				$scope.talukaid = $scope.talukaDetailById.talukaId;
				
				$scope.statename = $scope.talukaDetailById.stateId;
            	$scope.countryname = $scope.talukaDetailById.countryId;
            	$scope.talukaname = $scope.talukaDetailById.talukaName;
            	$scope.talukacode = $scope.talukaDetailById.talukaCode;
            	
            	var link = baseUrl+'getDistrictByStateId?stateid='+$scope.statename;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
					$scope.districtname = $scope.talukaDetailById.districtId;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
            	
			}).error(function(data, status, headers, config) {
				$scope.talukaDetailById = "Response Fail";
			});		
		}
		
		$scope.editTaluka = function(talukaid) {
			if(!$scope.talukacode) {
				$scope.talukacode = "";
			}

			if(!$scope.countryname) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countryname").focus();
			} else if(!$scope.statename) {				
				$scope.errorState = 1;
				$scope.msgState = "Please enter state name";
				document.getElementById("statename").focus();
			} else if(!$scope.districtname) {				
				$scope.errorDistrict = 1;
				$scope.msgDistrict = "Please enter district name";
				document.getElementById("districtname").focus();
			} else if(!$scope.talukaname) {				
				$scope.errorTalukaName = 1;
				$scope.msgTalukaName = "Please enter taluka name";
				document.getElementById("talukaname").focus();
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editTaluka?talukaid='+talukaid+'&countryname='+$scope.countryname+'&statename='+$scope.statename+'&districtname='+$scope.districtname+'&talukaname='+$scope.talukaname+'&talukacode='+$scope.talukacode;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.edittaluka = data;
					$scope.spin = 0;					
					if($scope.edittaluka == 'Success'){
						var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getTalukas = data;
							
							$scope.talukaSubmitError = 0;
							$scope.talukaSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.talukaSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getTalukas = "Response Fail";
						});						
					} else {
						$scope.talukaSubmitSuccess = 0;
						$scope.talukaSubmitError = 1;
						$scope.errorMsg = $scope.edittaluka;						
					}				
				}).error(function(data, status, headers, config) {
					$scope.edittaluka = data;
					$scope.talukaSubmitSuccess = 0;
					$scope.talukaSubmitError = 1;
					$scope.errorMsg = $scope.edittaluka;
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getTalukas, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}	
		
		$scope.deleteTaluka = function() {
			angular.forEach($scope.getTalukas, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteTaluka?talukaid='+item.talukaId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletetaluka = data;
					}).error(function(data, status, headers, config) {
						$scope.deletetaluka = "Response Fail";
					});
				}
				var link = baseUrl+'getTalukasByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			});			
		}	
}]);