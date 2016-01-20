define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/ArbitroModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplArbitroEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            ArbitroModel, BaseView, MainColoniaAdminView, ModalGenericView,
            tplArbitroEdit){

	var ArbitroEditView = BaseView.extend({
        template: _.template(tplArbitroEdit),

        events: {
            'click #colonia-buscar' : 'buscarColonia',
            'click #btn-ok'         : 'saveArbitro',
            'click #btn-cancel'     : 'cancelArbitro'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new ArbitroModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveArbitroSuccess);
            this.model.once("error", this.saveArbitroError);
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
            $('#arbitro-colonia-id').val(colonia.get('id'));
            $('#arbitro-colonia-desc').val(colonia.get('nombre'));
        },

        cancelArbitro: function(){
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

        saveArbitro: function(){
            var data = this.$el.find("#form-arbitro").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveArbitroSuccess: function(model, response, options){
            app.arbitros.add(model);
            if (typeof app.that === 'undefined') {
                return;
            }
            app.that.disabledAction(false);
            new ModalGenericView({
                message: 'Arbitro registrado correctamente'
            });
            app.that.destroyView();
            delete app.that;
        },

        saveArbitroError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el arbitro'
            });
        }
	});

	return ArbitroEditView;

});