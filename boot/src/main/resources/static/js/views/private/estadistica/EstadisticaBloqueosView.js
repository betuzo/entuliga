define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/BloqueosCollection',
	'views/private/estadistica/RowEstadisticaBloqueoView',
	'text!templates/private/estadistica/tplEstadisticaBloqueos.html'
], function($, Backbone, BaseView, BloqueosCollection,
            RowEstadisticaBloqueoView, tplEstadisticaBloqueos){

	var EstadisticaBloqueosView = BaseView.extend({
	    el: '#section-estaadisticas-bloqueos',
        template: _.template(tplEstadisticaBloqueos),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.bloqueosPartido = new BloqueosCollection();
            this.listenTo(app.bloqueosPartido, 'add', this.agregarBloqueo);
            this.listenTo(app.bloqueosPartido, 'sync', this.syncBloqueos);
            this.render();

            app.bloqueosPartido.setTorneoPartido(this.model);
            app.bloqueosPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarBloqueo: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaBloqueoView({modelo: model, parent: this.parent});
            $("#bloqueos").find('tbody:last').append(vista.render().$el);
        },

        syncBloqueos: function() {
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

	return EstadisticaBloqueosView;

});