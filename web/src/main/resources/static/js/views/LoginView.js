define([
	'jquery',
	'core/BaseView',
    'jquerySerializeObject',
	'text!templates/tplLogin.html',
	'Session'
], function($, BaseView, jquerySerializeObject, tplLogin, Session){

	var LoginView = BaseView.extend({
        template: _.template(tplLogin),

        events: {
            'click .btn.btn-lg.btn-primary.btn-block': 'login'
        },

        initialize: function() {
            this.model = Session;
            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        login: function(){
            var data = this.$el.find("#form-login").serializeObject();
            this.model.set(data);

            if(!this.model.isValid(true)){
                return;
            }
            var user = $("#username").val();
            var pass = $("#password").val();
            var remember = $("#remember").is(":checked");
            Session.login(function(response){
                Backbone.history.navigate('admin', { trigger : true });
            }, user, pass, remember);
        }

	});

	return LoginView;

});