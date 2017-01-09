'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:DimensiuniRegnulAnimaliaCtrl
 * @description
 * # DimensiuniRegnulAnimaliaCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  	.controller('DimensiuniRegnulAnimaliaCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  	function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
     
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.itemObj = {};

	if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }
    
	$scope.sendPost = function(itemObj) {
    	var url_dim = url + 'animalsizes',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_dim = url + 'animalsizes/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postDim = $resource(url_dim ,null,options).makeRequest(itemObj);
        $scope.postDim.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});
                console.log(itemObj);

            },errorCallback); 
	};

  }]);