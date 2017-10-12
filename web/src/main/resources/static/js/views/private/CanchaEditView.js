define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/CanchaModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplCanchaEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            CanchaModel, BaseView, MainColoniaAdminView, ModalGenericView,
            tplCanchaEdit){

	var CanchaEditView = BaseView.extend({
        template: _.template(tplCanchaEdit),

        events: {
            'click #colonia-buscar' : 'buscarColonia',
            'click #btn-ok'         : 'saveCancha',
            'click #btn-cancel'     : 'cancelCancha'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new CanchaModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveCanchaSuccess);
            this.model.once("error", this.saveCanchaError);
            Backbone.Validation.bind(this);
            app.that = this;
        },

        remove: function() {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        buscarColonia: function() {
            new MainColoniaAdminView(this.selectColonia);
        },

        selectColonia: function(colonia) {
            $('#cancha-colonia-id').val(colonia.get('id'));
            $('#cancha-colonia-desc').val(colonia.get('nombre'));
        },

        cancelCancha: function(){
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

        saveCancha: function(){
            var data = this.$el.find("#form-cancha").serializeObject();
						for (var k in data){
							console.log(k);
							if (data.hasOwnProperty(k)) {
								data[k] = _.escape(data[k]);
							}
						}
						this.model.set(data);
            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveCanchaSuccess: function(model, response, options){
            app.canchas.add(model);
            if (typeof app.that === 'undefined') {
                return;
            }
            app.that.disabledAction(false);
            new ModalGenericView({
                message: 'Cancha guardada correctamente'
            });
            app.that.destroyView();
            delete app.that;
            if(app.canchas.length == 1){
                $('#select-cancha').change();
            }
        },

        saveCanchaError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar la cancha'
            });
        }
	});

	return CanchaEditView;

});
