define([
	'jquery',
	'backbone',
	'bootstrap',
	'datetimepicker',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'models/TorneoModel',
	'collections/JornadaEquiposCollection',
	'collections/TorneoCanchasCollection',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplPartidoCreate.html'
], function($, Backbone, bootstrap, datetimepicker, BaseView, TorneoPartidoModel, TorneoModel,
            JornadaEquiposCollection, TorneoCanchasCollection, ModalGenericView, tplPartidoCreate){

	var PartidoCreateView = BaseView.extend({
	    el: '#modal-partido-create',
        template: _.template(tplPartidoCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar',
            'change #select-local': 'changeLocal',
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = new TorneoPartidoModel();
            this.jornada = opts.modelo;
            this.torneo = new TorneoModel({ id: this.jornada.get('torneoId')});
            this.render();

            this.locales = new JornadaEquiposCollection();
            this.listenTo(this.locales, 'add', this.agregarLocal);
            this.listenTo(this.locales, 'sync', this.syncLocales);
            this.locales.setJornada(this.jornada);

            this.visitas = new JornadaEquiposCollection();
            this.listenTo(this.visitas, 'add', this.agregarVisita);
            this.listenTo(this.visitas, 'sync', this.syncVisitas);
            this.visitas.setJornada(this.jornada);
            this.visitas.fetch();

            this.canchas = new TorneoCanchasCollection();
            this.listenTo(this.canchas, 'add', this.agregarCancha);
            this.listenTo(this.canchas, 'sync', this.syncCanchas);
            this.canchas.setTorneo(this.torneo);
            this.canchas.fetch();

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#partido-create-dialog').modal('show');
            this.$('.alert-danger').hide();
            this.$('#jornada-id').val(this.jornada.get('id'));
            this.$el.find('#dtp-horario').datetimepicker({
                format: "mm/dd/yyyy hh:ii"
            }).on('show', function(ev) {
                 $('.datepicker.dropdown-menu').addClass('ui-datepicker-partido');
            });
        },

        agregarLocal: function(modelo) {
            $('#select-local').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('equipoNombre')
            }));
        },

        syncLocales: function() {
            $('#select-local').change();
        },

        agregarVisita: function(modelo) {
        },

        syncVisitas: function(modelo) {
            this.locales.fetch();
        },

        agregarCancha: function(modelo) {
            $('#select-cancha').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('canchaNombre')
            }));
        },

        syncCanchas: function(modelo) {
            $('#select-cancha').change();
        },

        changeLocal: function(event) {
            var local = this.locales.get($(event.target).val());
            $('#select-visita').html('');
            this.visitas.each(function(modelo){
                if (local.get('id') == modelo.get('id')) {
                    return;
                }
                $('#select-visita').append($('<option>', {
                    value: modelo.get('id'),
                    text : modelo.get('equipoNombre')
                }));
            });
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-torneo-partido").serializeObject();
            this.model.set(data);
            var horario = new Date(this.model.get('horarioDes'));
            this.model.set({horario: horario.getTime()});
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Partido registrado correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar el partido'
                        });
                    }
                });
            }
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

	return PartidoCreateView;

});