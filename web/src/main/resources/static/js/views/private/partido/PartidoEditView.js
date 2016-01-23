define([
	'jquery',
	'backbone',
    'bootstrap',
    'datetimepicker',
	'core/BaseView',
	'models/TorneoModel',
    'collections/TorneoCanchasCollection',
    'views/private/util/ModalGenericView',
	'text!templates/private/partido/tplPartidoEdit.html'
], function($, Backbone, bootstrap, datetimepicker, BaseView, TorneoModel,
            TorneoCanchasCollection, ModalGenericView, tplPartidoEdit){

	var PartidoEditView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplPartidoEdit),

        events: {
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(opts) {
            this.model = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.render();

            this.canchas = new TorneoCanchasCollection();
            this.listenTo(this.canchas, 'add', this.agregarCancha);
            this.listenTo(this.canchas, 'sync', this.syncCanchas);
            var torneo = new TorneoModel({ id : this.model.get('torneoId') });
            this.canchas.setTorneo(torneo);
            this.canchas.fetch();

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#partido-create-dialog').modal('show');
            this.$('.alert-danger').hide();
            this.$el.find('#dtp-horario').datetimepicker({
                format: "mm/dd/yyyy hh:ii"
            }).on('show', function(ev) {
                 $('.datepicker.dropdown-menu').addClass('ui-datepicker-partido');
            });
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
                        new ModalGenericView({message: 'Partido registrado correctamente'});
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({message: 'Se presento un error al registrar el partido'});
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
            $("<div id='modal-partido-parent'></div>").appendTo('#modal-partido');
        }
	});

	return PartidoEditView;

});