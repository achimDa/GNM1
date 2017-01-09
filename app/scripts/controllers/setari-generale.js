'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:SetariGeneraleCtrl
 * @description
 * # SetariGeneraleCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('SetariGeneraleCtrl', ['$scope', 'HelpersService', '$sessionStorage', '$location', '$http', '$q', '$resource', 'Upload', '$routeParams', '$window', 'Go', '$timeout' , 'Navigator',
    function($scope, HelpersService, $sessionStorage, $location, $http, $q, $resource, Upload, $routeParams, $window, Go, $timeout, Navigator) {

  	var url = HelpersService.domain,
        errorCallback = $scope.main.errorHandlerCollback;

    $scope.navigatorConfig = Navigator.links;

    // EXTEND API 

    $scope.session = $sessionStorage;
    $scope.session.addLink.title = 'Setari generale';
    $scope.itemObj = {};

	$scope.formDropdownData = {};

    var options = {
        makeRequest: {
            method: 'GET',
            isArray: true
        }
    };

    var url_GetSettings = 'settings';

    $scope.getGeneralSettings = $resource(url + url_GetSettings, null, options).get();

    $scope.getGeneralSettings.$promise.then(function(success) {
        $scope.itemObj = success;
        $scope.itemObj.allowedImageExtensions = success.allowedImageExtensions.toString();
        $scope.itemObj.allowedVideoExtensions = success.allowedVideoExtensions.toString();
        $scope.itemObj.allowedExtensionsOfFile = success.allowedExtensionsOfFile.toString();
    }, errorCallback);

    $scope.sendPost = function(itemObj) {

        var newObj = {
 
				'publicCanReportEventOutsideProtectedArea': itemObj.publicCanReportEventOutsideProtectedArea,
				'adminCanReportEventOutsideProtectedArea': itemObj.adminCanReportEventOutsideProtectedArea,
				'publicCanAttachVideo': itemObj.publicCanAttachVideo,
				'nbOfDaysInvalidEventsAreKept': itemObj.nbOfDaysInvalidEventsAreKept,
				'nbOfDaysEventsAreConsideredValid': itemObj.nbOfDaysEventsAreConsideredValid,
				'nbOfEventsAttachedOnMobile': itemObj.nbOfEventsAttachedOnMobile,
				'maxImageSize': itemObj.maxImageSize,
				'nbOfImageAttachmentsPerEvent': itemObj.nbOfImageAttachmentsPerEvent,
				'allowedImageExtensions': itemObj.allowedImageExtensions.split(","),
				'maxImageResWidth': itemObj.maxImageResWidth,
				'maxImageResHeight': itemObj.maxImageResHeight,
				'maxVideoSize': itemObj.maxVideoSize,
				'nbOfVideoAttachmentsPerEvent': itemObj.nbOfVideoAttachmentsPerEvent,
				'allowedVideoExtensions': itemObj.allowedVideoExtensions.split(","),
				'maxVideoResWidth': itemObj.maxVideoResWidth,
				'maxVideoResHeight': itemObj.maxVideoResHeight,
				'maxSizeOfFile': itemObj.maxSizeOfFile,
				'nbOfAttachmentsOfFilePerEvent': itemObj.nbOfAttachmentsOfFilePerEvent,
				'allowedExtensionsOfFile': itemObj.allowedExtensionsOfFile.split(","),
				'minNbOfPointsForTrail': itemObj.minNbOfPointsForTrail,
				'maxNbOfDaysForTrailSearch': itemObj.maxNbOfDaysForTrailSearch
		};	
        
        var url_setting = url + 'settings',
            options = {
                makeRequest: {
                    isArray: false
                }
            };

        options.makeRequest.method = 'POST';

        $scope.postSettings = $resource(url_setting ,null,options).makeRequest(newObj);
        $scope.postSettings.$promise.then(
            function(success) {
                console.log(success);
                $scope.main.successHandlerCollback(success);
            },errorCallback); 
    };

  }]);
