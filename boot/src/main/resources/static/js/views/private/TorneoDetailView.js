define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplTorneoDetail.html'
], function($, Backbone, BaseView, tplTorneoDetail){

	var TorneoDetailView = BaseView.extend({
        template: _.template(tplTorneoDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return TorneoDetailView;

});