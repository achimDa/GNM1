'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:HistoryEventsCtrl
 * @description
 * # HistoryEventsCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('HistoryEventsCtrl',  ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go', '$timeout' ,
    function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go, $timeout) {
    
        var url = HelpersService.domain,
            errorCallback = $scope.main.errorHandlerCollback;

        $scope.session = $sessionStorage;
        $scope.formDropdownData = {};
        $scope.markers = {};
        $scope.session.title = 'Istoric evenimente notificate';

        var options = {
            makeRequest: {
                method: 'GET',
                isArray: false
            }
        };

        var url_history = 'events/history';
        
        $scope.getHistoryEvents = $resource(url + url_history, null, options).get();           
     
        $scope.getHistoryEvents.$promise.then(function(success) {
            $scope.formDropdownData.history = success.items;
            $scope.markEvent(success.items[0]);
        }, errorCallback);


        $scope.markEvent = function(items) {
            $scope.editableRow = items;
            $sessionStorage.editableRow = $scope.editableRow;

            console.log($sessionStorage.editableRow);

            // var mainMarker = {
            //       lat: $sessionStorage.editableRow.latitude,
            //       lng: $sessionStorage.editableRow.longitude,
            //       focus: true,
            //       message: '<a href="#/add-event?edit=true" class="popUpButton">' +  $sessionStorage.editableRow.eventCategory + '<br>' + $sessionStorage.editableRow.description + '<br>' + $sessionStorage.editableRow.eventDate + '</a>',
            //       draggable: false
            // };


            // $scope.markers = mainMarker;

            angular.extend($scope, {
                markers: {
                    pin: {
                        lat: $sessionStorage.editableRow.latitude,
                        lng: $sessionStorage.editableRow.longitude,
                        focus: true,
                        draggable: false,
                        message: '<a href="#/add-event?edit=true" class="popUpButton">' +  $sessionStorage.editableRow.eventCategoryName + '<br>' + 'Notificat la: ' + $sessionStorage.editableRow.notificationDate + '<br>' + 'Organizatie: ' + $sessionStorage.editableRow.organisationNotificaton + '</a>',
                        icon: {}
                    }
                }
            });

            // $scope.$on("leafletDirectiveMarker.dragend", function(event, args) {
            //     $scope.markers.pin.lat = args.model.lat;
            //     $scope.markers.pin.lng = args.model.lng;
            // });
        };

  }]);
