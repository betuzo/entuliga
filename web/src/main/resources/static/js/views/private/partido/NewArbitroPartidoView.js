define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'views/private/partido/ItemArbitroPartidoView',
	'views/private/partido/ListArbitroPartidoView',
	'text!templates/private/partido/tplNewArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel,
						TorneoArbitrosCollection, ItemArbitroPartidoView, ListArbitroPartidoView, tplNewArbitroPartido){


	var NewArbitroPartidoView = Mn.View.extend({
		template: _.template(tplNewArbitroPartido),
		el: '#modal-partido',
		
		regions: {
			listBody: {
				el: 'ul',
				replaceElement: true
			}
		},

		onBeforeRender: function(opts) {
			this.torneoarbitros = new TorneoArbitrosCollection();
			this.torneoarbitros.setTorneo(new TorneoArbitroModel({id: opts.options.modelo.attributes.torneoId}));
			this.torneoarbitros.fetch();
		},

		onRender: function(opts) {
			this.showChildView('listBody', new ListArbitroPartidoView({
				collection: this.torneoarbitros
			}));

			this.$('.alert-danger').hide();
			this.$('#new-arbitro-partido').modal('show');
		}
		
	});

	return NewArbitroPartidoView;

});