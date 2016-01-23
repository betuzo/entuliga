define([
	'jquery',
	'underscore',
	'core/BaseView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplRowTorneoCancha.html'
], function($, _, BaseView, ModalGenericView, tplRowTorneoCancha){

	var RowTorneoCanchaView = BaseView.extend({
        template: _.template(tplRowTorneoCancha),
        tagName: 'tr',

        events: {
            'click #eliminar-cancha': 'deleteCancha'
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        deleteCancha: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
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

	return RowTorneoCanchaView;

});