'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:SchimbaParolaCtrl
 * @description
 * # SchimbaParolaCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('SchimbaParolaCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage','Go' ,
  	function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
    
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;
 	
 	$scope.itemObj = {};
    
    $scope.sendPost = function(itemObj) {

    	var passwordChange = {
    		'oldPassword' : itemObj.oldPassword,
    		'newPassword' : itemObj.newPassword
    	};

        var url_changePassowrd = url + 'accounts/changepassword',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        options.makeRequest.method = 'PUT';

        $scope.putNewPassword = $resource(url_changePassowrd ,null,options).makeRequest(passwordChange);
        $scope.putNewPassword.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'dashboard'});

            },errorCallback); 
    };

  }]);
