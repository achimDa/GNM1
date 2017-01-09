'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AdaugaJudetCtrl
 * @description
 * # AdaugaJudetCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('AdaugaJudetCtrl', function ($scope,$http,$resource,HelpersService,$sessionStorage,$location,Go) {
	
	var url = HelpersService.domain,
		errorCallback = $scope.main.errorHandlerCollback;

	// delete $sessionStorage.addLink.label;
	
    // $scope.session.title = 'Judet';

	$scope.session = $sessionStorage;
	$scope.itemObj = {};
	$scope.formDropdownData = {};
	$scope.geometry = {};
	var newObj = {},
		options;

	options = {
	    makeRequest: {
	        method: 'GET',
	        isArray: true
	    }
    };

	
	if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
		
		$scope.itemObj = $sessionStorage.editableRow;
		
		options = {
            makeRequest: {
                method: 'GET',
                isArray: true
            }
        };

        var url_countyGeoJson = 'counties/' + $sessionStorage.editableRow.id;

        $scope.getCountyGeoJson = $resource(url + url_countyGeoJson, null, options).get();

        $scope.getCountyGeoJson.$promise.then(function(success) {
            // $scope.formDropdownData.judetegis = success.items;
            angular.extend($scope.layers.overlays, {
		        geojson: {
		            name:'Coridoare ecologice',
		            doRefresh: true,
		            type: 'geoJSONShape',
		            data: success.geoJson,
		            visible: true,
		            layerOptions: {
		                style: {
		                        color: '#000',
		                        fillColor: '#ff0000',
		                        weight: 2.0,
		                        opacity: 0.9,
		                        fillOpacity: 0.5,
		                }
		            }
		        }
		    });

		    $scope.itemObj.country = success.country;

        }, errorCallback);
	}


	$scope.data = $resource(HelpersService.domain + 'countries', {}, {
		makeRequest: {
			method: 'GET'
		}
	}).makeRequest();

	$scope.countyName = function (itemObj) {
		var url_Judete = 'counties/names/' + $scope.itemObj.country.id;

		$scope.getJudete = $resource(url + url_Judete, null, options).get();

	    $scope.getJudete.$promise.then(function(success) {
	        $scope.formDropdownData.judetegis = success.items;
	    }, errorCallback);
	    console.log(itemObj);
	};

	
	$scope.mapUpdate = function (itemObj) {

		console.log(itemObj);

		if (itemObj.geometry !== null) {	
		    var url_postgis = 'counties/' + itemObj.geometry.id + '/' + itemObj.country.id;

		    $scope.getPostGis = $resource(url + url_postgis, null, options).get();

		    $scope.getPostGis.$promise.then(function(success) {
		        $scope.formDropdownData.gis = success;

		        $scope.geometry = success.geoJson.features[0].geometry;

				angular.extend($scope.layers.overlays, {
			        geojson: {
			            name:'Coridoare ecologice',
			            doRefresh: true,
			            type: 'geoJSONShape',
			            data: success.geoJson,
			            visible: true,
			            layerOptions: {
			                style: {
			                        color: '#000',
			                        fillColor: '#ff0000',
			                        weight: 2.0,
			                        opacity: 0.9,
			                        fillOpacity: 0.9,
			                }
			            }
			        }
			    });
		        
		        $scope.geometry = success.geoJson.features[0].geometry;
	    	}, errorCallback);
	    } else {
	    	$scope.geometry = {};
	    	angular.extend($scope.layers.overlays, {
			        geojson: {
			            name:'Coridoare ecologice',
			            doRefresh: true,
			            type: 'geoJSONShape',
			            data: '',
			            visible: true,
			            layerOptions: {
			                style: {
			                        color: '#000',
			                        fillColor: '#ff0000',
			                        weight: 2.0,
			                        opacity: 0.9,
			                        fillOpacity: 0.9,
			                }
			            }
			        }
			    });
	    }
		
	};


	$scope.uploadFiles = function (files,Upload,$timeout) {
		$scope.files = files;
		if (files && files.length) {
			Upload.upload({
				url: '/images',
				data: {
					files: files
				}
			}).then(function (response) {
				$timeout(function () {
					$scope.result = response.data;
				});
			}, function (response) {
				if (response.status > 0) {
					$scope.errorMsg = response.status + ': ' + response.data;
				}
			}, function (evt) {
				$scope.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			});
		}
	};

	$scope.sendPost = function(itemObj) {
		newObj = {
			'name' : $scope.itemObj.name,
			'initials' : $scope.itemObj.initials,
			'country' : {
				'id' : $scope.itemObj.country.id
			},
			'geometry' : $scope.geometry
		};

		var url_judet = url + 'counties',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_judet = url + 'counties/' +  $scope.itemObj.id;

            newObj = {
				'name' : $scope.itemObj.name,
				'initials' : $scope.itemObj.initials,
				'country' : {
					'id' : $scope.itemObj.country.id
				},
				'geometry' : $scope.geometry.type !== undefined ? $scope.geometry : null
			};

            options.makeRequest.method = 'PUT';
            console.log($scope.geometry);

        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postJudet = $resource(url_judet ,null,options).makeRequest(newObj);
        $scope.postJudet.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});

        	},errorCallback); 
	};

  });
