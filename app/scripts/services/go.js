'use strict';

/**
 * @ngdoc service
 * @name gnmApp.go
 * @description
 * # go
 * Service in the gnmApp.
 */
angular.module('gnmApp')
  .service('Go',[ '$sessionStorage', '$location', function ($sessionStorage, $location) {

  	/* Usage */
    //Go.to({url:'', tab:''});
    return {
      	to : function (config) {
      		config = config || {};
      		if (!config.url) {
      			config.url = '/';
      		}
      		if (!config.tab) {
      			config.tab = 1;
      		}
  			$sessionStorage.activeTab = config.tab;
      		$location.url(config.url);
       	}
    };
}]);




