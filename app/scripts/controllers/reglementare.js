'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:ReglementareCtrl
 * @description
 * # ReglementareCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('ReglementareCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage', 'Go',
  function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {

    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.formDropdownData = [];   

    $scope.itemObj = {};

    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }

    var options = {
            makeRequest: {
                method: 'GET',
                isArray: false
            }
        };

    var url_reglementations_types = 'regulationtypes';

    $scope.getReglementation = $resource(url + url_reglementations_types ,null,options).makeRequest();
    $scope.getReglementation.$promise.then(function(success) {
        $scope.formDropdownData.regulationtypes = success.items;   
    }, errorCallback);

    $scope.sendPost = function(itemObj) {
 
        var newObj = {
            
            'number' : $scope.itemObj.number,
            'title' : $scope.itemObj.title,
            'content' : $scope.itemObj.content,
            'type' : {
                'id' : $scope.itemObj.type.id
            }
        };

        var url_legislation = url + 'regulations',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_legislation = url + 'regulations/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postLegislations = $resource(url_legislation ,null,options).makeRequest(newObj);
        $scope.postLegislations.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});

            },errorCallback); 
    };

  }]);