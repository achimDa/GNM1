'use strict';

describe('Controller: CoduriCaenCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var CoduriCaenCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CoduriCaenCtrl = $controller('CoduriCaenCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CoduriCaenCtrl.awesomeThings.length).toBe(3);
  });
});
