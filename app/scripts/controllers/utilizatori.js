'use strict';

/**
 * @ngdoc function
 * @name gnm2App.controller:UtilizatoriCtrl
 * @description
 * # UtilizatoriCtrl
 * Controller of the gnm2App
 */
angular.module('gnmApp')
    .controller('UtilizatoriCtrl', ['$scope', '$resource', 'HelpersService', '$sessionStorage', '$location',
        function($scope, $resource, HelpersService, $sessionStorage, $location) {

            var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            $scope.session = $sessionStorage;
            $scope.session.title = 'Utilizatori';
            $scope.itemObj = {};

            if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
                $scope.itemObj = $sessionStorage.editableRow;
                
            }

            // POPULATE DEFAULT TABLE
            $scope.tabelData = [];

            $scope.items = $resource(url + 'accounts/licenses', {}, {
                makeRequest: {
                    method: 'GET',
                    isArray: false
                }
            }).makeRequest();
            $scope.items.$promise.then(function(success) {
                $scope.tabelData = success;
                $scope.markRow(success.items[0]);
                $scope.sortType = 'name'; // set the default sort type
                $scope.sortReverse = false; // set the default sort order
                $scope.searchFish = ''; // set the default search/filter term
                delete $sessionStorage.editableLicenses;
            // }, errorCallback);
            });

            $scope.markRow = function(items) {
                $scope.editableRow = items;
                $sessionStorage.editableRow = $scope.editableRow;
                console.log($sessionStorage.editableRow);

                // | editableRow.accountId

                if ($sessionStorage.editableRow.isUsed === false) {
                    $scope.existUtil = true;
                    $scope.utilNew = true;
                    $scope.resetPassword = false;
                } else if ($sessionStorage.editableRow.isUsed === true) {
                    $scope.existUtil = false;
                    $scope.utilNew = false;
                    $scope.resetPassword = true;
                }
            };

            $scope.activeRow = function(items) {
                $scope.editableRow.active = true;

                $scope.items = $resource(url + 'accounts/' + $sessionStorage.editableRow.accountId + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableRow);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.deactiveRow = function(items) {

                $scope.editableRow.active = false;

                $scope.items = $resource(url + 'accounts/' + $sessionStorage.editableRow.accountId + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableRow);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };



        }
])

.filter('YesNo', function(){
    return function(text){
      return text ? "Da" : "Nu";
    };
})

.filter('MaleFemale', function(){
    return function(text){
      return text ? "Masculin" : "Feminin";
    };
});
