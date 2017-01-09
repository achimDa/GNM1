'use strict';

/**
 * @ngdoc service
 * @name gnmApp.noteService
 * @description
 * # noteService
 * Service in the gnmApp.
 */
angular.module('gnmApp')
.service('noteService',['$resource', 'HelpersService', function ($resource, HelpersService) {

	// var url = HelpersService.domain + '/comments';
	// var url = 'data/commentList.json';

 //    return {
 //        notes: $resource( url,{},{
	// 	            makeRequest: {
	// 	                method: 'GET',
	// 	                isArray: true
	// 	            }
	// 	        }).makeRequest(),
 //        // addNote: function (noteTitle) {
 //        //     console.log('this:', this);
 //        //     var currentIndex = this.deleteNote.data.length + 1;
 //        //     data.push({
 //        //         id:currentIndex,
 //        //         title:noteTitle
 //        //     });
 //        // },
 //        deleteNode: function (key, value, arr) {
            
 //            if (arr.length && key && value) {

 //                angular.forEach(arr, function (index, node) {
 //                    if (node[key] !== value) {
 //                        arr.pop(index);
 //                    }
 //                });

 //            } else {
 //                console.log('noteService.deleteNode() function was called with bad parameters.');
 //            }

 //            return arr;
 //        }
 //    };

  }]);