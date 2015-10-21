define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'text!templates/public/torneo/tplTorneoLideres.html'
], function($, bootstrap, BaseView, tplTorneoLideres){

	var TorneoLideresView = BaseView.extend({
	    el: '#torneo-lideres',
        template: _.template(tplTorneoLideres),

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

	return TorneoLideresView;

});