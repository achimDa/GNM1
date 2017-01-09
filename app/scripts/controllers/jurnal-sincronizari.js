'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:JurnalSincronizariCtrl
 * @description
 * # JurnalSincronizariCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('JurnalSincronizariCtrl', ['$scope', '$routeParams', '$resource', 'HelpersService', '$location', '$sessionStorage', 'Go',
      function($scope, $routeParams ,$resource, HelpersService, $location, $sessionStorage, Go) {

        var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

        $scope.session = $sessionStorage;
        $scope.formDropdownData = {};
        $scope.itemObj = {};

        $scope.session.title = 'Jurnal sincronizari terminale mobile';

        $scope.itemObj = {
            startDate: null,
            endDate: null,
            show: false
        };

        var optionsGet = {
            makeRequest: {
                method: 'GET',
                isArray: true
            }
        };

        var getSyncro = function() {
            if (!$sessionStorage.editableRow) {
                return;
            }
            $scope.getSyncroSession = $resource(url + 'synchronizations/' + $sessionStorage.editableRow.id , {}, {
                makeRequest: {
                    method: 'GET',
                    isArray: false
                }
            }).makeRequest();

            $scope.getSyncroSession.$promise.then(function(success) {
                $scope.formDropdownData.headers = success.headers;
                $scope.formDropdownData.syncrosession = success.items;
                // $scope.markRowContactsMedia(success.items[0]);
                // $scope.items = $sessionStorage.editableRow;
            }, errorCallback);
        };

        var url_Administrators = 'organisations/all/administrators';

        $scope.getAdministrators = $resource(url + url_Administrators, null, optionsGet).get();

        $scope.getAdministrators.$promise.then(function(success) {
            $scope.formDropdownData.administrator = success.items;

            $scope.dropRangers = function(itemObj) {
                
                var url_Rangers =  'accounts/organisations/' + itemObj.protectedAreaAdministratorId.id + '/rangers';
                
                $scope.getRangers = $resource(url + url_Rangers, null, optionsGet).get();
                
                $scope.getRangers.$promise.then(function(success) {
                    $scope.formDropdownData.ranger = success.items;
                    console.log(success.items);
                }, errorCallback);

            };

        }, errorCallback);

        $scope.sendPost = function(itemObj) {
 
            var newObj = {

              'protectedAreaAdministratorId': ($scope.itemObj.protectedAreaAdministratorId && $scope.itemObj.protectedAreaAdministratorId.id) ? $scope.itemObj.protectedAreaAdministratorId.id : null,
              'rangerId': ($scope.itemObj.rangerId && $scope.itemObj.rangerId.id) ? $scope.itemObj.rangerId.id : null,
              
              'startDate' : itemObj.startDate,
              'endDate' : itemObj.endDate,
              'synchronizationState' : itemObj.synchronizationState !== "" ? itemObj.synchronizationState : null 
            };
            
            var url_cities = url + 'synchronizations/search',
                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

            options.makeRequest.method = 'POST';

            $scope.postCities = $resource(url_cities ,null,options).makeRequest(newObj);
            $scope.postCities.$promise.then(
                function(success) {
                    console.log(success.items);

                    $scope.formDropdownData.syncro = success.items;

                    $scope.markRow(success.items[0]);

                    // $scope.main.successHandlerCollback(success);

                },errorCallback); 
        };

        $scope.markRow = function(items) {
            $scope.editableRow = items;
            $sessionStorage.editableRow = $scope.editableRow;

            getSyncro();

            if ($routeParams.edit === 'true') {
                $scope.items = $sessionStorage.editableRow;
                console.log("items", items);
            }

        };


  }]);
