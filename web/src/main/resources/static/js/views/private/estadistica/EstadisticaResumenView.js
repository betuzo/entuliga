define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/estadistica/tplEstadisticaResumen.html'
], function($, Backbone, BaseView, tplEstadisticaResumen){

	var EstadisticaResumenView = BaseView.extend({
	    el: '#section-estadisticas-resumen',
        template: _.template(tplEstadisticaResumen),

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

	return EstadisticaResumenView;

});