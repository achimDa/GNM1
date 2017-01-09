'use strict';

describe('Controller: AdaugaUtilizatorCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AdaugaUtilizatorCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdaugaUtilizatorCtrl = $controller('AdaugaUtilizatorCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdaugaUtilizatorCtrl.awesomeThings.length).toBe(3);
  });
});
