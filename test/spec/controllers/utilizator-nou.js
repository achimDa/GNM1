'use strict';

describe('Controller: UtilizatorNouCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var UtilizatorNouCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UtilizatorNouCtrl = $controller('UtilizatorNouCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UtilizatorNouCtrl.awesomeThings.length).toBe(3);
  });
});
