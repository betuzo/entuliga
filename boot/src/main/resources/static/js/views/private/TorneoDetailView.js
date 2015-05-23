define([
	'jquery',
	'backbone',
	'core/BaseView',
	'views/private/TorneoEquipoAdminView',
	'text!templates/private/tplTorneoDetail.html'
], function($, Backbone, BaseView, TorneoEquipoAdminView, tplTorneoDetail){

	var TorneoDetailView = BaseView.extend({
        template: _.template(tplTorneoDetail),

        events: {
            'click #torneo-jornada': 'viewJornada',
            'click #toreno-equipo': 'viewEquipo'
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        viewJornada: function() {

        },

        viewEquipo: function() {
            this.torneoEquipoAdminView = new TorneoEquipoAdminView({model: this.model});
            $('#torneo-edit').html(this.torneoEquipoAdminView.render().$el);
        }
	});

	return TorneoDetailView;

});