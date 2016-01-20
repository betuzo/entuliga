define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/EquiposCollection',
	'views/private/EquipoDetailView',
	'views/private/EquipoEditView',
	'text!templates/private/tplEquipoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, EquiposCollection,
            EquipoDetailView, EquipoEditView, tplEquipoAdmin){

	var EquipoAdminView = BaseView.extend({
        template: _.template(tplEquipoAdmin),

        events: {
            'change #select-equipo': 'changeEquipo',
            'click #equipo-nuevo': 'newEquipo',
            'click #equipo-editar': 'editEquipo'
        },

        initialize: function() {
            app.equipos = new EquiposCollection();
            this.listenTo(app.equipos, 'add', this.agregarEquipo);
            this.listenTo(app.equipos, 'sync', this.syncEquipos);

            app.equipos.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_equipo").select();
            return this;
        },

        changeEquipo: function(event) {
            var modelo = app.equipos.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.equipoDetailView = new EquipoDetailView({model: modelo});
                $('#equipo-detail').html(this.equipoDetailView.render().$el);
                $('#equipo-editar').removeAttr("disabled");
            } else {
                $('#equipo-editar').attr("disabled", true);
            }
        },

        newEquipo: function() {
            this.disabledAction(true);
            var equipoEditView = new EquipoEditView({tipo: 'new', modelo: null});
            $('#equipo-edit').html(equipoEditView.render().$el);
            equipoEditView.setUpNew();
        },

        editEquipo: function() {
            this.disabledAction(true);
            var modelo = app.equipos.get($("#select-equipo").val());
            var equipoEditView = new EquipoEditView({tipo: 'edit', modelo: modelo});
            $('#equipo-edit').html(equipoEditView.render().$el);
            equipoEditView.setUpEdit();
        },

        agregarEquipo: function(modelo) {
            $('#select-equipo').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncEquipos: function() {
            $('#select-equipo').change();
        }
	});

	return EquipoAdminView;

});