'use strict';

describe('Controller: SchimbaParolaCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var SchimbaParolaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SchimbaParolaCtrl = $controller('SchimbaParolaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SchimbaParolaCtrl.awesomeThings.length).toBe(3);
  });
});
