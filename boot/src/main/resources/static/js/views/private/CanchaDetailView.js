define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplCanchaDetail.html'
], function($, Backbone, BaseView, tplCanchaDetail){

	var CanchaDetailView = BaseView.extend({
        template: _.template(tplCanchaDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return CanchaDetailView;

});