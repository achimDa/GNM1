'use strict';

describe('Controller: DetaliiEvenimentCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var DetaliiEvenimentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DetaliiEvenimentCtrl = $controller('DetaliiEvenimentCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DetaliiEvenimentCtrl.awesomeThings.length).toBe(3);
  });
});
