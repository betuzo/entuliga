define([
	'jquery',
	'backbone',
	'core/BaseView',
	'collections/estadistica/RebotesCollection',
	'views/private/estadistica/RowEstadisticaReboteView',
	'text!templates/private/estadistica/tplEstadisticaRebotes.html'
], function($, Backbone, BaseView, RebotesCollection,
            RowEstadisticaReboteView, tplEstadisticaRebotes){

	var EstadisticaRebotesView = BaseView.extend({
	    el: '#section-estadisticas-rebotes',
        template: _.template(tplEstadisticaRebotes),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            app.rebotesPartido = new RebotesCollection();
            this.listenTo(app.rebotesPartido, 'add', this.agregarRebote);
            this.listenTo(app.rebotesPartido, 'sync', this.syncRebotes);
            this.render();

            app.rebotesPartido.setTorneoPartido(this.model);
            app.rebotesPartido.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarRebote: function(model) {
            var origenClass = this.generateClassByOrigenRebote(model.get('origen'));
            model.set({origenClass: origenClass});
            var vista = new RowEstadisticaReboteView({modelo: model, parent: this.parent});
            $("#rebotes").find('tbody:last').append(vista.render().$el);
        },

        syncRebotes: function() {
        },

        generateClassByOrigenRebote: function(estadoSolicitud) {
            switch (estadoSolicitud) {
                case "LOCAL":
                    return "success";
                case "VISITA":
                    return "info";
            }
        },
	});

	return EstadisticaRebotesView;

});