'use strict';

describe('Controller: AutoritateCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AutoritateCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AutoritateCtrl = $controller('AutoritateCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AutoritateCtrl.awesomeThings.length).toBe(3);
  });
});
