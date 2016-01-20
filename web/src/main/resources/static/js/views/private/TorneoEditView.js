define([
	'jquery',
	'backbone',
	'datepicker',
	'backboneValidation',
	'jquerySerializeObject',
	'models/TorneoModel',
	'core/BaseView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplTorneoEdit.html'
], function($, Backbone, datepicker, backboneValidation, jquerySerializeObject,
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
            this.$el.find('#dp-fecha-inicio').datepicker({format: "mm/dd/yyyy"});
            this.$el.find('#dp-fecha-fin').datepicker({format: "mm/dd/yyyy"});
            return this;
        },

        saveTorneo: function(){
            var data = this.$el.find("#form-torneo").serializeObject();
            this.model.set(data);
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
                message: '¿Desea cancelar la edición?',
                callbackConfirm: function (data) {
                    app.that.disabledAction(false);
                    app.that.destroyView();
                    delete app.that;
                }
            });
        },

        saveTorneoSuccess: function(model, response, options){
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
        },

        saveTorneoError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el Torneo'
            });
        }
	});

	return TorneoEditView;

});