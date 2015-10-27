define([
	'jquery',
	'underscore',
	'backbone',
	'core/BaseRouter',
	'views/LoginView',
	'views/private/MainAdminView',
	'views/private/MainAdminNavView',
	'views/private/LigaAdminView',
	'views/private/TorneoAdminView',
	'views/private/EquipoAdminView',
	'views/private/JugadorAdminView',
	'views/private/ArbitroAdminView',
	'views/private/CanchaAdminView',
	'views/private/partido/PartidoAdminView',
    'views/public/MainView',
    'views/public/MainNavView',
	'views/public/torneo/TorneoLandingView'
], function($, _, Backbone, BaseRouter, LoginView,
            MainAdminView, MainAdminNavView, LigaAdminView,
            TorneoAdminView, EquipoAdminView, JugadorAdminView,
            ArbitroAdminView, CanchaAdminView, PartidoAdminView,
            MainView, MainNavView, TorneoLandingView){
        var Router = BaseRouter.extend({

        routes: {
            '':                             'main',
            '/':                            'main',
            'login':                        'login',
            'admin':                        'admin',
            'admin/perfil':                 'adminPerfil',
            'admin/ligas':                  'adminLigas',
            'admin/torneos':                'adminTorneos',
            'admin/torneos/:liga':          'adminTorneos',
            'admin/equipos':                'adminEquipos',
            'admin/jugadores':              'adminJugadores',
            'admin/arbitros':               'adminArbitros',
            'admin/canchas':                'adminCanchas',
            'admin/partido/:partido':       'adminPartido',

            'torneo/:clave':                'publicTorneo'
        },

        before : function(params, next) {
            console.log('before');
            return next();
        },

        after : function() {
            console.log('after');
        },

        changeView : function(view) {
            function setView(view){
                if(this.currentView){
                    this.currentView.close();
                }
                this.currentView = view;
                $('#container-body').html(view.render().$el);
            }

            setView(view);
        },

        initialize: function () {
        },

        main: function() {
            var view = new MainView();
            this.changeView(view);
            new MainNavView();
        },

        login: function() {
            var view = new LoginView();
            this.changeView(view);
        },

        admin: function() {
            var view = new MainAdminView();
            this.changeView(view);
            new MainAdminNavView();
        },

        adminLigas: function() {
            var view = new LigaAdminView();
            this.changeView(view);
        },

        adminTorneos: function() {
            var view = new TorneoAdminView();
            this.changeView(view);
        },

        adminEquipos: function() {
            var view = new EquipoAdminView();
            this.changeView(view);
        },

        adminJugadores: function() {
            var view = new JugadorAdminView();
            this.changeView(view);
        },

        adminArbitros: function() {
            var view = new ArbitroAdminView();
            this.changeView(view);
        },

        adminCanchas: function() {
            var view = new CanchaAdminView();
            this.changeView(view);
        },

        adminPartido: function(partido) {
            var view = new PartidoAdminView();
            view.setIdPartido(partido);
            this.changeView(view);
        },

        publicTorneo: function(clave) {
            console.log('Selection: ' + clave);
            var view = new TorneoLandingView({clave : clave});
            this.changeView(view);
        }
	});

	return Router;

});
