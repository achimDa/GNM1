'use strict';

describe('Controller: TipCoridorEcologicCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var TipCoridorEcologicCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TipCoridorEcologicCtrl = $controller('TipCoridorEcologicCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TipCoridorEcologicCtrl.awesomeThings.length).toBe(3);
  });
});
