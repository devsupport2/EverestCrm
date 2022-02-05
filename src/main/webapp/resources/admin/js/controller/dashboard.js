angular.module("MyApp", ["chart.js"]).controller('dashboardCtrl', ['$scope', '$filter', '$http', '$window', '$location', '$timeout' ,function ($scope, $filter, $http, $window, $location, $timeout) {

var url = "/everest/";
var adminurl = "/everest/";


	
	var baseUrl = $location.protocol()+"://"+location.host+url;
    
	$scope.labels = [];
	$scope.data = [];
	$scope.projectlabels = [];
	$scope.projectdata = [];
	$scope.enquirylabels = [];
	$scope.enquirydata = [];
	
	var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
		$scope.labels = ["New", "Close", "Won", "Lost", "Working", "Hold"];
		$scope.data = [$scope.allcounts.newEnquiryCount, $scope.allcounts.closeEnquiryCount, $scope.allcounts.wonEnquiryCount, $scope.allcounts.lostEnquiryCount, $scope.allcounts.workingEnquiryCount, $scope.allcounts.holdEnquiryCount];
		$scope.totalCount = $scope.allcounts.closeEnquiryCount+$scope.allcounts.wonEnquiryCount+$scope.allcounts.lostEnquiryCount+$scope.allcounts.workingEnquiryCount+$scope.allcounts.newEnquiryCount+$scope.allcounts.holdEnquiryCount;
		$scope.projectlabels = ["Ongoing", "Completed"];
		$scope.projectdata = [$scope.allcounts.ongoingProjectCount, $scope.allcounts.completedProjectCount];
		  
	}).error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});
	
	$scope.getDashbroadDetails = function(){
		var link = baseUrl+'getAllDashBoardDetails';
		$http.get(link).success(function(data, status, headers, config) {
			$scope.getDashBoard = data;
			
			angular.forEach($scope.getDashBoard.enquiryCountryWiseList,function(item) {
				$scope.enquirylabels.push(item.countryName);
				$scope.enquirydata.push(item.countryEnquiryCount);
			});
			
		}).error(function(data, status, headers, config) {
			$scope.getDashBoard = "Response Fail";
		});
	}
	
	
	var link = baseUrl+'getEnquiryCurrentStats';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.getEnquiryCurrentStats = data;
	}).error(function(data, status, headers, config) {
		$scope.getEnquiryCurrentStats = "Response Fail";
	});
	
	
}]);