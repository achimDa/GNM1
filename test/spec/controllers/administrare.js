'use strict';

describe('Controller: AdministrareCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AdministrareCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdministrareCtrl = $controller('AdministrareCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdministrareCtrl.awesomeThings.length).toBe(3);
  });
});
