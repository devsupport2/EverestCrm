var app = angular.module("MyApp", ['angular.filter']);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('propertyCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' , function ($scope, $filter, $http, $window, $location, $timeout) {
	
	var baseUrl = $location.protocol()+"://"+location.host+url;
	
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
		$scope.units = range;
		$scope.floors = range;		

		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProperty = data;
		}).error(function(data, status, headers, config) {
			$scope.getProperty = "Response Fail";
		});	
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getProperty = data;
			}).error(function(data, status, headers, config) {
				$scope.getProperty = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getProperty';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
			} else {
				var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
			}
		}
		
		$scope.searchProperty = function() {
			var search = $scope.search;
			if(search == undefined || search == "") {
				var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchProperties?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
			}
		}
		
		var link = baseUrl+'getSubcategory';		
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getSubcategory = data;
		}).error(function(data, status, headers, config) {
			$scope.getSubcategory = "Response Fail";
		});

		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getProjectName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProjectName = data;
		}).error(function(data, status, headers, config) {
			$scope.getProjectName = "Response Fail";
		});
		
		var link = baseUrl+'getPriceLabel';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getPriceLabel = data;
		}).error(function(data, status, headers, config) {
			$scope.getPriceLabel = "Response Fail";
		});
		
		var link = baseUrl+'getTowerName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getTowerName = data;
		}).error(function(data, status, headers, config) {
			$scope.getTowerName = "Response Fail";
		});
		
		var link = baseUrl+'getAreaName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getAreaName = data;
		}).error(function(data, status, headers, config) {
			$scope.getAreaName = "Response Fail";
		});
		
		var link = baseUrl+'getRealestateName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getRealestateName = data;
		}).error(function(data, status, headers, config) {
			$scope.getRealestateName = "Response Fail";
		});
		
		var link = baseUrl+'getRoomName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getRoomName = data;
		}).error(function(data, status, headers, config) {
			$scope.getRoomName = "Response Fail";
		});
		
		var link = baseUrl+'getMeasurementUnits';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getMeasurementUnits = data;
		}).error(function(data, status, headers, config) {
			$scope.getMeasurementUnits = "Response Fail";
		});

		var link = baseUrl+'getUnitNameList';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.unitNameList = data;			
		}).error(function(data, status, headers, config) {
			$scope.unitNameList = "Response Fail";
		});
		
		$scope.getPropertyByProjectId = function(projectid){
			if(!projectid){
				var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
				
			} else {
				var link = baseUrl+'getAllPropertyByProjectId?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
				
				var link = baseUrl+'getProjectUnitByProjectId?projectid='+projectid;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectUnitNameList = data;				
				}).error(function(data, status, headers, config) {
					$scope.projectUnitNameList = "Response Fail";				
				});
			}
		}
		
		$scope.getPropertyById = function(projectid, projectunitid){
			var link = baseUrl+'getAllPropertyByProjectIdAndUnitID?projectid='+projectid+'&projectunitid='+projectunitid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getProperty = data;
			}).error(function(data, status, headers, config) {
				$scope.getProperty = "Response Fail";
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
		
		$scope.removeAreaDetails = function(propertyAreaId, propertyid) {			
			
			var link = baseUrl+'deleteAreaDetail?propertyareaid='+propertyAreaId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletearea = data;
			}).error(function(data, status, headers, config) {
				$scope.deletearea = "Response Fail";
			});
			
			var link = baseUrl+'getPropertyAreaById?propertyid='+propertyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertyArea = data;
			}).error(function(data, status, headers, config) {
				$scope.propertyArea = "Response Fail";
			});
		}
		
		$scope.createCloneProperty = function(propertyid) {
			var link = baseUrl+'createCloneById?propertyid='+propertyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertyClone = data;
				
				if(!$scope.projectid){
					var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getProperty = data;
						var link = baseUrl+'getAllCounts';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.allcounts = data;
						}).error(function(data, status, headers, config) {
							$scope.allcounts = "Response Fail";
						});
					}).error(function(data, status, headers, config) {
						$scope.getProperty = "Response Fail";
					});
					
				} else{
				
					var link = baseUrl+'getAllPropertyByProjectId?projectid='+$scope.projectid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getProperty = data;
						
						var link = baseUrl+'getAllCounts';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.allcounts = data;
						}).error(function(data, status, headers, config) {
							$scope.allcounts = "Response Fail";
						});
						
					}).error(function(data, status, headers, config) {
						$scope.getProperty = "Response Fail";
					});
				}
				
			}).error(function(data, status, headers, config) {
				$scope.propertyClone = "Response Fail";
			});
		}
		
		$scope.removeTowerName = function(towerNameId) {
			var link = baseUrl+'deleteTowerName?towernameid='+towerNameId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletetower = data;
			}).error(function(data, status, headers, config) {
				$scope.deletetower = "Response Fail";
			});			
			var link = baseUrl+'getTowerName';
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getTowerName = data;
			}).error(function(data, status, headers, config) {
				$scope.getTowerName = "Response Fail";
			});
		}
		
		$scope.removeRoomDetails = function(propertyRoomId, propertyid) {		
			var link = baseUrl+'deleteRoomDetail?propertyroomid='+propertyRoomId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteroom = data;
			}).error(function(data, status, headers, config) {
				$scope.deleteroom = "Response Fail";
			});
			
			var link = baseUrl+'getPropertyRoomById?propertyid='+propertyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertyRoom = data;
			}).error(function(data, status, headers, config) {
				$scope.propertyRoom = "Response Fail";
			});
			
		}
		
		$scope.removePriceDetails = function(paymentScheduleId, propertyid) {		
			var link = baseUrl+'deletePriceDetail?paymentscheduleid='+paymentScheduleId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteprice = data;
			}).error(function(data, status, headers, config) {
				$scope.deleteprice = "Response Fail";
			});
			
			var link = baseUrl+'getPropertyPriceById?propertyid='+propertyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertyPrice = data;
			}).error(function(data, status, headers, config) {
				$scope.propertyPrice = "Response Fail";
			});
			
		}
		
		$scope.removePropertyPriceDetails = function(propertyPriceInfoId, propertyid) {
			
			var link = baseUrl+'deletePropertyPriceDetail?propertypriceinfoid='+propertyPriceInfoId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletepriceinfo = data;
			}).error(function(data, status, headers, config) {
				$scope.deletepriceinfo = "Response Fail";
			});
			
			var link = baseUrl+'getPropertyPriceInfoById?propertyid='+propertyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertyPriceInfo = data;
			}).error(function(data, status, headers, config) {
				$scope.propertyPriceInfo = "Response Fail";
			});
		}
		
		$scope.onChangeProject = function(projectid) {					
			if(!projectid) {				
				$scope.paymentschedulelist = "";				
			} else {				
				var link = baseUrl+'getProjectDetailsById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectRealestateCategory = data;
				}).error(function(data, status, headers, config) {
					$scope.projectRealestateCategory = "Response Fail";
				});
				
				var link = baseUrl+'getPropertyPaymentScheduleById?projectid='+projectid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.paymentschedulelist = data;
				}).error(function(data, status, headers, config) {
					$scope.paymentschedulelist = "Response Fail";
				});
			}
		}
		
		$scope.onChangeCategory = function(projectid, categoryid) {
			var link = baseUrl+'getProjectSubcategoryById?projectid='+projectid+'&categoryid='+categoryid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectSubcategory = data;
			}).error(function(data, status, headers, config) {
				$scope.projectSubcategory = "Response Fail";
			});
		}
		
		$scope.onChangeSubcategory = function(projectid, categoryid, subcategoryid) {			
			var link = baseUrl+'getProjectRealestateTypeById?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectRealestateType = data;
			}).error(function(data, status, headers, config) {
				$scope.projectRealestateType = "Response Fail";
			});
		}
		
		$scope.onChangeType = function(projectid, categoryid, subcategoryid, estatetypeid) {			
			var link = baseUrl+'getProjectUnitById?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid+'&typeid='+estatetypeid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectUnitNameList = data;				
			}).error(function(data, status, headers, config) {
				$scope.projectUnitNameList = "Response Fail";				
			});			
			
			var link = baseUrl+'getProjectAreaForPropertyById?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid+'&typeid='+estatetypeid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectArea = data;				
			}).error(function(data, status, headers, config) {
				$scope.projectArea = "Response Fail";				
			});
			
			var link = baseUrl+'getProjectUnitPriceForPropertyById?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid+'&typeid='+estatetypeid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectUnitPrice = data;				
			}).error(function(data, status, headers, config) {
				$scope.projectUnitPrice = "Response Fail";				
			});
			
			var link = baseUrl+'getProjectPaymentScheduleById?projectid='+projectid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectPaymentSchedule = data;
			}).error(function(data, status, headers, config) {
				$scope.projectPaymentSchedule = "Response Fail";
			});
		}
		
		$scope.onChangeUnitName = function(projectid, categoryid, subcategoryid, estatetypeid, unitmasterid) {		
			
			var link = baseUrl+'getProjectAreaForPropertyByIdAndUnitId?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid+'&typeid='+estatetypeid+'&unitmasterid='+unitmasterid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectArea = data;				
			}).error(function(data, status, headers, config) {
				$scope.projectArea = "Response Fail";				
			});
			
			var link = baseUrl+'getProjectUnitPriceForPropertyByIdAndUnitId?projectid='+projectid+'&categoryid='+categoryid+'&subcategoryid='+subcategoryid+'&typeid='+estatetypeid+'&unitmasterid='+unitmasterid;			
			$http.get(link).success(function(data, status, headers, config) {
				$scope.projectUnitPrice = data;				
			}).error(function(data, status, headers, config) {
				$scope.projectUnitPrice = "Response Fail";				
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
				$scope.projectPaymentSchedule.push({'sequence' : $scope.sequenceadd, 'sequenceTitle' : $scope.sequencetitleadd, 'paymentLable' : $scope.lableadd, 'paymentValue' : $scope.valueadd, 'unitId' : $scope.unittypeadd, 'unitName' : $scope.unit});
			}
		}
		
		$scope.removePaymentScheduleRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectPaymentSchedule.length; i++) {
				if($scope.projectPaymentSchedule[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectPaymentSchedule.splice(index, 1);
		};		
		
		$scope.onChangeRealestateType = function() {						
			if($scope.realsubidadd  == null || $scope.realsubidadd==undefined) {				
				$scope.getRealestateTitles = "";				
			}
			else{			
				var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.realsubidadd;				
				
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
			}
			else{			
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
			}
			else{			
				var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.subcategoryid;				
				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRealestateTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getRealestateTitles = "Response Fail";
				});		
				
			}
		}		
		
		$scope.editPropertyPaymentSchedule = function(propertyid) {			
			
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
				var link = baseUrl+'editPropertyPaymentsSchedule?propertyid='+propertyid+'&sequence='+$scope.sequenceedit+'&sequencetitle='+$scope.sequencetitleedit+'&lable='+$scope.lableedit+'&value='+$scope.valueedit+'&measurementunitid='+$scope.unittypeedit;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editpropertypaymentschedule = data;
					var link = baseUrl+'getPropertyPaymentsScheduleById?propertyid='+propertyid;
					$http.get(link).success(function(data, status, headers, config) {
						$scope.propertyPaymentSchedule = data;
					}).error(function(data, status, headers, config) {
						$scope.propertyPaymentSchedule = "Response Fail";
					});
					$scope.projectspin = 0;										
				}).error(function(data, status, headers, config) {
					$scope.editpropertypaymentschedule = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editprojectpriceinfo;
				});   			
			}
		}
				
		$scope.removePropertyPaymentSchedule = function(propertyPaymentScheduleId, propertyid) {			
			var link = baseUrl+'deletePropertyPaymentSchedule?id='+propertyPaymentScheduleId;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletepaymentschedule = data;
				var link = baseUrl+'getPropertyPaymentsScheduleById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyPaymentSchedule = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyPaymentSchedule = "Response Fail";
				});
			}).error(function(data, status, headers, config) {
				$scope.deletepaymentschedule = "Response Fail";
			});
		}
		
		$scope.addRealestateType = function() {
						
			if($scope.realestatecodeadd==undefined) {
				$scope.realestatecodeadd = "";
			}
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}		
			
			if($scope.realestatetitleadd==undefined || $scope.realestatetitleadd=="") {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please enter realestate title";
				document.getElementById("realestatetitleadd").focus();
			} else {
				$scope.spin = 1;				
												
				var description = $scope.descriptionadd.replace("&","$");
				var description1 = description.replace("#","~");
				var realestatedescription = description1.replace("%","!");
				
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "&" , "$" );
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "#" , "~" );
				$scope.realestatetitleadd = tools_replaceAll($scope.realestatetitleadd, "%" , "!" );
				
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.derealestatetitlescriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
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
		
		$scope.addRealestateSubcategory = function() {
			
			if($scope.realestatecodeadd==undefined) {
				$scope.realestatecodeadd = "";
			}
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}		
			
			if($scope.realeidadd==undefined || $scope.realeidadd=="") {			
				$scope.errorRealSubcategoryTitle = 1;
				$scope.msgSubcategoryType = "Please select realestate type!";
				document.getElementById("realestatetypeidadd").focus();
			} else if($scope.realestatesubtitleadd==undefined || $scope.realestatesubtitleadd=="") {			
				$scope.errorRealSubTitle = 1;
				$scope.msgSubcategoryTitle = "Please enter title";
				document.getElementById("realestatesubtitleadd").focus();
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
	    				$scope.msgError = "Data not inserted! Duplicate entry for category code!";
	    				$timeout(function(){
	    					$scope.submitError = 0;				
	    				}, 3000);
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

		$scope.addRealestate = function() {
			
			if($scope.realestatecodeadd==undefined) {
				$scope.realestatecodeadd = "";
			}
			if($scope.descriptionadd==undefined) {
				$scope.descriptionadd = "";
			}		
			if($scope.realeidadd==undefined || $scope.realeidadd=="") {			
				$scope.errorRealCategoryTitle = 1;
				$scope.msgCategoryType = "Please select realestate category!";
				document.getElementById("realeidadd").focus();
			} else if($scope.realsubidadd==undefined || $scope.realsubidadd=="") {			
				$scope.errorRealSubcategoryTitle = 1;
				$scope.msgSubcategoryType = "Please select realestate type!";
				document.getElementById("realsubidadd").focus();
			} else if($scope.realestatetitleadd==undefined || $scope.realestatetitleadd=="") {			
				$scope.errorRealSubTitle = 1;
				$scope.msgSubcategoryTitle = "Please enter title";
				document.getElementById("realestatetitleadd").focus();
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
	    					document.getElementById("realestatetype").reset();
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
					$scope.addrealestate = data;				
					$scope.spin = 0;
					$scope.submitError = 1;
					$scope.msgError = $scope.addcategory;
					$timeout(function(){
						$('#realestateTypeModal').modal('hide'); 
						document.getElementById("realestatetype").reset();
					}, 5000);					
				});    			
			}
		}
		
		$scope.addProperty = function() {
			
			if(!$scope.projectunitidadd) {
				$scope.projectunitidadd = 0;
			}
			if(!$scope.propertytypeidadd) {
				$scope.propertytypeidadd = 0;
			}
			if(!$scope.floor) {
				$scope.floor = "";
			}			
			if(!$scope.watersourceadd) {
				$scope.watersourceadd = "";
			}
			if(!$scope.drainageadd) {
				$scope.drainageadd = "";
			}
			if(!$scope.furnishingadd) {
				$scope.furnishingadd = "";
			}
			if(!$scope.parkingadd) {
				$scope.parkingadd = "";
			}
			if(!$scope.availabilityadd) {
				$scope.availabilityadd = "";
			}			
			if(!$scope.descriptionadd) {
				$scope.descriptionadd = "";
			}
			if(!$scope.chargesadd) {
				$scope.chargesadd = "";
			}
			if(!$scope.maintenanceamount) {
				$scope.maintenanceamount = 0;
			}
			if(!$scope.pdescriptionadd) {
				$scope.pdescriptionadd = "";
			}
			
			if($scope.projectidadd==undefined || $scope.projectidadd=="") {			
				$scope.errorProjectTitle = 1;
				$scope.msgProjectType = "Please select project type!";
				document.getElementById("projectidadd").focus();
			} else if($scope.categoryidadd==undefined || $scope.categoryidadd=="") {			
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryType = "Please select realestate category!";
				document.getElementById("categoryidadd").focus();
			} else if($scope.subcategoryidadd==undefined || $scope.subcategoryidadd=="") {			
				$scope.errorRealestateSubcategoryTitle = 1;
				$scope.msgRealestateSubType = "Please select realestate subcategory!";
				document.getElementById("subcategoryidadd").focus();
			} else if($scope.projecttypeidadd==undefined || $scope.projecttypeidadd=="") {			
				$scope.errorRealestateTypeTitle = 1;
				$scope.msgRealestateTypeTitle = "Please select realestate type!";
				document.getElementById("projecttypeidadd").focus();
			} else if($scope.propertytitle==undefined || $scope.propertytitle=="") {			
				$scope.errorUnits = 1;
				$scope.msgUnits = "Please enter property number!";
				document.getElementById("propertytitle").focus();
			} else {				
				var a = 0, b = 0, c = 0, d = 0, e = 0;
				$scope.spin = 1;
				
								
				$scope.layouttitlelist = [];
				angular.forEach($scope.propertyLayout,function(item) {
					$scope.layouttitlelist.push(item.layoutTitle);
					b = b + 1;
				});
				
				var link = baseUrl+'addProperty?projectid='+$scope.projectidadd+'&realecateid='+$scope.categoryidadd+'&realestatesubid='+$scope.subcategoryidadd+'&realestatetypeid='+$scope.projecttypeidadd+'&propertytypeoid='+$scope.propertytypeidadd+'&propertytitle='+$scope.propertytitle+'&propertyunitmasterid='+$scope.projectunitidadd+'&floor='+$scope.floor+'&watersource='+$scope.watersourceadd+'&drainage='+$scope.drainageadd+'&furnishing='+$scope.furnishingadd+'&parking='+$scope.parkingadd+'&availability='+$scope.availabilityadd+'&description='+$scope.descriptionadd+'&charges='+$scope.chargesadd+'&maintenanceamount='+$scope.maintenanceamount+'&pdescription='+$scope.pdescriptionadd;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addproperty = data;
					
					if($scope.addproperty == "Success"){
						angular.forEach($scope.projectArea,function(item) {
							var link = baseUrl+'addAreaDetails?areatitleid='+item.areaId+'&value='+item.areaValue+'&unit='+item.unitId;
							
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addareadetails = data;
								a = a + 1;
								
								if($scope.propertyLayout.length == b && $scope.projectArea.length == a && $scope.roomlist.length == c && $scope.projectUnitPrice.length == d && $scope.projectPaymentSchedule.length == e ) {
									$scope.spin = 0;
									$scope.propertySubmitError = 0;
									$scope.propertySubmitSuccess = 1;
									$scope.successMsg = "Data Submited Successfully";
									$timeout(function(){
										$scope.propertySubmitSuccess = 0;
										$window.location.href = adminurl+'manage_property';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addareadetails = "Response Fail";
							});
						});						
						
						if ($scope.propertyLayout.length > 0) {
							var link = baseUrl+ 'addPropertyLayout?layouttitle='+$scope.layouttitlelist;
							$http({method : 'POST', url : link, headers : {'Content-Type' : undefined}, data : LayoutImage, transformRequest : function(data,headersGetterFunction) { return data; }}).success(function(data,status) {
								$scope.addpropertylayout = data;																		
								if($scope.propertyLayout.length == b && $scope.projectArea.length == a && $scope.roomlist.length == c && $scope.projectUnitPrice.length == d && $scope.projectPaymentSchedule.length == e ) {
									$scope.spin = 0;
									$scope.propertySubmitError = 0;
									$scope.propertySubmitSuccess = 1;
									$scope.successMsg = "Data Submited Successfully";
									$timeout(function(){
										$scope.propertySubmitSuccess = 0;
										$window.location.href = adminurl+'manage_property';
									}, 2000);
								}
							}).error(function(data,status,headers,config) {
								$scope.addpropertylayout = "Response Fail";
							});
						}
						
						angular.forEach($scope.roomlist,function(item) {												
							var link = baseUrl+'addRoomDetails?roomtitleid='+item.roomTitleId+'&length='+item.length+'&lengthunit='+item.lengthUnitId+'&breadth='+item.breadth+'&breadthunit='+item.breadthUnitId+'&height='+item.height+'&heightunit='+item.heightUnitId;
							
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addroomdetails = data;
								c = c + 1;
								if($scope.propertyLayout.length == b && $scope.projectArea.length == a && $scope.roomlist.length == c && $scope.projectUnitPrice.length == d && $scope.projectPaymentSchedule.length == e ) {
									$scope.spin = 0;
									$scope.propertySubmitError = 0;
									$scope.propertySubmitSuccess = 1;
									$scope.successMsg = "Data Submited Successfully";
									$timeout(function(){
										$scope.propertySubmitSuccess = 0;
										$window.location.href = adminurl+'manage_property';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addroomdetails = "Response Fail";
							});
						});
											
						angular.forEach($scope.projectUnitPrice,function(item) {						
							var link = baseUrl+'addPriceDetailInfo?pricelable='+item.priceLable+'&pricevalue='+item.priceValue+'&measurementunitid='+item.unitId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addpricedetails = data;
								d = d + 1;
								if($scope.propertyLayout.length == b && $scope.projectArea.length == a && $scope.roomlist.length == c && $scope.projectUnitPrice.length == d && $scope.projectPaymentSchedule.length == e ) {
									$scope.spin = 0;
									$scope.propertySubmitError = 0;
									$scope.propertySubmitSuccess = 1;
									$scope.successMsg = "Data Submited Successfully";
									$timeout(function(){
										$scope.propertySubmitSuccess = 0;
										$window.location.href = adminurl+'manage_property';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addpricedetails = "Response Fail";
							});
						});
						
						angular.forEach($scope.projectPaymentSchedule,function(item) {												
							var link = baseUrl+'addPropertyPaymentSchedule?sequence='+item.sequence+'&sequencetitle='+item.sequenceTitle+'&lable='+item.paymentLable+'&value='+item.paymentValue+'&measurementunitid='+item.unitId;							
							
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addprojectpaymentschedule = data;
								e = e + 1;
								if($scope.propertyLayout.length == b && $scope.projectArea.length == a && $scope.roomlist.length == c && $scope.projectUnitPrice.length == d && $scope.projectPaymentSchedule.length == e ) {
									$scope.spin = 0;
									$scope.propertySubmitError = 0;
									$scope.propertySubmitSuccess = 1;
									$scope.successMsg = "Data Submited Successfully";
									$timeout(function(){
										$scope.propertySubmitSuccess = 0;
										$window.location.href = adminurl+'manage_property';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addprojectpaymentschedule = "Response Fail";
							});
						});
						
						if($scope.propertyLayout.length == b && $scope.projectArea.length == a && $scope.roomlist.length == c && $scope.projectUnitPrice.length == d && $scope.projectPaymentSchedule.length == e ) {
							$scope.spin = 0;
							$scope.propertySubmitError = 0;
							$scope.propertySubmitSuccess = 1;
							$scope.successMsg = "Data Submited Successfully";
							$timeout(function(){
								$scope.propertySubmitSuccess = 0;
								$window.location.href = adminurl+'manage_property';
							}, 2000);
						}				
					} else {
						$scope.spin = 0;
						$scope.propertySubmitError = 1;
						$scope.propertySubmitSuccess = 0;
						$scope.successMsg = data;
						$timeout(function(){
							$scope.propertySubmitError = 0;							
						}, 2000);
					}						
				}).error(function(data, status, headers, config) {
					$scope.addproperty = data;
					$scope.propertySubmitSuccess = 0;
					$scope.propertySubmitError = 1;
					$scope.errorMsg = $scope.addproperty;
				});			
			}
		}
		
		$scope.getPropertyDetails = function(propertyid) {			
			var link = baseUrl+'getPropertyDetailById?propertyid='+propertyid;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.propertyDetail = data;
				
				$scope.propertyid = $scope.propertyDetail.propertyId;
				$scope.projectidedit = $scope.propertyDetail.projectTypeId;
				$scope.categoryid = $scope.propertyDetail.categoryId;
				$scope.subcategoryid = $scope.propertyDetail.subcategoryId;
				
				$scope.realestateid = $scope.propertyDetail.realestateId;
				$scope.propertytypeid = $scope.propertyDetail.propertyTypeId;
				$scope.propertytitleedit = $scope.propertyDetail.propertyTitle;
				$scope.projectunitid = $scope.propertyDetail.propertyUnitMasterId;
				$scope.flooredit = $scope.propertyDetail.floor;
				$scope.watersourceedit = $scope.propertyDetail.waterSource;
				$scope.drainageedit = $scope.propertyDetail.drainage;
				$scope.furnishingedit = $scope.propertyDetail.furnishing;
				$scope.reservedparkingedit = $scope.propertyDetail.reservedParking;
				$scope.propertyavailabilityedit = $scope.propertyDetail.propertyAvailability;
				$scope.propertyavailabilitydes = $scope.propertyDetail.propertyAvailabilityDescription;
				$scope.maintenancechargesedit = $scope.propertyDetail.maintenanceCharges;
				$scope.maintenanceamountedit = $scope.propertyDetail.maintenanceAmount;				
				$scope.propertydescriptionedit = $scope.propertyDetail.propertyDescription;				
				
				
				var link = baseUrl+'getAllRealestateSubcategoryTitleByRealestateId?realestateid='+$scope.categoryid;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getSubcategoryTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getSubcategoryTitles = "Response Fail";
				});	
				
				var link = baseUrl+'getAllRealestateTypeTitleByRealestateSubcategoryId?realestatesubcategoryid='+$scope.subcategoryid;				
				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getRealestateTitles = data;
				}).error(function(data, status, headers, config) {
					$scope.getRealestateTitles = "Response Fail";
				});
				var link = baseUrl+'getPropertyAreaById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyArea = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyArea = "Response Fail";
				});
				
				var link = baseUrl+'getPropertyRoomById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyRoom = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyRoom = "Response Fail";
				});
				
				var link = baseUrl+'getPropertyPriceById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyPrice = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyPrice = "Response Fail";
				});
				
				var link = baseUrl+'getPropertyLayoutById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyLayout = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyLayout = "Response Fail";
				});
				
				var link = baseUrl+'getPropertyPriceInfoById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyPriceInfo = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyPriceInfo = "Response Fail";
				});
				
				var link = baseUrl+'getPropertyPaymentsScheduleById?propertyid='+propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyPaymentSchedule = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyPaymentSchedule = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.propertyDetail = "Response Fail";
			});
		}
		
		$scope.addAreaTitle = function() {
								
			if($scope.areatitleadd==undefined || $scope.areatitleadd=="") {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please enter area title";
				document.getElementById("areatitleadd").focus();
			} else {
				$scope.spin = 1;				
											
				$scope.areatitleadd = tools_replaceAll($scope.areatitleadd, "&" , "$" );
				$scope.areatitleadd = tools_replaceAll($scope.areatitleadd, "#" , "~" );
				$scope.areatitleadd = tools_replaceAll($scope.areatitleadd, "%" , "!" );
				
				
				var link = baseUrl+'addAreaType?areatitle='+$scope.areatitleadd;
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addarea = data;
					$scope.spin = 0;					
					if($scope.addarea == 'Success'){
						var link = baseUrl+'getAreaName';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getAreaName = data;
						}).error(function(data, status, headers, config) {
							$scope.getAreaName = "Response Fail";
						});
						
						$scope.areaSubmitError = 0;
						$scope.areaSubmitSuccess = 1;
						$scope.successMsg = "Data Submited Successfully";
						$timeout(function(){
	    					$('#areaTypeModal').modal('hide');
	    					$scope.areaSubmitSuccess = 0;
	    					document.getElementById("areatitle").reset();
	    				}, 2000);
					} else {
						$scope.areaSubmitSuccess = 0;
						$scope.areaSubmitError = 1;
						$scope.errorMsg = $scope.addproperty;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addarea = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.addproperty;
				});   			
			}
		}
		
		$scope.addUnitTitle = function() {
			
			if($scope.unittitleadd==undefined || $scope.unittitleadd=="") {			
				$scope.errorUnitTitle = 1;
				$scope.msgUnitTitle = "Please enter unit title";
				document.getElementById("unittitleadd").focus();
			} else {
				$scope.unitSpin = 1;				
											
				$scope.unittitleadd = tools_replaceAll($scope.unittitleadd, "&" , "$" );
				$scope.unittitleadd = tools_replaceAll($scope.unittitleadd, "#" , "~" );
				$scope.unittitleadd = tools_replaceAll($scope.unittitleadd, "%" , "!" );
				
				
				var link = baseUrl+'addUnitType?unittitle='+$scope.unittitleadd;
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addunit = data;
					$scope.unitSpin = 0;					
					if($scope.addunit == 'Success'){
						var link = baseUrl+'getTowerName';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getTowerName = data;
						}).error(function(data, status, headers, config) {
							$scope.getTowerName = "Response Fail";
						});
						
						$scope.unitSubmitError = 0;
						$scope.unitSubmitSuccess = 1;
						$scope.successMsg = "Data Submited Successfully";
						$timeout(function(){
	    					$('#towerNameModal').modal('hide');
	    					$scope.unitSubmitSuccess = 0;
	    					document.getElementById("unittitle").reset();
	    				}, 2000);
					} else {
						$scope.unitSubmitSuccess = 0;
						$scope.unitSubmitError = 1;
						$scope.errorMsg = $scope.addunit;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addunit = data;
					$scope.unitSubmitSuccess = 0;
					$scope.unitSubmitError = 1;
					$scope.errorMsg = $scope.addunit;
				});   			
			}
		}
		
		$scope.addPriceTitle = function() {
			
			if($scope.labeladd==undefined || $scope.labeladd=="") {			
				$scope.errorCategoryTitle = 1;
				$scope.msgCategoryTitle = "Please enter price label";
				document.getElementById("labeladd").focus();
			} else {
				$scope.spin = 1;				
											
				$scope.labeladd = tools_replaceAll($scope.labeladd, "&" , "$" );
				$scope.labeladd = tools_replaceAll($scope.labeladd, "#" , "~" );
				$scope.labeladd = tools_replaceAll($scope.labeladd, "%" , "!" );			
				
				var link = baseUrl+'addPriceType?pricelabel='+$scope.labeladd;
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addarea = data;
					$scope.spin = 0;					
					if($scope.addarea == 'Success'){
						var link = baseUrl+'getAreaName';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getAreaName = data;
						}).error(function(data, status, headers, config) {
							$scope.getAreaName = "Response Fail";
						});
						
						$scope.areaSubmitError = 0;
						$scope.areaSubmitSuccess = 1;
						$scope.successMsg = "Data Submited Successfully";
						$timeout(function(){
	    					$('#priceTypeModal').modal('hide');
	    					$scope.areaSubmitSuccess = 0;
	    					document.getElementById("pricetitle").reset();
	    				}, 2000);
					} else {
						$scope.areaSubmitSuccess = 0;
						$scope.areaSubmitError = 1;
						$scope.errorMsg = $scope.addproperty;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addarea = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.addproperty;
				});   			
			}
		}	
				
		$scope.addAreaRow = function() {									
			if(!$scope.areatypeidadd) {				
				$scope.errorArea = 1;
				$scope.msgArea = "Please select area type!";
				document.getElementById("areatypeidadd").focus();
			} else if(!$scope.areavalueadd) {				
				$scope.errorAreaV = 1;
				$scope.msgAreaV = "Please enter area value!";
				document.getElementById("areavalueadd").focus();
			} else if(!$scope.areaunittypeadd) {				
				$scope.errorAreaU = 1;
				$scope.msgAreaU = "Please select unit type!";
				document.getElementById("areaunittypeadd").focus();
			} else 	{			
				for (i in $scope.getAreaName) {
					if ($scope.getAreaName[i].areaTypeId == $scope.areatypeidadd) {
						$scope.title = $scope.getAreaName[i].areaTypeTitle;
						break;
					}
				}
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.areaunittypeadd) {
						$scope.unit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}					
				}
				$scope.projectArea.push({'areaId' : $scope.areatypeidadd, 'areaTypeTitle' : $scope.title, 'areaValue' : $scope.areavalueadd, 'unitId' : $scope.areaunittypeadd, 'measurementUnitName' : $scope.unit});
			}
		}
		
		$scope.removeAreaRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectArea.length; i++) {
				if($scope.projectArea[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectArea.splice(index, 1);
		};
		
		
		$scope.addRoomTitle = function() {
			if($scope.descriptionadd=="" || $scope.descriptionadd==undefined){
				$scope.descriptionadd="";
			}
			
			if($scope.roomtitleadd==undefined || $scope.roomtitleadd=="") {			
				$scope.errorRoomTitle = 1;
				$scope.msgRoomTitle = "Please enter room title";
				document.getElementById("roomtitleadd").focus();
			} else {
				$scope.spin = 1;				
											
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "&" , "$" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "#" , "~" );
				$scope.descriptionadd = tools_replaceAll($scope.descriptionadd, "%" , "!" );
				
				
				var link = baseUrl+'addRoomType?roomtitle='+$scope.roomtitleadd+'&description='+$scope.descriptionadd;
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addroom = data;
					$scope.spin = 0;					
					if($scope.addroom == 'Success'){
						var link = baseUrl+'getRoomName';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getRoomName = data;
						}).error(function(data, status, headers, config) {
							$scope.getRoomName = "Response Fail";
						});
						
						$scope.roomSubmitError = 0;
						$scope.roomSubmitSuccess = 1;
						$scope.successMsg = "Data Submited Successfully";
						$timeout(function(){
	    					$('#roomTypeModal').modal('hide');
	    					$scope.roomSubmitSuccess = 0;
	    					document.getElementById("roomtitle").reset();
	    				}, 2000);
					} else {
						$scope.roomSubmitSuccess = 0;
						$scope.roomSubmitError = 1;
						$scope.errorMsg = $scope.addroom;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.addroom = data;
					$scope.roomSubmitSuccess = 0;
					$scope.roomSubmitError = 1;
					$scope.errorMsg = $scope.addroom;
				});   			
			}
		}
		
		$scope.setFlag = function() {
			$scope.errorDevelopmentAmount = 0;
			$scope.errorTower = 0;
			$scope.errorRoomHunit = 0;
			$scope.errorRoomHeight = 0;
			$scope.errorRoomBunit = 0;
			$scope.errorRoomBreadth = 0;
			$scope.errorRoomLunit = 0;
			$scope.errorRoomLength = 0;		
			$scope.errorAreaV = 0;
			$scope.errorAreaU = 0;
			$scope.errorArea = 0;
			$scope.errorRoomTitle = 0;
			$scope.errorRoomTitle = 0;
			$scope.errorProjectTitle = 0;
			$scope.errorRealestateCategoryTitle = 0;
			$scope.errorRealestateSubcategoryTitle = 0;
			$scope.errorRealestateTypeTitle = 0;
			$scope.errorPropertyTitle = 0;
			$scope.errorUnits = 0;
			$scope.errorRealCategoryTitle = 0;
			$scope.errorCategoryTitle = 0;
			$scope.errorRealSubcategoryTitle = 0;
			$scope.errorRealSubTitle = 0;
			$scope.errorSpecDescription = 0;
			$scope.errorAmenityDescription = 0;			
			$scope.errorUnitTitle = 0;
			$scope.errorPriceL = 0;
			$scope.errorPriceV = 0;
			$scope.errorPriceU = 0;
			$scope.errorAreaU = 0;
			$scope.errorPriceLable = 0;
			$scope.errorPriceValue = 0;			
			$scope.errorSequence = 0;
			$scope.errorSequenceTitle = 0;
			$scope.errorLable = 0;
			$scope.errorValue = 0;
			$scope.errorUnit = 0;
			$scope.errorValue = 0;
		}		
				
		$scope.addPriceDetailRow = function() {
									
			if(!$scope.pricelableadd) {				
				$scope.errorPriceL = 1;
				$scope.msgPriceL = "Please enter price lable!";
				document.getElementById("pricelableadd").focus();
			} else if(!$scope.pricevalueadd) {				
				$scope.errorPriceV = 1;
				$scope.msgPriceV = "Please enter price value!";
				document.getElementById("pricevalueadd").focus();
			} else if(!$scope.areaunittypeadd) {				
				$scope.errorPriceU = 1;
				$scope.msgPriceU = "Please select unit type!";
				document.getElementById("areaunittypeadd").focus();
			} else 	{
				
				$scope.pricelableadd = tools_replaceAll($scope.pricelableadd, "&" , "$" );
				$scope.pricelableadd = tools_replaceAll($scope.pricelableadd, "#" , "~" );
				$scope.pricelableadd = tools_replaceAll($scope.pricelableadd, "%" , "!" );
				
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.areaunittypeadd) {
						$scope.unit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}					
				}
				$scope.projectUnitPrice.push({'priceLable' : $scope.pricelableadd, 'priceValue' : $scope.pricevalueadd, 'unitId' : $scope.areaunittypeadd, 'unitName' : $scope.unit});
			}
		}
		
		$scope.removePriceDetailRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectUnitPrice.length; i++) {
				if($scope.projectUnitPrice[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.projectUnitPrice.splice(index, 1);
		};
		
		$scope.editProperty = function(propertyid) {
			
			if(!$scope.projectunitid) {
				$scope.projectunitid = 0;
			}
			if(!$scope.propertytypeid) {
				$scope.propertytypeid = 0;
			}
			if(!$scope.flooredit) {
				$scope.flooredit = "";
			}
			if(!$scope.watersourceedit) {
				$scope.watersourceedit = "";
			}
			if(!$scope.drainageedit) {
				$scope.drainageedit = "";
			}
			if(!$scope.furnishingedit) {
				$scope.furnishingedit = "";
			}
			if(!$scope.reservedparkingedit) {
				$scope.reservedparkingedit = "";
			}
			if(!$scope.propertyavailabilityedit) {
				$scope.propertyavailabilityedit = "";
			}
			if(!$scope.propertyavailabilitydes) {
				$scope.propertyavailabilitydes = "";
			}
			if(!$scope.maintenancechargesedit) {
				$scope.maintenancechargesedit = "";
			}
			if(!$scope.maintenanceamountedit) {
				$scope.maintenanceamountedit = 0;
			}
			if(!$scope.propertydescriptionedit) {
				$scope.propertydescriptionedit = "";
			}
			
			if($scope.projectidedit==undefined || $scope.projectidedit=="") {			
				$scope.errorProjectTitle = 1;
				$scope.msgProjectType = "Please select project type!";
				document.getElementById("projectidedit").focus();
			} else if($scope.categoryid==undefined || $scope.categoryid=="") {			
				$scope.errorRealestateCategoryTitle = 1;
				$scope.msgCategoryType = "Please select realestate category!";
				document.getElementById("categoryid").focus();
			} else if($scope.subcategoryid==undefined || $scope.subcategoryid=="") {			
				$scope.errorRealestateSubcategoryTitle = 1;
				$scope.msgRealestateSubType = "Please select realestate subcategory!";
				document.getElementById("subcategoryid").focus();
			} else if($scope.realestateid==undefined || $scope.realestateid=="") {			
				$scope.errorRealestateTypeTitle = 1;
				$scope.msgRealestateTypeTitle = "Please select realestate type!";
				document.getElementById("realestateid").focus();
			} else if($scope.propertytitleedit==undefined || $scope.propertytitleedit=="") {			
				$scope.errorUnits = 1;
				$scope.msgUnits = "Please enter property title!";
				document.getElementById("propertytitleedit").focus();
			} else {
				
				$scope.spin = 1;
							
				var link = baseUrl+'editProperty?propertyid='+propertyid+'&projectid='+$scope.projectidedit+'&realecateid='+$scope.categoryid+'&realestatesubid='+$scope.subcategoryid+'&realestatetypeid='+$scope.realestateid+'&propertytypeoid='+$scope.propertytypeid+'&propertytitle='+$scope.propertytitleedit+'&propertyunitmasterid='+$scope.projectunitid+'&floor='+$scope.flooredit+'&watersource='+$scope.watersourceedit+'&drainage='+$scope.drainageedit+'&furnishing='+$scope.furnishingedit+'&parking='+$scope.reservedparkingedit+'&availability='+$scope.propertyavailabilityedit+'&description='+$scope.propertyavailabilitydes+'&charges='+$scope.maintenancechargesedit+'&amount='+$scope.maintenanceamountedit+'&pdescription='+$scope.propertydescriptionedit;
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editproperty = data;
					var link = baseUrl+'getProperty';
					$http.get(link).success(function(data, status, headers, config) {
						$scope.getProperty = data;
					}).error(function(data, status, headers, config) {
						$scope.getProperty = "Response Fail";
					});
					$scope.spin = 0;					
					if($scope.editproperty = "Success" ) {
						$scope.propertySubmitError = 0;
						$scope.propertySubmitSuccess = 1;
						$scope.successMsg = "Data Submited Successfully";
						$timeout(function(){
							$scope.propertySubmitSuccess = 0;
							$('#editModal').modal('hide');	    					
	    					document.getElementById("editproperty").reset();							
						}, 2000);
					} else {
						$scope.propertySubmitSuccess = 0;
						$scope.propertySubmitError = 1;
						$scope.errorMsg = $scope.addproperty;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.editproperty = data;
					$scope.propertySubmitSuccess = 0;
					$scope.propertySubmitError = 1;
					$scope.errorMsg = $scope.addproperty;
				});
				
				
			}
		}
		
		$scope.editPropertyPrice = function(propertyid) {			
			if(!$scope.pricelableedit) {				
				$scope.errorPriceL = 1;
				$scope.msgPriceL = "Please enter price lable type!";
				document.getElementById("pricelableedit").focus();
			} else if(!$scope.pricevalueedit) {				
				$scope.errorPriceV = 1;
				$scope.msgPriceV = "Please enter value!";
				document.getElementById("pricevalueedit").focus();
			} else if(!$scope.areaunittypeedit) {				
				$scope.errorPriceU = 1;
				$scope.msgPriceU = "Please select unit type!";
				document.getElementById("areaunittypeedit").focus();
			} else {
				$scope.spin = 1;					
				
				$scope.pricelableedit = tools_replaceAll($scope.pricelableedit, "&" , "$" );
				$scope.pricelableedit = tools_replaceAll($scope.pricelableedit, "#" , "~" );
				$scope.pricelableedit = tools_replaceAll($scope.pricelableedit, "%" , "!" );
				
				var link = baseUrl+'editPropertyPriceDetails?propertyid='+propertyid+'&pricelable='+$scope.pricelableedit+'&pricevalue='+$scope.pricevalueedit+'&measurementunitid='+$scope.areaunittypeedit;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editpropertyprice = data;
					$scope.spin = 0;					
					if($scope.editpropertyprice == 'Success'){
						var link = baseUrl+'getPropertyPriceInfoById?propertyid='+propertyid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.propertyPriceInfo = data;
						}).error(function(data, status, headers, config) {
							$scope.propertyPriceInfo = "Response Fail";
						});
					} else {
						$scope.errorMsg = $scope.editpropertyprice;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editpropertyprice = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editpropertyprice;
				});   			
			}
		}
		
		$scope.editAreaDetail = function(propertyid) {			
			if(!$scope.areatypeidedit) {				
				$scope.errorArea = 1;
				$scope.msgArea = "Please select area type!";
				document.getElementById("areatypeidedit").focus();
			} else if(!$scope.areavalueedit) {				
				$scope.errorAreaV = 1;
				$scope.msgAreaV = "Please enter area value!";
				document.getElementById("areavalueedit").focus();
			} else if(!$scope.areaunittypeedit) {				
				$scope.errorAreaU = 1;
				$scope.msgAreaU = "Please select unit type!";
				document.getElementById("areaunittypeedit").focus();
			} else {
				$scope.spin = 1;					
				
				var link = baseUrl+'editAreaDetails?propertyid='+propertyid+'&areatitleid='+$scope.areatypeidedit+'&value='+$scope.areavalueedit+'&unit='+$scope.areaunittypeedit;				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editarea = data;
					$scope.spin = 0;					
					if($scope.editarea == 'Success'){
						var link = baseUrl+'getPropertyAreaById?propertyid='+propertyid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.propertyArea = data;
						}).error(function(data, status, headers, config) {
							$scope.propertyArea = "Response Fail";
						});						
					} else {
						$scope.errorMsg = $scope.editarea;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editarea = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editarea;
				});   			
			}
		}
		
		$scope.editRoomDetail = function(propertyid) {			
			
			if($scope.roomheightadd=="" || $scope.roomheightadd==undefined){
				$scope.roomheightadd=0.0;
			}	
			
			if($scope.roomheightunit=="" || $scope.roomheightunit==undefined){
				$scope.roomheightunit=2;
			}			
			
			if(!$scope.roomtypeidadd) {				
				$scope.errorRoomTitle = 1;
				$scope.msgTitle = "Please select room Title!";
				document.getElementById("roomtypeidadd").focus();
			} else if(!$scope.roomlengthadd) {				
				$scope.errorRoomLength = 1;
				$scope.msgLength = "Please Room Length!";
				document.getElementById("roomlengthadd").focus();
			} else if(!$scope.roomlengthunit) {				
				$scope.errorRoomLunit = 1;
				$scope.msgLunit = "Please select length Unit type!";
				document.getElementById("roomlengthunit").focus();
			} else if(!$scope.roombreadthadd) {				
				$scope.errorRoomBreadth = 1;
				$scope.msgBreadth = "Please enter room breadth!";
				document.getElementById("roombreadthadd").focus();
			} else if(!$scope.roombreadthunit) {				
				$scope.errorRoomBunit = 1;
				$scope.msgBunit = "Please select breadth unit type!";
				document.getElementById("roombreadthunit").focus();
			} else {
				$scope.roomspin = 1;					
				
				var link = baseUrl+'editRoomDetails?propertyid='+propertyid+'&roomtitleid='+$scope.roomtypeidadd+'&length='+$scope.roomlengthadd+'&lengthunit='+$scope.roomlengthunit+  '&breadth='+$scope.roombreadthadd+'&breadthunit='+$scope.roombreadthunit+'&height='+$scope.roomheightadd+'&heightunit='+$scope.roomheightunit;				
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editroom = data;
					$scope.roomspin = 0;					
					if($scope.editroom == 'Success'){
						var link = baseUrl+'getPropertyRoomById?propertyid='+propertyid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.propertyRoom = data;
						}).error(function(data, status, headers, config) {
							$scope.propertyRoom = "Response Fail";
						});					
					} else {
						$scope.errorMsg = $scope.editroom;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editroom = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editroom;
				});   			
			}
		}
		
		$scope.editPriceDetail = function(propertyid) {			
						
			if($scope.saledeededit=="" || $scope.saledeededit==undefined){
				$scope.saledeededit=0;
			}
			if($scope.gstedit=="" || $scope.gstedit==undefined){
				$scope.gstedit=0;
			}
			if($scope.stampdutyedit=="" || $scope.stampdutyedit==undefined){
				$scope.stampdutyedit=0;
			}
			if($scope.maintenanceedit=="" || $scope.maintenanceedit==undefined){
				$scope.maintenanceedit=0;
			}
			if($scope.totaledit=="" || $scope.totaledit==undefined){
				$scope.totaledit=0;
			}
			
			if(!$scope.developmentedit) {				
				$scope.errorDevelopmentAmount = 1;
				$scope.msgDevelopment = "Please enter development amount!";
				document.getElementById("developmentedit").focus();
			} else {
				$scope.pricespin = 1;					
				
				var link = baseUrl+'editPriceDetails?propertyid='+propertyid+'&saledeed='+$scope.saledeededit+'&gst='+$scope.gstedit+'&stampduty='+$scope.stampdutyedit+'&development='+$scope.developmentedit+'&maintenance='+$scope.maintenanceedit+'&total='+$scope.totaledit;				
				
				$http.post(link).success(function(data, status, headers, config) {
					$scope.editprice = data;
					$scope.pricespin = 0;					
					if($scope.editprice == 'Success'){
						var link = baseUrl+'getPropertyPriceById?propertyid='+propertyid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.propertyPrice = data;
						}).error(function(data, status, headers, config) {
							$scope.propertyPrice = "Response Fail";
						});					
					} else {
						$scope.errorMsg = $scope.editprice;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editprice = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editprice;
				});   			
			}
		}	
		
		$scope.roomlist = [];
		$scope.addRoomRow = function() {
			
			if(!$scope.roomheightadd){
				$scope.roomheightadd=0;
			}
			if(!$scope.roomheightunit){
				$scope.roomheightunit=2;
			}
			
			if(!$scope.roomtypeidadd1) {				
				$scope.errorRoomTitle = 1;
				$scope.msgTitle = "Please select room Title!";
				document.getElementById("roomtypeidadd1").focus();
			} else if(!$scope.roomlengthadd) {				
				$scope.errorRoomLength = 1;
				$scope.msgLength = "Please Room Length!";
				document.getElementById("roomlengthadd").focus();
			} else if(!$scope.roomlengthunit) {				
				$scope.errorRoomLunit = 1;
				$scope.msgLunit = "Please select length Unit type!";
				document.getElementById("roomlengthunit").focus();
			} else if(!$scope.roombreadthadd) {				
				$scope.errorRoomBreadth = 1;
				$scope.msgBreadth = "Please enter room breadth!";
				document.getElementById("roombreadthadd").focus();
			} else if(!$scope.roombreadthunit) {				
				$scope.errorRoomBunit = 1;
				$scope.msgBunit = "Please select breadth unit type!";
				document.getElementById("roombreadthunit").focus();
			} else {			
				for (i in $scope.getRoomName) {
					if ($scope.getRoomName[i].roomTitleId == $scope.roomtypeidadd1) {
						$scope.title = $scope.getRoomName[i].roomTitle;
						break;
					}
				}
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.roomlengthunit) {
						$scope.lengthunit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}
				}
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.roombreadthunit) {
						$scope.breadthunit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}
				}
				for (i in $scope.getMeasurementUnits) {
					if ($scope.getMeasurementUnits[i].measurementUnitId == $scope.roomheightunit) {
						$scope.heightunit = $scope.getMeasurementUnits[i].measurementUnitName;
						break;
					}
				}
				
				$scope.roomlist.push({'roomTitleId' : $scope.roomtypeidadd1, 'title' : $scope.title, 'length' : $scope.roomlengthadd, 'lengthUnitId' : $scope.roomlengthunit, 'lengthunit' : $scope.lengthunit,  'breadth' : $scope.roombreadthadd, 'breadthUnitId' : $scope.roombreadthunit, 'breadthunit' : $scope.breadthunit,  'height' : $scope.roomheightadd, 'heightUnitId' : $scope.roomheightunit, 'heightunit' : $scope.heightunit});
			}
		}
		
		$scope.removeRoomRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.roomlist.length; i++) {
				if($scope.roomlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.roomlist.splice(index, 1);
		};
		
		$scope.checkRecordSelectForDelete = function() {			
			$scope.d = 0;		
			angular.forEach($scope.getProperty, function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});			
		}
		
		$scope.deleteProperty = function() {
			angular.forEach($scope.getProperty, function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteProperty?propertyid='+item.propertyId;					
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteproperty = data;
						var link = baseUrl+'getAllCounts';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.allcounts = data;
						}).error(function(data, status, headers, config) {
							$scope.allcounts = "Response Fail";
						});
					}).error(function(data, status, headers, config) {
						$scope.deleteproperty = "Response Fail";
					});
				}
				var link = baseUrl+'getPropertyByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getProperty = data;
					$('#deleteModal').modal('hide');
				}).error(function(data, status, headers, config) {
					$scope.getProperty = "Response Fail";
				});
								
			});			
		}
		
				
		$scope.propertyLayout = [];
		var LayoutImage = new FormData();
		$scope.addLayoutRow = function() {									
			if(!$scope.layouttitleadd) {				
				$scope.errorLayoutTitle = 1;
				$scope.msgLayoutTitle = "Please enter title!";
				document.getElementById("layouttitleadd").focus();
			} else if(!document.getElementById("layoutimageadd").value) {				
				$scope.errorLayoutImage = 1;
				$scope.msgLayoutImage = "Please choose file!";
				document.getElementById("layoutimageadd").focus();
				$timeout(function(){
					$scope.errorLayoutImage = 0;
				}, 2000);
			} else {
				$scope.propertyLayout.push({'layoutTitle' : $scope.layouttitleadd});
				LayoutImage.append("filelayout",layoutimageadd.files[0]);
				$scope.layouttitleadd = "";
				document.getElementById("layoutimageadd").value = "";
			}
		}
		
		$scope.removeLayoutRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.propertyLayout.length; i++) {
				if($scope.propertyLayout[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record...Please try again!");
				return;
			}
			$scope.propertyLayout.splice(index, 1);
		};
		
		
		
		$scope.editLayoutRow = function(){
			if(!$scope.layouttitleedit) {				
				$scope.errorLayoutTitle = 1;
				$scope.msgLayoutTitle = "Please enter title!";
				document.getElementById("layouttitleedit").focus();
			} else if(!document.getElementById("layoutimageedit").value) {				
				$scope.errorLayoutImage = 1;
				$scope.msgLayoutImage = "Please choose file!";
				document.getElementById("layoutimageedit").focus();
				$timeout(function(){
					$scope.errorLayoutImage = 0;
				}, 2000);
			} else {
				$scope.spin = 1;
				
				var link = baseUrl+'editPropertyLayout?propertyid='+$scope.propertyid+'&layouttitle='+$scope.layouttitleedit;				
				var formData = new FormData();
				formData.append("filelayout",layoutimageedit.files[0]);
				$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
					$scope.editlayout = data;
					if($scope.editlayout == 'Success'){
						var link = baseUrl+'getPropertyLayoutById?propertyid='+$scope.propertyid;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.propertyLayout = data;
							$scope.spin = 0;
						}).error(function(data, status, headers, config) {
							$scope.propertyLayout = "Response Fail";
						});					
					} else {
						$scope.errorMsg = $scope.editlayout;
					}					
				}).error(function(data, status, headers, config) {
					$scope.editlayout = data;
					$scope.areaSubmitSuccess = 0;
					$scope.areaSubmitError = 1;
					$scope.errorMsg = $scope.editlayout;
				});
			}
		}
		
		$scope.removeLayoutRowEdit = function(layoutid){
			var link = baseUrl+'deletePropertyLayout?layoutid='+layoutid;		
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deletepropertylayout = data;
				var link = baseUrl+'getPropertyLayoutById?propertyid='+$scope.propertyid;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.propertyLayout = data;
				}).error(function(data, status, headers, config) {
					$scope.propertyLayout = "Response Fail";
				});	
			}).error(function(data, status, headers, config) {
				$scope.deletepropertylayout = "Response Fail";
			});
		}
		
		
}]);

