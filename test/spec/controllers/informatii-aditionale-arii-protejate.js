'use strict';

describe('Controller: InformatiiAditionaleAriiProtejateCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var InformatiiAditionaleAriiProtejateCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InformatiiAditionaleAriiProtejateCtrl = $controller('InformatiiAditionaleAriiProtejateCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InformatiiAditionaleAriiProtejateCtrl.awesomeThings.length).toBe(3);
  });
});
