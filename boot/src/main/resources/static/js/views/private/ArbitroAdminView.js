define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/ArbitrosCollection',
	'views/private/ArbitroDetailView',
	'views/private/ArbitroEditView',
	'text!templates/private/tplArbitroAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, ArbitrosCollection, ArbitroDetailView, ArbitroEditView, tplArbitroAdmin){

	var ArbitroAdminView = BaseView.extend({
        template: _.template(tplArbitroAdmin),

        events: {
            'change #select-arbitro': 'changeArbitro',
            'click #arbitro-nuevo': 'newArbitro',
            'click #arbitro-editar': 'editArbitro'
        },

        initialize: function() {
            this.arbitros = new ArbitrosCollection();
            this.listenTo(this.arbitros, 'add', this.agregarArbitro);
            this.listenTo(this.arbitros, 'sync', this.syncArbitros);

            this.arbitros.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_arbitro").select();
            return this;
        },

        changeArbitro: function(event) {
            var modelo = this.arbitros.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.arbitroDetailView = new ArbitroDetailView({model: modelo});
                $('#arbitro-detail').html(this.arbitroDetailView.render().$el);
                $('#arbitro-editar').removeAttr("disabled");
            } else {
                $('#arbitro-editar').attr("disabled", true);
            }
        },

        newArbitro: function() {
            this.arbitroEditView = new ArbitroEditView({tipo: 'new', modelo: null});
            $('#arbitro-edit').html(this.arbitroEditView.render().$el);
        },

        editArbitro: function() {
            var modelo = this.arbitros.get($("#select-arbitro").val());
            this.arbitroEditView = new ArbitroEditView({tipo: 'edit', modelo: modelo});
            $('#arbitro-edit').html(this.arbitroEditView.render().$el);
        },

        agregarArbitro: function(modelo) {
            $('#select-arbitro').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncArbitros: function() {
            $('#select-arbitro').change();
        }
	});

	return ArbitroAdminView;

});