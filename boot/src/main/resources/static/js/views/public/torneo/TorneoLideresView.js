define([
	'jquery',
	'bootstrap',
	'core/BaseView',
    'collections/EstadisticasCollection',
    'views/public/torneo/RowEstadisticaJugadorView',
	'text!templates/public/torneo/tplTorneoLideres.html'
], function($, bootstrap, BaseView, EstadisticasCollection, RowEstadisticaJugadorView, tplTorneoLideres){

	var TorneoLideresView = BaseView.extend({
	    el: '#torneo-lideres',
        template: _.template(tplTorneoLideres),

        events: {
        },

        initialize: function(modelo) {
            this.model = modelo;

            this.lideres = new EstadisticasCollection();
            this.lideres.setTorneo(this.model);
            this.listenTo(this.lideres, 'add', this.agregarLider);
            this.listenTo(this.lideres, 'sync', this.syncLideres);

            this.lideres.fetch();

            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarLider: function(modelo) {
            var vista = new RowEstadisticaJugadorView(modelo);
            $("#torneo-tabla-posiciones").find('tbody:last').append(vista.render().$el);
        },

        syncLideres: function() {

        },

        getTableByTipoEstadistica: function(tipoEstadistica) {
            switch (tipoEstadistica) {
                case 1:
                    return "torneo-lideres-puntos";
                case 2:
                    return "torneo-lideres-rebotes";
                case 3:
                    return "torneo-lideres-asistencias";
                case 4:
                    return "torneo-lideres-bloqueos";
                case 5:
                    return "torneo-lideres-robos";
            }
        }
	});

	return TorneoLideresView;

});