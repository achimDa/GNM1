'use strict';

describe('Controller: SpeciiAnimalCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var SpeciiAnimalCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SpeciiAnimalCtrl = $controller('SpeciiAnimalCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SpeciiAnimalCtrl.awesomeThings.length).toBe(3);
  });
});
