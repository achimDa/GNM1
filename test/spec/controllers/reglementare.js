'use strict';

describe('Controller: ReglementareCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var ReglementareCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ReglementareCtrl = $controller('ReglementareCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ReglementareCtrl.awesomeThings.length).toBe(3);
  });
});
