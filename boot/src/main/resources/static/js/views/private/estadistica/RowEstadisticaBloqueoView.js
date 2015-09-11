define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/estadistica/tplRowEstadisticaBloqueo.html'
], function($, _, BaseView, tplRowEstadisticaBloqueo){

	var RowEstadisticaBloqueoView = BaseView.extend({
        template: _.template(tplRowEstadisticaBloqueo),
        tagName: 'tr',

        events: {
            'click #eliminar-bloqueo': 'deleteBloqueo'
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

        deleteBloqueo: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    that.parent.successRemoveBloqueo(model);
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

	return RowEstadisticaBloqueoView;

});