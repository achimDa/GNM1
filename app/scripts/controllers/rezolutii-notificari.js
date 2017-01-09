'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:RezolutiiNotificariCtrl
 * @description
 * # RezolutiiNotificariCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
.controller('RezolutiiNotificariCtrl', ['$scope','getsTableData', 'HelpersService', 'Notificari','$location', '$sessionStorage' , 'appData', '$resource','$filter',
  	function ($scope,getsTableData, HelpersService, Notificari, $location, $sessionStorage, appData, $resource,$filter) {
  	  	
	  	
	  	var url = HelpersService.domain,
	        errorCallback = $scope.main.errorHandlerCollback;

	  	$scope.navigatorConfig = Notificari.links;
	  	$scope.session = $sessionStorage;

	  	var sorting = [];
	  	sorting[0] = sorting[1] = sorting[2] = sorting[3] = sorting[4] = sorting[5] = sorting[6] = sorting[7] = sorting[8] = 1;

		$scope.hasToRender = function (key,td,type,strType) {

	    	var str = td,
	    		icon = false,
	    		color = false;

	    	if ((typeof td === 'object') && (type === 'object')) {
	    		return true;
	    	} else if ((typeof td === 'boolean') && (type === 'boolean')) {
	    		return true;
	    	} else if ((typeof td === 'number') && (type === 'number')) {
	    		if (type === 'number') {
	    			return true;
	    		} else if (type === 'Date') {
	    			return true;
	    		} 
	    	} else if ((typeof td === 'string') && (type === 'string')) {
		    	
		    	if (str.indexOf('#') > -1) {
					color = true;
				} else if (str.indexOf('fa fa-') > -1) {
					icon = true;
				}

				if ((strType === 'icon') && (icon === true)) {
					return true;
				} else if ((strType === 'color') && (color === true)) {
					return true;
				} else if ((strType === 'text') && (color !== true) && (icon !== true)) {
					return true;
				} 
			}	
	    };
	    
	    $scope.filterTdId = function(row) {
	        var newRow = {};
	    	$scope.counter = $scope.counter + 1;

	        angular.forEach(row, function(value, key) {
	            if (key !== 'id' &&
	            	key !== 'resolutionStatus' &&
	            	key !== 'canValidate' &&
	            	key !== 'ecoCorridorId') {
	                newRow[key] = value;
		            
		            if (key === 'poza') {
		                newRow[key] = 'pozaaaaa';
		            }
	            }
	        });

		    if ($scope.counter < 3 ) {
		    	// console.log('row: ',row);
		    	// console.log('newRow: ',newRow);
		    }

	        return newRow;
	    };

		$scope.hasAccess = function(toWhat, onItem) {
	    	var accessLevel = $sessionStorage.user.authority;

	    	if (onItem.permissions) {
	    		if (onItem.permissions[toWhat]) {
	    			return (onItem.permissions[toWhat].indexOf(accessLevel) !== -1);
	    		} else {
	    			// If the edit\deactivate permission does not exist, consider it the same with the add\activate permission
	    			if (toWhat === 'edit' && onItem.permissions.add) {
	    				return (onItem.permissions.add.indexOf(accessLevel) !== -1);
	    			} else if (toWhat === 'deactivate' && onItem.permissions.activate) {
	    				return (onItem.permissions.activate.indexOf(accessLevel) !== -1);
	    			}
	    		}
	    	}
	    	return false;
	    };

	    var orderDirection = function (index) {
			sorting[index]++;
			if (sorting[index] % 2 !== 0) {
				return "ASC";
			} else {
				return "DESC";
			}
	    };

		$scope.populateTabel = function(item) {
	    	var httpInstance;
	    	if ($scope.hasAccess('access', item)) {

	    		if (($sessionStorage.user.authority === 'ROLE_ADMIN_GENERAL') ||
	    			($sessionStorage.user.authority === 'ROLE_COMISAR_JUDETEAN') ||
	    			($sessionStorage.user.authority === 'ROLE_COMISAR_GNM')) {
	    			httpInstance = getsTableData.http(item.link2);
	    		} else {
	    			httpInstance = getsTableData.http(item.link);
	    		}

    			$sessionStorage.addLink = item;
    	
    			// Reset the selected row - also hides the modify button while nothing is selected
    		  	$scope.editableRow = null;
    		  	$sessionStorage.editableRow = null;
    	
    		    httpInstance.then(
    		    	function (success) {

    		    		angular.forEach(success.data.items, function(each){
		    				$scope.canValidate = each.canValidate;
				      	});

    			        $scope.tabelData = success.data.items;
    			        $scope.headerData = success.data.headers;
                        $scope.markRow(success.data.items[0]);
    	
    					// PAGINATION

    					$scope.perPage = 15;
	                    $scope.maxSize = 5;
	                    $scope.setPage = function (pageNo) {
	                        $scope.currentPage = pageNo;
	                    };

	                    $scope.$watch('search', function (term) {
	                        var obj = term;
	                        $scope.filterList = $filter('filter')($scope.tabelData, obj);
	                        $scope.currentPage = 1;
	                    });

    					$scope.changeSorting = function(columnHeader) {
 
 							var srcHeaders,
 								srcStatus,
 								headers,
 								order;

 							if ($sessionStorage.addLink.label === 'Fara rezolutie') {
 								srcStatus = 'PENDING';
 							} else if ($sessionStorage.addLink.label === 'Validate') {
 								srcStatus = 'VALID';
 							} else if ($sessionStorage.addLink.label === 'Invalidate') {
 								srcStatus = 'INVALID';
 							}

 							if (columnHeader === 'Prioritate categorie eveniment') {
 								srcHeaders = 'priority';

						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(0)
								};
 							} else if (columnHeader === 'ID eveniment') {
 								srcHeaders = 'eventId';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(1)
								};
 							} else if (columnHeader === 'Categorie Eveniment') {
 								srcHeaders = 'eventCategoryName';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(2)
								};
 							} else if (columnHeader === 'Data eveniment') {
 								srcHeaders = 'eventDate';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(3)
								};
 							} else if (columnHeader === 'Arie Protejata') {
 								srcHeaders = 'protectedAreaName';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(4)
								};
 							} else if (columnHeader === 'Administrator arie protejata - Validat de') {
 								srcHeaders = 'protectedAreaAdministrator';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(5)
								};
 							} else if (columnHeader === 'Administrator arie protejata - Validat la') {
 								srcHeaders = 'protectedAreaResolutionDate';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(6)
								};
 							} else if (columnHeader === 'Comisar judetean - Validat de') {
 								srcHeaders = 'countyAdministrator';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(7)
								};
 							} else if (columnHeader === 'Comisar judetean - Validat la') {
 								srcHeaders = 'countyResolutionDate';
						        headers = {
									'eventResolutionCountyEnum' : srcHeaders,
									'resolutionStatus' : srcStatus,
									'order' : orderDirection(8)
								};
 							} else {
 								return;
 							}
						        
					        var url_headers = url + 'events/county/search',
					            options = {
					                makeRequest: {
					                    isArray: false
					                }
					            };

				            options.makeRequest.method = 'POST';

					        $scope.postHeaders = $resource(url_headers ,null,options).makeRequest(headers);
					        $scope.postHeaders.$promise.then(
					            function(success) {
					            	console.log(success);
					            	$scope.tabelData = success.items;
					            },errorCallback); 

					    };
    	
    			    }, errorCallback);
    	
    		    $scope.select = function(item) {
    				$scope.county = item;
    	    	};
    	
    		    $scope.county = {};
    		}
		};

	    $scope.showPage = true;
    
		$scope.markRow = function(row) {
			$scope.editableRow = row;
			$sessionStorage.editableRow = $scope.editableRow;
			console.log(row);
		};

		$scope.cancelEco = function(row) {
	    	$scope.items = $resource(HelpersService.domain + 'ecocorridors/resolutions/' + row.id + '/pending',{},{
				makeRequest: {
					method: 'PUT',
					isArray: false
				}
			}).makeRequest();

	    	$scope.items.$promise.then(
				function(success) {

					var arr = $scope.tabelData.items;
					var newArray = [];

					angular.forEach(arr, function(obj) {
					
						if (obj.id !== row.id) {
							newArray.push(obj);
						}
					
						$scope.tabelData.items = newArray;
					});
					$scope.main.successHandlerCollback(success);
				}, errorCallback);
	  	};

		$scope.removeEco = function(row) {
	    	$scope.items = $resource(HelpersService.domain + '/ecocorridors/resolutions/' + row.id,{},{
				makeRequest: {
					method: 'DELETE',
					isArray: false
				}
			}).makeRequest();

	    	$scope.items.$promise.then(
				function(success) {

					var arr = $scope.tabelData.items;
					var newArray = [];

					angular.forEach(arr, function(obj) {
					
						if (obj.id !== row.id) {
							newArray.push(obj);
						}
					
						$scope.tabelData.items = newArray;
					});
					$scope.main.successHandlerCollback(success);
				}, errorCallback);
	  	};

	  	$scope.cancelEvent = function(row) {
	    	$scope.items = $resource(HelpersService.domain + 'events/county/pending/' + row.id,{},{
				makeRequest: {
					method: 'PUT',
					isArray: false
				}
			}).makeRequest();

	    	$scope.items.$promise.then(
				function(success) {

					var arr = $scope.tabelData.items;
					var newArray = [];

					angular.forEach(arr, function(obj) {
					
						if (obj.id !== row.id) {
							newArray.push(obj);
						}
					
						$scope.tabelData.items = newArray;
					});
					$scope.main.successHandlerCollback(success);
				}, errorCallback);
	  	};

		$scope.removeEvent = function(row) {
	    	$scope.items = $resource(HelpersService.domain + 'events/county/' + row.id,{},{
				makeRequest: {
					method: 'DELETE',
					isArray: false
				}
			}).makeRequest();

	    	$scope.items.$promise.then(
				function(success) {

					var arr = $scope.tabelData.items;
					var newArray = [];

					angular.forEach(arr, function(obj) {
					
						if (obj.id !== row.id) {
							newArray.push(obj);
						}
					
						$scope.tabelData.items = newArray;
					});
					$scope.main.successHandlerCollback(success);
				}, errorCallback);
	  	};

		// POPULATE DEFAULT TABLE
	    if ($scope.session.addLink) {
	        $scope.populateTabel($scope.session.addLink);
	    } else {
	        $scope.populateTabel({	
					link : 'events/protectedarea/noresolution',
					link2 : 'events/county/noresolution',
					label : 'Fara rezolutie',
					title : 'Fara rezolutie',
					reseco : false, 
					resev : true, 
					cancelE : false,
					cancelC : false,
					stergeE : true,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				});
	    }

}])

.filter('start', function () {
  return function (input, start) {
    if (!input || !input.length) { return; }
    start = +start;
    return input.slice(start);
  };
});

// .factory('itemsCounty', ['$http', function($http){
// 	var itemsCounty = {
// 	itemDetails: function() {
// 	  return $http({
// 	    url: "data/countyList.json",
// 	    method: "GET"
// 	  })
// 	  .then(function (response) {
// 	    return response.data;
// 	    });
// 	  }
// 	};
// 	return itemsCounty;
// }]);