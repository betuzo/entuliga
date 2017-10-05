define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/UserModel',
    'views/private/util/ModalGenericView',
	'text!templates/tplSignup.html'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, UserModel, ModalGenericView, tplSignup){

	var SignupView = BaseView.extend({
        template: _.template(tplSignup),

        events: {
            'click #btn-ok'         : 'signup',
            'focus .signup-username' : 'showDetailsUsername',
            'focusout .signup-username' : 'hideDetailsUsername',
            'focus .signup-pass' : 'showDetailsPass',
            'focusout .signup-pass' : 'hideDetailsPass',
            'focus .signup-pass-confirm' : 'showDetailsPassConfirm',
            'focusout .signup-pass-confirm' : 'hideDetailsPassConfirm'
        },


        initialize: function() {
            this.model = new UserModel();
            this.model.once("sync", this.saveUserSuccess);
            this.model.once("error", this.saveUserError);
            Backbone.Validation.bind(this, {
                valid: function(view, attr, selector) {
                    var $el = view.$('[name=' + attr + ']'),  $group = $el.closest('.form-group');
                    if (view.model.preValidate(attr, $el.val())) {
                        return;
                    }
                    $group.removeClass('has-error');
                    $group.find('.help-block').html('').addClass('hidden');
                },

                invalid: function(view, attr, error, selector) {
                    var $el = view.$('[name=' + attr + ']'),
                    $group = $el.closest('.form-group');
                    $group.addClass('has-error');
                    $group.find('.help-block').html(error).removeClass('hidden');
                }
            });
            app.that = this;

        },


        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        signup: function(){
            var data = this.$el.find("#form-user").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveUserSuccess: function(model, response, options){
            if (typeof app.that === 'undefined') {
                return;
            }
            new ModalGenericView({
                message: 'Revise su email, para confirmar su registro'
            });
            app.that.destroyView();
            delete app.that;
        },

        saveUserError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el usuario'
            });
        },

        showDetailsUsername : function() {
            this.$(".signup-username").popover({
                content: 'Ingresa tu dirección de correo electronico.',
                placement: 'left'
            });
            this.$(".signup-username").popover('show');
        },
        hideDetailsUsername : function() {
            this.$(".signup-username").popover('hide');
        },

        showDetailsPass : function() {
            this.$(".signup-pass").popover({
                content: 'El password debe tener por lo menos una letra mayuscula, una minuscula y un numero',
                placement: 'left'
            });

            this.$(".signup-pass").popover('show');
        },
        hideDetailsPass : function() {
            this.$(".signup-pass").popover('hide');
        },

        showDetailsPassConfirm : function() {
            this.$(".signup-pass-confirm").popover({
                content: 'Confirmar password',
                placement: 'left'
            });
            this.$(".signup-pass-confirm").popover('show');
        },
        hideDetailsPassConfirm : function() {
            this.$(".signup-pass-confirm").popover('hide');
        }
	});

	return SignupView;

});
