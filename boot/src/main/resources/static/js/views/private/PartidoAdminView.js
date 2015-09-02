define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'text!templates/private/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, TorneoPartidoModel,
            TorneoEquipoModel, TorneoJugadoresCollection, tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {

        },

        initialize: function(idPartido) {
            this.model = new TorneoPartidoModel();
            this.listenTo(this.model, 'sync', this.syncPartido);

            this.locales = new TorneoJugadoresCollection();
            this.listenTo(this.locales, 'add', this.agregarLocal);
            this.listenTo(this.locales, 'sync', this.syncLocales);

            this.visitantes = new TorneoJugadoresCollection();
            this.listenTo(this.visitantes, 'add', this.agregarVisitante);
            this.listenTo(this.visitantes, 'sync', this.syncVisitantes);
        },

        render: function() {
            return this;
        },

        syncPartido: function() {
            this.$el.html(this.template(this.model.toJSON()));

            local = new TorneoEquipoModel({ id : this.model.get('localId') });
            visita = new TorneoEquipoModel({ id : this.model.get('visitaId') });

            this.locales.setTorneoEquipo(local);
            this.locales.fetch();

            this.visitantes.setTorneoEquipo(visita);
            this.visitantes.fetch();
        },

        setIdPartido: function(idPartido) {
            this.model.set('id', idPartido);
            this.model.fetch();
        },

        agregarLocal: function(model) {
        },

        syncLocales: function() {
        },

        agregarVisitante: function(model) {
        },

        syncVisitantes: function() {
        }
	});

	return PartidoAdminView;

});