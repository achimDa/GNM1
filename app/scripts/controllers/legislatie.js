'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:LegislatieCtrl
 * @description
 * # LegislatieCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('LegislatieCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
     
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.formDropdownData = [];   

    $scope.itemObj = {};
    $scope.itemObj.enforcementStartDate = '';

    var options = {
        makeRequest: {
            method: 'GET',
            isArray: false
        }
    };

    $scope.itemObj = {
        enforcementStartDate: null,
        enforcementEndDate: null,
        show: false
    };


    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        // $scope.itemObj = $sessionStorage.editableRow;

        var url_reglementations_types = 'legislations/' + $sessionStorage.editableRow.id;

        $scope.getReglementation = $resource(url + url_reglementations_types ,null,options).makeRequest();
        $scope.getReglementation.$promise.then(function(success) {
            console.log(success);
            // $scope.formDropdownData.legislation = success.type;   
            $scope.itemObj = success;   
        }, errorCallback);
    }
  
    var url_legislation_types = 'legislationtypes';

    $scope.getLegislationTypes = $resource(url + url_legislation_types ,null,options).makeRequest();
    $scope.getLegislationTypes.$promise.then(function(success) {
        $scope.formDropdownData.legislation = success.items;   
    }, errorCallback);

    $scope.sendPost = function(itemObj) {
 
        var newObj = {
            
            'number' : $scope.itemObj.number,
            'enforcementStartDate' : $scope.itemObj.enforcementStartDate ? $scope.itemObj.enforcementStartDate : null,
            'enforcementEndDate' : $scope.itemObj.enforcementEndDate ? $scope.itemObj.enforcementEndDate : null,
            'content' : $scope.itemObj.content,
            'type' : {
                'id' : $scope.itemObj.type.id
            }
        };

        var url_legislation = url + 'legislations',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_legislation = url + 'legislations/' + $scope.itemObj.id;
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
