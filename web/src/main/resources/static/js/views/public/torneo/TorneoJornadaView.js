define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/TorneoJornadasCollection',
	'collections/TorneoPartidosCollection',
	'views/public/torneo/RowJornadaPartidoView',
	'text!templates/public/torneo/tplTorneoJornada.html'
], function($, bootstrap, BaseView, TorneoJornadasCollection,
            TorneoPartidosCollection, RowJornadaPartidoView,
            tplTorneoJornada){

	var TorneoJornadaView = BaseView.extend({
	    el: '#torneo-jornadas',
        template: _.template(tplTorneoJornada),

        events: {
            'change #select-torneo-jornada' : 'changeJornada'
        },

        initialize: function(modelo) {
            this.model = modelo;
            this.render();

            this.torneojornadas = new TorneoJornadasCollection();
            this.torneojornadas.setTorneo(this.model);
            this.listenTo(this.torneojornadas, 'add', this.agregarTorneoJornada);
            this.listenTo(this.torneojornadas, 'sync', this.syncTorneoJornadas);

            this.torneojornadas.fetch();

            this.torneopartidos = new TorneoPartidosCollection();
            this.listenTo(this.torneopartidos, 'add', this.agregarTorneoPartido);
            this.listenTo(this.torneopartidos, 'sync', this.syncTorneoPartidos);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarTorneoJornada: function(modelo) {
            $('#select-torneo-jornada').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre') + ' - ' + modelo.get('fase')
            }));
        },

        syncTorneoJornadas: function() {
            $('#select-torneo-jornada').change();
        },

        changeJornada: function(event) {
            var modelo = this.torneojornadas.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.torneoJornada = modelo;
                $("#torneo-partidos").find('tbody').html('');
                this.torneopartidos.setTorneoJornada(this.torneoJornada);
                this.torneopartidos.fetch();
            }
        },

        agregarTorneoPartido: function(modelo) {
            var vista = new RowJornadaPartidoView(modelo);
            $("#torneo-partidos").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoPartidos: function() {
        }
	});

	return TorneoJornadaView;

});