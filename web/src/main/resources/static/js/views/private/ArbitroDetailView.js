define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplArbitroDetail.html'
], function($, Backbone, BaseView, tplArbitroDetail){

	var ArbitroDetailView = BaseView.extend({
        template: _.template(tplArbitroDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return ArbitroDetailView;

});