'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:EventsCtrl
 * @description
 * # EventsCtrl
 * Controller of the gnmApp
 */

angular.module('gnmApp')
	.controller('EventsCtrl', ['$scope', 'Constant', 'HelpersService', '$http', '$q', '$resource', '$sessionStorage', 'Go', '$window',
		function ($scope, Constant, HelpersService, $http, $q, $resource, $sessionStorage, Go, $modalInstance, $window) {

			var url = HelpersService.domain,
				errorCallback = $scope.main.errorHandlerCollback;

			$scope.session = $sessionStorage;
			$scope.categories = Constant;
			$scope.itemObj = {};
			$scope.session.title = 'Evenimente';
			$scope.formDropdownData = {};

			// //EXTEND API 
			var promises = [];
			var MyData = {},
				urls = [
					url + 'organisations/all/administrators',
					url + 'protectedareas/custody',
					url + 'accounts/organisations/rangers',
					url + 'eventcategories/view',
					url + 'animalcategories',
					url + 'animalsizes',
					url + 'animalspecies',
					url + 'animals'
				];

			$scope.result = {};
			for (var i = 0; i < urls.length; i++) {
				var promise = $http.get(urls[i]);
				promises.push(promise);
			}

			$q.all(promises).then(function (values) {
				angular.forEach(values, function (value) {
					var str = value.config.url,
						res = str.split("/"),
						apiSplit = res[res.length - 1];

					if (str.indexOf('eventcategories/view') > -1) {
						$scope.formDropdownData.eventcategory = value.data.items;
					} else if (str.indexOf('animalcategories') > -1) {
						$scope.formDropdownData.category = value.data.items;
					} else if (str.indexOf('animalsizes') > -1) {
						$scope.formDropdownData.size = value.data.items;
					} else if (str.indexOf('animalspecies') > -1) {
						$scope.formDropdownData.species = value.data.items;
					} else if (str.indexOf('animals') > -1) {
						$scope.formDropdownData.animale = value.data.items;
					} else if (str.indexOf('organisations/all/administrators') > -1) {
						$scope.formDropdownData.administrators = value.data.items;
					} else if (str.indexOf('protectedareas/custody') > -1) {
						$scope.formDropdownData.custody = value.data.items;
					} else if (str.indexOf('accounts/organisations/rangers') > -1) {
						$scope.formDropdownData.ranger = value.data.items;
					}
					//$scope.result = $scope.result.concat(value.data);
					angular.extend($scope.result, value.data.items);
				});
				// console.log($scope.result);
				// console.log('values',values[0]);
			}, errorCallback);

			angular.extend($scope, {
				center: {
					autoDiscover: true,
					zoom: 11
				},
				defaults: {
					scrollWheelZoom: false
				},
				markers: {},
				layers: {
					baselayers: {
						osm: {
							name: 'OpenStreetMap',
							url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
							type: 'xyz'
						},
					},
					doRefresh: true,
					overlays: {}
				}
			});

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


			$scope.searchEvent = function () {

				var search = {

					'protectedAreaAdminsId': [],
					'protectedAreasId': [],
					'rangersId': [],
					'eventStatusId': [],
					'eventCategoriesId': [],
					'animalCategoryId': [],
					'animalSizeId': [],
					'animalSpecieId': [],
					'animalId': [],
					'isMigratory': [],
					'startDate': $scope.itemObj.startDate,
					'endDate': $scope.itemObj.endDate

				};

				angular.forEach($scope.formDropdownData.administrators, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaAdminsId.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.custody, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreasId.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.ranger, function (each, index) {
					if (each.isChecked === true) {
						search.rangersId.push(each.id);
					}
				});

				angular.forEach($scope.categories.stare, function (each, index) {
					if (each.isChecked === true) {
						search.eventStatusId.push(each.value);
					}
				});

				angular.forEach($scope.formDropdownData.eventcategory, function (each, index) {
					if (each.isChecked === true) {
						search.eventCategoriesId.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.category, function (each, index) {
					if (each.isChecked === true) {
						search.animalCategoryId.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.size, function (each, index) {
					if (each.isChecked === true) {
						search.animalSizeId.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.species, function (each, index) {
					if (each.isChecked === true) {
						search.animalSpecieId.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animale, function (each, index) {
					if (each.isChecked === true) {
						search.animalId.push(each.id);
					}
				});

				angular.forEach($scope.categories.migrator, function (each, index) {
					if (each.isChecked === true) {
						search.isMigratory.push(each.value);
					}
				});

				var url_element = url + 'events/search',
					options = {
						makeRequest: {
							isArray: false
						}
					};

				options.makeRequest.method = 'POST';

				$scope.postElement = $resource(url_element, null, options).makeRequest(search);
				$scope.postElement.$promise.then(
					function (success) {

						console.log(success);

						jQuery('.results-table').show();

						jQuery('#mapO').addClass('mapSmall');

						$scope.formDropdownData.events = success.items;

						$scope.markEvent = function (items) {
							$scope.editableRow = items;
							$sessionStorage.editableRow = $scope.editableRow;
							console.log(items);
						};

					}, errorCallback);

			};

			$scope.markEvent = function (items) {
				$scope.editableRow = items;
				$sessionStorage.editableRow = $scope.editableRow;
				console.log('MARKER', items);
			};

			$scope.markers = {};

			$scope.checkAll = function (items) {

				angular.forEach($scope.formDropdownData.events, function (items) {
					items.selected = $scope.selectedAll;

					$scope.editableRow = items;
					$sessionStorage.editableRow = $scope.editableRow;

					var mainMarker = {
						lat: items.latitude,
						lng: items.longitude,
						focus: true,
						//message: '<a href="#/add-event?edit=true" class="popUpButton">' +  items.eventCategory + '<br>' + items.description + '<br>' + items.eventDate + '</a>',
						// message: '<button type="button" class="popUpButton" ng-click="markerEventPage()">' +  $sessionStorage.editableRow.eventCategory + '<br>' + $sessionStorage.editableRow.description + '<br>' + $sessionStorage.editableRow.eventDate + '</button>',
						draggable: false,
					};

					if ($scope.selectedAll) {
						$scope.selectedAll = true;
						$scope.markers[items.eventId] = mainMarker;
						$scope.editableRow = items;
					} else {
						$scope.selectedAll = false;
						delete $scope.markers[items.eventId];
					}
				});

			};

			$scope.$on('leafletDirectiveMarker.click', function (e, args) {
				// Args will contain the marker name and other relevant information
				console.log("Leaflet Click", e, args);
				$sessionStorage.editableRow.eventId = args.modelName;
			});

			$scope.showMarkers = function (items, marker) {
				// console.log(items);
				$scope.editableRow = items;
				$sessionStorage.editableRow = $scope.editableRow;

				var mainMarker = {
					lat: $sessionStorage.editableRow.latitude,
					lng: $sessionStorage.editableRow.longitude,
					focus: true,
					message: '<a href="#/add-event?edit=true" class="popUpButton">' +
					$sessionStorage.editableRow.eventCategory +
					'<br>' +
					$sessionStorage.editableRow.description +
					'<br>' + $sessionStorage.editableRow.eventDate +
					'</a>',

					// message: '<a class="popUpButton" ng-click="markerEventPage()"></a>',
					draggable: false
				};

				if (items.selected === true) {
					$scope.markers[items.eventId] = mainMarker;
					$scope.editableRow = items;
					$sessionStorage.editableRow = $scope.editableRow;
				} else {
					delete $scope.markers[items.eventId];
				}
				// console.log('MARKER',items);

			};

			$scope.downloadCsv = function () {

				var eventIds = '';

				angular.forEach($scope.formDropdownData.events, function (each, index) {

					if (each.selected === true) {
						if (eventIds !== '') {
							eventIds = eventIds + ',';
						}
						eventIds = eventIds + each.eventId;
					}
				});

				$scope.fileUrl = url + 'events/csv/' + '[' + eventIds + ']';
			};

			if ($sessionStorage.editableRow) {
				$scope.clean = function () {
					delete $sessionStorage.editableRow;
					delete $sessionStorage.editableContactsMedia;
					// delete $sessionStorage.addLink;
				};
			}

		}])

	.filter('YesNo2', function () {
		return function (text) {
			// return text ? "Da" : "Nu";
			if (text === null) {
				return "";
			} else if (text === true) {
				return "Da";
			} else {
				return "Nu";
			}
		};
	});


