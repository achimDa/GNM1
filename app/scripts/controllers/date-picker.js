'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:EventsCtrl
 * @description
 * # EventsCtrl
 * Controller of the gnmApp
 */

angular.module('gnmApp')
	.controller('Datepicker', function($scope, Constant) {

		// $scope.selection = {
		//     ids: {"50d5ad": true}
		// };

		$scope.categories = Constant;

		// $scope.categories = [ { "name": "Sport", "id": "50d5ad" } , {"name": "General", "id": "678ffr" } , {"name": "Test", "id": "678ffr" }, {"name": "Bla", "id": "678ffr" }];

		// Datepicker

		$scope.today = function() {
			$scope.dt = new Date();
		};
		$scope.today();

		$scope.clear = function() {
			$scope.dt = null;
		};

		// Disable weekend selection
		$scope.disabled = function(date, mode) {
			return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
		};

		$scope.toggleMin = function() {
			$scope.minDate = $scope.minDate ? null : new Date();
		};
		$scope.toggleMin();
		$scope.maxDate = new Date(2020, 5, 22);

		$scope.open = function($event) {
			$scope.status.opened = true;
		};

		$scope.setDate = function(year, month, day) {
			$scope.dt = new Date(year, month, day);
		};

		$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
		};

		$scope.formats = ['dd-MM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		$scope.format = $scope.formats[0];

		$scope.status = {
			opened: false
		};

		var tomorrow = new Date();
		tomorrow.setDate(tomorrow.getDate() + 1);
		var afterTomorrow = new Date();
		afterTomorrow.setDate(tomorrow.getDate() + 2);
		$scope.events = [{
			date: tomorrow,
			status: 'full'
		}, {
			date: afterTomorrow,
			status: 'partially'
		}];

		$scope.getDayClass = function(date, mode) {
			if (mode === 'day') {
				var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

				for (var i = 0; i < $scope.events.length; i++) {
					var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

					if (dayToCheck === currentDay) {
						return $scope.events[i].status;
					}
				}
			}

			return '';
		};


		$scope.runMarker = function() {

			jQuery('.results-table').show();

			jQuery('#mapO').addClass('mapSmall');

			// $scope.markers = {
			//   coords: {
			//     lat: 46.7721,
			//     lng: 23.5922,
			//   }
			// }

			$scope.markerList = [{
				lat: 46.7821,
				lng: 23.5222,
			}, {
				lat: 46.7752,
				lng: 23.5622,
			}, {
				lat: 46.7621,
				lng: 23.6222,
			}];



			// var marker = L.marker([46.7721, 23.5922]).addTo(map);
			// marker.bindPopup("<b>Hello world!</b><br>I am a popup.").openPopup();

			// var marker = L.marker([46.7750, 23.5600]).addTo(map);
			// marker.bindPopup("<b>Hello world!</b><br>I am a popup.").openPopup();
		};

	});
