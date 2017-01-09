'use strict';

describe('Controller: NotificariAutoritatiCtrl', function () {

  // load the controller's module
  beforeEach(module('gnmApp'));

  var NotificariAutoritatiCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    NotificariAutoritatiCtrl = $controller('NotificariAutoritatiCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(NotificariAutoritatiCtrl.awesomeThings.length).toBe(3);
  });
});
