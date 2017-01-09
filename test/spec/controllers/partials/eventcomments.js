'use strict';

describe('Controller: PartialsEventcommentsCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var PartialsEventcommentsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PartialsEventcommentsCtrl = $controller('PartialsEventcommentsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(PartialsEventcommentsCtrl.awesomeThings.length).toBe(3);
  });
});
