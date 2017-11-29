define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'text!templates/private/partido/tplItemArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel,
						TorneoArbitrosCollection, tplItemArbitroPartido){


	var ItemArbitroPartidoView = Mn.View.extend({
		tagName: 'li',
		
		template: _.template(tplItemArbitroPartido),

		onRender: function(opts) {
			this.$el.html(this.template(this.model.toJSON()));			
		},


		onBeforeDetach: function() {
			console.log('before:detach');
		},

		onDetach: function() {
			console.log('detach');
		},

		onDomRemove: function() {
			console.log('dom:remove');
		}

	});

	return ItemArbitroPartidoView;

});