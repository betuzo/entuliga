define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'views/private/partido/RowPartidoTorneoJugadorView',
	'text!templates/private/partido/tplPartidoLocal.html'
], function($, Backbone, BaseView, TorneoPartidoModel, TorneoEquipoModel,
            TorneoJugadoresCollection, RowPartidoTorneoJugadorView, tplPartidoLocal){

	var PartidoLocalView = BaseView.extend({
	    el: '#section-local',
        template: _.template(tplPartidoLocal),

        events: {

        },

        initialize: function(modelo) {
            this.model = modelo;
            this.locales = new TorneoJugadoresCollection();
            this.listenTo(this.locales, 'add', this.agregarLocal);
            this.listenTo(this.locales, 'sync', this.syncLocales);

            var local = new TorneoEquipoModel({ id : this.model.get('localId') });
            this.locales.setTorneoEquipo(local);
            this.locales.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        agregarLocal: function(model) {
            var vista = new RowPartidoTorneoJugadorView(model);
            $("#locales").find('tbody:last').append(vista.render().$el);
        },

        syncLocales: function() {
        }
	});

	return PartidoLocalView;

});