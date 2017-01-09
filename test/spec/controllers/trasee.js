'use strict';

describe('Controller: TraseeCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var TraseeCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TraseeCtrl = $controller('TraseeCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TraseeCtrl.awesomeThings.length).toBe(3);
  });
});
