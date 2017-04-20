'use strict';

/**
 * @ngdoc function
 * @name gnmApp.controller:UtilizatorExistentCtrl
 * @description
 * # UtilizatorExistentCtrl
 * Controller of the gnmApp
 */
angular.module('gnmApp')
  .controller('UtilizatorExistentCtrl',['$scope', '$resource', 'HelpersService', '$location', '$sessionStorage', 'Go', '$window',
        function($scope, $resource, HelpersService, $location, $sessionStorage, Go, $window) {

            var url = HelpersService.domain,
                errorCallback = $scope.main.errorHandlerCollback;

    		$scope.itemObj = {};
            $scope.formDropdownData = {};
            // $scope.session.addLink.title = 'Aloca utilizator existent';

        	var url_accounts = {};
         	var options = {
                makeRequest: {
                    method: 'GET',
                    isArray: true
                }
            };

            // Admin Api
            if ($sessionStorage.user.authority === 'ROLE_ADMIN_GENERAL') {
            	console.log('Admin',$sessionStorage.user.authority);

              	if ($sessionStorage.editableRow.license) {
            		url_accounts = 'accounts/licenses/' + $sessionStorage.editableRow.organisation.id + '/used';
              	} else  {
            		url_accounts = 'accounts/licenses/' + $sessionStorage.editableLicenses.organisation.id + '/used';
              	}
            	

            } else {
            	url_accounts = 'accounts/licenses/used';
            }
            

            $scope.getAccounts = $resource(url + url_accounts, null, options).makeRequest();
            $scope.getAccounts.$promise.then(function(success) {
                $scope.formDropdownData.licente = success;
                console.log(success);
            }, errorCallback);


            $scope.getData = function (licenseId) {
		        var url_data = 'accounts/licenses/' + licenseId;
			    var options = {
	                makeRequest: {
	                    method: 'GET',
	                    isArray: false
	                }
	            };

		        $scope.getDate = $resource(url + url_data, null, options).makeRequest();
		        $scope.getDate.$promise.then(function(success) {
	                $scope.formDropdownData.data = success;
	            	console.log($scope.formDropdownData.data.gender);
	            }, errorCallback);

	            console.log('dasdas');
		        
		    };

            if (($location.search().edit === 'true') && $sessionStorage.editableRow.isUsed === false) {
                $scope.itemObj = $sessionStorage.editableRow;

            } else if (($location.search().edit === 'true') && $sessionStorage.editableLicenses.isUsed === false) {
        		$scope.itemObj = $sessionStorage.editableLicenses;
          	} 
  
   
            $scope.sendPost = function(itemObj) {

 				// $sessionStorage.editableRow = $sessionStorage.editableLicenses;
 				var url_element;

 				if ($sessionStorage.editableRow.isUsed === false) {
            		url_element = url + 'accounts/allocate/' + $scope.formDropdownData.data.accountId + '/' + $sessionStorage.editableRow.licenseId;
 				} else {
            		url_element = url + 'accounts/allocate/' + $scope.formDropdownData.data.accountId + '/' + $sessionStorage.editableLicenses.licenseId;
 				}
                
				options = {
					makeRequest: {
					  isArray: false
					}
				};

              	options.makeRequest.method = 'PUT';

				$scope.postElement = $resource(url_element ,null,options).makeRequest();
				$scope.postElement.$promise.then(
				  function(success) {
				      $scope.main.successHandlerCollback(success);
				      $window.history.back();

				  },errorCallback); 
            };

        }
    ]);