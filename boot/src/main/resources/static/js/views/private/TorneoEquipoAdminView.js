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
            this.torneoequipos = new TorneoEquiposCollection();
            this.torneoequipos.setTorneo(this.model);
            this.listenTo(this.torneoequipos, 'add', this.agregarTorneoEquipo);
            this.listenTo(this.torneoequipos, 'sync', this.syncTorneoEquipos);

            this.torneoequipos.fetch();
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
            var that = this;
            var modelo = new TorneoEquipoModel();
            modelo.save({ torneoId: this.model.get('id'),
                           equipoId: equipo.get('id')}, {
                wait:true,
                success:function(model, response) {
                    $("#torneo-equipos").find('tbody').html('');
                    that.torneoequipos.fetch();
                },
                error: function(model, error) {
                    console.log(model.toJSON());
                    console.log('error.responseText');
                }
            });
        }
	});

	return TorneoEquipoAdminView;

});