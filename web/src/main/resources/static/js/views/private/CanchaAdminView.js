define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/CanchasCollection',
	'views/private/CanchaDetailView',
	'views/private/CanchaEditView',
	'text!templates/private/tplCanchaAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, CanchasCollection,
            CanchaDetailView, CanchaEditView, tplCanchaAdmin){

	var CanchaAdminView = BaseView.extend({
        template: _.template(tplCanchaAdmin),

        events: {
            'change #select-cancha': 'changeCancha',
            'click #cancha-nuevo': 'newCancha',
            'click #cancha-editar': 'editCancha'
        },

        initialize: function() {
            app.canchas = new CanchasCollection();
            this.listenTo(app.canchas, 'add', this.agregarCancha);
            this.listenTo(app.canchas, 'sync', this.syncCanchas);

            app.canchas.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_cancha").select();
            return this;
        },

        changeCancha: function(event) {
            var modelo = app.canchas.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.canchaDetailView = new CanchaDetailView({model: modelo});
                $('#cancha-detail').html(this.canchaDetailView.render().$el);
                $('#cancha-editar').removeAttr("disabled");
            } else {
                $('#cancha-editar').attr("disabled", true);
            }
        },

        newCancha: function() {
            this.disabledAction(true);
            var canchaEditView = new CanchaEditView({tipo: 'new', modelo: null});
            $('#cancha-edit').html(canchaEditView.render().$el);
        },

        editCancha: function() {
            this.disabledAction(true);
            var modelo = app.canchas.get($("#select-cancha").val());
            var canchaEditView = new CanchaEditView({tipo: 'edit', modelo: modelo});
            $('#cancha-edit').html(canchaEditView.render().$el);
        },

        agregarCancha: function(modelo) {
            $('#select-cancha').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncCanchas: function() {
            $('#select-cancha').change();
        }
	});

	return CanchaAdminView;

});