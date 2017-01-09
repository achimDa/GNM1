'use strict';

describe('Controller: HartaTaraCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var HartaTaraCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    HartaTaraCtrl = $controller('HartaTaraCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(HartaTaraCtrl.awesomeThings.length).toBe(3);
  });
});
