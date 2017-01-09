'use strict';

describe('Controller: EditareCoridorEcologicCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var EditareCoridorEcologicCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EditareCoridorEcologicCtrl = $controller('EditareCoridorEcologicCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EditareCoridorEcologicCtrl.awesomeThings.length).toBe(3);
  });
});
