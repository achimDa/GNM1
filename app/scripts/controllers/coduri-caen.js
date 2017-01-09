'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:CoduriCaenCtrl
 * @description
 * # CoduriCaenCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('CoduriCaenCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage','Go' ,
  	function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
    
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;
 	
 	$scope.itemObj = {};
    
    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }

    $scope.sendPost = function(itemObj) {

        var url_cod_caen = url + 'caencodes',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
        	url_cod_caen = url + 'caencodes/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postCodCaen = $resource(url_cod_caen ,null,options).makeRequest(itemObj);
        $scope.postCodCaen.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});

            },errorCallback); 
    };

  }]);
