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
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    alert(response.message);
                },
                error: function(model, error) {
                    alert(error);
                }
            });
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

	return RowTorneoJugadorView;

});