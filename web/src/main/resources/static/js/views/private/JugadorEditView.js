define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/JugadorModel',
	'models/util/PhotoModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'views/private/util/UploadFileView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplJugadorEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            JugadorModel, PhotoModel, BaseView, MainColoniaAdminView,
            UploadFileView, ModalGenericView, tplJugadorEdit){

	var JugadorEditView = BaseView.extend({
        template: _.template(tplJugadorEdit),

        events: {
            'click #colonia-buscar' : 'buscarColonia',
            'click #btn-ok'         : 'saveJugador',
            'click #btn-cancel'     : 'cancelJugador'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new JugadorModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveJugadorSuccess);
            this.model.once("error", this.saveJugadorError);
            Backbone.Validation.bind(this);
            app.that = this;
        },

        setUpNew: function() {
            $('#upload-file').html('<img class="avatar avatar-sm" src="photo/jugador/jugador-default.png">');
        },

        setUpEdit: function() {
            var that = this;
            var photo = new PhotoModel();
            photo.set({pathLogo: this.model.get('rutaLogoJugador')});
            photo.set({hasLogo: this.model.get('hasLogoJugador')});
            photo.set({idLogo: this.model.get('id')});
            photo.set({nameLogo: this.model.get('logoJugadpr')});
            var uploadFile = new UploadFileView({
                modelo: photo,
                urlUpload: 'file/upload/foto',
                urlDelete: 'file/delete/foto',
                callbackUpload:function (data) {
                    that.model.set({hasLogoJugador: true});
                    that.model.set({rutaLogoJugador: data.pathfilename});
                    that.model.set({logoJugadpr: data.filename});
                    app.jugadores.add(that.model);
                    $('#select-jugador').change();
                },
                callbackDelete:function (data) {
                    that.model.set({hasLogoJugador: false});
                    that.model.set({rutaLogoJugador: data.defaultname});
                    that.model.set({logoJugadpr: ''});
                    app.jugadores.add(that.model);
                    $('#select-jugador').change();
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

        buscarColonia: function() {
            new MainColoniaAdminView(this.selectColonia);
        },

        selectColonia: function(colonia) {
            $('#jugador-colonia-id').val(colonia.get('id'));
            $('#jugador-colonia-desc').val(colonia.get('nombre'));
        },

        cancelJugador: function(){
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

        saveJugador: function(){
            var data = this.$el.find("#form-jugador").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveJugadorSuccess: function(model, response, options){
            app.jugadores.add(model);
            if (typeof app.that === 'undefined') {
                return;
            }
            app.that.disabledAction(false);
            new ModalGenericView({
                message: 'Jugador registrado correctamente'
            });
            app.that.destroyView();
            delete app.that;
            if(app.jugadores.length == 1){
                $('#select-jugador').change();
            }
        },

        saveJugadorError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el Jugador'
            });
        }
	});

	return JugadorEditView;

});