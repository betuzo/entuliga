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
			this.parentView = opts.parent;
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
			
			var listBody = this.getChildView('listBody');
			var idPartidoModel =  this.modelPartido.get('id');
			that = this;

			listBody.children.each(function(childView) {
				var objectArbitro = childView.$el.find("select,input").serializeObject();
				objectArbitro.partidoId = idPartidoModel;
				
				if (childView.$el.find(".togglecheckbox").is(":checked")) {
					that.model.set(objectArbitro);

					if (that.model.isValid(true)) {
						var modelsavetest = that.model.save();
						modelsavetest.complete(function(model) {
							that.parentView.viewPartidoArbitro.getChildView('listBody').collection.add(new ArbitroPartidoModel(model.responseJSON));
						});
						childView.$el.find("select,input").remove();
						childView.destroy();
					}
				}
			});

			this.destroy();
			$("#modal-partido-parent").append('<div id="modal-partido"></div>');
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