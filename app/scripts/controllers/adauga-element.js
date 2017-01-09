'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:AdaugaElementCtrl
 * @description
 * # AdaugaElementCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('AdaugaElementCtrl', ['$scope','$sessionStorage','Upload','$resource','HelpersService','$http','$q','$location','Go', '$window',
  function ($scope,$sessionStorage,Upload,$resource,HelpersService,$http,$q,$location,Go,$window) {

    var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.session.title = 'Element regnul Animalia';

    if (($location.search().edit === 'true') && $sessionStorage.editableRow ) {
        $scope.itemObj = $sessionStorage.editableRow;
    }
    var promises = [],
        MyData = {},
        urls =[ 
                url + 'animalcategories',
                url + 'animalsizes',
                url + 'animalspecies'
              ];

    $scope.result = {};
      for (var i = 0; i < urls.length; i++) {
        var promise = $http.get(urls[i]);
        promises.push(promise); 
      }
    
    $scope.formDropdownData = {};

    $q.all(promises).then(function(values){
      angular.forEach(values, function(value){
        var str = value.config.url,
            res = str.split("/"),
            apiSplit = res[res.length - 1];

        if (str.indexOf('animalcategories') > -1) {
          $scope.formDropdownData.category = value.data.items;
        } else  if (str.indexOf('animalsizes') > -1)  {
          $scope.formDropdownData.size = value.data.items;
        } else  if (str.indexOf('animalspecies') > -1)  {
          $scope.formDropdownData.species = value.data.items;
        }
          angular.extend($scope.result, value.data.items);
        });
      },errorCallback); 


    $scope.stepsModel = [];

    $scope.imageUpload = function(event){
         var files = event.target.files; //FileList object
         
         for (var i = 0; i < files.length; i++) {
             var file = files[i];
                 var reader = new FileReader();
                 reader.onload = $scope.imageIsLoaded; 
                 reader.readAsDataURL(file);
         }

    };

  $scope.imageIsLoaded = function(e){
      $scope.$apply(function() {
          $scope.stepsModel.push(e.target.result);
      });
      
  };

  $scope.bob = {};
  $scope.bob.select = function(files) {
      $scope.bob.fileMedia = files;
  };

    $scope.sendPost = function(itemObj) {
 
        var newObj = {
            'name' : $scope.itemObj.name,
            'description' : $scope.itemObj.description,
            'category' : {
                    'id' : $scope.itemObj.category.id
                  },
            'size' : {
                    'id' : $scope.itemObj.size.id
                  },
            'migrate' : $scope.itemObj.migrate ? $scope.itemObj.migrate : false,
            'species' : {
                    'id' : $scope.itemObj.species.id
                  }
          };
        
        var url_element = url + 'animals',
              options = {
                  makeRequest: {
                      isArray: false
                  }
              };

          if (($location.search().edit === 'true') && $sessionStorage.editableRow) {
              url_element = url + 'animals/' + $scope.itemObj.id;
              options.makeRequest.method = 'PUT';
          } else {
              options.makeRequest.method = 'POST';
          }

          $scope.postElement = $resource(url_element ,null,options).makeRequest(newObj);
          $scope.postElement.$promise.then(
              function(success) {
                  $scope.main.successHandlerCollback(success);
                  // Go.to({url:'regnul-animalia'});
                  $window.history.back();
              },errorCallback); 
    };

}]);
