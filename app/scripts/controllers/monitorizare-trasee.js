'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:MonitorizareTraseeCtrl
 * @description
 * # MonitorizareTraseeCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('MonitorizareTraseeCtrl', ['$scope', '$routeParams', '$resource', 'HelpersService', '$location', '$sessionStorage', 'Go',
      function($scope, $routeParams ,$resource, HelpersService, $location, $sessionStorage, Go) {

        var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

        $scope.session = $sessionStorage;
        $scope.formDropdownData = {};
        $scope.itemObj = {};

        $scope.session.title = 'Monitorizare trasee terminale mobile';

        $scope.itemObj = {
            startDate: null,
            endDate: null,
            show: false
        };

        var optionsGet = {
            makeRequest: {
                method: 'GET',
                isArray: true
            }
        };

        var url_Administrators = 'organisations/all/administrators';

        $scope.getAdministrators = $resource(url + url_Administrators, null, optionsGet).get();

        $scope.getAdministrators.$promise.then(function(success) {
            $scope.formDropdownData.commissariats = success.items;
        }, errorCallback);

        $scope.tomorrow = new Date();
        $scope.tomorrow.setDate($scope.tomorrow.getDate() + 10);
        
        $scope.protectedArea = function(itemObj) {
            
            if (itemObj.protectedAreaAdministratorId !== null) {
                var url_protectedArea =  'protectedareas/custody/' + itemObj.protectedAreaAdministratorId.id;
                
                $scope.getArea = $resource(url + url_protectedArea, null, optionsGet).get();
                
                $scope.getArea.$promise.then(function(success) {
                    $scope.formDropdownData.area = success.items;
                }, errorCallback);
            } else {
                delete $scope.formDropdownData.area;
                delete $scope.formDropdownData.ranger;
            }
        };

        $scope.dropRangers = function(itemObj) {

            if (itemObj.protectedAreaId !== null) {
                var url_protectedArea =  'accounts/permissions/protectedareas/' + itemObj.protectedAreaId.id;
                
                $scope.getArea = $resource(url + url_protectedArea, null, optionsGet).get();
                
                $scope.getArea.$promise.then(function(success) {
                    $scope.formDropdownData.ranger = success.items;
                }, errorCallback);
            } else {
                delete $scope.formDropdownData.ranger;
            }       
        };

        $scope.sendPost = function(itemObj) {
 
            var newObj = {
              'protectedAreaAdministratorId': ($scope.itemObj.protectedAreaAdministratorId && $scope.itemObj.protectedAreaAdministratorId.id) ? $scope.itemObj.protectedAreaAdministratorId.id : null,
              'protectedAreaId': ($scope.itemObj.protectedAreaId && $scope.itemObj.protectedAreaId.id) ? $scope.itemObj.protectedAreaId.id : null,
              'accountId': ($scope.itemObj.accountId && $scope.itemObj.accountId.id) ? $scope.itemObj.accountId.id : null,
              'startDate' : itemObj.startDate,
              'endDate' : itemObj.endDate,
            };
            
            var url_trackroute = url + 'trackroute/search',
                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

            options.makeRequest.method = 'POST';

            $scope.postTrackroute = $resource(url_trackroute ,null,options).makeRequest(newObj);
            $scope.postTrackroute.$promise.then(
                function(success) {
                    console.log(success);

                    L.Icon.Default.imagePath = 'bower_components/leaflet/dist/images';

                    if (success.geoJson.features !== null) {
                        angular.extend($scope.layers.overlays, {
                            trasee: {
                                name:'Monitorizare trasee',
                                doRefresh: true,
                                type: 'geoJSONShape',
                                data: success.geoJson,
                                visible: true,
                                layerOptions: {
                                    style: {
                                            color: '#00D',
                                            fillColor: 'green',
                                            weight: 2.0,
                                            opacity: 0.9,
                                            fillOpacity: 0.3,
                                    }
                                }
                            }
                        });

                        // $scope.markers = success

                    } else {
                      angular.extend($scope.layers.overlays, {
                            trasee: {
                                name:'Monitorizare trasee',
                                doRefresh: true,
                                type: 'geoJSONShape',
                                data: '',
                                visible: true,
                                layerOptions: {
                                    style: {
                                            color: '#00D',
                                            fillColor: 'green',
                                            weight: 2.0,
                                            opacity: 0.9,
                                            fillOpacity: 0.3,
                                    }
                                }
                            }
                        });
                    }

                },errorCallback); 
        };
  }]);
