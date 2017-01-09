'use strict';

describe('Controller: RapoarteCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var RapoarteCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RapoarteCtrl = $controller('RapoarteCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(RapoarteCtrl.awesomeThings.length).toBe(3);
  });
});
