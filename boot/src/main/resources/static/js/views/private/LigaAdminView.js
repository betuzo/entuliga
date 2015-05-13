define([
	'jquery',
	'bootflat',
	'selecter',
	'core/BaseView',
	'text!templates/private/tplLigaAdmin.html'
], function($, bootflat, selecter, BaseView, tplLigaAdmin){

	var LigaAdminView = BaseView.extend({
        template: _.template(tplLigaAdmin),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_liga").selecter();
            return this;
        }
	});

	return LigaAdminView;

});