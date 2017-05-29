/* global $*/
'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:EditareCoridorEcologicCtrl
 * @description
 * # EditareCoridorEcologicCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
	.controller('EditareCoridorEcologicCtrl', ['$scope', '$routeParams', '$sessionStorage', 'Constant', 'HelpersService', '$http', '$q', 'leafletData', '$resource','Go', '$location', '$window' , '$route', 'leafletDrawEvents',
		function($scope, $routeParams, $sessionStorage, Constant, HelpersService, $http, $q, leafletData, $resource, Go, $location, $window, $route,leafletDrawEvents) {

			// //EXTEND API

			var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            $scope.hideStuff = function () {
                $scope.hidden = true;
            };
            $scope.showStuff = function () {
                $scope.hidden = false;
            };

            $scope.itemObj = {
                startDate: null,
                endDate: null,
                show: false
            };

            $scope.session = $sessionStorage;
            $scope.markers = {};
            $scope.categories = Constant;

			$scope.formDropdownData = {};
			$scope.itemObj = {};
			$scope.obj = {};
			$scope.validator = {};

            $scope.session.title = 'Editare coridor ecologic';


            var url_element;

			var promises = [],
				MyData = {},
				// ecotype = 'ecocorridortypes/add',
				urls = [
					url + 'eventcategories/view',
					url + 'animalcategories',
					url + 'animalsizes',
					url + 'animalspecies',
					url + 'animals/active',
					url + 'ecocorridortypes/add'
				];

			$scope.result = {};
			for (var i = 0; i < urls.length; i++) {
				var promise = $http.get(urls[i]);
				promises.push(promise);
			}

			$q.all(promises).then(function(values) {
					angular.forEach(values, function(value) {
						var str = value.config.url,
							res = str.split("/"),
							apiSplit = res[res.length - 1];

						if (str.indexOf('eventcategories/view') > -1)  {
							$scope.formDropdownData.event = value.data.items;
						} else if (str.indexOf('animalcategories') > -1)  {
							$scope.formDropdownData.animalcategorie = value.data.items;
						} else if (str.indexOf('animalsizes') > -1)  {
							$scope.formDropdownData.size = value.data.items;
						} else if (str.indexOf('animalspecies') > -1)  {
							$scope.formDropdownData.species = value.data.items;
						} else if (str.indexOf('animals') > -1)  {
							$scope.formDropdownData.animals = value.data.items;
						} else if (str.indexOf('ecocorridortypes/add') > -1)  {
							$scope.formDropdownData.ecocorridortype = value.data.items;
						}
						angular.extend($scope.result, value.data.items);
					});
				},errorCallback);

				var getInfo = function() {
	                if ($sessionStorage.editableRow.ecoCorridorId) {
			        	url_element = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId + '/additionalinfos';
			        } else {
			        	url_element = url + 'ecocorridors/' + $sessionStorage.editableRow.id + '/additionalinfos';
			        }

					var options = {
					  makeRequest: {
					      isArray: false
					  }
					};

	              	options.makeRequest.method = 'GET';

					$scope.postElement = $resource(url_element ,null,options).makeRequest();
					$scope.postElement.$promise.then(
					  function(success) {
	  	                    $scope.infos = success.items;
		                    $scope.Info(success.items[0]);
		                    $scope.items = $sessionStorage.editableInfo;
					  },errorCallback);
	            };

			// var getInfo = function() {
   //              $scope.getAdditionalInfo = $resource(url + 'ecocorridors/' + $sessionStorage.editableRow.id + '/additionalinfos', {}, {
   //                  makeRequest: {
   //                      method: 'GET',
   //                      isArray: false
   //                  }
   //              }).makeRequest();

   //              $scope.getAdditionalInfo.$promise.then(function(success) {
                    // $scope.infos = success.items;
                    // $scope.Info(success.items[0]);
                    // $scope.items = $sessionStorage.editableInfo;
   //              }, errorCallback);
   //          };

			if ($location.search().edit === 'true') {
		        // $scope.itemObj = $sessionStorage.editableRow;
		        if ($sessionStorage.editableRow.ecoCorridorId) {
		        	url_element = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId;
		        } else {
		        	url_element = url + 'ecocorridors/' + $sessionStorage.editableRow.id;
		        }

				var options = {
				  makeRequest: {
				      isArray: false
				  }
				};

              	options.makeRequest.method = 'GET';

				$scope.postElement = $resource(url_element ,null,options).makeRequest();
				$scope.postElement.$promise.then(
				  function(success) {
				  	var string = JSON.stringify(success);

				  	$sessionStorage.editableType = success;

				  	$scope.itemObj = success;
				  	$scope.itemObj.denumireAnimal = success.ecoCorridorType.animal;

				  	// console.log('============',$scope.itemObj);

				  	// $scope.itemObj.number = $sessionStorage.editableRow.number;

			      	angular.extend($scope.layers.overlays, {
		                countries: {
		                    name:'Coridoare ecologice',
		                    doRefresh: true,
		                    type: 'geoJSONShape',
		                    data: success.geoJson,
		                    visible: true,
		                    layerOptions: {
		                        style: {
		                                color: '#000',
		                                fillColor: $sessionStorage.editableType.ecoCorridorType.color,
		                                weight: 2.0,
		                                opacity: 0.9,
		                                fillOpacity: 0.9,
		                        }
		                    }
		                }
		            });


				  },errorCallback);
	            getInfo();

	            if (($sessionStorage.user.authority === 'ROLE_COMISAR_GNM' && $sessionStorage.editableRow.ecoCorridorNumber) ||
	                ($sessionStorage.user.authority === 'ROLE_ADMIN_GENERAL' && $sessionStorage.editableRow.ecoCorridorNumber)) {

                    var url_resolutionsGet = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId + '/resolution';
                    options = {
                        makeRequest: {
                            isArray: false
                        }
                    };

                    $scope.getResolutionGetter = $resource(url_resolutionsGet ,null,options).makeRequest();
                    $scope.getResolutionGetter.$promise.then(
                        function(success) {
                            console.log('success =========', success);
                            $scope.validator.resolution = success.resolution;
                        },errorCallback);
                }

		    } else {
		    	delete $sessionStorage.editableRow;
		    }

		    var changeMigrator = function(boolean) {
                if (boolean === 'Da') {
                  return true;
                } else if (boolean === 'Nu') {
                  return false;
                }
            };

			var currentDate = new Date();

			var minStartDateParse = Date.parse(currentDate) - 2629746000;

			$scope.maxStartDate = currentDate;

			$scope.itemObj.startDate = new Date(minStartDateParse);
			$scope.itemObj.endDate = new Date();

			$scope.changeStartDate = function (data) {
				var currendDate = new Date();
				var startDate = Date.parse(data);
				var endDate = Date.parse($scope.itemObj.endDate);
				var delta = endDate - startDate;

				if (delta < 0) {
					if (startDate + 2629746000 > Date.parse(currendDate)) {
						$scope.itemObj.endDate = currendDate;
					} else {
						$scope.itemObj.endDate = new Date(startDate + 2629746000);
					}
				} else if (Date.parse($scope.itemObj.endDate) - Date.parse(data) > 2629746000) {
					$scope.itemObj.endDate = new Date(Date.parse(data) + 2629746000);
				}
			};

			$scope.changeEndDate = function (data) {
				var currendDate = new Date();
				var startDate = Date.parse($scope.itemObj.startDate);
				var endDate = Date.parse(data);
				var delta = endDate - startDate;

				if (delta < 0) {
					$scope.itemObj.startDate = new Date(endDate - 2629746000);
				} else if (Date.parse(data) - Date.parse($scope.itemObj.startDate) > 2629746000) {
					$scope.itemObj.startDate = new Date(Date.parse(data) - 2629746000);
				}
			};

			$scope.animalSearch = function() {

				var obj = {
					 'animalCategoryId': ($scope.itemObj.animalCategoryId && $scope.itemObj.animalCategoryId.id) ? $scope.itemObj.animalCategoryId.id : null,
					 'animalSizeId': ($scope.itemObj.animalSizeId && $scope.itemObj.animalSizeId.id) ? $scope.itemObj.animalSizeId.id : null,
					 'animalSpecieId': ($scope.itemObj.animalSpecieId && $scope.itemObj.animalSpecieId.id) ? $scope.itemObj.animalSpecieId.id : null,
					 'migrating': $scope.itemObj.migrate ? changeMigrator($scope.itemObj.migrate) : null
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
				      $scope.formDropdownData.animals = success.items;
				  },errorCallback); 
			};

			$scope.corriorEco = function() {

				console.log('animalSearch');

				var obj = {
					 'animalId': $scope.itemObj.denumireAnimal === null ? null : $scope.itemObj.denumireAnimal.id,
				};

				var url_element = url + 'ecocorridortypes/search',
			                      options = {
			                          makeRequest: {
			                              isArray: false
			                          }
			                      };

              	options.makeRequest.method = 'POST';

				$scope.postElement = $resource(url_element ,null,options).makeRequest(obj);
				$scope.postElement.$promise.then(
				  function(success) {
			      	$scope.formDropdownData.ecocorridortyp = success.items;
			      	if (($location.search().edit === 'true') && $sessionStorage.editableType ) {
						$scope.itemObj.ecoCorridorType = $sessionStorage.editableType.ecoCorridorType;
					}
				  },errorCallback); 
			};

			// MAP DRAW
			// var drawnItems = new L.FeatureGroup();
			// angular.extend($scope, {
		 //      map: {
		 //        drawOptions: {
		 //          position: "topleft",
		 //          draw: {
		 //            polyline: {
		 //              metric: false
		 //            },
		 //            polygon: {
		 //              metric: false,
		 //              showArea: true,
		 //              drawError: {
		 //                color: '#b00b00',
		 //                timeout: 1000
		 //              },
		 //              shapeOptions: {
		 //                color: 'blue'
		 //              }
		 //            },
		 //            circle: {
		 //              showArea: true,
		 //              metric: false,
		 //              shapeOptions: {
		 //                color: '#662d91'
		 //              }
		 //            },
		 //            marker: false
		 //          },
		 //          edit: {
		 //            featureGroup: drawnItems,
		 //            remove: true
		 //          }
		 //        }
		 //      }
		 //    });

		 //    var handle = {
		 //      created: function(e,leafletEvent, leafletObject, model, modelName) {
		 //        drawnItems.addLayer(leafletEvent.layer);
		 //        console.log(leafletEvent.layer);
		 //      },
		 //      edited: function(arg) {},
		 //      deleted: function(arg) {
		 //        var layers;
		 //        layers = arg.layers;
		 //        drawnItems.removeLayer(layer);
		 //      },
		 //      drawstart: function(arg) {},
		 //      drawstop: function(arg) {},
		 //      editstart: function(arg) {},
		 //      editstop: function(arg) {},
		 //      deletestart: function(arg) {},
		 //      deletestop: function(arg) {}
		 //    };
		 //    var drawEvents = leafletDrawEvents.getAvailableEvents();
		 //    drawEvents.forEach(function(eventName){
		 //        $scope.$on('leafletDirectiveDraw.' + eventName, function(e, payload) {
		 //          //{leafletEvent, leafletObject, model, modelName} = payload
		 //          var leafletEvent, leafletObject, model, modelName; //destructuring not supported by chrome yet :(
		 //          leafletEvent = payload.leafletEvent, leafletObject = payload.leafletObject, model = payload.model,
		 //          modelName = payload.modelName;
		 //          handle[eventName.replace('draw:','')](e,leafletEvent, leafletObject, model, modelName);
		 //        });
		 //    });

			$scope.savedItems = [];

		 	var drawnItems = new L.FeatureGroup(),
		       onEachFeatureHandler = function(feature, layer) {
		           drawnItems.addLayer(layer);
		       },
		       styleHandler = function(feature) {
		           return {
		               color: '#ff2200'
		           };
		       };

			/* jshint ignore:start */
			for (var ii = 0; ii < $scope.savedItems.length; ii++) {
			    L.geoJson($scope.savedItems[ii].geoJSON, {
			        style: styleHandler,
			        onEachFeature: onEachFeatureHandler
			    });
			}
			
			/* jshint ignore:end */

			angular.extend($scope, {
				doRefresh: true,
				// controls: {
				// 	draw: {
				// 		polygon: {
				//           allowIntersection: false,
				//           showArea: true,
				//           drawError: {
				//             color: '#b00b00',
				//             timeout: 1000
				//           },
				//           shapeOptions: {
				//             color: '#bada55'
				//           }
				//         },
				// 		edit: {
				// 			featureGroup: drawnItems,
				// 			edit: {
				// 	          selectedPathOptions: {
				// 	            maintainColor: true,
				// 	            opacity: 0.9
				// 	          }
				// 	        },
				// 	        remove: true
				// 		}
				// 	}
				// }
			});

			leafletData.getMap().then(function(map) {

				// var drawnItems = $scope.controls.draw.edit.featureGroup;
				drawnItems.addTo(map);

				map.on('draw:created', function(e) {
					var layer = e.layer;
					drawnItems.addLayer(layer);

					$scope.savedItems.push({
						id: layer._leaflet_id,
						geoJSON: layer.toGeoJSON()
					});


				});


				map.on('draw:edited', function(e) {
					var layers = e.layers;
					layers.eachLayer(function(layer) {

						for (var i = 0; i < $scope.savedItems.length; i++) {
							if ($scope.savedItems[i].id === layer._leaflet_id) {
								$scope.savedItems[i].geoJSON = layer.toGeoJSON();
							}
						}
					});
				});

				map.on('draw:deleted', function(e) {
					var layers = e.layers;
					layers.eachLayer(function(layer) {
						for (var i = 0; i < $scope.savedItems.length; i++) {
							if ($scope.savedItems[i].id === layer._leaflet_id) {
								$scope.savedItems.splice(i, 1);
							}
						}
					});
				});
			});

			$scope.searchEventsEco = function () {
        
				var searchCorridor = {
				  'eventCategoriesId': [],
				  'animalCategoryId': [],
				  'animalSizeId': [],
				  'animalSpecieId': [],
				  'animalId': [],
				  'isMigratory': [],
				  'startDate': $scope.itemObj.startDate,
				  'endDate': $scope.itemObj.endDate
				};

		        angular.forEach($scope.formDropdownData.event, function(each, index){
					if (each.isChecked === true) {
					  searchCorridor.eventCategoriesId.push(each.id);
					}
				});
			  	
			  	if ($scope.itemObj.animalCategoryId && $scope.itemObj.animalCategoryId.id) {
			  		searchCorridor.animalCategoryId.push($scope.itemObj.animalCategoryId.id);
			  	}
			  	if ($scope.itemObj.animalSizeId && $scope.itemObj.animalSizeId.id) {
			  		searchCorridor.animalSizeId.push($scope.itemObj.animalSizeId.id);
			  	}
			  	if ($scope.itemObj.animalSpecieId && $scope.itemObj.animalSpecieId.id) {
			  		searchCorridor.animalSpecieId.push($scope.itemObj.animalSpecieId.id);
			  	}
			  	if ($scope.itemObj.animalId && $scope.itemObj.animalId.id) {
			  		searchCorridor.animalId.push($scope.itemObj.animalId.id);
			  	}
		      	
		      	if ($scope.itemObj.migrate === undefined ) {
		      		// return;
		      	} else if ($scope.itemObj.migrate === 'Da') {
		      		// searchCorridor.isMigratory = true;
		      		searchCorridor.isMigratory.push(true);
		      	} else if ($scope.itemObj.migrate === 'Nu') {
		      		// searchCorridor.isMigratory = false;
		      		searchCorridor.isMigratory.push(false);
		      	}

		      	var url_element = url + 'events/search',
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

				options.makeRequest.method = 'POST';

				$scope.markers = {};

				$scope.postElement = $resource(url_element ,null,options).makeRequest(searchCorridor);
				$scope.postElement.$promise.then(
				function(success) {

	                angular.forEach(success.items, function(each, index){
						
						var mainMarker = {
		                      lat: each.latitude,
		                      lng: each.longitude,
		                      focus: true,
		                      message: each.eventCategory,
		                      // message: '<button type="button" class="popUpButton" ng-click="markerEventPage()">' +  $sessionStorage.editableRow.eventCategory + '<br>' + $sessionStorage.editableRow.description + '<br>' + $sessionStorage.editableRow.eventDate + '</button>',
		                      draggable: false
		                };
	                  	
	                  	$scope.markers[each.eventId] = mainMarker;
		                  // $scope.editableRow = items;
		                  // $sessionStorage.editableRow = $scope.editableRow;
	                });

				},errorCallback); 
		  	};

		  	$scope.Info = function(note) {
                $scope.editableInfo = note;
                $sessionStorage.editableInfo = $scope.editableInfo;
                // appData.editableRow = $scope.editableRow;

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableInfo;
                }

            };

			$scope.sendPost = function(itemObj) {

				itemObj = {
					'name': itemObj.name,
					'number': itemObj.number,
					'ecoCorridorType': {
						'id': itemObj.ecoCorridorType.id
					},
					'description': itemObj.description,
					// 'poligon': $scope.savedItems[0].geoJSON.geometry

					'poligon': $scope.savedItems[0] ? $scope.savedItems[0].geoJSON.geometry : null,
				};

	            var url_judet = url + 'ecocorridors',
	            options = {
	                makeRequest: {
	                    isArray: false
	                }
	            };


		        if ($location.search().edit === 'true') {
	             	if ($sessionStorage.editableRow.ecoCorridorId) {
			        	url_judet = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId;
			        } else {
			        	url_judet = url + 'ecocorridors/' + $sessionStorage.editableRow.id;
			        }
		            options.makeRequest.method = 'PUT';
		        } else {
		            options.makeRequest.method = 'POST';
		        }

		        $scope.postJudet = $resource(url_judet ,null,options).makeRequest(itemObj);
		        $scope.postJudet.$promise.then(
		            function(success) {
		                $scope.main.successHandlerCollback(success);
		                $location.url('/editare-coridor-ecologic?edit=true');
		                // $sessionStorage.ecocorridorsID = success.id;
		                $sessionStorage.editableRow = success;
		        	},errorCallback); 
			};

			$scope.note = {
                'active' : true,
                'description' : ''
            };

			$scope.addInfo = function(addInfoForm, note) {

                note.active = true;

                if (addInfoForm.$valid) {

                    var additionalInfo = {
                    	'additionalInfoId' : $sessionStorage.editableInfo.additionalInfoId,
                    	'description' : note.description
                    };

                    var url_contact = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId + '/additionalinfos' ,
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                    options.makeRequest.method = 'POST';

                    $scope.postComment = $resource(url_contact, null, options).makeRequest(additionalInfo);
                    $scope.postComment.$promise.then(function(success) {
                        $('#addInfoModal').modal('hide');
                        getInfo();
                    }, errorCallback);
                }
            };

            $scope.changeState = function(state) {

                $scope.state = state;

                if (state === 'adauga') {
                    $scope.note = {};
                }
            };

            $scope.activeInfo = function(items) {

                $scope.editableInfo.active = true;
                $scope.items = $resource(url + 'ecocorridors/' +  $sessionStorage.editableRow.ecoCorridorId + '/additionalinfos/' + $sessionStorage.editableInfo.additionalInfoId + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableInfo);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.deactiveInfo = function(items) {

                $scope.editableInfo.active = false;
                $scope.items = $resource(url + 'ecocorridors/' +  $sessionStorage.editableRow.ecoCorridorId + '/additionalinfos/' + $sessionStorage.editableInfo.additionalInfoId + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableInfo);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);
            };

            $scope.activeEco = function() {

            	var url_active = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId + '/enable',
	                options = {
	                    makeRequest: {
	                        isArray: false
	                    }
	                };

	            options.makeRequest.method = 'PUT';

	            $scope.activeEcoCorridors = $resource(url_active ,null,options).makeRequest();
	            $scope.activeEcoCorridors.$promise.then(
	                function(success) {
	                	$scope.main.successHandlerCollback(success);
	                    console.log(success);
	                },errorCallback); 
            };

            $scope.deactiveEco = function() {

            	var url_active = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId + '/disable',
	                options = {
	                    makeRequest: {
	                        isArray: false
	                    }
	                };

	            options.makeRequest.method = 'PUT';

	            $scope.activeEcoCorridors = $resource(url_active ,null,options).makeRequest();
	            $scope.activeEcoCorridors.$promise.then(
	                function(success) {
	                	$scope.main.successHandlerCollback(success);
	                    console.log(success);
	                },errorCallback); 
            };

            $scope.sendValidator = function(validator) {

            	var url_resolutions;

                var valid = {
                    'resolution' : validator.resolution
                };

                url_resolutions = url + 'ecocorridors/' + $sessionStorage.editableRow.ecoCorridorId + '/resolution';
                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

                options.makeRequest.method = 'POST';

                $scope.postResolutions = $resource(url_resolutions ,null,options).makeRequest(valid);
                $scope.postResolutions.$promise.then(
                    function(success) {

                        if (validator.valid === 'true') {
                            console.log('true');
                            url_resolutions = url + 'ecocorridors/resolutions/' + $sessionStorage.editableRow.id + '/validate';
                        } else {
                            console.log('false');
                            url_resolutions = url + 'ecocorridors/resolutions/' + $sessionStorage.editableRow.id + '/invalidate';
                        }

                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                        options.makeRequest.method = 'PUT';
                        
                        $scope.postResolutions = $resource(url_resolutions ,null,options).makeRequest();
                        $scope.postResolutions.$promise.then(
                            function(success) {
                                $scope.main.successHandlerCollback(success);
                                Go.to({url:'rezolutii-notificari'});
                            },errorCallback); 

                    },errorCallback); 
            };

		}
	]);
