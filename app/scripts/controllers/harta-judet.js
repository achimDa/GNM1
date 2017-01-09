'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:HartaJudetCtrl
 * @description
 * # HartaJudetCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('HartaJudetCtrl', ['$scope', 'Upload', '$timeout', '$resource','$location', '$sessionStorage', 'HelpersService', 'Go' ,'leafletData',
	function ($scope, Upload, $timeout, $resource, $location, $sessionStorage, HelpersService, Go, leafletData) {

    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    // delete $sessionStorage.addLink.label;
    $scope.session = $sessionStorage;
    $scope.session.title = 'Harta judet';

    $scope.itemObj = {};

    var options = {
        makeRequest: {
            method: 'GET',
            isArray: false
        }
    };
    
    $scope.itemObj = {};

    angular.extend($scope, {
        doRefresh: true,
        romania: {
          autoDiscover: true,
          zoom: 6
        },
        romania2: {
          autoDiscover: true,
          zoom: 6
        },  
        controls: {
            scale: true
        },
        defaults: {
          scrollWheelZoom: false
        },
        geojson:{},
        layers: {
          baselayers: {
              osm: {
                name: 'OpenStreetMap',
                url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                type: 'xyz'
              },
          },
          overlays:{}
        }
    });

    var url_country = 'countries/' + $sessionStorage.editableRow.id + '/countygeom';
            
    $scope.getCountry = $resource(url + url_country, null, options).get();           
 
    $scope.getCountry.$promise.then(function(success) {
        console.log(success);
        angular.extend($scope.geojson, {
            romania:{
                data: success.geoJson,
                resetStyleOnMouseout: true,
                style: {
                    fillColor: "blue",
                    weight: 2,
                    opacity: 1,
                    color: 'white',
                    dashArray: '3',
                    fillOpacity: 0.7
                }
            }
        });

    }, errorCallback);

    $scope.sendFile = function(files) {

        var fdata = new FormData();
        angular.forEach(files, function(value, index){
            fdata.append("map", files[index]);
        });

        $scope.fdata = fdata;
        $scope.files = files;

        $resource(url + 'countries/' + $sessionStorage.editableRow.id + '/uploadcounty', null, {
            postWithFile: {
                method: "POST",
                params: $scope.fdata,
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }
        }).postWithFile($scope.fdata).$promise.then(function(response){

            $scope.coutryMap = response.geoJson;
            $location.url('/administrare');
        	$scope.main.successHandlerCollback(response);
        },errorCallback); 

    };

}]);
