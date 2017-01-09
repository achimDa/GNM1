'use strict';

/**
 * @ngdoc function
 * @name gnm2App.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the gnm2App
 */
angular.module('gnmApp')
  .controller('LoginCtrl',  ['$scope','$location', 'HelpersService', '$resource','$localStorage','$sessionStorage',
    function ($scope,$location,HelpersService,$resource,$localStorage,$sessionStorage) {
    
    $scope.login = {};

    $scope.main.session.user = '';
    $sessionStorage.$reset();
    $localStorage.$reset();

    $scope.session = $sessionStorage;

    $scope.login.alerts = [];
    $scope.login.closeAlert = function (list, index){
        list.splice(index, 1);
    };

    var doFakeLogin = function (role) {
               
        $sessionStorage.isLoggedIn = true;
        $localStorage.isLoggedIn = true;
        $location.path('/dashboard');
       
        $sessionStorage.user = {
            firstName : 'Admin',
            lastName : 'Test',
            username : 'admin@gnm.ro',
            authority : role
        };

        if (!$sessionStorage.user.authority) {
            $sessionStorage.user.authority = 'ROLE_ADMIN';
        }

    };        

    $scope.login = function(formData, generalForm) {
        if (generalForm.$valid) {
            // fake loghin due to CORS issues the call dosent work
                // doFakeLogin(formData.password);
                // // return;
            // end facke

            // Real Login
            var url_login = HelpersService.domain + 'login',
                actions = {
                    login: {
                        isArray: false,
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                            'X-Login-Ajax-call': true
                        }
                    }
                },
                data = $.param({
                    username : formData.username,
                    password : formData.password
                });

            $scope.postLogin = $resource(url_login ,null,actions).login(data);
            $scope.postLogin.$promise.then(function(response) {
                if (response.result === 'success') {
                    $sessionStorage.isLoggedIn = true;
                    $localStorage.isLoggedIn = true;
                    $location.path('/dashboard');
                    $sessionStorage.user = response.user;
                } else if (response.result === 'failure') {
                   $scope.loginerror = 'Procedura de login a esuat.';
                }
            }
            // , $scope.main.errorHandlerCollback
            );
        }
    };

        

}]);