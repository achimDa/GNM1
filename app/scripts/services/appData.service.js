'use strict';

/**
 * @ngdoc function
 * @name gnmApp.service:appData
 * @description
 * # appData
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .service('appData',[ '$log', function appData($log) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    return {
        editableRow : {}
    };
}]);

  
  