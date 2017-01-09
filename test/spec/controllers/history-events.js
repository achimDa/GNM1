'use strict';

describe('Controller: HistoryEventsCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var HistoryEventsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    HistoryEventsCtrl = $controller('HistoryEventsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(HistoryEventsCtrl.awesomeThings.length).toBe(3);
  });
});
