'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:TipuriAriiProtejateCtrl
 * @description
 * # TipuriAriiProtejateCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('TipuriAriiProtejateCtrl', [ '$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  	function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
    
    var url = HelpersService.domain,
    	errorCallback = $scope.main.errorHandlerCollback;
 	
 	$scope.itemObj = {};
    
    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }

	$scope.sendPost = function(itemObj) {
	    var url_tara = url + 'protectedareatypes',
				options = {
					makeRequest: {
						isArray: false
					}
				};

		if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
			url_tara = url + 'protectedareatypes/' + $scope.itemObj.id;
			options.makeRequest.method = 'PUT';
		} else {
			options.makeRequest.method = 'POST';
		}

        $scope.postTara = $resource(url_tara ,null,options).makeRequest(itemObj);
        $scope.postTara.$promise.then(
            function(success) {
              	$scope.main.successHandlerCollback(success);
				Go.to({url:'administrare'});
            },errorCallback); 
    };
}]);

