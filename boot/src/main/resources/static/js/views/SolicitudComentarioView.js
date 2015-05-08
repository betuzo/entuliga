define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/tplSolicitudComentario.html'
], function($, _, BaseView, tplSolicitudComentario){

	var SolicitudComentarioView = BaseView.extend({
        template: _.template(tplSolicitudComentario),

        events: {
        },

        initialize: function() {
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return SolicitudComentarioView;

});