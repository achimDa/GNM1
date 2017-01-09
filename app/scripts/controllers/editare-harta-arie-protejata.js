'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:EditareHartaArieProtejataCtrl
 * @description
 * # EditareHartaArieProtejataCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('EditareHartaArieProtejataCtrl',  ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go', '$timeout' ,
    function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go, $timeout) {

        var url = HelpersService.domain,
            errorCallback = $scope.main.errorHandlerCollback;

        // EXTEND API 

        $scope.session = $sessionStorage;
        $scope.itemObj = {};
        $scope.itemCore = {};
        $scope.formDropdownData = {};

        if ($location.search().edit !== 'true') {
            delete $sessionStorage.editableRow;
        }

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            var options = {
                makeRequest: {
                    method: 'GET',
                    isArray: false
                }
            };

            var url_MetadataId = 'protectedareas/maps/' + $sessionStorage.editableRow.id + '/columnames';
            var url_Maps = 'protectedareas/maps/' + $sessionStorage.editableRow.id;
            var url_MetaMaps = 'protectedareas/maps/' + $sessionStorage.editableRow.id + '/metadata';
            var url_Core = 'protectedareas/maps/' + $sessionStorage.editableRow.id + '/corelation';
            
            $scope.getMetadataId = $resource(url + url_MetadataId, null, options).get();           
            $scope.getMaps = $resource(url + url_Maps, null, options).get();
            $scope.getMetaMaps = $resource(url + url_MetaMaps, null, options).get();
            $scope.getCore = $resource(url + url_Core, null, options).get();

         
            $scope.getMetadataId.$promise.then(function(success) {
                $scope.formDropdownData.metadataid = success.items;
            }, errorCallback);

            $scope.getMaps.$promise.then(function(success) {
                $scope.itemObj = success;
            }, errorCallback);

            $scope.getMetaMaps.$promise.then(function(success) {

                $scope.formDropdownData.header = success.headers;
                $scope.formDropdownData.metamaps = success.items;

            }, errorCallback);

            $scope.getCore.$promise.then(function(success) {
                $scope.itemCore = success;
            }, errorCallback);
            
        }

        $scope.corelare = function(itemCore) {

            var core = {
                'idColumn': itemCore.idColumn,
                'nameColumn': itemCore.nameColumn,
                'geometryColumn': itemCore.geometryColumn
            };

            var url_core = url + 'protectedareas/maps/' + $sessionStorage.editableRow.id + '/corelation';
            var options = {
                makeRequest: {
                    isArray: false
                }
            };

            options.makeRequest.method = 'PUT';

            $scope.postEvent = $resource(url_core ,null,options).makeRequest(core);
            $scope.postEvent.$promise.then(
                function(success) {
                    console.log(success);
                },errorCallback);
        };

        $scope.sendFile = function(files) {

            var fdata = new FormData();
            angular.forEach(files, function(value, index){
                fdata.append("map", files[index]);
            });

            $scope.fdata = fdata;
            $scope.files = files;
            console.log($scope.fdata);
            console.log($scope.files);
        };

        $scope.sendPost = function(itemObj) {

            var NewitemObj;

            NewitemObj = {
                'name': itemObj.name,
                'description': itemObj.description,
            };

            var url_event = url + 'protectedareas/maps';
            var options = {
                makeRequest: {
                    isArray: false
                }
            };

            if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
                url_event = url + 'protectedareas/maps/' + $sessionStorage.editableRow.id;
                options.makeRequest.method = 'PUT';
                NewitemObj = {
                    'name': itemObj.name,
                    'description': itemObj.description,
                    'id': itemObj.id
                };

            } else {
                options.makeRequest.method = 'POST';
            }

            $scope.postEvent = $resource(url_event ,null,options).makeRequest(NewitemObj);
            $scope.postEvent.$promise.then(
                function(success) {

                    console.log($scope.fdata);

                    if ($scope.fdata !== undefined) {

                        $resource(url + 'protectedareas/maps/' + success.id, null, {
                            postWithFile: {
                                method: "POST",
                                params: $scope.fdata,
                                transformRequest: angular.identity,
                                headers: { 'Content-Type': undefined }
                            }
                        }).postWithFile($scope.fdata).$promise.then(function(response){

                            console.log('response***********',response);
                            $location.url('/editare-harta-arie-protejata?edit=true');

                        },errorCallback); 

                        $sessionStorage.editableRow = success;
                        $scope.main.successHandlerCollback(success);
                    } else {
                        $scope.main.successHandlerCollback(success);
                    }


                },errorCallback); 
        };

  }]);
