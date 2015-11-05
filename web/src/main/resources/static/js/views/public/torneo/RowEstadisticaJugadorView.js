define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/public/torneo/tplRowEstadisticaJugador.html'
], function($, _, BaseView, tplRowEstadisticaJugador){

	var RowEstadisticaJugadorView = BaseView.extend({
        template: _.template(tplRowEstadisticaJugador),
        tagName: 'tr',

        events: {
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return RowEstadisticaJugadorView;

});