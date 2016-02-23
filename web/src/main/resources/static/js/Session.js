define([
	'jquery',
	'backbone',
	'views/private/util/ModalGenericView'
], function($, Backbone, ModalGenericView){

	var SessionModel = Backbone.Model.extend({
	    url : 'session/login',

	    initialize: function(){
	    	this.set('authenticated', false);
	    },

		login : function(callback, user, pass){
			var that = callback;
			var Session = this;

			this.save({
						username: user,
						password: pass}, {
				wait:true,
				success:function(model, response) {
					Session.set('authenticated', true);
					Session.set('username', user);
					$.ajaxSetup({
						headers: {
							"X-Auth-Token": model.get('token')
						}
					});
					console.log('Successfully saved!');
					that();
				},
				error: function(model, error) {
					Session.set('authenticated', false);
					Session.set('username', '');
					new ModalGenericView({
						message: 'Usuario y/o contraseña incorrecta'
					});
				}
			});
		},

		logout : function(callback){
			var thisSession = this;
			var that = callback;
			var Session = new SessionModel();

			Session.save({ username: this.get('username'),
						   logout: 'logout'}, {
				wait:true,
				success:function(model, response) {
					thisSession.clear();
					$.ajaxSetup({
						headers: {
							"X-Auth-Token": ''
						}
					});
					console.log('Successfully saved!');
					that();
				},
				error: function(model, error) {
					console.log(model.toJSON());
					console.log('error.responseText');
				}
			});
		}
	});

	return new SessionModel();
});