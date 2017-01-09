'use strict';

describe('Controller: RestrictiiAriiProtejateCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var RestrictiiAriiProtejateCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RestrictiiAriiProtejateCtrl = $controller('RestrictiiAriiProtejateCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(RestrictiiAriiProtejateCtrl.awesomeThings.length).toBe(3);
  });
});
