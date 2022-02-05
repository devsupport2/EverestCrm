var app = angular.module("MyApp", ['angular.filter']);
var url = "/everest/";
var adminurl = "/everest/";
function tools_replaceAll(str, find, replace) {
   return str.replace(new RegExp(find, 'g'), replace);
}

app.filter('startFrom', function() {
	return function(input, start) {
		start = +start; // parse to int
		return input.slice(start);
	}
});

app.directive('capitalize', function() {
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, modelCtrl) {
			var capitalize = function(inputValue) {
				if (inputValue == undefined) inputValue = '';
				var capitalized = inputValue.toUpperCase();
				if (capitalized !== inputValue) {
					modelCtrl.$setViewValue(capitalized);
					modelCtrl.$render();
				}
				return capitalized;
			}
			modelCtrl.$parsers.push(capitalize);
			capitalize(scope[attrs.ngModel]); 
		}
	};
});

app.filter('limitHtml', function() {
    return function(text, limit) {
        var changedString = String(text).replace(/<[^>]+>/gm, '');
        var length = changedString.length;
        return changedString.length > limit ? changedString.substr(0, limit - 1) : changedString; 
    }
})

/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	};
}]);
	/* For Print data with html tag end */

