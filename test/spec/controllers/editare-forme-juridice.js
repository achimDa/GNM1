'use strict';

describe('Controller: EditareFormeJuridiceCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var EditareFormeJuridiceCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EditareFormeJuridiceCtrl = $controller('EditareFormeJuridiceCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EditareFormeJuridiceCtrl.awesomeThings.length).toBe(3);
  });
});
