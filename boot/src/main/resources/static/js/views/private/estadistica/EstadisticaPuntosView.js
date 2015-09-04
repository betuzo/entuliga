define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/PuntosCollection',
	'views/private/estadistica/RowEstadisticaPuntoView',
	'text!templates/private/estadistica/tplEstadisticaPuntos.html'
], function($, Backbone, BaseView, PuntosCollection,
            RowEstadisticaPuntoView, tplEstadisticaPuntos){

	var EstadisticaPuntosView = BaseView.extend({
	    el: '#section-estaadisticas',
        template: _.template(tplEstadisticaPuntos),

        events: {

        },

        initialize: function(modelo) {
            this.model = modelo;
            this.puntos = new PuntosCollection();
            this.listenTo(this.puntos, 'add', this.agregarPunto);
            this.listenTo(this.puntos, 'sync', this.syncPuntos);
            this.render();

            this.puntos.setTorneoPartido(this.model);
            this.puntos.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarPunto: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaPuntoView(model);
            $("#puntos").find('tbody:last').append(vista.render().$el);
        },

        syncPuntos: function() {
        },

        generateClassByOrigenPunto: function(estadoSolicitud) {
            switch (estadoSolicitud) {
                case "LOCAL":
                    return "success";
                case "VISITA":
                    return "info";
            }
        },
	});

	return EstadisticaPuntosView;

});