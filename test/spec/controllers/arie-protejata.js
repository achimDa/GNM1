'use strict';

describe('Controller: ArieProtejataCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var ArieProtejataCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ArieProtejataCtrl = $controller('ArieProtejataCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ArieProtejataCtrl.awesomeThings.length).toBe(3);
  });
});
