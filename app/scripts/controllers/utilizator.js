'use strict';

/**
 * @ngdoc function
 * @name gnm2App.controller:UtilizatorCtrl
 * @description
 * # UtilizatorCtrl
 * Controller of the gnm2App
 */
angular.module('gnmApp')

.controller('UtilizatorCtrl', ['$scope', '$resource', 'HelpersService', '$location', '$sessionStorage',
    function($scope, $resource, HelpersService, $location, $sessionStorage) {

        var url = HelpersService.domain,
            errorCallback = $scope.main.errorHandlerCollback;

        $scope.itemObj = {};
        // $scope.itemObj.roles = [];

        $scope.formDropdownData = {};

        var options = { makeRequest: { method: 'GET', isArray: true } };

        var url_Comisariat = 'comisariat',
            url_Rol = 'rol';

        $scope.getComisariat = $resource(url + url_Comisariat, null, options).get();
        $scope.getRol = $resource(url + url_Rol, null, options).get();

        $scope.getComisariat.$promise.then(function(success) {
            $scope.formDropdownData.comisariat = success.items;
        }, errorCallback);

        $scope.getRol.$promise.then(function(success) {
            $scope.formDropdownData.rol = success.items;
        }, errorCallback);


        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            $scope.itemObj = $sessionStorage.editableRow;

            var url_Utilizator = 'utilizator/' + $sessionStorage.editableRow.id;
            $scope.getUtilizator = $resource(url + url_Utilizator, null, options).get();
            $scope.getUtilizator.$promise.then(function(success) {
                console.log('successsuccesssuccesssuccess', success.item);

                $scope.formDropdownData.utilizator = success.item;
                $scope.itemObj.agencyId = success.item.agencyId;
                $scope.itemObj.roles = success.item.roles[0];

                console.log('=======================', $scope.itemObj);

            }, errorCallback);
        }

        $scope.sendPost = function(itemObj) {


            var newItemObj = {
                'firstName': $scope.itemObj.firstName,
                'lastName': $scope.itemObj.lastName,
                'username': $scope.itemObj.username,
                'telephone': $scope.itemObj.telephone,
                'email': $scope.itemObj.email,
                'createdOn': $scope.itemObj.createdOn,
                'password': $scope.itemObj.password,

                'agency': {
                    'id': $scope.itemObj.agency.id
                },

                'roles': [{
                    'nume': $scope.itemObj.roles.nume
                }]
            };

            var url_tara = url + 'utilizator',
                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

            if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
                newItemObj.id = $sessionStorage.editableRow.id;
                options.makeRequest.method = 'PUT';

            } else {
                options.makeRequest.method = 'POST';
            }

            $scope.postJudet = $resource(url_tara, null, options).makeRequest(newItemObj);
            $scope.postJudet.$promise.then(function(success) {
                $scope.main.alerts.push({ type: 'success', duration: 2000, msg: 'Modificarile au fost salvate.' });
                // Go.to({url:'utilizatori'});
            }, errorCallback);

        };

    }
]);
