define([
	'jquery',
	'backbone',
	'core/BaseView',
	'views/private/EquipoSearchView',
	'text!templates/private/tplTorneoEquipoAdmin.html'
], function($, Backbone, BaseView, EquipoSearchView, tplTorneoEquipoAdmin){

	var TorneoEquipoAdminView = BaseView.extend({
        template: _.template(tplTorneoEquipoAdmin),

        events: {
            'click #equipo-agregar': 'agregarEquipo'
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarEquipo: function() {
            this.equipoSearchView = new EquipoSearchView({model: this.model, callback: this.selectEquipo});
        },

        selectEquipo: function() {
        }
	});

	return TorneoEquipoAdminView;

});