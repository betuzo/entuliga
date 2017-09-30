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
            $('#liga-colonia-id').val(colonia.get('id'));
            $('#liga-colonia-desc').val(colonia.get('nombre'));
        },

        cancelLiga: function(){
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



        saveLiga: function(){
            var data = this.$el.find("#form-liga").serializeObject();

						for (var k in data){
							if (data.hasOwnProperty(k)) {
								data[k] = _.escape(data[k]);
							}
						}

            this.model.set(data);
            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveLigaSuccess: function(model, response, options){
            app.ligas.add(model);
            if (typeof app.that === 'undefined') {
                return;
            }
            app.that.disabledAction(false);
            new ModalGenericView({
                message: 'Liga guardada correctamente'
            });
            app.that.destroyView();
            delete app.that;
            if(app.ligas.length == 1){
                $('#select-liga').change();
            }
        },

        saveLigaError: function(model, error, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar la liga'
            });
        }
	});

	return LigaEditView;

});
