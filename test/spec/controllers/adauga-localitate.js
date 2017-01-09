'use strict';

describe('Controller: AdaugaLocalitateCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AdaugaLocalitateCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdaugaLocalitateCtrl = $controller('AdaugaLocalitateCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdaugaLocalitateCtrl.awesomeThings.length).toBe(3);
  });
});
