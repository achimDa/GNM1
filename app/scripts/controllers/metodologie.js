'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:MetodologieCtrl
 * @description
 * # MetodologieCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('MetodologieCtrl', ['$scope','$resource','HelpersService','$location','$sessionStorage','Go',
  function ($scope,$resource,HelpersService,$location,$sessionStorage,Go) {
     
    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    // delete $sessionStorage.addLink.label;
    $scope.session.title = 'Editare - Metodologie';

    $scope.itemObj = {};
    var url_methodologies,
        options;

	if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
        $scope.itemObj.name = $sessionStorage.editableRow.title;
    }
    
	$scope.sendPost = function(itemObj) {
        var newObj = {
            'number' : itemObj.number,
            'name' : itemObj.name,
            'content' : itemObj.content,
            'url' : itemObj.url
        };

        url_methodologies = url + 'methodologies';
        options = {
            makeRequest: {
                isArray: false
            }
        };

        if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
            url_methodologies = url + 'methodologies/' + $scope.itemObj.id;
            options.makeRequest.method = 'PUT';
        } else {
            options.makeRequest.method = 'POST';
        }

        $scope.postMethodologies = $resource(url_methodologies ,null,options).makeRequest(newObj);
        $scope.postMethodologies.$promise.then(
            function(success) {
                $scope.main.successHandlerCollback(success);
                Go.to({url:'administrare'});

            },errorCallback); 
	};

  }]);
