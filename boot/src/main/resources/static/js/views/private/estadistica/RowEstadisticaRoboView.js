define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/estadistica/tplRowEstadisticaRobo.html'
], function($, _, BaseView, tplRowEstadisticaRobo){

	var RowEstadisticaRoboView = BaseView.extend({
        template: _.template(tplRowEstadisticaRobo),
        tagName: 'tr',

        events: {
            'click #eliminar-robo': 'deleteRobo'
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

        deleteRobo: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    that.parent.successRemoveRobo(model);
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

	return RowEstadisticaRoboView;

});