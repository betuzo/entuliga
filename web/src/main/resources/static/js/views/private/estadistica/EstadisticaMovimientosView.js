define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/MovimientosCollection',
	'views/private/estadistica/RowEstadisticaMovimientoView',
	'text!templates/private/estadistica/tplEstadisticaMovimientos.html'
], function($, Backbone, BaseView, MovimientosCollection,
            RowEstadisticaMovimientoView, tplEstadisticaMovimientos){

	var EstadisticaMovimientosView = BaseView.extend({
	    el: '#section-estadisticas-cambios',
        template: _.template(tplEstadisticaMovimientos),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.movimientosPartido = new MovimientosCollection();
            this.listenTo(app.movimientosPartido, 'add', this.agregarMovimiento);
            this.listenTo(app.movimientosPartido, 'sync', this.syncMovimientos);
            this.render();

            app.movimientosPartido.setTorneoPartido(this.model);
            app.movimientosPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarMovimiento: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaMovimientoView({modelo: model, parent: this.parent});
            $("#movimientos").find('tbody:last').append(vista.render().$el);
        },

        syncMovimientos: function() {
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

	return EstadisticaMovimientosView;

});