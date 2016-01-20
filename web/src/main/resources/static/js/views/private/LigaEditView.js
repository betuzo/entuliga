define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/LigaModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplLigaEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            LigaModel, BaseView, MainColoniaAdminView, ModalGenericView, tplLigaEdit){

	var LigaEditView = BaseView.extend({
        template: _.template(tplLigaEdit),

        events: {
            'click #colonia-buscar' : 'buscarColonia',
            'click #btn-ok'         : 'saveLiga',
            'click #btn-cancel'     : 'cancelLiga'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new LigaModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveLigaSuccess);
            this.model.once("error", this.saveLigaError);
            Backbone.Validation.bind(this);
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
            $('#liga-colonia-id').val(colonia.get('id'));
            $('#liga-colonia-desc').val(colonia.get('nombre'));
        },

        cancelLiga: function(){
            var that = this;
            new ModalGenericView({
                type: 'confirm',
                labelConfirm: 'Si',
                labelCancel: 'No',
                message: '¿Desea cancelar la edición?',
                callbackConfirm: function (data) {
                    that.destroyView();
                }
            });
        },

        saveLiga: function(){
            var data = this.$el.find("#form-liga").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveLigaSuccess: function(model, response, options){
            app.ligas.add(model);
            console.log('Successfully saved!');
            new ModalGenericView({
                message: 'Liga registrada correctamente'
            });
        },

        saveLigaError: function(model, response, options){
            console.log(model.toJSON());
            new ModalGenericView({
                message: 'Se presento un error al registrar la Liga'
            });
        }
	});

	return LigaEditView;

});