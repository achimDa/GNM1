'use strict';

describe('Controller: SetariGeneraleCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var SetariGeneraleCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SetariGeneraleCtrl = $controller('SetariGeneraleCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SetariGeneraleCtrl.awesomeThings.length).toBe(3);
  });
});
