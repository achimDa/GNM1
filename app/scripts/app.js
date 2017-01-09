'use strict';

/**
 * @ngdoc overview
 * @name gnmApp
 * @description
 * # gnmApp
 *
 * Main module of the application.
 */
angular
	.module('gnmApp', [
		'ngAnimate',
		'ngAria',
		'ngCookies',
		'ngMessages',
		'ngResource',
		'ngRoute',
		'ngSanitize',
		'ngStorage',
		'ngTouch',
		'ui.bootstrap',
		'ngFileUpload',
		'ui-iconpicker',
		'validation.match',
		'datePicker',
		'ngCsv',
		'ui-leaflet',
		// 'angularUtils.directives.dirPagination',
		'mgcrea.ngStrap',
		'objectTable'
	])
	.config(function($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl: 'views/login.html',
				controller: 'LoginCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL', 'ROLE_ADMIN_LOCAL']
			})
			.when('/login', {
				templateUrl: 'views/login.html',
				controller: 'LoginCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL', 'ROLE_ADMIN_LOCAL']
			})
			.when('/dashboard', {
				templateUrl: 'views/dashboard.html',
				controller: 'DashboardCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL', 'ROLE_ADMIN_LOCAL']
			})
			.when('/events', {
				templateUrl: 'views/events.html',
				controller: 'EventsCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL']
			})
			.when('/add-event', {
				templateUrl: 'views/add-event.html',
				controller: 'AddEventCtrl',
				title: 'Adauga eveniment',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL']
			})
			.when('/administrare', {
				templateUrl: 'views/administrare.html',
				controller: 'AdministrareCtrl',
				title: 'Administrare',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/adauga-tara', {
				templateUrl: 'views/adauga-tara.html',
				controller: 'AdaugaTaraCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/adauga-judet', {
				templateUrl: 'views/adauga-judet.html',
				controller: 'AdaugaJudetCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/adauga-localitate', {
				templateUrl: 'views/adauga-localitate.html',
				controller: 'AdaugaLocalitateCtrl',
				controllerAs: 'adaugaLocalitate',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/partials/eventComments', {
				templateUrl: 'views/partials/eventcomments.html',
				controller: 'PartialsEventcommentsCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL']
			})
			.when('/history-events', {
				templateUrl: 'views/history-events.html',
				controller: 'HistoryEventsCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_AUTORITATE', 'ROLE_ADMIN_GENERAL', 'ROLE_ADMIN_LOCAL']
			})
			.when('/rezolutii-notificari', {
				templateUrl: 'views/rezolutii-notificari.html',
				controller: 'RezolutiiNotificariCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_ADMIN_GENERAL']
			})
			.when('/coridoare-ecologice', {
				templateUrl: 'views/coridoare-ecologice.html',
				controller: 'CoridoareEcologiceCtrl',
				title: 'Coridoare ecologice',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN','ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_AUTORITATE','ROLE_ADMIN_GENERAL']
			})
			.when('/editare-coridor-ecologic', {
				templateUrl: 'views/editare-coridor-ecologic.html',
				controller: 'EditareCoridorEcologicCtrl',
				title: 'Editare coridor ecologic',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_ADMIN_GENERAL']
			})
			.when('/tip-coridor-ecologic', {
				templateUrl: 'views/tip-coridor-ecologic.html',
				controller: 'TipCoridorEcologicCtrl',
				title: 'Tip coridor ecologic',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/monitorizare-trasee', {
				templateUrl: 'views/monitorizare-trasee.html',
				controller: 'MonitorizareTraseeCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_ADMIN_GENERAL']
			})
			.when('/regnul-animalia', {
				templateUrl: 'views/regnul-animalia.html',
				controller: 'RegnulAnimaliaCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM','ROLE_ADMIN_ARIE_PROTEJATA','ROLE_ADMIN_GENERAL']
			})
			.when('/jurnal-sincronizari', {
				templateUrl: 'views/jurnal-sincronizari.html',
				controller: 'JurnalSincronizariCtrl',
				acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_ADMIN_GENERAL', 'ROLE_ADMIN_LOCAL']
			})
			.when('/arie-protejata', {
				templateUrl: 'views/arie-protejata.html',
				controller: 'ArieProtejataCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/editare-harta-arie-protejata', {
				templateUrl: 'views/editare-harta-arie-protejata.html',
				controller: 'EditareHartaArieProtejataCtrl',
				controllerAs: 'editareHartaArieProtejata',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/comisariat', {
				templateUrl: 'views/comisariat.html',
				controller: 'ComisariatCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/categorie-evenimente', {
				templateUrl: 'views/categorie-evenimente.html',
				controller: 'CategorieEvenimenteCtrl',
				title: 'Adauga categorie eveniment',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/adauga-element', {
				templateUrl: 'views/adauga-element.html',
				controller: 'AdaugaElementCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/legislatie', {
				templateUrl: 'views/legislatie.html',
				controller: 'LegislatieCtrl',
				title: 'Legislatie',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/reglementare', {
				templateUrl: 'views/reglementare.html',
				controller: 'ReglementareCtrl',
				title: 'Reglementare',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/metodologie', {
				templateUrl: 'views/metodologie.html',
				controller: 'MetodologieCtrl',
				title: 'Metodologie',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/animal-dimensiuni', {
				templateUrl: 'views/animal-dimensiuni.html',
				controller: 'AnimalDimensiuniCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/animal-categorii', {
				templateUrl: 'views/animal-categorii.html',
				controller: 'AnimalCategoriiCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/persoana-juridica', {
				templateUrl: 'views/persoana-juridica.html',
				controller: 'PersoanaJuridicaCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/persoana-fizica', {
				templateUrl: 'views/persoana-fizica.html',
				controller: 'PersoanaFizicaCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/utilizatori', {
				templateUrl: 'views/utilizatori.html',
				controller: 'UtilizatoriCtrl',
				acceptedRoles: ['ROLE_ADMIN_LOCAL']
			})
			.when('/utilizator-nou', {
				templateUrl: 'views/utilizator-nou.html',
				controller: 'UtilizatorNouCtrl',
				acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/adauga-utilizator', {
				templateUrl: 'views/adauga-utilizator.html',
				controller: 'AdaugaUtilizatorCtrl',
				acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/aloca-utilizator-existent', {
				templateUrl: 'views/aloca-utilizator-existent.html',
				controller: 'AlocaUtilizatorExistentCtrl',
				acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/editare-forme-juridice', {
			  templateUrl: 'views/editare-forme-juridice.html',
			  controller: 'EditareFormeJuridiceCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/unitati-emitente-certificate-fiscale', {
			  templateUrl: 'views/unitati-emitente-certificate-fiscale.html',
			  controller: 'UnitatiEmitenteCertificateFiscaleCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/categorii-regnul-animalia', {
			  templateUrl: 'views/categorii-regnul-animalia.html',
			  controller: 'CategoriiRegnulAnimaliaCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/dimensiuni-regnul-animalia', {
			  templateUrl: 'views/dimensiuni-regnul-animalia.html',
			  controller: 'DimensiuniRegnulAnimaliaCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/specii-regnul-animalia', {
			  templateUrl: 'views/specii-regnul-animalia.html',
			  controller: 'SpeciiRegnulAnimaliaCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/tipuri-arii-protejate', {
			  templateUrl: 'views/tipuri-arii-protejate.html',
			  controller: 'TipuriAriiProtejateCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/informatii-aditionale-arii-protejate', {
			  templateUrl: 'views/informatii-aditionale-arii-protejate.html',
			  controller: 'InformatiiAditionaleAriiProtejateCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/restrictii-arii-protejate', {
			  templateUrl: 'views/restrictii-arii-protejate.html',
			  controller: 'RestrictiiAriiProtejateCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/utilizator-existent', {
			  templateUrl: 'views/utilizator-existent.html',
			  controller: 'UtilizatorExistentCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/coduri-caen', {
			  templateUrl: 'views/coduri-caen.html',
			  controller: 'CoduriCaenCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/autoritate', {
			  templateUrl: 'views/autoritate.html',
			  controller: 'AutoritateCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/sediu-arie-protejata', {
			  templateUrl: 'views/sediu-arie-protejata.html',
			  controller: 'SediuArieProtejataCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL']
			})
			.when('/notificari-autoritati', {
			  templateUrl: 'views/notificari-autoritati.html',
			  controller: 'NotificariAutoritatiCtrl',
			  acceptedRoles: ['ROLE_ADMIN_LOCAL', 'ROLE_ADMIN_GENERAL', 'ROLE_COMISAR_JUDETEAN']
			})
			.when('/setari-generale', {
			  templateUrl: 'views/setari-generale.html',
			  controller: 'SetariGeneraleCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/tip-legislatie', {
			  templateUrl: 'views/tip-legislatie.html',
			  controller: 'TipLegislatieCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/tip-reglementare', {
			  templateUrl: 'views/tip-reglementare.html',
			  controller: 'TipReglementareCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/schimba-parola', {
			  templateUrl: 'views/schimba-parola.html',
			  controller: 'SchimbaParolaCtrl',
			  acceptedRoles: ['ROLE_COMISAR_GNM', 'ROLE_COMISAR_JUDETEAN', 'ROLE_ADMIN_ARIE_PROTEJATA', 'ROLE_RANGER', 'ROLE_ADMIN_GENERAL', 'ROLE_ADMIN_LOCAL']
			})
			.when('/harta-tara', {
			  templateUrl: 'views/harta-tara.html',
			  controller: 'HartaTaraCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/harta-judet', {
			  templateUrl: 'views/harta-judet.html',
			  controller: 'HartaJudetCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/setari', {
			  templateUrl: 'views/setari.html',
			  controller: 'SetariCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/rapoarte', {
			  templateUrl: 'views/rapoarte.html',
			  controller: 'RapoarteCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/indicatori-performanta-categorii-evenimente', {
			  templateUrl: 'views/indicatori-performanta-categorii-evenimente.html',
			  controller: 'IndicatoriPerformantaCategoriiEvenimenteCtrl',
	  		  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/detalii-eveniment', {
			  templateUrl: 'views/detalii-eveniment.html',
			  controller: 'DetaliiEvenimentCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/detalii-coridor-ecologic', {
			  templateUrl: 'views/detalii-coridor-ecologic.html',
			  controller: 'DetaliiCoridorEcologicCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/evenimente-dupa-legislatie', {
			  templateUrl: 'views/evenimente-dupa-legislatie.html',
			  controller: 'EvenimenteDupaLegislatieCtrl',
			  acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.when('/trasee', {
				templateUrl: 'views/trasee.html',
				controller: 'TraseeCtrl',
				acceptedRoles: ['ROLE_ADMIN_GENERAL']
			})
			.otherwise({
				redirectTo: '/'
			});
	})

.run(['$rootScope', '$location', '$sessionStorage', '$route', '$routeParams', '$localStorage',
	function($rootScope, $location, $sessionStorage, $route, $routeParams, $localStorage) {

		$rootScope.$on('$routeChangeStart', function(scope, current, pre) {
			/* Set wich routes are open to public in an array */
			var routesOpenToUser = [],
				user = {};

			if ($sessionStorage.user) {
				user = $sessionStorage.user;
			}

			angular.forEach($route.routes, function(route, path) {
				//Create a list of routes accesible to the user role aka 'authority'
				if (route.acceptedRoles && (route.acceptedRoles.indexOf(user.authority) !== -1)) {
					routesOpenToUser.push(path);
				}
			});

			/* Check if the route on wich the user whants to go is open to public or not*/
			var closedToUser = (routesOpenToUser.indexOf($location.path()) === -1);

			if (closedToUser || (!$sessionStorage.isLoggedIn && !$localStorage.isLoggedIn)) {
				/* If the user is not logged in it will be redireced to login route */
				$location.path('/login');
			}
		});
	}
]);
