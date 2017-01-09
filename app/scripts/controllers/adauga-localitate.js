'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AdaugaLocalitateCtrl
 * @description
 * # AdaugaLocalitateCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
.controller('AdaugaLocalitateCtrl', 
    function ($scope,$http,$resource,HelpersService,$sessionStorage,$location,Go) {
    
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.session = $sessionStorage;
    $scope.itemObj = {};
    
    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
        // $scope.itemObj.county = $sessionStorage.editableRow.county.id;
    }

    $scope.data = $resource(HelpersService.domain + 'counties', {}, {
            makeRequest: {
                method: 'GET'
            }
        }).makeRequest();

    $scope.data.$promise.then(function(response) {
        if (response.message === 'success') {
           console.log('seccess');
        }
    },
    function(error) {
        if (error.data.message === 'Wrong Password') {
        
        } else {
           
        }
    });

    $scope.sendPost = function(itemObj) {
 
        var newObj = {
			'name' : $scope.itemObj.name,
			'county' : {
                'id' : $scope.itemObj.county.id
            }
		};
        
        var url_cities = url + 'cities',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_cities = url + 'cities/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postCities = $resource(url_cities ,null,options).makeRequest(newObj);
        $scope.postCities.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});

            },errorCallback); 
    };

  });
