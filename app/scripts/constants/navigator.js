'use strict';

/**
 * @ngdoc service
 * @name gnmApp
 * @description
 * # gnmApp
 * Constant in the gnmApp.
 */



angular.module('gnmApp').constant('Navigator', {
	'links':[
		{
			groupLabel : 'Comune',
			groupLinks : [
				{	
					link : 'countries',
					url : '#/adauga-tara',
					label : 'Tari',
					title : 'Tari',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : true,
					hartajudet : true,
					reset : false
				},
				
				{	
					link : 'counties',
					url : '#/adauga-judet',
					label : 'Judete',
					title : 'Judete',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'cities',
					url : '#/adauga-localitate',
					label : 'Localitati',
					title : 'Localitati',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'legalentitytypes',
					url : '#/editare-forme-juridice',
					label : 'Forme juridice',
					title : 'Forme juridice',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'issuingunits',
					url : '#/unitati-emitente-certificate-fiscale',
					label : 'Unitati emitente certificate',
					title : 'Unitati emitente certificate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'protectedareas/maps',
					url : '#/editare-harta-arie-protejata',
					label : 'Harti arii protejate',
					title : 'Harti arii protejate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'legislations',
					url : '#/legislatie',
					label : 'Legislatie',
					title : 'Legislatie',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'legislationtypes',
					url : '#/tip-legislatie',
					label : 'Tip legislatie',
					title : 'Tip legislatie',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'regulations',
					url : '#/reglementare',
					label : 'Reglementari',
					title : 'Reglementari',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'regulationtypes',
					url : '#/tip-reglementare',
					label : 'Tip reglementare',
					title : 'Tip reglementare',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'methodologies',
					url : '#/metodologie',
					label : 'Metodologii',
					title : 'Metodologii',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'caencodes',
					url : '#/coduri-caen',
					label : 'Coduri CAEN',
					title : 'Coduri CAEN',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'animalcategories',
					url : '#/categorii-regnul-animalia',
					label : 'Categorii regnul Animalia',
					title : 'Categorii regnul Animalia',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'animalsizes',
					url : '#/dimensiuni-regnul-animalia',
					label : 'Dimensiuni regnul Animalia',
					title : 'Dimensiuni regnul Animalia',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'animalspecies',
					url : '#/specii-regnul-animalia',
					label : 'Specii regnul Animalia',
					title : 'Specii regnul Animalia',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				}
			]
		},
		{
			groupLabel : 'Arii Protejate',
			groupLinks : [
				
				{
					link : 'protectedareatypes',
					url : '#/tipuri-arii-protejate',
					label : 'Tipuri arii protejate',
					title : 'Tipuri arii protejate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{
					link : 'additionalinfos',
					url : '#/informatii-aditionale-arii-protejate',
					label : 'Informatii aditionale arii protejate',
					title : 'Informatii aditionale arii protejate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{
					link : 'restrictions',
					url : '#/restrictii-arii-protejate',
					label : 'Restrictii arii protejate',
					title : 'Restrictii arii protejate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{
					link : 'protectedareas',
					label : 'Arii protejate',
					url : '#/arie-protejata',
					title : 'Arii protejate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						edit: [],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{
					link : 'eventcategories',
					label : 'Categorii evenimente',
					url : '#/categorie-evenimente',
					title : 'Categorii evenimente',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				
				{	
					link : 'animals',
					label : 'Regnul Animalia',
					url : '#/adauga-element',
					title : 'Regnul Animalia',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL', 'ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA'],
						add: ['ROLE_ADMIN_GENERAL', 'ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA'],
						activate: ['ROLE_ADMIN_GENERAL', 'ROLE_COMISAR_GNM', 'ROLE_ADMIN_ARIE_PROTEJATA']
					},
					adauga : true,
					modifica : false,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				}
			]
		},
		{
			groupLabel : 'Coridoare ecologice',
			groupLinks : [
				{
					link : 'ecocorridortypes',
					label : 'Tipuri coridoare ecologice',
					title : 'Tipuri coridoare ecologice',
					url : '#/tip-coridor-ecologic',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						edit: []
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				}
			]
		},
		{
			groupLabel : 'Organizare',
			groupLinks : [
				{
					link : 'organisations/comisariats',
					label : 'Comisariate',
					url : '#/comisariat',
					title : 'Comisariate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				{
					link : 'organisations/administrators',
					label : 'Administratori arii protejate',
					url : '#/persoana-fizica',
					url2 : '#/persoana-juridica',
					title : 'Administratori arii protejate',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : false,
					alocafizica : true,
					alocajuridica : true,
					modifica : false,
					modificaPF : true,
					modificaPJ : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				},
				{
					link : 'organisations/authorities',
					label : 'Autoritati',
					url : '#/autoritate',
					title : 'Autoritati',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : true,
					modifica : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					hartatara : false,
					hartajudet : false,
					reset : false
				}
			]
		},
		{
			groupLabel : 'Sistem',
			groupLinks : [
				{
					link : 'accounts/licenses',
					url : '#/utilizator-nou',
					label : 'Utilizatori',
					title : 'Utilizatori',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL'],
						activate: ['ROLE_ADMIN_GENERAL']
					},
					adauga : false,
					modifica : false,
					modificaUser : true,
					sterge : false,
					activeaza : false,
					dezactiveaza : false,
					activeazaUser : true,
					dezactiveazaUser : true,
					existent : true,
					nou : true,
					hartatara : false,
					hartajudet : false,
					reset : true,
					headers : {
						denumire : 'denumire',
						indicativ : 'indicativ'
					}
				},
				{
					link : 'publicaccounts',
					label : 'Utilizatori publici',
					title : 'Utilizatori publici',
					permissions: {
						access: ['ROLE_ADMIN_GENERAL'],
						add: ['ROLE_ADMIN_GENERAL']
					},
					adauga : false,
					modifica : false,
					sterge : false,
					activeaza : true,
					dezactiveaza : true,
					hartatara : false,
					hartajudet : false,
					reset : false
				}
				// {
				// 	link : '#/setari-generale',
				// 	label : 'Setari generale',
				// 	url : '#/setari-generale',
				// 	title : 'Setari generale',
				// 	permissions: {
				// 		access: ['ROLE_ADMIN_GENERAL'],
				// 		add: ['ROLE_ADMIN_GENERAL']
				// 	},
				// 	adauga : true,
				// 	modifica : true,
				// 	sterge : false,
				// 	activeaza : false,
				// 	dezactiveaza : false,
				// 	reset : false
				// }
			]
		}
	]
});