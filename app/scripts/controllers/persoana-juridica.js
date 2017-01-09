'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:PersoanaJuridicaCtrl
 * @description
 * # PersoanaJuridicaCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('PersoanaJuridicaCtrl', ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go',
        function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go) {

            var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            $scope.session = $sessionStorage;

            $scope.session.addLink.title = 'Editare Administratori arii protejate';

            $scope.hideStuff = function () {
                $scope.hidden = true;
            };
            $scope.showStuff = function () {
                $scope.hidden = false;
            };

            $scope.modify = false;

            $scope.itemObj = {};
            $scope.formDropdownData = {};

            $scope.itemObj = {
                registrationDate: null,
                show: false
            };

            var system = {};

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

                $scope.postComisariat = $resource(url + 'organisations/administrators/' + $sessionStorage.editableRow.id, {}, {
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
                        url_Systems = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/system',
                        url_LocalAdmin =  'accounts/organisations/' + $sessionStorage.editableRow.organisation.id +'/localadmin',
                        url_AreaAdmin =  'accounts/organisations/' + $sessionStorage.editableRow.organisation.id +'/protectedareaadmins';
                    // var url_Licenses = 'accounts/licenses/organisations/' + 1;

                    $scope.getLicenses = $resource(url + url_Licenses, null, options).get();
                    $scope.getSystems = $resource(url + url_Systems, null, options).get();
                    $scope.getLocalAdmin = $resource(url + url_LocalAdmin, null, options).get();
                    $scope.getAreaAdmin = $resource(url + url_AreaAdmin, null, options).get();

                    $scope.getLicenses.$promise.then(function(success) {
                        $scope.formDropdownData.licenses = success.items;
                        $scope.markLicenses(success.items[0]);
                    }, errorCallback);

                    $scope.getSystems.$promise.then(function(success) {
                        $scope.formDropdownData.systems = success;
                        $scope.systemObj = success;
                    }, errorCallback);

                    $scope.getLocalAdmin.$promise.then(function(success) {
                        $scope.formDropdownData.localadmin = success.items;
                    }, errorCallback);

                    $scope.getAreaAdmin.$promise.then(function(success) {
                        $scope.formDropdownData.areaadmin = success.items;
                    }, errorCallback);

                }, errorCallback);
            }

            //SYSTEMS
            
            $scope.localAdminState = function(systemObj) {
                system = {
                  'localAdminAccount': (typeof systemObj.localAdminAccount !== 'undefined') && (systemObj.localAdminAccount !== null)  ? {
                      'id': systemObj.localAdminAccount.id 
                  } : null,
                  'resolutionAccount': (typeof systemObj.resolutionAccount !== 'undefined') && (systemObj.resolutionAccount !== null)  ? {
                      'id': systemObj.resolutionAccount.id 
                  } : null
                };

                var url_localAdmin = url + 'organisations/' + $sessionStorage.editableRow.organisation.id + '/system',
                      options = {
                          makeRequest: {
                              isArray: false
                          }
                      };
                options.makeRequest.method = 'PUT';

                $scope.postElement = $resource(url_localAdmin ,null,options).makeRequest(system);
                $scope.postElement.$promise.then(
                  function(success) {
                      $scope.main.successHandlerCollback(success);
                      // Go.to({url:'utilizatori'});

                  },errorCallback); 
            };

            $scope.markRow2 = function(note) {
                $scope.editableContact = note;
                $sessionStorage.editableContact = $scope.editableContact;
                // appData.editableRow = $scope.editableRow;

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableContact;
                    console.log("note", note);
                }

            };

            $scope.markLicenses = function(items) {
                $scope.editableLicenses = items;
                $sessionStorage.editableLicenses = $scope.editableLicenses;
                // delete $sessionStorage.editableRow;
            };

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

                // if ($scope.itemObj.administrator === null) {
                //   delete newItemObj.administrator;
                // }

                var url_comisariat = url + 'organisations/administrators',
                    options = {
                        makeRequest: {
                            method: 'POST',
                            isArray: false
                        }
                    };

                if ($routeParams.edit === 'true') {
                    url_comisariat = url + 'organisations/' + $scope.itemObj.organisation.id + '/administrators/' +  $scope.itemObj.id;
                    // $scope.itemObj.socialAddress.id = $sessionStorage.editableRow.socialAddress.id;
                    // $scope.itemObj.workAddress.id = $sessionStorage.editableRow.workAddress.id;

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
                    console.log(response);
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
                    $location.url('/persoana-juridica?edit=true');

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
                    console.log(success);
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
                    console.log(success);
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
                    console.log(success);
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
                    console.log(success);
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

            $scope.downloadCsv = function() {
                $scope.fileUrl = url + 'accounts/licenses/organisations/' + $sessionStorage.editableRow.organisation.id + '/download';
            };

        }
  ]);
