define([
	'jquery',
	'backbone',
	'core/BaseView',
	'text!templates/private/tplTorneoEquipoAdmin.html'
], function($, Backbone, BaseView, tplTorneoEquipoAdmin){

	var TorneoEquipoAdminView = BaseView.extend({
        template: _.template(tplTorneoEquipoAdmin),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return TorneoEquipoAdminView;

});