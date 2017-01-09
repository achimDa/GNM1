'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:CategorieEvenimenteCtrl
 * @description
 * # CategorieEvenimenteCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('CategorieEvenimenteCtrl',['$scope','$resource','$routeParams' ,'$sessionStorage', '$location', 'HelpersService','Go', '$timeout' ,
   function ($scope,$resource,$routeParams,$sessionStorage,$location,HelpersService,Go,$timeout) {
  
	var url = HelpersService.domain,
        errorCallback = $scope.errorHandlerCollback;

	//EXTEND API

	$scope.session = $sessionStorage;
	$scope.formDropdownData = {};
    $scope.itemObj = {};
    $scope.indicatorObj = {};

    $scope.hiddenIndicators = true;

    $scope.hideStuff = function () {
        $scope.hiddenOk = false;
        $scope.hiddenIndicators = true;
        $scope.hiddenBack = false;
    };
    $scope.hideStuff1 = function () {
        $scope.hiddenOk = true;
        $scope.hiddenIndicators = false;
        $scope.hiddenBack = false;
    };
    $scope.hideStuff2 = function () {
        $scope.hiddenOk = true;
        $scope.hiddenIndicators = true;
        $scope.hiddenBack = true;
    };

	// Color Picker

    $scope.project = {
	    colorRed: "#ff0000",
	    colorYellow: "#FFFF00",
	    colorGreen: "#008000"
	};
    
    var optionsP = {
            makeRequest: {
                method: 'GET',
                isArray: true
            }
        };

    var legislationsGet = function() {
	    var url_LegislationsCE = 'eventcategories/' + $sessionStorage.editableRow.id + '/legislations',
	    	getLegislationsCE = $resource(url + url_LegislationsCE, null, optionsP).get();
    	getLegislationsCE.$promise.then(function(success) {
	        $scope.formDropdownData.legislations = success.items;
	    }, errorCallback);

	};

	var reglementationsGet = function() {
		var url_ReglementationsCE = 'eventcategories/' + $sessionStorage.editableRow.id + '/regualtions',
	    	getReglementationsCE = $resource(url + url_ReglementationsCE, null, optionsP).get();
	    getReglementationsCE.$promise.then(function(success) {
	        $scope.formDropdownData.reglementations = success.items;
	    }, errorCallback);
	};

	var permissionsGet = function() {
		var url_PermissionsCE = 'eventcategories/' + $sessionStorage.editableRow.id + '/permissions',
	    	getPermissionsCE = $resource(url + url_PermissionsCE, null, optionsP).get();
	    getPermissionsCE.$promise.then(function(success) {
	        $scope.formDropdownData.permissions = success.items;
	    }, errorCallback);
    };

    var performanceGet = function() {
		var url_performanceIndicators = 'eventcategories/' + $sessionStorage.editableRow.id + '/performance',
	    	getPerformanceIndicators = $resource(url + url_performanceIndicators, null, optionsP).get();
	    	getPerformanceIndicators.$promise.then(function(success) {
	        
	        $scope.indicatorObj = success;
	        $scope.project = {
	              colorRed: $scope.indicatorObj.firstCollor,
	              colorYellow: $scope.indicatorObj.secondCollor,
	              colorGreen: $scope.indicatorObj.thirdCollor
	        };

	    }, errorCallback);
	};

    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;

        // $timeout(function() {
        // 	$scope.itemObj.symbol = $sessionStorage.editableRow.symbol;
        // }, 100);

        legislationsGet();
        reglementationsGet();
        permissionsGet();
        performanceGet();

        console.log($scope.itemObj.symbol);
        // console.log($sessionStorage.editableRow.symbol);

    }

	$scope.sendPost = function(itemObj) {

		var newObj = {
                      'name' : itemObj.name,
                      // 'animalInfo': ($scope.itemObj.animalInfo && $scope.itemObj.animalInfo.id) ? $scope.itemObj.animalInfo.id : false,
                      'animalInfo': (typeof itemObj.animalInfo !== 'undefined') && (itemObj.animalInfo !== null) ? itemObj.animalInfo : false,
                      'ecoCorridorInfo': (typeof itemObj.ecoCorridorInfo !== 'undefined') && (itemObj.ecoCorridorInfo !== null) ? itemObj.ecoCorridorInfo : false,
                      'symbol' : itemObj.symbol,
                      'priority' : itemObj.priority,
                      'description' : itemObj.description
                    };
    	
		var url_protectedArea = url + 'eventcategories',
            options = {
                makeRequest: {
                    method: 'POST',
                    isArray: false
                }
            };

        if ($routeParams.edit === 'true') {
            url_protectedArea = url + 'eventcategories/' + $sessionStorage.editableRow.id;
            options.makeRequest.method = 'PUT';

        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postProtectedArea = $resource(url_protectedArea, null, options).makeRequest(newObj);
        $scope.postProtectedArea.$promise.then(function(response) {

            if (response.status === 500) {
                errorCallback(response);
                return;
            }

            // $scope.main.alerts.push({
            //     type: 'success',
            //     duration: 2000,
            //     msg: 'Modificarile au fost salvate.'
            // });
        	$scope.main.successHandlerCollback(response);

            $sessionStorage.editableRow = response;
            $sessionStorage.symbol = response.symbol;
            
            $location.url('/categorie-evenimente?edit=true');
        }, errorCallback);
	};

	$scope.visualization = function(items) {

		angular.forEach($scope.formDropdownData.permissions, function(each, index) {
            if (each.id === items.id) {
				if (items.canView === true) {

					items.canView = true;
	                $scope.items = $resource(url + 'eventcategories/permissions/' + items.id + '/view/enable', {}, {
	                    makeRequest: {
	                        method: 'PUT',
	                        isArray: false
	                    }
	                }).makeRequest($scope.editableInfo);
	                	$scope.items.$promise.then(function(success) {
	                		$scope.main.successHandlerCollback(success);
	                }, errorCallback);


				} else {
					items.canView = false;
	                $scope.items = $resource(url + 'eventcategories/permissions/' + items.id + '/view/disable', {}, {
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

	$scope.register = function(items) {

		angular.forEach($scope.formDropdownData.permissions, function(each, index) {
            if (each.id === items.id) {
				if (items.canAdd === true) {

					items.canAdd = true;
	                $scope.items = $resource(url + 'eventcategories/permissions/' + items.id + '/add/enable', {}, {
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
	                $scope.items = $resource(url + 'eventcategories/permissions/' + items.id + '/add/disable', {}, {
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

	$scope.legislations = function(items) {

		angular.forEach($scope.formDropdownData.legislations, function(each, index) {
            if (each.id === items.id) {
				if (items.active === true) {

					items.active = true;
	                $scope.items = $resource(url + 'eventcategories/' + $sessionStorage.editableRow.id + '/legislations/' + items.id + '/enable', {}, {
	                    makeRequest: {
	                        method: 'PUT',
	                        isArray: false
	                    }
	                }).makeRequest();
	                $scope.items.$promise.then(function(success) {
	                	$scope.main.successHandlerCollback(success);
	                }, errorCallback);


				} else {
					items.active = false;
	                $scope.items = $resource(url + 'eventcategories/' + $sessionStorage.editableRow.id + '/legislations/' + items.id + '/disable', {}, {
	                    makeRequest: {
	                        method: 'PUT',
	                        isArray: false
	                    }
	                }).makeRequest();
	                $scope.items.$promise.then(function(success) {
	                	$scope.main.successHandlerCollback(success);
	                }, errorCallback);

				}
            }
        });    
	};

	$scope.regulations = function(items) {
		angular.forEach($scope.formDropdownData.reglementations, function(each, index) {
            if (each.id === items.id) {
				if (items.active === true) {

					items.active = true;
	                $scope.items = $resource(url + 'eventcategories/' + $sessionStorage.editableRow.id + '/regualtions/' + items.id + '/enable', {}, {
	                    makeRequest: {
	                        method: 'PUT',
	                        isArray: false
	                    }
	                }).makeRequest();
	                $scope.items.$promise.then(function(success) {
	                	$scope.main.successHandlerCollback(success);
	                }, errorCallback);


				} else {
					items.active = false;
	                $scope.items = $resource(url + 'eventcategories/' + $sessionStorage.editableRow.id + '/regualtions/' + items.id + '/disable', {}, {
	                    makeRequest: {
	                        method: 'PUT',
	                        isArray: false
	                    }
	                }).makeRequest();
	                $scope.items.$promise.then(function(success) {
	                	$scope.main.successHandlerCollback(success);
	                }, errorCallback);

				}
            }
        });    
	};

	$scope.saveIdicators = function(indicatorObj) {
		
        var url_indicators = url + 'eventcategories/' + $sessionStorage.editableRow.id + '/performance',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        options.makeRequest.method = 'PUT';

        $scope.putIndicators = $resource(url_indicators ,null,options).makeRequest(indicatorObj);
        $scope.putIndicators.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
            },errorCallback); 
	};

  }]);
	


