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
		ui:{
			toggle: '.toggle'
		},

		events:{
			'click @ui.toggle' : 'toggle'
		},

		toggle: function(){
			console.log("toggle arbitro partido");
		},

		onRender: function(opts) {
			this.$el.html(this.template(this.model.toJSON()));			
		},


	});

	return ItemArbitroPartidoView;

});