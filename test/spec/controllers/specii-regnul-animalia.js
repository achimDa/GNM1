'use strict';

describe('Controller: SpeciiRegnulAnimaliaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var SpeciiRegnulAnimaliaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SpeciiRegnulAnimaliaCtrl = $controller('SpeciiRegnulAnimaliaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SpeciiRegnulAnimaliaCtrl.awesomeThings.length).toBe(3);
  });
});
