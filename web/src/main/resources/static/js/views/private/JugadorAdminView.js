define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/JugadoresCollection',
	'views/private/JugadorDetailView',
	'views/private/JugadorEditView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplJugadorAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, JugadoresCollection, JugadorDetailView,
            JugadorEditView, ModalGenericView, tplJugadorAdmin){

	var JugadorAdminView = BaseView.extend({
        template: _.template(tplJugadorAdmin),

        events: {
            'change #select-jugador'    : 'changeJugador',
            'click #jugador-nuevo'      : 'newJugador',
            'click #jugador-editar'     : 'editJugador',
            'click #jugador-borrar'     : 'deleteJugador'
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
                $('#jugador-borrar').removeAttr("disabled");
            } else {
                if (typeof this.jugadorDetailView !== 'undefined') {
                    this.jugadorDetailView.destroyView();
                }
                $('#jugador-editar').attr("disabled", true);
                $('#jugador-borrar').attr("disabled", true);
            }
        },

        newJugador: function() {
            this.disabledAction(true);
            var jugadorEditView = new JugadorEditView({tipo: 'new', modelo: null});
            $('#jugador-edit').html(jugadorEditView.render().$el);
            jugadorEditView.setUpNew();
        },

        editJugador: function() {
            this.disabledAction(true);
            var modelo = app.jugadores.get($("#select-jugador").val());
            var jugadorEditView = new JugadorEditView({tipo: 'edit', modelo: modelo});
            $('#jugador-edit').html(jugadorEditView.render().$el);
            jugadorEditView.setUpEdit();
        },

        deleteJugador: function() {
            var modelo = app.jugadores.get($("#select-jugador").val());
            modelo.destroy({
                wait:true,
                success: function(model, response) {
                    new ModalGenericView({message: response.message});
                    if(response.result){
                        $("#select-jugador option:selected").remove();
                        $('#select-jugador').change();
                    }
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },


        agregarJugador: function(modelo) {
            $('#select-jugador').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre') + " " + modelo.get('paterno') + " " + modelo.get('paterno')
            }));
        },

        syncJugadores: function() {
            $('#select-jugador').change();
        }
	});

	return JugadorAdminView;

});
