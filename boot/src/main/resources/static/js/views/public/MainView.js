define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/TorneosCollection',
	'text!templates/public/tplMain.html'
], function($, bootstrap, BaseView, TorneosCollection, tplMain){

	var MainView = BaseView.extend({
        template: _.template(tplMain),

        events: {
        },

        initialize: function() {
            this.torneos = new TorneosCollection();
            this.listenTo(this.torneos, 'add', this.agregarTorneo);
            this.listenTo(this.torneos, 'sync', this.syncTorneos);

            this.torneos.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarTorneo: function(modelo) {
        },

        syncTorneos: function() {
        }
	});

	return MainView;

});