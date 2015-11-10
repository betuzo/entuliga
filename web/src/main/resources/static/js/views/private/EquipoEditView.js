define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'fileinput',
	'fileinputes',
	'models/EquipoModel',
	'core/BaseView',
	'text!templates/private/tplEquipoEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            fileinput, fileinputes, EquipoModel, BaseView, tplEquipoEdit){

	var EquipoEditView = BaseView.extend({
        template: _.template(tplEquipoEdit),

        events: {
            'click #btn-ok': 'saveEquipo'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new EquipoModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveEquipoSuccess);
            this.model.once("error", this.saveEquipoError);
            Backbone.Validation.bind(this);
        },

        setUp: function() {
            var equipoId = this.model.get('id');
            var logo = {};
            var initPre = [];
            var initPreConfig = [];

            if (this.model.get('hasLogoEquipo')) {
                logo = { logo: this.model.get('logoEquipo') };
                initPre = ["<img src='" + this.model.get('rutaLogoEquipo') + "'>"];
                initPreConfig = [ {caption: this.model.get('aliasEquipo'), url: "file/delete", key: equipoId} ];
            }

            $('#file-es').fileinput({
                language: 'es',
                uploadUrl: 'file/upload',
                allowedFileExtensions : ['jpg', 'png','gif'],
                overwriteInitial: false,
                initialPreview: initPre,
                initialPreviewConfig: initPreConfig,
                uploadExtraData: { equipoId: equipoId },
                deleteExtraData: logo
            });
        },

        remove: function() {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        saveEquipo: function(){
            var data = this.$el.find("#form-equipo").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveEquipoSuccess: function(model, response, options){
            app.equipos.add(model);
            console.log('Successfully saved!');
            alert('Great Success!');
        },

        saveEquipoError: function(model, response, options){
            console.log(model.toJSON());
            console.log('error.responseText');
        }
	});

	return EquipoEditView;

});