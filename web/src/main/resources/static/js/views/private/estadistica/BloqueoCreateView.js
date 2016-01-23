define([
	'jquery',
	'backbone',
    'bootstrap',
	'core/BaseView',
	'models/estadistica/BloqueoModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'views/private/util/ModalGenericView',
	'text!templates/private/estadistica/tplBloqueoCreate.html'
], function($, Backbone, bootstrap, BaseView, BloqueoModel, TorneoEquipoModel,
            TorneoJugadoresCollection, ModalGenericView, tplBloqueoCreate){

	var BloqueoCreateView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplBloqueoCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(opts) {
            this.model = new BloqueoModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.bloqueadores = new TorneoJugadoresCollection();
            this.listenTo(this.bloqueadores, 'add', this.agregarInfractor);
            this.listenTo(this.bloqueadores, 'sync', this.syncBloqueadores);
            this.fetchBloqueadoresByOrigen(opts.origen);

            this.bloqueados = new TorneoJugadoresCollection();
            this.listenTo(this.bloqueados, 'add', this.agregarReceptor);
            this.listenTo(this.bloqueados, 'sync', this.syncBloqueados);
            this.fetchBloqueadosByOrigen(opts.origen);

            Backbone.Validation.bind(this);
        },

        fetchBloqueadoresByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.bloqueadores.setTorneoEquipo(equipo);
            this.bloqueadores.fetch();
        },

        fetchBloqueadosByOrigen: function(origen) {
            var equipo;
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.bloqueados.setTorneoEquipo(equipo);
            this.bloqueados.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#bloqueo-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarInfractor: function(modelo) {
            $('#select-bloquea').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncBloqueadores: function(modelo) {
            $('#select-bloquea').change();
        },

        agregarReceptor: function(modelo) {
            $('#select-bloqueado').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncBloqueados: function(modelo) {
            $('#select-bloqueado').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-bloqueo").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Bloqueo registrado correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar el bloqueo'
                        });
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

	return BloqueoCreateView;

});