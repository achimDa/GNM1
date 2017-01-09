'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:NotificariAutoritatiCtrl
 * @description
 * # NotificariAutoritatiCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('NotificariAutoritatiCtrl', ['$scope', '$routeParams', '$sessionStorage', 'HelpersService', '$http', '$resource','Go', '$location', '$window' , '$route',
	function($scope, $routeParams, $sessionStorage, HelpersService, $http,  $resource, Go, $location, $window, $route) {

    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.session = $sessionStorage;
    $scope.itemObj = {};

    $scope.session.title = 'Notificare autoritati';

    var options = {
        makeRequest: {
            method: 'GET',
            isArray: true
        }
    };

    var url_EmailBody = 'events/notification/' + $sessionStorage.editableRow.id;

    $scope.getEmailBody = $resource(url + url_EmailBody, null, options).get();

    $scope.getEmailBody.$promise.then(function(success) {
        $scope.itemObj = success;

       //  angular.forEach(files, function(value, index){
       //  	fdata.append("files", files[index]);
      	// });

        $scope.itemObj.emails = success.emails;


    }, errorCallback);

    var arrayToString = function(array) {

	    if (typeof array !== 'string') {
	    	return array.toString();
	    }
	    return array;
    } ;



    $scope.sendPost = function(itemObj) {
 
        var newObj = {
			'emails': arrayToString(itemObj.emails).split(","),
			'subject': itemObj.subject,
			'infoMessage': itemObj.infoMessage,
			'additionalMessage': itemObj.additionalMessage,
			'signature': itemObj.signature,
			'id' : itemObj.id
		};
        
        var url_cities = url + 'events/notification',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        options.makeRequest.method = 'POST';

        $scope.postCities = $resource(url_cities ,null,options).makeRequest(newObj);
        $scope.postCities.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'rezolutii-notificari'});
            },errorCallback); 
    };

  }]);
