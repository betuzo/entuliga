define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/UserModel',
	'text!templates/tplLogin.html',
	'Session'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, UserModel, tplLogin, Session){

	var LoginView = BaseView.extend({
        template: _.template(tplLogin),

        events: {
            'click .btn.btn-lg.btn-primary.btn-block': 'login'
        },

        initialize: function() {
            this.$el.find('.tip').tooltip();
            console.log("initialize login");
            this.model = new UserModel();

            Backbone.Validation.bind(this, {
                valid: function(view, attr, selector) {
                    var $el = view.$('[name=' + attr + ']'),  $group = $el.closest('.form-group');
                    if (view.model.preValidate(attr, $el.val())) {
                        return;
                    }
                    $group.removeClass('has-error');
                    $group.addClass('has-success');
                },

                invalid: function(view, attr, error, selector) {
                    var $el = view.$('[name=' + attr + ']'),
                    $group = $el.closest('.form-group');
                    $el.popover({
                        content: error,
                        placement: 'left'
                    });
                    $el.popover('show');
                    $group.addClass('has-error');
                }

            });
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        remove: function() {
            // Remove the validation binding
            // See: http://thedersen.com/projects/backbone-validation/#using-form-model-validation/unbinding
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        login: function(){
            var data = this.$el.find("#form-login").serializeObject();
            this.model.set(data);
            this.model.set({passwordConfirm: this.model.get('password')});

            if(this.model.isValid(true)){
                var user = this.model.get('username');
                var pass = this.model.get('password');
                var remember = $("#remember").is(":checked");
                Session.login(function(response){
                    Backbone.history.navigate('admin', { trigger : true });
                }, user, pass, remember);
            }

        }

	});

	return LoginView;

});
