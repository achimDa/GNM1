'use strict';

describe('Controller: IndicatoriPerformantaCategoriiEvenimenteCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var IndicatoriPerformantaCategoriiEvenimenteCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    IndicatoriPerformantaCategoriiEvenimenteCtrl = $controller('IndicatoriPerformantaCategoriiEvenimenteCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(IndicatoriPerformantaCategoriiEvenimenteCtrl.awesomeThings.length).toBe(3);
  });
});
