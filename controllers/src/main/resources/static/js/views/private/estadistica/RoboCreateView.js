define([
	'jquery',
	'backbone',
    'bootstrap',
	'core/BaseView',
	'models/estadistica/RoboModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'text!templates/private/estadistica/tplRoboCreate.html'
], function($, Backbone, bootstrap, BaseView, RoboModel, TorneoEquipoModel,
            TorneoJugadoresCollection, tplRoboCreate){

	var RoboCreateView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplRoboCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(opts) {
            this.model = new RoboModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.robadores = new TorneoJugadoresCollection();
            this.listenTo(this.robadores, 'add', this.agregarRobador);
            this.listenTo(this.robadores, 'sync', this.syncRobadores);
            this.fetchRobadoresByOrigen(opts.origen);

            this.perdedores = new TorneoJugadoresCollection();
            this.listenTo(this.perdedores, 'add', this.agregarPerdedor);
            this.listenTo(this.perdedores, 'sync', this.syncPerdedores);
            this.fetchPerdedoresByOrigen(opts.origen);

            Backbone.Validation.bind(this);
        },

        fetchRobadoresByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.robadores.setTorneoEquipo(equipo);
            this.robadores.fetch();
        },

        fetchPerdedoresByOrigen: function(origen) {
            var equipo;
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.perdedores.setTorneoEquipo(equipo);
            this.perdedores.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#robo-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarRobador: function(modelo) {
            $('#select-robo').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncRobadores: function(modelo) {
            $('#select-robo').change();
        },

        agregarPerdedor: function(modelo) {
            $('#select-pierde').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncPerdedores: function(modelo) {
            $('#select-pierde').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-robo").serializeObject();
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

	return RoboCreateView;

});