'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:IndicatoriPerformantaCategoriiEvenimenteCtrl
 * @description
 * # IndicatoriPerformantaCategoriiEvenimenteCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('IndicatoriPerformantaCategoriiEvenimenteCtrl',['$scope','$http','$resource','HelpersService','$sessionStorage','$location','Go', '$log','$timeout','$window','$sce','ServiceIndicatoriEvenimentePDF',
  		function ($scope,$http,$resource,HelpersService,$sessionStorage,$location,Go,$log,$timeout,$window,$sce,ServiceIndicatoriEvenimentePDF) {

  			var url = HelpersService.domain,
        		errorCallback = $scope.main.errorHandlerCollback;

    		$scope.raportObj = {};
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

    		var protectedArea = function () {
    			var protectedArea = url + '/protectedareas/custody';
    			$http.get(protectedArea).then(function(success){
    				$scope.formDropdownData.protectedArea = success.data.items;
    				console.log($scope.formDropdownData.protectedArea);
    			});
    		};
    		protectedArea();

    		$scope.runRaport = function(raportObj) {
	    		
	    		var search = {
					'eventCategoryIds': [],
					'protectedAreaIds': [],
					'startDate': raportObj.startDate,
					'endDate': raportObj.endDate
				};

				angular.forEach($scope.formDropdownData.eventCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.eventCategoryIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.protectedArea, function(each, index){
					if (each.isChecked === true) {
					  	search.protectedAreaIds.push(each.id);
					}
				});
			
				var url_authTransport = url + 'reports/event-by-category',
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
					'eventCategoryIds': [],
					'protectedAreaIds': [],
					'startDate': raportObj.startDate,
					'endDate': raportObj.endDate
				};

				angular.forEach($scope.formDropdownData.eventCategory, function(each, index){
					if (each.isChecked === true) {
					  	search.eventCategoryIds.push(each.id);
					}
				});

				angular.forEach($scope.formDropdownData.protectedArea, function(each, index){
					if (each.isChecked === true) {
					  	search.protectedAreaIds.push(each.id);
					}
				});

				var filePdf = 'event-by-category-pdf',
					fileCsv = 'event-by-category.csv',
					a;

    			if (stringValue === 'raportPdf') {
				    a = document.createElement("a");
				    document.body.appendChild(a);
				    a.style = "display: none";
				    ServiceIndicatoriEvenimentePDF.downloadPdf(search).then(function (result) {
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

.factory("ServiceIndicatoriEvenimentePDF", ["$http", "HelpersService", function(a, b) {
        var c = b.domain;
        return {
            downloadPdf: function(search) {
                return a.post(c + "reports/event-by-category-pdf", search, {
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
//             return $http.post(url + 'reports/event-by-category.csv', { 
//             	responseType: 'arraybuffer',
//             	params : search
//             }).then(function (response) {
//                 return response;
//             });
//         }
//     };
// });