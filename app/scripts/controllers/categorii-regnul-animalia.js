'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:CategoriiRegnulAnimaliaCtrl
 * @description
 * # CategoriiRegnulAnimaliaCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('CategoriiRegnulAnimaliaCtrl',['$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
     
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.itemObj = {};

	if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }
    
	$scope.sendPost = function(itemObj) {
    	var url_cat_animalia = url + 'animalcategories',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_cat_animalia = url + 'animalcategories/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postAnimalia = $resource(url_cat_animalia ,null,options).makeRequest(itemObj);
        $scope.postAnimalia.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});
                console.log(itemObj);

            },errorCallback); 
	};

  }]);