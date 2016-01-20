define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/LigasCollection',
	'collections/TorneosCollection',
	'views/private/LigaDetailView',
	'views/private/TorneoDetailView',
	'views/private/TorneoEditView',
	'text!templates/private/tplTorneoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, LigasCollection,
            TorneosCollection, LigaDetailView, TorneoDetailView, TorneoEditView, tplTorneoAdmin){

	var TorneoAdminView = BaseView.extend({
        template: _.template(tplTorneoAdmin),

        events: {
            'change #select-liga':      'changeLiga',
            'change #select-torneo':    'changeTorneo',
            'click #torneo-nuevo':      'newTorneo',
            'click #torneo-editar':     'editTorneo'
        },

        initialize: function() {
            this.ligas = new LigasCollection();
            this.listenTo(this.ligas, 'add', this.agregarLiga);
            this.listenTo(this.ligas, 'sync', this.syncLigas);

            app.torneos = new TorneosCollection();
            this.listenTo(app.torneos, 'add', this.agregarTorneo);
            this.listenTo(app.torneos, 'sync', this.syncTorneos);

            this.ligas.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_liga").select();
            return this;
        },

        changeLiga: function(event) {
            var modelo = this.ligas.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.ligaDetailView = new LigaDetailView({model: modelo});
                $('#liga-detail').html(this.ligaDetailView.render().$el);
                app.torneos.setLiga(modelo);
                app.torneos.fetch();
                $('#torneo-nuevo').removeAttr("disabled");
                $('#torneo-editar').removeAttr("disabled");
            } else {
                $('#torneo-nuevo').attr("disabled", true);
                $('#torneo-editar').attr("disabled", true);
            }
        },

        changeTorneo: function(event) {
            var modelo = app.torneos.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.torneoDetailView = new TorneoDetailView({model: modelo});
                $('#torneo-detail').html(this.torneoDetailView.render().$el);
                $('#torneo-editar').removeAttr("disabled");
            } else {
                $('#torneo-editar').attr("disabled", true);
            }
        },

        agregarLiga: function(modelo) {
            $('#select-liga').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncLigas: function() {
            $('#select-liga').change();
        },

        agregarTorneo: function(modelo) {
            $('#select-torneo').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncTorneos: function() {
            $('#select-torneo').change();
        },

        newTorneo: function() {
            this.disabledAction(true);
            var ligaId = this.ligas.get($("#select-liga").val()).get('id');
            var torneoEditView = new TorneoEditView({tipo: 'new', idLiga: ligaId});
            $('#torneo-edit').html(torneoEditView.render().$el);
        },

        editTorneo: function() {
            this.disabledAction(true);
            var modelo = app.torneos.get($("#select-torneo").val());
            var torneoEditView = new TorneoEditView({tipo: 'edit', modelo: modelo});
            $('#torneo-edit').html(torneoEditView.render().$el);
        }
	});

	return TorneoAdminView;

});