'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:UtilizatorNouCtrl
 * @description
 * # UtilizatorNouCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
    .controller('UtilizatorNouCtrl', ['$scope', '$resource', 'HelpersService', '$location', '$sessionStorage', 'Go', '$routeParams', '$window', '$timeout' ,
        function($scope, $resource, HelpersService, $location, $sessionStorage, Go, $routeParams, $window,$timeout) {

            var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            $scope.session = $sessionStorage;

            $scope.hideStuff = function () {
                $scope.hidden = true;
            };
            $scope.showStuff = function () {
                $scope.hidden = false;
            };
            $scope.itemObj = {
                dateOfBirth: null,
                show: false
            };

            // if ($sessionStorage.editableRow.isUsed === true) {
            //     $scope.session.addLink.title = 'Modifica contul utilizatorului';
            // } else {
            //     $scope.session.addLink.title = 'Aloca utilizator nou';
            // } 

            $scope.formDropdownData = {};
            $scope.itemObj = {};

            // $scope.$watch('itemObj.username', function() {
            //     $scope.itemObj.username = $scope.itemObj.username.replace(/\s+/g,'');
            // });

            var editedValue = "testValue";

            function doIt(e) {
                var e = e || event;
                if (!(e.keyCode >= 65 && e.keyCode <= 90 || 
                      e.keyCode >= 97 && e.keyCode <= 122 || 
                      e.keyCode == 8)) return false;
            }

            window.onload = function(){
                var inp = document.getElementById("inputText");
                inp.onkeydown = doIt;
            };

            var url_element = {},
                url_accounts,
                url_areaPermissions,
                options,
                url_web;

            if (($location.search().create === 'true') && $sessionStorage.editableRow.active === null) {
                delete $sessionStorage.editableLicenses;
                $scope.itemObj = $sessionStorage.editableRow;
                delete $sessionStorage.editableRow.dateOfBirth;
                $scope.passInput = true;
                $scope.isvisible = true;

            } else if (($location.search().create === 'true') && $sessionStorage.editableLicenses.active === null) {
                $scope.itemObj = $sessionStorage.editableLicenses;
                $scope.passInput = true;
                $scope.isvisible = true;
            }

            if ($location.search().edit === 'true') {
                options = {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                };

                if ($sessionStorage.editableRow.isUsed === true) {
                    url_accounts = 'accounts/' + $sessionStorage.editableRow.accountId;
                } else {
                    url_accounts = 'accounts/' + $sessionStorage.accountId;
                }

                $scope.getAccounts = $resource(url + url_accounts, null, options).makeRequest();

                $scope.getAccounts.$promise.then(function(success) {
                    $scope.itemObj = success;
                    $scope.itemObj.license = success.accountLicense.license;
                    $scope.itemObj.organisationName = success.organisation.name;
                    $scope.itemObj.roleId = success.accountRole;
                    $scope.itemObj.licenseId = success.accountLicense.id;

                }, errorCallback);

                $scope.passInput = false;
                $scope.isvisible = true;

                if ($sessionStorage.editableRow.isUsed === true) {
                      url_areaPermissions = 'accounts/' + $sessionStorage.editableRow.accountId + '/permissions/protectedareas/' + $sessionStorage.editableRow.organisation.id;
                } else {
                      url_areaPermissions = 'accounts/' + $sessionStorage.accountId + '/permissions/protectedareas/' + $sessionStorage.editableRow.organisation.id;
                }

                $scope.areaPermissions = $resource(url + url_areaPermissions, null, options).get();
                $scope.areaPermissions.$promise.then(function(success) {
                    console.log(success);
                    $scope.formDropdownData.areapermissions = success.items;
                    $scope.markArea(success.items[0]);
                }, errorCallback);

            }

            if ((($location.search().password === 'true') && $sessionStorage.editableRow ) ||
                (($location.search().password === 'true') && $sessionStorage.editableLicenses )) {
                options = {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                };

                if ($sessionStorage.editableRow.active === false) {
                    url_accounts = 'accounts/' + $sessionStorage.editableRow.accountId;
                } else {
                    url_accounts = 'accounts/' + $sessionStorage.editableLicenses.accountId;
                }

                $scope.getAccounts = $resource(url + url_accounts, null, options).makeRequest();
                $scope.getAccounts.$promise.then(function(success) {
                    console.log(success);
                    $scope.itemObj = success;
                    $scope.itemObj.license = success.accountLicense.license;
                    $scope.itemObj.organisationName = success.organisation.name;
                    $scope.itemObj.roleId = success.accountRole;
                    $scope.itemObj.licenseId = success.accountLicense.id;
                }, errorCallback);

                $scope.isvisible = false;
                $scope.passInput = true;
            }
            
            options = {
                makeRequest: {
                    method: 'GET',
                    isArray: true
                }
            };

            var url_rol = 'organisations/' + $sessionStorage.editableRow.organisation.id + '/roles';
            $scope.getRol = $resource(url + url_rol, null, options).makeRequest();
            $scope.getRol.$promise.then(function(success) {
                $scope.formDropdownData.role = success;
            }, errorCallback);


            $scope.formDropdownData.gen = [{
                id: '1',
                name: 'Masculin'
            }, {
                id: '2',
                name: 'Feminin'
            }];

            var newObj;
            var changeGender = function(gender) {
                if (gender === 'Masculin') {
                  return 'MALE';
                } else if (gender === 'Feminin') {
                  return 'FEMALE';
                }
            };

            var changeGender1 = function(gender) {
                if (gender === 'MALE') {
                  return 'Masculin';
                } else if (gender === 'FEMALE') {
                  return 'Feminin';
                }
            };

            // changeGender();

            $scope.sendPost = function(itemObj) {

                var url_element = url + 'accounts/create',
                      options = {
                          makeRequest: {
                              isArray: false
                          }
                      };

                newObj = {
                  'licenseId' : itemObj.licenseId,
                  'organisationId' : itemObj.organisation.id,
                  'username' : $scope.itemObj.username,
                  'password' : $scope.itemObj.password,
                  'firstName' : $scope.itemObj.firstName,
                  'lastName' : $scope.itemObj.lastName,
                  'phone' : $scope.itemObj.phone,
                  'email' : $scope.itemObj.email,
                  'dateOfBirth' : $scope.itemObj.dateOfBirth,
                  'gender' : $scope.itemObj.gender.name ? changeGender($scope.itemObj.gender.name) : changeGender($sessionStorage.editableRow.gender),
                  'roleId' : $scope.itemObj.roleId.id,
                  'webAccess' : false,
                  'mobileAccess' : false
                };

                if (($location.search().edit === 'true') && $sessionStorage.editableRow) {

                      newObj = {
                        'licenseId' : itemObj.licenseId,
                        'organisationId' : itemObj.organisation.id,
                        'username' : $scope.itemObj.username,
                        'firstName' : $scope.itemObj.firstName,
                        'lastName' : $scope.itemObj.lastName,
                        'phone' : $scope.itemObj.phone,
                        'email' : $scope.itemObj.email,
                        'dateOfBirth' : $scope.itemObj.dateOfBirth,
                        'gender' : $scope.itemObj.gender.name ? changeGender($scope.itemObj.gender.name) : changeGender($sessionStorage.editableRow.gender),
                        'roleId' : $scope.itemObj.roleId.id,
                        'webAccess' : $scope.itemObj.webAccess,
                        'mobileAccess' : $scope.itemObj.mobileAccess,
                      };

                      if ($sessionStorage.editableRow.accountId !== null) {
                        url_element = url + 'accounts/edit/' + $sessionStorage.editableRow.accountId;
                      } else {
                        url_element = url + 'accounts/edit/' + $sessionStorage.accountId;
                      }

                    options.makeRequest.method = 'PUT';

                } else if ($location.search().edit !== 'true') {
                    options.makeRequest.method = 'POST';
                }

                if ((($location.search().password === 'true') && $sessionStorage.editableRow ) ||
                    (($location.search().password === 'true') && $sessionStorage.editableLicenses )) {
                      newObj = {
                        'password' : $scope.itemObj.password
                      };

                      if ($sessionStorage.editableRow.active === false) {
                    
                          url_element = url + 'accounts/resetpassword/' + $sessionStorage.editableRow.accountId;

                      } else {

                          url_element = url + 'accounts/resetpassword/' + $sessionStorage.editableLicenses.accountId;
                      }

                    options.makeRequest.method = 'PUT';

                } 

                $scope.postElement = $resource(url_element ,null,options).makeRequest(newObj);
                $scope.postElement.$promise.then(
                    function(success) {
                        $scope.main.successHandlerCollback(success);
                        
                        if ((($location.search().password === 'true') && $sessionStorage.editableRow ) ||
                            (($location.search().password === 'true') && $sessionStorage.editableLicenses )) {
                              window.history.back();
                        } else {
                            $location.url('/utilizator-nou?edit=true');

                            if (success.accountId) {
                                $sessionStorage.accountId = success.accountId;
                                // $sessionStorage.editableRow.gender = success.gender;
                                // $scope.itemObj.gender.name = changeGender1(success.gender);
                            } 
                            // $sessionStorage.editableRow = success;
                        }

                    },errorCallback); 
            };

            $scope.accessWeb = function(itemObj) {

                if (itemObj.webAccess === true) {
                    console.log('true');
                    url_web = url + 'accounts/' + itemObj.id + '/webacces/enable';
                } else {
                    console.log('false');
                    url_web = url + 'accounts/' + itemObj.id + '/webacces/disable';
                }

                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

                options.makeRequest.method = 'PUT';
                
                $scope.postResolutions = $resource(url_web ,null,options).makeRequest();
                $scope.postResolutions.$promise.then(
                    function(success) {
                        $scope.main.successHandlerCollback(success);
                    },errorCallback); 
            };

            $scope.accessMobile = function(itemObj) {

                if (itemObj.mobileAccess === true) {
                    console.log('true');
                    url_web = url + 'accounts/' + itemObj.id + '/mobileacces/enable';
                } else {
                    console.log('false');
                    url_web = url + 'accounts/' + itemObj.id + '/mobileacces/disable';
                }

                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

                options.makeRequest.method = 'PUT';
                
                $scope.postResolutions = $resource(url_web ,null,options).makeRequest();
                $scope.postResolutions.$promise.then(
                    function(success) {
                        $scope.main.successHandlerCollback(success);
                    },errorCallback); 
            };

            $scope.activePermissions = function(items) {

                $scope.editableArea.active = true;

                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

                if ($sessionStorage.editableRow.isUsed === true) {
                      url_web = url + 'accounts/' + $sessionStorage.editableRow.accountId + '/permissions/protectedareas/' + $sessionStorage.editableArea.id;
                } else {
                      url_web = url + 'accounts/' + $sessionStorage.accountId + '/permissions/protectedareas/' + $sessionStorage.editableArea.id;
                }

                
                options.makeRequest.method = 'POST';
                
                $scope.postResolutions = $resource(url_web ,null,options).makeRequest();
                $scope.postResolutions.$promise.then(
                    function(success) {
                        $scope.main.successHandlerCollback(success);
                    },errorCallback); 
            };
            
            $scope.deactivePermissions = function(items) {

                $scope.editableArea.active = false;

                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

                if ($sessionStorage.editableRow.isUsed === true) {
                      url_web = url + 'accounts/' + $sessionStorage.editableRow.accountId + '/permissions/protectedareas/' + $sessionStorage.editableArea.id;
                } else {
                      url_web = url + 'accounts/' + $sessionStorage.accountId + '/permissions/protectedareas/' + $sessionStorage.editableArea.id;
                }
                
                options.makeRequest.method = 'DELETE';
                
                $scope.postResolutions = $resource(url_web ,null,options).makeRequest();
                $scope.postResolutions.$promise.then(
                    function(success) {
                        $scope.main.successHandlerCollback(success);
                    },errorCallback); 
            };

            $scope.markArea = function(items) {
                $scope.editableArea = items;
                $sessionStorage.editableArea = $scope.editableArea;

                if ($routeParams.edit === 'true') {
                    $scope.items = $sessionStorage.editableArea;
                }
            };

        }
    ]);