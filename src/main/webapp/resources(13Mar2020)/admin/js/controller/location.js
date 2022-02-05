var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('locationCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];		
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getLocations.length/$scope.pageSize);
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
		
		$scope.getTalukaByDistrictId = function(districtname) {
			if(!districtname) {
				$scope.talukanameadd = "";
				$scope.talukaname = "";
				$scope.getTalukas = "";
			} else {
				var link = baseUrl+'getTalukaByDistrictId?districtid='+districtname;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getLocations = data;
		}).error(function(data, status, headers, config) {
			$scope.getLocations = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
			
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getLocations = data;
				}).error(function(data, status, headers, config) {
					$scope.getLocations = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;			
			var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getLocations = data;
			}).error(function(data, status, headers, config) {
				$scope.getLocations = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getLocations';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getLocations = data;
				}).error(function(data, status, headers, config) {
					$scope.getLocations = "Response Fail";
				});
			} else {
				var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getLocations = data;
				}).error(function(data, status, headers, config) {
					$scope.getLocations = "Response Fail";
				});
			}
		}
		
		$scope.searchLocation = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getLocations = data;
				}).error(function(data, status, headers, config) {
					$scope.getLocations = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchLocations?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getLocations = data;
				}).error(function(data, status, headers, config) {
					$scope.getLocations = "Response Fail";
				});
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorLocationName = 0;
			$scope.errorCountry = 0;
			$scope.errorState = 0;
			$scope.errorDistrict = 0;
			$scope.errorLocationName = 0;
		}
		
		$scope.addLocation = function() {			
			if(!$scope.locationcodeadd) {
				$scope.locationcodeadd = "";
			}
			if(!$scope.talukanameadd) {
				$scope.talukanameadd = 0;
			}
			if(!$scope.cityvillageadd) {
				$scope.cityvillageadd = "";
			}
			if(!$scope.mojeadd) {
				$scope.mojeadd = "";
			}
			if(!$scope.areaadd) {
				$scope.areaadd = "";
			}
			if(!$scope.zipadd) {
				$scope.zipadd = "";
			}
			if(!$scope.citysurveyadd) {
				$scope.citysurveyadd = "";
			}
			if(!$scope.tpadd) {
				$scope.tpadd = "";
			}
			if(!$scope.gmaplinkadd) {
				$scope.gmaplinkadd = "";
			}
			if(!$scope.latitudeadd) {
				$scope.latitudeadd = 0;
			}
			if(!$scope.longitudeadd) {
				$scope.longitudeadd = 0;
			}

			if(!$scope.locationnameadd) {				
				$scope.errorLocationName = 1;
				$scope.msgLocationName = "Please enter location name";
				document.getElementById("locationnameadd").focus();
			} else if(!$scope.countrynameadd) {				
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
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'addLocation?locationname='+$scope.locationnameadd+'&locationcode='+$scope.locationcodeadd+'&countryname='+$scope.countrynameadd+'&statename='+$scope.statenameadd+'&districtname='+$scope.districtnameadd+'&talukaname='+$scope.talukanameadd+'&cityvillage='+$scope.cityvillageadd+'&moje='+$scope.mojeadd+'&area='+$scope.areaadd+'&zip='+$scope.zipadd+'&citysurvey='+$scope.citysurveyadd+'&tp='+$scope.tpadd+'&gmaplink='+$scope.gmaplinkadd+'&latitude='+$scope.latitudeadd+'&longitude='+$scope.longitudeadd;				
				var formData=new FormData();				
				formData.append("locationmap",locationmap.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addlocation = data;
					$scope.spin = 0;					
					if($scope.addlocation == 'Success'){
						$scope.locationSubmitError = 0;
						$scope.locationSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.locationSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_location';
						}, 2000);
					} else {
						$scope.locationSubmitSuccess = 0;
						$scope.locationSubmitError = 1;
						$scope.errorMsg = $scope.addlocation;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addlocation = data;
					$scope.locationSubmitSuccess = 0;
					$scope.locationSubmitError = 1;
					$scope.errorMsg = $scope.addlocation;
				});
			}
		}
			
		$scope.getLocation = function(locationid) {
			
			var link = baseUrl+'getLocationDetailById?locationid='+locationid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.locationDetailById = data;
				
				$scope.locationid = $scope.locationDetailById.locationId;
				$scope.locationname = $scope.locationDetailById.locationName;
            	$scope.locationcode = $scope.locationDetailById.locationCode;
            	$scope.countryname = $scope.locationDetailById.countryId;
				$scope.statename = $scope.locationDetailById.stateId;           	
            	var link = baseUrl+'getDistrictByStateId?stateid='+$scope.statename;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getDistricts = data;
					$scope.districtname = $scope.locationDetailById.districtId;
				}).error(function(data, status, headers, config) {
					$scope.getDistricts = "Response Fail";
				});
				var link = baseUrl+'getTalukaByDistrictId?districtid='+$scope.districtname;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getTalukas = data;
					$scope.talukaname = $scope.locationDetailById.talukaId;
				}).error(function(data, status, headers, config) {
					$scope.getTalukas = "Response Fail";
				});
				
				$scope.cityvillage = $scope.locationDetailById.cityVillage;
				$scope.moje = $scope.locationDetailById.moje;				
				$scope.area = $scope.locationDetailById.area;
				$scope.citysurvey = $scope.locationDetailById.citySurveyNo;
				$scope.tp = $scope.locationDetailById.tp;
				$scope.gmaplink = $scope.locationDetailById.gMapLink;
				$scope.latitude = $scope.locationDetailById.latitude;
				$scope.longitude = $scope.locationDetailById.longitude;
				$scope.locationmap = $scope.locationDetailById.locationMap;
            	
			}).error(function(data, status, headers, config) {
				$scope.locationDetailById = "Response Fail";
			});		
		}
		
		$scope.editLocation = function(locationid) {
			if(!$scope.locationcode) {
				$scope.locationcode = "";
			}
			if(!$scope.talukaname) {
				$scope.talukaname = 0;
			}
			if(!$scope.cityvillage) {
				$scope.cityvillage = "";
			}
			if(!$scope.moje) {
				$scope.moje = "";
			}
			if(!$scope.area) {
				$scope.area = "";
			}
			if(!$scope.zip) {
				$scope.zip = "";
			}
			if(!$scope.citysurvey) {
				$scope.citysurvey = "";
			}
			if(!$scope.tp) {
				$scope.tp = "";
			}
			if(!$scope.gmaplink) {
				$scope.gmaplink = "";
			}
			if(!$scope.latitude) {
				$scope.latitude = 0;
			}
			if(!$scope.longitude) {
				$scope.longitude = 0;
			}

			if(!$scope.locationname) {				
				$scope.errorLocationName = 1;
				$scope.msgLocationName = "Please enter location name";
				document.getElementById("locationname").focus();
			} else if(!$scope.countryname) {				
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
			} else {
				$scope.spin = 1;				
				var link = baseUrl+'editLocation?locationid='+locationid+'&locationname='+$scope.locationname+'&locationcode='+$scope.locationcode+'&countryname='+$scope.countryname+'&statename='+$scope.statename+'&districtname='+$scope.districtname+'&talukaname='+$scope.talukaname+'&cityvillage='+$scope.cityvillage+'&moje='+$scope.moje+'&area='+$scope.area+'&zip='+$scope.zip+'&citysurvey='+$scope.citysurvey+'&tp='+$scope.tp+'&gmaplink='+$scope.gmaplink+'&latitude='+$scope.latitude+'&longitude='+$scope.longitude+'&locationmap='+$scope.locationmap;
				var formData=new FormData();				
				formData.append("locationmapedit",locationmapedit.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editlocation = data;
					$scope.spin = 0;					
					if($scope.editlocation == 'Success'){
						var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getLocations = data;
							
							$scope.locationSubmitError = 0;
							$scope.locationSubmitSuccess = 1;
							$scope.successMsg = "Data updated";
							$timeout(function(){
								$scope.locationSubmitSuccess = 0;
								$('#editModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getLocations = "Response Fail";
						});						
					} else {
						$scope.locationSubmitSuccess = 0;
						$scope.locationSubmitError = 1;
						$scope.errorMsg = $scope.editlocation;						
					}				
				}).error(function(data, status, headers, config) {
					$scope.editlocation = data;
					$scope.locationSubmitSuccess = 0;
					$scope.locationSubmitError = 1;
					$scope.errorMsg = $scope.editlocation;
				});
			}
		}
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getLocations, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}	
		
		$scope.deleteLocation = function() {
			angular.forEach($scope.getLocations, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteLocation?locationid='+item.locationId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deletelocation = data;
					}).error(function(data, status, headers, config) {
						$scope.deletelocation = "Response Fail";
					});
				}
				var link = baseUrl+'getLocationsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getLocations = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getLocations = "Response Fail";
				});
			});			
		}	
}]);