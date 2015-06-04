define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/tplRowTorneoEquipo.html'
], function($, _, BaseView, tplRowTorneoEquipo){

	var RowTorneoEquipoView = BaseView.extend({
        template: _.template(tplRowTorneoEquipo),
        tagName: 'tr',

        events: {
            'click #eliminar-equipo': 'deleteEquipo'
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        deleteEquipo: function() {
        }
	});

	return RowTorneoEquipoView;

});