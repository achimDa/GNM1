'use strict';

describe('Controller: JurnalSincronizariCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var JurnalSincronizariCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    JurnalSincronizariCtrl = $controller('JurnalSincronizariCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(JurnalSincronizariCtrl.awesomeThings.length).toBe(3);
  });
});
