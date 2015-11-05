define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/AsistenciasCollection',
	'views/private/estadistica/RowEstadisticaAsistenciaView',
	'text!templates/private/estadistica/tplEstadisticaAsistencias.html'
], function($, Backbone, BaseView, AsistenciasCollection,
            RowEstadisticaAsistenciaView, tplEstadisticaAsistencias){

	var EstadisticaAsistenciasView = BaseView.extend({
	    el: '#section-estadisticas-asistencias',
        template: _.template(tplEstadisticaAsistencias),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.asistenciasPartido = new AsistenciasCollection();
            this.listenTo(app.asistenciasPartido, 'add', this.agregarAsistencia);
            this.listenTo(app.asistenciasPartido, 'sync', this.syncAsistencias);
            this.render();

            app.asistenciasPartido.setTorneoPartido(this.model);
            app.asistenciasPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarAsistencia: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaAsistenciaView({modelo: model, parent: this.parent});
            $("#asistencias").find('tbody:last').append(vista.render().$el);
        },

        syncAsistencias: function() {
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

	return EstadisticaAsistenciasView;

});