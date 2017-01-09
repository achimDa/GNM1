'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:EditareFormeJuridiceCtrl
 * @description
 * # EditareFormeJuridiceCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('EditareFormeJuridiceCtrl', [ '$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  	function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
    
    var url = HelpersService.domain,
    	errorCallback = $scope.main.errorHandlerCollback;

 	$scope.itemObj = {};
    
    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }

	$scope.sendPost = function(itemObj) {
	    var url_forme_juridice = url + 'legalentitytypes',
				options = {
					makeRequest: {
						isArray: false
					}
				};

			if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
				url_forme_juridice = url + 'legalentitytypes/' + $scope.itemObj.id;
				options.makeRequest.method = 'PUT';
			} else {
				options.makeRequest.method = 'POST';
			}

	        $scope.postFormeJuridice = $resource(url_forme_juridice ,null,options).makeRequest(itemObj);
	        $scope.postFormeJuridice.$promise.then(
	            function(success) {
	              	$scope.main.successHandlerCollback(success);
					Go.to({url:'administrare'});

	            },errorCallback); 
    };


  }]);
