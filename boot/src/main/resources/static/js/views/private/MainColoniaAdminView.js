define([
	'jquery',
	'bootflat',
	'core/BaseView',
	'text!templates/private/tplMainColoniaAdmin.html'
], function($, bootflat, BaseView, tplMainColoniaAdmin){

	var MainColoniaAdminView = BaseView.extend({
	    el: '#modal-colonia',
        template: _.template(tplMainColoniaAdmin),

        events: {
        },

        initialize: function() {
            this.render();
        },

        render: function() {
            this.$el.html(this.template());
            this.$('.modal-dialog').modal();
            return this;
        }
	});

	return MainColoniaAdminView;

});