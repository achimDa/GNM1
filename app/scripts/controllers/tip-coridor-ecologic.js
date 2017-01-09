'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:TipCoridorEcologicCtrl
 * @description
 * # TipCoridorEcologicCtrl
 * Controller of the gnmApp 
 */
angular.module('gnmApp')
  .controller('TipCoridorEcologicCtrl', ['$scope', 'HelpersService','$http', '$resource','$location','$sessionStorage','Go',
  function ($scope,HelpersService,$http,$resource,$location,$sessionStorage,Go) {
  
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;
  	
    $scope.itemObj = {};

    $scope.formDropdownData = {};

    $scope.hideStuff = function () {
        $scope.hidden = true;
    };
    $scope.showStuff = function () {
        $scope.hidden = false;
    };

    $scope.project = {
          colorGreen: "#008000"
    };

    var optionsGetter = {
        makeRequest: {
            method: 'GET',
            isArray: true
        }
    };

    var url_animalcategories = 'animalcategories',
        url_animalsizes = 'animalsizes',
        url_animalspecies =  'animalspecies',
        url_animals =  'animals',
        url_methodologies =  'methodologies',
        url_migrator =  'ecocorridortypes/boolean';
    
    var optionsPermissions = {
        makeRequest: {
            method: 'GET',
            isArray: true
        }
    };

    $scope.getAnimalcategories = $resource(url + url_animalcategories, null, optionsGetter).get();
    $scope.getAnimalsizes = $resource(url + url_animalsizes, null, optionsGetter).get();
    $scope.getAnimalspecies = $resource(url + url_animalspecies, null, optionsGetter).get();
    $scope.getAnimals = $resource(url + url_animals, null, optionsGetter).get();
    $scope.getMethodologies = $resource(url + url_methodologies, null, optionsGetter).get();
    $scope.getMigrators = $resource(url + url_migrator, null, optionsGetter).get();

    $scope.getAnimalcategories.$promise.then(function(success) {
        $scope.formDropdownData.category = success.items;
    }, errorCallback);

    $scope.getAnimalsizes.$promise.then(function(success) {
        $scope.formDropdownData.size = success.items;
    }, errorCallback);

    $scope.getAnimalspecies.$promise.then(function(success) {
        $scope.formDropdownData.species = success.items;
    }, errorCallback);

    $scope.getAnimals.$promise.then(function(success) {
        $scope.formDropdownData.animale = success.items;
    }, errorCallback);

    $scope.getMethodologies.$promise.then(function(success) {
        $scope.formDropdownData.methodologies = success.items;
    }, errorCallback);

    $scope.getMigrators.$promise.then(function(success) {
        $scope.formDropdownData.migrator = success.items;
    }, errorCallback);

    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {

        var url_ecocorridortype = 'ecocorridortypes/' + $sessionStorage.editableRow.id;
        var url_permissionsType = 'ecocorridortypes/' + $sessionStorage.editableRow.id + '/permissions';

        $scope.getEcocorridortype = $resource(url + url_ecocorridortype, null, optionsPermissions).get();
        $scope.getPermissionsType = $resource(url + url_permissionsType, null, optionsPermissions).get();

        $scope.getEcocorridortype.$promise.then(function(success) {
            $scope.itemObj = success;
            console.log(success);

            $scope.project = {
                  colorGreen: $scope.itemObj.color
            };

        }, errorCallback);

        $scope.getPermissionsType.$promise.then(function(success) {
            $scope.formDropdownData.typepermissions = success.items;
            console.log('success', $scope.formDropdownData.typepermissions);
        }, errorCallback);
    }
  
    $scope.sendPost = function(itemObj) {
 
          var newObj = {
                'name' : itemObj.name,
                'color' : itemObj.color,
                'animalCategory': (typeof $scope.itemObj.animalCategory !== 'undefined') && ($scope.itemObj.animalCategory !== null) ? {
                    'id': $scope.itemObj.animalCategory.id 
                } : null,
                'animalSize': (typeof $scope.itemObj.animalSize !== 'undefined') && ($scope.itemObj.animalSize !== null) ? {
                    'id': $scope.itemObj.animalSize.id 
                } : null,
                'animalSpecies': (typeof $scope.itemObj.animalSpecies !== 'undefined') && ($scope.itemObj.animalSpecies !== null) ? {
                    'id': $scope.itemObj.animalSpecies.id 
                } : null,
                // 'animalSpecies': (typeof $scope.itemObj.animalSpecies !== 'undefined') && ($scope.itemObj.animalSpecies !== null) ? {
                //     'id': $scope.itemObj.animalSpecies.id 
                // } : null,
                'migrate': (typeof $scope.itemObj.migrate !== 'undefined') && ($scope.itemObj.migrate !== null) ? {
                    'id': $scope.itemObj.migrate.id 
                } : null,
                'animal': (typeof $scope.itemObj.animal !== 'undefined') && ($scope.itemObj.animal !== null) ? {
                    'id': $scope.itemObj.animal.id 
                } : null,
                'description' : itemObj.description,
                'methodology': (typeof $scope.itemObj.methodology !== 'undefined') && ($scope.itemObj.methodology !== null) ? {
                    'id': $scope.itemObj.methodology.id 
                } : null
            };

            if ($scope.itemObj.migrate === undefined ) {
                  // return;
            } else if ($scope.itemObj.migrate === 'Da') {
              newObj.migrate = true;
            } else if ($scope.itemObj.migrate === 'Nu') {
              newObj.migrate = false;
            }

          var url_methodologies = url + 'ecocorridortypes',
              options = {
                  makeRequest: {
                      isArray: false
                  }
              };

          if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
              url_methodologies = url + 'ecocorridortypes/' + $scope.itemObj.id;
              options.makeRequest.method = 'PUT';
          } else {
              options.makeRequest.method = 'POST';
          }

          $scope.postMethodologies = $resource(url_methodologies ,null,options).makeRequest(newObj);
          $scope.postMethodologies.$promise.then(
              function(success) {

                  $scope.main.successHandlerCollback(success);
                  $sessionStorage.editableRow = success;

                  $location.url('/tip-coridor-ecologic?edit=true');
                  console.log(success);
                
              },errorCallback); 
    };

    var checkOption = {
        makeRequest: {
            method: 'PUT',
            isArray: true
        }
    };
    var url_enable;

    $scope.visualization = function(items) {

        angular.forEach($scope.formDropdownData.typepermissions, function(each, index) {
            if (each.id === items.id) {
                if (items.canView === true) {

                    items.canView = true;
                    
                    url_enable = 'ecocorridortypes/permissions/' + items.id + '/view/enable';

                    $scope.putEnable = $resource(url + url_enable, null, checkOption).makeRequest($scope.editableInfo);

                    $scope.putEnable.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                } else {

                    items.canView = false;

                    url_enable = 'ecocorridortypes/permissions/' + items.id + '/view/disable';

                    $scope.putEnable = $resource(url + url_enable, null, checkOption).makeRequest($scope.editableInfo);

                    $scope.putEnable.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                }
            }
        });

    };

    // $scope.visualization = function(items) {

    //     angular.forEach($scope.formDropdownData.typepermissions, function(each, index) {
    //         if (each.id === items.id) {
    //             if (items.canView === true) {

    //                 items.canView = true;

    //                 $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/view/enable', {}, {
    //                     makeRequest: {
    //                         method: 'PUT',
    //                         isArray: false
    //                     }
    //                 }).makeRequest($scope.editableInfo);
    //                 $scope.items.$promise.then(function(success) {
    //                     console.log(success);
    //                 }, errorCallback);

    //             } else {
    //                 items.canView = false;
    //                 $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/view/disable', {}, {
    //                     makeRequest: {
    //                         method: 'PUT',
    //                         isArray: false
    //                     }
    //                 }).makeRequest($scope.editableInfo);
    //                 $scope.items.$promise.then(function(success) {
    //                    console.log(success);
    //                 }, errorCallback);

    //             }
    //         }
    //     });
    // };

    $scope.add = function(items) {

        angular.forEach($scope.formDropdownData.typepermissions, function(each, index) {
            if (each.id === items.id) {
                if (items.canAdd === true) {

                    items.canAdd = true;
                    $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/add/enable', {}, {
                        makeRequest: {
                            method: 'PUT',
                            isArray: false
                        }
                    }).makeRequest($scope.editableInfo);
                    $scope.items.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                } else {
                    items.canAdd = false;
                    $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/add/disable', {}, {
                        makeRequest: {
                            method: 'PUT',
                            isArray: false
                        }
                    }).makeRequest($scope.editableInfo);
                    $scope.items.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                }
            }
        });

    };

    $scope.validation = function(items) {

        angular.forEach($scope.formDropdownData.typepermissions, function(each, index) {
            if (each.id === items.id) {
                if (items.canValidate === true) {

                    items.canValidate = true;
                    $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/validate/enable', {}, {
                        makeRequest: {
                            method: 'PUT',
                            isArray: false
                        }
                    }).makeRequest($scope.editableInfo);
                    $scope.items.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                } else {
                    items.canValidate = false;
                    $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/validate/disable', {}, {
                        makeRequest: {
                            method: 'PUT',
                            isArray: false
                        }
                    }).makeRequest($scope.editableInfo);
                    $scope.items.$promise.then(function(success) {
                       $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                }
            }
        });

    };

    $scope.enable = function(items) {

        angular.forEach($scope.formDropdownData.typepermissions, function(each, index) {
            if (each.id === items.id) {
                if (items.canEnable === true) {

                    items.canEnable = true;
                    $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/enable/enable', {}, {
                        makeRequest: {
                            method: 'PUT',
                            isArray: false
                        }
                    }).makeRequest($scope.editableInfo);
                    $scope.items.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                } else {
                    items.canEnable = false;
                    $scope.items = $resource(url + 'ecocorridortypes/permissions/' + items.id + '/enable/disable', {}, {
                        makeRequest: {
                            method: 'PUT',
                            isArray: false
                        }
                    }).makeRequest($scope.editableInfo);
                    $scope.items.$promise.then(function(success) {
                        $scope.main.successHandlerCollback(success);
                    }, errorCallback);

                }
            }
        });

    };

  }]);
