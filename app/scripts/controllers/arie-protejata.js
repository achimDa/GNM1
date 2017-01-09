'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:ArieProtejataCtrl
 * @description
 * # ArieProtejataCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
	.controller('ArieProtejataCtrl',['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go', 'leafletData',
        function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go, leafletData) {

			var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            $scope.session = $sessionStorage;

            $scope.itemObj = {};
            $scope.formDropdownData = {};

            $scope.hideStuff = function () {
                $scope.hidden = true;
            };
            $scope.showStuff = function () {
                $scope.hidden = false;
            };

            $scope.itemObj = {
                startDate: null,
                endDate: null,
                show: false
            };

            var getContacts = function() {
                $scope.getProtectedAreas = $resource(url + 'protectedareas/' + $sessionStorage.editableRow.id + '/responsibilities', {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();

                $scope.getProtectedAreas.$promise.then(function(success) {
                    $scope.notes = success.items;
                    $scope.markRow2(success.items[0]);
                    $scope.items = $sessionStorage.editableRowContacts;
                }, errorCallback);
            };

            var getInfo = function() {
                $scope.getAdditionalInfo = $resource(url + 'protectedareas/' + $sessionStorage.editableRow.id + '/additionalinfos', {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();

                $scope.getAdditionalInfo.$promise.then(function(success) {
                    $scope.infos = success.items;
                    $scope.Info(success.items[0]);
                    $scope.items = $sessionStorage.editableInfo;
                }, errorCallback);
            };

            var getDoc = function() {
                $scope.getInfoDoc = $resource(url + 'protectedareas/' + $sessionStorage.editableRow.id + '/documents', {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();

                $scope.getInfoDoc.$promise.then(function(success) {
                    $scope.documents = success.items;
                    $scope.Doc(success.items[0]);
                    $scope.items = $sessionStorage.editableDoc;
                }, errorCallback);
            };

            $scope.sendFile = function(files) {

                var fdata = new FormData();
                angular.forEach(files, function(value, index){
                    fdata.append("files", files[index]);
                });

                $resource(url + 'protectedareas/' + $sessionStorage.editableRow.id + '/documents/' + $sessionStorage.editableDoc.id + '/media', null, {
                    postWithFile: {
                        method: "POST",
                        params: fdata,
                        transformRequest: angular.identity,
                        headers: { 'Content-Type': undefined }
                    }
                }).postWithFile(fdata).$promise.then(function(response){

                     getDoc();
                     // console.log(response);

                }, errorCallback);
            };


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
                  overlays:{}
              }
            });

            var options = {
                makeRequest: {
                    method: 'GET',
                    isArray: true
                }
            };

			var url_Protectedareatypes = 'protectedareatypes',
				url_Commissariats = 'organisations/all/commissariats',
				url_ProtectedAreaMaps = 'protectedareas/maps';


            $scope.getProtectedareatypes = $resource(url + url_Protectedareatypes, null, options).get();
            $scope.getCommissariats = $resource(url + url_Commissariats, null, options).get();
            $scope.getProtectedAreaMaps = $resource(url + url_ProtectedAreaMaps, null, options).get();
         
            $scope.getProtectedareatypes.$promise.then(function(success) {
                $scope.formDropdownData.protectedareatypes = success.items;
            }, errorCallback);
            $scope.getCommissariats.$promise.then(function(success) {
                $scope.formDropdownData.commissariats = success.items;
            }, errorCallback);
            $scope.getProtectedAreaMaps.$promise.then(function(success) {
                $scope.formDropdownData.protectedAreaMaps = success.items;
                console.log('suc', success.items);
                

            }, errorCallback);


            
            $scope.assignedArea = function(itemObj) {

            	if (itemObj.maparea !== null) {
	            	var url_Area = 'protectedareas/maps/' + itemObj.maparea.id + '/columninfo';
	            	$scope.getArea = $resource(url + url_Area, null, options).get();
	            	$scope.getArea.$promise.then(function(areaData) {
	                
	                	$scope.formDropdownData.area = areaData.items;

		            }, errorCallback);
            	} else {
            		delete $scope.formDropdownData.area;
            	}

            	
            };

                        
            $scope.assignedGeometry = function(itemObj) {

            	if (itemObj.area !== null) {
		        	var url_Geometry = 'protectedareas/maps/' + itemObj.maparea.id + '/geometry/' + itemObj.area[0];

		        	$scope.getGeometry = $resource(url + url_Geometry, null, options).get();
		        	$scope.getGeometry.$promise.then(function(geometryData) {
		                angular.extend($scope.layers.overlays, {
			                countries: {
			                    name:'Coridoare ecologice',
			                    doRefresh: true,
			                    type: 'geoJSONShape',
			                    data: geometryData.geoJson,
			                    visible: true,
			                    layerOptions: {
			                        style: {
			                                color: '#000',
			                                fillColor: '#ff0000',
			                                weight: 2.0,
			                                opacity: 0.9,
			                                fillOpacity: 0.2,

			                        }
			                    }
			                }
			            });
			            $scope.geometry = geometryData.geometry;

                        leafletData.getMap().then(function(map) {
                            var latlngs = [];
                            for (var i in geometryData.geoJson.features[0].geometry.coordinates) {
                                var coord = geometryData.geoJson.features[0].geometry.coordinates[i];
                                for (var j in coord) {
                                    var points = coord[j];
                                    for (var k in points) {
                                        latlngs.push(L.GeoJSON.coordsToLatLng(points[k]));
                                    }
                                }
                            }
                            map.fitBounds(latlngs);
                        });

			            console.log('geo',$scope.geometry);
		            }, errorCallback);
            	}
            };
            
            if (($location.search().edit === 'true') && $sessionStorage.editableRow) {

                $scope.postProtectedArea = $resource(url + 'protectedareas/' + $sessionStorage.editableRow.id, {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();
                $scope.postProtectedArea.$promise.then(function(success) {

                    $scope.itemObj = success;

                    angular.extend($scope.layers.overlays, {
		                countries: {
		                    name:'Coridoare ecologice',
		                    doRefresh: true,
		                    type: 'geoJSONShape',
		                    data: success.geometry,
		                    visible: true,
		                    layerOptions: {
		                        style: {
		                                color: '#000',
		                                fillColor: '#ff0000',
		                                weight: 2.0,
		                                opacity: 0.9,
		                                fillOpacity: 0.2,
		                        }
		                    }
		                }
		            });

                    leafletData.getMap().then(function(map) {
                        var latlngs = [];
                        for (var i in success.geometry.features[0].geometry.coordinates) {
                            var coord = success.geometry.features[0].geometry.coordinates[i];
                            for (var j in coord) {
                                var points = coord[j];
                                for (var k in points) {
                                    latlngs.push(L.GeoJSON.coordsToLatLng(points[k]));
                                }
                            }
                        }
                        map.fitBounds(latlngs);
                    });

		            getContacts();
		            getInfo();
		            getDoc();

                 	var options = {
                        makeRequest: {
                            method: 'GET',
                            isArray: true
                        }
                    };

                    var url_InfoCustody = 'organisations/all/administrators',
                		url_Residence = 'protectedareas/' + $sessionStorage.editableRow.id + '/residences',
                		url_Restrictions = 'protectedareas/' + $sessionStorage.editableRow.id + '/restrictions';
                		// url_Email= 'organisations/' + $sessionStorage.editableRow.organisation.id + '/email';


                    $scope.getInfoCustory = $resource(url + url_InfoCustody, null, options).get();
                    $scope.getResidences = $resource(url + url_Residence, null, options).get();
                    $scope.getRestrictions = $resource(url + url_Restrictions, null, options).get();
                    // $scope.getEmail = $resource(url + url_Email, null, options).get();

                    $scope.getInfoCustory.$promise.then(function(success) {
                        $scope.formDropdownData.custody = success.items;
                    }, errorCallback);

		            $scope.getResidences.$promise.then(function(success) {
		                $scope.formDropdownData.residences = success.items;
		                $scope.markResidence(success.items[0]);
		            }, errorCallback);

		            $scope.getRestrictions.$promise.then(function(success) {
		                $scope.formDropdownData.restrictions = success.items;
		                $scope.markRestrictions(success.items[0]);
		            }, errorCallback);

		            // $scope.getEmail.$promise.then(function(success) {
		            //     $scope.emailObj.notificationEmail = success.notificationEmail;
		            // }, errorCallback);

		            $scope.urlDownload = url + 'protectedareas/documents/media/';

                }, errorCallback);
            }

            $scope.markResidence = function(items) {
                $scope.editableResidence = items;
                $sessionStorage.editableResidence = $scope.editableResidence;
                // delete $sessionStorage.editableRow;
            };

            $scope.markRow2 = function(note) {
                $scope.editableContact = note;
                $sessionStorage.editableContact = $scope.editableContact;
                // appData.editableRow = $scope.editableRow;

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableContact;
                }

            };

            $scope.markRestrictions = function(items) {
                $scope.editableRestrictions = items;
                $sessionStorage.editableRestrictions = $scope.editableRestrictions;
                // delete $sessionStorage.editableRow;
            };

            $scope.Info = function(note) {
                $scope.editableInfo = note;
                $sessionStorage.editableInfo = $scope.editableInfo;
                // appData.editableRow = $scope.editableRow;

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableInfo;
                }

            };

            $scope.Doc = function(note) {
                $scope.editableDoc = note;
                $sessionStorage.editableDoc = $scope.editableDoc;
                // appData.editableRow = $scope.editableRow;

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableDoc;
                }

            };

			$scope.sendPost = function(itemObj) {

                var newItemObj = {
					    'name' : itemObj.name,
					    'protectedAreaType' : {
					        'id' : itemObj.protectedAreaType.id
					    },
					    'validationOrganisation': (typeof $scope.itemObj.validationOrganisation !== 'undefined') && ($scope.itemObj.validationOrganisation !== null)  ? {
                            'id': $scope.itemObj.validationOrganisation.id 
                        } : null,
					    'notificationOrganisation': (typeof $scope.itemObj.notificationOrganisation !== 'undefined') && ($scope.itemObj.notificationOrganisation !== null)  ? {
                            'id': $scope.itemObj.notificationOrganisation.id 
                        } : null,
					    'custodyOrganisation': (typeof itemObj.custodyOrganisation !== 'undefined') && (itemObj.custodyOrganisation !== null)  ? {
                            'id': itemObj.custodyOrganisation.id 
                        } : null,
                        'startDate' : itemObj.startDate,
                        'endDate' : itemObj.endDate,
					    'geometry': $scope.geometry
					};

                var url_protectedArea = url + 'protectedareas',
                    options = {
                        makeRequest: {
                            method: 'POST',
                            isArray: false
                        }
                    };

                if ($routeParams.edit === 'true') {
                    url_protectedArea = url + 'protectedareas/' + $sessionStorage.editableRow.id;
                    options.makeRequest.method = 'PUT';

                } else {
                    options.makeRequest.method = 'POST';
                    //   $scope.tabs = [{ 
                    //  disabled: false 
                    // }];
                }

                $scope.postProtectedArea = $resource(url_protectedArea, null, options).makeRequest(newItemObj);
                $scope.postProtectedArea.$promise.then(function(response) {

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

                    $location.url('/arie-protejata?edit=true');

                }, errorCallback);
            };

            $scope.note = {
                'active' : true,
                'name' : '',
                'type' : '',
                'description' : ''
            };

            $scope.state = 'adauga';

            $scope.addComment = function(addNoteForm, note) {

                note.active = true;

                if (addNoteForm.$valid) {

                    var url_contact = url + 'protectedareas/' + $sessionStorage.editableRow.id + '/responsibilities' ,
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                    if ($scope.state === 'modifica') {
                        url_contact = url + 'protectedareas/' + $sessionStorage.editableRow.id +'/responsibilities/' + $sessionStorage.editableContact.id;
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

            $scope.addInfo = function(addInfoForm, note) {

                note.active = true;

                if (addInfoForm.$valid) {

                    var additionalInfo = {
                    	'additionalInfoId' : $sessionStorage.editableInfo.additionalInfoId,
                    	'description' : note.description
                    };

                    var url_contact = url + 'protectedareas/' + $sessionStorage.editableRow.id + '/additionalinfos' ,
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

            
                    if ($scope.state === 'modifica') {
	                    options.makeRequest.method = 'POST';
                    } else if ($scope.state === 'adauga') {
                        options.makeRequest.method = 'POST';
                    }

                    $scope.postComment = $resource(url_contact, null, options).makeRequest(additionalInfo);
                    $scope.postComment.$promise.then(function(success) {
                        $('#addInfoModal').modal('hide');
                        getInfo();
                    }, errorCallback);
                }
            };

            $scope.addDoc = function(addDocForm, note) {

                if (addDocForm.$valid) {

                    var url_contact = url + 'protectedareas/' + $sessionStorage.editableRow.id + '/documents' ,
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                    if ($scope.state === 'modifica') {
                        url_contact = url + 'protectedareas/' + $sessionStorage.editableRow.id +'/documents/' + $sessionStorage.editableDoc.id;
                        
                        note = {
                        	'type' : note.type,
                        	'description' : note.description
                        };

                        options.makeRequest.method = 'PUT';
                        // $sessionStorage.editableRow.id = $scope.postComisariat.item.id;
                    } else if ($scope.state === 'adauga') {
                        options.makeRequest.method = 'POST';
                    }

                    $scope.postComment = $resource(url_contact, null, options).makeRequest(note);
                    $scope.postComment.$promise.then(function(success) {
                        $('#addDocModal').modal('hide');
                        getDoc();
                    }, errorCallback);
                }
            };

            $scope.changeState = function(state) {

                $scope.state = state;

                if (state === 'adauga') {
                    $scope.note = {};
                }
            };

            $scope.activeResidence = function(items) {

                $scope.editableResidence.active = true;
                $scope.items = $resource(url + 'protectedareas/residences/' + $sessionStorage.editableResidence.id + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableResidence);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.deactiveResidence = function(items) {

                $scope.editableResidence.active = false;
                $scope.items = $resource(url + 'protectedareas/residences/' + $sessionStorage.editableResidence.id + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableResidence);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.activeRow = function(items) {

                $scope.editableContact.active = true;
                $scope.items = $resource(url + 'protectedareas/responsibilities/' + $sessionStorage.editableContact.id + '/enable', {}, {
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
                $scope.items = $resource(url + 'protectedareas/responsibilities/' + $sessionStorage.editableContact.id + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableContact);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.activeRestrictions = function(items) {

                $scope.editableRestrictions.active = true;
                $scope.items = $resource(url + 'protectedareas/' +  $sessionStorage.editableRow.id + '/restrictions/' + $sessionStorage.editableRestrictions.id + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableRestrictions);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.deactiveRestrictions = function(items) {

                $scope.editableRestrictions.active = false;
                $scope.items = $resource(url + 'protectedareas/' +  $sessionStorage.editableRow.id + '/restrictions/' + $sessionStorage.editableRestrictions.id + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableRestrictions);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.activeInfo = function(items) {

                $scope.editableInfo.active = true;
                $scope.items = $resource(url + 'protectedareas/' +  $sessionStorage.editableRow.id + '/additionalinfos/' + $sessionStorage.editableInfo.additionalInfoId + '/enable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableInfo);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.deactiveInfo = function(items) {

                $scope.editableInfo.active = false;
                $scope.items = $resource(url + 'protectedareas/' +  $sessionStorage.editableRow.id + '/additionalinfos/' + $sessionStorage.editableInfo.additionalInfoId + '/disable', {}, {
                    makeRequest: {
                        method: 'PUT',
                        isArray: false
                    }
                }).makeRequest($scope.editableInfo);
                $scope.items.$promise.then(function(success) {
                    console.log(success);
                }, errorCallback);

            };

            $scope.delMedia = function(note) {

                var url_commentsDel = url + 'protectedareas/documents/media/' + note.mediaId;
                    options = {
                      del : {
                        method: 'DELETE',
                        isArray: false
                      }
                    };

                $scope.mediaDel = $resource(url_commentsDel ,null,options).del();

                $scope.mediaDel.$promise.then(function(success) {
                   
                    getDoc();
                    // angular.forEach($scope.documents, function(each, index) {
                    //     if (each.id === note.id) {
                    // 		$scope.documents.splice(index, 1);
                    //     }
                    // });
                }, errorCallback);
            };

            $scope.deleteDocument = function(note) {

                var url_rowDocument = url + 'protectedareas/documents/' + $sessionStorage.editableDoc.id;
                    options = {
                      del : {
                        method: 'DELETE',
                        isArray: false
                      }
                    };

                $scope.rowDel = $resource(url_rowDocument ,null,options).del();

                $scope.rowDel.$promise.then(function(success) {
                   getDoc();
                }, errorCallback);
            };

		}
	]);
