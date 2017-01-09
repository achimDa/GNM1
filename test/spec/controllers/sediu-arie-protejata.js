'use strict';

describe('Controller: SediuArieProtejataCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var SediuArieProtejataCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SediuArieProtejataCtrl = $controller('SediuArieProtejataCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SediuArieProtejataCtrl.awesomeThings.length).toBe(3);
  });
});
