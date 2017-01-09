'use strict';

describe('Controller: ComisariatCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var ComisariatCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ComisariatCtrl = $controller('ComisariatCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ComisariatCtrl.awesomeThings.length).toBe(3);
  });
});
