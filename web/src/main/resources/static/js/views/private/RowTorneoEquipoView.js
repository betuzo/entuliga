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

	return RowTorneoEquipoView;

});