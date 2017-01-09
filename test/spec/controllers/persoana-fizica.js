'use strict';

describe('Controller: PersoanaFizicaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var PersoanaFizicaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PersoanaFizicaCtrl = $controller('PersoanaFizicaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(PersoanaFizicaCtrl.awesomeThings.length).toBe(3);
  });
});
