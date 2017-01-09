'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AutoritateCtrl
 * @description
 * # AutoritateCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('AutoritateCtrl', ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go',
        function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go) {

            var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            $scope.session = $sessionStorage;

            $scope.session.addLink.title = 'Autoritate';

            $scope.hideStuff = function () {
                $scope.hidden = true;
            };
            $scope.showStuff = function () {
                $scope.hidden = false;
            };

            $scope.itemObj = {
                registrationDate: null,
                show: false
            };

            $scope.modify = false;

            $scope.itemObj = {};
            $scope.emailObj = {};
            $scope.formDropdownData = {};

            var options = {
                makeRequest: {
                    method: 'GET',
                    isArray: true
                }
            };

            var getContacts = function() {
                $scope.getContacts = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/contacts', {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();

                $scope.getContacts.$promise.then(function(success) {
                    $scope.notes = success.items;
                    $scope.markRow2(success.items[0]);
                    $scope.items = $sessionStorage.editableRow;
                }, errorCallback);
            };

            if (($location.search().edit === 'true') && $sessionStorage.editableRow) {

                $scope.postComisariat = $resource(url + 'organisations/authorities/' + $sessionStorage.editableRow.id, {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();
                $scope.postComisariat.$promise.then(function(success) {

                    $scope.itemObj = success;

                    $scope.itemObj.socialAddress.countryId = success.socialAddress.city.county.country;
                    $scope.itemObj.workAddress.countryId = success.workAddress.city.county.country;
                    
                    $scope.populateJudete($scope.itemObj.socialAddress.countyId = success.socialAddress.city.county, 'judetHQ');
                    $scope.populateJudete($scope.itemObj.workAddress.countyId = success.workAddress.city.county, 'judetPL');

                    $scope.populateLocalitati($scope.itemObj.socialAddress.city = success.socialAddress.city, 'localitateHQ');
                    $scope.populateLocalitati($scope.itemObj.workAddress.city = success.workAddress.city, 'localitatePL');

                    getContacts();

                    if ($scope.itemObj.caenCode.active === false) {
                        delete $scope.itemObj.caenCode.description;
                    }

                     var options = {
                        makeRequest: {
                            method: 'GET',
                            isArray: true
                        }
                    };

                    var url_Licenses = 'accounts/licenses/organisations/' + $sessionStorage.editableRow.organisation.id,
                		url_Eventcategories = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/eventcategories',
                		url_Commissariat = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/commissariats',
                		url_Email = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/email',
                		url_AutorityPermissions = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/eventcategories',
                		url_ProtectedAreas = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/protectedareas',
                		url_ecoCorridors = '/organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/ecocorridortypes';


                    $scope.getLicenses = $resource(url + url_Licenses, null, options).get();
                    $scope.getEventcategories = $resource(url + url_Eventcategories, null, options).get();
                    $scope.getCommissariat = $resource(url + url_Commissariat, null, options).get();
                    $scope.getEmail = $resource(url + url_Email, null, options).get();
                    $scope.AutorityPermissions = $resource(url + url_AutorityPermissions, null, options).get();
                    $scope.ProtectedArea = $resource(url + url_ProtectedAreas, null, options).get();
                    $scope.EcoCorridor = $resource(url + url_ecoCorridors, null, options).get();

                    $scope.getLicenses.$promise.then(function(success) {
                        $scope.formDropdownData.licenses = success.items;
                        $scope.markLicenses(success.items[0]);
                    }, errorCallback);

		            $scope.getEventcategories.$promise.then(function(success) {
		                $scope.formDropdownData.eventcategories = success.items;
		                $scope.markRowCategoriesEvent(success.items[0]);
		            }, errorCallback);

		            $scope.getCommissariat.$promise.then(function(success) {
		                $scope.formDropdownData.commissariat = success.items;
		                $scope.markRowCommissariat(success.items[0]);
		            }, errorCallback);

		            $scope.getEmail.$promise.then(function(success) {
		                $scope.emailObj.notificationEmail = success.notificationEmail;
		            }, errorCallback);

		            $scope.AutorityPermissions.$promise.then(function(success) {
		                $scope.formDropdownData.autoritypermission = success.items;
		            }, errorCallback);

		            $scope.ProtectedArea.$promise.then(function(success) {
		                $scope.formDropdownData.area = success.items;
		            }, errorCallback);

		            $scope.EcoCorridor.$promise.then(function(success) {
		                $scope.formDropdownData.ecocor = success.items;
		            }, errorCallback);

                }, errorCallback);
            }

            $scope.markRow2 = function(note) {
                $scope.editableContact = note;
                $sessionStorage.editableContact = $scope.editableContact;
                // appData.editableRow = $scope.editableRow;

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableContact;
                }
            };

            $scope.markLicenses = function(items) {
                $scope.editableLicenses = items;
                $sessionStorage.editableLicenses = $scope.editableLicenses;
                // delete $sessionStorage.editableRow;
            };

            $scope.markRowCategoriesEvent = function(items) {
                $scope.editableCategoriesEvent = items;
                $sessionStorage.editableCategoriesEvent = $scope.editableCategoriesEvent;
                // delete $sessionStorage.editableRow;
            };

            $scope.markRowCommissariat = function(items) {
                $scope.editableCommissariat = items;
                $sessionStorage.editableCommissariat = $scope.editableCommissariat;
                // delete $sessionStorage.editableRow;
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
              if (tipAdresa === 'judetHQ') {
                $scope.itemObj.socialAddress.countyId = null;
                $scope.itemObj.socialAddress.city = null;
                delete $scope.formDropdownData.judetHQ;
                delete $scope.formDropdownData.localitateHQ;
              } else if (tipAdresa === 'judetPL') {
                $scope.itemObj.workAddress.countyId = null;
                $scope.itemObj.workAddress.city = null;
                delete $scope.formDropdownData.judetPL;
                delete $scope.formDropdownData.localitatePL;
              } else if (tipAdresa === 'localitateHQ') {
                $scope.itemObj.socialAddress.city = null;
                delete $scope.formDropdownData.localitateHQ;
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

                if (tipAdresa === 'judetHQ') {

                    contiue = checkIfIsMainOffice(countryId, tipAdresa);
                  
                    if (contiue && $scope.itemObj.socialAddress.countryId !== undefined) {
                        newObj = {
                          'countryId': $scope.itemObj.socialAddress.countryId.id
                        };
                        getFormData(url_element, options, newObj, tipAdresa);
                    }

                } else if (tipAdresa === 'judetPL'){

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
                             
                if (tipAdresaLocalitate === 'localitateHQ') {

                    contiue = checkIfIsMainOffice(countyId, tipAdresaLocalitate);

                    if (contiue && $scope.itemObj.socialAddress.countyId !== undefined) {

                        newCounty = {
                          'countyId': $scope.itemObj.socialAddress.countyId.id
                        };
                        getFormData(url_element, options, newCounty, tipAdresaLocalitate);
                    }

                } else if (tipAdresaLocalitate === 'localitatePL') {

                    contiue = checkIfIsMainOffice(countyId, tipAdresaLocalitate);

                    if (contiue && $scope.itemObj.socialAddress.countyId !== undefined) {

                        newCounty = {
                          'countyId': $scope.itemObj.workAddress.countyId.id
                        };
                        getFormData(url_element, options, newCounty, tipAdresaLocalitate);
                    }
                }
            };

            var newItemObj;

            $scope.sendPost = function() {

                newItemObj = {
                    'name': $scope.itemObj.name,
                    'legalEntityType': {
                        'id': $scope.itemObj.legalEntityType.id
                    },
                    'fiscalAttribute': "RO",
                    'uniqueIdentificationCode': $scope.itemObj.uniqueIdentificationCode,
                    'registrationDate': $scope.itemObj.registrationDate,
                    'jointStock': $scope.itemObj.jointStock,
                    'jointStockCurrency': "RON",
                    'caenCode': {
                        'id': $scope.itemObj.caenCode.id
                    },
                    'issuingUnit': {
                        'id': $scope.itemObj.issuingUnit.id
                    },
                    'registrationCode': $scope.itemObj.registrationCode,
                    'socialAddress': {
                        'street': $scope.itemObj.socialAddress.street,
                        'number': $scope.itemObj.socialAddress.number,
                        'block': $scope.itemObj.socialAddress.block,
                        'entrance': $scope.itemObj.socialAddress.entrance,
                        'floor': $scope.itemObj.socialAddress.floor,
                        'apartament': $scope.itemObj.socialAddress.apartament,
                        'city': {
                            'id': $scope.itemObj.socialAddress.city.id
                        },
                        'postalCode': $scope.itemObj.socialAddress.postalCode
                    },
                    'workAddress': {
                        'street': $scope.itemObj.workAddress.street,
                        'number': $scope.itemObj.workAddress.number,
                        'block': $scope.itemObj.workAddress.block,
                        'entrance': $scope.itemObj.workAddress.entrance,
                        'floor': $scope.itemObj.workAddress.floor,
                        'apartament': $scope.itemObj.workAddress.apartament,
                        'city': {
                            'id': $scope.itemObj.workAddress.city.id
                        },
                        'postalCode': $scope.itemObj.workAddress.postalCode
                    }
                };

                var url_comisariat = url + 'organisations/authorities',
                    options = {
                        makeRequest: {
                            method: 'POST',
                            isArray: false
                        }
                    };

                if ($routeParams.edit === 'true') {
                    url_comisariat = url + 'organisations/' + $scope.itemObj.organisation.id + '/authorities/' +  $scope.itemObj.id;

                    newItemObj = {
                        'name': $scope.itemObj.name,
                        'legalEntityType': {
                            'id': $scope.itemObj.legalEntityType.id
                        },
                        'fiscalAttribute': "RO",
                        'uniqueIdentificationCode': $scope.itemObj.uniqueIdentificationCode,
                        'registrationDate': $scope.itemObj.registrationDate,
                        'jointStock': $scope.itemObj.jointStock,
                        'jointStockCurrency': "RON",
                        'caenCode': {
                            'id': $scope.itemObj.caenCode.id
                        },
                        'issuingUnit': {
                            'id': $scope.itemObj.issuingUnit.id
                        },
                        'registrationCode': $scope.itemObj.registrationCode,
                        'socialAddress': {
                            'street': $scope.itemObj.socialAddress.street,
                            'number': $scope.itemObj.socialAddress.number,
                            'block': $scope.itemObj.socialAddress.block,
                            'entrance': $scope.itemObj.socialAddress.entrance,
                            'floor': $scope.itemObj.socialAddress.floor,
                            'apartament': $scope.itemObj.socialAddress.apartament,
                            'city': {
                                'id': $scope.itemObj.socialAddress.city.id
                            },
                            'postalCode': $scope.itemObj.socialAddress.postalCode
                        },
                        'workAddress': {
                            'street': $scope.itemObj.workAddress.street,
                            'number': $scope.itemObj.workAddress.number,
                            'block': $scope.itemObj.workAddress.block,
                            'entrance': $scope.itemObj.workAddress.entrance,
                            'floor': $scope.itemObj.workAddress.floor,
                            'apartament': $scope.itemObj.workAddress.apartament,
                            'city': {
                                'id': $scope.itemObj.workAddress.city.id
                            },
                            'postalCode': $scope.itemObj.workAddress.postalCode
                        }
                    };

                    options.makeRequest.method = 'PUT';

                } else {
                    options.makeRequest.method = 'POST';
                    //   $scope.tabs = [{ 
                    //  disabled: false 
                    // }];
                }

                $scope.postComisariat = $resource(url_comisariat, null, options).makeRequest(newItemObj);
                $scope.postComisariat.$promise.then(function(response) {
                    $sessionStorage.editableRow = response;

                    if (response.status === 500) {
                        errorCallback(response);
                        return;
                    }
                    $scope.main.alerts.push({
                        type: 'success',
                        duration: 2000,
                        msg: 'Modificarile au fost salvate.'
                    });
                    $location.url('/autoritate?edit=true');

                }, errorCallback);
            };

            $scope.note = {
                'active': true,
                'firstName': '',
                'lastName': '',
                'phone1': '',
                'phone2': '',
                'fax': '',
                'email': '',
                'function': ''
            };

            $scope.state = 'adauga';

            $scope.addComment = function(addNoteForm, note) {

                note.active = true;

                if (addNoteForm.$valid) {

                    var url_contact = url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/contacts' ,
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                    if ($scope.state === 'modifica') {
                        url_contact = url + 'organisations/' + $sessionStorage.editableRow.organisation.id +'/contacts/' + $sessionStorage.editableContact.id;
                        options.makeRequest.method = 'PUT';
                        // $sessionStorage.editableRow.id = $scope.postComisariat.item.id;
                    } else if ($scope.state === 'adauga') {
                        options.makeRequest.method = 'POST';
                    }

                    $scope.postComment = $resource(url_contact, null, options).makeRequest(note);
                    $scope.postComment.$promise.then(function(success) {
                        $('#addCommentModal').modal('hide');
                        getContacts();
                    }, errorCallback);
                }
            };

            $scope.changeState = function(state) {

                $scope.state = state;

                if (state === 'adauga') {
                    $scope.note = {};
                }
            };

            $scope.activeRow = function(items) {

                $scope.editableContact.active = true;
                $scope.items = $resource(url + 'organisations/contacts/' + $sessionStorage.editableContact.id + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableContact);
                $scope.items.$promise.then(function(success) {
                }, errorCallback);
            };

            $scope.deactiveRow = function(items) {

                $scope.editableContact.active = false;
                $scope.items = $resource(url + 'organisations/contacts/' + $sessionStorage.editableContact.id + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableContact);
                $scope.items.$promise.then(function(success) {
                }, errorCallback);
            };

            $scope.activeLicenses = function(items) {
                $scope.editableLicenses.active = true;

                $scope.items = $resource(url + 'accounts/' + $sessionStorage.editableLicenses.accountId + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableLicenses);
                $scope.items.$promise.then(function(success) {
                }, errorCallback);
            };

            $scope.deactiveLicenses = function(items) {

                $scope.editableLicenses.active = false;

                $scope.items = $resource(url + 'accounts/' + $sessionStorage.editableLicenses.accountId + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableLicenses);
                $scope.items.$promise.then(function(success) {
                }, errorCallback);
            };

            $scope.activeEventCategories = function(items) {
                $scope.editableCategoriesEvent.active = true;

                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/eventcategories/' + $sessionStorage.editableCategoriesEvent.id, {}, {
                    makeRequest: {
                        method: 'POST',
                        isArray: false
                    }
                }).makeRequest($scope.editableCategoriesEvent);
                $scope.items.$promise.then(function(success) {
                	$scope.main.successHandlerCollback(success);
                }, errorCallback);
            };

            $scope.deactiveEventCategories = function(items) {

                $scope.editableCategoriesEvent.active = false;

                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/eventcategories/' + $sessionStorage.editableCategoriesEvent.id, {}, {
                    makeRequest: {
                        method: 'DELETE',
                        isArray: false
                    }
                }).makeRequest();
                $scope.items.$promise.then(function(success) {
                	$scope.main.successHandlerCollback(success);
                }, errorCallback);
            };

            $scope.activeCommissariat = function(items) {
                $scope.editableCommissariat.active = true;

                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/commissariats/' + $sessionStorage.editableCommissariat.id, {}, {
                    makeRequest: {
                        method: 'POST',
                        isArray: false
                    }
                }).makeRequest($scope.editableCommissariat);
                $scope.items.$promise.then(function(success) {
                	$scope.main.successHandlerCollback(success);
                }, errorCallback);
            };

            $scope.deactiveCommissariat = function(items) {

                $scope.editableCommissariat.active = false;

                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/commissariats/' + $sessionStorage.editableCommissariat.id, {}, {
                    makeRequest: {
                        method: 'DELETE',
                        isArray: false
                    }
                }).makeRequest();
                $scope.items.$promise.then(function(success) {
                	$scope.main.successHandlerCollback(success);
                }, errorCallback);
            };

            $scope.emailEventsNotification = function(emailObj) {

            	// if (emailForm.$valid) {

	            	var emailObj2 = {
	            		'notificationEmail' : emailObj.notificationEmail
	            	};

	                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/email', {}, {
	                    makeRequest: {
	                        method: 'POST',
	                        isArray: false
	                    }
	                }).makeRequest(emailObj2);

	                $scope.items.$promise.then(function(success) {
	                    $scope.main.successHandlerCollback(success);
	                }, errorCallback);

            	// }
            };
	                
            $scope.stateEventCategories = function(items) {

            	if (items.checkbox === true) {
                	items.active = true;
	                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/eventcategories/' + $sessionStorage.editableCategoriesEvent.id, {}, {
	                    makeRequest: {
	                        method: 'POST',
	                        isArray: false
	                    }
	                }).makeRequest();


            	} else if (items.checkbox !== true){
                	items.active = null;
	                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/notifications/eventcategories/' + $sessionStorage.editableCategoriesEvent.id, {}, {
	                    makeRequest: {
	                        method: 'DELETE',
	                        isArray: false
	                    }
	                }).makeRequest();
            		
            	}

                $scope.items.$promise.then(function(success) {
                }, errorCallback);
            };

            $scope.licensesGenerator = function() {
                var options = {
                        makeRequest: {
                            method: 'GET',
                            isArray: true
                        }
                    };

                var url_Generator = 'accounts/organisation/' + $sessionStorage.editableRow.organisation.id + '/generate';
              
                $scope.getGenerator = $resource(url + url_Generator, null, options).get();

                $scope.getGenerator.$promise.then(function(success) {
                    $scope.formDropdownData.licenses.push(success);
                }, errorCallback);
            };

            $scope.eventVisualization = function(items) {
				angular.forEach($scope.formDropdownData.autoritypermission, function(each, index) {
		            if (each.id === items.id) {
						if (items.active === true) {

							items.active = true;
			                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/eventcategories/' + items.id, {}, {
			                    makeRequest: {
			                        method: 'POST',
			                        isArray: false
			                    }
			                }).makeRequest();
			                $scope.items.$promise.then(function(success) {
			                	$scope.main.successHandlerCollback(success);
			                }, errorCallback);

						} else {
							items.active = false;
			                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/eventcategories/' + items.id, {}, {
			                    makeRequest: {
			                        method: 'DELETE',
			                        isArray: false
			                    }
			                }).makeRequest();
			                $scope.items.$promise.then(function(success) {
			                	$scope.main.successHandlerCollback(success);
			                }, errorCallback);

						}
		            }
		        });
			};

            $scope.eventAreas = function(items) {
				angular.forEach($scope.formDropdownData.area, function(each, index) {
		            if (each.id === items.id) {
						if (items.active === true) {

							items.active = true;
			                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/protectedareas/' + items.id, {}, {
			                    makeRequest: {
			                        method: 'POST',
			                        isArray: false
			                    }
			                }).makeRequest();
			                $scope.items.$promise.then(function(success) {
			                	$scope.main.successHandlerCollback(success);
			                }, errorCallback);

						} else {
							items.active = false;
			                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/protectedareas/' + items.id, {}, {
			                    makeRequest: {
			                        method: 'DELETE',
			                        isArray: false
			                    }
			                }).makeRequest();
			                $scope.items.$promise.then(function(success) {
			                	$scope.main.successHandlerCollback(success);
			                }, errorCallback);

						}
		            }
		        });
			};

            $scope.ecoCorridors = function(items) {
				angular.forEach($scope.formDropdownData.ecocor, function(each, index) {
		            if (each.id === items.id) {
						if (items.active === true) {

							items.active = true;
			                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/ecocorridortypes/' + items.id, {}, {
			                    makeRequest: {
			                        method: 'POST',
			                        isArray: false
			                    }
			                }).makeRequest();
			                $scope.items.$promise.then(function(success) {
			                	$scope.main.successHandlerCollback(success);
			                }, errorCallback);

						} else {
							items.active = false;
			                $scope.items = $resource(url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/permissions/ecocorridortypes/' + items.id, {}, {
			                    makeRequest: {
			                        method: 'DELETE',
			                        isArray: false
			                    }
			                }).makeRequest();
			                $scope.items.$promise.then(function(success) {
			                	$scope.main.successHandlerCollback(success);
			                }, errorCallback);

						}
		            }
		        });
			};

			$scope.downloadCsv = function() {
                $scope.fileUrl = url + 'accounts/licenses/organisations/' + $sessionStorage.editableRow.organisation.id + '/download';
            };

        }

  ]);
