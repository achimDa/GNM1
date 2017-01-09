'use strict';

describe('Controller: AlocaUtilizatorCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AlocaUtilizatorCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AlocaUtilizatorCtrl = $controller('AlocaUtilizatorCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AlocaUtilizatorCtrl.awesomeThings.length).toBe(3);
  });
});
