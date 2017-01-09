'use strict';

describe('Controller: AddEventCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var AddEventCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AddEventCtrl = $controller('AddEventCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AddEventCtrl.awesomeThings.length).toBe(3);
  });
});
