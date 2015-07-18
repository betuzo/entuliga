define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoEquipoModel',
	'collections/TorneoEquiposCollection',
	'views/private/EquipoSearchView',
	'views/private/RowTorneoEquipoView',
	'text!templates/private/tplTorneoEquipoAdmin.html'
], function($, Backbone, BaseView, TorneoEquipoModel, TorneoEquiposCollection,
            EquipoSearchView, RowTorneoEquipoView, tplTorneoEquipoAdmin){

	var TorneoEquipoAdminView = BaseView.extend({
        template: _.template(tplTorneoEquipoAdmin),

        events: {
            'click #equipo-agregar': 'agregarEquipo'
        },

        initialize: function() {
            app.torneoequipos = new TorneoEquiposCollection();
            app.torneoequipos.setTorneo(this.model);
            this.listenTo(app.torneoequipos, 'add', this.agregarTorneoEquipo);
            this.listenTo(app.torneoequipos, 'sync', this.syncTorneoEquipos);

            app.torneoequipos.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarTorneoEquipo: function(modelo) {
            var vista = new RowTorneoEquipoView(modelo);
            $("#torneo-equipos").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoEquipos: function() {
        },

        agregarEquipo: function() {
            this.equipoSearchView = new EquipoSearchView({modelo: this.model, callbackAceptar: this.selectEquipo});
        },

        selectEquipo: function(equipo) {
            this.destroyView();
            $("<div id='modal-equipo-search'></div>").appendTo('#torneo-equipos');
            var modelo = new TorneoEquipoModel();
            modelo.save({ torneoId: this.model.get('id'),
                           equipoId: equipo.get('id')}, {
                wait:true,
                success:function(model, response) {
                    app.torneoequipos.add(model);
                },
                error: function(model, error) {
                    console.log(model.toJSON());
                    console.log('error.responseText');
                }
            });
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

	return TorneoEquipoAdminView;

});