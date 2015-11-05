define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/LigasCollection',
	'views/private/LigaDetailView',
	'views/private/LigaEditView',
	'text!templates/private/tplLigaAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, LigasCollection, LigaDetailView, LigaEditView, tplLigaAdmin){

	var LigaAdminView = BaseView.extend({
        template: _.template(tplLigaAdmin),

        events: {
            'change #select-liga': 'changeLiga',
            'click #liga-nuevo': 'newLiga',
            'click #liga-editar': 'editLiga'
        },

        initialize: function() {
            app.ligas = new LigasCollection();
            this.listenTo(app.ligas, 'add', this.agregarLiga);
            this.listenTo(app.ligas, 'sync', this.syncLigas);

            app.ligas.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_liga").select();
            return this;
        },

        changeLiga: function(event) {
            var modelo = app.ligas.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.ligaDetailView = new LigaDetailView({model: modelo});
                $('#liga-detail').html(this.ligaDetailView.render().$el);
                $('#liga-editar').removeAttr("disabled");
            } else {
                $('#liga-editar').attr("disabled", true);
            }
        },

        newLiga: function() {
            this.ligaEditView = new LigaEditView({tipo: 'new', modelo: null});
            $('#liga-edit').html(this.ligaEditView.render().$el);
        },

        editLiga: function() {
            var modelo = app.ligas.get($("#select-liga").val());
            this.ligaEditView = new LigaEditView({tipo: 'edit', modelo: modelo});
            $('#liga-edit').html(this.ligaEditView.render().$el);
        },

        agregarLiga: function(modelo) {
            $('#select-liga').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncLigas: function() {
            $('#select-liga').change();
        }
	});

	return LigaAdminView;

});