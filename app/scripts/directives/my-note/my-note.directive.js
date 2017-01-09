'use strict';

/**
 * @ngdoc directive
 * @name gnmApp.directive:myNote
 * @description
 * # myNote
 */
angular.module('gnmApp')
.directive('myNote', function () {
    return {
		restrict: 'E',
		templateUrl: 'scripts/directives/my-note/my-note.template.html',
        scope:{
            delete:'&',
            note:'='
        },
        
		link:function (scope, element, attrs) {
			var $el = $(element);

			$el.hide().fadeIn('slow');

			$('.thumbnails').sortable({
			    placeholder:"ui-state-highlight", forcePlaceholderSize:true
			});
		}
    };
});
