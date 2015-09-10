define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/estadistica/tplRowEstadisticaPunto.html'
], function($, _, BaseView, tplRowEstadisticaPunto){

	var RowEstadisticaPuntoView = BaseView.extend({
        template: _.template(tplRowEstadisticaPunto),
        tagName: 'tr',

        events: {
            'click #eliminar-punto': 'deletePunto'
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

        deletePunto: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    that.parent.successRemovePunto(model);
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

	return RowEstadisticaPuntoView;

});