define([
	'jquery',
	'underscore',
	'core/BaseView',
	'views/private/util/ModalGenericView',
	'text!templates/private/estadistica/tplRowEstadisticaRebote.html'
], function($, _, BaseView, ModalGenericView, tplRowEstadisticaRebote){

	var RowEstadisticaReboteView = BaseView.extend({
        template: _.template(tplRowEstadisticaRebote),
        tagName: 'tr',

        events: {
            'click #eliminar-rebote': 'deleteRebote'
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

        deleteRebote: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    that.parent.successRemoveRebote(model);
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

	return RowEstadisticaReboteView;

});