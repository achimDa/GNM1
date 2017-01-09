'use strict';

describe('Controller: MonitorizareTraseeCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var MonitorizareTraseeCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MonitorizareTraseeCtrl = $controller('MonitorizareTraseeCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MonitorizareTraseeCtrl.awesomeThings.length).toBe(3);
  });
});
