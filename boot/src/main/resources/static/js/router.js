define([
	'jquery',
	'underscore',
	'backbone',
	'core/BaseRouter',
	'views/HotelAdnView',
	'views/LoginView',
	'views/public/MainView',
	'views/public/MainNavView',
	'views/private/MainAdminView',
	'views/private/MainAdminNavView',
	'views/private/LigaAdminView'
], function($, _, Backbone, BaseRouter, HotelAdnView, LoginView,
            MainView, MainNavView, MainAdminView, MainAdminNavView,
            LigaAdminView){
        var Router = BaseRouter.extend({

        routes: {
            '': 'main',
            '/': 'main',
            'login': 'login',
            'admin': 'admin',
            'admin/perfil': 'adminPerfil',
            'admin/ligas': 'adminLigas',
            'admin/torneos': 'adminTorneos',
            'admin/equipos': 'adminEquipos',
            'admin/jugadores': 'adminJugadores'
        },

        before : function(params, next){
            console.log('before');
            return next();
        },

        after : function(){
            console.log('after');
        },

        changeView : function(view){
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

        mainNav: function(){
            new MainNavView();
        },

        main: function(){
            var view = new MainView();
            this.changeView(view);
        },

        login: function(){
            var view = new LoginView();
            this.changeView(view);
        },

        mainAdminNav: function(){
            new MainAdminNavView();
        },

        admin: function(){
            var view = new MainAdminView();
            this.changeView(view);
            this.mainAdminNav();
        },

        adminLigas: function(){
            var view = new LigaAdminView();
            this.changeView(view);
        }
	});

	return Router;

});
