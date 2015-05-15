define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplLigaDetail.html'
], function($, Backbone, BaseView, tplLigaDetail){

	var LigaDetailEdit = BaseView.extend({
        template: _.template(tplLigaDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        }
	});

	return LigaDetailEdit;

});