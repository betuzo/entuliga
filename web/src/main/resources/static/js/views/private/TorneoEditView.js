define([
	'jquery',
	'backbone',
	'datetimepicker',
	'backboneValidation',
	'jquerySerializeObject',
	'models/TorneoModel',
	'core/BaseView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplTorneoEdit.html'
], function($, Backbone, datetimepicker, backboneValidation, jquerySerializeObject,
            TorneoModel, BaseView, ModalGenericView, tplTorneoEdit){

	var TorneoEditView = BaseView.extend({
        template: _.template(tplTorneoEdit),

        events: {
            'click #btn-ok'         : 'saveTorneo',
            'click #btn-cancel'     : 'cancelTorneo'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new TorneoModel();
                this.model.set({ligaId: opts.idLiga});
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveTorneoSuccess);
            this.model.once("error", this.saveTorneoError);
            Backbone.Validation.bind(this);
            app.that = this;
        },

        remove: function() {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$el.find('#dp-fecha-inicio').datetimepicker({
                format: "mm/dd/yyyy",
                pickTime: false,
                autoclose: true,
                startView: 'month',
                minView: 'month'
            });
            this.$el.find('#dp-fecha-fin').datetimepicker({
                format: "mm/dd/yyyy",
                pickTime: false,
                autoclose: true,
                startView: 'month',
                minView: 'month'
            });

            return this;
        },

        saveTorneo: function(){
            var data = this.$el.find("#form-torneo").serializeObject();
            this.model.set(data);
						for (var k in data){
							if (data.hasOwnProperty(k)) {
								data[k] = _.escape(data[k]);
							}
						}
						var fechaInicio = new Date(this.model.get('fechaInicioDes'));
            var fechaFin = new Date(this.model.get('fechaFinDes'));
            this.model.set({fechaInicio: fechaInicio.getTime(), fechaFin: fechaFin.getTime()});

						if(this.model.isValid(true)){
                this.model.save();
            }

        },

        cancelTorneo: function(){
            new ModalGenericView({
                type: 'confirm',
                labelConfirm: 'Si',
                labelCancel: 'No',
                message: '¿Desea cancelar la acción?',
                callbackConfirm: function (data) {
                    app.that.disabledAction(false);
                    app.that.destroyView();
                    delete app.that;
                }
            });
        },

        saveTorneoSuccess: function(model, response, options){
						for (var m in model.attributes) {
							if (model.attributes.hasOwnProperty(m)) {
								model.attributes[m] = _.escape(model.attributes[m])
							}
						}

            app.torneos.add(model);
            if (typeof app.that === 'undefined') {
                return;
            }
            app.that.disabledAction(false);
            new ModalGenericView({
                message: 'Torneo registrado correctamente'
            });
            app.that.destroyView();
            delete app.that;
            if(app.torneos.length == 1){
                $('#select-torneo').change();
            }
        },

        saveTorneoError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el Torneo'
            });
        }
	});

	return TorneoEditView;

});
