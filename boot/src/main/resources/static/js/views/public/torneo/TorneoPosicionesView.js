define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'text!templates/public/torneo/tplTorneoPosiciones.html'
], function($, bootstrap, BaseView, tplTorneoPosiciones){

	var TorneoPosicionesView = BaseView.extend({
	    el: '#torneo-posiciones',
        template: _.template(tplTorneoPosiciones),

        events: {
        },

        initialize: function(modelo) {
            this.model = modelo;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        }
	});

	return TorneoPosicionesView;

});