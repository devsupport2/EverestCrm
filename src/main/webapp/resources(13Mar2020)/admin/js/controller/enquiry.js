var app = angular.module("MyApp", ['angular.filter']);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('enquiryCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' , function ($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
	$scope.currentPage = 0;
	$scope.pageSize = 100;
	$scope.search = '';
	$scope.startindex = $scope.currentPage;
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	$scope.info = 0;
	$scope.success = 0;
	$scope.spin = 0;
	$scope.currentdate = new Date();
	$scope.todyadate = new Date();
	$scope.enquiryLoader = true;
	
	var link = baseUrl+'getAllOccupation';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.alloccupation = data;
	}).error(function(data, status, headers, config) {
		$scope.alloccupation = "Response Fail";
	});
	
	var link = baseUrl+'getAllRanges';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allrange = data;
	}).error(function(data, status, headers, config) {
		$scope.allrange = "Response Fail";
	});
	
	var link = baseUrl+'getAllDesignation';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.alldesignation = data;
	}).error(function(data, status, headers, config) {
		$scope.alldesignation = "Response Fail";
	});
	
	
	var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
	}).error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});
	
	$scope.getLastEnquiryList = function(){
		$scope.enquiryLoader = true;
		var link = baseUrl+'getLastHundredEnquiryList';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.enquiryLoader = false;
			$scope.spin = 0;
			$scope.getEnquiries = data;
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	}
	
	
	$scope.getEnquiryListByDate = function() {
		
		$scope.enquiryLoader = true;		
		$scope.spin = 1;
		
		var startdate = document.getElementById("startdate").value;
		var enddate = document.getElementById("enddate").value;

		if(!startdate) {
			startdate = $filter('date')(new Date(d.getFullYear(), d.getMonth(), 1), 'dd/MM/yyyy');
		}
		if(!enddate) {
			enddate = $filter('date')(new Date(d.getFullYear(), d.getMonth() + 1, 0), 'dd/MM/yyyy');
		}
		
		var link = baseUrl+'getEnquiryListByDate?startdate='+startdate+'&enddate='+enddate;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.enquiryLoader = false;
			$scope.spin = 0;
			$scope.getEnquiries = data;		
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	}
	
	$scope.getCount = function(strCat){
		return filterFilter( $scope.heroes, {comic:strCat}).length;
	}
	
	if($scope.pageSize == "All") {
		var link = baseUrl+'getAllOpenEnquiries';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.enquiryLoader = false;
			$scope.getEnquiries = data;
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	} else {
		var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.enquiryLoader = false;
			$scope.getEnquiries = data;		
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	}
	
	$scope.checkRecordSelectForDelete = function() {		
		$scope.d = 0;		
		angular.forEach($scope.getEnquiries, function(item) {
			if (item.selected) {
				$scope.d = $scope.d + 1
			}
		});		
	}
	
	$scope.deleteEnquiry = function() {
		angular.forEach($scope.getEnquiries, function(item) {
			if (item.selected) {
				var link = baseUrl+'deleteEnquiry?enquiryid='+item.enquiryId;				
				$http['delete'](link).success(function(data, status, headers, config) {
					$scope.deleteenquiry = data;
					
					var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {						
						$scope.getEnquiries = data;		
						$('#deleteModal').modal('hide');
					}).error(function(data, status, headers, config) {
						$scope.getEnquiries = "Response Fail";
					});	
					
				}).error(function(data, status, headers, config) {
					$scope.deleteenquiry = "Response Fail";
				});
			}
		});		
	}
	
	/*$scope.getEnquiriesByFilter = function(id, flag) {	
		$scope.enquiryLoader = true;
		if(id){
			var link = baseUrl+'getEnquiriesByFilter?id='+id+'&flag='+flag;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		} else {
			var link = baseUrl+'getAllOpenEnquiries';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;								
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
		
	}*/
	
	$scope.changeStatus = function() {
		$scope.enquiryLoader = true;
		if($scope.enquirystatus) {
			var link = baseUrl+'getEnquiriesByStatus?status='+$scope.enquirystatus;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		} else {
			if($scope.enquirystatus == undefined || $scope.enquirystatus == "") {
				var link = baseUrl+'getAllOpenEnquiries';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.enquiryLoader = false;
					$scope.getEnquiries = data;
				}).error(function(data, status, headers, config) {
					$scope.getEnquiries = "Response Fail";
				});
			}
		}				
	}

	$scope.next = function() {
		$scope.search = '';
		if($scope.pageSize == "All") {
			
		} else {
			$scope.enquiryLoader = true;
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
				
			var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
	}
		
	$scope.prev = function() {
		$scope.enquiryLoader = true;
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.enquiryLoader = false;
			$scope.getEnquiries = data;
		}).error(function(data, status, headers, config) {
			$scope.getEnquiries = "Response Fail";
		});
	}
		
	$scope.changePage = function() {
		$scope.enquiryLoader = true;
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		if($scope.pageSize == "All") {
			var link = baseUrl+'getAllOpenEnquiries';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		} else {
			var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
	}
		
	var link = baseUrl+'getTodayFollowupEnquiries';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.todayFollowupEnquiries = data;
	}).error(function(data, status, headers, config) {
		$scope.todayFollowupEnquiries = "Response Fail";
	});
		
	$scope.fromdate = $scope.todyadate.setDate($scope.todyadate.getDate() + 1);
	$scope.fromdate = $filter('date')(new Date($scope.fromdate), "dd/MM/yyyy");
	$scope.todate = $scope.todyadate.setDate($scope.todyadate.getDate() + 7);
	$scope.todate = $filter('date')(new Date($scope.todate), "dd/MM/yyyy");
	
	var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
	$http.get(link).success(function(data, status, headers, config) {
		$scope.followupEnquiriesByDate = data;
	}).error(function(data, status, headers, config) {
		$scope.followupEnquiriesByDate = "Response Fail";
	});
	
	$scope.getEnquiryFollowupsByDate = function(){
		$scope.fromdate = document.getElementById("fromdate").value;
		$scope.todate = document.getElementById("todate").value;
		var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.followupEnquiriesByDate = data;
		}).error(function(data, status, headers, config) {
			$scope.followupEnquiriesByDate = "Response Fail";
		});
	}
	
	var link = baseUrl+'getUserTypes';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getUserTypes = data;
	}).error(function(data, status, headers, config) {
		$scope.getUserTypes = "Response Fail";
	});
	
	$scope.getUserByUserType = function(usertypeid) {
		var link = baseUrl+'getUserNameByUserType?usertypeid='+usertypeid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserNameByUserType = data;			
		}).error(function(data, status, headers, config) {
			$scope.getUserNameByUserType = "Response Fail";
		});
	}
		
	$scope.searchEnquiry = function() {
		$scope.enquiryLoader = true;
		if(!$scope.search) {
			if($scope.pageSize == "All") {
				var link = baseUrl+'getAllOpenEnquiries';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.enquiryLoader = false;
					$scope.getEnquiries = data;
				}).error(function(data, status, headers, config) {
					$scope.getEnquiries = "Response Fail";
				});
			} else {
				var link = baseUrl+'getEnquiriesByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.enquiryLoader = false;
					$scope.getEnquiries = data;
				}).error(function(data, status, headers, config) {
					$scope.getEnquiries = "Response Fail";
				});
			}
		} else {
			var link = baseUrl+'searchEnquiry?keyword='+$scope.search;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.enquiryLoader = false;
				$scope.getEnquiries = data;
			}).error(function(data, status, headers, config) {
				$scope.getEnquiries = "Response Fail";
			});
		}
	}	
		
	var link = baseUrl+'getProjectName';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getProjectName = data;
	}).error(function(data, status, headers, config) {
		$scope.getProjectName = "Response Fail";
	});	
		
	var link = baseUrl+'getRoomName';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getRoomName = data;
	}).error(function(data, status, headers, config) {
		$scope.getRoomName = "Response Fail";
	});
				
	var link = baseUrl+'getEmployees';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getEmployees = data;
	}).error(function(data, status, headers, config) {
		$scope.getEmployees = "Response Fail";
	});
		
	var link = baseUrl+'getClientAndProspectTitle';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getClientNames = data;
	}).error(function(data, status, headers, config) {
		$scope.getClientNames = "Response Fail";
	});
		
	var link = baseUrl+'getStatusReason';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getStatusReason = data;		
	}).error(function(data, status, headers, config) {
		$scope.getStatusReason = "Response Fail";
	});
	
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
		if(countryname == "" || countryname == undefined) {
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
		
	var link = baseUrl+'getAllEnquiryPropertyName';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allEnquiryProperties = data;
	}).error(function(data, status, headers, config) {
		$scope.allEnquiryProperties = "Response Fail";
	});		
		
	$scope.onChangeUSerType = function() {
		if($scope.usertypeidadd  == null || $scope.usertypeidadd==undefined) {				
			$scope.getReferenceNames = "";				
		}
		else{				
			var link = baseUrl+'getReferenceTitleByUserTypeId?usertypeid='+$scope.usertypeidadd;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getReferenceNames = data;
			}).error(function(data, status, headers, config) {
				$scope.getReferenceNames = "Response Fail";
			});			
		}
	}
	
	var link = baseUrl+'getRealestateName';			
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getAllCategory = data;
	}).error(function(data, status, headers, config) {
		$scope.getAllCategory = "Response Fail";
	});
	
	var link = baseUrl+'getAllRealestateType';			
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getRealestateType = data;
	}).error(function(data, status, headers, config) {
		$scope.getRealestateType = "Response Fail";
	});
	
	
	var link = baseUrl+'getUnitNameList';			
	$http.get(link).success(function(data, status, headers, config) {
		$scope.projectUnitNameList = data;
	}).error(function(data, status, headers, config) {
		$scope.projectUnitNameList = "Response Fail";
	});
	
	var link = baseUrl+'getSubcategory';		
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getSubcategory = data;
	}).error(function(data, status, headers, config) {
		$scope.getSubcategory = "Response Fail";
	});
	
	
	$scope.onChangeCategory = function(categoryid) {
		var link = baseUrl+'getAllRealestateSubcategoryTitleByRealestateId?realestateid='+categoryid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.projectSubcategory = data;
		}).error(function(data, status, headers, config) {
			$scope.projectSubcategory = "Response Fail";
		});
	}
	
	/*$scope.onChangeSubcategory = function(projectid, categoryid, subcategoryid) {			
		var link = baseUrl+'getProjectRealestateTypeById?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.projectRealestateType = data;
		}).error(function(data, status, headers, config) {
			$scope.projectRealestateType = "Response Fail";
		});
	}*/
		
	$scope.onChangeProject = function(projectid, categoryid, subcateid) {
		var link = baseUrl+'getPropertyNumberListForEnquiryById?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcateid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.propertyNumberList = data;
		}).error(function(data, status, headers, config) {
			$scope.propertyNumberList = "Response Fail";
		});
	}
	
	$scope.onChangeUnitName = function(projectid, categoryid, subcategoryid, typeid, unitmasterid) {
		var link = baseUrl+'getPropertyNumberListByIdAndUnitMasterId?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid+'&typeid='+typeid+'&unitmasterid='+unitmasterid;			
		$http.get(link).success(function(data, status, headers, config) {
			$scope.propertyNumberList = data;				
		}).error(function(data, status, headers, config) {
			$scope.propertyNumberList = "Response Fail";				
		});
	}
	
	$scope.onchangeTest = function(subcategory){
		
	}
		
	$scope.setFlag = function() {			
		$scope.errorProjectType = 0;
		$scope.errorRealestateCategoryTitle = 0;
		$scope.errorRealestateSubcategoryTitle = 0;
		$scope.errorRealestateTypeTitle = 0;
		$scope.errortowertitle = 0;
		$scope.errorEnquiryDate = 0;			
		$scope.errorEnquiryVia = 0;
		$scope.errorEnquiryClient = 0;			
		$scope.errorEnquiryDate = 0;
	}
	
	$scope.propertyforenquirylist = [];		
	$scope.addPropertyForEnquiryRow = function() {
		
		
		if(!$scope.realsubidadd) {
			$scope.realsubidadd = 0;
		}
		if(!$scope.realtypeidadd) {
			$scope.realtypeidadd = 0;
		}
		if(!$scope.unitmasteridadd) {
			$scope.unitmasteridadd = 0;
		}
		if(!$scope.projectidadd) {
			$scope.projectidadd = 0;
		}
		if(!$scope.propertyidadd) {
			$scope.propertyidadd = 0;
		}
								
		if(!$scope.realeidadd) {				
			$scope.errorRealestateCategoryTitle = 1;
			$scope.msgCategoryType = "Please select category!";
			document.getElementById("realeidadd").focus();
		} else {
			
			var link = baseUrl+'getAllRealestateSubcategoryTitleByRealestateId?realestateid='+$scope.realeidadd;		
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectSubcategory = data;
			}).error(function(data, status, headers, config) {
				$scope.projectSubcategory = "Response Fail";
			});
			
			for (i in $scope.getProjectName) {
				if ($scope.getProjectName[i].projectId == $scope.projectidadd) {
					$scope.projectname = $scope.getProjectName[i].projectTitle;
					break;
				}					
			}
			for (i in $scope.getAllCategory) {
				if ($scope.getAllCategory[i].realestateTypeId == $scope.realeidadd) {
					$scope.categorytitle = $scope.getAllCategory[i].realestateTypeName;
					break;
				}
			}
			for (i in $scope.projectSubcategory) {
				if ($scope.projectSubcategory[i].realestateSubcategoryId == $scope.realsubidadd) {
					$scope.subcategorytitle = $scope.projectSubcategory[i].subcategoryTitle;
					break;
				}
			}
			for (i in $scope.getRealestateType) {
				if ($scope.getRealestateType[i].realestateId == $scope.realtypeidadd) {
					$scope.realstitle = $scope.getRealestateType[i].realestateTitle;
					break;
				}
			}
			for (i in $scope.projectUnitNameList) {
				if ($scope.projectUnitNameList[i].unitMasterId == $scope.unitmasteridadd) {
					$scope.unitname = $scope.projectUnitNameList[i].unitName;
					break;
				} else {
					$scope.unitname = "";
				}
			}
			for (i in $scope.propertyNumberList) {
				if ($scope.propertyNumberList[i].propertyId == $scope.propertyidadd) {
					$scope.propertynumber = $scope.propertyNumberList[i].propertyTitle;
					break;
				}
			}
			$scope.propertyforenquirylist.push({'projectName' : $scope.projectname, 'projectId' : $scope.projectidadd, 'title' : $scope.categorytitle, 'realestateTypeId' : $scope.realeidadd, 'subcategTitle' : $scope.subcategorytitle, 'realestateSubcategoryId' : $scope.realsubidadd, 'realestateId' : $scope.realtypeidadd, 'realstatetitle' : $scope.realstitle , 'unitMasterId' : $scope.unitmasteridadd, 'unitName' : $scope.unitname, 'propertyId' : $scope.propertyidadd, 'propertyTitle' : $scope.propertynumber});
		}
	}
	
	$scope.removePropertyForEnquiryRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.propertyforenquirylist.length; i++) {
			if($scope.propertyforenquirylist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record...Please try again!");
			return;
		}
		$scope.propertyforenquirylist.splice(index, 1);
	};
	
	$scope.assignlist = [];
	$scope.addAssignRow = function() {
		if($scope.employeeidadd==undefined || $scope.employeeidadd=="") {			
			$scope.errorEmpName = 1;
			$scope.msgEmpName = "Please select an employee!";
			document.getElementById("employeeidadd").focus();
		} else {			
			var link = baseUrl+'getUserDetailById?userid='+$scope.employeeidadd;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;			
            	
            	$scope.firstname = $scope.userDetailById.firstName;            	
            	$scope.lastname = $scope.userDetailById.lastName;
            	
            	$scope.assignlist.push({'userId':$scope.employeeidadd, 'firstName':$scope.firstname, 'lastName':$scope.lastname});
    			$scope.employeeidadd = "";
    			
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});			
		}
	}

	$scope.removeAssignRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.assignlist.length; i++) {
			if($scope.assignlist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record..Please try again!");
			return;
		}
		$scope.assignlist.splice(index, 1);
	};
		
		
	$scope.addEnquiry = function() {		
		$scope.enquirydate = document.getElementById("datepicker").value;
		$scope.employeeidadd = 0;
		$scope.clientidadd = document.getElementById("clientidadd").value;
		$scope.referenceidadd = document.getElementById("referenceidadd").value;			
		
		if($scope.remarksadd==undefined) {
			$scope.remarksadd= "";
		}
		if(!$scope.occupationidadd) {
			$scope.occupationidadd= 0;
		}
		if(!$scope.designationidadd) {
			$scope.designationidadd= 0;
		}
		
		if(!$scope.chooseoptionadd) {
			$scope.chooseoptionadd= "";
		}
		if(!$scope.budgetadd) {
			$scope.budgetadd= 0;
		}
		if(!$scope.featuresadd) {
			$scope.featuresadd= "";
		}
		if(!$scope.consideringprojectadd) {
			$scope.consideringprojectadd= "";
		}
		if(!$scope.expectingtimeadd) {
			$scope.expectingtimeadd= 0;
		}
		if($scope.referenceidadd==undefined || $scope.referenceidadd=="") {
			$scope.referenceidadd= 0;
		}			
		if($scope.enquirydate==undefined || $scope.enquirydate=="") {				
			$scope.errorEnquiryDate = 1;
			$scope.msgEnquiryDate = "Please select enquiry date!";
			document.getElementById("enquirydate").focus();
		} else if($scope.enquiryviaadd==undefined || $scope.enquiryviaadd=="") {
			$scope.errorEnquiryVia = 1;
			$scope.msgEnquiryVia = "Please select enquiry via!";
			document.getElementById("enquiryviaadd").focus();				
		} else if($scope.clientidadd==undefined || $scope.clientidadd=="") {
			$scope.errorEnquiryClient = 1;
			$scope.msgEnquiryClient = "Please select client!!";
			document.getElementById("clientidadd").focus();
		} else {
			var a = 0, b = 0;
			
			$scope.spin = 1;				
			var link = baseUrl+'addEnquiry?enquirydate='+$scope.enquirydate+'&enquiryvia='+$scope.enquiryviaadd+'&referenceid='+$scope.referenceidadd+'&clientid='+$scope.clientidadd+'&employeeid='+$scope.employeeidadd+'&remark='+$scope.remarksadd+'&occupationid='+$scope.occupationidadd+'&designationid='+$scope.designationidadd+'&chooseoption='+$scope.chooseoptionadd+'&budgetid='+$scope.budgetadd+'&features='+$scope.featuresadd+'&consideringproject='+$scope.consideringprojectadd+'&expectingtime='+$scope.expectingtimeadd;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addenquiry = data;
				angular.forEach($scope.propertyforenquirylist,function(item) {						
					var link = baseUrl+'addEnquiryProperty?projectid='+item.projectId+'&realestatetypeid='+item.realestateTypeId+'&realestateSubcategoryid='+item.realestateSubcategoryId+'&realestateid='+item.realestateId+'&unitmasterid='+item.unitMasterId+'&propertyid='+item.propertyId;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addpropertyenquiry = data;
						a = a + 1;
						if($scope.propertyforenquirylist.length == a && $scope.assignlist.length == b) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
					}).error(function(data, status, headers, config) {
						$scope.addpropertyenquiry = "Response Fail";
					});
				});
				
				angular.forEach($scope.assignlist,function(item) {												
					var link = baseUrl+'addEnquiryAssign?userid='+item.userId;
					$http.post(link).success(function(data, status, headers, config) {
						$scope.addenquiryassign = data;
						b = b + 1;
						if($scope.propertyforenquirylist.length == a && $scope.assignlist.length == b) {
							$scope.spin = 0;
							$scope.success = 1;
							$scope.message = "Enquiry Added Successfully.";
							$timeout(function(){
								$scope.success = 0;
								$window.location.href = adminurl+'manage_enquiry';
							}, 2000);
						}
					}).error(function(data, status, headers, config) {
						$scope.addenquiryassign = "Response Fail";
					});
				});
				if($scope.addenquiry == "Success" && $scope.propertyforenquirylist.length == a && $scope.assignlist.length == b) {
					$scope.spin = 0;
					$scope.success = 1;
					$scope.message = "Enquiry Added Successfully.";
					$timeout(function(){
						$scope.success = 0;
						$window.location.href = adminurl+'manage_enquiry';
					}, 2000);
				}				
			}).error(function(data, status, headers, config) {
				$scope.addenquiry = "Response Fail";
			});
		}
	}		
		
	$scope.getEnquiryDetail = function(enquiryid) {
		$scope.logLoader = true;
		$scope.followupLoader = true;	
		var link = baseUrl+'getEnquiryDetailsById?enquiryid='+enquiryid;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getEnquiryDetailById = data;
			$scope.enquiryid = $scope.getEnquiryDetailById.enquiryId;				
        	$scope.userid = $scope.getEnquiryDetailById.referenceId;
        	
        	if($scope.userid != 0){
        		var link = baseUrl+'getUserDetailById?userid='+$scope.userid;        	
        		$http.get(link).success(function(data, status, headers, config) {
        			$scope.getUserDetailById = data;        			
        			$scope.usertypeid = $scope.getUserDetailById.userTypeId;
        			
        			var link = baseUrl+'getUserNameByUserType?usertypeid='+$scope.usertypeid;
        			$http.get(link).success(function(data, status, headers, config) {
        				$scope.getUserNameByUserType = data;
        				$scope.referenceid = $scope.userid;
        			}).error(function(data, status, headers, config) {
        				$scope.getUserNameByUserType = "Response Fail";
        			});
        			
        		}).error(function(data, status, headers, config) {
        			$scope.getUserDetailById = "Response Fail";
        		});
        	} else {
        		$scope.usertypeid = "";
        		$scope.referenceid = "";
        	}
        	
        	$scope.clientid = $scope.getEnquiryDetailById.clientId;
        	
        	$scope.getUser($scope.clientid);
        	$scope.enquirydate = $scope.getEnquiryDetailById.enquiryDate;
        	$scope.enquiryvia = $scope.getEnquiryDetailById.enquiryVia;
        	$scope.remarks = $scope.getEnquiryDetailById.enquriRemarks;
        	$scope.occupationid = $scope.getEnquiryDetailById.occupationId;
        	$scope.designationid = $scope.getEnquiryDetailById.designationId;
        	
    		$scope.chooseoption= $scope.getEnquiryDetailById.projectType;
    		$scope.budget= $scope.getEnquiryDetailById.budgetId;
    		$scope.features= $scope.getEnquiryDetailById.importantFeatures;
    		$scope.consideringproject= $scope.getEnquiryDetailById.consideringProject;
    		$scope.expectingtime= $scope.getEnquiryDetailById.finalizedExpectingTime;
        	
        	var link = baseUrl+'getEnquiryPropertyByEnquiryId?enquiryid='+enquiryid;        	
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getenquiryproperty = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getenquiryproperty = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryAssignByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryLogByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.logLoader = false;
    			$scope.getEnquiryLog = data;    			
    		}).error(function(data, status, headers, config) {
    			$scope.getEnquiryLog = "Response Fail";
    		});
    		
    		var link = baseUrl+'getEnquiryFollowupByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.followupLoader = false;
    			$scope.getEnquiryFollowup = data;
    		}).error(function(data, status, headers, config) {
    			$scope.getEnquiryFollowup = "Response Fail";
    		});
			
		}).error(function(data, status, headers, config) {
			$scope.getEnquiryDetailById = "Response Fail";
		});	
	}
	
	$scope.editEnquiryProperty = function(enquiryid) {	
		if(!$scope.subcategoryid) {
			$scope.subcategoryid = 0;
		}
		if(!$scope.realestateid) {
			$scope.realestateid = 0;
		}
		if(!$scope.unitmasterid) {
			$scope.unitmasterid = 0;
		}
		if(!$scope.projectidedit) {
			$scope.projectidedit = 0;
		}
		if(!$scope.propertyid) {
			$scope.propertyid = 0;
		}
		
		if(!$scope.categoryid) {				
			$scope.errorRealestateCategoryTitle = 1;
			$scope.msgCategoryType = "Please select category!";
			document.getElementById("categoryid").focus();
		} else {
			$scope.enqspin = 1;
			var link = baseUrl+'editEnquiryProperty?enquiryid='+enquiryid+'&projectid='+$scope.projectidedit+'&categoryid='+$scope.categoryid+'&subcategoryid='+$scope.subcategoryid+'&realestateid='+$scope.realestateid+'&unitmasterid='+$scope.unitmasterid+'&propertyid='+$scope.propertyid;				
			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editenquiryproperty = data;
				
				$scope.enqspin = 0;					
				if($scope.editenquiryproperty == 'Success'){						
					var link = baseUrl+'getEnquiryPropertyByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getenquiryproperty = data;
		    		}).error(function(data, status, headers, config) {
		    			$scope.getenquiryproperty = "Response Fail";
		    		});
				} else {
					$scope.errorMsg = $scope.editenquiryproperty;
				}					
			}).error(function(data, status, headers, config) {
				$scope.editenquiryproperty = data;
				$scope.areaSubmitSuccess = 0;
				$scope.areaSubmitError = 1;
				$scope.errorMsg = $scope.editenquiryproperty;
			});   			
		}
	}
	
	$scope.addAssignRowEdit = function(enquiryid) {
		if($scope.employeeid==undefined || $scope.employeeid=="") {			
			$scope.errorEmpName = 1;
			$scope.msgEmpName = "Please select an employee!";
			document.getElementById("employeeid").focus();
		} else {
			$scope.spinEmp = 1;
			
			var link = baseUrl+'editEnquiryAssign?enquiryid='+enquiryid+'&userid='+$scope.employeeid;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editenquiryassign = data;												
				if($scope.editenquiryassign == "Success") {
					var link = baseUrl+'getEnquiryAssignByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getenquiryassignlist = data;		    			
		    			$scope.spinEmp = 0;
		    		}).error(function(data, status, headers, config) {
		    			$scope.getenquiryassignlist = "Response Fail";
		    		});											
				}
			}).error(function(data, status, headers, config) {
				$scope.editenquiryassign = "Response Fail";
			});
						
		}
	}
	
	$scope.removeAssignRowEdit = function(id, enquiryid) {
		var link = baseUrl+'deleteEnquiryAssignRow?id='+id;		
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deleteenquiryassign = data;
			var link = baseUrl+'getEnquiryAssignByEnquiryId?enquiryid='+enquiryid;
    		$http.get(link).success(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = data;  			
    		}).error(function(data, status, headers, config) {
    			$scope.getenquiryassignlist = "Response Fail";
    		});
		}).error(function(data, status, headers, config) {
			$scope.deleteenquiryassign = "Response Fail";
		});
	}
	
		
	$scope.editEnquiry = function(enquiryid) {
		
		$scope.enquirydate = document.getElementById("datepicker1").value;
		$scope.employeeid = 0;
		if($scope.remarks==undefined) {
			$scope.remarks= "";
		}
		if(!$scope.occupationid) {
			$scope.occupationid= 0;
		}
		if(!$scope.designationid) {
			$scope.designationid= 0;
		}
		
		if(!$scope.chooseoption) {
			$scope.chooseoption= "";
		}
		if(!$scope.budget) {
			$scope.budget= 0;
		}
		if(!$scope.features) {
			$scope.features= "";
		}
		if(!$scope.consideringproject) {
			$scope.consideringproject= "";
		}
		if(!$scope.expectingtime) {
			$scope.expectingtime= 0;
		}
		
		if($scope.referenceid==undefined || $scope.referenceid=="") {
			$scope.referenceid=0;
		}
		if($scope.enquirydate==undefined) {
			document.getElementById("datepicker1").focus();
			$scope.info = 1;
			$scope.message = "Please select enquiry date!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else if($scope.enquiryvia==undefined) {
			document.getElementById("enquiryvia").focus();
			$scope.info = 1;
			$scope.message = "Please select enquiry via!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else if($scope.clientid==undefined) {
			document.getElementById("clientid").focus();
			$scope.info = 1;
			$scope.message = "Please select client!";
			$timeout(function(){
				$scope.info = 0;
			}, 2000);
		} else {
			$scope.spin = 1;			
			var link = baseUrl+'editEnquiry?enquiryid='+enquiryid+'&enquirydate='+$scope.enquirydate+'&enquiryvia='+$scope.enquiryvia+'&referenceid='+$scope.referenceid+'&clientid='+$scope.clientid+'&employeeid='+$scope.employeeid+'&remark='+$scope.remarks+'&occupationid='+$scope.occupationid+'&designationid='+$scope.designationid+'&chooseoption='+$scope.chooseoption+'&budgetid='+$scope.budget+'&features='+$scope.features+'&consideringproject='+$scope.consideringproject+'&expectingtime='+$scope.expectingtime;
			$http.post(link).success(function(data, status, headers, config) {
				$scope.editenquiry = data;
				$scope.spin = 0;
				$scope.success = 1;
				$scope.message = "Enquiry Updated Successfully.";
				$timeout(function(){
					$scope.success = 0;
					$window.location.href = adminurl+'manage_enquiry';
				}, 2000);
			}).error(function(data, status, headers, config) {
				$scope.editenquiry = "Response Fail";
			});				
		}
	}
	
	$scope.removeEnquiryProperties = function(enquiryPropertyId, enquiryid) {		
		
		var link = baseUrl+'deleteEnquiryProperty?enquirypropertyid='+enquiryPropertyId;		
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deleteenquiryproperty = data;
			
			var link = baseUrl+'getEnquiryPropertyByEnquiryId?enquiryid='+enquiryid;
	   		$http.get(link).success(function(data, status, headers, config) {
	   			$scope.getenquiryproperty = data;
	   		}).error(function(data, status, headers, config) {
	   			$scope.getenquiryproperty = "Response Fail";
	   		});
	   		
		}).error(function(data, status, headers, config) {
			$scope.deleteenquiryproperty = "Response Fail";
		});	
	}
	
	
	$scope.emplist = [];
	$scope.addEmpRow = function() {
		if($scope.employeeidadd==undefined || $scope.employeeidadd=="") {			
			$scope.errorEmpName = 1;
			$scope.msgEmpName = "Please select an employee!";
			document.getElementById("employeeidadd").focus();
		} else {
			
			var link = baseUrl+'getUserDetailById?userid='+$scope.employeeidadd;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.userDetailById = data;			
            	
            	$scope.firstname = $scope.userDetailById.firstName;            	
            	$scope.lastname = $scope.userDetailById.lastName;
            	
            	$scope.emplist.push({'userId':$scope.employeeidadd, 'firstName':$scope.firstname, 'lastName':$scope.lastname});
    			$scope.employeeidadd = "";
    			
			}).error(function(data, status, headers, config) {
				$scope.userDetailById = "Response Fail";
			});			
		}
	}
	$scope.removeEmpRow = function(item) {
		var index = -1;
		for(var i=0; i<$scope.emplist.length; i++) {
			if($scope.emplist[i] == item){
				index = i;
				break;
			}
		}
		if(index < 0) {
			$window.alert("Error while removing record..Please try again!");
			return;
		}
		$scope.emplist.splice(index, 1);
	};
	
	$scope.addNewFollowup = function(){
		var enquiryid = 0;
		$scope.followupdatetime = document.getElementById("datetimepicker2").value;
		
		if (document.getElementById("notifyfollowupviaemail").checked == true)
			$scope.notifyfollowupviaemail = 'Yes';
		if (document.getElementById("notifyfollowupviaemail").checked == false)
			$scope.notifyfollowupviaemail = 'No';
		if (document.getElementById("notifyfollowupviasms").checked == true)
			$scope.notifyfollowupviasms = 'Yes';
		if (document.getElementById("notifyfollowupviasms").checked == false)
			$scope.notifyfollowupviasms = 'No';
		
		if(!$scope.followupdatetime){			
			$scope.errorFollowupDate = 1;
			$scope.msgFollowupDate = "Please enter date & time ";
			document.getElementById("followupdatetime").focus();
		} else if(!$scope.followupremark){
			$scope.errorFollowupRemark = 1;
			$scope.msgFollowupRemark = "Please enter your remark ";
			document.getElementById("followupremark").focus();		
		} else {
			$scope.spinFollowup = 1;
			$scope.followupremark = tools_replaceAll($scope.followupremark, "&" , "$" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "#" , "~" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "%" , "!" );
			
			var link = baseUrl+'addEnquiryFollowup?enquiryid='+enquiryid+'&followupremark='+$scope.followupremark+'&followupdatetime='+$scope.followupdatetime+'&notifyfollowupviaemail='+$scope.notifyfollowupviaemail+'&notifyfollowupviasms='+$scope.notifyfollowupviasms;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addFollowup = data;												
				if($scope.addFollowup == "Success") {
					
					angular.forEach($scope.emplist,function(item) {												
						var link = baseUrl+'addEmpFollowup?userid='+item.userId;
						$http.post(link).success(function(data, status, headers, config) {
							$scope.addempfollowup = data;
							
							var link = baseUrl+'getTodayFollowupEnquiries';
				   			$http.get(link).success(function(data, status, headers, config) {
				   				$scope.todayFollowupEnquiries = data;
				   				
				   				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
				   				$http.get(link).success(function(data, status, headers, config) {
				   					$scope.followupEnquiriesByDate = data;	   					
					    			
					    			$('#toDoModal').modal('hide');
					    			$scope.spinFollowup = 0;
					    			$scope.followupremark = "";
					    			
					    			var link = baseUrl+'sendFollowupNotification';
					    			$http.post(link).success(function(data, status, headers, config) {
					    				$scope.sendfollowupnotification = data;			    					
					    			}).error(function(data, status, headers, config) {
					    				$scope.sendfollowupnotification = "Response Fail";
					    			});
					    			
				    			}).error(function(data, status, headers, config) {
				    				$scope.followupEnquiriesByDate = "Response Fail";
				    			});		    				
				    						    			
				    		}).error(function(data, status, headers, config) {
				    			$scope.todayFollowupEnquiries = "Response Fail";
				    		});								
							
						}).error(function(data, status, headers, config) {
							$scope.addempfollowup = "Response Fail";
						});
					});
					
					if($scope.emplist.length == 0){
						var link = baseUrl+'getTodayFollowupEnquiries';
			   			$http.get(link).success(function(data, status, headers, config) {
			   				$scope.todayFollowupEnquiries = data;
			   				
			   				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
			   				$http.get(link).success(function(data, status, headers, config) {
			   					$scope.followupEnquiriesByDate = data;	   					
				    			
				    			$('#toDoModal').modal('hide');
				    			$scope.spinFollowup = 0;
				    			$scope.followupremark = "";
				    			
			    			}).error(function(data, status, headers, config) {
			    				$scope.followupEnquiriesByDate = "Response Fail";
			    			});		    				
			    						    			
			    		}).error(function(data, status, headers, config) {
			    			$scope.todayFollowupEnquiries = "Response Fail";
			    		});	
					}
				}
			}).error(function(data, status, headers, config) {
				$scope.addFollowup = "Response Fail";
			});
		}
	}
	
	$scope.addEnquiryLog = function(enquiryid) {
		$scope.logdatetime = document.getElementById("datetimepicker").value;
		
		if (document.getElementById("notifylogviaemail").checked == true)
			$scope.notifylogviaemail = 'Yes';
		if (document.getElementById("notifylogviaemail").checked == false)
			$scope.notifylogviaemail = 'No';
		if (document.getElementById("notifylogviasms").checked == true)
			$scope.notifylogviasms = 'Yes';
		if (document.getElementById("notifylogviasms").checked == false)
			$scope.notifylogviasms = 'No';
		
		if(!$scope.enquirylog){			
			$scope.errorEnquiryLog = 1;
			$scope.msgEnquiryLog = "Please enter your remark";
			document.getElementById("enquirylog").focus();
		} else {
			$scope.spinLog = 1;
			$scope.enquirylog = tools_replaceAll($scope.enquirylog, "&" , "$" );
			$scope.enquirylog = tools_replaceAll($scope.enquirylog, "#" , "~" );
			$scope.enquirylog = tools_replaceAll($scope.enquirylog, "%" , "!" );
			
			var link = baseUrl+'addEnquiryLog?enquiryid='+enquiryid+'&enquirylog='+$scope.enquirylog+'&logdatetime='+$scope.logdatetime+'&notifylogviaemail='+$scope.notifylogviaemail+'&notifylogviasms='+$scope.notifylogviasms;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addEnquiryRemark = data;												
				if($scope.addEnquiryRemark == "Success") {					
					var link = baseUrl+'getEnquiryLogByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getEnquiryLog = data;
		    			$scope.spinLog = 0;
		    			var link = baseUrl+'getAllOpenEnquiries';
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.getEnquiries = data;
		    				$scope.enquirylog = "";
			    			$scope.spinLog = 0;
			    			
		    				var link = baseUrl+'sendLogNotification';
			    			$http.post(link).success(function(data, status, headers, config) {
			    				$scope.sendlognotification = data;			    					
			    			}).error(function(data, status, headers, config) {
			    				$scope.sendlognotification = "Response Fail";
			    			});			    			
		    					
		    			}).error(function(data, status, headers, config) {
		    				$scope.getEnquiries = "Response Fail";
		    			});		    					    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.getEnquiryLog = "Response Fail";
		    		});					
				}
			}).error(function(data, status, headers, config) {
				$scope.addEnquiryRemark = "Response Fail";
			});
		}
	}
	
	$scope.addEnquiryFollowup = function(enquiryid) {
		$scope.followupdatetime = document.getElementById("datetimepicker1").value;
		
		if (document.getElementById("notifyfollowupviaemail").checked == true)
			$scope.notifyfollowupviaemail = 'Yes';
		if (document.getElementById("notifyfollowupviaemail").checked == false)
			$scope.notifyfollowupviaemail = 'No';
		if (document.getElementById("notifyfollowupviasms").checked == true)
			$scope.notifyfollowupviasms = 'Yes';
		if (document.getElementById("notifyfollowupviasms").checked == false)
			$scope.notifyfollowupviasms = 'No';
		
		if(!$scope.followupremark){
			$scope.followupremark = "";
		}
		
		if(!$scope.followupdatetime){			
			$scope.errorFollowupDate = 1;
			$scope.msgFollowupDate = "Please enter date & time ";
			document.getElementById("followupdatetime").focus();
		} else {
			$scope.spinFollowup = 1;
			$scope.followupremark = tools_replaceAll($scope.followupremark, "&" , "$" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "#" , "~" );
			$scope.followupremark = tools_replaceAll($scope.followupremark, "%" , "!" );
			
			var link = baseUrl+'addEnquiryFollowup?enquiryid='+enquiryid+'&followupremark='+$scope.followupremark+'&followupdatetime='+$scope.followupdatetime+'&notifyfollowupviaemail='+$scope.notifyfollowupviaemail+'&notifyfollowupviasms='+$scope.notifyfollowupviasms;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addFollowup = data;												
				if($scope.addFollowup == "Success") {					
					var link = baseUrl+'getEnquiryFollowupByEnquiryId?enquiryid='+enquiryid;
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getEnquiryFollowup = data;
		    			
		    			var link = baseUrl+'getTodayFollowupEnquiries';
		    			$http.get(link).success(function(data, status, headers, config) {
		    				$scope.todayFollowupEnquiries = data;
		    				
		    				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
		    				$http.get(link).success(function(data, status, headers, config) {
		    					$scope.followupEnquiriesByDate = data;
		    					
		    					$scope.followupremark = "";
				    			$scope.spinFollowup = 0;
				    			
				    			var link = baseUrl+'sendFollowupNotification';
				    			$http.post(link).success(function(data, status, headers, config) {
				    				$scope.sendfollowupnotification = data;			    					
				    			}).error(function(data, status, headers, config) {
				    				$scope.sendfollowupnotification = "Response Fail";
				    			});
				    			
		    				}).error(function(data, status, headers, config) {
		    					$scope.followupEnquiriesByDate = "Response Fail";
		    				});		    				
		    							    			
		    			}).error(function(data, status, headers, config) {
		    				$scope.todayFollowupEnquiries = "Response Fail";
		    			});		    			
		    			
		    		}).error(function(data, status, headers, config) {
		    			$scope.getEnquiryFollowup = "Response Fail";
		    		});					
				}
			}).error(function(data, status, headers, config) {
				$scope.addFollowup = "Response Fail";
			});
		}
	}
	
	$scope.addStatusReason = function(){
		if(!$scope.reason){			
			$scope.errorStatusReason = 1;
			$scope.msgStatusReason = "Please enter reason";
			document.getElementById("reason").focus();
		} else {
			$scope.spinReason = 1;
			$scope.reason = tools_replaceAll($scope.reason, "&" , "$" );
			$scope.reason = tools_replaceAll($scope.reason, "#" , "~" );
			$scope.reason = tools_replaceAll($scope.reason, "%" , "!" );
			
			var link = baseUrl+'addStatusReason?reason='+$scope.reason;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.addstatusreason = data;												
				if($scope.addstatusreason == "Success") {					
					var link = baseUrl+'getStatusReason';
		    		$http.get(link).success(function(data, status, headers, config) {
		    			$scope.getStatusReason = data;		    			
		    			$scope.spinReason = 0;
		    			$scope.reasonSubmitSuccess = 1;
		    			$scope.reasonSuccessMsg = "Data added"
		    			$timeout(function(){
		    				$scope.reasonSubmitSuccess = 0;
		    				$('#statusReasonModal').modal('hide'); 				
		    			}, 2000);
		    		}).error(function(data, status, headers, config) {
		    			$scope.getStatusReason = "Response Fail";
		    		});					
				} else {
					$scope.spinReason = 0;
	    			$scope.reasonSubmitError = 1;
	    			$scope.reasonErrorMsg = "Something wrong! Data not added!"	    			
				}
			}).error(function(data, status, headers, config) {
				$scope.addstatusreason = "Response Fail";
				$scope.spinReason = 0;
    			$scope.reasonSubmitError = 1;
    			$scope.reasonErrorMsg = "Something wrong! Data not added!"
			});
		}	
	}
	
	$scope.getEnquiryFollowupsByDate = function(){
		$scope.fromdate = document.getElementById("fromdate").value;
		$scope.todate = document.getElementById("todate").value;
		var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.followupEnquiriesByDate = data;
		}).error(function(data, status, headers, config) {
			$scope.followupEnquiriesByDate = "Response Fail";
		});
	}
	
	$scope.updateEnquiryStatus = function(enquiryid){		
		
		if(!$scope.statusreason){			
			$scope.statusreason = 0;
		}
		if(!$scope.enquirystatus){			
			$scope.errorEnquiryStatus = 1;
			$scope.msgEnquiryStatus = "Please select status";
			document.getElementById("enquirystatus").focus();
		} else {
			$scope.spinStatus = 1;
			
			if(document.getElementById("statusclose").checked == true && $scope.enquirystatus == 'w')
				$scope.enquirystatus = 'cw';
			if(document.getElementById("statusclose").checked == true && $scope.enquirystatus == 'l')
				$scope.enquirystatus = 'cl';		
			
			var link = baseUrl+'changeEnquiryStatus?enquiryid='+enquiryid+'&enquirystatus='+$scope.enquirystatus+'&statusreason='+$scope.statusreason;			
			$http.post(link).success(function(data, status, headers, config) {
				$scope.updateenquirystatus = data;												
				if($scope.updateenquirystatus == "Success") {    			
					var link = baseUrl+'getAllOpenEnquiries';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getEnquiries = data;	    			
		    			$scope.spinStatus = 0;
		    			$scope.statusSubmitSuccess = 1;
		    			$scope.statusSuccessMsg = "Status updated"
		    			$timeout(function(){
		    				$scope.enquirystatus = "";
		    				$scope.statusreason = "";
		    				$scope.statusSubmitSuccess = 0;
		    				$('#statusModal').modal('hide'); 				
		    			}, 2000);
		    		}).error(function(data, status, headers, config) {
		    			$scope.getEnquiries = "Response Fail";
		    		});					
				} else {
					$scope.spinStatus = 0;
	    			$scope.statusSubmitError = 1;
	    			$scope.statusErrorMsg = "Something wrong! Status not updated!"	    			
				}
			}).error(function(data, status, headers, config) {
				$scope.updateenquirystatus = "Response Fail";
				$scope.spinStatus = 0;
    			$scope.statusSubmitError = 1;
    			$scope.statusErrorMsg = "Something wrong! Status not updated!"
			});
		}	
	}
	
	$scope.markCompleteFollowup = function(followupid, status) {
		if(status == "y") {
			status = "c";
		} else if(status == "c") {
			status = "y";
		}
		
		var link = baseUrl+'markCompleteFollowup?followupid='+followupid+'&status='+status;		
		$http.post(link).success(function(data, status, headers, config) {
			$scope.markfollowuptocomplete = data;
			if($scope.markfollowuptocomplete == "Success"){
				var link = baseUrl+'getTodayFollowupEnquiries';
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = data;
    				
    				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
    				$http.get(link).success(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = data;
    				}).error(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = "Response Fail";
    				});
    				
    			}).error(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = "Response Fail";
    			});	
			} else {
				$scope.errorFollowupStatus = 1;
				$scope.msgFollowupStatus = $scope.markfollowuptocomplete;
				$timeout(function() {
					$scope.errorFollowupStatus = 0;
				}, 2000);
			}
		}).error(function(data, status, headers, config) {
			$scope.errorFollowupStatus = 1;
			$scope.msgFollowupStatus = "Something wrong!";
			$timeout(function() {
				$scope.errorFollowupStatus = 0;
			}, 2000);
		});
	}
	
	$scope.deleteFollowup = function(followupid) {		
		var link = baseUrl+'deleteFollowup?followupid='+followupid;		
		$http['delete'](link).success(function(data, status, headers, config) {
			$scope.deletefollowup = data;
			
				var link = baseUrl+'getTodayFollowupEnquiries';
    			$http.get(link).success(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = data;
    				
    				var link = baseUrl+'getFollowupEnquiriesByDate?fromdate='+$scope.fromdate+'&todate='+$scope.todate;	
    				$http.get(link).success(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = data;
    				}).error(function(data, status, headers, config) {
    					$scope.followupEnquiriesByDate = "Response Fail";
    				});
    				
    			}).error(function(data, status, headers, config) {
    				$scope.todayFollowupEnquiries = "Response Fail";
    			});	
			
		}).error(function(data, status, headers, config) {
			$scope.errorFollowupStatus = 1;
			$scope.msgFollowupStatus = "Something wrong!";
			$timeout(function() {
				$scope.errorFollowupStatus = 0;
			}, 2000);
		});
	}
	
	$scope.getSubRealestateByRealestateId = function(){
		var link = baseUrl+'getRealestateName?realestateid='+realestateid;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getRealestateName = data;
		}).error(function(data, status, headers, config) {
			$scope.getRealestateName = "Response Fail";
		});
	}		
	
	$scope.onChangePropertyPrice = function() {			
		if($scope.realtypeidadd == null || $scope.realtypeidadd == undefined) {				
			$scope.propertypricelist = "";				
		} else {
			var link = baseUrl+'getPriceInfoById?projectid='+$scope.projectidadd+'&categoryid='+$scope.realeidadd+'&subcategoryid='+$scope.realsubidadd+'&realestateid='+$scope.realtypeidadd;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertypricelist = data;
			}).error(function(data, status, headers, config) {
				$scope.propertypricelist = "Response Fail";
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
	
	$scope.addUser = function() {						
		$scope.dateofbirth = document.getElementById("dateofbirth").value;
		$scope.aadharnumber = document.getElementById("aadharnumberadd").value;	
		
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
		if(!$scope.passportnumberadd)	{
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
			var link = baseUrl+'addUser?companyname='+$scope.companynameadd+'&firstname='+$scope.firstnameadd+'&middlename='+$scope.middlenameadd+'&lastname='+$scope.lastnameadd+'&usertypename='+$scope.usertypenameadd+'&gender='+$scope.genderadd+'&dateofbirth='+$scope.dateofbirth+'&aadharnumber='+$scope.aadharnumber+'&passportnumber='+$scope.passportnumberadd+'&pannumber='+$scope.pannumberadd+'&address1='+$scope.address1add+'&address2='+$scope.address2add+'&address3='+$scope.address3add+'&countryname='+$scope.countrynameadd+'&statename='+$scope.statenameadd+'&cityname='+$scope.citynameadd+'&pincode='+$scope.pincodeadd+'&mobilenumber='+$scope.mobilenumberadd+'&landlinenumber='+$scope.landlinenumberadd+'&email='+$scope.emailadd+'&password='+$scope.passwordadd;				
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
					
					var link = baseUrl+'getReferenceTitleByUserTypeId?usertypeid='+$scope.usertypeidadd;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getReferenceNames = data;		
					}).error(function(data, status, headers, config) {
						$scope.getReferenceNames = "Response Fail";
					});	
					
					var link = baseUrl+'getClientAndProspectTitle';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getClientNames = data;						
					}).error(function(data, status, headers, config) {
						$scope.getClientNames = "Response Fail";
					});
					
					$scope.userSubmitError = 0;
					$scope.userSubmitSuccess = 1;
					$scope.successMsg = "Data added";
					$timeout(function(){
						$scope.userSubmitSuccess = 0;
						$('#userModal').modal('hide');
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
	
	$scope.importEnquiry = function() {
		if(document.getElementById("enquiryfile").value == "" || document.getElementById("enquiryfile").value == undefined){			
			$scope.errorFile = 1;
			$scope.msgFile = "Please choose file!";
		} else {
			$scope.spin = 1;				
			var link = baseUrl+'importEnquiry';				
			var formData=new FormData();
			formData.append("enquiryfile",enquiryfile.files[0]);				
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status) {
				$scope.addimportenquiry = data;						
				if($scope.addimportenquiry != 0){
    				$scope.spin = 0;
    				$scope.submitSuccess = 1;
    				$scope.msgSuccess = "Total "+$scope.addimportenquiry+" inquiries imported from file";
    				$timeout(function(){
    					$('#importModal').modal('hide'); 				
    				}, 3000);
    			} else {
    				$scope.spin = 0;    				
    				$scope.submitError = 1;
    				$scope.msgError = "Data not inserted! Something went wrong!";
    				$timeout(function(){
    					$scope.submitError = 0;				
    				}, 3000);
    			}
			}).error(function(data, status, headers, config) {
				$scope.addimportenquiry = data;
				$scope.spin = 0;
				$scope.submitError = 1;
				$scope.msgError = $scope.addimportenquiry;
				$timeout(function(){
					$scope.submitError = 0;
				}, 5000);
			});
		}
	}
	
	
	$scope.exportData = function() {
		var blob = new Blob([document.getElementById('exportable').innerHTML], {
			type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		});
		saveAs(blob, "Enquiry List.xls");
	}
	
	
	//User Editing
	
	$scope.getUser = function(userid) {
		
		var link = baseUrl+'getUserDetailById?userid='+userid;			
		$http.get(link).success(function(data, status, headers, config) {
			$scope.userDetailById = data;
			
			$scope.userid = $scope.userDetailById.userId;
        	$scope.locationname = $scope.userDetailById.locationId;
        	$scope.companyname = $scope.userDetailById.userCompanyName;
        	$scope.firstname = $scope.userDetailById.firstName;
        	$scope.middlename = $scope.userDetailById.middleName;
        	$scope.lastname = $scope.userDetailById.lastName;
        	$scope.usertypename = $scope.userDetailById.userTypeId;                	
        	$scope.gender = $scope.userDetailById.gender;                	
        	$scope.dateofbirth = $scope.userDetailById.dateOfBirth;            	                	
        	$scope.aadharnumber = $scope.userDetailById.aadharNumber;
        	$scope.passportnumber = $scope.userDetailById.passportNumber;
        	$scope.pannumber = $scope.userDetailById.panNumber;
        	$scope.profilepicture1 = $scope.userDetailById.profilePicture;
        	$scope.address1 = $scope.userDetailById.address1;
        	$scope.address2 = $scope.userDetailById.address2;
        	$scope.address3 = $scope.userDetailById.address3;
        	$scope.countryname = $scope.userDetailById.countryId;
        	$scope.statename = $scope.userDetailById.stateId;
        	$scope.cityname = $scope.userDetailById.cityName;
        	$scope.pincode = $scope.userDetailById.pincode;
        	$scope.mobilenumber = $scope.userDetailById.mobileNumber;
        	$scope.landlinenumber = $scope.userDetailById.landlineNumber;
        	$scope.email = $scope.userDetailById.email;
        	$scope.email1 = $scope.userDetailById.email;
        	$scope.password = $scope.userDetailById.password;
        	$scope.gstnoedit = $scope.userDetailById.gstNumber;
        	
        	if($scope.userDetailById.accessProject == "y"){
        		
        		$scope.accessprojectedit= true;
           	} else {
        		$scope.accessprojectedit= false;
        	}            	

        	if($scope.userDetailById.accessProperty == "y"){
        		
        		$scope.accesspropertyedit= true;
           	} else {
        		$scope.accesspropertyedit= false;
        	}            	

        	if($scope.userDetailById.accessEnquiry == "y"){
        		
        		$scope.accessenquiryedit= true;
           	} else {
        		$scope.accessenquiryedit= false;
        	}            	
     

        	if($scope.userDetailById.accessPayment == "y"){
        		
        		$scope.accesspaymentedit= true;
           	} else {
        		$scope.accesspaymentedit= false;
        	}
        	
        	if($scope.userDetailById.accessUser == "y"){
        		
        		$scope.accessuseredit= true;
           	} else {
        		$scope.accessuseredit= false;
        	}
        	
		}).error(function(data, status, headers, config) {
			$scope.userDetailById = "Response Fail";
		});		
	}
	
	$scope.getStateByCountryId = function(countryname) {
		if(countryname == "" || countryname == undefined) {
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
	
	
	$scope.editUser = function(userid) {					
		var profilepicture1 = $scope.profilepicture1;		
		
		$scope.dateofbirth = document.getElementById("datepicker1").value;			
		$scope.aadharnumber = document.getElementById("aadharnumber").value;	
		
		if(!$scope.companyname)	{
			$scope.companyname = "";
		}
		if(!$scope.middlename)	{
			$scope.middlename = "";
		}
		if(!$scope.lastname)	{
			$scope.lastname = "";
		}
		if(!$scope.gender)	{
			$scope.gender = "";
		}			
		if(!$scope.dateofbirth || $scope.dateofbirth=="day/month/year")	{
			$scope.dateofbirth = "";
		}
		if(!$scope.aadharnumber) {
			$scope.aadharnumber = "";
		}
		if(!$scope.passportnumber)	{
			$scope.passportnumber = "";
		}
		if(!$scope.pannumber) {
			$scope.pannumber = "";
		}
		if(!$scope.address1) {
			$scope.address1 = "";
		}
		if(!$scope.address2) {
			$scope.address2 = "";
		}
		if($scope.address3) {
			$scope.address3 = "";
		}
		if(!$scope.countryname) {
			$scope.countryname = 0;
		}
		if(!$scope.statename) {
			$scope.statename = 0;
		}
		if(!$scope.cityname) {
			$scope.cityname = "";
		}
		if(!$scope.pincode) {
			$scope.pincode = "";
		}
		if(!$scope.mobilenumber) {
			$scope.mobilenumber = "";
		}
		if(!$scope.landlinenumber) {
			$scope.landlinenumber = "";
		}
		if(!$scope.email) {
			$scope.email = "";
		}
		if(!$scope.password) {
			$scope.password = "";
		}
		if(!$scope.gstnoedit) {
			$scope.gstnoedit="";
		}
		
		if(!$scope.accessprojectedit) {
			$scope.accessprojectedit="n";
		} else {
			$scope.accessprojectedit="y";
		}
		
		if(!$scope.accesspropertyedit) {
			$scope.accesspropertyedit="n";
		} else {
			$scope.accesspropertyedit="y";
		}
		
		if(!$scope.accessenquiryedit) {
			$scope.accessenquiryedit="n";
		} else {
			$scope.accessenquiryedit="y";
		}
		
		if(!$scope.accesspaymentedit) {
			$scope.accesspaymentedit="n";
		} else {
			$scope.accesspaymentedit="y";
		}
		
		if(!$scope.accessuseredit) {
			$scope.accessuseredit="n";
		} else {
			$scope.accessuseredit="y";
		}
		
		if(!$scope.firstname) {				
			$scope.errorFirstName = 1;
			$scope.msgFirstName = "Please enter first name";
			document.getElementById("firstname").focus();
		} else if(!$scope.usertypename) {				
			$scope.errorUserType = 1;
			$scope.msgUserType = "Please select user type";
			document.getElementById("usertypename").focus();
		} else {			
			$scope.spin = 1;					
			var link = baseUrl+'editUser?userid='+userid+'&companyname='+$scope.companyname+'&firstname='+$scope.firstname+'&middlename='+$scope.middlename+'&lastname='+$scope.lastname+'&usertypename='+$scope.usertypename+'&gender='+$scope.gender+'&dateofbirth='+$scope.dateofbirth+'&aadharnumber='+$scope.aadharnumber+'&passportnumber='+$scope.passportnumber+'&pannumber='+$scope.pannumber+'&address1='+$scope.address1+'&address2='+$scope.address2+'&address3='+$scope.address3+'&countryname='+$scope.countryname+'&statename='+$scope.statename+'&cityname='+$scope.cityname+'&pincode='+$scope.pincode+'&mobilenumber='+$scope.mobilenumber+'&landlinenumber='+$scope.landlinenumber+'&email='+$scope.email+'&password='+$scope.password+'&profilepicture1='+profilepicture1+'&accessproject='+$scope.accessprojectedit+'&accessproperty='+$scope.accesspropertyedit+'&accessenquiry='+$scope.accessenquiryedit+'&accesspayment='+$scope.accesspaymentedit+'&accessuser='+$scope.accessuseredit+'&gstno='+$scope.gstnoedit;				
			var formData=new FormData();
			formData.append("profilepicture",profilepicture.files[0]);					
			$http({method: 'POST',
				url: link,
		        headers: {'Content-Type': undefined},
		        data: formData,
		        transformRequest: function(data, headersGetterFunction) {
		        	return data;
		        }
		    }).success(function(data, status) {
		    	$scope.edituser = data;
		    	$scope.spin = 0;
		    	if($scope.edituser == 'Success'){			    		
		    		var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getUsers = data;
						
						$scope.userSubmitError = 0;
						$scope.userSubmitSuccess = 1;
						$scope.successMsg = "Data updated";
						$timeout(function(){
							$scope.userSubmitSuccess = 0;
							$scope.getUser(userid);
							/*$('#editModal').modal('hide');*/
						}, 2000);
						
					}).error(function(data, status, headers, config) {
						$scope.getUsers = "Response Fail";
					});
					
				} else {
					$scope.userSubmitSuccess = 0;
					$scope.userSubmitError = 1;
					$scope.errorMsg = $scope.edituser;
				}
			}).error(function(data, status, headers, config) {
				$scope.edituser = data;
				$scope.spin = 0;
				$scope.userSubmitSuccess = 0;
				$scope.userSubmitError = 1;
				$scope.errorMsg = "Something wrong! Please try again later!";
			});
		}
	}
	
	//15Feb2020
	
	$scope.changeProjectName = function(projectname){
		if(projectname=="null") {
			$scope.project = undefined;
		} else {
			$scope.project = projectname;
		}
	}
	/*$scope.getAllEnquiryListByName = function(projectname){
		
		if(!projectname){
			$window.alert("Hloo");
			$scope.pageSize = 'All';
		}
		
		
	}*/
	
	
	
	
}]);