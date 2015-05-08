define([
	'jquery',
	'core/BaseView',
	'text!templates/tplHotelNav.html',
	'Session'
], function($, BaseView, tplHotelNav, Session){

	var HotelNavView = BaseView.extend({
	    el: $("#hotel-nav"),

        template: _.template(tplHotelNav),

        events: {
            'click #close-hotel-detail': 'closeHotelDetail',
            'click #logout-hotel': 'logout'
        },

        initialize: function() {
            this.render();
            $("#close-hotel-detail").hide();
            $("#logout-hotel").hide();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        closeHotelDetail: function(){
            Backbone.history.navigate('', {trigger: true});
            $("#close-hotel-detail").hide();
        },

        logout: function(){
            Session.logout(function(response){
                $("#logout-hotel").hide();
                Backbone.history.navigate('login', { trigger : true });
            });
        }
	});

	return HotelNavView;

});