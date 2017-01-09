'use strict';

/**
 * @ngdoc service
 * @name gnmApp
 * @description
 * # gnmApp
 * Constant in the gnmApp.
 */

angular.module('gnmApp').constant('Notificari', {
	'links':[
		{
			groupLabel : 'Evenimente',
			groupLinks : [

				{	
					link : 'events/protectedarea/noresolution',
					link2 : 'events/county/noresolution',
					label : 'Fara rezolutie',
					title : 'Evenimente fara rezolutie',
					reseco : false, 
					resev : true, 
					cancelE : false,
					cancelC : false,
					stergeE : true,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				},
				{	
					link : 'events/protectedarea/valid',
					link2 : 'events/county/valid',
					label : 'Validate',
					title : 'Evenimente validate',
					reseco : false, 
					resev : false, 
					cancelE : true,
					cancelC : false,
					stergeE : false,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				},
				{	
					link : 'events/protectedarea/invalid',
					link2 : 'events/county/invalid',
					label : 'Invalidate',
					title : 'Evenimente invalidate',
					reseco : false, 
					resev : false, 
					cancelE : true,
					cancelC : false,
					stergeE : false,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				},
				{	
					link : 'events/notification',
					link2 : 'events/notification',
					label : 'Notificari',
					title : 'Evenimente notificate',
					reseco : false, 
					resev : false, 
					cancelE : false,
					cancelC : false,
					stergeE : false,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM' , 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_GENERAL']
					}
				}
			]
		},
		{
			groupLabel : 'Coridoare ecologice',
			groupLinks : [
				{	
					link : 'ecocorridors/resolutions/noresolution',
					link2 : 'ecocorridors/resolutions/noresolution',
					label : 'Fara rezolutie',
					title : 'Coridoare ecologice fara rezolutie',
					reseco : true, 
					resev : false, 
					cancelE : false,
					cancelC : false,
					stergeE : false,
					stergeC : true,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				},
				{	
					link : 'ecocorridors/resolutions/valid',
					link2 : 'ecocorridors/resolutions/valid',
					label : 'Validate',
					title : 'Coridoare ecologice validate',
					reseco : false, 
					resev : false, 
					cancelE : false,
					cancelC : true,
					stergeE : false,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				},
				{	
					link : 'ecocorridors/resolutions/invalid',
					link2 : 'ecocorridors/resolutions/invalid',
					label : 'Invalidate',
					title : 'Coridoare ecologice invalidate',
					reseco : false, 
					resev : false, 
					cancelE : false,
					cancelC : true,
					stergeE : false,
					stergeC : false,
					permissions: {
						access: ['ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL'],
						add: ['ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
					}
				}
			]
		}
	]
});