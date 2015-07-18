define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplLigaDetail.html'
], function($, Backbone, BaseView, tplLigaDetail){

	var LigaDetailView = BaseView.extend({
        template: _.template(tplLigaDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return LigaDetailView;

});