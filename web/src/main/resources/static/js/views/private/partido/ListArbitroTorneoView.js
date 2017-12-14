define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'views/private/partido/ItemArbitroTorneoView',
	'text!templates/private/partido/tplListArbitroTorneo.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel,
						TorneoArbitrosCollection, ItemArbitroTorneoView, tplListArbitroTorneo){


	var ListArbitroTorneoView = Mn.CollectionView.extend({
		tagName: 'ul',
		className: "list-group",
		template: _.template(tplListArbitroTorneo),

		childView: ItemArbitroTorneoView
	});


	return ListArbitroTorneoView;
});