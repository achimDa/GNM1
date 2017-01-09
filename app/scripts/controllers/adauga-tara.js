'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AdaugaTaraCtrl
 * @description
 * # AdaugaTaraCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
.controller('AdaugaTaraCtrl', ['$scope', 'Upload', '$timeout', '$resource','$location', '$sessionStorage', 'HelpersService', 'Go' ,'leafletData', '$interval', 'leafletBoundsHelpers',
function ($scope, Upload, $timeout, $resource, $location, $sessionStorage, HelpersService, Go, leafletData, $interval, leafletBoundsHelpers) {

    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    // delete $sessionStorage.addLink.label;
    // $scope.session.title = 'Tara';
    
    $scope.itemObj = {};

    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }

    // $scope.sendPost = function(itemObj) {
 
    //     var resourceUrl = url + 'countries';

    //     var newObj = {

    //     }

    //     resourceUrl += $scope.bIsEdit ? "/" + $scope.itemObj.id : "";
    //     // itemObj.id = "2";

    //     $scope.items = $resource(resourceUrl, {}, {
    //         makeRequest: {
    //             method: $scope.bIsEdit ? 'PUT' : 'POST',
    //             isArray: false
    //         }
    //     }).makeRequest(itemObj);

    //     $scope.items.$promise.then(
    //         function(success) {
                
    //             $scope.main.successHandlerCollback(success);
    //             Go.to({url:'administrare'});

    //         },errorCallback); 
    // };

    $scope.sendPost = function(itemObj) {
 
        var newObj = {
            'name' : $scope.itemObj.name,
            'initials' : $scope.itemObj.initials,
          };
        
        var url_element = url + 'countries',
              options = {
                  makeRequest: {
                      isArray: false
                  }
              };

          if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
              url_element = url + 'countries/' + $scope.itemObj.id;
              options.makeRequest.method = 'PUT';
          } else {
              options.makeRequest.method = 'POST';
          }

          $scope.postElement = $resource(url_element ,null,options).makeRequest(newObj);
          $scope.postElement.$promise.then(
              function(success) {
                    $scope.main.successHandlerCollback(success);
                    Go.to({url:'administrare'});
              },errorCallback); 
    };

}]);
