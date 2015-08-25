define([
	'jquery',
	'underscore',
	'backbone',
	'core/BaseRouter',
	'views/LoginView',
	'views/public/MainView',
	'views/public/MainNavView',
	'views/private/MainAdminView',
	'views/private/MainAdminNavView',
	'views/private/LigaAdminView',
	'views/private/TorneoAdminView',
	'views/private/EquipoAdminView',
	'views/private/JugadorAdminView',
	'views/private/ArbitroAdminView',
	'views/private/CanchaAdminView'
], function($, _, Backbone, BaseRouter, LoginView,
            MainView, MainNavView, MainAdminView, MainAdminNavView,
            LigaAdminView, TorneoAdminView, EquipoAdminView,
            JugadorAdminView, ArbitroAdminView){
        var Router = BaseRouter.extend({

        routes: {
            '':                     'main',
            '/':                    'main',
            'login':                'login',
            'admin':                'admin',
            'admin/perfil':         'adminPerfil',
            'admin/ligas':          'adminLigas',
            'admin/torneos':        'adminTorneos',
            'admin/torneos/:liga':  'adminTorneos',
            'admin/equipos':        'adminEquipos',
            'admin/jugadores':      'adminJugadores',
            'admin/arbitros':       'adminArbitros',
            'admin/canchas':        'adminCanchas'
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
            this.main();
            this.mainNav();
        },

        mainNav: function() {
            new MainNavView();
        },

        main: function() {
            var view = new MainView();
            this.changeView(view);
        },

        login: function() {
            var view = new LoginView();
            this.changeView(view);
        },

        mainAdminNav: function() {
            new MainAdminNavView();
        },

        admin: function() {
            var view = new MainAdminView();
            this.changeView(view);
            this.mainAdminNav();
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
        }
	});

	return Router;

});
