'use strict';

describe('Controller: RegnulAnimaliaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var RegnulAnimaliaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RegnulAnimaliaCtrl = $controller('RegnulAnimaliaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(RegnulAnimaliaCtrl.awesomeThings.length).toBe(3);
  });
});
