define([
	'jquery',
	'backbone',
	'bootstrap',
	'core/BaseView',
	'models/TorneoJugadorModel',
	'collections/JugadoresCollection',
	'collections/PosicionesCollection',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplJugadorSearch.html'
], function($, Backbone, bootstrap, BaseView, TorneoJugadorModel,
            JugadoresCollection, PosicionesCollection, ModalGenericView,
            tplJugadorSearch){

	var EquipoSearchView = BaseView.extend({
	    el: '#modal-jugador-search',
        template: _.template(tplJugadorSearch),

        events: {
            'keydown #text-busqueda' : 'changeBuscar',
            'click #btn-aceptar'    : 'clickAceptar',
            'click .list-group-item': 'clickItemSearch'
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = new TorneoJugadorModel();
            this.torneoEquipo = opts.modelo;
            this.render();
            this.jugadores = new JugadoresCollection();
            this.listenTo(this.jugadores, 'add', this.agregarJugador);
            this.listenTo(this.jugadores, 'sync', this.syncJugadores);
            this.posiciones = new PosicionesCollection();
            this.listenTo(this.posiciones, 'add', this.agregarPosicion);
            this.listenTo(this.posiciones, 'sync', this.syncPosiciones);
            this.posiciones.fetch();

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#jugador-search-dialog').modal('show');
            this.$('.alert-danger').hide();
            this.$('#torneo-liga-id').val(this.torneoEquipo.get('id'));
        },

        agregarJugador: function(modelo) {

        },

        syncJugadores: function() {
            $("#result-search").html('');
            this.jugadores.forEach(function(jugador) {
                var liitem = "<a class='list-group-item' id='" + jugador.get('id') + "'>" + jugador.get('nombre') +" "+jugador.get('paterno') + " " + jugador.get('materno')+ "</a>";
                $(liitem).appendTo("#result-search");
            });
        },

        agregarPosicion: function(modelo) {
            $('#select-posicion').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncPosiciones: function(modelo) {
            $('#select-posicion').change();
        },

        changeBuscar: function(event) {
            var textName = $(event.target).val();
            var idTorneo = this.torneoEquipo.get('torneoId');
            if (textName != '' && textName != 'undefined' && textName.length > 2) {
                $("#result-search").html('');
                this.jugadores.setTipo('like');
                this.jugadores.setCriterio(textName);
                this.jugadores.fetch({
                    data: { tipo: 'notInTorneoAndContainName', idTorneo: idTorneo },
                    processData: true
                });
            }
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-torneo-jugador").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Jugador registrado correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar el jugador'
                        });
                    }
                });
            }
        },

        clickItemSearch: function(event) {
            $('.list-group-item').removeClass('active');
            $(event.target).addClass('active');
            var jugadorId = $('.list-group-item.active').attr('id');
            this.$('#jugador-id').val(jugadorId);
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

	return EquipoSearchView;

});
