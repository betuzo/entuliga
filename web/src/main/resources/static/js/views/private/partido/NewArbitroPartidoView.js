define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'text!templates/private/partido/tplNewArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel,
						TorneoCanchasCollection, tplNewArbitroPartido){


	var NewArbitroPartidoView = Mn.View.extend({
		template: _.template(tplNewArbitroPartido),
		el: '#modal-partido',
		
		regions: {
			region1: '#buscarArbitro',
			region2: '#listArbitro'
		},

		onBeforeRender: function() {
			console.log('before:render');
		},

		onRender: function() {
			console.log('render');
			this.$('#new-arbitro-partido').modal('show');
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

	return NewArbitroPartidoView;

});