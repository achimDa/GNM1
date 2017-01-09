'use strict';

describe('Controller: AdaugaJudetCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AdaugaJudetCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdaugaJudetCtrl = $controller('AdaugaJudetCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdaugaJudetCtrl.awesomeThings.length).toBe(3);
  });
});
