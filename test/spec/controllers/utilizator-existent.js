'use strict';

describe('Controller: UtilizatorExistentCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var UtilizatorExistentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UtilizatorExistentCtrl = $controller('UtilizatorExistentCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UtilizatorExistentCtrl.awesomeThings.length).toBe(3);
  });
});
