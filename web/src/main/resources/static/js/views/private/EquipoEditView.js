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
            app.equipoEdit = this;
            var equipoId = this.model.get('id');
            var logo = {};
            var initPre = [];
            var initPreConfig = [ {caption: this.model.get('aliasEquipo'), width: "120px", url: "file/delete", key: equipoId} ];

            if (this.model.get('hasLogoEquipo')) {
                logo = { logo: this.model.get('logoEquipo') };
                initPre = ["<img src='" + this.model.get('rutaLogoEquipo') + "'>"];
            }

            $('#file-es').fileinput({
                language: 'es',
                uploadUrl: 'file/upload',
                previewFileType: "image",
                browseClass: "btn btn-success",
                browseLabel: "Pick Image",
                browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
                removeClass: "btn btn-danger",
                removeLabel: "Delete",
                removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
                uploadClass: "btn btn-info",
                uploadLabel: "Upload",
                uploadIcon: "<i class=\"glyphicon glyphicon-upload\"></i> ",
                maxFileCount: 1,
                initialPreview: initPre,
                initialPreviewConfig: initPreConfig,
                uploadExtraData: { equipoId: equipoId },
                deleteExtraData: logo
            });

            $('#file-es').on('fileuploaded', function(event, data, previewId, index) {
                var form = data.form, files = data.files, extra = data.extra,
                    response = data.response, reader = data.reader;
                console.log('File uploaded triggered');
                app.equipoEdit.model.set({hasLogoEquipo: true});
                app.equipoEdit.model.set({rutaLogoEquipo: data.response.filename});
            });

            $('#file-es').on('filedeleted', function(event, id, index) {
                console.log('fileremoved id = ' + id);
                app.equipoEdit.model.set({hasLogoEquipo: false});
                app.equipoEdit.model.set({rutaLogoEquipo: index.responseJSON.defaultname});
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