'use strict';

describe('Controller: AlocaUtilizatorExistentCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AlocaUtilizatorExistentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AlocaUtilizatorExistentCtrl = $controller('AlocaUtilizatorExistentCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AlocaUtilizatorExistentCtrl.awesomeThings.length).toBe(3);
  });
});
