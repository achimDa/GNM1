'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:SetariCtrl
 * @description
 * # SetariCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp').controller('SetariCtrl', ['$scope','getsTableData', 'HelpersService', 'Navigator','$location', '$sessionStorage' , 'appData', '$resource',
function ($scope,getsTableData, HelpersService, Navigator, $location, $sessionStorage, appData, $resource) {
  	var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.navigatorConfig = Navigator.links;

  }]);
