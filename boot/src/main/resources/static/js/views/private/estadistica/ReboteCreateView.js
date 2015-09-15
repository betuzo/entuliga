define([
	'jquery',
	'backbone',
    'bootstrap',
	'core/BaseView',
	'models/estadistica/ReboteModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
    'collections/estadistica/TipoRebotesCollection',
	'text!templates/private/estadistica/tplReboteCreate.html'
], function($, Backbone, bootstrap, BaseView, ReboteModel, TorneoEquipoModel,
            TorneoJugadoresCollection, TipoRebotesCollection, tplReboteCreate){

	var ReboteCreateView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplReboteCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(opts) {
            this.model = new ReboteModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.tipos = new TipoRebotesCollection();
            this.listenTo(this.tipos, 'add', this.agregarTipoRebote);
            this.listenTo(this.tipos, 'sync', this.syncTipoRebotes);
            this.tipos.fetch();

            this.jugadores = new TorneoJugadoresCollection();
            this.listenTo(this.jugadores, 'add', this.agregarJugador);
            this.listenTo(this.jugadores, 'sync', this.syncJugadores);
            this.fetchJugadoresByOrigen(opts.origen);

            Backbone.Validation.bind(this);
        },

        fetchJugadoresByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.jugadores.setTorneoEquipo(equipo);
            this.jugadores.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#rebote-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarTipoRebote: function(modelo) {
            $('#select-tipo-rebote').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncTipoRebotes: function(modelo) {
            $('#select-tipo-rebote').change();
        },

        agregarJugador: function(modelo) {
            $('#select-jugador').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncJugadores: function(modelo) {
            $('#select-jugador').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-rebote").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        console.log('Successfully saved!');
                        alert('Great Success!');
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        console.log(model.toJSON());
                        console.log('error.responseText');
                    }
                });
            }
        },

        destroyView: function() {
            $("body").removeClass("modal-open");
            // COMPLETELY UNBIND THE VIEW
            this.undelegateEvents();
            this.$el.removeData().unbind();
            // Remove view from DOM
            this.remove();
            Backbone.View.prototype.remove.call(this);
            $("<div id='modal-partido'></div>").appendTo('#modal-partido-parent');
        }
	});

	return ReboteCreateView;

});