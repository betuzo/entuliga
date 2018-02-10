define([
    'jquery',
    'backbone',
    'bootstrap',
    'core/BaseView',
    'models/estadistica/PuntoModel',
    'models/TorneoEquipoModel',
    'collections/TorneoJugadoresCollection',
    'collections/estadistica/TipoEncestesCollection',
    'views/private/util/ModalGenericView',
    'text!templates/private/estadistica/tplPuntoCreate.html'
], function($, Backbone, bootstrap, BaseView, PuntoModel, TorneoEquipoModel,
            TorneoJugadoresCollection, TipoEncestesCollection, ModalGenericView,
            tplPuntoCreate){

    var PuntoCreateView = BaseView.extend({
        el: '#modal-partido',
        template: _.template(tplPuntoCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar',
            'change .validate-number':'validateNumber',
            'keyup .validate-number':'validateNumber'
        },

        initialize: function(opts) {
            this.model = new PuntoModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.tipos = new TipoEncestesCollection();
            this.listenTo(this.tipos, 'add', this.agregarTipoEnceste);
            this.listenTo(this.tipos, 'sync', this.syncTipoEncestes);
            this.tipos.fetch();

            this.tiradores = new TorneoJugadoresCollection();
            this.listenTo(this.tiradores, 'add', this.agregarTirador);
            this.listenTo(this.tiradores, 'sync', this.syncTiradores);
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
            this.tiradores.setTorneoEquipo(equipo);
            this.tiradores.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#punto-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarTipoEnceste: function(modelo) {
            $('#select-tipo-enceste').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncTipoEncestes: function(modelo) {
            $('#select-tipo-enceste').change();
        },

        agregarTirador: function(modelo) {
            $('#select-tirador').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncTiradores: function(modelo) {
            $('#select-tirador').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-punto").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Punto registrado correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar el punto'
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

    return PuntoCreateView;

});