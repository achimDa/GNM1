'use strict';

describe('Controller: AdaugaElementCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AdaugaElementCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdaugaElementCtrl = $controller('AdaugaElementCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdaugaElementCtrl.awesomeThings.length).toBe(3);
  });
});
