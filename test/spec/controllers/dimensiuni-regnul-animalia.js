'use strict';

describe('Controller: DimensiuniRegnulAnimaliaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var DimensiuniRegnulAnimaliaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DimensiuniRegnulAnimaliaCtrl = $controller('DimensiuniRegnulAnimaliaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DimensiuniRegnulAnimaliaCtrl.awesomeThings.length).toBe(3);
  });
});
