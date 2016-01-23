define([
	'jquery',
	'underscore',
	'core/BaseView',
	'views/private/util/ModalGenericView',
	'text!templates/private/estadistica/tplRowEstadisticaRobo.html'
], function($, _, BaseView, ModalGenericView, tplRowEstadisticaRobo){

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
                    new ModalGenericView({message: response.message});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
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