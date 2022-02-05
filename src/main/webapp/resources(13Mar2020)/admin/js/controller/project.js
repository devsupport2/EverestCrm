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

app.controller('projectCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
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
			
		var link = baseUrl+'getRealestateName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getRealestateName = data;
		}).error(function(data, status, headers, config) {
			$scope.getRealestateName = "Response Fail";
		});
		
		var link = baseUrl+'getMeasurementUnits';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMeasurementUnits = data;
		}).error(function(data, status, headers, config) {
			$scope.getMeasurementUnits = "Response Fail";
		});
		
		var link = baseUrl+'getAreaName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAreaName = data;
		}).error(function(data, status, headers, config) {
			$scope.getAreaName = "Response Fail";
		});
		
		var link = baseUrl+'getProjectsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProjects = data;
		}).error(function(data, status, headers, config) {
			$scope.getProjects = "Response Fail";
		});
		
		var link = baseUrl+'getLocationName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getLocationName = data;
		}).error(function(data, status, headers, config) {
			$scope.getLocationName = "Response Fail";
		});
		
		var link = baseUrl+'getUserTypes';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserTypes = data;
		}).error(function(data, status, headers, config) {
			$scope.getUserTypes = "Response Fail";
		});
		
		var link = baseUrl+'getPropertyName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getPropertyName = data;
		}).error(function(data, status, headers, config) {
			$scope.getPropertyName = "Response Fail";
		});
		
		var link = baseUrl+'getUserName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserName = data;
		}).error(function(data, status, headers, config) {
			$scope.getUserName = "Response Fail";
		});
		
		var link = baseUrl+'getUserNameByUserType?usertypeid='+"6";
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getArchitectName = data;
		}).error(function(data, status, headers, config) {
			$scope.getArchitectName = "Response Fail";
		});
		
		var link = baseUrl+'getUserNameByUserType?usertypeid='+"27";
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getDesignerName = data;
		}).error(function(data, status, headers, config) {
			$scope.getDesignerName = "Response Fail";
		});
		
		var link = baseUrl+'getUserNameByUserType?usertypeid='+"28";
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAdvisorName = data;
		}).error(function(data, status, headers, config) {
			$scope.getAdvisorName = "Response Fail";
		});
		
		var link = baseUrl+'getCountries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getCountries = data;
		}).error(function(data, status, headers, config) {
			$scope.getCountries = "Response Fail";
		});
		
		var link = baseUrl+'getUnitNameList';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.unitNameList = data;			
		}).error(function(data, status, headers, config) {
			$scope.unitNameList = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getProjectsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProjects = data;
				}).error(function(data, status, headers, config) {
					$scope.getProjects = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getProjectsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getProjects = data;
			}).error(function(data, status, headers, config) {
				$scope.getProjects = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getProjects';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProjects = data;
				}).error(function(data, status, headers, config) {
					$scope.getProjects = "Response Fail";
				});
			} else {
				var link = baseUrl+'getProjectsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProjects = data;
				}).error(function(data, status, headers, config) {
					$scope.getProjects = "Response Fail";
				});
			}
		}
		
		$scope.searchProject = function() {					
			if(!$scope.search) {
				var link = baseUrl+'getProjectsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProjects = data;
				}).error(function(data, status, headers, config) {
					$scope.getProjects = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchProject?keyword='+$scope.search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProjects = data;
				}).error(function(data, status, headers, config) {
					$scope.getProjects = "Response Fail";
				});
			}
		}
		
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
		
		$scope.onChangeRealestateCategory = function(categoryid) {						
			if(!categoryid) {				
				$scope.getSubcategoryTitles = "";				
			} else {			
				var link = baseUrl+'getAllRealestateSubcategoryTitleByRealestateId?realestateid='+categoryid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategoryTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategoryTitles = "Response Fail";
				});		
				
			}
		}
		
		$scope.onChangeRealestateSubcategory = function() {
			if($scope.realestatesubcategoryidadd  == null || $scope.realestatesubcategoryidadd==undefined) {				
				$scope.getRealestateTitles = "";				
			} else {			
				var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.realestatesubcategoryidadd;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRealestateTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getRealestateTitles = "Response Fail";
				});				
			}
		}	
		
		$scope.onChangeRealestateSubcategoryLayout = function() {
			if($scope.layoutrealestatesubcategoryidadd  == null || $scope.layoutrealestatesubcategoryidadd==undefined) {				
				$scope.layoutrealestatesubcategoryidadd = "";
			} else {
				var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.layoutrealestatesubcategoryidadd;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRealestateTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getRealestateTitles = "Response Fail";
				});
			}
		}
		
		$scope.onChangeRealestateedit = function() {						
			if($scope.categoryid  == null || $scope.categoryid==undefined) {				
				$scope.getSubcategoryTitles = "";				
			} else {			
				var link = baseUrl+'getAllRealestateSubcategoryTitleByRealestateId?realestateid='+$scope.categoryid;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategoryTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategoryTitles = "Response Fail";
				});			
			}
		}		
		
		$scope.onChangeRealestateTypeedit = function() {						
			if($scope.subcategoryid  == null || $scope.subcategoryid==undefined) {				
				$scope.getRealestateTitles = "";				
			} else {			
				var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.subcategoryid;								
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRealestateTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getRealestateTitles = "Response Fail";
				});				
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorUserType = 0;
			$scope.errorFirstName = 0;
			$scope.errorCountryName = 0;
			$scope.errorCountry = 0;
			$scope.errorStateName = 0;
			$scope.errorLocationName = 0;
			$scope.errorState = 0;
			$scope.errorDistrict = 0;
			$scope.errorProjectTitle = 0;
			$scope.errorLocation = 0;
			$scope.errorSpecDescription = 0;
			$scope.errorAmenityDescription = 0;
			$scope.errorTowerName = 0;
			$scope.errorBHK = 0;
			$scope.errorUnits = 0;
			$scope.errorRealestateSubcategoryTitle = 0;
			$scope.errorRealestateCategoryTitle = 0;
			$scope.errorRealestateTypeTitle = 0;
			$scope.errorRealestateSubCategoryTitle = 0;
			$scope.errorRealestateTitle = 0;
			$scope.errorAreaU = 0;
			$scope.errorPriceLable = 0;
			$scope.errorPriceValue = 0;			
			$scope.errorSequence = 0;
			$scope.errorSequenceTitle = 0;
			$scope.errorLable = 0;
			$scope.errorValue = 0;
			$scope.errorUnit = 0;
			$scope.errorAreaCategoryId = 0;
			$scope.errorAreaSubcategoryId = 0;
			$scope.errorAreaTypeId = 0;
			$scope.errorAreaId = 0;
			$scope.errorAreaUnitId = 0;
			$scope.errorAreaValue = 0;
			$scope.errorUnitName = 0;
			$scope.errorCategoryTitle = 0;
			$scope.errorCategoryCode = 0;
			$scope.errorRealSubCode = 0
			$scope.errorRealSubTitle = 0;
			$scope.errorTypeCode = 0;
			$scope.errorTypeTitle = 0;
			$scope.errorCompanyName = 0;
			
			$scope.errorTower = 0;
			$scope.errorFloor = 0;
			$scope.errorSeq = 0;
			
			
		}		
		
		$scope.paymentschedulelist = [];
		$scope.addPaymentScheduleRow = function() {
			
			if(!$scope.sequencetitleadd){
				$scope.sequencetitleadd="";
			}
			
			if(!$scope.sequenceadd) {				
				$scope.errorSequence = 1;
				$scope.msgSequence = "Please sequence number!";
				document.getElementById("sequenceadd").focus();
			} else if(!$scope.lableadd) {				
				$scope.errorLable = 1;
				$scope.msgLables = "Please enter lable!";
				document.getElementById("lableadd").focus();
			} else if(!$scope.valueadd) {				
				$scope.errorValue = 1;
				$scope.msgValues = "Please enter value!";
				document.getElementById("valueadd").focus();
			} else if(!$scope.unittypeadd) {				
				$scope.errorUnit = 1;
				$scope.msgUnittype = "Please select unit type!";
				document.getElementById("unittypeadd").focus();
			} else {				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.unittypeadd) {
						$scope.unit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}					
				}
				
				$scope.paymentschedulelist.push({'sequence' : $scope.sequenceadd, 'sequencetitle' : $scope.sequencetitleadd, 'lable' : $scope.lableadd, 'value' : $scope.valueadd, 'measurementUnitId' : $scope.unittypeadd, 'unit' : $scope.unit});
			}
		}
		
		$scope.removePaymentScheduleRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.paymentschedulelist.length; i++) {
				if($scope.paymentschedulelist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.paymentschedulelist.splice(index, 1);
		};		
		
		$scope.editProjectPriceInfo = function(projectid) {
			if(!$scope.priceinfounitmasterid) {
				$scope.priceinfounitmasterid=0;
			}
			if(!$scope.categoryid) {				
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryTypes = "Please category type!";
				document.getElementById("categoryid").focus();
			} else if(!$scope.subcategoryid) {				
				$scope.errorRealestateSubCategoryTitle = 1;
				$scope.msgSubCategoryTypes = "Please subcategory type!";
				document.getElementById("subcategoryid").focus();
			} else if(!$scope.realestateid) {				
				$scope.errorRealestateTitle = 1;
				$scope.msgTypes = "Please realestate type!";
				document.getElementById("realestateid").focus();
			} else if(!$scope.pricelableedit) {				
				$scope.errorPriceLable = 1;
				$scope.msgLable = "Please enter price lable!";
				document.getElementById("pricelableedit").focus();
			} else if(!$scope.pricevalueedit) {				
				$scope.errorPriceValue = 1;
				$scope.msgValue = "Please enter price value !";
				document.getElementById("pricevalueedit").focus();
			} else if(!$scope.areaunittypeedit) {				
				$scope.errorAreaU = 1;
				$scope.msgAreaU = "Please select unit type!";
				document.getElementById("areaunittypeedit").focus();
			} else {
				$scope.projectpricespin = 1;					
				
				var link = baseUrl+'editProjectPriceDetails?projectid='+projectid+'&categoryid='+$scope.categoryid+'&subcategoryid='+$scope.subcategoryid+'&realestateid='+$scope.realestateid+'&unitmasterid='+$scope.priceinfounitmasterid+'&pricelable='+$scope.pricelableedit+'&pricevalue='+$scope.pricevalueedit+'&measurementunitid='+$scope.areaunittypeedit;				
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editprojectpriceinfo = data;
					var link = baseUrl+'getProjectPriceDetailsById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectPriceDetails = data;
					}).error(function(data, status, headers, config) {
						$scope.projectPriceDetails = "Response Fail";
					});
					$scope.projectpricespin = 0;					
					if($scope.editprojectinfo == 'Success'){						
						
					} else {
						$scope.errorMsg = $scope.editprojectpriceinfo;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editprojectpriceinfo = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editprojectpriceinfo;
				});   			
			}
		}
		
		
		
		$scope.projectdetaillist = [];		
		$scope.addProjectInfoRow = function() {	
			
			if(!$scope.towertitleadd){
				$scope.towertitleadd=0;
			}			
			if(!$scope.numberofunits){
				$scope.numberofunits="";
			}
			if(!$scope.realestatecategoryidadd) {				
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryType = "Please category type!";
				document.getElementById("realestatecategoryidadd").focus();
			} else if(!$scope.realestatesubcategoryidadd) {				
				$scope.errorRealestateSubCategoryTitle = 1;
				$scope.msgSubCategoryType = "Please subcategory type!";
				document.getElementById("realestatesubcategoryidadd").focus();
			} else if(!$scope.realestatetypeidadd) {				
				$scope.errorRealestateTitle = 1;
				$scope.msgType = "Please realestate type!";
				document.getElementById("realestatetypeidadd").focus();
			} else {
				for (i in $scope.getRealestateName) {
					if ($scope.getRealestateName[i].realestateTypeId == $scope.realestatecategoryidadd) {
						$scope.realestatecategorytitle = $scope.getRealestateName[i].realestateTypeName;
						break;
					}
				}
				for (i in $scope.getSubcategoryTitles) {
					if ($scope.getSubcategoryTitles[i].realestateSubcategoryId == $scope.realestatesubcategoryidadd) {
						$scope.realestatesubcategorytitle = $scope.getSubcategoryTitles[i].subcategoryTitle;
						break;
					}
				}
				for (i in $scope.getRealestateTitles) {
					if ($scope.getRealestateTitles[i].realestateId == $scope.realestatetypeidadd) {
						$scope.realestatetypetitle = $scope.getRealestateTitles[i].realestateTitle;
						break;
					}
				}
				for (i in $scope.unitNameList) {
					if ($scope.unitNameList[i].unitMasterId == $scope.towertitleadd) {
						$scope.unitname = $scope.unitNameList[i].unitName;
						break;
					}
				}
				$scope.projectdetaillist.push({'categoryTitle' : $scope.realestatecategorytitle, 'categoryId' : $scope.realestatecategoryidadd, 'subcategoryTitle' : $scope.realestatesubcategorytitle, 'subcategoryId' : $scope.realestatesubcategoryidadd, 'typeTitle' : $scope.realestatetypetitle, 'typeId' : $scope.realestatetypeidadd, 'unitTitle' : $scope.unitname, 'unitId' : $scope.towertitleadd, 'totalUnit' : $scope.numberofunits});
			}
		}
		
		$scope.removeProjectInfoRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectdetaillist.length; i++) {
				if($scope.projectdetaillist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectdetaillist.splice(index, 1);
		};
		
		
		
		$scope.projectfloorlayoutlist = [];	
		var LayoutImage = new FormData();
		$scope.addProjectFloorLayoutRow = function() {	
								
			if(!$scope.layoutnumberofunits){
				$scope.layoutnumberofunits="";
			}
			if(!$scope.layouttowertitleadd){
				$scope.layouttowertitleadd=0;
			}
			if(!$scope.layoutrealestatecategoryidadd) {				
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryType1 = "Please category type!";
				document.getElementById("layoutrealestatecategoryidadd").focus();
			} else if($scope.projectfloor == null && $scope.projectfloor == undefined) {				
				$scope.errorFloor = 1;
				$scope.msgFloor = "Please select floor!";
				document.getElementById("projectfloor").focus();
			} else if(document.getElementById("filelayoutadd").value == undefined || document.getElementById("filelayoutadd").value == "") {				
				$scope.errorImage = 1;
				$scope.msgImage = "Please choose image!";
				document.getElementById("filelayoutadd").focus();
				$timeout(function(){
					$scope.errorImage = 0;
				}, 2000);
			} else {
				for (i in $scope.getRealestateName) {
					if ($scope.getRealestateName[i].realestateTypeId == $scope.layoutrealestatecategoryidadd) {
						$scope.realestatecategorytitle = $scope.getRealestateName[i].realestateTypeName;
						break;
					}
				}
				for (i in $scope.unitNameList) {
					if ($scope.unitNameList[i].unitMasterId == $scope.layouttowertitleadd) {
						$scope.unitname = $scope.unitNameList[i].unitName;
						break;
					}
				}
				$scope.projectfloorlayoutlist.push({'categoryTitle' : $scope.realestatecategorytitle, 'categoryId' : $scope.layoutrealestatecategoryidadd, 'unitTitle' : $scope.unitname, 'unitId' : $scope.layouttowertitleadd, 'floorNumber' : $scope.projectfloor, 'totalUnit' : $scope.layoutnumberofunits});
				LayoutImage.append("filelayout",filelayoutadd.files[0]);
				$scope.layoutrealestatecategoryidadd = 0;
				$scope.layouttowertitleadd = 0;
				$scope.projectfloor = 0;
				$scope.layoutnumberofunits = "";
				document.getElementById("filelayoutadd").value = "";
			}
		}
		
		$scope.removeProjectInfoRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectdetaillist.length; i++) {
				if($scope.projectdetaillist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectdetaillist.splice(index, 1);
		};
		
		
		
		
		
		$scope.projectarealist = [];
		$scope.addProjectAreaRow = function() {	
			if(!$scope.unitmasteridadd) {
				$scope.unitmasteridadd = 0;
			}
			
			if(!$scope.propertyareacategoryidadd) {				
				$scope.errorAreaCategoryId = 1;
				$scope.msgAreaCategoryId = "Please select category!";
				document.getElementById("propertyareacategoryidadd").focus();
			} else if(!$scope.propertyareasubcategoryidadd) {				
				$scope.errorAreaSubcategoryId = 1;
				$scope.msgAreaSubcategoryId = "Please select subcategory!";
				document.getElementById("propertyareasubcategoryidadd").focus();
			} else if(!$scope.propertyareatypeidadd) {				
				$scope.errorAreaTypeId = 1;
				$scope.msgAreaTypeId = "Please select realestate type!";
				document.getElementById("propertyareatypeidadd").focus();
			} else if(!$scope.propertyareaidadd) {				
				$scope.errorAreaId = 1;
				$scope.msgAreaId = "Please select area!";
				document.getElementById("propertyareaidadd").focus();
			} else if(!$scope.propertyareaunitidadd) {				
				$scope.errorAreaUnitId = 1;
				$scope.msgAreaUnitId = "Please select unit!";
				document.getElementById("propertyareaunitidadd").focus();
			} else if(!$scope.areavalueadd) {				
				$scope.errorAreaTypeId = 1;
				$scope.msgAreaTypeId = "Please realestate type!";
				document.getElementById("areavalueadd").focus();
			} else {				
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].categoryId == $scope.propertyareacategoryidadd) {
						$scope.propertyareacategorytitle = $scope.projectdetaillist[i].categoryTitle;
						break;
					}
				}
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].subcategoryId == $scope.propertyareasubcategoryidadd) {
						$scope.propertyareasubcategorytitle = $scope.projectdetaillist[i].subcategoryTitle;
						break;
					}
				}
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].typeId == $scope.propertyareatypeidadd) {
						$scope.propertyareatypetitle = $scope.projectdetaillist[i].typeTitle;
						break;
					}
				}
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].unitId == $scope.unitmasteridadd) {
						$scope.unitname = $scope.projectdetaillist[i].unitTitle;
						break;
					}
				}
				for (i in $scope.getAreaName) {
					if ($scope.getAreaName[i].areaTypeId == $scope.propertyareaidadd) {
						$scope.propertyareatitle = $scope.getAreaName[i].areaTypeTitle;
						break;
					}
				}
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.propertyareaunitidadd) {
						$scope.propertyunittitle = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}
				}
				
				$scope.projectarealist.push({'categoryTitle' : $scope.propertyareacategorytitle, 'categoryId' : $scope.propertyareacategoryidadd, 'subcategoryTitle' : $scope.propertyareasubcategorytitle, 'subcategoryId' : $scope.propertyareasubcategoryidadd, 'typeTitle' : $scope.propertyareatypetitle, 'typeId' : $scope.propertyareatypeidadd, 'unitName' : $scope.unitname, 'unitMasterId' : $scope.unitmasteridadd, 'areaTitle' : $scope.propertyareatitle, 'areaId' : $scope.propertyareaidadd, 'unitTitle' : $scope.propertyunittitle, 'unitId' : $scope.propertyareaunitidadd, 'areaValue' : $scope.areavalueadd});
			}
		}
		
		$scope.removeProjectAreaRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectarealist.length; i++) {
				if($scope.projectarealist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectarealist.splice(index, 1);
		};
		
		$scope.projectpricedetaillist = [];
		$scope.addProjectPriceInfoRow = function() {			
			if(!$scope.priceinfounitmasteridadd) {
				$scope.priceinfounitmasteridadd = 0;
			}
			if(!$scope.realeidadd) {				
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryTypes = "Please category type!";
				document.getElementById("realeidadd").focus();
			} else if(!$scope.realsubidadd) {				
				$scope.errorRealestateSubCategoryTitle = 1;
				$scope.msgSubCategoryTypes = "Please subcategory type!";
				document.getElementById("realsubidadd").focus();
			} else if(!$scope.realtypeidadd) {				
				$scope.errorRealestateTitle = 1;
				$scope.msgTypes = "Please realestate type!";
				document.getElementById("realtypeidadd").focus();
			} else if(!$scope.pricelableadd) {				
				$scope.errorPriceLable = 1;
				$scope.msgLable = "Please enter price lable!";
				document.getElementById("pricelableadd").focus();
			} else if(!$scope.pricevalueadd) {				
				$scope.errorPriceValue = 1;
				$scope.msgValue = "Please enter price value !";
				document.getElementById("pricevalueadd").focus();
			} else if(!$scope.areaunittypeadd) {				
				$scope.errorAreaU = 1;
				$scope.msgAreaU = "Please select unit type!";
				document.getElementById("areaunittypeadd").focus();
			} else {
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].categoryId == $scope.realeidadd) {
						$scope.title = $scope.projectdetaillist[i].categoryTitle;
						break;
					}
				}
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].subcategoryId == $scope.realsubidadd) {
						$scope.subcategorytitle = $scope.projectdetaillist[i].subcategoryTitle;
						break;
					}
				}
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].typeId == $scope.realtypeidadd) {
						$scope.realstitle = $scope.projectdetaillist[i].typeTitle;
						break;
					}
				}
				for (i in $scope.projectdetaillist) {
					if ($scope.projectdetaillist[i].unitId == $scope.priceinfounitmasteridadd) {
						$scope.unitname = $scope.projectdetaillist[i].unitTitle;
						break;
					}
				}
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.areaunittypeadd) {
						$scope.unit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}					
				}
				
				$scope.projectpricedetaillist.push({'title' : $scope.title, 'realestateTypeId' : $scope.realeidadd, 'subcategTitle' : $scope.subcategorytitle, 'realestateSubcategoryId' : $scope.realsubidadd, 'realestateId' : $scope.realtypeidadd, 'realstatetitle' : $scope.realstitle, 'unitMasterId' : $scope.priceinfounitmasteridadd, 'unitTitle' : $scope.unitname, 'pricelable' : $scope.pricelableadd, 'pricevalue' : $scope.pricevalueadd, 'measurementUnitId' : $scope.areaunittypeadd, 'unit' : $scope.unit});
			}
		}
				
		$scope.removeProjectPriceInfoRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectpricedetaillist.length; i++) {
				if($scope.projectpricedetaillist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectpricedetaillist.splice(index, 1);
		};
		
		
		$scope.editProjectPaymentSchedule = function(projectid) {			
			
			if(!$scope.sequencetitleedit){
				$scope.sequencetitleedit="";
			}			
			if(!$scope.sequenceedit) {				
				$scope.errorSequence = 1;
				$scope.msgSequence = "Please sequence number!";
				document.getElementById("sequenceedit").focus();
			} else if(!$scope.lableedit) {				
				$scope.errorLable = 1;
				$scope.msgLables = "Please enter lable!";
				document.getElementById("lableedit").focus();
			} else if(!$scope.valueedit) {				
				$scope.errorValue = 1;
				$scope.msgValues = "Please enter value!";
				document.getElementById("valueedit").focus();
			} else if(!$scope.unittypeedit) {				
				$scope.errorUnit = 1;
				$scope.msgUnittype = "Please select unit type!";
				document.getElementById("unittypeedit").focus();
			} else {
				$scope.projectspin = 1;					
				
				var link = baseUrl+'editProjectPaymentSchedule?projectid='+projectid+'&sequence='+$scope.sequenceedit+'&sequencetitle='+$scope.sequencetitleedit+'&lable='+$scope.lableedit+'&value='+$scope.valueedit+'&measurementunitid='+$scope.unittypeedit;				
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editprojectpaymentschedule = data;
					var link = baseUrl+'getProjectPaymentScheduleById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectPaymentSchedule = data;
					}).error(function(data, status, headers, config) {
						$scope.projectPaymentSchedule = "Response Fail";
					});
					$scope.projectspin = 0;										
				}).error(function(data, status, headers, config) {
					$scope.editprojectpaymentschedule = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editprojectpriceinfo;
				});   			
			}
		}		
		
		$scope.removePaymentSchedule = function(projectPaymentScheduleId, projectid) {			
			var link = baseUrl+'deleteProjectPaymentSchedule?id='+projectPaymentScheduleId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletepaymentschedule = data;
				var link = baseUrl+'getProjectPaymentScheduleById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectPaymentSchedule = data;
				}).error(function(data, status, headers, config) {
					$scope.projectPaymentSchedule = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.deletepaymentschedule = "Response Fail";
			});
		}
		
		
		$scope.specificationlist = [{}];
		var SpecFile = new FormData();
		$scope.addSpecificationRow = function() {	
			$scope.specdescription1 = CKEDITOR.instances.specdescriptionadd.getData();
			
			if(!$scope.titleadd) {
				$scope.titleadd = "";
			}
			if(!$scope.subtitleadd) {
				$scope.subtitleadd = "";
			}
			if(!$scope.specdescription1) {
				$scope.specdescription1 = "";
			}
						
			$scope.specdescriptionadd = encodeURIComponent($scope.specdescription1);
			$scope.specificationlist.push({'title' : $scope.titleadd, 'subtitle' : $scope.subtitleadd, 'description' : $scope.specdescriptionadd});
			SpecFile.append("sfileadd",sfileadd.files[0]);
			$scope.titleadd = "";
			$scope.subtitleadd = "";
			document.getElementById("sfileadd").value = "";
			CKEDITOR.instances.specdescriptionadd.setData("");
						
		};

		$scope.removeSpecificationRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.specificationlist.length; i++) {
				if($scope.specificationlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.specificationlist.splice(index, 1);
		};
		
		$scope.amenitylist = [{}];
		var AmenityFile = new FormData();
		$scope.addAmenityRow = function() {
			$scope.amenitydescriptionadd = CKEDITOR.instances.amenitydescriptionadd.getData();
			
			if(!$scope.titleadd) {
				$scope.titleadd = "";
			}
			if(!$scope.subtitleadd) {
				$scope.subtitleadd = "";
			}
			
			if(!$scope.amenitydescriptionadd) {				
				$scope.errorAmenityDescription = 1;
				$scope.msgAmenityDescription = "Please enter description!";
				document.getElementById("amenitydescriptionadd").focus();
			} else {
				$scope.amenitydescriptionadd = encodeURIComponent($scope.amenitydescriptionadd);
				$scope.amenitylist.push({'title' : $scope.titleadd, 'subtitle' : $scope.subtitleadd, 'description' : $scope.amenitydescriptionadd});
				AmenityFile.append("afileadd",afileadd.files[0]);
				$scope.titleadd = "";
				$scope.subtitleadd = "";
				CKEDITOR.instances.amenitydescriptionadd.setData("");
				document.getElementById("afileadd").value = "";
			}				
		};

		$scope.removeAmenityRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.amenitylist.length; i++) {
				if($scope.amenitylist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.amenitylist.splice(index, 1);
		};
		
		
		$scope.editProjectInfo = function(projectid) {			
			if(!$scope.towertitle){
				$scope.towertitle=0;
			}						
			if(!$scope.numberofunit){
				$scope.numberofunit="";
			}
			if(!$scope.categoryid) {				
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryType = "Please category type!";
				document.getElementById("categoryid").focus();
			} else if(!$scope.subcategoryid) {				
				$scope.errorRealestateSubCategoryTitle = 1;
				$scope.msgSubCategoryType = "Please  subcategory type!";
				document.getElementById("subcategoryid").focus();
			} else if(!$scope.realestateid) {				
				$scope.errorRealestateTitle = 1;
				$scope.msgType = "Please realestate type!";
				document.getElementById("realestateid").focus();
			} else {
				$scope.projectinfospin = 1;					
				
				var link = baseUrl+'editProjectDetails?projectid='+projectid+'&categoryid='+$scope.categoryid+'&subcategoryid='+$scope.subcategoryid+'&towertitle='+$scope.towertitle +'&realestateid='+$scope.realestateid+'&numberofunit='+$scope.numberofunit;		
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editprojectinfo = data;
					$scope.projectinfospin = 0;					
					if($scope.editprojectinfo == 'Success'){
						var link = baseUrl+'getProjectDetailsById?projectid='+projectid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.projectDetails = data;
						}).error(function(data, status, headers, config) {
							$scope.projectDetails = "Response Fail";
						});					
					} else {
						$scope.errorMsg = $scope.editarea;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editprojectinfo = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editarea;
				});   			
			}
		}
		
		
		$scope.removeProjectDetails = function(projectDetailId, projectid) {
			
			var link = baseUrl+'deleteProjectDetail?projectdetailid='+projectDetailId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteproject = data;
				var link = baseUrl+'getProjectDetailsById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectDetails = data;
				}).error(function(data, status, headers, config) {
					$scope.projectDetails = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.deleteproject = "Response Fail";
			});
		}
		
		$scope.editProjectAreaRow = function(projectid) {			
			if(!$scope.unitmasteridadd) {
				$scope.unitmasteridadd = 0;
			}
			if(!$scope.propertyareacategoryidadd) {				
				$scope.errorAreaCategoryId = 1;
				$scope.msgAreaCategoryId = "Please select category!";
				document.getElementById("propertyareacategoryidadd").focus();
			} else if(!$scope.propertyareasubcategoryidadd) {				
				$scope.errorAreaSubcategoryId = 1;
				$scope.msgAreaSubcategoryId = "Please select subcategory!";
				document.getElementById("propertyareasubcategoryidadd").focus();
			} else if(!$scope.propertyareatypeidadd) {				
				$scope.errorAreaTypeId = 1;
				$scope.msgAreaTypeId = "Please select realestate type!";
				document.getElementById("propertyareatypeidadd").focus();
			} else if(!$scope.propertyareaidadd) {				
				$scope.errorAreaId = 1;
				$scope.msgAreaId = "Please select area!";
				document.getElementById("propertyareaidadd").focus();
			} else if(!$scope.propertyareaunitidadd) {				
				$scope.errorAreaUnitId = 1;
				$scope.msgAreaUnitId = "Please select unit!";
				document.getElementById("propertyareaunitidadd").focus();
			} else if(!$scope.areavalueadd) {				
				$scope.errorAreaTypeId = 1;
				$scope.msgAreaTypeId = "Please realestate type!";
				document.getElementById("areavalueadd").focus();
			} else {
				$scope.areaSpin = 1;				
				var link = baseUrl+'editProjectArea?projectid='+projectid+'&categoryid='+$scope.propertyareacategoryidadd+'&subcategoryid='+$scope.propertyareasubcategoryidadd+'&typeid='+$scope.propertyareatypeidadd+'&unitmasterid='+$scope.unitmasteridadd+'&areaid='+$scope.propertyareaidadd+'&unitid='+$scope.propertyareaunitidadd+'&areavalue='+$scope.areavalueadd;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editprojectarea = data;
					$scope.areaSpin = 0;					
					if($scope.editprojectarea == 'Success'){
						var link = baseUrl+'getProjectAreaById?projectid='+projectid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.projectArea = data;
						}).error(function(data, status, headers, config) {
							$scope.projectArea = "Response Fail";
						});					
					} else {
						$scope.errorMsg = $scope.editprojectarea;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editprojectarea = data;
					$scope.areaSpin = 0;
				});
			}
		}
		
		$scope.deleteProjectAreaRow = function(projectareaid, projectid) {			
			var link = baseUrl+'deleteProjectAreaRow?projectareaid='+projectareaid;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteprojectarea = data;
				var link = baseUrl+'getProjectAreaById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectArea = data;
				}).error(function(data, status, headers, config) {
					$scope.projectArea = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.deleteprojectarea = "Response Fail";
			});
		}
		
		$scope.removeProjectPriceDetails = function(projectPriceInfoId, projectid) {
			
			var link = baseUrl+'deleteProjectPriceDetail?projectpriceinfoid='+projectPriceInfoId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteproject = data;
				var link = baseUrl+'getProjectPriceDetailsById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectPriceDetails = data;
				}).error(function(data, status, headers, config) {
					$scope.projectPriceDetails = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.deleteproject = "Response Fail";
			});
		}
		
		$scope.addProject = function() {
			$scope.locationidadd = document.getElementById("locationidadd").value;
			$scope.architectidadd = document.getElementById("architectidadd").value;
			$scope.structuraldesigneridadd = document.getElementById("structuraldesigneridadd").value;
			$scope.legaladvisoridadd = document.getElementById("legaladvisoridadd").value;
			$scope.developercompanyidadd = document.getElementById("developercompanyidadd").value;
			$scope.descriptionadd = CKEDITOR.instances.descriptionadd.getData();
			
			if(!$scope.projectsubtitleadd) {
				$scope.projectsubtitleadd = "";
			}			
			if(!$scope.architectidadd) {
				$scope.architectidadd = 0;
			}
			if(!$scope.structuraldesigneridadd) {
				$scope.structuraldesigneridadd = 0;
			}
			if(!$scope.legaladvisoridadd) {
				$scope.legaladvisoridadd = 0;
			}
			if(!$scope.developercompanyidadd) {
				$scope.developercompanyidadd = 0;
			}
			if(!$scope.propertytypeidadd) {
				$scope.propertytypeidadd = 0;
			}
			if(!$scope.rereapprovedadd) {
				$scope.rereapprovedadd = "";
			}
			if(!$scope.reranoadd) {
				$scope.reranoadd = "";
			}
			if(!$scope.watersourceadd) {
				$scope.watersourceadd = "";
			}
			if(!$scope.drainageadd) {
				$scope.drainageadd = "";
			}
			if(!$scope.workstatusadd) {
				$scope.workstatusadd = "";
			}
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.projectsitelinkadd) {
				$scope.projectsitelinkadd = "";
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
			
			if(!$scope.projecttitleadd) {				
				$scope.errorProjectTitle = 1;
				$scope.msgProjectTitle = "Please enter project title";
				document.getElementById("projecttitleadd").focus();
			} else if(!$scope.projectcodeadd) {				
				$scope.errorProjectCode = 1;
				$scope.msgProjectCode = "Please enter unique project code";
				document.getElementById("projectcodeadd").focus();
			} else if(!$scope.locationidadd) {				
				$scope.errorLocation = 1;
				$scope.msgLocation = "Please select location";
				document.getElementById("locationidadd").focus();
			} else if(!$scope.sequenceadd) {				
				$scope.errorSeq = 1;
				$scope.msgSeq = "Please enter sequence";
				document.getElementById("sequenceadd").focus();
			} else {
				
				$scope.spin = 1;
				var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );
				
				$scope.spectitlelist = [];
				$scope.specsubtitlelist = [];
				$scope.specdescriptionlist = [];
				angular.forEach($scope.specificationlist,function(item) {
					$scope.spectitlelist.push(item.title);
					$scope.specsubtitlelist.push(item.subtitle);
					$scope.specdescriptionlist.push(item.description);
					a = a + 1;
				});
												
				$scope.amenitytitlelist = [];
				$scope.amenitysubtitlelist = [];
				$scope.amenitydescriptionlist = [];
				angular.forEach($scope.amenitylist,function(item) {
					$scope.amenitytitlelist.push(item.title);
					$scope.amenitysubtitlelist.push(item.subtitle);
					$scope.amenitydescriptionlist.push(item.description);
					b = b + 1;
				});
				
				$scope.layoutcategoryidlist = [];
				$scope.layoutunitidlist = [];
				$scope.layoutfloorlist = [];
				$scope.totallayoutunitlist = [];
				angular.forEach($scope.projectfloorlayoutlist,function(item) {
					$scope.layoutcategoryidlist.push(item.categoryId);
					$scope.layoutunitidlist.push(item.unitId);
					$scope.layoutfloorlist.push(item.floorNumber);
					$scope.totallayoutunitlist.push(item.totalUnit);
					c = c + 1;
				});
				
				$scope.descriptionadd = encodeURIComponent($scope.descriptionadd);
				
				var link = baseUrl+'addProject?projecttitle='+$scope.projecttitleadd+'&projectsubtitle='+$scope.projectsubtitleadd+'&projectcode='+$scope.projectcodeadd+'&locationid='+$scope.locationidadd+'&architectid='+$scope.architectidadd+'&structuraldesignerid='+$scope.structuraldesigneridadd+'&legaladvisorid='+$scope.legaladvisoridadd+'&developercompanyid='+$scope.developercompanyidadd+'&propertytypeid='+$scope.propertytypeidadd+'&rereapproved='+$scope.rereapprovedadd+'&rerano='+$scope.reranoadd+'&drainage='+$scope.drainageadd+'&watersource='+$scope.watersourceadd+'&workstatus='+$scope.workstatusadd+'&description='+$scope.descriptionadd+'&valuex='+valuex1+'&valuey='+valuey1+'&valuew='+valuew1+'&valueh='+valueh1+'&projectsitelink='+$scope.projectsitelinkadd+'&sequence='+$scope.sequenceadd;
				var formData = new FormData();
				formData.append("projectlogo",projectlogo.files[0]);
				formData.append("projectlayout",projectlayout.files[0]);
				formData.append("projectmainimage",projectimageadd.files[0]);
				formData.append("projectpdf",projectpdfadd.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addproject = data;
					
					if($scope.addproject == 'Success') {
						
						angular.forEach($scope.projectdetaillist,function(item) {
							var link = baseUrl+'addProjectDetails?realestatetypeid='+item.categoryId+'&realestatesubcategoryid='+item.subcategoryId+'&unitmasterid='+item.unitId+'&realestateid='+item.typeId+'&numberofunit='+item.totalUnit;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addprojectdetails = data;
								c = c + 1;
								if($scope.specificationlist.length == a && $scope.amenitylist.length == b && $scope.projectdetaillist.length != c && $scope.projectpricedetaillist.length == d && $scope.paymentschedulelist.length == e && $scope.projectarealist.length == f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addprojectdetails = "Response Fail";
							});
						});
						angular.forEach($scope.projectarealist,function(item) {												
							var link = baseUrl+'addProjectArea?categoryid='+item.categoryId+'&subcategoryid='+item.subcategoryId+'&typeid='+item.typeId+'&unitmasterid='+item.unitMasterId+'&areaid='+item.areaId+'&unitid='+item.unitId+'&areavalue='+item.areaValue;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addpropertyarea = data;
								f = f + 1;
								if($scope.projectfloorlayoutlist == g && $scope.specificationlist.length == a && $scope.amenitylist.length == b && $scope.projectdetaillist.length == c && $scope.projectpricedetaillist.length == d && $scope.paymentschedulelist.length == e && $scope.projectarealist.length != f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addpropertyarea = "Response Fail";
							});
						});
						
						if ($scope.projectfloorlayoutlist.length > 0) {
							var link = baseUrl+ 'addProjectFloorLayout?categoryid='+$scope.layoutcategoryidlist+'&unitid='+$scope.layoutunitidlist+'&floorNumber='+$scope.layoutfloorlist+'&totalUnit='+$scope.totallayoutunitlist;
							$http({method : 'POST', url : link, headers : {'Content-Type' : undefined}, data : LayoutImage, transformRequest : function(data,headersGetterFunction) { return data; }}).success(function(data,status) {
								$scope.addprojectfloorlayout = data;																		
								if($scope.projectfloorlayoutlist != g && $scope.specificationlist.length == a && $scope.amenitylist.length == b && $scope.projectdetaillist.length == c && $scope.projectpricedetaillist.length == d && $scope.paymentschedulelist.length == e && $scope.projectarealist.length == f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data,status,headers,config) {
								$scope.addprojectfloorlayout = "Response Fail";
							});
						}
							
						angular.forEach($scope.projectpricedetaillist,function(item) {												
							var link = baseUrl+'addProjectPriceDetails?realestatetypeid='+item.realestateTypeId+'&realestatesubcategoryid='+item.realestateSubcategoryId+'&realestateid='+item.realestateId+'&unitmasterid='+item.unitMasterId+'&pricelable='+item.pricelable+'&pricevalue='+item.pricevalue+'&measurementunitid='+item.measurementUnitId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addprojectpricedetails = data;
								d = d + 1;
								if($scope.projectfloorlayoutlist == g && $scope.specificationlist.length == a && $scope.amenitylist.length == b && $scope.projectdetaillist.length == c && $scope.projectpricedetaillist.length != d && $scope.paymentschedulelist.length == e && $scope.projectarealist.length == f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addprojectpricedetails = "Response Fail";
							});
						});
						
						angular.forEach($scope.paymentschedulelist,function(item) {												
							var link = baseUrl+'addProjectPaymentSchedule?sequence='+item.sequence+'&sequencetitle='+item.sequencetitle+'&lable='+item.lable+'&value='+item.value+'&measurementunitid='+item.measurementUnitId;						
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addprojectpaymentschedule = data;
								e = e + 1;
								if($scope.projectfloorlayoutlist == g && $scope.specificationlist.length == a && $scope.amenitylist.length == b && $scope.projectdetaillist.length == c && $scope.projectpricedetaillist.length == d && $scope.paymentschedulelist.length != e && $scope.projectarealist.length == f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addprojectpaymentschedule = "Response Fail";
							});
						});
						
						
						if ($scope.specificationlist.length > 0) {
							var link = baseUrl+ 'addProjectSpecification?title='+ $scope.spectitlelist+'&subtitle='+$scope.specsubtitlelist+'&description='+$scope.specdescriptionlist;							
							$http({method : 'POST', url : link, headers : {'Content-Type' : undefined}, data : SpecFile, transformRequest : function(data,headersGetterFunction) { return data; }}).success(function(data,status) {
								$scope.addprojectspeciafication = data;																		
								if($scope.projectfloorlayoutlist == g && $scope.specificationlist.length != a && $scope.amenitylist.length == b && $scope.projectdetaillist.length == c && $scope.projectpricedetaillist.length == d && $scope.paymentschedulelist.length == e && $scope.projectarealist.length == f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data,status,headers,config) {
								$scope.addprojectspeciafication = "Response Fail";
							});
						}
						
						if ($scope.amenitylist.length > 0) {
							var link = baseUrl+ 'addProjectAmenity?title='+ $scope.amenitytitlelist+'&subtitle='+$scope.amenitysubtitlelist+'&description='+$scope.amenitydescriptionlist;				
							$http({method : 'POST', url : link, headers : {'Content-Type' : undefined}, data : AmenityFile, transformRequest : function(data,headersGetterFunction) { return data; }}).success(function(data,status) {
								$scope.addprojectamenity = data;																		
								if($scope.projectfloorlayoutlist == g && $scope.specificationlist.length == a && $scope.amenitylist.length != b && $scope.projectdetaillist.length == c && $scope.projectpricedetaillist.length == d && $scope.paymentschedulelist.length == e && $scope.projectarealist.length == f) {
									$scope.spin = 0;
									$scope.projectSubmitError = 0;
									$scope.projectSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.projectSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_project';
									}, 2000);
								}
							}).error(function(data,status,headers,config) {
								$scope.addprojectamenity = "Response Fail";
							});
						}
						
						/*if($scope.projectfloorlayoutlist != g && $scope.specificationlist.length != a && $scope.amenitylist.length != b && $scope.projectdetaillist.length != c && $scope.projectpricedetaillist.length != d && $scope.paymentschedulelist.length != e && $scope.projectarealist.length != f) {*/
							$scope.spin = 0;
							$scope.projectSubmitError = 0;
							$scope.projectSubmitSuccess = 1;
							$scope.successMsg = "Data added";
							$timeout(function(){
								$scope.projectSubmitSuccess = 0;
								$window.location.href = adminurl+'manage_project';
							}, 2000);
						/*}*/
						
					} else {
						$scope.projectSubmitSuccess = 0;
						$scope.projectSubmitError = 1;
						$scope.errorMsg = $scope.addproject;						
					}								
										
				}).error(function(data, status, headers, config) {
					$scope.addproject = data;
					$scope.spin = 0;
					$scope.projectSubmitSuccess = 0;
					$scope.projectSubmitError = 1;
					$scope.errorMsg = $scope.addproject;
				});
			}
		}
			
		$scope.getProject = function(projectid) {
			var link = baseUrl+'getProjectDetailById?projectid='+projectid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectDetail = data;
				
				$scope.projectid = $scope.projectDetail.projectId;
				$scope.projecttitle = $scope.projectDetail.projectTitle;
				$scope.projectsubtitle = $scope.projectDetail.projectSubtitle;
				$scope.projectcode = $scope.projectDetail.projectCode;
				$scope.projectlogo = $scope.projectDetail.projectLogo;
				$scope.locationid = $scope.projectDetail.locationId;
				$scope.architectid = $scope.projectDetail.architectId;
				$scope.structuraldesignerid = $scope.projectDetail.structuralDesignerId;
				$scope.legaladvisorid = $scope.projectDetail.legalAdvisorId;
				$scope.developercompanyid = $scope.projectDetail.developerCompanyId;
				$scope.propertytypeid = $scope.projectDetail.propertyTypeId;				
				$scope.rereapproved = $scope.projectDetail.reraApprove;
				$scope.rerano = $scope.projectDetail.reraNo;
				$scope.projectlayout = $scope.projectDetail.layoutMap;
				$scope.watersourceedit = $scope.projectDetail.waterSource;
				$scope.drainageedit = $scope.projectDetail.drainage;
				$scope.workstatus = $scope.projectDetail.workStatus;
				var desc = $scope.projectDetail.description;
				CKEDITOR.instances.description.setData(desc);
				
				$scope.projmainimg = $scope.projectDetail.projmainimg;
				$scope.projectPDF = $scope.projectDetail.projectPDF;
				$scope.sitelinkedit = $scope.projectDetail.siteLink;
				$scope.sequence = $scope.projectDetail.sequence;
				
				var link = baseUrl+'getProjectSpecificationById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectSpecification = data;
				}).error(function(data, status, headers, config) {
					$scope.projectSpecification = "Response Fail";
				});
				
				var link = baseUrl+'getProjectAmenityById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectAmenity = data;
				}).error(function(data, status, headers, config) {
					$scope.projectAmenity = "Response Fail";
				});
				
				var link = baseUrl+'getProjectAreaById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectArea = data;
				}).error(function(data, status, headers, config) {
					$scope.projectArea = "Response Fail";
				});
				
				var link = baseUrl+'getProjectDetailsById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectDetails = data;
				}).error(function(data, status, headers, config) {
					$scope.projectDetails = "Response Fail";
				});
				
				var link = baseUrl+'getProjectPriceDetailsById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectPriceDetails = data;
				}).error(function(data, status, headers, config) {
					$scope.projectPriceDetails = "Response Fail";
				});
								
				var link = baseUrl+'getProjectPaymentScheduleById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectPaymentSchedule = data;
				}).error(function(data, status, headers, config) {
					$scope.projectPaymentSchedule = "Response Fail";
				});
				
				var link = baseUrl+'getProjectFloorLayoutById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectFloorLayout = data;
				}).error(function(data, status, headers, config) {
					$scope.projectFloorLayout = "Response Fail";
				});
				
				var link = baseUrl+'getPaymentDetailById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.paymentById = data;				 	
				}).error(function(data, status, headers, config) {
					$scope.paymentById = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.projectDetail = "Response Fail";
			});
		}
		
		$scope.editProject = function(projectid) {
			
			$scope.descriptionedit = CKEDITOR.instances.description.getData();
			
			if(!$scope.projectsubtitle) {
				$scope.projectsubtitle = "";
			}
			if(!$scope.projectcode) {
				$scope.projectcode = "";
			}
			if(!$scope.architectid) {
				$scope.architectid = 0;
			}
			if(!$scope.structuraldesignerid) {
				$scope.structuraldesignerid = 0;
			}
			if(!$scope.legaladvisorid) {
				$scope.legaladvisorid = 0;
			}
			if(!$scope.developercompanyid) {
				$scope.developercompanyid = 0;
			}
			if(!$scope.propertytypeid) {
				$scope.propertytypeid = 0;
			}
			if(!$scope.rereapproved) {
				$scope.rereapproved = "";
			}
			if(!$scope.rerano) {
				$scope.rerano = "";
			}
			if(!$scope.watersourceedit) {
				$scope.watersourceedit = "";
			}
			if(!$scope.drainageedit) {
				$scope.drainageedit = "";
			}
			if(!$scope.workstatus) {
				$scope.workstatus = "";
			}
			if(!$scope.description) {
				$scope.description = "";
			}
			if(!$scope.sitelinkedit) {
				$scope.sitelinkedit = "";
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
			if(!$scope.projecttitle) {				
				$scope.errorProjectTitle = 1;
				$scope.msgProjectTitle = "Please enter project title";
				document.getElementById("projecttitle").focus();
			} else if(!$scope.locationid) {				
				$scope.errorLocation = 1;
				$scope.msgLocation = "Please select location";
				document.getElementById("locationid").focus();
			} else if(!$scope.sequence) {				
				$scope.errorSeq = 1;
				$scope.msgSeq = "Please enter sequence";
				document.getElementById("sequence").focus();
			} else {
				$scope.spin = 1;
				$scope.descriptionedit = encodeURIComponent($scope.descriptionedit);
				var link = baseUrl+'editProject?projectid='+projectid+'&projecttitle='+$scope.projecttitle+'&projectsubtitle='+$scope.projectsubtitle+'&projectcode='+$scope.projectcode+'&locationid='+$scope.locationid+'&architectid='+$scope.architectid+'&structuraldesignerid='+$scope.structuraldesignerid+'&legaladvisorid='+$scope.legaladvisorid+'&developercompanyid='+$scope.developercompanyid+'&propertytypeid='+$scope.propertytypeid+'&rereapproved='+$scope.rereapproved+'&rerano='+$scope.rerano+'&watersource='+$scope.watersourceedit+'&drainage='+$scope.drainageedit+'&workstatus='+$scope.workstatus+'&description='+$scope.descriptionedit+'&projectlogo='+$scope.projectlogo+'&projectlayout='+$scope.projectlayout+'&projmainimg='+$scope.projmainimg+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&sitelink='+$scope.sitelinkedit+'&pdfpath='+$scope.projectPDF+'&sequence='+$scope.sequence;
				//var link = baseUrl+'editMainSlider?sliderid='+sliderid+'&slidertitle='+tit2+'&active='+active1+'&sliderimage='+$scope.sliderimage+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&sldsequence='+sldsequence+'&sldsubtitle='+sldsubtitle+'&slddescription='+slddescription;//+'&sldlink='+sllink;;
				var formData=new FormData();
				
				formData.append("projectlogoedit",projectlogoedit.files[0]);
				formData.append("projectlayoutedit",projectlayoutedit.files[0]);
				formData.append("projectmainimageedit",imageedit.files[0]);
				formData.append("projectpdf",pdfedit.files[0]);
				
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editproject = data;
					$scope.spin = 0;
				
					if($scope.editproject == 'Success'){
						$scope.projectSubmitError = 0;
						$scope.projectSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.projectSubmitSuccess = 0;
							$('#editModal').modal('hide');
						}, 2000);
					} else {
						$scope.projectSubmitSuccess = 0;
						$scope.projectSubmitError = 1;
						$scope.errorMsg = $scope.editproject;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.editproject = data;
					$scope.spin = 0;
					$scope.projectSubmitSuccess = 0;
					$scope.projectSubmitError = 1;
					$scope.errorMsg = $scope.editproject;
				});
			}
		}	
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getProjects, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteProject = function() {
			angular.forEach($scope.getProjects, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteProject?projectid='+item.projectId;					
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteproject = data;
					}).error(function(data, status, headers, config) {
						$scope.deleteproject = "Response Fail";
					});
				}
				var link = baseUrl+'getProjectsByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProjects = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getProjects = "Response Fail";
				});
				
				var link = baseUrl+'getAllCounts';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.allcounts = data;
				}).error(function(data, status, headers, config) {
					$scope.allcounts = "Response Fail";
				});
			});			
		}
		
		$scope.deleteSpecificationRow = function(projectid, projectspecificationid) {			
			var link = baseUrl+'deleteSpecification?projectspecificationid='+projectspecificationid;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletespecification = data;
				
				var link = baseUrl+'getProjectSpecificationById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectSpecification = data;					
				}).error(function(data, status, headers, config) {
					$scope.projectSpecification = "Response Fail";
				});	
				
			}).error(function(data, status, headers, config) {				
				$scope.errorSpecDelete = 1;
				$scope.msgSpecDelete = "Something wrong!";
				$timeout(function() {
					$scope.errorSpecDelete = 0;
				}, 2000);
			});
		}
		
		$scope.deleteAmenityRow = function(projectid, projectamenityid) {			
			var link = baseUrl+'deleteAmenity?projectamenityid='+projectamenityid;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteamenity = data;
				
				var link = baseUrl+'getProjectAmenityById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectAmenity = data;					
				}).error(function(data, status, headers, config) {
					$scope.projectAmenity = "Response Fail";
				});	
				
			}).error(function(data, status, headers, config) {				
				$scope.errorAmenityDelete = 1;
				$scope.msgAmenityDelete = "Something wrong!";
				$timeout(function() {
					$scope.errorAmenityDelete = 0;
				}, 2000);
			});
		}
		
		$scope.editSpecificationRow = function(projectid) {
			$scope.specdescriptionedit = CKEDITOR.instances.specdescriptionedit.getData();
			if(!$scope.titleadd) {
				$scope.titleadd = "";
			}
			if(!$scope.subtitleadd) {
				$scope.subtitleadd = "";
			}
			if(!$scope.specdescriptionedit) {
				$scope.specdescriptionedit = "";
			}
			
			/*if(!$scope.specdescriptionadd) {				
				$scope.errorSpecDescription = 1;
				$scope.msgSpecDescription = "Please enter description!";
				document.getElementById("specdescriptionadd").focus();
			} else {*/
				$scope.spin = 1;
				$scope.specdescriptionedit = encodeURIComponent($scope.specdescriptionedit);
				var link = baseUrl+'editSpecificationRow?projectid='+projectid+'&title='+$scope.titleadd+'&subtitle='+$scope.subtitleadd+'&description='+$scope.specdescriptionedit;				
				var formData = new FormData();
				formData.append("sfileedit",sfileedit.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editspecification = data;
					$scope.titleadd = 0;
					$scope.subtitleadd = 0;
					CKEDITOR.instances.specdescriptionedit.setData("");
					var link = baseUrl+'getProjectSpecificationById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectSpecification = data;						
						$scope.spin = 0;						
					}).error(function(data, status, headers, config) {
						$scope.projectSpecification = "Response Fail";
					});
					
				}).error(function(data, status, headers, config) {
					$scope.editspecification = data;
					$scope.spin = 0;
					$scope.specificationSubmitSuccess = 0;
					$scope.specificationSubmitError = 1;
					$scope.errorMsg = "Something went wrong!";
				});
			/*}*/				
		};
				
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
					var link = baseUrl+'getLocationName';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getLocationName = data;
					}).error(function(data, status, headers, config) {
						$scope.getLocationName = "Response Fail";
					});
					$scope.spin = 0;					
					if($scope.addlocation == 'Success'){
						$scope.locationSubmitError = 0;
						$scope.locationSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.locationSubmitSuccess = 0;
							/*$window.location.href = adminurl+'manage_location';*/
							$('#locationModal').modal('hide');
							
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
		
		
		$scope.addUser = function() {
			$scope.dateofbirth = "";
			$scope.aadharnumber = "";
			
			if(!$scope.companynameadd)	{
				$scope.companynameadd = "";
			}
			if(!$scope.middlenameadd)	{
				$scope.middlenameadd = "";
			}
			if(!$scope.lastnameadd)	{
				$scope.lastnameadd = "";
			}
			if(!$scope.genderadd)	{
				$scope.genderadd = "";
			}			
			if(!$scope.dateofbirth || $scope.dateofbirth=="day/month/year")	{
				$scope.dateofbirth = "";
			}
			if(!$scope.aadharnumber) {
				$scope.aadharnumber = "";
			}
			if(!$scope.passportnumberadd){
				$scope.passportnumberadd = "";
			}
			if(!$scope.pannumberadd) {
				$scope.pannumberadd = "";
			}
			if(!$scope.address1add) {
				$scope.address1add = "";
			}
			if(!$scope.address2add) {
				$scope.address2add = "";
			}
			if(!$scope.address3add) {
				$scope.address3add = "";
			}
			if(!$scope.countrynameadd) {
				$scope.countrynameadd = 0;
			}
			if(!$scope.statenameadd) {
				$scope.statenameadd = 0;
			}
			if(!$scope.citynameadd) {
				$scope.citynameadd = "";
			}
			if(!$scope.pincodeadd) {
				$scope.pincodeadd = "";
			}
			if(!$scope.mobilenumberadd) {
				$scope.mobilenumberadd = "";
			}
			if(!$scope.landlinenumberadd) {
				$scope.landlinenumberadd = "";
			}
			if(!$scope.emailadd) {
				$scope.emailadd = "";
			}
			if(!$scope.passwordadd) {
				$scope.passwordadd = "";
			}
			if(!$scope.gstnoadd){
				$scope.gstnoadd="";
			}
			
			if(!$scope.firstnameadd) {				
				$scope.errorFirstName = 1;
				$scope.msgFirstName = "Please enter first name";
				document.getElementById("firstnameadd").focus();
			} else if(!$scope.usertypenameadd) {				
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please select user type";
				document.getElementById("usertypenameadd").focus();
			} else {
				$scope.spin = 1;		
				var link = baseUrl+'addUser?companyname='+$scope.companynameadd+'&firstname='+$scope.firstnameadd+'&middlename='+$scope.middlenameadd+'&lastname='+$scope.lastnameadd+'&usertypename='+$scope.usertypenameadd+'&gender='+$scope.genderadd+'&dateofbirth='+$scope.dateofbirth+'&aadharnumber='+$scope.aadharnumber+'&passportnumber='+$scope.passportnumberadd+'&pannumber='+$scope.pannumberadd+'&address1='+$scope.address1add+'&address2='+$scope.address2add+'&address3='+$scope.address3add+'&countryname='+$scope.countrynameadd+'&statename='+$scope.statenameadd+'&cityname='+$scope.citynameadd+'&pincode='+$scope.pincodeadd+'&mobilenumber='+$scope.mobilenumberadd+'&landlinenumber='+$scope.landlinenumberadd+'&email='+$scope.emailadd+'&password='+$scope.passwordadd+'&gstno='+$scope.gstnoadd;				
				var formData=new FormData();
				formData.append("profilepicture",profilepictureadd.files[0]);					
				$http({method: 'POST',
					url: link,				
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction) {
			        	return data;
			        }
				}).success(function(data, status) {
					$scope.adduser = data;
					
					
					$scope.spin = 0;
					if($scope.adduser == 'Success'){
						$scope.userSubmitError = 0;
						$scope.userSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.userSubmitSuccess = 0;
							$('#userModal').modal('hide');
							var link = baseUrl+'getUserNameByUserType?usertypeid='+"6";
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getArchitectName = data;
							}).error(function(data, status, headers, config) {
								$scope.getArchitectName = "Response Fail";
							});
							var link = baseUrl+'getUserNameByUserType?usertypeid='+"27";
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getDesignerName = data;
							}).error(function(data, status, headers, config) {
								$scope.getDesignerName = "Response Fail";
							});
							
							var link = baseUrl+'getUserNameByUserType?usertypeid='+"28";
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getAdvisorName = data;
							}).error(function(data, status, headers, config) {
								$scope.getAdvisorName = "Response Fail";
							});
							var link = baseUrl+'getUserName';
							$http.get(link).success(function(data, status, headers, config) {
								$scope.getUserName = data;
							}).error(function(data, status, headers, config) {
								$scope.getUserName = "Response Fail";
							});
							jQuery("#usermodule input[type='text'], input[type='checkbox'], input[type='file']").each(function() {
						        this.value = '';
						    });
						}, 2000);
					} else {
						$scope.userSubmitSuccess = 0;
						$scope.userSubmitError = 1;
						$scope.errorMsg = $scope.adduser;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.adduser = data;
					$scope.spin = 0;
					$scope.userSubmitSuccess = 0;
					$scope.userSubmitError = 1;
					$scope.errorMsg = "Something wrong! Please try again later!";
				});			
			}
		}
		
		
		$scope.editAmenityRow = function(projectid) {
			$scope.amenitydescriptionedit = CKEDITOR.instances.amenitydescriptionedit.getData();
			
			if(!$scope.title) {
				$scope.title = "";
			}
			if(!$scope.subtitle) {
				$scope.subtitle = "";
			}
			
			if(!$scope.amenitydescriptionedit) {				
				$scope.errorAmenityDescription = 1;
				$scope.msgAmenityDescription = "Please enter description!";
				document.getElementById("amenitydescriptionedit").focus();
			} else {
				$scope.spin = 1;
				$scope.amenitydescriptionedit = encodeURIComponent($scope.amenitydescriptionedit);
				
				var link = baseUrl+'editAmenityRow?projectid='+projectid+'&title='+$scope.title+'&subtitle='+$scope.subtitle+'&description='+$scope.amenitydescriptionedit;				
				var formData = new FormData();
				formData.append("afileedit",afileedit.files[0]);				
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editamenity = data;
					$scope.title="";
					$scope.subtitle="";
					CKEDITOR.instances.amenitydescriptionedit.setData("");
					var link = baseUrl+'getProjectAmenityById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectAmenity = data;						
						$scope.spin = 0;						
					}).error(function(data, status, headers, config) {
						$scope.projectAmenity = "Response Fail";
					});
					
				}).error(function(data, status, headers, config) {
					$scope.editamenity = data;
					$scope.spin = 0;
					$scope.amenitySubmitSuccess = 0;
					$scope.amenitySubmitError = 1;
					$scope.errorMsg = "Something went wrong!";
				});
			}				
		};
		
		
		$scope.addType = function() {			
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}		
			if(!$scope.realeidadd) {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please select realestate category!";
				document.getElementById("realeidadd").focus();
			} else if(!$scope.realsubidadd) {			
				$scope.errorRealSubTitle = 1;
				$scope.msgSubcategoryTitle = "Please select realestate type!";
				document.getElementById("realsubidadd").focus();
			} else if(!$scope.realestatetitleadd) {			
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
					var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.realsubidadd;				
					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getRealestateTitles = data;
					}).error(function(data, status, headers, config) {
						$scope.getRealestateTitles = "Response Fail";
					});	
	    			if($scope.addrealestate == "Success"){
	    				$scope.spin = 0;
	    				$scope.submitSuccess = 1;
	    				$scope.msgSuccess = "Data added successfully";
	    				$timeout(function(){
	    					$('#realestateTypeModal').modal('hide');
	    					$scope.submitSuccess = 0;	    					
	    				}, 2000);
	    				
	    			} else {
	    				$scope.spin = 0;    				
	    				$scope.submitError = 1;
	    				$scope.msgError = "Data not inserted! Duplicate entry for type code!";
	    				$timeout(function(){
	    					$scope.submitError = 0;				
	    				}, 3000);
	    			}
	    			
				}).error(function(data, status, headers, config) {
					$scope.addrealestate = data;				
					$scope.spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addcategory;
					$timeout(function(){
						$('#realestateTypeModal').modal('hide');						
					}, 5000);
					
				});    			
			}
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
				$scope.spin = 1;	
				
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
					
					var link = baseUrl+'getRealestateName';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getRealestateName = data;
					}).error(function(data, status, headers, config) {
						$scope.getRealestateName = "Response Fail";
					});
					
					if($scope.addrealestatetype == "Success"){
						$scope.spin = 0;
						$scope.submitSuccess = 1;
						$scope.msgSuccess = "Data added successfully";
						$timeout(function(){
							$('#realestateModal').modal('hide');
							$scope.submitSuccess = 0;
							document.getElementById("realestatecategory").reset();
						}, 3000);
						
					} else {
						$scope.spin = 0;    				
						$scope.submitError = 1;
						$scope.msgError = "Data not inserted! Duplicate entry for category code!";
						$timeout(function(){
							$scope.submitError = 0;				
						}, 3000);
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addrealestatetype = data;				
					$scope.spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addcategory;
					$timeout(function(){
						$('#realestateModal').modal('hide');						
						document.getElementById("realestatecategory").reset();
					}, 3000);
					
				});    			
			}
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
					var link = baseUrl+'getAllRealestateSubcategoryTitleByRealestateId?realestateid='+$scope.realeidadd;				
					
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getSubcategoryTitles = data;
					}).error(function(data, status, headers, config) {
						$scope.getSubcategoryTitles = "Response Fail";
					});
					if($scope.addrealestatesubcategory == "Success"){
						$scope.spin = 0;
						$scope.submitSuccess = 1;
						$scope.msgSuccess = "Data added successfully";
						$timeout(function(){
							$('#realestateSubModal').modal('hide');
							$scope.submitSuccess = 0;
							document.getElementById("realestateSubcategory").reset();
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
					$scope.msgError = $scope.addcategory;
					$timeout(function(){
						$('#realestateSubModal').modal('hide');
						document.getElementById("realestateSubcategory").reset();
					}, 5000);
					
				});    			
			}
		}
		
		$scope.addPropertyType = function() {
			
			if($scope.propertycodeadd==undefined) {
				$scope.propertycodeadd = "";
			}
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}		
			
			if($scope.propertytitleadd==undefined || $scope.propertytitleadd=="") {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please enter property type title";
				document.getElementById("propertytitleadd").focus();
			} else {
				$scope.spin = 1;				
				
				$scope.propertytitleadd = tools_replaceAll($scope.propertytitleadd, "&" , "$" );
				$scope.propertytitleadd = tools_replaceAll($scope.propertytitleadd, "#" , "~" );
				$scope.propertytitleadd = tools_replaceAll($scope.propertytitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );
				
				var link = baseUrl+'addPropertyType?propertytitle='+$scope.propertytitleadd+'&propertycode='+$scope.propertycodeadd+'&description='+$scope.descriptionadd;
				
				var formData=new FormData();
				formData.append("image",image2add.files[0]);					
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.addrealestatetype = data;   			
	    			if($scope.addrealestatetype == "Success"){
	    				$scope.spin = 0;
	    				$scope.submitSuccess = 1;
	    				$scope.msgSuccess = "Data added successfully";
	    				$timeout(function(){
	    					$('#propertytypeModal').modal('hide');
	    					$scope.submitSuccess = 0;
	    					document.getElementById("propertytype").reset();
	    				}, 2000);
	    				
	    			} else {
	    				$scope.spin = 0;    				
	    				$scope.submitError = 1;
	    				$scope.msgError = "Data not inserted! Duplicate entry for category code!";
	    				$timeout(function(){
	    					$scope.submitError = 0;				
	    				}, 3000);
	    			}
	    			
				}).error(function(data, status, headers, config) {
					$scope.addrealestatetype = data;				
					$scope.spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addcategory;
					$timeout(function(){
						$('#propertytypeModal').modal('hide');
						document.getElementById("propertytype").reset();
					}, 5000);
					
				});    			
			}
		}
		
		$scope.addUnitMaster = function() {
			if(!$scope.unitname) {			
				$scope.errorUnitName = 1;
				$scope.msgUnitName = "Please enter unit name";
				document.getElementById("unitname").focus();
			} else {
				$scope.unitSpin = 1;			
				var link = baseUrl+'addUnitMaster?unitname='+$scope.unitname;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addunitmaster = data;
					var link = baseUrl+'getUnitNameList';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.unitNameList = data;
						$scope.unitSpin = 0;
						$scope.unitSubmitSuccess = 1;
						$scope.unitSuccessMsg = "Data added successfully.";
						$timeout(function() {
							$scope.unitSubmitSuccess = 0;
							$scope.unitname = "";
							$('#unitMasterModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.unitNameList = "Response Fail";
					});
				}).error(function(data, status, headers, config) {
					$scope.addunitmaster = "Response Fail";
				});
			}
		}
		
		// Price Label
		
		
		var link = baseUrl+'getAllPrices';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getPricesLabel = data;
		}).error(function(data, status, headers, config) {
			$scope.getPricesLabel = "Response Fail";
		});
		
		
		$scope.addPrice = function() {
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.pricelabeladd) {
				$scope.errorPriceLabel = 1;
				$scope.msgPriceLabel = "Please enter price label";
				document.getElementById("pricelabeladd").focus();
			} else {
				$scope.spin = 1;
				var link = baseUrl+'addPriceLabel?pricelabel='+$scope.pricelabeladd+'&description='+$scope.descriptionadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addprice = data;
					$scope.spin = 0;
					if($scope.addprice == 'Success'){
						$scope.priceSubmitError = 0;
						$scope.priceSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						var link = baseUrl+'getAllPrices';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getPricesLabel = data;
						}).error(function(data, status, headers, config) {
							$scope.getPricesLabel = "Response Fail";
						});
						$timeout(function(){
							$scope.priceSubmitSuccess = 0;
							$('#priceLabelModal').modal('hide');
							$scope.pricelabeladd="";
							$scope.descriptionadd="";
						}, 2000);
					} else {
						$scope.priceSubmitSuccess = 0;
						$scope.priceSubmitError = 1;
						$scope.errorMsg = $scope.addprice;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addprice = data;
					$scope.spin = 0;
					$scope.priceSubmitSuccess = 0;
					$scope.priceSubmitError = 1;
					$scope.errorMsg = $scope.addprice;
				});
			}
		}
		
		$scope.addUserType = function() {		
			if(!$scope.usertypename) {			
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please enter user type name";
				document.getElementById("usertypename").focus();
			} else {
				$scope.userTypeSpin = 1;			
				var link = baseUrl+'addUserType?usertypename='+$scope.usertypename;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addusertype = data;
					var link = baseUrl+'getUserTypes';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getUserTypes = data;
						$scope.userTypeSpin = 0;
						$scope.userTypeSubmitSuccess = 1;
						$scope.userTypeSuccessMsg = "Data added successfully.";
						$timeout(function() {
							$scope.userTypeSubmitSuccess = 0;
							$scope.usertypename = "";
							$('#userTypeModal').modal('hide');
						}, 2000);
					}).error(function(data, status, headers, config) {
						$scope.getUserTypes = "Response Fail";
					});
				}).error(function(data, status, headers, config) {
					$scope.addusertype = "Response Fail";
				});
			}
		}
		//Developer Company
		
		$scope.addDeveloperCompany = function() {
			$scope.ddateofbirth = "";
			$scope.aadharnumber = "";
			
			if(!$scope.dfirstnameadd) {
				$scope.dfirstnameadd = "";
			}
			if(!$scope.dmiddlenameadd) {
				$scope.dmiddlenameadd = "";
			}
			if(!$scope.dlastnameadd)	{
				$scope.dlastnameadd = "";
			}
			if(!$scope.dgenderadd)	{
				$scope.dgenderadd = "";
			}			
			if(!$scope.ddateofbirth || $scope.dateofbirth=="day/month/year")	{
				$scope.ddateofbirth = "";
			}
			if(!$scope.daadharnumber) {
				$scope.daadharnumber = "";
			}
			if(!$scope.dpassportnumberadd){
				$scope.dpassportnumberadd = "";
			}
			if(!$scope.dpannumberadd) {
				$scope.dpannumberadd = "";
			}
			if(!$scope.daddress1add) {
				$scope.daddress1add = "";
			}
			if(!$scope.daddress2add) {
				$scope.daddress2add = "";
			}
			if(!$scope.daddress3add) {
				$scope.daddress3add = "";
			}
			if(!$scope.dcountrynameadd) {
				$scope.dcountrynameadd = 0;
			}
			if(!$scope.dstatenameadd) {
				$scope.dstatenameadd = 0;
			}
			if(!$scope.dcitynameadd) {
				$scope.dcitynameadd = "";
			}
			if(!$scope.dpincodeadd) {
				$scope.dpincodeadd = "";
			}
			if(!$scope.dmobilenumberadd) {
				$scope.dmobilenumberadd = "";
			}
			if(!$scope.dlandlinenumberadd) {
				$scope.dlandlinenumberadd = "";
			}
			if(!$scope.demailadd) {
				$scope.demailadd = "";
			}
			if(!$scope.dpasswordadd) {
				$scope.dpasswordadd = "";
			}
			
			if(!$scope.dcompanynameadd) {				
				$scope.errorCompanyName = 1;
				$scope.msgCompanyName = "Please enter company name";
				document.getElementById("dcompanynameadd").focus();
			} else if(!$scope.dusertypenameadd) {				
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please select user type";
				document.getElementById("dusertypenameadd").focus();
			} else {
				$scope.spin = 1;		
				var link = baseUrl+'addUser?companyname='+$scope.dcompanynameadd+'&firstname='+$scope.dfirstnameadd+'&middlename='+$scope.dmiddlenameadd+'&lastname='+$scope.dlastnameadd+'&usertypename='+$scope.dusertypenameadd+'&gender='+$scope.dgenderadd+'&dateofbirth='+$scope.ddateofbirth+'&aadharnumber='+$scope.daadharnumber+'&passportnumber='+$scope.dpassportnumberadd+'&pannumber='+$scope.dpannumberadd+'&address1='+$scope.daddress1add+'&address2='+$scope.daddress2add+'&address3='+$scope.daddress3add+'&countryname='+$scope.dcountrynameadd+'&statename='+$scope.dstatenameadd+'&cityname='+$scope.dcitynameadd+'&pincode='+$scope.dpincodeadd+'&mobilenumber='+$scope.dmobilenumberadd+'&landlinenumber='+$scope.dlandlinenumberadd+'&email='+$scope.demailadd+'&password='+$scope.dpasswordadd;
				
				var formData=new FormData();
				formData.append("profilepicture",dprofilepictureadd.files[0]);					
				$http({method: 'POST',
					url: link,				
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction) {
			        	return data;
			        }
				}).success(function(data, status) {
					$scope.adduser = data;
					var link = baseUrl+'getUserNameByUserType?usertypeid='+"6";
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getArchitectName = data;
					}).error(function(data, status, headers, config) {
						$scope.getArchitectName = "Response Fail";
					});
					var link = baseUrl+'getUserName';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getUserName = data;
					}).error(function(data, status, headers, config) {
						$scope.getUserName = "Response Fail";
					});
					
					$scope.spin = 0;
					if($scope.adduser == 'Success'){
						$scope.userSubmitError = 0;
						$scope.userSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.userSubmitSuccess = 0;
							$('#companyModal').modal('hide');
							jQuery("#usermodule input[type='text'], input[type='checkbox'], input[type='file']").each(function() {
						        this.value = '';
						    });
						}, 2000);
					} else {
						$scope.userSubmitSuccess = 0;
						$scope.userSubmitError = 1;
						$scope.errorMsg = $scope.adduser;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.adduser = data;
					$scope.spin = 0;
					$scope.userSubmitSuccess = 0;
					$scope.userSubmitError = 1;
					$scope.errorMsg = "Something wrong! Please try again later!";
				});			
			}
		}
		
		
		//14Feb2020
		$scope.getProjectDetailsById = function(projectid){
			if(projectid != "null"){
				$("#editModal").modal("show")
			
				var link = baseUrl+'getProjectDetailById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectDetail = data;
					
					$scope.projectid = $scope.projectDetail.projectId;
					$scope.projecttitle = $scope.projectDetail.projectTitle;
					$scope.projectsubtitle = $scope.projectDetail.projectSubtitle;
					$scope.projectcode = $scope.projectDetail.projectCode;
					$scope.projectlogo = $scope.projectDetail.projectLogo;
					$scope.locationid = $scope.projectDetail.locationId;
					$scope.architectid = $scope.projectDetail.architectId;
					$scope.structuraldesignerid = $scope.projectDetail.structuralDesignerId;
					$scope.legaladvisorid = $scope.projectDetail.legalAdvisorId;
					$scope.developercompanyid = $scope.projectDetail.developerCompanyId;
					$scope.propertytypeid = $scope.projectDetail.propertyTypeId;				
					$scope.rereapproved = $scope.projectDetail.reraApprove;
					$scope.rerano = $scope.projectDetail.reraNo;
					$scope.projectlayout = $scope.projectDetail.layoutMap;
					$scope.watersourceedit = $scope.projectDetail.waterSource;
					$scope.drainageedit = $scope.projectDetail.drainage;
					$scope.workstatus = $scope.projectDetail.workStatus;
					$scope.description = $scope.projectDetail.description;
					$scope.projmainimg = $scope.projectDetail.projmainimg;
									
					var link = baseUrl+'getProjectSpecificationById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectSpecification = data;
					}).error(function(data, status, headers, config) {
						$scope.projectSpecification = "Response Fail";
					});
					
					var link = baseUrl+'getProjectAmenityById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectAmenity = data;
					}).error(function(data, status, headers, config) {
						$scope.projectAmenity = "Response Fail";
					});
					
					var link = baseUrl+'getProjectAreaById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectArea = data;
					}).error(function(data, status, headers, config) {
						$scope.projectArea = "Response Fail";
					});
					
					var link = baseUrl+'getProjectDetailsById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectDetails = data;
					}).error(function(data, status, headers, config) {
						$scope.projectDetails = "Response Fail";
					});
					
					var link = baseUrl+'getProjectPriceDetailsById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectPriceDetails = data;
					}).error(function(data, status, headers, config) {
						$scope.projectPriceDetails = "Response Fail";
					});
									
					var link = baseUrl+'getProjectPaymentScheduleById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.projectPaymentSchedule = data;
					}).error(function(data, status, headers, config) {
						$scope.projectPaymentSchedule = "Response Fail";
					});
					
					var link = baseUrl+'getPaymentDetailById?projectid='+projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.paymentById = data;				 	
					}).error(function(data, status, headers, config) {
						$scope.paymentById = "Response Fail";
					});
					
				}).error(function(data, status, headers, config) {
					$scope.projectDetail = "Response Fail";
				});
			}
		}
		
		$scope.editProjectFloorLayoutRow = function(){
			
			if(!$scope.layoutnumberofunitsedit){
				$scope.layoutnumberofunitsedit="";
			}
			if(!$scope.layouttowertitleedit){
				$scope.layouttowertitleedit=0;
			}
			if(!$scope.layoutrealestatecategoryidedit) {				
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryType1 = "Please category type!";
				document.getElementById("layoutrealestatecategoryidedit").focus();
			} else if($scope.projectflooredit == null && $scope.projectflooredit == undefined) {				
				$scope.errorFloor = 1;
				$scope.msgFloor = "Please select floor!";
				document.getElementById("projectflooredit").focus();
			} else if(document.getElementById("filelayoutedit").value == undefined || document.getElementById("filelayoutedit").value == "") {				
				$scope.errorImage = 1;
				$scope.msgImage = "Please choose image!";
				document.getElementById("filelayoutedit").focus();
				$timeout(function(){
					$scope.errorImage = 0;
				}, 2000);
			} else {
				$scope.projectinfospin = 1;
				
				var link = baseUrl+'editProjectFloorLayout?projectid='+$scope.projectid+'&categoryid='+$scope.layoutrealestatecategoryidedit+'&unitid='+$scope.layouttowertitleedit+'&floorNumber='+$scope.projectflooredit +'&totalUnit='+$scope.layoutnumberofunitsedit;
				var formData = new FormData();
				formData.append("filelayout",filelayoutedit.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editprojectlayout = data;
					$scope.projectinfospin = 0;
					if($scope.editprojectlayout == 'Success'){
						var link = baseUrl+'getProjectFloorLayoutById?projectid='+$scope.projectid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.projectFloorLayout = data;
						}).error(function(data, status, headers, config) {
							$scope.projectFloorLayout = "Response Fail";
						});					
					} else {
						$scope.errorMsg = $scope.editprojectlayout;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editprojectlayout = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editprojectlayout;
				});   			
			}
			
		}
		
		$scope.removeProjectFloorLayoutRow = function(floorlayoutid){
			var link = baseUrl+'deleteProjectFloorLayout?floorlayoutid='+floorlayoutid;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteprojectfloor = data;
				var link = baseUrl+'getProjectFloorLayoutById?projectid='+$scope.projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectFloorLayout = data;
				}).error(function(data, status, headers, config) {
					$scope.projectFloorLayout = "Response Fail";
				});	
			}).error(function(data, status, headers, config) {
				$scope.deleteprojectfloor = "Response Fail";
			});
		}
		
		$scope.checkSequence = function(sequence){
			var link = baseUrl+'getProjects';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getprojects = data;
				
				for(i in $scope.getprojects){
					if(sequence == $scope.getprojects[i].sequence){
						$scope.errorSeq = 1;
						$scope.msgSeq = "This sequence is already exist";
					}
				}
			}).error(function(data, status, headers, config) {
				$scope.getprojects = "Response Fail";
			});
		}
				
}]);