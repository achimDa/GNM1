'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:TraseeCtrl
 * @description
 * # TraseeCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
	.controller('TraseeCtrl', ['$scope', '$http', '$resource', 'HelpersService', '$sessionStorage', '$location', 'Go', '$log', '$timeout', '$window', '$sce', 'ServiceDetaliiCoridorEcologicCSV', 'ServiceDetaliiCoridorEcologicPDF', 'Constant',
		function ($scope, $http, $resource, HelpersService, $sessionStorage, $location, Go, $log, $timeout, $window, $sce, ServiceDetaliiCoridorEcologicCSV, ServiceDetaliiCoridorEcologicPDF, Constant) {

			var url = HelpersService.domain,
				errorCallback = $scope.main.errorHandlerCollback;

			$scope.raportObj = {};
			$scope.formDropdownData = {};

			$scope.categories = Constant;

			var gestiuneObj;

			// $scope.session.title = 'Fisa de gestiune';

			var protectedArea = function () {
				var protectedArea = url + '/protectedareas/custody';
				$http.get(protectedArea).then(function (success) {
					$scope.formDropdownData.protectedArea = success.data.items;
				});
			};
			protectedArea();

			$scope.runRaport = function (raportObj) {

				var search = {
					'protectedAreaIds': [],
					'status': raportObj.type,
					'startDate': Date.parse(raportObj.startDate),
					'endDate': Date.parse(raportObj.endDate)
				};


				angular.forEach($scope.formDropdownData.protectedArea, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaIds.push(each.id);
					}
				});

				var url_authTransport = url + 'reports/eco-corridor-details',
					options = {
						makeRequest: {
							isArray: false
						}
					};

				options.makeRequest.method = 'POST';

				$scope.authTransport = $resource(url_authTransport, null, options).makeRequest(search);
				$scope.authTransport.$promise.then(function (success) {
					// $scope.main.alerts.push({ type: 'success', duration : 2000, msg: 'Modificarile au fost salvate.' });

					$scope.tabelData = success.items;

					console.log(success.items);
					// $location.url('/formulare-valide-transport-deseuri');
				}, errorCallback);
			};

			$scope.downloadRaport = function (raportObj, stringValue) {

				var search = {
					'eventCategoryIds': [],
					'status': raportObj.type,
					'startDate': Date.parse(raportObj.startDate),
					'endDate': Date.parse(raportObj.endDate)
				};

				angular.forEach($scope.formDropdownData.protectedArea, function (each, index) {
					if (each.isChecked === true) {
						search.protectedAreaIds.push(each.id);
					}
				});

				// sanctiuneObj = { 'params' : raport }; 

				var filePdf = 'eco-corridor-details-pdf',
					fileCsv = 'eco-corridor-details.csv',
					a;

				if (stringValue === 'raportPdf') {
					a = document.createElement("a");
					document.body.appendChild(a);
					a.style = "display: none";
					ServiceTraseePDF.downloadPdf(search).then(function (result) {
						var pdf = new Blob([result.data], { type: 'application/pdf' });
						var fileURL = URL.createObjectURL(pdf);
						a.href = fileURL;
						a.download = filePdf;
						a.click();
					});
				} else if (stringValue === 'raportCsv') {
					a = document.createElement("a");
					document.body.appendChild(a);
					ServiceDetaliiCoridorEcologicCSV.downloadCsv(search).then(function (result) {
						var csv = new Blob([result.data], { type: 'application/csv' });
						var fileURL = URL.createObjectURL(csv);
						a.href = fileURL;
						a.download = fileCsv;
						a.click();
					});
				}
			};
		}])

	.factory("ServiceTraseePDF", ["$http", "HelpersService", function (a, b) {
		var c = b.domain;
		return {
			downloadPdf: function (search) {
				return a.post(c + "reports/eco-corridor-details-pdf", search, {
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
