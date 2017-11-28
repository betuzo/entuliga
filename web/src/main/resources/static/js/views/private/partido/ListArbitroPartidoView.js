define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'views/private/partido/ItemArbitroPartidoView',
	'text!templates/private/partido/tplListArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel,
						TorneoArbitrosCollection, ItemArbitroPartidoView, tplListArbitroPartido){


	var ListArbitroPartidoView = Mn.CollectionView.extend({
		tagName: 'ul',

		template: _.template(tplListArbitroPartido),

		childView: ItemArbitroPartidoView
	});

	return ListArbitroPartidoView;

});