define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'views/private/partido/RowPartidoTorneoJugadorView',
	'text!templates/private/partido/tplPartidoEquipo.html'
], function($, Backbone, BaseView, TorneoPartidoModel, TorneoEquipoModel,
            TorneoJugadoresCollection, RowPartidoTorneoJugadorView, tplPartidoEquipo){

	var PartidoLocalView = BaseView.extend({
	    el: '#section-equipo',
        template: _.template(tplPartidoEquipo),

        events: {

        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.parent = opts.parent;
            this.type = opts.type;
            this.$el = $('#section-'+ this.type);
            this.jugadores = new TorneoJugadoresCollection();
            this.listenTo(this.jugadores, 'add', this.agregarJugador);
            this.listenTo(this.jugadores, 'sync', this.syncJugadores);
            this.render();

            var local = new TorneoEquipoModel({ id : this.model.get(this.type + 'Id') });
            this.jugadores.setTorneoEquipo(local);
            this.jugadores.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarJugador: function(model) {
            var vista = new RowPartidoTorneoJugadorView(model);
            this.$el.find("#all-jugadores").find('tbody:last').append(vista.render().$el);
        },

        syncJugadores: function() {
            this.parent.addPlayers(this.jugadores, this.type);
        }
	});

	return PartidoLocalView;

});