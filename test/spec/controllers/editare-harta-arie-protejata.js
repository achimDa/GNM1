'use strict';

describe('Controller: EditareHartaArieProtejataCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var EditareHartaArieProtejataCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EditareHartaArieProtejataCtrl = $controller('EditareHartaArieProtejataCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EditareHartaArieProtejataCtrl.awesomeThings.length).toBe(3);
  });
});