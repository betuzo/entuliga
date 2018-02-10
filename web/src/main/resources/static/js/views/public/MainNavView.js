define([
	'jquery',
	'backbone',
	'marionette',
	'core/BaseView',
	'text!templates/public/tplMainNav.html',
	'Session'
], function($, Backbone, Mn, BaseView, tplMainNav, Session) {

	var MainNavView = Mn.View.extend({
		// el: $("#header-general"),

		template: _.template(tplMainNav),

		events: {
			'click #signin': 'signin',
			'click #signup': 'signup',
			'click #home': 'home'
		},

		initialize: function() {},

		onBeforeRender: function() {},

		onRender: function() {},

		signin: function() {
			Backbone.history.navigate('login', { trigger: true });
		},

		signup: function() {
			Backbone.history.navigate('signup', { trigger: true });
		},

		home: function() {
			Backbone.history.navigate('', { trigger: true });
		}
	});

	return MainNavView;

});
