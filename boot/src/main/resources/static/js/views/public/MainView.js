define([
	'jquery',
	'bootstrap',
	'jqueryValidate',
	'entuliga',
	'core/BaseView',
	'text!templates/public/tplMain.html'
], function($, bootstrap, jqueryValidate, entuliga, BaseView, tplMain){

	var MainView = BaseView.extend({
        template: _.template(tplMain),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            $('nav').hide();
            return this;
        }
	});

	return MainView;

});