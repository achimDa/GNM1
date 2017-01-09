'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:SediuArieProtejataCtrl
 * @description
 * # SediuArieProtejataCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('SediuArieProtejataCtrl', ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go',
    function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go) {

        var url = HelpersService.domain,
            errorCallback = $scope.main.errorHandlerCollback;

        $scope.session = $sessionStorage;

        $scope.itemObj = {};
        $scope.formDropdownData = {};
        var newObj = {};

	    var options = {
	        makeRequest: {
	            method: 'GET',
	            isArray: true
	        }
	    };

	    var url_Juridic = 'legalentitytypes',
	        url_Certificat = 'issuingunits',
	        url_Caen = 'caencodes',
	        url_Tara = 'countries';

	    $scope.postTara = $resource(url + url_Tara, null, options).get();
	    $scope.postJuridic = $resource(url + url_Juridic, null, options).get();
	    $scope.postCertificat = $resource(url + url_Certificat, null, options).get();
	    $scope.postCaen = $resource(url + url_Caen, null, options).get();


	    $scope.postTara.$promise.then(function(success) {
	        $scope.formDropdownData.tara = success.items;
	    }, errorCallback);
	    $scope.postJuridic.$promise.then(function(success) {
	        $scope.formDropdownData.juridic = success.items;
	    }, errorCallback);
	    $scope.postCertificat.$promise.then(function(success) {
	        $scope.formDropdownData.certificat = success.items;
	    }, errorCallback);
	    $scope.postCaen.$promise.then(function(success) {
	        $scope.formDropdownData.caen = success.items;
	    }, errorCallback);


		function checkIfIsMainOffice (countryId, tipAdresa) {

          if (countryId) {
            return true;
          }
          if (tipAdresa === 'judetPL') {
            $scope.itemObj.workAddress.countyId = null;
            $scope.itemObj.address.city = null;
            delete $scope.formDropdownData.judetPL;
            delete $scope.formDropdownData.localitatePL;
          } else if (tipAdresa === 'localitatePL') {
            $scope.itemObj.workAddress.city = null;
            delete $scope.formDropdownData.localitatePL;
          }
          
          return false;

        }


        function getFormData(url_element, options, newObj, tipAdresa){
          var getFormDataElm;
          getFormDataElm = $resource(url_element, null, options).makeRequest(newObj);
          getFormDataElm.$promise.then( function(success) {
              $scope.formDropdownData[tipAdresa] = success.items;
          }, errorCallback);
        }
        
        $scope.populateJudete = function(countryId, tipAdresa) {

            var newObj,
                url_element = url + 'counties/search',
                contiue,
                options = {
                    makeRequest: {
                      method:'POST',
                      isArray: false
                    }
                };  

            if (tipAdresa === 'judetPL'){

                contiue = checkIfIsMainOffice(countryId, tipAdresa);
              
                if ( contiue && $scope.itemObj.workAddress.countryId !== undefined) {
                    newObj = {
                      'countryId': $scope.itemObj.workAddress.countryId.id
                    };
                    getFormData(url_element, options, newObj, tipAdresa);
                }
            }

        };

        $scope.populateLocalitati = function(countyId, tipAdresaLocalitate) {
            
            var newCounty,
                url_element = url + 'cities/search',
                contiue,
                options = {
                    makeRequest: {
                      method:'POST',
                      isArray: false
                    }
                };  
                         
            if (tipAdresaLocalitate === 'localitatePL') {

                contiue = checkIfIsMainOffice(countyId, tipAdresaLocalitate);

                if (contiue && $scope.itemObj.workAddress.countyId !== undefined) {

                    newCounty = {
                      'countyId': $scope.itemObj.workAddress.countyId.id
                    };
                    getFormData(url_element, options, newCounty, tipAdresaLocalitate);
                }
            }

        };

	    if (($location.search().edit === 'true') && $sessionStorage.editableResidence) {

                $scope.getResidences = $resource(url + 'protectedareas/residences/' + $sessionStorage.editableResidence.id, {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();
                $scope.getResidences.$promise.then(function(success) {

                	$scope.itemObj = success;

                    $scope.itemObj.workAddress = {};

                    $scope.itemObj.workAddress.countryId = success.address.city.county.country;
                    
                    $scope.populateJudete($scope.itemObj.workAddress.countyId = success.address.city.county, 'judetPL');
                    $scope.populateLocalitati($scope.itemObj.address.city = success.address.city, 'localitatePL');

                    // $scope.populateLocalitati($scope.itemObj);

                }, errorCallback);
        }

	    // $scope.populateJudete = function(itemObj) {

	    // 	var newObj = {
     //              'countryId': itemObj.address.country.id
     //            };

	    //     var url_locationCounty = url + 'counties/search',
	    //         options = {
	    //             makeRequest: {
	    //                 isArray: false
	    //             }
	    //         };
	    //     options.makeRequest.method = 'POST';

	    //     $scope.postLocation = $resource(url_locationCounty, null, options).makeRequest(newObj);
	    //     $scope.postLocation.$promise.then(
	    //         function(success) {
	    //             $scope.formDropdownData.judet = success.items;
	    //         }, errorCallback);
	    // };

	    // $scope.populateLocalitati = function(itemObj) {

	    // 	var newObj2 = {
     //              'countyId': itemObj.address.city.county.id
     //            };

	    //     var url_location = url + 'cities/search',
	    //         options = {
	    //             makeRequest: {
	    //                 isArray: false
	    //             }
	    //         };
	    //     options.makeRequest.method = 'POST';

	    //     $scope.postLocation = $resource(url_location, null, options).makeRequest(newObj2);
	    //     $scope.postLocation.$promise.then(
	    //         function(success) {
	    //             $scope.formDropdownData.localitate = success.items;
	    //         }, errorCallback);
	    // };

        $scope.sendPost = function(itemObj) {
 
        	newObj = {
	            'residenceType' : itemObj.residenceType,
	            'address' : {
                    'street': itemObj.address.street,
                    'number': itemObj.address.number,
                    'block': itemObj.address.block,
                    'entrance' : itemObj.address.entrance,
                    'floor' : itemObj.address.floor,
                    'apartament' : itemObj.address.apartament,
                    'sector' : itemObj.address.sector,
                    'postalCode' : itemObj.address.postalCode,
                    'city' : {
                      'id' : itemObj.address.city.id
                    }
                }
          	};
	        
	        var url_location = url + 'protectedareas/' + $sessionStorage.editableRow.id + '/residences',
	              options = {
	                  makeRequest: {
	                      isArray: false
	                  }
	              };

	          if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
              	
              	url_location = url + 'protectedareas/' + $sessionStorage.editableRow.id + '/residences/' + $sessionStorage.editableResidence.id;
				
				newObj = {
				    'residenceType' : itemObj.residenceType,
				    'address' : {
				        'id' : $sessionStorage.editableResidence.address.id,
				        'street': $scope.itemObj.address.street,
				        'number': $scope.itemObj.address.number,
				        'block': $scope.itemObj.address.block,
				        'entrance' : $scope.itemObj.address.entrance,
				        'floor' : $scope.itemObj.address.floor,
				        'apartament' : $scope.itemObj.address.apartament,
				        'sector' : $scope.itemObj.address.sector,
				        'postalCode' : $scope.itemObj.address.postalCode,
				        'city' : {
				          'id' : $scope.itemObj.address.city.id
				        }
				    }
				};
			        
	              options.makeRequest.method = 'PUT';
	          } else {
	              options.makeRequest.method = 'POST';
	          }

	          $scope.postLocation = $resource(url_location ,null,options).makeRequest(newObj);
	          $scope.postLocation.$promise.then(
	              function(success) {
	                  $scope.main.successHandlerCollback(success);
	                  Go.to({url:'arie-protejata?edit=true'});

	              },errorCallback); 
	    };

  }]);
