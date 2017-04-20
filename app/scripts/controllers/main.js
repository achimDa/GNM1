'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the gnmApp
 */

angular.module('gnmApp')
  .controller('MainCtrl', ['HelpersService', '$timeout', '$scope', '$location', '$resource', '$sessionStorage', '$window', '$log','Go',
    function (HelpersService, $timeout, $scope, $location, $resource, $sessionStorage, $window, $log, Go) {
	
	  // $scope.Page = Page;

    $scope.main = {};
    $scope.session = $sessionStorage;
    // $scope.session.title = '';
    // delete $sessionStorage.editableCategoriesEvent;

    $scope.back = function() {
      $window.history.back();
    };
    
    $scope.main.go = function(url) {
      Go.to(url);
    };

    $scope.main.user = $sessionStorage.user;

    $scope.main.alerts = [];
    // ex: $scope.main.alerts.push({ type: 'danger', duration : 1000, msg: 'Oh snap! Change a few things up and try submitting again.' });
    $scope.main.defaultMessages = {};
    $scope.main.defaultMessages.success = 'Editarea a avut success';
    $scope.main.defaultMessages.error = 'A aparut o eroare';
    $scope.main.messages = {};
    $scope.main.closeAlert = function(index) {
      $scope.main.alerts.splice(index, 1);
    };

    $scope.main.session = $sessionStorage;

    // $scope.reloadW = function() {
    //   location.reload();
    // };

  // Create a general error hendler
    $scope.main.errorHandlerCollback = function(error) {
        $scope.main.alerts.push(
          // { type: 'danger', duration : 10000, msg: $scope.main.defaultMessages.error }
          { type: 'danger', duration : 10000, msg: $scope.main.defaultMessages.error + ': ' + JSON.stringify(error.data.message) }
        );
        $log.warn($scope.main.defaultMessages.error + ': ' + error);

        if (error.status === 401) {
          $location.path('/login');
        }
    };

  // Create a general success hendler
    $scope.main.successHandlerCollback = function(success) {
        if (!success) {
          success = 'success';
        }
        
        $scope.main.alerts.push(
          { type: 'success', duration : 3000, msg: $scope.main.defaultMessages.success}
        );
    };
    $scope.main.hasToRender = function(list) {
        var hasAccess = false;
        angular.forEach(list, function(each){
          if (each === $sessionStorage.user.authority) {
            hasAccess = true;
          }
        });
        return hasAccess;
    };

    
    $timeout(function() {
      var deductor = jQuery('#footer').outerHeight() + jQuery('#header').outerHeight() + 60;
      HelpersService.setElemMinHeight('#main-container', deductor);

    }, 750);

  	// Setup Map for event Page
    angular.extend($scope, {
      center: {
          lat: 45.81348,
          lng: 24.54345,
          // autoDiscover: true,
          zoom: 11
      },  
      defaults: {
          scrollWheelZoom: false
      },
      markers: {},
      layers: {
          baselayers: {
              osm: {
                name: 'OpenStreetMap',
                url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                type: 'xyz'
              },
          },
          doRefresh: true,
          overlays:{}
      }
    });

    // SORTING 
  	$scope.sort = {
        column: 'denumire',
        descending: false
    };

  	$scope.changeSorting = function(column) {
        var sort = $scope.sort;
        if (sort.column === column) {
            sort.descending = !sort.descending;
        } else {
            sort.column = column;
            sort.descending = false;
        }
    };

    $scope.logout = function () {
      // $sessionStorage.$reset();
      var url_logout = HelpersService.domain + 'logout',
      actions = {
          makeRequest: {
              isArray: false,
              method: 'POST',
              headers: {
                  'Content-Type': 'application/x-www-form-urlencoded',
                  'X-Login-Ajax-call': true
              }
          }
      };

      $scope.postLogin = $resource(url_logout ,null,actions).makeRequest();

      $scope.postLogin.$promise.then(
          function(success) {
            console.log('success');
            $sessionStorage.isLoggedIn = false;
            $location.path('/login');
          },
          function(error) {
            console.log('success');
          }
      );
    };

    $scope.footerScope = {};
    $scope.footerScope.showFooter = false;
    $scope.$on('$routeChangeSuccess', function(next, current) { 
          var routeWithNoFooter = ['/', '/dashboard', '/login'];
      if (-1 !== routeWithNoFooter.indexOf($location.path())) {
        $scope.footerScope.showFooter = true;
      } else {
        $scope.footerScope.showFooter = false;
      }
    });

  }])

.config(function($datepickerProvider) {
  angular.extend($datepickerProvider.defaults, {
    dateFormat: 'dd-MM-yyyy',
    startWeek: 1
  });
});



    // $scope.footerIsVisible = true;