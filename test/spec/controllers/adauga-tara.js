'use strict';

describe('Controller: AdaugaTaraCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AdaugaTaraCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdaugaTaraCtrl = $controller('AdaugaTaraCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdaugaTaraCtrl.awesomeThings.length).toBe(3);
  });
});
