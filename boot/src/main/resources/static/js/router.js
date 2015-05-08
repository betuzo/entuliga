define([
	'jquery',
	'underscore',
	'backbone',
	'core/BaseRouter',
	'views/HotelAdnView',
	'views/LoginView',
	'views/HotelDetailView',
	'views/HotelNavView'
], function($, _, Backbone, BaseRouter, HotelAdnView, LoginView, HotelDetailView, HotelNavView){
        var Router = BaseRouter.extend({

        routes: {
            '': 'hotel',
            '/': 'hotel',
            'login': 'login',
            'hotel/:id/detail': 'detalle'
        },

        before : function(params, next){
            console.log('before');
            return next();
        },

        after : function(){
            console.log('after');
        },

        changeView : function(view){
            //Close is a method in BaseView
            //that check for childViews and
            //close them before closing the
            //parentView
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
            this.hotel();
            this.hotelNav();
        },

        hotelNav: function(){
            new HotelNavView();
        },

        hotel: function(){
            var view = new HotelAdnView();
            this.changeView(view);
        },

        login: function(){
            var view = new LoginView();
            this.changeView(view);
        },

        detalle: function(id){
            var modelo = app.hoteles.get(id);
            var view = new HotelDetailView({model: modelo});
            this.changeView(view);
        }
	});

	return Router;

});
