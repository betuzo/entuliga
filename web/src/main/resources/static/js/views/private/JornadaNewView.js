define([
	'jquery',
	'backbone',
	'bootstrap',
	'core/BaseView',
	'models/TorneoJornadaModel',
	'collections/FasesCollection',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplJornadaNew.html'
], function($, Backbone, bootstrap, BaseView, TorneoJornadaModel,
            FasesCollection, ModalGenericView, tplJornadaNew){

	var EquipoSearchView = BaseView.extend({
	    el: '#modal-jornada-new',
        template: _.template(tplJornadaNew),

        events: {
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = new TorneoJornadaModel();
            this.torneo = opts.modelo;
            this.render();

            this.fases = new FasesCollection();
            this.listenTo(this.fases, 'add', this.agregarFase);
            this.listenTo(this.fases, 'sync', this.syncFases);
            this.fases.fetch();

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#jornada-new-dialog').modal('show');
            this.$('.alert-danger').hide();
            this.$('#torneo-liga-id').val(this.torneo.get('id'));
        },

        agregarFase: function(modelo) {
            $('#select-fase').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncFases: function(modelo) {
            $('#select-fase').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-torneo-jornada").serializeObject();
						for (var k in data){
							console.log(k);
							if (data.hasOwnProperty(k)) {
								data[k] = _.escape(data[k]);
							}
						}

						this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Jornada registrada correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar la jornada'
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

	return EquipoSearchView;

});
