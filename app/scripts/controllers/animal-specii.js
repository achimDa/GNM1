'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AnimalSpeciiCtrl
 * @description
 * # AnimalSpeciiCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
	.controller('AnimalSpeciiCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  	function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
     
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.itemObj = {};

	if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }
    
	$scope.sendPost = function(itemObj) {
    	var url_species = url + 'animalspecies',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_species = url + 'animalspecies/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postSpecies = $resource(url_species ,null,options).makeRequest(itemObj);
        $scope.postSpecies.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});
                console.log(itemObj);

            },errorCallback); 
	};

  }]);