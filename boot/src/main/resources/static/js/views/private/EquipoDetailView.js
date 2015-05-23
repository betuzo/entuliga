define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplEquipoDetail.html'
], function($, Backbone, BaseView, tplEquipoDetail){

	var EquipoDetailView = BaseView.extend({
        template: _.template(tplEquipoDetail),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return EquipoDetailView;

});