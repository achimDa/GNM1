'use strict';

/**
 * @ngdoc function
 * @name gnmApp.service:HelpersService
 * @description
 * # HelpersService
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .service('HelpersService',[ '$log', function HelpersService($log) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    return {
        // domain: 'http://216.66.81.48:8080/gnm1/',
        
        // domain: 'http://192.168.10.82:8080/GardaNationalaDeMediu/',

        domain: 'http://46.101.129.94:8080/gnm1/',

        //domain: '/GardaNationalaDeMediu/',
        //domain: 'http://localhost:8080/',
        
        // domain: 'http://192.168.10.38:8080/',
        // domain: 'http://192.168.10.38:8080/',
        // domain: 'http://192.168.10.19:8080/',
        // prefix: 'gnm1/',
        innerHeight: window.innerHeight,
        isDev: false,
        innerWidth: window.innerWidth,
        elemHeight: function(elem) {
            return jQuery(elem).outerHeight();
        },
        setElemMinHeight: function(setableElement, deductor) {
            jQuery(setableElement).css('min-height', this.innerHeight - deductor);
        }
    };
}]);