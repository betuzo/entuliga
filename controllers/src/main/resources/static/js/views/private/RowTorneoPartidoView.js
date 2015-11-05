define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/tplRowTorneoPartido.html'
], function($, _, BaseView, tplRowTorneoPartido){

	var RowTorneoPartidoView = BaseView.extend({
        template: _.template(tplRowTorneoPartido),
        tagName: 'tr',

        events: {
            'click #eliminar-partido': 'deletePartido'
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        deletePartido: function() {
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

	return RowTorneoPartidoView;

});