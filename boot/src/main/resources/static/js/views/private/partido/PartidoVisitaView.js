define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'views/private/partido/RowPartidoTorneoJugadorView',
	'text!templates/private/partido/tplPartidoVisita.html'
], function($, Backbone, BaseView, TorneoPartidoModel, TorneoEquipoModel,
            TorneoJugadoresCollection, RowPartidoTorneoJugadorView, tplPartidoVisita){

	var PartidoVisitaView = BaseView.extend({
	    el: '#section-visita',
        template: _.template(tplPartidoVisita),

        events: {

        },

        initialize: function(modelo) {
            this.model = modelo;
            this.visitantes = new TorneoJugadoresCollection();
            this.listenTo(this.visitantes, 'add', this.agregarVisitante);
            this.listenTo(this.visitantes, 'sync', this.syncVisitantes);
            this.render();

            var visita = new TorneoEquipoModel({ id : this.model.get('visitaId') });
            this.visitantes.setTorneoEquipo(visita);
            this.visitantes.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarVisitante: function(model) {
            var vista = new RowPartidoTorneoJugadorView(model);
            $("#visitantes").find('tbody:last').append(vista.render().$el);
        },

        syncVisitantes: function() {
        }
	});

	return PartidoVisitaView;

});