define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/FaltasCollection',
	'views/private/estadistica/RowEstadisticaFaltaView',
	'text!templates/private/estadistica/tplEstadisticaFaltas.html'
], function($, Backbone, BaseView, PuntosCollection,
            RowEstadisticaPuntoView, tplEstadisticaFaltas){

	var EstadisticaFaltasView = BaseView.extend({
	    el: '#section-estaadisticas-faltas',
        template: _.template(tplEstadisticaFaltas),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.puntosPartido = new FaltasCollection();
            this.listenTo(app.puntosPartido, 'add', this.agregarPunto);
            this.listenTo(app.puntosPartido, 'sync', this.syncPuntos);
            this.render();

            app.puntosPartido.setTorneoPartido(this.model);
            app.puntosPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarPunto: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaFaltaView({modelo: model, parent: this.parent});
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

	return EstadisticaFaltasView;

});