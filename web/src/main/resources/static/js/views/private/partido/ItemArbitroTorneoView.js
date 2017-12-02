define([
	'jquery',
	'backbone',
	'marionette',
	'bootstrap',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'text!templates/private/partido/tplItemArbitroTorneo.html'
], function($, Backbone, Mn, bootstrap, TorneoArbitroModel,
						TorneoArbitrosCollection, tplItemArbitroTorneo){


	var ItemArbitroTorneoView = Mn.View.extend({
		tagName: 'li',
		template: _.template(tplItemArbitroTorneo),
		ui:{
			toggle: '.toggle'
		},

		events:{
			'click @ui.toggle' : 'toggle'
		},

		toggle: function(){
			console.log("toggle arbitro torneo");
		},

		onRender: function(opts) {
			this.$el.html(this.template(this.model.toJSON()));			
		}
	});

	return ItemArbitroTorneoView;

});