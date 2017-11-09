define([
	'jquery',
	'backbone',
    'bootstrap',
	'core/BaseView',
	'models/estadistica/MovimientoModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
    'collections/estadistica/TipoMovimientosCollection',
    'views/private/util/ModalGenericView',
	'text!templates/private/estadistica/tplMovimientoCreate.html'
], function($, Backbone, bootstrap, BaseView, MovimientoModel, TorneoEquipoModel,
            TorneoJugadoresCollection, TipoMovimientosCollection, ModalGenericView,
            tplMovimientoCreate){

	var MovimientoCreateView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplMovimientoCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar',
            'change #select-entra': 'changeEntra'
        },

        initialize: function(opts) {
            this.model = new MovimientoModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.origen = opts.origen;
            this.tipo = opts.tipo;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.tipos = new TipoMovimientosCollection();
            this.listenTo(this.tipos, 'add', this.agregarTipoMovimiento);
            this.listenTo(this.tipos, 'sync', this.syncTipoMovimientos);
            this.tipos.fetch();

            this.entran = new TorneoJugadoresCollection();
            this.listenTo(this.entran, 'add', this.agregarEntra);
            this.listenTo(this.entran, 'sync', this.syncEntraes);

            this.salen = new TorneoJugadoresCollection();
            this.listenTo(this.salen, 'add', this.agregarSale);
            this.listenTo(this.salen, 'sync', this.syncSalees);
            if (this.tipo != 'DIRECT_INIT' && this.tipo != 'DIRECT_OTHER') {
                this.fetchSaleesByOrigen(this.origen);
            } else {
                this.entran.add(opts.entra);
                if (this.tipo == 'DIRECT_OTHER') {
                    this.salen.add(opts.sale);
                } else {
                    this.agregarSaleDefault();
                }
            }

            Backbone.Validation.bind(this);
        },

        fetchEntraesByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.entran.setTorneoEquipo(equipo);
            this.entran.fetch();
        },

        fetchSaleesByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.salen.setTorneoEquipo(equipo);
            this.salen.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#movimiento-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarTipoMovimiento: function(modelo) {
            if (this.tipo == 'DIRECT_INIT' && modelo.get('clave') != 'INICIO'){
                return;
            }
            if (this.tipo == 'DIRECT_OTHER' && modelo.get('clave') == 'INICIO'){
                return;
            }
            $('#select-tipo-movimiento').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncTipoMovimientos: function(modelo) {
            $('#select-tipo-movimiento').change();
        },

        agregarEntra: function(modelo) {
            $('#select-entra').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncEntraes: function(modelo) {
            $('#select-entra').change();
        },

        changeEntra: function(event) {
            var entra = this.entran.get($(event.target).val());
            $('#select-sale').html('');
            that = this;
            this.salen.each(function(modelo){
                if (entra.get('id') == modelo.get('id')) {
                    return;
                }
                that.agregarSale(modelo);
            });
        },

        agregarSaleDefault: function() {
            $('#select-sale').append($('<option>', {
                value: '',
                text : 'Ninguno'
            }));
        },

        agregarSale: function(modelo) {
            $('#select-sale').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncSalees: function(modelo) {
            $('#select-sale').change();
            this.fetchEntraesByOrigen(this.origen);
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-movimiento").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Movimiento registrado correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar el movimiento'
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

	return MovimientoCreateView;

});