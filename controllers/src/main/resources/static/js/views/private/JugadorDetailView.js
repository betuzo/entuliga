define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplJugadorDetail.html'
], function($, Backbone, BaseView, tplJugadorDetail){

	var JugadorDetailView = BaseView.extend({
        template: _.template(tplJugadorDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return JugadorDetailView;

});