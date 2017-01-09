'use strict';

/**
 * @ngdoc service
 * @name gnmApp
 * @description
 * # gnmApp
 * Constant in the gnmApp.
 */ 

angular.module('gnmApp').constant('Constant', {
	'administrators':[
		{
			name:'Rangers SRL'
		},
		{
			name:'Padurarii iscusiti SRL'
		},
		{
			name:'Rangers SRL'
		},
		{
			name:'Padurarii iscusiti SRL'
		}
	],
	'migrator':[
		{
			name:'Da',
			id:1,
			value:'true'
		},
		{
			name:'Nu',
			id:2,
			value:'false'
		}
	],
	'stare':[
		{
			name:'Asteapta rezolutie',
			value: 'PENDING'
		},
		{
			name:'Validat',
			value: 'VALID'
		},
		{
			name:'Invalidat',
			value: 'INVALID'
		},
		{
			name:'Notificat',
			value: 'NOTIFIED'
		},
		{
			name:'Nu mai este de actualitate',
			value: 'OLD'
		}
	],
	'stare2':[
		{
			name:'Asteapta rezolutie',
			value: 'PENDING'
		},
		{
			name:'Validat',
			value: 'VALID'
		},
		{
			name:'Invalidat',
			value: 'INVALID'
		}
	],
	'stareEveniment':[
		{
			name:'Asteapta rezolutie',
			value: 'PENDING'
		},
		{
			name:'Validat',
			value: 'VALID'
		},
		{
			name:'Invalidat',
			value: 'INVALID'
		}
	]
});