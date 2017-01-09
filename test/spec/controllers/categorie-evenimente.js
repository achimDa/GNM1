'use strict';

describe('Controller: CategorieEvenimenteCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var CategorieEvenimenteCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CategorieEvenimenteCtrl = $controller('CategorieEvenimenteCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CategorieEvenimenteCtrl.awesomeThings.length).toBe(3);
  });
});
