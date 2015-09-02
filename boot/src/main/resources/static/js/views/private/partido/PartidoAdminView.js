define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'views/private/partido/PartidoLocalView',
	'views/private/partido/PartidoVisitaView',
	'text!templates/private/partido/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, TorneoPartidoModel,
            PartidoLocalView, PartidoVisitaView, tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {

        },

        initialize: function(idPartido) {
            this.model = new TorneoPartidoModel();
            this.listenTo(this.model, 'sync', this.syncPartido);
        },

        render: function() {
            return this;
        },

        syncPartido: function() {
            this.$el.html(this.template(this.model.toJSON()));

            new PartidoLocalView(this.model);
            new PartidoVisitaView(this.model);
        },

        setIdPartido: function(idPartido) {
            this.model.set('id', idPartido);
            this.model.fetch();
        }
	});

	return PartidoAdminView;

});