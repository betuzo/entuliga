define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoJornadaModel',
	'collections/TorneoJornadasCollection',
	'views/private/JornadaNewView',
	'views/private/RowTorneoJornadaView',
	'text!templates/private/tplTorneoJornadaAdmin.html'
], function($, Backbone, BaseView, TorneoJornadaModel, TorneoJornadasCollection,
            JornadaNewView, RowTorneoJornadaView, tplTorneoJornadaAdmin){

	var TorneoJornadaAdminView = BaseView.extend({
        template: _.template(tplTorneoJornadaAdmin),

        events: {
            'click #jornada-agregar': 'agregarJornada'
        },

        initialize: function() {
            app.torneojornadas = new TorneoJornadasCollection();
            app.torneojornadas.setTorneo(this.model);
            this.listenTo(app.torneojornadas, 'add', this.agregarTorneoJornada);
            this.listenTo(app.torneojornadas, 'sync', this.syncTorneoJornadas);

            app.torneojornadas.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarTorneoJornada: function(modelo) {
            var vista = new RowTorneoJornadaView(modelo);
            $("#torneo-jornadas").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoJornadas: function() {
        },

        agregarJornada: function() {
            this.jornadaNewView = new JornadaNewView({modelo: this.model, callbackAceptar: this.selectJornada});
        },

        selectJornada: function(jornada) {
            this.destroyView();
            $("<div id='modal-jornada-new'></div>").appendTo('#torneo-jornadas');
            app.torneojornadas.add(jornada);
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

	return TorneoJornadaAdminView;

});