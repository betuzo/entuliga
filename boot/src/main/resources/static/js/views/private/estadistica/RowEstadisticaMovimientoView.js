define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/estadistica/tplRowEstadisticaMovimiento.html'
], function($, _, BaseView, tplRowEstadisticaMovimiento){

	var RowEstadisticaMovimientoView = BaseView.extend({
        template: _.template(tplRowEstadisticaMovimiento),
        tagName: 'tr',

        events: {
            'click #eliminar-movimiento': 'deleteMovimiento'
        },

        initialize: function(opts) {
            this.model =  opts.modelo;
            this.parent = opts.parent;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$el.addClass( this.model.get('origenClass') );
            return this;
        },

        deleteMovimiento: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    that.parent.successRemoveMovimiento(model);
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

	return RowEstadisticaMovimientoView;

});