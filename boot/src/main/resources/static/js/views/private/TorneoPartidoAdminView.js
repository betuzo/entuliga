define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoJugadorModel',
	'collections/TorneoJugadoresCollection',
	'collections/TorneoEquiposCollection',
	'views/private/JugadorSearchView',
	'views/private/RowTorneoJugadorView',
	'text!templates/private/tplTorneoJugadorAdmin.html'
], function($, Backbone, BaseView, TorneoJugadorModel, TorneoJugadoresCollection,
            TorneoEquiposCollection, JugadorSearchView, RowTorneoJugadorView,
            tplTorneoJugadorAdmin){

	var TorneoPartidoAdminView = BaseView.extend({
        template: _.template(tplTorneoJugadorAdmin),

        events: {
            'click #jugador-agregar': 'agregarJugador',
            'change #select-torneo-equipo' : 'changeEquipo'
        },

        initialize: function() {
            this.torneoequipos = new TorneoEquiposCollection();
            this.torneoequipos.setTorneo(this.model);
            this.listenTo(this.torneoequipos, 'add', this.agregarTorneoEquipo);
            this.listenTo(this.torneoequipos, 'sync', this.syncTorneoEquipos);

            this.torneoequipos.fetch();

            app.torneojugadores = new TorneoJugadoresCollection();
            this.listenTo(app.torneojugadores, 'add', this.agregarTorneoJugador);
            this.listenTo(app.torneojugadores, 'sync', this.syncTorneoJugadores);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarTorneoEquipo: function(modelo) {
            $('#select-torneo-equipo').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('equipoNombre')
            }));
        },

        syncTorneoEquipos: function() {
            $('#select-torneo-equipo').change();
        },

        changeEquipo: function(event) {
            var modelo = this.torneoequipos.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.torneoEquipo = modelo;
                $("#torneo-jugadores").find('tbody').html('');
                app.torneojugadores.setTorneoEquipo(this.torneoEquipo);
                app.torneojugadores.fetch();
            }
        },

        agregarTorneoJugador: function(modelo) {
            var vista = new RowTorneoJugadorView(modelo);
            $("#torneo-jugadores").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoJugadores: function() {
        },

        agregarJugador: function() {
            this.jugadorSearchView = new JugadorSearchView({modelo: this.torneoEquipo, callbackAceptar: this.selectJugador});
        },

        selectJugador: function(jugador) {
            this.destroyView();
            $("<div id='modal-jugador-search'></div>").appendTo('#torneo-jugadores');
            app.torneojugadores.add(jugador);
        },

        destroyView: function() {
            // COMPLETELY UNBIND THE VIEW
            this.undelegateEvents();
            this.$el.removeData().unbind();
            // Remove view from DOM
            this.remove();
            Backbone.View.prototype.remove.call(this);
        }
	});

	return TorneoPartidoAdminView;

});