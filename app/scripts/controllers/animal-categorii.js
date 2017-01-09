'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AnimalSpeciiCtrl
 * @description
 * # AnimalSpeciiCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
	.controller('AnimalSpeciiCtrl', ['$scope', '$resource', 'HelpersService', '$location', '$sessionStorage',
		function($scope, $resource, HelpersService, $location, $sessionStorage) {

			$scope.itemObj = {};
			if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
				$scope.itemObj = $sessionStorage.editableRow;
			}
			var url = HelpersService.domain;

			$scope.sendPost = function(itemObj) {

				console.log('itemObj', itemObj);
				$scope.items = $resource(url + 'animalspecies', {}, {
					makeRequest: {
						method: 'POST',
						isArray: false
					}
				}).makeRequest(itemObj);
				$scope.items.$promise.then(
					function(success) {
						console.log('success');
						console.log(success);
						location.reload();

					},
					function(error) {
						console.log(error);
					}
				);
			};

		}
	]);
