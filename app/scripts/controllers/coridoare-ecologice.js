'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:CoridoareEcologiceCtrl
 * @description
 * # CoridoareEcologiceCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('CoridoareEcologiceCtrl', ['$scope', '$sessionStorage', 'Constant', 'HelpersService', '$http', '$q', 'leafletData', '$resource','Go',
  function($scope, $sessionStorage, Constant, HelpersService, $http, $q, leafletData, $resource, Go) {

   	var url = HelpersService.domain,
              errorCallback = $scope.main.errorHandlerCollback;

    $scope.session = $sessionStorage;
    $scope.session.title = 'Coridoare ecologice';

    var promises = [],
        MyData = {},
        urls = [
          url + 'eventcategories',
          url + 'animalcategories',
          url + 'animalsizes',
          url + 'animalspecies',
          url + 'animals/active',
          url + 'ecocorridortypes/view'
        ];

    $scope.result = {};
    for (var i = 0; i < urls.length; i++) {
      var promise = $http.get(urls[i]);
      promises.push(promise);
    }

    $scope.formDropdownData = {};

    $q.all(promises).then(function(values) {
      angular.forEach(values, function(value) {
        var str = value.config.url,
            res = str.split("/"),
            apiSplit = res[res.length - 1];

        if (str.indexOf('eventcategories') > -1)  {
          $scope.formDropdownData.event = value.data.items;
        } else if (str.indexOf('animalcategories') > -1)  {
          $scope.formDropdownData.animalcategorie = value.data.items;
        } else if (str.indexOf('animalsizes') > -1)  {
          $scope.formDropdownData.size = value.data.items;
        } else if (str.indexOf('animalspecies') > -1)  {
          $scope.formDropdownData.species = value.data.items;
        } else if (str.indexOf('animals') > -1)  {
          $scope.formDropdownData.animals = value.data.items;
        } else if (str.indexOf('ecocorridortypes/view') > -1)  {
          $scope.formDropdownData.ecocorridortype = value.data.items;
        }
        angular.extend($scope.result, value.data.items);
      });
    },

    function(error) {
      console.log("Errore: " + error.data);
    });
    
    angular.extend($scope, {
      center: {
          autoDiscover: true,
          zoom: 11
      },
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

    $scope.categories = Constant;
    $scope.itemObj = {};

    $scope.animalSearch = function() {

      console.log('animalSearch');

      var obj = {
         'animalCategoryId': ($scope.itemObj.animalCategoryId && $scope.itemObj.animalCategoryId.id) ? $scope.itemObj.animalCategoryId.id : null,
         'animalSizeId': ($scope.itemObj.animalSizeId && $scope.itemObj.animalSizeId.id) ? $scope.itemObj.animalSizeId.id : null,
         'animalSpecieId': ($scope.itemObj.animalSpecieId && $scope.itemObj.animalSpecieId.id) ? $scope.itemObj.animalSpecieId.id : null,
         'migrating': ''
      };

      var url_element = url + 'animals/search',
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

      options.makeRequest.method = 'POST';

      $scope.postElement = $resource(url_element ,null,options).makeRequest(obj);
      $scope.postElement.$promise.then(
        function(success) {
            console.log(success);
            $scope.formDropdownData.animals = success.items;
        },errorCallback); 
    };

  	$scope.searchCEco = function () {

      jQuery('.act-dez').show();
      jQuery('#add-event,#mod-event').css( "float", "left");
        
      var search = {
          'animalCategoryId': ($scope.itemObj.animalCategoryId && $scope.itemObj.animalCategoryId.id) ? $scope.itemObj.animalCategoryId.id : null,
          'animalSizeId': ($scope.itemObj.animalSizeId && $scope.itemObj.animalSizeId.id) ? $scope.itemObj.animalSizeId.id : null,
          'animalSpecieId': ($scope.itemObj.animalSpecieId && $scope.itemObj.animalSpecieId.id) ? $scope.itemObj.animalSpecieId.id : null,
          'animalId': ($scope.itemObj.animalId && $scope.itemObj.animalId) ?  $scope.itemObj.animalId.id : null,
          
          'ecoCorridorTypeId': [],
          
          'ecoCorridorStatus': [],

          'migrating': ($scope.itemObj.migrating && $scope.itemObj.migrating) ?  $scope.itemObj.migrating : null
          // 'migrating': $scope.itemObj.migrating
      };

      angular.forEach($scope.formDropdownData.ecocorridortype, function(each, index){
        if (each.isChecked === true) {
          search.ecoCorridorTypeId.push(each.id);
        }
      });

      angular.forEach($scope.categories.stare2, function(each, index){
        if (each.isChecked === true) {
          search.ecoCorridorStatus.push(each.value);
        }
      });

      var url_element = url + 'ecocorridors/search',
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

      options.makeRequest.method = 'POST';

      $scope.postElement = $resource(url_element ,null,options).makeRequest(search);
      $scope.postElement.$promise.then(
        function(success) {

            $scope.corridors = success;

            $scope.savedItems = success.geoJson;

            var popup = L.popup();

            function onEachFeature(feature, layer) {
              layer.on({
                mouseover: onMouseOver,
                // mouseout: onMouseOut,
                click: function(e) {
                  $scope.coridors = layer.feature.properties.name;
                  $sessionStorage.editableRow = layer.feature.properties;
                  $sessionStorage.editableRow.ecoCorridorId = layer.feature.properties.id;
                  $scope.clickCoridor = e;
                  leafletData.getMap().then(function(map) {
                    popup
                        .setLatLng(e.latlng)
                        // .setContent("You clicked the map at " + e.latlng.toString())
                        .setContent("Coridor ecologic - " + $sessionStorage.editableRow.name)
                        .openOn(map);

                  });

                },
              });
            }

            angular.forEach(success.geoJson.features, function(each, index){
              if (each.properties.fill) {
                  $scope.fill = each.properties.fill;
              }
            });

            angular.extend($scope.layers.overlays, {
                countries: {
                    name:'Coridoare ecologice',
                    doRefresh: true,
                    type: 'geoJSONShape',
                    data: success.geoJson,
                    visible: true,
                    layerOptions: {
                        style: {
                                color: '#00D',
                                fillColor: $scope.fill,
                                weight: 2.0,
                                opacity: 0.9,
                                fillOpacity: 0.3,
                        },
                        onEachFeature: onEachFeature
                    }
                }
            });

            function onMouseOver (e) {
              var layer = e.target;

              layer.setStyle({
                "weight": 1.5,
                "color": "#fad635",
                "fillOpacity": "0.65"
              });
            }

            // function onMouseOut (e) {
            //   var layer = e.target;

            //   success.geoJson.resetStyle(layer);
            //   console.log(success.geoJson.features);
            // }


            // angular.forEach(success.geoJson.features, function(each, index){
            //   if ($scope.layers.overlays) {
            //     success.geoJson.features.splice(each);
            //     // console.log('layers',success.geoJson);
            //   }
            // });

            // console.log('layers',$scope.layers.overlays.countries.data.geoJson.features);

        },errorCallback); 
  	};

}]);
