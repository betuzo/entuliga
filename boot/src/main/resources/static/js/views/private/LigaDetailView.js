define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/LigaModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'text!templates/private/tplLigaDetail.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            LigaModel, BaseView, MainColoniaAdminView, tplLigaDetail){

	var LigaDetailView = BaseView.extend({
        template: _.template(tplLigaDetail),

        events: {
            'click #colonia-buscar': 'buscarColonia',
            'click #btn-ok': 'saveLiga'
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
            this.$el.html(this.template());
            return this;
        },

        buscarColonia: function() {
            new MainColoniaAdminView(this.selectColonia);
        },

        selectColonia: function(colonia) {
            $('#liga-colonia-id').val(colonia.get('id'));
            $('#liga-colonia-desc').val(colonia.get('nombre'));
        },

        saveLiga: function(){
            var data = this.$el.find("#form-liga").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveLigaSuccess: function(model, response, options){
            console.log('Successfully saved!');
            alert('Great Success!');
        },

        saveLigaError: function(model, response, options){
            console.log(model.toJSON());
            console.log('error.responseText');
        }
	});

	return LigaDetailView;

});