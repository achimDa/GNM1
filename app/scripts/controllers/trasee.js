'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:TraseeCtrl
 * @description
 * # TraseeCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
	.controller('TraseeCtrl', ['$scope', '$http', '$resource', 'HelpersService', '$sessionStorage', '$location', 'Go', '$log', '$timeout', '$window', '$sce', 'ServiceDetaliiCoridorEcologicCSV', 'ServiceTraseePDF', 'Constant',
		function ($scope, $http, $resource, HelpersService, $sessionStorage, $location, Go, $log, $timeout, $window, $sce, ServiceDetaliiCoridorEcologicCSV, ServiceTraseePDF, Constant) {

			var url = HelpersService.domain,
				errorCallback = $scope.main.errorHandlerCollback;

			$scope.raportObj = {};
			$scope.formDropdownData = {};

			$scope.categories = Constant;

			var state;

			$scope.type = function (value) {
				if (value.type === '1') {
					state = 1;
					$scope.generalTable = true;
					$scope.area = false;
					$scope.admin = false;
					$scope.tabelData = [];
				} else if (value.type === '2') {
					state = 2;					
					$scope.generalTable = false;
					$scope.area = true;
					$scope.admin = false;
					$scope.tabelData = [];
				} else {
					state = 3;					
					$scope.generalTable = false;
					$scope.area = false;
					$scope.admin = true;
					$scope.tabelData = [];
				}
			};

			var gestiuneObj;

			// $scope.session.title = 'Fisa de gestiune';

			var areaAdmin = function () {
				var areaAdmin = url + '/organisations/all/administrators';
				$http.get(areaAdmin).then(function (success) {
					$scope.formDropdownData.areaAdmin = success.data.items;
				});
			};
			areaAdmin();

			var protectedArea = function () {
				var protectedArea = url + '/protectedareas/custody';
				$http.get(protectedArea).then(function (success) {
					$scope.formDropdownData.protectedArea = success.data.items;
				});
			};
			protectedArea();
			
			var ranger = function () {
				var ranger = url + '/accounts/organisations/rangers';
				$http.get(ranger).then(function (success) {
					$scope.formDropdownData.ranger = success.data.items;
				});
			};
			ranger();

			$scope.runRaport = function (raportObj) {

				var search = {
					'protectedAreaAdminIds': [],
					'protectedAreaIds': [],
					'rangerIds': [],
					'status': raportObj.type,
					'startDate': raportObj.startDate,
					'endDate': raportObj.endDate
				};


				angular.forEach($scope.formDropdownData.areaAdmin, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaAdminIds.push(each.id);
					}
				});
				
				angular.forEach($scope.formDropdownData.protectedArea, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaIds.push(each.id);
					}
				});
				
				angular.forEach($scope.formDropdownData.ranger, function (each, index) {
					if (each.isChecked === true) {
						search.rangerIds.push(each.id);
					}
				});

				var url_Trasee,
					options = {
						makeRequest: {
							isArray: false
						}
					};

				if (state === 1) {
					url_Trasee = url + 'reports/activity-details';
				} else if (state === 2) {
					url_Trasee = url + 'reports/activity-details-area';
				} else {
					url_Trasee = url + 'reports/activity-details-admin';
				}
				

				options.makeRequest.method = 'POST';

				$scope.authTransport = $resource(url_Trasee, null, options).makeRequest(search);
				$scope.authTransport.$promise.then(function (success) {
					$scope.tabelData = success.items;
				}, errorCallback);
			};

			$scope.downloadRaport = function (raportObj, stringValue) {

				var search = {
					'protectedAreaAdminIds': [],
					'protectedAreaIds': [],
					'rangerIds': [],
					'status': raportObj.type,
					'startDate': raportObj.startDate,
					'endDate': raportObj.endDate
				};

				angular.forEach($scope.formDropdownData.areaAdmin, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaAdminIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.protectedArea, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.ranger, function (each, index) {
					if (each.isChecked === true) {
						search.rangerIds.push(each.id);
					}
				});

				var filePdf,
					a;

				if (state === 1) {
					filePdf = 'reports/activity-details-pdf';
				} else if (state === 2) {
					filePdf = 'reports/activity-details-area-pdf';
				} else {
					filePdf = 'reports/activity-details-admin-pdf';
				}

				a = document.createElement("a");
				document.body.appendChild(a);
				a.style = "display: none";
				ServiceTraseePDF.downloadPdf(search,state,url).then(function (result) {
					var pdf = new Blob([result.data], { type: 'application/pdf' });
					var fileURL = URL.createObjectURL(pdf);
					a.href = fileURL;
					a.download = filePdf;
					a.click();
				});
				
			};
		}])

	.factory("ServiceTraseePDF", ["$http", "HelpersService", function (a, b) {
		var c = b.domain;
		return {
			downloadPdf: function (search,state,url) {
				var filePdfUrl;
				if (state === 1) {
					filePdfUrl = url + 'reports/activity-details-pdf';
				} else if (state === 2) {
					filePdfUrl = url + 'reports/activity-details-area-pdf';
				} else {
					filePdfUrl = url + 'reports/activity-details-admin-pdf';
				}

				return a.post(filePdfUrl, search, {
					headers: {
						'Accept': 'application/pdf'
					},
					responseType: 'arraybuffer'
				})
					.then(function (a) {
						return a;
					});
			}
		};
	}

	]);
