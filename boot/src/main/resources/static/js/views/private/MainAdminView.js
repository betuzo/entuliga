define([
	'jquery',
	'bootflat',
	'core/BaseView',
	'text!templates/private/tplMainAdmin.html'
], function($, bootflat, BaseView, tplMainAdmin){

	var MainAdminView = BaseView.extend({
        template: _.template(tplMainAdmin),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        }
	});

	return MainAdminView;

});