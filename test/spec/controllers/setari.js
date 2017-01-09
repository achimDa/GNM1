'use strict';

describe('Controller: SetariCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var SetariCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SetariCtrl = $controller('SetariCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SetariCtrl.awesomeThings.length).toBe(3);
  });
});
