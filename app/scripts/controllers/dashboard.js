'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('DashboardCtrl', function ($scope) {
    $scope.session.title = '';
    delete $scope.session.addLink;

    $scope.footerIsVisible = {};
    $scope.footerIsVisible = true;
  });
