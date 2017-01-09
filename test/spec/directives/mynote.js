'use strict';

describe('Directive: myNote', function () {

  // load the directive's module
  beforeEach(module('gnmApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<my-note></my-note>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the myNote directive');
  }));
});
