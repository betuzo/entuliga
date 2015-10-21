define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/public/torneo/tplRowJornadaPartido.html'
], function($, _, BaseView, tplRowJornadaPartido){

	var RowJornadaPartidoView = BaseView.extend({
        template: _.template(tplRowJornadaPartido),
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

	return RowJornadaPartidoView;

});