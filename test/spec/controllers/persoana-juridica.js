'use strict';

describe('Controller: PersoanaJuridicaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var PersoanaJuridicaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PersoanaJuridicaCtrl = $controller('PersoanaJuridicaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(PersoanaJuridicaCtrl.awesomeThings.length).toBe(3);
  });
});
