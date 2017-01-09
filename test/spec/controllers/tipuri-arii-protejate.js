'use strict';

describe('Controller: TipuriAriiProtejateCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var TipuriAriiProtejateCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TipuriAriiProtejateCtrl = $controller('TipuriAriiProtejateCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TipuriAriiProtejateCtrl.awesomeThings.length).toBe(3);
  });
});
