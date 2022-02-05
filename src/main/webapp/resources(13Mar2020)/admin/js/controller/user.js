var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('userCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,
	function ($scope, $filter, $http, $window, $location, $timeout) {
		$scope.currentPage = 0;
		$scope.pageSize = 20;
		$scope.search = '';
		$scope.startindex = $scope.currentPage;	    
	    $scope.pages = [5, 10, 20, 50, 100, 'All'];	
		$scope.spin = 0;
    
		$scope.numberOfPages=function() {
			return Math.ceil($scope.getUsers.length/$scope.pageSize);
		}
    
		var baseUrl = $location.protocol()+"://"+location.host+url;
		var link = baseUrl+'getAllCounts';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.allcounts = data;
		}).error(function(data, status, headers, config) {
			$scope.allcounts = "Response Fail";
		});
		
		var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUsers = data;
		}).error(function(data, status, headers, config) {
			$scope.getUsers = "Response Fail";
		});
		
		var link = baseUrl+'getProjectName';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getProjectName = data;
		}).error(function(data, status, headers, config) {
			$scope.getProjectName = "Response Fail";
		});
		
		$scope.next = function() {
			$scope.search = '';
			if($scope.pageSize == "All") {
					
			} else {
				$scope.currentPage = $scope.currentPage + 1;
				$scope.startindex = $scope.pageSize * $scope.currentPage;					
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
		}
		
		$scope.prev = function() {
			$scope.search = '';
			$scope.currentPage = $scope.currentPage - 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
			
			var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getUsers = data;
			}).error(function(data, status, headers, config) {
				$scope.getUsers = "Response Fail";
			});
		}
		
		$scope.changePage = function() {
			$scope.search = '';
			$scope.currentPage = 0;
			$scope.startindex = 0;
			
			if($scope.pageSize == "All") {
				var link = baseUrl+'getUsers';
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			} else {
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
		}
		
		$scope.searchUser = function() {
			var search = $scope.search;			
			if(search == undefined || search == "") {
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			} else {
				var link = baseUrl+'searchUsers?keyword='+search;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}
		}	
		
		var link = baseUrl+'getUserTypes';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getUserTypes = data;
		}).error(function(data, status, headers, config) {
			$scope.getUserTypes = "Response Fail";
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
		
		$scope.setFlag = function(){			
			$scope.errorUserType = 0;
			$scope.errorFirstName = 0;
			$scope.errorCountryName = 0;
			$scope.errorCountry = 0;
			$scope.errorStateName = 0;
			$scope.errorProject = 0;
		}
		
		$scope.getUserByType = function(usertypeid) {
			if(usertypeid){
				var link = baseUrl+'getUserByType?usertypeid='+usertypeid;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			} else {
				var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
				$http.get(link).success(function(data, status, headers, config) {
					$scope.getUsers = data;
				}).error(function(data, status, headers, config) {
					$scope.getUsers = "Response Fail";
				});
			}			
		}
		
		$scope.projectlist = [];
		$scope.addProjectRow = function() {
			$scope.userSubmitError = 0;
			if(!$scope.projectidadd) {			
				$scope.errorProject = 1;
				$scope.msgProject = "Please select project!";
				document.getElementById("projectidadd").focus();
			} else {			
				var link = baseUrl+'getProjectDetailById?projectid='+$scope.projectidadd;				
				$http.get(link).success(function(data, status, headers, config) {
					$scope.projectDetailById = data;			
	            	
	            	$scope.projecttitle = $scope.projectDetailById.projectTitle;	            	
	            	
	            	$scope.projectlist.push({'projectId':$scope.projectidadd, 'projectTitle':$scope.projecttitle});
	    			$scope.projectidadd = "";
	    			
				}).error(function(data, status, headers, config) {
					$scope.projectDetailById = "Response Fail";
				});			
			}
		}

		$scope.removeProjectRow = function(item) {
			var index = -1;
			for(var i=0; i<$scope.projectlist.length; i++) {
				if($scope.projectlist[i] == item){
					index = i;
					break;
				}
			}
			if(index < 0) {
				$window.alert("Error while removing record..Please try again!");
				return;
			}
			$scope.projectlist.splice(index, 1);
		};
		
		$scope.addUser = function() {						
			$scope.dateofbirth = document.getElementById("datepicker").value;
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
			if(!$scope.gstnoadd) {
				$scope.gstnoadd = "";
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
			
			if(!$scope.accessproject)
			{
				$scope.accessproject="n";
			}else{
				$scope.accessproject="y";
			}
			
			if(!$scope.accessproperty)
			{
				$scope.accessproperty="n";
			}else
			{
				$scope.accessproperty="y";
			}
			
			if(!$scope.accessenquiry)
			{
				$scope.accessenquiry="n";
			}else
			{
				$scope.accessenquiry="y";
			}
			
			if(!$scope.accesspayment)
			{
				$scope.accesspayment="n";
			}else{
				$scope.accesspayment="y";
			}
			
			if(!$scope.accessuser)
			{
				$scope.accessuser="n";
			}else{
				$scope.accessuser="y";
			}
			
			if(!$scope.firstnameadd) {				
				$scope.errorFirstName = 1;
				$scope.msgFirstName = "Please enter first name";
				document.getElementById("firstnameadd").focus();
			} else if(!$scope.usertypenameadd) {				
				$scope.errorUserType = 1;
				$scope.msgUserType = "Please select user type";
				document.getElementById("usertypenameadd").focus();
			} else if($scope.usertypenameadd == 2 && $scope.projectlist.length == 0) {				
				$scope.userSubmitError = 1;
				$scope.errorMsg = "Please assign atleat one project to employee!";
				return;
			} else {
				$scope.spin = 1;
				var a = 0;
				
				var link = baseUrl+'addUser?companyname='+$scope.companynameadd+'&firstname='+$scope.firstnameadd+'&middlename='+$scope.middlenameadd+'&lastname='+$scope.lastnameadd+'&usertypename='+$scope.usertypenameadd+'&gender='+$scope.genderadd+'&dateofbirth='+$scope.dateofbirth+'&aadharnumber='+$scope.aadharnumber+'&passportnumber='+$scope.passportnumberadd+'&pannumber='+$scope.pannumberadd+'&address1='+$scope.address1add+'&address2='+$scope.address2add+'&address3='+$scope.address3add+'&countryname='+$scope.countrynameadd+'&statename='+$scope.statenameadd+'&cityname='+$scope.citynameadd+'&pincode='+$scope.pincodeadd+'&mobilenumber='+$scope.mobilenumberadd+'&landlinenumber='+$scope.landlinenumberadd+'&email='+$scope.emailadd+'&password='+$scope.passwordadd+'&accessproject='+$scope.accessproject+'&accessproperty='+$scope.accessproperty+'&accessenquiry='+$scope.accessenquiry+'&accesspayment='+$scope.accesspayment+'&accessuser='+$scope.accessuser+'&gstno='+$scope.gstnoadd;				
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
					
					if($scope.adduser == 'Success' && $scope.projectlist.length > 0){
						
						angular.forEach($scope.projectlist,function(item) {												
							var link = baseUrl+'addEmployeeProject?projectid='+item.projectId;
							$http.post(link).success(function(data, status, headers, config) {
								$scope.addemployeeproject = data;
								a = a + 1;
								if($scope.projectlist.length == a) {
									$scope.spin = 0;
									$scope.userSubmitError = 0;
									$scope.userSubmitSuccess = 1;
									$scope.successMsg = "Data added";
									$timeout(function(){
										$scope.userSubmitSuccess = 0;
										$window.location.href = adminurl+'manage_user';
									}, 2000);
								}
							}).error(function(data, status, headers, config) {
								$scope.addenquiryassign = "Response Fail";
							});
						});	
						
					} else if($scope.adduser == 'Success' && $scope.projectlist.length == 0){
						$scope.spin = 0;
						$scope.userSubmitError = 0;
						$scope.userSubmitSuccess = 1;
						$scope.successMsg = "Data added";
						$timeout(function(){
							$scope.userSubmitSuccess = 0;
							$window.location.href = adminurl+'manage_user';
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
		
		$scope.deleteProfilePicture = function() {
			$scope.profilepicture1 = "";
		}
		
		$scope.editProjectRow = function(userid) {
			if(!$scope.projectid) {			
				$scope.errorProject = 1;
				$scope.msgProject = "Please select project!";
				document.getElementById("projectid").focus();
			} else {
				$scope.spinEmployeeProject = 1;
				var link = baseUrl+'editEmployeeProject?userid='+userid+'&projectid='+$scope.projectid;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addemployeeproject = data;				
					
					var link = baseUrl+'getEmployeeProjectListById?userid='+userid;			
					$http.get(link).success(function(data, status, headers, config) {
						$scope.userDetailById.employeeProjectList = data;
						$scope.spinEmployeeProject = 0;
					}).error(function(data, status, headers, config) {
						$scope.userDetailById.employeeProjectList = "Response Fail";
					});
					
				}).error(function(data, status, headers, config) {
					$scope.addemployeeproject = "Response Fail";
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
			if(!$scope.gstnoedit){
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
								$('#editModal').modal('hide');
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
		
		$scope.deleteProjectRow = function(epid, userid) {
			var link = baseUrl+'deleteEmployeeProject?epid='+epid;			
			$http['delete'](link).success(function(data, status, headers, config) {
				$scope.deleteep = data;
				
				var link = baseUrl+'getEmployeeProjectListById?userid='+userid;			
				$http.get(link).success(function(data, status, headers, config) {
					$scope.userDetailById.employeeProjectList = data;					
				}).error(function(data, status, headers, config) {
					$scope.userDetailById.employeeProjectList = "Response Fail";
				});
				
			}).error(function(data, status, headers, config) {
				$scope.deleteep = "Response Fail";
			});					
		}
		
		$scope.checkRecordSelectForDelete = function() {
			$scope.d = 0;			
			angular.forEach($scope.getUsers,function(item) {
				if (item.selected) {
					$scope.d = $scope.d + 1
				}
			});
		}
		
		$scope.deleteUser = function() {
			angular.forEach($scope.getUsers,function(item) {
				if (item.selected) {
					var link = baseUrl+'deleteUser?userid='+item.userId;
					$http['delete'](link).success(function(data, status, headers, config) {
						$scope.deleteuser = data;
					}).error(function(data, status, headers, config) {
						$scope.deleteuser = "Response Fail";
					});
				}
			});
			var link = baseUrl+'getUsersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(function(data, status, headers, config) {
				$scope.getUsers = data;
				$('#deleteModal').modal('hide');
			}).error(function(data, status, headers, config) {
				$scope.getUsers = "Response Fail";
			});
		}
		
		$scope.addCountry = function() {			
			if(!$scope.countrycodeadd) {
				$scope.countrycodeadd = "";
			}			
			if(!$scope.countrydialingcodeadd) {
				$scope.countrydialingcodeadd = "";
			}
			if(!$scope.countrynameadd) {				
				$scope.errorCountryName = 1;
				$scope.msgCountryName = "Please enter country name";
				document.getElementById("countrynameadd").focus();
			} else {
				$scope.spinCountry = 1;				
				var link = baseUrl+'addCountry?countryname='+$scope.countrynameadd+'&countrycode='+$scope.countrycodeadd+'&countrydialingcode='+$scope.countrydialingcodeadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addcountry = data;
					$scope.spinCountry = 0;
					if($scope.addcountry == 'Success'){
						
						var link = baseUrl+'getCountries';
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getCountries = data;
							
							$scope.countrySubmitError = 0;
							$scope.countrySubmitSuccess = 1;
							$scope.successMsg = "Data added";
							$timeout(function(){
								$scope.countrySubmitSuccess = 0;
								$('#countryModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getCountries = "Response Fail";
						});		
						
					} else {
						$scope.countrySubmitSuccess = 0;
						$scope.countrySubmitError = 1;
						$scope.errorMsg = $scope.addcountry;						
					}
					
				}).error(function(data, status, headers, config) {
					$scope.addcountry = data;
					$scope.spinCountry = 0;
					$scope.countrySubmitSuccess = 0;
					$scope.countrySubmitError = 1;
					$scope.errorMsg = $scope.addcountry;	
				});
			}
		}
		
		$scope.addState = function() {			
			if(!$scope.statecodeadd) {
				$scope.statecodeadd = "";
			}

			if(!$scope.countrynameadd) {				
				$scope.errorCountry = 1;
				$scope.msgCountry = "Please select country";
				document.getElementById("countrynameadd").focus();
			} else if(!$scope.statenameadd) {				
				$scope.errorStateName = 1;
				$scope.msgStateName = "Please enter state name";
				document.getElementById("statenameadd").focus();
			} else {
				$scope.spinState = 1;				
				var link = baseUrl+'addState?statename='+$scope.statenameadd+'&statecode='+$scope.statecodeadd+'&countryname='+$scope.countrynameadd;
				$http.post(link).success(function(data, status, headers, config) {
					$scope.addstate = data;
					$scope.spinState = 0;					
					if($scope.addstate == 'Success'){
						
						var link = baseUrl+'getStateByCountryId?countryid='+$scope.countrynameadd;
						$http.get(link).success(function(data, status, headers, config) {
							$scope.getStates = data;
							
							$scope.stateSubmitError = 0;
							$scope.stateSubmitSuccess = 1;
							$scope.successMsg = "Data added";
							$timeout(function(){
								$scope.stateSubmitSuccess = 0;
								$('#stateModal').modal('hide');
							}, 2000);
							
						}).error(function(data, status, headers, config) {
							$scope.getStates = "Response Fail";
						});					
						
					} else {
						$scope.stateSubmitSuccess = 0;
						$scope.stateSubmitError = 1;
						$scope.errorMsg = $scope.addstate;						
					}					
				}).error(function(data, status, headers, config) {
					$scope.spinState = 0;
					$scope.addstate = data;
					$scope.stateSubmitSuccess = 0;
					$scope.stateSubmitError = 1;
					$scope.errorMsg = $scope.addstate;
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
		
}]);