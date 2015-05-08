define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'text!templates/tplLogin.html',
	'Session'
], function($, bootstrap, BaseView, tplLogin, Session){

	var LoginView = BaseView.extend({
        template: _.template(tplLogin),

        events: {
            'click .btn.btn-lg.btn-primary.btn-block': 'login'
        },

        initialize: function() {;
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        login: function(){
            var user = $("#j_username").val();
            var pass = $("#j_password").val();
            Session.login(function(response){
                $("#logout-hotel").show();
                Backbone.history.navigate('', { trigger : true });
            }, user, pass);
        }

	});

	return LoginView;

});