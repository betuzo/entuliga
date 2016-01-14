define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/EquipoModel',
	'models/util/PhotoModel',
	'core/BaseView',
	'views/private/util/UploadFileView',
	'text!templates/private/tplEquipoEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject, EquipoModel,
                PhotoModel, BaseView, UploadFileView, tplEquipoEdit){

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
            var that = this;
            var photo = new PhotoModel();
            photo.set({pathLogo: this.model.get('rutaLogoEquipo')});
            photo.set({hasLogo: this.model.get('hasLogoEquipo')});
            photo.set({idLogo: this.model.get('id')});
            photo.set({nameLogo: this.model.get('logoEquipo')});
            var uploadFile = new UploadFileView({
                modelo: photo,
                callbackUpload:function (data) {
                    that.model.set({hasLogoEquipo: true});
                    that.model.set({rutaLogoEquipo: data.pathfilename});
                    that.model.set({logoEquipo: data.filename});
                    app.equipos.add(that.model);
                    $('#select-equipo').change();
                },
                callbackDelete:function (data) {
                    that.model.set({hasLogoEquipo: false});
                    that.model.set({rutaLogoEquipo: data.defaultname});
                    that.model.set({logoEquipo: ''});
                    app.equipos.add(that.model);
                    $('#select-equipo').change();
                }
            });
            $('#upload-file').html(uploadFile.render().$el);
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