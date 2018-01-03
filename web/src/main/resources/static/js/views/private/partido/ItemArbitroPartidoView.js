define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/ArbitroPartidoModel',
	'text!templates/private/partido/tplItemArbitroPartido.html'
], function($, Backbone, Mn, bootstrap, ArbitroPartidoModel, tplItemArbitroPartido){


	var ItemArbitroPartidoView = Mn.View.extend({
		tagName: 'tr',
		template: _.template(tplItemArbitroPartido),

		ui:{
			eliminarArbitro: '#eliminar-arbitro-partido'
		},

		events:{
			'click @ui.eliminarArbitro' : 'deleteArbitroPartido'
		},

		deleteArbitroPartido: function(){
			console.log("delte arbitro partido");
		},

		onRender: function(opts) {
			this.$el.html(this.template(this.model.toJSON()));			
		},


	});

	return ItemArbitroPartidoView;

});