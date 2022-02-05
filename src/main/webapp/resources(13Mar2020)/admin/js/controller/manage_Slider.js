var app = angular.module("MyApp", []);

var url = "/everest/";
var adminurl = "/everest/";

app.controller('sliderCtrl', ['$scope', '$filter', '$http', '$window', '$location' ,function ($scope, $filter, $http, $window, $location){
	$scope.currentPage = 0;
	$scope.pageSize = 20;
	$scope.search = '';
	$scope.startindex = $scope.currentPage;
    
    $scope.pages = [5, 10, 20, 50, 100, 'All'];
	
	$scope.info = 0;
	$scope.success = 0;
	$scope.spin = 0;

	$scope.numberOfPages=function()
	{
		return Math.ceil($scope.getSliders.length/$scope.pageSize);
	}
    
    var baseUrl = $location.protocol()+"://"+location.host+url;
    
    var link = baseUrl+'getAllCounts';
	$http.get(link).success(function(data, status, headers, config) {
		$scope.allcounts = data;
	}). error(function(data, status, headers, config) {
		$scope.allcounts = "Response Fail";
	});
    
    $http.get("https://ipinfo.io/json").then(function (response) {
		$scope.ipaddress = response.data.ip;
	});

    var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
	$http.get(link).success(					
			function(data, status, headers, config)
			{
				$scope.getSliders = data;
				//$window.alert("From get Data-------1"+ $scope.getSliders[i].sliderSubtitle + "----" + $scope.getSliders[i].sliderTitle)
			}).
			error(function(data, status, headers, config)
			{
				$scope.getSliders = "Response Fail";
			});
	
	$scope.next = function()
	{
		$scope.search = '';
		if($scope.pageSize == "All")
		{
		}
		else
		{
			$scope.currentPage = $scope.currentPage + 1;
			$scope.startindex = $scope.pageSize * $scope.currentPage;
				
			var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getSliders = data;
						//$window.alert("From get Data-------2"+ $scope.getSliders[i].sliderSubtitle + "----" + $scope.getSliders[i].sliderTitle)
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSliders = "Response Fail";
					});
		}
	}
	
	$scope.prev = function()
	{
		$scope.search = '';
		$scope.currentPage = $scope.currentPage - 1;
		$scope.startindex = $scope.pageSize * $scope.currentPage;
		
		var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
		$http.get(link).success(					
				function(data, status, headers, config)
				{
					$scope.getSliders = data;
					//$window.alert("From get Data-------3"+ $scope.getSliders[i].sliderSubtitle + "----" + $scope.getSliders[i].sliderTitle)
				}).
				error(function(data, status, headers, config)
				{
					$scope.getSliders = "Response Fail";
					
				});
	}
	
	$scope.changePage = function()
	{
		$scope.search = '';
		$scope.currentPage = 0;
		$scope.startindex = 0;
		
		if($scope.pageSize == "All")
		{
			var link = baseUrl+'getSliders';
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getSliders = data;
						//$window.alert("From get Data-------"+ $scope.getSliders[i].sliderSubtitle + "----" + $scope.getSliders[i].sliderTitle)
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSliders = "Response Fail";
					});
		}
		else
		{
			var link = baseUrl+'getSlidersByPage?pagesize='+$scope.pageSize+'&startindex='+$scope.startindex;
			$http.get(link).success(					
					function(data, status, headers, config)
					{
						$scope.getSliders = data;
						//$window.alert("From get Data-------4"+ $scope.getSliders[i].sliderSubtitle + "----" + $scope.getSliders[i].sliderTitle)
					}).
					error(function(data, status, headers, config)
					{
						$scope.getSliders = "Response Fail";
					});
		}
	}
	
	$scope.addSlider = function()
	{
		var slidertitle = $scope.slidertitleadd;
		var active = $scope.activeadd;
		
		var sldsequence=$scope.sldsequenceadd;
		var sldsubtitle=$scope.sldsubtitleadd;
		//var slddescription=$scope.slddescriptionadd;
		//$scope.specdescription1 = CKEDITOR.instances.specdescriptionadd.getData();
		$scope.description = CKEDITOR.instances.slddescriptionadd.getData();
		var slddescription= $scope.description;//document.document.getElementById("slddescriptionadd");
		//$window.alert(document.getElementById("slddescriptionadd") + "---" +slddescription)
		//var sldlink;
		
		var active1 = "";
		
		if(active == true)
		{
			active1 = "y";
		}
		else
		{
			active1 = "n";
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
		
		if(slidertitle==undefined || slidertitle=="")
		{
			slidertitle = "";
		}
		
		if(imageadd.files[0]==undefined || imageadd.files[0]=="")
		{
			$window.alert("Please select image!");
			document.getElementById("imageadd").focus();
			return;
		}
		else
		{
			$scope.spin = 1;
			
			var tit = slidertitle.replace("&","$");
			var tit1 = tit.replace("#","~");
			var tit2 = tit1.replace("%","!");

			//var link = baseUrl+'addSlider?slidertitle='+tit2+'&active='+active1+'&ipaddress='+$scope.ipaddress+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
			var link = baseUrl+'addSliderDetails?slidertitle='+tit2+'&active='+active1+'&ipaddress='+$scope.ipaddress+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&sldsequence='+sldsequence+'&sldsubtitle='+sldsubtitle+'&slddescription='+slddescription;//+'&sldlink='+sllink;
			//$window.alert(link);
			var formData=new FormData();
			formData.append("image",imageadd.files[0]);
			$http({
			        method: 'POST',
			        url: link,
			        headers: {'Content-Type': undefined},
			        data: formData,
			        transformRequest: function(data, headersGetterFunction)
			        {
			        	return data;
			        }
			}).success(function(data, status, headers, config)	{
				$scope.addslider = data;
				$scope.spin = 0;
				$window.alert("Slider Added Successfully...");
				$window.location.href = adminurl+'manage_slider_details';
			}).error(function(data, status, headers, config) {
				$scope.addslider = "Response Fail";
			});
		}
	}
	
	
	$scope.getSlider = function(sliderid)
	{
		for (i in $scope.getSliders)
		{
            if ($scope.getSliders[i].sliderId == sliderid)
            {
            	$scope.sliderid = $scope.getSliders[i].sliderId;
            	//$window.alert($scope.sliderid);
            	$scope.slidertitle = $scope.getSliders[i].sliderTitle;
            	$scope.sliderimage = $scope.getSliders[i].image;
            	$scope.active1 = $scope.getSliders[i].active;
            	$scope.slddescription=$scope.getSliders[i].sliderDescription
            	
            	
            	$scope.sldsubtitle=$scope.getSliders[i].sliderSubtitle;
            	$scope.sldsequence=$scope.getSliders[i].sliderSequence;
            }
		}
		
		if($scope.active1 == "y")
			$scope.active = true;
	}
	
	$scope.deleteImage = function()
	{
		$scope.sliderimage = "";
	}
	
	
	$scope.editSlider = function(sliderid)	{
		var slidertitle = $scope.slidertitle;
		var active = $scope.active;
		
		var sldsequence=$scope.sldsequence;
		var sldsubtitle=$scope.sldsubtitle;
		//var slddescription=$scope.slddescriptionadd;
		//$scope.specdescription1 = CKEDITOR.instances.specdescriptionadd.getData();
		$scope.description = CKEDITOR.instances.slddescription.getData();
		var slddescription= $scope.description;//document.document.getElemen
		
		/*$scope.description = CKEDITOR.instances.slddescription.getData();
		var slddescription= $scope.description;*/
		
		var active1 = "";
		
		if(active == true)
		{
			active1 = "y";
		}
		else
		{
			active1 = "n";
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
		
		if(slidertitle==undefined || slidertitle=="")
		{
			slidertitle = "";
		}
		
		if($scope.sliderimage == "" && (image.files[0]==undefined || image.files[0]==""))
		{
			$window.alert("Please select image!");
			document.getElementById("image").focus();
			return;
		}
		else
		{
			$scope.spin = 1;
			$scope.ipaddress = "139.125.1.25";
			
			var tit = slidertitle.replace("&","$");
			var tit1 = tit.replace("#","~");
			var tit2 = tit1.replace("%","!");
			//var link = baseUrl+'addSlider?slidertitle='+tit2+'&active='+active1+'&ipaddress='+$scope.ipaddress+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&sldsequence='+sldsequence+'&sldsubtitle='+slsubtitle+'&slddescription='+sldescription;//+'&sldlink='+sllink;			
			
			//var link = baseUrl+'editMainSlider?sliderid='+sliderid+'&slidertitle='+tit2+'&active='+active1+'&sliderimage='+$scope.sliderimage+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh;
			//var link = baseUrl+'addSliderDetails?slidertitle='+tit2+'&active='+active1+'&ipaddress='+$scope.ipaddress+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&sldsequence='+sldsequence+'&sldsubtitle='+sldsubtitle+'&slddescription='+slddescription;//+'&sldlink='+sllink;
			//int sldsequence,String sldsubtitle,String slddescription
			//public  MainSlider(@RequestParam(value="imageedit", required=false) MultipartFile imageedit, int sliderid, String slidertitle, String active, String sliderimage,	int valuex, int valuey, int valuew, int valueh,int sldsequence,String sldsubtitle,String slddescription, HttpSession session, HttpServletRequest request) {//
			var link = baseUrl+'editMainSlider?sliderid='+sliderid+'&slidertitle='+tit2+'&active='+active1+'&sliderimage='+$scope.sliderimage+'&valuex='+valuex+'&valuey='+valuey+'&valuew='+valuew+'&valueh='+valueh+'&sldsequence='+sldsequence+'&sldsubtitle='+sldsubtitle+'&slddescription='+slddescription;//+'&sldlink='+sllink;;
			$window.alert(link);
			var formData=new FormData();
			formData.append("imageedit",imageedit.files[0]);
			$http({method: 'POST', url: link, headers: {'Content-Type': undefined}, data: formData, transformRequest: function(data, headersGetterFunction) { return data; }}).success(function(data, status, headers, config) {
				$scope.editslider = data;
				
				/*$http.post(link).success(function(data, status, headers, config) {
				$scope.editslider = data;*/
				$scope.spin = 0;
				$window.alert("Slider Updated Successfully...");
				$window.location.href = adminurl+'manage_slider_details';
			
			}).error(function(data, status, headers, config) {
				$scope.editslider = "Response Fail";
			});
		}
	}
	
	
	$scope.checkAll = function()
	{
		if ($scope.selectedAll)
		{
			$scope.selectedAll = false;
		}
		else
		{
            $scope.selectedAll = true;
        }
		angular.forEach($scope.getSliders, function (item)
		{
			item.selected = $scope.selectedAll;
		});
    }
	
	
	$scope.deleteSlider = function()
	{
		deleteSlider = $window.confirm('Are you sure you want to delete slider?');
		if(deleteSlider)
		{
		    angular.forEach($scope.getSliders,
		    		function(item)
		    		{
		    			if (item.selected)
		    			{
		    				var link = baseUrl+'deleteSlider?sliderid='+item.sliderId;
		    				$http['delete'](link).success(function(data, status, headers, config) {
		    					$scope.deleteslider = data;
		    				}).error(function(data, status, headers, config) {
		    					$scope.deleteslider = "Response Fail";
		    				});
		    			}
		    		});
		    $window.location.href = adminurl+'manage_slider_details';
		}
	}
	
	$scope.setActive = function(sliderid, active)
	{
		if(active == "y")
		{
			active = "n";
		}
		else if(active == "n")
		{
			active = "y";
		}
		
		var link = baseUrl+'setActiveOrInActiveSlider?sliderid='+sliderid+'&active='+active+'&ipaddress='+$scope.ipaddress;
		$http.post(link).success(
				function(data, status, headers, config)
				{
					$scope.setactiveorinactiveslider = data;
					$window.location.href = adminurl+'manage_slider_details';
				}).
				error(function(data, status, headers, config)
				{
					$scope.setactiveorinactiveslider = "Response Fail";
				});
	}
	
	
	
}]);
