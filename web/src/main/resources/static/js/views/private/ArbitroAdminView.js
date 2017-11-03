define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'collections/ArbitrosCollection',
	'views/private/ArbitroDetailView',
	'views/private/ArbitroEditView',
	'views/private/util/ModalGenericView',
	'text!templates/private/tplArbitroAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, ArbitrosCollection,
            ArbitroDetailView, ArbitroEditView, ModalGenericView, tplArbitroAdmin){

	var ArbitroAdminView = BaseView.extend({
        template: _.template(tplArbitroAdmin),

        events: {
            'change #select-arbitro'    : 'changeArbitro',
            'click #arbitro-nuevo'      : 'newArbitro',
            'click #arbitro-editar'     : 'editArbitro',
            'click #arbitro-borrar'     : 'deleteArbitro'
        },

        initialize: function() {
            app.arbitros = new ArbitrosCollection();
            this.listenTo(app.arbitros, 'add', this.agregarArbitro);
            this.listenTo(app.arbitros, 'sync', this.syncArbitros);

            app.arbitros.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$el.find(".selecter_arbitro").select();
            return this;
        },

        changeArbitro: function(event) {
            var modelo = app.arbitros.get($(event.target).val());
            if (typeof modelo != 'undefined') {
                this.arbitroDetailView = new ArbitroDetailView({model: modelo});
                $('#arbitro-detail').html(this.arbitroDetailView.render().$el);
                $('#arbitro-editar').removeAttr("disabled");
                $('#arbitro-borrar').removeAttr("disabled");
            } else {
                if (typeof this.arbitroDetailView !== 'undefined') {
                    this.arbitroDetailView.destroyView();
                }
                $('#arbitro-editar').attr("disabled", true);
                $('#arbitro-borrar').attr("disabled", true);
            }
        },

        newArbitro: function() {
            this.disabledAction(true);
            var arbitroEditView = new ArbitroEditView({tipo: 'new', modelo: null});
            $('#arbitro-edit').html(arbitroEditView.render().$el);
            arbitroEditView.setUpNew();
        },

        editArbitro: function() {
            this.disabledAction(true);
            var modelo = app.arbitros.get($("#select-arbitro").val());
            var arbitroEditView = new ArbitroEditView({tipo: 'edit', modelo: modelo});
            $('#arbitro-edit').html(arbitroEditView.render().$el);
            arbitroEditView.setUpEdit();
        },

        deleteArbitro: function() {
            var modelo = app.arbitros.get($("#select-arbitro").val());
            modelo.destroy({
                wait:true,
                success: function(model, response) {
                    new ModalGenericView({message: response.message});
                    if(response.result){
                        $("#select-arbitro option:selected").remove();
                        $('#select-arbitro').change();
                    }
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        agregarArbitro: function(modelo) {
            $('#select-arbitro').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre') + " " + modelo.get('paterno') + " " + modelo.get('paterno')
            }));
        },

        syncArbitros: function() {
            $('#select-arbitro').change();
        }
	});

	return ArbitroAdminView;

});
