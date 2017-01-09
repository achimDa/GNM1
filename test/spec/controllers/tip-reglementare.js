'use strict';

describe('Controller: TipReglementareCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var TipReglementareCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TipReglementareCtrl = $controller('TipReglementareCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TipReglementareCtrl.awesomeThings.length).toBe(3);
  });
});
