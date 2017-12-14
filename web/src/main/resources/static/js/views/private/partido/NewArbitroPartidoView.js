define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/ArbitroPartidoModel',
	'collections/TorneoArbitrosCollection',
	'views/private/partido/ItemArbitroPartidoView',
	'views/private/partido/ListArbitroTorneoView',
	'text!templates/private/partido/tplNewArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, ArbitroPartidoModel, TorneoArbitrosCollection, ItemArbitroPartidoView, ListArbitroTorneoView, tplNewArbitroPartido){


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

		initialize: function(opts) {
			this.modelPartido = opts.modelo;
			this.model = new ArbitroPartidoModel();

			
			this.torneoarbitros = new TorneoArbitrosCollection();
			this.torneoarbitros.setTorneo(new ArbitroPartidoModel({id: this.modelPartido.get('id') }));
			this.torneoarbitros.fetch();
		},

		cancelarDestroyView: function(){
			this.destroy();
			$("#modal-partido-parent").append('<div id="modal-partido"></div>');
		},

		guardarArbitro: function(){
			console.log("guardar arbitros");
			if (this.model.get("id") != null) {
				delete this.model.attributes.id;
			}

			var listBody = this.getChildView('listBody');
			// var arrayArbitros = [];

			var idPartidoModel =  this.modelPartido.get('id');
			that = this;
			
			listBody.children.each(function(childView) {
				var objectArbitro = childView.$el.find("select,input").serializeObject();
				objectArbitro.partidoId = idPartidoModel;
				
				if (childView.$el.find(".togglecheckbox").is(":checked")) {

					// arrayArbitros.push(objectArbitro);
					that.model.set(objectArbitro);
					if (that.model.isValid(true)) {
						that.model.save();
						childView.$el.find("select,input").remove();
						childView.destroy();
					}
				}
			});

			// for (var i = 0; i < arrayArbitros.length; i++) {
			// 	this.model.set(arrayArbitros[i]);
			// 	if (this.model.isValid(true)) {
			// 		this.model.save();
			// 		childView.destroy();
			// 		// this.model.destroy();
			// 	}
			// }
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