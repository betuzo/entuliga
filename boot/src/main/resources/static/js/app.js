
define([
	'jquery',
	'backbone',
	'router',
	'Session'
], function($, Backbone, Router, Session){

	var ApplicationModel = Backbone.Model.extend({

	    start : function(){
	    	Backbone.history.start();
            var router = new Router();

			$.ajaxSetup({
				statusCode: {
					401: function(){
						// Redirec the to the login page.
						Backbone.history.navigate('login', { trigger : true });

					},
					403: function() {
						// 403 -- Access denied
						Backbone.history.navigate('login', { trigger : true });
					}
				}
			});
		}
	});

	return ApplicationModel;
});