define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/RobosCollection',
	'views/private/estadistica/RowEstadisticaRoboView',
	'text!templates/private/estadistica/tplEstadisticaRobos.html'
], function($, Backbone, BaseView, RobosCollection,
            RowEstadisticaRoboView, tplEstadisticaRobos){

	var EstadisticaRobosView = BaseView.extend({
	    el: '#section-estadisticas-robos',
        template: _.template(tplEstadisticaRobos),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.robosPartido = new RobosCollection();
            this.listenTo(app.robosPartido, 'add', this.agregarRobo);
            this.listenTo(app.robosPartido, 'sync', this.syncRobos);
            this.render();

            app.robosPartido.setTorneoPartido(this.model);
            app.robosPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarRobo: function(model) {
            var origenClass = this.generateClassByOrigenPunto(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaRoboView({modelo: model, parent: this.parent});
            $("#robos").find('tbody:last').append(vista.render().$el);
        },

        syncRobos: function() {
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

	return EstadisticaRobosView;

});