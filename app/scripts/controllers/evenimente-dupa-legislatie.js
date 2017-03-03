'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:EvenimenteDupaLegislatieCtrl
 * @description
 * # EvenimenteDupaLegislatieCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('EvenimenteDupaLegislatieCtrl',['$scope','$http','$resource','HelpersService','$sessionStorage','$location','Go', '$log','$timeout','$window','$sce','ServiceEvenimenteLegislatiePDF',
  		function ($scope,$http,$resource,HelpersService,$sessionStorage,$location,Go,$log,$timeout,$window,$sce,ServiceEvenimenteLegislatiePDF) {

  			var url = HelpersService.domain,
        		errorCallback = $scope.main.errorHandlerCollback;

    		$scope.evenimenteObj = {};
    		$scope.formDropdownData = {};

    		var gestiuneObj;

    		// $scope.session.title = 'Fisa de gestiune';

    		var eventCategory = function () {
    			var eventCategory = url + '/eventcategories/view';
    			$http.get(eventCategory).then(function(success){
    				$scope.formDropdownData.eventCategory = success.data.items;
    			});
    		};
    		eventCategory();    		

    		$scope.legislatie = function (viewValue) {
    			var urlLeg = url + 'legislations',
    				params = {number: viewValue};
    				return $http.get(urlLeg, {params: params}).then(function(success){
    				// $scope.formDropdownData.legislatii = success.data.items;
    				return success.data.items;
    			});
    		};

    		$scope.reglementare = function (viewValue) {
    			var urlReg = url + 'regulations',
    				params = {number: viewValue};
    				return $http.get(urlReg, {params: params}).then(function(success){
    				// $scope.formDropdownData.legislatii = success.data.items;
    				return success.data.items;
    			});
    		};


    		$scope.runRaport = function(evenimenteObj) {
	    		
	    		var search = {
	    			'legislationIds': [],
	    			'regulationIds' : [],
					'eventCategoryIds': [],
					'startDate': evenimenteObj.startDate,
					'endDate': evenimenteObj.endDate
					// 'startDate': Date.parse(evenimenteObj.startDate),
					// 'endDate': Date.parse(evenimenteObj.endDate)
				};

				angular.forEach($scope.formDropdownData.eventCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.eventCategoryIds.push(each.id);
					}
				});

				if ($scope.evenimenteObj.legislatie !== undefined) {
					if ($scope.evenimenteObj.legislatie !== "") {
						search.legislationIds.push($scope.evenimenteObj.legislatie);
					} 
				} 

				if ($scope.evenimenteObj.reglementare !== undefined) {
					if ($scope.evenimenteObj.reglementare !== "") {
						search.regulationIds.push($scope.evenimenteObj.reglementare);
					} 
				} 
			
				var url_authTransport = url + 'reports/event-by-legislation',
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

    		$scope.downloadRaport = function(evenimenteObj, stringValue) {

    			var search = {
	    			'legislationIds': [],
	    			'regulationIds' : [],
					'eventCategoryIds': [],
					'startDate': evenimenteObj.startDate,
					'endDate': evenimenteObj.endDate
				};

				angular.forEach($scope.formDropdownData.eventCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.eventCategoryIds.push(each.id);
					}
				});

				if ($scope.evenimenteObj.legislatie !== undefined) {
					if ($scope.evenimenteObj.legislatie !== "") {
						search.legislationIds.push($scope.evenimenteObj.legislatie);
					} 
				} 

				if ($scope.evenimenteObj.reglementare !== undefined) {
					if ($scope.evenimenteObj.reglementare !== "") {
						search.regulationIds.push($scope.evenimenteObj.reglementare);
					} 
				} 

				var filePdf = 'event-by-legislation-pdf',
					fileCsv = 'event-by-legislation.csv',
					a;

    			if (stringValue === 'raportPdf') {
				    a = document.createElement("a");
				    document.body.appendChild(a);
				    a.style = "display: none";
				    ServiceEvenimenteLegislatiePDF.downloadPdf(search).then(function (result) {
				        var pdf = new Blob([result.data], {type: 'application/pdf'});
				        var fileURL = URL.createObjectURL(pdf);
				        a.href = fileURL;
				        a.download = filePdf;
				        a.click();
				    });
    			} 

    	// 		else if (stringValue === 'raportCsv') {
					// a = document.createElement("a");
				 //    document.body.appendChild(a);
				 //    ServiceIndicatoriEvenimenteCSV.downloadCsv(search).then(function (result) {
				 //        var csv = new Blob([result.data], {type: 'application/csv'});
				 //        var fileURL = URL.createObjectURL(csv);
				 //        a.href = fileURL;
				 //        a.download = fileCsv;
				 //        a.click();
				 //    });
    	// 		}
			};
}])

.factory("ServiceEvenimenteLegislatiePDF", ["$http", "HelpersService", function(a, b) {
        var c = b.domain;
        return {
            downloadPdf: function(search) {
                return a.post(c + "reports/event-by-legislation-pdf", search, {
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

]);

// .factory('ServiceIndicatoriEvenimenteCSV', function ($http,HelpersService) {
// 	var url = HelpersService.domain;
//         return {
//             downloadCsv: function (search) {
//             return $http.post(url + 'reports/event-by-legislation.csv', { 
//             	responseType: 'arraybuffer',
//             	params : search
//             }).then(function (response) {
//                 return response;
//             });
//         }
//     };
// });