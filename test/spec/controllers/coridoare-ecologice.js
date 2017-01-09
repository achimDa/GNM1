'use strict';

describe('Controller: CoridoareEcologiceCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var CoridoareEcologiceCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CoridoareEcologiceCtrl = $controller('CoridoareEcologiceCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CoridoareEcologiceCtrl.awesomeThings.length).toBe(3);
  });
});
