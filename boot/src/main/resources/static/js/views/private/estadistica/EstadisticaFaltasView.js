define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/FaltasCollection',
	'views/private/estadistica/RowEstadisticaFaltaView',
	'text!templates/private/estadistica/tplEstadisticaFaltas.html'
], function($, Backbone, BaseView, FaltasCollection,
            RowEstadisticaFaltaView, tplEstadisticaFaltas){

	var EstadisticaFaltasView = BaseView.extend({
	    el: '#section-estaadisticas-faltas',
        template: _.template(tplEstadisticaFaltas),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.faltasPartido = new FaltasCollection();
            this.listenTo(app.faltasPartido, 'add', this.agregarFalta);
            this.listenTo(app.faltasPartido, 'sync', this.syncFaltas);
            this.render();

            app.faltasPartido.setTorneoPartido(this.model);
            app.faltasPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarFalta: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaFaltaView({modelo: model, parent: this.parent});
            $("#faltas").find('tbody:last').append(vista.render().$el);
        },

        syncFaltas: function() {
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