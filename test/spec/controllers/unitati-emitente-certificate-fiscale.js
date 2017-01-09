'use strict';

describe('Controller: UnitatiEmitenteCertificateFiscaleCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var UnitatiEmitenteCertificateFiscaleCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UnitatiEmitenteCertificateFiscaleCtrl = $controller('UnitatiEmitenteCertificateFiscaleCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UnitatiEmitenteCertificateFiscaleCtrl.awesomeThings.length).toBe(3);
  });
});
