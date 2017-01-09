/* global $ */
/* global itemObj */
'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AddEventCtrl
 * @description
 * # AddEventCtrl
 * Controller of the gnmApp
 */

angular.module('gnmApp')
    .controller('AddEventCtrl', ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go', '$timeout' ,'leafletData',
        function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go, $timeout,leafletData) {

            var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

            // EXTEND API 

            $scope.session = $sessionStorage;
            $scope.itemObj = {};
            $scope.NewitemObj = {};
            $scope.validator = {};

            if ($sessionStorage.editableRow) {
                $scope.session.title = 'Modifica eveniment';
            } else {
                $scope.session.title = 'Adauga eveniment';
            }

            $scope.hideStuff = function () {
                $scope.hidden = true;
            };
            $scope.showStuff = function () {
                $scope.hidden = false;
            };

            var url_eventsMedia,
                url_events,
                url_contact,
                url_commentsDel,
                options,
                url_resolutions,
                url_resolutionsGet,
                url_audit,
                note;

            var getFileMedia = function() {

                if ($location.search().eventId) {
                        url_eventsMedia = 'events/' + $location.search().eventId + '/media';
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };
                    // delete $sessionStorage.editableRow;
                    options.makeRequest.method = 'GET';
                } else {
                        url_eventsMedia = 'events/' + $sessionStorage.editableRow.eventId + '/media';
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };
                    options.makeRequest.method = 'GET';
                }

                // var url_eventsMedia = 'events/' + $sessionStorage.editableRow.eventId + '/media';

                $scope.postEventsMedia = $resource(url + url_eventsMedia, null, options).get();

                $scope.postEventsMedia.$promise.then(function(success) {
                    $scope.formDropdownData.media = success.items;

                    $scope.urlDownload = url + 'events/media/';

                }, errorCallback);
            };

            var getContacts = function() {

                if ($location.search().eventId) {
                    $scope.getContactList = $resource(url + 'events/' + $location.search().eventId + '/comments' , {}, {
                        makeRequest: {
                            method: 'GET',
                            isArray: false
                        }
                    }).makeRequest();
                } else {
                    $scope.getContactList = $resource(url + 'events/' + $sessionStorage.editableRow.eventId + '/comments' , {}, {
                        makeRequest: {
                            method: 'GET',
                            isArray: false
                        }
                    }).makeRequest();
                }

                $scope.getContactList.$promise.then(function(success) {
                    $scope.notes = success.items;
                    $scope.items = $sessionStorage.editableRow;

                    $scope.markRowContacts(success.items[0]);

                }, errorCallback);
            };

            $scope.sendFile = function(files) {

                var fdata = new FormData();
                angular.forEach(files, function(value, index){
                    fdata.append("files", files[index]);
                });

                $resource(url + 'events/' + $scope.itemObj.id + '/media', null, {
                    postWithFile: {
                        method: "POST",
                        params: fdata,
                        transformRequest: angular.identity,
                        headers: { 'Content-Type': undefined }
                    }
                }).postWithFile(fdata).$promise.then(function(response){

                     getFileMedia();

                },function(error){
                    //error
                });
            };

            var getFileComments = function() {
                if (!$sessionStorage.editableContacts) {
                    return;
                }
                $scope.getFileComments = $resource(url + 'events/comments/' + $sessionStorage.editableContacts.id + '/media' , {}, {
                    makeRequest: {
                        method: 'GET',
                        isArray: false
                    }
                }).makeRequest();

                $scope.getFileComments.$promise.then(function(success) {
                    $scope.media = success.items;
                    $scope.markRowContactsMedia(success.items[0]);
                    // $scope.items = $sessionStorage.editableRow;
                    if ($sessionStorage.editableContactsMedia) {
                        $scope.urlDownloadCommentMedia = url + 'events/comments/media/' + $sessionStorage.editableContactsMedia.id;
                    }
                }, errorCallback);
            };

            $scope.sendFileComments = function(files) {
                
                var fdata = new FormData();
                angular.forEach(files, function(value, index){
                    fdata.append("files", files[index]);
                });

                $resource(url + 'events/comments/' + $sessionStorage.editableContacts.id + '/media', null, {
                    postWithFile: {
                        method: "POST",
                        params: fdata,
                        transformRequest: angular.identity,
                        headers: { 'Content-Type': undefined }
                    }
                }).postWithFile(fdata).$promise.then(function(response){

                    getFileComments();

                },function(error){
                    //error
                });
            };

            $scope.protectedAreaGeom = function(itemObj) {
                options = {
                    makeRequest: {
                        method: 'GET',
                        isArray: true
                    }
                };

                if (itemObj.arieProtected === null ) {
                    return;
                }

                var url_geom = 'protectedareas/' + itemObj.arieProtected.id;

                $scope.getGeom = $resource(url + url_geom, null, options).get();

                $scope.getGeom.$promise.then(function(success) {
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
                }, errorCallback);
            };

            if (($location.search().edit === 'true') && $sessionStorage.editableRow || $location.search().eventId ) {

                if ($location.search().eventId) {
                        url_events = url + 'events/' + $location.search().eventId;
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };
                    // delete $sessionStorage.editableRow;
                    delete $sessionStorage.editableContactsMedia;
                    options.makeRequest.method = 'GET';
                } else {
                        url_events = url + 'events/' + $sessionStorage.editableRow.eventId;
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };
                    options.makeRequest.method = 'GET';
                }

                var getResolution = function() {
                    if (($sessionStorage.user.authority === 'ROLE_COMISAR_GNM' && $sessionStorage.editableRow.canValidate === true) ||
                        ($sessionStorage.user.authority === 'ROLE_COMISAR_JUDETEAN' && $sessionStorage.editableRow.canValidate === true) ||
                        ($sessionStorage.user.authority === 'ROLE_ADMIN_GENERAL' && $sessionStorage.editableRow.canValidate === true)) {
                        
                        url_resolutionsGet = url + 'events/' + $sessionStorage.editableRow.eventId + '/resolutions';
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                        $scope.getResolutionGetter = $resource(url_resolutionsGet ,null,options).makeRequest();
                        $scope.getResolutionGetter.$promise.then(
                            function(success) {
                                $scope.validator.eventResolution = success.eventResolution;
                            },errorCallback);
                    }
                };

                var getAudit = function() {
                    if ($sessionStorage.user.authority !== 'ROLE_COMISAR_JUDETEAN' && $sessionStorage.editableRow.canValidate === true){

                        url_audit = url + 'events/' + $sessionStorage.editableRow.eventId + '/audits';
                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                        $scope.getAudit = $resource(url_audit ,null,options).makeRequest();
                        $scope.getAudit.$promise.then(
                            function(success) {
                                console.log('audit=======',success);
                                $scope.formDropdownData.audit = success.items;
                            },errorCallback);
                    }
                };

                if ($sessionStorage.editableRow) {
                    getResolution();
                    getAudit();
                }

                $scope.getEvents = $resource(url_events ,null,options).makeRequest();
                $scope.getEvents.$promise.then(
                    function(success) {
                        
                        $scope.itemObj = success;
                        console.log('ddasdasda',$scope.itemObj);
                        angular.extend($scope, {
                            markers: {
                                pin: {
                                    draggable: false,
                                    lat: $scope.itemObj.latitude,
                                    lng: $scope.itemObj.longitude
                                    // message: $sessionStorage.editableRow.eventCategory
                                }
                            }
                        });

                        getContacts();
                        if ($sessionStorage.editableContacts) {
                            getFileComments();
                        }
                        getFileMedia();
                        // if ($sessionStorage.editableContactsMedia) {
                        // }

                    },errorCallback);
            }

            var promises = [],
                MyData = {},
                urls = [
                    url + 'eventcategories/add',
                    url + 'animalcategories',
                    url + 'animalsizes',
                    url + 'animalspecies',
                    url + 'animals/active',
                    url + 'protectedareas/all'
                ],
                request1;

            $scope.result = {};
            for (var i = 0; i < urls.length; i++) {
                var promise = $http.get(urls[i]);
                promises.push(promise);
            }

            $scope.formDropdownData = {};

            $q.all(promises).then(function(values) {
                angular.forEach(values, function(value) {
                    var str = value.config.url,
                        res = str.split("/"),
                        apiSplit = res[res.length - 1];

                    if (str.indexOf('eventcategories') > -1) {
                        $scope.formDropdownData.eventcategory = value.data.items;
                    } else if (str.indexOf('animalcategories') > -1) {
                        $scope.formDropdownData.category = value.data.items;
                    } else if (str.indexOf('animalsizes') > -1) {
                        $scope.formDropdownData.size = value.data.items;
                    } else if (str.indexOf('animalspecies') > -1) {
                        $scope.formDropdownData.species = value.data.items;
                    } else if (str.indexOf('animals') > -1) {
                        $scope.formDropdownData.animals = value.data.items;
                    } else if (str.indexOf('protectedareas/all') > -1) {
                        $scope.formDropdownData.protectedareas = value.data.items;
                    }
                    angular.extend($scope.result, value.data.items);
                });
            },
            function(error) {
                console.log("Errore: " + error.data);
            });
            
            angular.extend($scope, {
                center: {
                    autoDiscover: true,
                    zoom: 11
                },
                 defaults: {
                    scrollWheelZoom: false
                },
                markers: {
                    pin: {
                        lat: 46.77173,
                        lng: 23.51932,
                        focus: true,
                        draggable: true,
                        // message: "Hi there!",
                        icon: {}
                    }
                },
                // layers: {
                //   fitBoundsaselayers: {
                //         osm: {
                //             name: 'OpenStreetMap',
                //             url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                //             type: 'xyz'
                //         },
                //     },
                //     doRefresh: true,
                //     overlays:{}
                // },
                events: {
                    markers: {
                        enable: ['dragend']
                            //logic: 'emit'
                    }
                }
            });

            $scope.$on("leafletDirectiveMarker.dragend", function(event, args) {
                $scope.markers.pin.lat = args.model.lat;
                $scope.markers.pin.lng = args.model.lng;
            });

            $scope.stepsModel = [];

            $scope.imageUpload = function(event) {
                var files = event.target.files; //FileList object

                for (var i = 0; i < files.length; i++) {
                    var file = files[i],
                        reader = new FileReader();
                    reader.onload = $scope.imageIsLoaded;
                    reader.readAsDataURL(file);
                }
            };

            $scope.imageIsLoaded = function(e) {
                $scope.$apply(function() {
                    $scope.stepsModel.push(e.target.result);
                });
            };

            $scope.animalInfo = false;

            $scope.sendPost = function(itemObj) {
                var url1;
                var NewitemObj = {
                        'eventCategory': {
                            'id': $scope.itemObj.eventCategory.id
                        },

                        'animalCategory': (typeof $scope.itemObj.animalCategory !== 'undefined') && ($scope.itemObj.animalCategory !== null)  ? {
                            'id': $scope.itemObj.animalCategory.id 
                        } : null,

                        'animalSize': (typeof $scope.itemObj.animalSize !== 'undefined') && ($scope.itemObj.animalSize !== null) ? {
                            'id': $scope.itemObj.animalSize.id
                        } : null,
                        // 'migrate': $scope.itemObj.migrate,
                        'migrate': $scope.itemObj.migrate ? $scope.itemObj.migrate : false,
                        'animalSpecies': (typeof $scope.itemObj.animalSpecies !== 'undefined') && ($scope.itemObj.animalSpecies !== null) ? {
                            'id': $scope.itemObj.animalSpecies.id
                        } : null,
                        'animal': (typeof $scope.itemObj.animal !== 'undefined') && ($scope.itemObj.animal !== null) ? {
                            'id': $scope.itemObj.animal.id 
                        } : null,
                        'description': $scope.itemObj.description,
                        'eventDate': $scope.itemObj.eventDate ? $scope.itemObj.eventDate : new Date(),
                        'latitude': $scope.markers.pin.lat,
                        'longitude': $scope.markers.pin.lng
                };

                var url_event = url + 'events',
                    options = {
                        makeRequest: {
                            isArray: false
                        }
                    };

                if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
                    url_event = url + 'events/' +  $scope.itemObj.id;
                    options.makeRequest.method = 'PUT';
                } else if (($location.search().edit === 'true') && !$sessionStorage.editableRow) {
                    console.log('dasdasdasdasdas===============');
                    url_event = url + 'events/' +  $scope.itemObj.id;
                    options.makeRequest.method = 'PUT';
                }
                else {
                    options.makeRequest.method = 'POST';
                    delete $sessionStorage.editableContactsMedia;
                }

                $scope.postEvent = $resource(url_event ,null,options).makeRequest(NewitemObj);
                $scope.postEvent.$promise.then(
                    function(success) {
                        $scope.main.successHandlerCollback(success);

                        $location.url('/add-event?edit=true' + '&eventId=' + success.id);

                    },errorCallback); 
            };

            var changeMigrator = function(boolean) {
                if (boolean === 'Da') {
                  return true;
                } else if (boolean === 'Nu') {
                  return false;
                }
            };

            $scope.animalSearch = function(itemObj) {

                var obj = {
                     'animalCategoryId': ($scope.itemObj.animalCategory && $scope.itemObj.animalCategory.id) ? $scope.itemObj.animalCategory.id : null,
                     'animalSizeId': ($scope.itemObj.animalSize && $scope.itemObj.animalSize.id) ? $scope.itemObj.animalSize.id : null,
                     'migrating': $scope.itemObj.migrate ? changeMigrator($scope.itemObj.migrate) : null,
                     'animalSpecieId': ($scope.itemObj.animalSpecies && $scope.itemObj.animalSpecies.id) ? $scope.itemObj.animalSpecies.id : null
                };

                var url_element = url + 'animals/search',
                      options = {
                          makeRequest: {
                              isArray: false
                          }
                      };
                options.makeRequest.method = 'POST';

                $scope.postElement = $resource(url_element ,null,options).makeRequest(obj);
                $scope.postElement.$promise.then(
                  function(success) {
                      $scope.formDropdownData.animals = success.items;
                  },errorCallback); 
            };


            $scope.note = {
                'comment': '',
            };

            $scope.state = 'adauga';

            $scope.addComentariu = function(addNoteForm, note) {

                if (addNoteForm.$valid) {

                    if ($location.search().eventId) {
                            url_contact = url + 'events/' + $location.search().eventId + '/comments';
                            options = {
                                makeRequest: {
                                    isArray: false
                                }
                        };
                    } else {
                            url_contact = url + 'events/' + $sessionStorage.editableRow.eventId + '/comments' ;
                            options = {
                                makeRequest: {
                                    isArray: false
                                }
                        };
                    }

                    if ($scope.state === 'modifica') {

                        if ($sessionStorage.editableRow) {
                            url_contact = url + 'events/' + $sessionStorage.editableRow.eventId +'/comments/' + $sessionStorage.editableContacts.id;
                        } else if (!$sessionStorage.editableRow) {
                            url_contact = url + 'events/' + $location.search().eventId +'/comments/' + $sessionStorage.editableContacts.id;
                        }
                        
                        note = {
                            'comment' : $scope.note.comment
                        };

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

            $scope.markRowContacts = function(note) {

                $scope.editableContacts = note;
                $sessionStorage.editableContacts = $scope.editableContacts;
                // appData.editableRow = $scope.editableRow;

                // $scope.media = $scope.media;
                // if ($sessionStorage.editableContacts) {
                    getFileComments();
                // }

                if ($routeParams.edit === 'true') {
                    $scope.note = $sessionStorage.editableContacts;
                }
            };

            $scope.markRowContactsMedia = function(fRow) {
                $scope.editableContactsMedia = fRow;
                $sessionStorage.editableContactsMedia = $scope.editableContactsMedia;
                // appData.editableRow = $scope.editableRow;

                // $scope.media = $scope.media;

                if ($routeParams.edit === 'true') {
                    $scope.fRow = $sessionStorage.editableContactsMedia;
                }
            };

            $scope.delMedia = function(fileMedia) {

                var url_commentsDel = url + 'events/media/' + fileMedia.id;
                    options = {
                      del : {
                        method: 'DELETE',
                        isArray: false
                      }
                    };

                $scope.commentsDel = $resource(url_commentsDel ,null,options).del();

                $scope.commentsDel.$promise.then(function(success) {
                    angular.forEach($scope.formDropdownData.media, function(each, index) {
                        if (each.id === fileMedia.id) {
                            $scope.formDropdownData.media.splice(index, 1);
                        }
                    });
                }, errorCallback);
            };

            $scope.delComment = function(notes) {

                if ($location.search().eventId) {
                        url_commentsDel = url + 'events/' + $location.search().eventId +'/comments/' + $sessionStorage.editableContacts.id;
                        options = {
                          del : {
                            method: 'DELETE',
                            isArray: false
                          }
                        };
                    } else {
                        url_commentsDel = url + 'events/' + $sessionStorage.editableRow.eventId +'/comments/' + $sessionStorage.editableContacts.id;
                        options = {
                          del : {
                            method: 'DELETE',
                            isArray: false
                          }
                        };
                    }

                $scope.commentsDel = $resource(url_commentsDel ,null,options).del();

                $scope.commentsDel.$promise.then(function(success) {

                    angular.forEach(notes, function(each, index) {
                        if (each.id === $sessionStorage.editableContacts.id) {
                          notes.splice(index, 1);
                          delete $scope.media;
                        }
                    });

                }, errorCallback);
            };

            $scope.delCommentMedia = function(media) {

                var url_commentsDel = url + 'events/comments/media/' + $sessionStorage.editableContactsMedia.id ;
                    options = {
                      del : {
                        method: 'DELETE',
                        isArray: false
                      }
                    };

                $scope.commentsDel = $resource(url_commentsDel ,null,options).del();

                $scope.commentsDel.$promise.then(function(success) {

                    angular.forEach(media, function(each, index) {
                        if (each.id === $sessionStorage.editableContactsMedia.id) {
                          media.splice(index, 1);
                        }
                    });

                }, errorCallback);
            };

            $scope.sendValidator = function(validator) {

                var valid = {
                    'eventResolution' : validator.eventResolution
                };

                url_resolutions = url + 'events/' + $sessionStorage.editableRow.eventId + '/resolutions';
                options = {
                    makeRequest: {
                        isArray: false
                    }
                };

                options.makeRequest.method = 'POST';

                $scope.postResolutions = $resource(url_resolutions ,null,options).makeRequest(valid);
                $scope.postResolutions.$promise.then(
                    function(success) {

                        if ($sessionStorage.user.authority === 'ROLE_COMISAR_GNM' || 
                            $sessionStorage.user.authority === 'ROLE_COMISAR_JUDETEAN' || 
                            $sessionStorage.user.authority === 'ROLE_ADMIN_GENERAL') {
                            if (validator.valid === 'true') {
                                console.log('true');
                                url_resolutions = url + 'events/county/valid/' + $sessionStorage.editableRow.id;
                            } else {
                                console.log('false');
                                url_resolutions = url + 'events/county/invalid/' + $sessionStorage.editableRow.id;
                            }
                        } else {
                            if (validator.valid === 'true') {
                                console.log('true');
                                url_resolutions = url + 'events/protectedarea/valid/' + $sessionStorage.editableRow.id;
                            } else {
                                console.log('false');
                                url_resolutions = url + 'events/protectedarea/invalid/' + $sessionStorage.editableRow.id;
                            }
                        }

                        options = {
                            makeRequest: {
                                isArray: false
                            }
                        };

                        options.makeRequest.method = 'PUT';
                        
                        $scope.postResolutions = $resource(url_resolutions ,null,options).makeRequest();
                        $scope.postResolutions.$promise.then(
                            function(success) {
                                $scope.main.successHandlerCollback(success);
                                Go.to({url:'rezolutii-notificari'});
                            },errorCallback); 

                    },errorCallback); 
            };


        }

    ]);
