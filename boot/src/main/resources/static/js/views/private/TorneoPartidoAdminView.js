define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'collections/TorneoJornadasCollection',
	'collections/TorneoPartidosCollection',
    'views/private/RowTorneoPartidoView',
    'views/private/PartidoCreateView',
	'text!templates/private/tplTorneoPartidoAdmin.html'
], function($, Backbone, BaseView, TorneoPartidoModel, TorneoJornadasCollection,
            TorneoPartidosCollection, RowTorneoPartidoView, PartidoCreateView, tplTorneoPartidoAdmin){

	var TorneoPartidoAdminView = BaseView.extend({
        template: _.template(tplTorneoPartidoAdmin),

        events: {
            'click #partido-agregar': 'agregarPartido',
            'change #select-torneo-jornada' : 'changeJornada'
        },

        initialize: function() {
            this.torneojornadas = new TorneoJornadasCollection();
            this.torneojornadas.setTorneo(this.model);
            this.listenTo(this.torneojornadas, 'add', this.agregarTorneoJornada);
            this.listenTo(this.torneojornadas, 'sync', this.syncTorneoJornadas);

            this.torneojornadas.fetch();

            app.torneopartidos = new TorneoPartidosCollection();
            this.listenTo(app.torneopartidos, 'add', this.agregarTorneoPartido);
            this.listenTo(app.torneopartidos, 'sync', this.syncTorneoPartidos);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarTorneoJornada: function(modelo) {
            $('#select-torneo-jornada').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre') + ' - ' + modelo.get('fase')
            }));
        },

        syncTorneoJornadas: function() {
            $('#select-torneo-jornada').change();
        },

        changeJornada: function(event) {
            var modelo = this.torneojornadas.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.torneoJornada = modelo;
                $("#torneo-partidos").find('tbody').html('');
                app.torneopartidos.setTorneoJornada(this.torneoJornada);
                app.torneopartidos.fetch();
            }
        },

        agregarTorneoPartido: function(modelo) {
            var vista = new RowTorneoPartidoView(modelo);
            $("#torneo-partidos").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoPartidos: function() {
        },

        agregarPartido: function() {
            this.partidoCreateView = new PartidoCreateView({modelo: this.torneoJornada, callbackAceptar: this.selectPartido});
        },

        selectPartido: function(partido) {
            this.destroyView();
            $("<div id='modal-equipo-create'></div>").appendTo('#torneo-partidos');
            app.torneojugadores.add(partido);
        },

        destroyView: function() {
            // COMPLETELY UNBIND THE VIEW
            this.undelegateEvents();
            this.$el.removeData().unbind();
            // Remove view from DOM
            this.remove();
            Backbone.View.prototype.remove.call(this);
        }
	});

	return TorneoPartidoAdminView;

});