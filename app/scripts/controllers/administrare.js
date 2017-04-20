'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AdministrareCtrl
 * @description
 * # AdministrareCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('AdministrareCtrl', ['$scope','getsTableData', 'HelpersService', 'Navigator','$location', '$sessionStorage' , 'appData', '$resource','filterFilter', '$filter',
  	function ($scope,getsTableData, HelpersService, Navigator, $location, $sessionStorage, appData, $resource,filterFilter,$filter) {
  	  	
  	var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.navigatorConfig = Navigator.links;
    delete $scope.session.title;

    $scope.session = $sessionStorage;

    delete $sessionStorage.editableCategoriesEvent;
    delete $sessionStorage.editableCommissariat;
    delete $sessionStorage.editableContact;
    delete $sessionStorage.editableLicenses;
    delete $sessionStorage.title;

	$scope.go = function (path,link) {
      $scope.currentLink = link.url;
    };

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
                key !== 'regulations' && 
                key !== 'authorities' &&  
                key !== 'legislations' && 
                key !== 'organizations' && 
                key !== 'permissionAuthorities' && 
                key !== 'notificationAuthorities' && 
                key !== 'protectedAreaName' && 
                key !== 'organisationId' && 
                key !== 'accountId' && 
                key !== 'licenseId' && 
                key !== 'organisation' && 
                key !== 'isUsed' && 
                key !== 'url' && 
                key !== 'shapeName' && 
                key !== 'geometry' && 
                key !== 'poza') {
                newRow[key] = value;
	            
                if (key === 'poza') {
                    newRow[key] = 'pozaaaaa';
                }
	            if (key === 'description') {
                    console.log('description');
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

    $scope.search = {};
    $scope.search.query = '';

    $scope.populateTabel = function(item) {

        $scope.search.query = '';

        var httpInstance;
        
        if (item.isSettings === true) {
            $scope.isSettingsPage = true;
            $scope.setari = true;
            delete $sessionStorage.addLink;
            $scope.session.title = 'Setari generale';
        } else if (item.isSettings !== true) {
            $scope.isSettingsPage = false;
            $scope.setari = false;
            delete $scope.session.title;
        }

    	if ($scope.hasAccess('access', item)) {
    		// $scope.spinner = true;
    		httpInstance = getsTableData.http(item.link);
			$sessionStorage.addLink = item;
	
			// Reset the selected row - also hides the modify button while nothing is selected
		  	$scope.editableRow = null;
		  	$sessionStorage.editableRow = null;
	
		    httpInstance.then(
		    	function (success) {
                    $scope.tabelData = success.data.items;
                    $scope.headerData = success.data.headers;
                    $scope.markRow(success.data.items[0]);
					// $location.search('link', 'url');

                    $scope.perPage = 20;
                    $scope.maxSize = 5;
                    $scope.setPage = function (pageNo) {
                        $scope.currentPage = pageNo;
                    };

                    $scope.$watch('search', function (term) {
                        var obj = term;
                        $scope.filterList = $filter('filter')($scope.tabelData, obj);
                        $scope.currentPage = 1;
                    });

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
    };

    $scope.activeRow = function(items) {
        $scope.editableRow.active = true;
        // $sessionStorage.editableRow.id = $scope.editableRow.id;
        $scope.items = $resource( url + $sessionStorage.addLink.link + '/' + $scope.editableRow.id + '/enable',{},{
                            makeRequest: {
                                method: 'PUT',
                                isArray: false
                            }
                        }).makeRequest($scope.editableRow);
        $scope.items.$promise.then(function(success) {
                console.log(success);
        },errorCallback);
    };

    $scope.deactiveRow = function(items) {
        $scope.editableRow.active = false;
        // $sessionStorage.editableRow.id = $scope.editableRow.id;
        $scope.items = $resource( url + $sessionStorage.addLink.link + '/' + $scope.editableRow.id + '/disable',{},{
                            makeRequest: {
                                method: 'PUT',
                                isArray: false
                            }
                        }).makeRequest($scope.editableRow);
        $scope.items.$promise.then(function(success) {
                console.log(success);
        },errorCallback);
    };

    $scope.activeRowUser = function(items) {
        $scope.editableRow.active = true;

        $scope.items = $resource(url + 'accounts/' + $sessionStorage.editableRow.accountId + '/enable', {}, {
            makeRequest: {
                method: 'PUT',
                isArray: false
            }
        }).makeRequest($scope.editableRow);
        $scope.items.$promise.then(function(success) {
            console.log(success);
        }, errorCallback);

    };

    $scope.deactiveRowUser = function(items) {

        $scope.editableRow.active = false;

        $scope.items = $resource(url + 'accounts/' + $sessionStorage.editableRow.accountId + '/disable', {}, {
            makeRequest: {
                method: 'PUT',
                isArray: false
            }
        }).makeRequest($scope.editableRow);
        $scope.items.$promise.then(function(success) {
            console.log(success);
        }, errorCallback);

    };

	// POPULATE DEFAULT TABLE
    if ($scope.session.addLink) {
        $scope.populateTabel($scope.session.addLink);
    } else {
        $scope.populateTabel({  
                    link : 'countries',
                    url : '#/adauga-tara',
                    label : 'Tari',
                    title : 'Tari',
                    permissions: {
                        access: ['ROLE_ADMIN_GENERAL'],
                        add: ['ROLE_ADMIN_GENERAL']
                    },
                    adauga : true,
                    modifica : true,
                    sterge : false,
                    activeaza : false,
                    dezactiveaza : false,
                    hartatara : true,
                    hartajudet : true,
                    reset : false
                });
    }

    // SETRAI GENERALE

    $scope.itemObj = {};

    $scope.formDropdownData = {};

    var options = {
        makeRequest: {
            method: 'GET',
            isArray: true
        }
    };

    var url_GetSettings = 'settings';

    $scope.getGeneralSettings = $resource(url + url_GetSettings, null, options).get();

    $scope.getGeneralSettings.$promise.then(function(success) {
        $scope.itemObj = success;
        $scope.itemObj.allowedImageExtensions = success.allowedImageExtensions.toString();
        $scope.itemObj.allowedVideoExtensions = success.allowedVideoExtensions.toString();
        $scope.itemObj.allowedExtensionsOfFile = success.allowedExtensionsOfFile.toString();
    }, errorCallback);

    $scope.sendPost = function(itemObj) {

        var newObj = {
 
                'publicCanReportEventOutsideProtectedArea': itemObj.publicCanReportEventOutsideProtectedArea,
                'adminCanReportEventOutsideProtectedArea': itemObj.adminCanReportEventOutsideProtectedArea,
                'publicCanAttachVideo': itemObj.publicCanAttachVideo,
                'nbOfDaysInvalidEventsAreKept': itemObj.nbOfDaysInvalidEventsAreKept,
                'nbOfDaysEventsAreConsideredValid': itemObj.nbOfDaysEventsAreConsideredValid,
                'nbOfEventsAttachedOnMobile': itemObj.nbOfEventsAttachedOnMobile,
                'maxImageSize': itemObj.maxImageSize,
                'nbOfImageAttachmentsPerEvent': itemObj.nbOfImageAttachmentsPerEvent,
                'allowedImageExtensions': itemObj.allowedImageExtensions.split(","),
                'maxImageResWidth': itemObj.maxImageResWidth,
                'maxImageResHeight': itemObj.maxImageResHeight,
                'maxVideoSize': itemObj.maxVideoSize,
                'nbOfVideoAttachmentsPerEvent': itemObj.nbOfVideoAttachmentsPerEvent,
                'allowedVideoExtensions': itemObj.allowedVideoExtensions.split(","),
                'maxVideoResWidth': itemObj.maxVideoResWidth,
                'maxVideoResHeight': itemObj.maxVideoResHeight,
                'maxSizeOfFile': itemObj.maxSizeOfFile,
                'nbOfAttachmentsOfFilePerEvent': itemObj.nbOfAttachmentsOfFilePerEvent,
                'allowedExtensionsOfFile': itemObj.allowedExtensionsOfFile.split(","),
                'minNbOfPointsForTrail': itemObj.minNbOfPointsForTrail,
                'maxNbOfDaysForTrailSearch': itemObj.maxNbOfDaysForTrailSearch
        };  
        
        var url_setting = url + 'settings',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        options.makeRequest.method = 'POST';

        $scope.postSettings = $resource(url_setting ,null,options).makeRequest(newObj);
        $scope.postSettings.$promise.then(
            function(success) {
                console.log(success);
                $scope.main.successHandlerCollback(success);
            },errorCallback); 
    };


}])

.factory('getsTableData', ['$http','HelpersService', function($http, HelpersService){
	console.log('working sow hard');
    return {
    	http: function(link) {
      		return $http({
        		url: HelpersService.domain + link,
        		method: "GET"
      		});
      	}
    };
}])

// .filter('startFrom', function () {
//     return function (input, start) {
//         if (input) {
//             start = +start;
//             return input.slice(start);
//         }
//         return [];
//     };
// });

.filter('start', function () {
  return function (input, start) {
    if (!input || !input.length) { return; }
    start = +start;
    return input.slice(start);
  };
});