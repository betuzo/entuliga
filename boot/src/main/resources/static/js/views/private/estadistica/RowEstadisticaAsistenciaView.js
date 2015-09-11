define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/estadistica/tplRowEstadisticaAsistencia.html'
], function($, _, BaseView, tplRowEstadisticaAsistencia){

	var RowEstadisticaAsistenciaView = BaseView.extend({
        template: _.template(tplRowEstadisticaAsistencia),
        tagName: 'tr',

        events: {
            'click #eliminar-asistencia': 'deleteAsistencia'
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

        deleteAsistencia: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    that.parent.successRemoveAsistencia(model);
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

	return RowEstadisticaAsistenciaView;

});