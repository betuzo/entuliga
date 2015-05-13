define([
	'jquery',
	'core/BaseView',
	'text!templates/public/tplMain.html'
], function($, BaseView, tplMain){

	var MainView = BaseView.extend({
        template: _.template(tplMain),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        }
	});

	return MainView;

});