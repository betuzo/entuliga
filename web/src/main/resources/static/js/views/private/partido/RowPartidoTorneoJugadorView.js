define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/partido/tplRowPartidoTorneoJugador.html'
], function($, _, BaseView, tplRowPartidoTorneoJugador){

	var RowPartidoTorneoJugadorView = BaseView.extend({
        template: _.template(tplRowPartidoTorneoJugador),
        tagName: 'tr',

        events: {
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        destroyView: function() {
            // COMPLETELY UNBIND THE VIEW
            this.undelegateEvents();
            this.$el.removeData().unbind();
            // Remove view from DOM
            this.remove();
            Backbone.View.prototype.remove.call(this);
        }
	});

	return RowPartidoTorneoJugadorView;

});