'use strict';

describe('Controller: CategoriiRegnulAnimaliaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var CategoriiRegnulAnimaliaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CategoriiRegnulAnimaliaCtrl = $controller('CategoriiRegnulAnimaliaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CategoriiRegnulAnimaliaCtrl.awesomeThings.length).toBe(3);
  });
});
