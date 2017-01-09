'use strict';

/**
 * @ngdoc directive
 * @name gnmApp.directive:myNote
 * @description
 * # myNote
 */
angular.module('gnmApp')
.directive('colorPicker', function() {
    return ({
    controller:'CategorieEvenimenteCtrl',
    scope: {
      color: '=colorPicker'
    },
    link: function(scope, element, attrs) {
      element.colorPicker({
        // initialize the color to the color on the scope
        pickerDefault: scope.color,
        // update the scope whenever we pick a new color
        onColorChange: function(id, newValue) {
          scope.$apply(function() {
            scope.color = newValue;
          });
        }
      });

      // update the color picker whenever the value on the scope changes
      scope.$watch('color', function(value) {
        element.val(value);
        element.change();                
      });
    }
  });
});
