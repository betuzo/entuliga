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

		// ui:{
		// 	toggle: '.toggle'
		// },

		// events:{
		// 	'click @ui.toggle' : 'toggle'
		// },

		// toggle: function(){
		// 	console.log("toggle arbitro partido");
		// },

		
		onRender: function(opts) {
			this.$el.html(this.template(this.model.toJSON()));			
		},


	});

	return ItemArbitroPartidoView;

});