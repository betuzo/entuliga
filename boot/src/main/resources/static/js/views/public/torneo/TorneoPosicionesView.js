define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/ClasificacionesCollection',
	'views/public/torneo/RowEstadisticaEquipoView',
	'text!templates/public/torneo/tplTorneoPosiciones.html'
], function($, bootstrap, BaseView, ClasificacionesCollection,
            RowEstadisticaEquipoView, tplTorneoPosiciones){

	var TorneoPosicionesView = BaseView.extend({
	    el: '#torneo-posiciones',
        template: _.template(tplTorneoPosiciones),

        events: {
        },

        initialize: function(modelo) {
            this.model = modelo;

            this.clasificaciones = new ClasificacionesCollection();
            this.clasificaciones.setTorneo(this.model);
            this.listenTo(this.clasificaciones, 'add', this.agregarClasificacion);
            this.listenTo(this.clasificaciones, 'sync', this.syncClasificaciones);

            this.clasificaciones.fetch();

            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarClasificacion: function(modelo) {
            var vista = new RowEstadisticaEquipoView(modelo);
            $("#torneo-tabla-posiciones").find('tbody:last').append(vista.render().$el);
        },

        syncClasificaciones: function() {

        }
	});

	return TorneoPosicionesView;

});