'use strict';

describe('Controller: MetodologieCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var MetodologieCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MetodologieCtrl = $controller('MetodologieCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MetodologieCtrl.awesomeThings.length).toBe(3);
  });
});
