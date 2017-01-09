'use strict';

describe('Controller: HartaJudetCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var HartaJudetCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    HartaJudetCtrl = $controller('HartaJudetCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(HartaJudetCtrl.awesomeThings.length).toBe(3);
  });
});
