define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'models/ArbitroPartidoModel',
	'collections/TorneoArbitrosCollection',
	'views/private/partido/ItemArbitroPartidoView',
	'views/private/partido/ListArbitroTorneoView',
	'text!templates/private/partido/tplNewArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel, ArbitroPartidoModel, TorneoArbitrosCollection, ItemArbitroPartidoView, ListArbitroTorneoView, tplNewArbitroPartido){


	var NewArbitroPartidoView = Mn.View.extend({
		template: _.template(tplNewArbitroPartido),
		el: '#modal-partido',
		ui:{
			btnAceptar: '#btn-aceptar-arbitro-partido',
			btnCancelar: '#btn-cancelar-arbitro-partido'
		},

		events:{
			'click @ui.btnAceptar': 'guardarArbitro',
			'click @ui.btnCancelar': 'cancelarDestroyView'
		},

		regions: {
			'listBody':'#list-arbitros-torneo'
		},

		cancelarDestroyView: function(){
			this.destroy();
			$("#modal-partido-parent").append('<div id="modal-partido"></div>');
		},

		guardarArbitro: function(){
			console.log("guardar arbitros");
			console.log("listBody");			
			var listBody = this.getChildView('listBody');
			console.log(listBody);			
			console.log(listBody.children);			
			console.log(listBody.children._views);
			var viewschild = listBody.children._views;
			console.log(viewschild.length);
			console.log("For*******************");

			for (key in viewschild) {
				console.log("For*******************", key);

			// Hacer algo con la clave key
			}

			// for (var i = 0; i < viewschild.length; i++) {
			// 	console.log("For*******************", viewschild[0]);

			// 	console.log(viewschild[i].model.attributes);
			// 	// viewschild[i].model.attributes;
			// }

		},

		onBeforeRender: function(opts) {
			this.torneoarbitros = new TorneoArbitrosCollection();
			this.torneoarbitros.setTorneo(new TorneoArbitroModel({id: opts.options.modelo.attributes.torneoId}));
			this.torneoarbitros.fetch();
		},

		onRender: function(opts) {
			this.showChildView('listBody', new ListArbitroTorneoView({
				collection: this.torneoarbitros
			}));

			var that = this;			
			$('#new-arbitro-partido').on('hidden.bs.modal', function (e) {
				that.destroy();
				$("#modal-partido-parent").append('<div id="modal-partido"></div>');
			});
			this.$('.alert-danger').hide();
			this.$('#new-arbitro-partido').modal('show');
		},


		
	});

	return NewArbitroPartidoView;

});