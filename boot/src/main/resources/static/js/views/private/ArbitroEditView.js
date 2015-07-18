define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/ArbitroModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'text!templates/private/tplArbitroEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            ArbitroModel, BaseView, MainColoniaAdminView, tplArbitroEdit){

	var ArbitroEditView = BaseView.extend({
        template: _.template(tplArbitroEdit),

        events: {
            'click #colonia-buscar': 'buscarColonia',
            'click #btn-ok': 'saveArbitro'
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

        saveArbitro: function(){
            var data = this.$el.find("#form-arbitro").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveArbitroSuccess: function(model, response, options){
            console.log('Successfully saved!');
            alert('Great Success!');
        },

        saveArbitroError: function(model, response, options){
            console.log(model.toJSON());
            console.log('error.responseText');
        }
	});

	return ArbitroEditView;

});