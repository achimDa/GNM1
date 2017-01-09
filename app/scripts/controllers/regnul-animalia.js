'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:RegnulAnimaliaCtrl
 * @description
 * # RegnulAnimaliaCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
.controller('RegnulAnimaliaCtrl', ['$scope','$sessionStorage', '$resource', 'HelpersService',
	function($scope,$sessionStorage, $resource, HelpersService) {

		var url = HelpersService.domain,
        	errorCallback = $scope.main.errorHandlerCollback;

		$scope.session = $sessionStorage;
		$scope.session.title = 'Regnul Animalia';
		
		// POPULATE DEFAULT TABLE
		$scope.tabelData = [];
		$scope.items = $resource(url + 'animals', {}, {
			makeRequest: {
				method: 'GET',
				isArray: false
			}
		}).makeRequest();
		$scope.items.$promise.then(
			
			function(success) {
				$scope.tabelData = success;

				// PAGINATION
				$scope.viewby = 15;
				$scope.totalItems = success.items.length;
				$scope.currentPage = 1;
				$scope.itemsPerPage = $scope.viewby;
				$scope.maxSize = 5; //Number of pager buttons to show
				$scope.markRow(success.items[0]);

				$scope.setPage = function(pageNo) {
					$scope.currentPage = pageNo;
				};

				$scope.pageChanged = function() {
				};
				$scope.setItemsPerPage = function(num) {
					$scope.itemsPerPage = num;
					$scope.currentPage = 1; //reset to first paghe
				};

			},errorCallback); 

		$scope.markRow = function(items) {
	    	$scope.editableRow = items;
	    	$sessionStorage.editableRow = $scope.editableRow;
	    };

	    $scope.activeRow = function(items) {
	        $scope.editableRow.active = true;
	        $scope.items = $resource( url + 'animals' + '/' + $scope.editableRow.id + '/enable',{},{
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
	        $scope.items = $resource( url + 'animals' + '/' + $scope.editableRow.id + '/disable',{},{
	                            makeRequest: {
	                                method: 'PUT',
	                                isArray: false
	                            }
	                        }).makeRequest($scope.editableRow);
	        $scope.items.$promise.then(function(success) {
	        },errorCallback);
	    };
	}
])

.filter('YesNo', function(){
	return function(text){
	  return text ? "Da" : "Nu";
	};
});
