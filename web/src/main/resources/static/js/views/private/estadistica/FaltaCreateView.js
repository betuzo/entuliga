define([
	'jquery',
	'backbone',
    'bootstrap',
	'core/BaseView',
	'models/estadistica/FaltaModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
    'collections/estadistica/TipoFaltasCollection',
    'views/private/util/ModalGenericView',
	'text!templates/private/estadistica/tplFaltaCreate.html'
], function($, Backbone, bootstrap, BaseView, FaltaModel, TorneoEquipoModel,
            TorneoJugadoresCollection, TipoFaltasCollection, ModalGenericView,
            tplFaltaCreate){

	var FaltaCreateView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplFaltaCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar',
            'change .validate-number':'validateNumber',
            'keyup .validate-number':'validateNumber'
        },

        initialize: function(opts) {
            this.model = new FaltaModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.tipos = new TipoFaltasCollection();
            this.listenTo(this.tipos, 'add', this.agregarTipoFalta);
            this.listenTo(this.tipos, 'sync', this.syncTipoFaltas);
            this.tipos.fetch();

            this.infractores = new TorneoJugadoresCollection();
            this.listenTo(this.infractores, 'add', this.agregarInfractor);
            this.listenTo(this.infractores, 'sync', this.syncInfractores);
            this.fetchInfractoresByOrigen(opts.origen);

            this.receptores = new TorneoJugadoresCollection();
            this.listenTo(this.receptores, 'add', this.agregarReceptor);
            this.listenTo(this.receptores, 'sync', this.syncReceptores);
            this.fetchReceptoresByOrigen(opts.origen);

            Backbone.Validation.bind(this);
        },

        fetchInfractoresByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.infractores.setTorneoEquipo(equipo);
            this.infractores.fetch();
        },

        fetchReceptoresByOrigen: function(origen) {
            var equipo;
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.receptores.setTorneoEquipo(equipo);
            this.receptores.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#falta-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarTipoFalta: function(modelo) {
            $('#select-tipo-falta').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncTipoFaltas: function(modelo) {
            $('#select-tipo-falta').change();
        },

        agregarInfractor: function(modelo) {
            $('#select-infractor').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncInfractores: function(modelo) {
            $('#select-infractor').change();
        },

        agregarReceptor: function(modelo) {
            $('#select-receptor').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncReceptores: function(modelo) {
            $('#select-receptor').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-falta").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Falta registrada correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar la falta'
                        });
                    }
                });
            }
        },

        validateNumber: function(event){
            if ($.isNumeric($(event.currentTarget).val())) {
                if ($(event.currentTarget).val() != $(event.currentTarget).val().replace(/[^0-9\.]/g, '')) {
                    var value = $(event.currentTarget).val().replace(/[^0-9.]/g, '');
                    $(event.currentTarget).val(value);
                }else{
                    var max = parseInt($(event.currentTarget).attr('max'));
                    var min = parseInt($(event.currentTarget).attr('min'));
                    if ( $(event.currentTarget).val() > max){
                        $(event.currentTarget).val(max);
                    } else if ($(event.currentTarget).val() < min){
                        $(event.currentTarget).val(min);
                    }
                }
            }else{
                var value = $(event.currentTarget).val().replace(/[^0-9.]/g, '');
                $(event.currentTarget).val(value);
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

	return FaltaCreateView;

});