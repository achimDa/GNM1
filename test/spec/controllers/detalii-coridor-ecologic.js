'use strict';

describe('Controller: DetaliiCoridorEcologicCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var DetaliiCoridorEcologicCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DetaliiCoridorEcologicCtrl = $controller('DetaliiCoridorEcologicCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DetaliiCoridorEcologicCtrl.awesomeThings.length).toBe(3);
  });
});
