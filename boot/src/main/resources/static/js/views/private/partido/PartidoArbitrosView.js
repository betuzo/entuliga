define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/partido/tplPartidoArbitros.html'
], function($, Backbone, BaseView, tplPartidoArbitros){

	var PartidoArbitrosView = BaseView.extend({
	    el: '#section-arbitros',
        template: _.template(tplPartidoArbitros),

        events: {

        },

        initialize: function(modelo) {
            this.model = modelo;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        }
	});

	return PartidoArbitrosView;

});