define([
	'jquery',
	'core/BaseView',
	'text!templates/tplSignup.html',
	'Session'
], function($, BaseView, tplSignup, Session){

	var SignupView = BaseView.extend({
        template: _.template(tplSignup),

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
                Backbone.history.navigate('admin', { trigger : true });
            }, user, pass);
        }

	});

	return SignupView;

});