'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:DetaliiCoridorEcologicCtrl
 * @description
 * # DetaliiCoridorEcologicCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('DetaliiCoridorEcologicCtrl', ['$scope','$http','$resource','HelpersService','$sessionStorage','$location','Go', '$log','$timeout','$window','$sce','ServiceDetaliiCoridorEcologicCSV','ServiceDetaliiCoridorEcologicPDF','Constant',
  		function ($scope,$http,$resource,HelpersService,$sessionStorage,$location,Go,$log,$timeout,$window,$sce,ServiceDetaliiCoridorEcologicCSV,ServiceDetaliiCoridorEcologicPDF,Constant) {

  			var url = HelpersService.domain,
        		errorCallback = $scope.main.errorHandlerCollback;

    		$scope.raportObj = {};
    		$scope.formDropdownData = {};

    		$scope.categories  = Constant;

    		var gestiuneObj;

    		// $scope.session.title = 'Fisa de gestiune';

    		var ecocorridorTypes = function () {
    			var ecocorridorTypes = url + '/ecocorridortypes/view';
    			$http.get(ecocorridorTypes).then(function(success){
    				$scope.formDropdownData.ecocorridorTypes = success.data.items;
    			});
    		};
    		ecocorridorTypes();    		

    		var protectedArea = function () {
    			var protectedArea = url + '/protectedareas/custody';
    			$http.get(protectedArea).then(function(success){
    				$scope.formDropdownData.protectedArea = success.data.items;
    			});
    		};
    		protectedArea();

    		var animalCategory = function () {
    			var animalCategory = url + 'animalcategories';
    			$http.get(animalCategory).then(function(success){
    				$scope.formDropdownData.animalCategory = success.data.items;
    			});
    		};
    		animalCategory();

    		var animalSpecies = function () {
    			var animalSpecies = url + 'animalspecies';
    			$http.get(animalSpecies).then(function(success){
    				$scope.formDropdownData.animalSpecies = success.data.items;
    			});
    		};
    		animalSpecies();

    		var animalSize = function () {
    			var animalSize = url + 'animalsizes';
    			$http.get(animalSize).then(function(success){
    				$scope.formDropdownData.animalSize = success.data.items;
    			});
    		};
    		animalSize();

    		var animal = function () {
    			var animal = url + 'animals';
    			$http.get(animal).then(function(success){
    				$scope.formDropdownData.animal = success.data.items;
    			});
    		};
    		animal();

			var currentDate = new Date();

			var minStartDateParse = Date.parse(currentDate) - 2629746000;
			$scope.maxStartDate = currentDate;

			$scope.raportObj.startDate = new Date(minStartDateParse);
			$scope.raportObj.endDate = new Date();

			$scope.changeStartDate = function (data) {
				var currendDate = new Date();
				var startDate = Date.parse(data);
				var endDate = Date.parse($scope.raportObj.endDate);
				var delta = endDate - startDate;

				if (delta < 0) {
					if (startDate + 2629746000 > Date.parse(currendDate)) {
						$scope.raportObj.endDate = currendDate;
					} else {
						$scope.raportObj.endDate = new Date(startDate + 2629746000);
					}
				} else if (Date.parse($scope.raportObj.endDate) - Date.parse(data) > 2629746000) {
					$scope.raportObj.endDate = new Date(Date.parse(data) + 2629746000);
				}
			};

			$scope.changeEndDate = function (data) {
				var currendDate = new Date();
				var startDate = Date.parse($scope.raportObj.startDate);
				var endDate = Date.parse(data);
				var delta = endDate - startDate;

				if (delta < 0) {
					$scope.raportObj.startDate = new Date(endDate - 2629746000);
				} else if (Date.parse(data) - Date.parse($scope.raportObj.startDate) > 2629746000) {
					$scope.raportObj.startDate = new Date(Date.parse(data) - 2629746000);
				}
			};

    		$scope.runRaport = function(raportObj) {
	    		
	    		var search = {
					'ecoCorridorTypeIds': [],
					'eventCategoryIds': [],
					'protectedAreaIds': [],
					'animalCategoryIds': [],
					'animalSpeciesIds': [],
					'animalSizeIds': [],
					'animalIds': [],
					'status': raportObj.type,
					'startDate': raportObj.startDate,
					'endDate': raportObj.endDate
				};

				angular.forEach($scope.formDropdownData.ecocorridorTypes, function(each, index){
					if (each.isChecked === true) {
					  	search.ecoCorridorTypeIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.eventCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.eventCategoryIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animalCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.animalCategoryIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.protectedArea, function(each, index){
					if (each.isChecked === true) {
					  	search.protectedAreaIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animalSize, function(each, index){
					if (each.isChecked === true) {
					  	search.animalSizeIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animal, function(each, index){
			        if (each.isChecked === true) {
			          search.animalIds.push(each.id);
			        }
		      	});
			
				var url_authTransport = url + 'reports/eco-corridor-details',
				  	options = {
				      	makeRequest: {
				          	isArray: false
				      	}
				  	};

			  	options.makeRequest.method = 'POST';

				$scope.authTransport = $resource(url_authTransport ,null,options).makeRequest(search);
				$scope.authTransport.$promise.then(function(success) {
				  	// $scope.main.alerts.push({ type: 'success', duration : 2000, msg: 'Modificarile au fost salvate.' });
				  	
				  	$scope.tabelData = success.items;

				  	console.log(success.items);
				  	// $location.url('/formulare-valide-transport-deseuri');
				},errorCallback);
			};

    		$scope.downloadRaport = function(raportObj, stringValue) {

    			var search = {
					'ecoCorridorTypeIds': [],
					'eventCategoryIds': [],
					'protectedAreaIds': [],
					'animalCategoryIds': [],
					'animalSpeciesIds': [],
					'animalSizeIds': [],
					'animalIds': [],
					'status': raportObj.type,
					'startDate': raportObj.startDate,
					'endDate': raportObj.endDate
				};

				angular.forEach($scope.formDropdownData.ecocorridorTypes, function(each, index){
					if (each.isChecked === true) {
					  	search.ecoCorridorTypeIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.eventCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.eventCategoryIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animalCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.animalCategoryIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.protectedArea, function(each, index){
					if (each.isChecked === true) {
					  	search.protectedAreaIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animalSize, function(each, index){
					if (each.isChecked === true) {
					  	search.animalSizeIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.animal, function(each, index){
			        if (each.isChecked === true) {
			          search.animalIds.push(each.id);
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
				    ServiceDetaliiCoridorEcologicPDF.downloadPdf(search).then(function (result) {
				        var pdf = new Blob([result.data], {type: 'application/pdf'});
				        var fileURL = URL.createObjectURL(pdf);
				        a.href = fileURL;
				        a.download = filePdf;
				        a.click();
				    });
    			} else if (stringValue === 'raportCsv') {
					a = document.createElement("a");
				    document.body.appendChild(a);
				    ServiceDetaliiCoridorEcologicCSV.downloadCsv(search).then(function (result) {
				        var csv = new Blob([result.data], {type: 'application/csv'});
				        var fileURL = URL.createObjectURL(csv);
				        a.href = fileURL;
				        a.download = fileCsv;
				        a.click();
				    });
    			}
			};
}])

.factory("ServiceDetaliiCoridorEcologicPDF", ["$http", "HelpersService", function(a, b) {
        var c = b.domain;
        return {
            downloadPdf: function(search) {
                return a.post(c + "reports/eco-corridor-details-pdf", search , {
                        headers: {
                            'Accept': 'application/pdf'
                        },
                        responseType: 'arraybuffer'
                    })
                    .then(function(a) {
                        return a;
                    });
            }
        };
    }

])

.factory('ServiceDetaliiCoridorEcologicCSV', function ($http,HelpersService) {
	var url = HelpersService.domain;
        return {
            downloadCsv: function (search) {
            return $http.post(url + 'reports/eco-corridor-details.csv', { 
            	responseType: 'arraybuffer',
            	params : search
            }).then(function (response) {
                return response;
            });
        }
    };
});
