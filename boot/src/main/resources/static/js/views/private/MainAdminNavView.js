define([
	'jquery',
	'core/BaseView',
	'text!templates/private/tplMainAdminNav.html',
	'Session'
], function($, BaseView, tplMainAdminNav, Session){

	var MainAdminNavView = BaseView.extend({
	    el: $("#hotel-nav"),

        template: _.template(tplMainAdminNav),

        events: {
            'click #logout': 'logout',
            'click #home': 'homeAdmin'
        },

        initialize: function() {
            this.render();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        logout: function(){
            Session.logout(function(response){
                Backbone.history.navigate('', { trigger : true });
            });
        },

        homeAdmin: function(){
            Backbone.history.navigate('admin', { trigger : true });
        }
	});

	return MainAdminNavView;

});