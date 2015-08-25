define([
	'jquery',
	'backbone',
	'bootstrap',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'models/TorneoModel',
	'collections/JornadaEquiposCollection',
	'collections/TorneoCanchasCollection',
	'text!templates/private/tplPartidoCreate.html'
], function($, Backbone, bootstrap, BaseView, TorneoPartidoModel,
            JornadaEquiposCollection, TorneoCanchasCollection, tplPartidoCreate){

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
            this.jornada = opts.model;
            this.torneo = new TorneoModel({ id: this.jornada.get('torneoId')});
            this.render();

            this.locales = new JornadaEquiposCollection();
            this.listenTo(this.locales, 'add', this.agregarLocal);
            this.listenTo(this.locales, 'sync', this.syncLocales);
            this.locales.setJornada(this.jornada);
            this.locales.fetch();

            this.visitas = new JornadaEquiposCollection();
            this.listenTo(this.visitas, 'add', this.agregarVisita);
            this.listenTo(this.visitas, 'sync', this.syncVisitas);
            this.visitas.setJornada(this.jornada);

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
        },

        agregarLocal: function(modelo) {
            $('#select-local').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncLocales: function() {
            $('#select-local').change();
        },

        agregarVisita: function(modelo) {
            if ($('#select-local').val() == modelo.get('id')) {
                return;
            }
            $('#select-visita').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncVisitas: function(modelo) {
            $('#select-visita').change();
        },

        agregarCancha: function(modelo) {
            $('#select-cancha').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncCanchas: function(modelo) {
            $('#select-cancha').change();
        },

        changeLocal: function(event) {
            var modelo = this.locales.get($(event.target).val());
            $('#select-visita').html('');
            this.visitas.fetch();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-torneo-partido").serializeObject();
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