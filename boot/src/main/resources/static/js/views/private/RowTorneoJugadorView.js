define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/tplRowTorneoJugador.html'
], function($, _, BaseView, tplRowTorneoJugador){

	var RowTorneoJugadorView = BaseView.extend({
        template: _.template(tplRowTorneoJugador),
        tagName: 'tr',

        events: {
            'click #eliminar-jugador': 'deleteJugador'
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        deleteJugador: function() {
        }
	});

	return RowTorneoJugadorView;

});