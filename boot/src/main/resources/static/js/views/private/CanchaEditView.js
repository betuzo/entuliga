define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/CanchaModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'text!templates/private/tplCanchaEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            CanchaModel, BaseView, MainColoniaAdminView, tplCanchaEdit){

	var CanchaEditView = BaseView.extend({
        template: _.template(tplCanchaEdit),

        events: {
            'click #colonia-buscar': 'buscarColonia',
            'click #btn-ok': 'saveCancha'
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

        saveCancha: function(){
            var data = this.$el.find("#form-cancha").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveCanchaSuccess: function(model, response, options){
            console.log('Successfully saved!');
            alert('Great Success!');
        },

        saveCanchaError: function(model, response, options){
            console.log(model.toJSON());
            console.log('error.responseText');
        }
	});

	return CanchaEditView;

});