define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/public/torneo/tplRowEstadisticaEquipo.html'
], function($, _, BaseView, tplRowEstadisticaEquipo){

	var RowEstadisticaEquipoView = BaseView.extend({
        template: _.template(tplRowEstadisticaEquipo),
        tagName: 'tr',

        events: {
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
	});

	return RowEstadisticaEquipoView;

});