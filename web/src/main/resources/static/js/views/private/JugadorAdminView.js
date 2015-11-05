define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/JugadoresCollection',
	'views/private/JugadorDetailView',
	'views/private/JugadorEditView',
	'text!templates/private/tplJugadorAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, JugadoresCollection, JugadorDetailView, JugadorEditView, tplJugadorAdmin){

	var JugadorAdminView = BaseView.extend({
        template: _.template(tplJugadorAdmin),

        events: {
            'change #select-jugador': 'changeJugador',
            'click #jugador-nuevo': 'newJugador',
            'click #jugador-editar': 'editJugador'
        },

        initialize: function() {
            app.jugadores = new JugadoresCollection();
            app.jugadores.setTipo('none');
            this.listenTo(app.jugadores, 'add', this.agregarJugador);
            this.listenTo(app.jugadores, 'sync', this.syncJugadores);

            app.jugadores.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_jugador").select();
            return this;
        },

        changeJugador: function(event) {
            var modelo = app.jugadores.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.jugadorDetailView = new JugadorDetailView({model: modelo});
                $('#jugador-detail').html(this.jugadorDetailView.render().$el);
                $('#jugador-editar').removeAttr("disabled");
            } else {
                $('#jugador-editar').attr("disabled", true);
            }
        },

        newJugador: function() {
            this.jugadorEditView = new JugadorEditView({tipo: 'new', modelo: null});
            $('#jugador-edit').html(this.jugadorEditView.render().$el);
        },

        editJugador: function() {
            var modelo = app.jugadores.get($("#select-jugador").val());
            this.jugadorEditView = new JugadorEditView({tipo: 'edit', modelo: modelo});
            $('#jugador-edit').html(this.jugadorEditView.render().$el);
        },

        agregarJugador: function(modelo) {
            $('#select-jugador').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncJugadores: function() {
            $('#select-jugador').change();
        }
	});

	return JugadorAdminView;

});