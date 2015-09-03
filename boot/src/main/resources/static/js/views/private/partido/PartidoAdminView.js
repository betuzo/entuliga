define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'views/private/partido/PartidoLocalView',
	'views/private/partido/PartidoVisitaView',
	'views/private/partido/PartidoArbitrosView',
	'views/private/partido/PartidoEditView',
	'text!templates/private/partido/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView, TorneoPartidoModel,
            PartidoLocalView, PartidoVisitaView, PartidoArbitrosView,
            PartidoEditView, tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {
            'click #partido-editar': 'partidoEditar',
            'click #agregar-arbitro': 'agregarArbitro',
            'click #partido-comenzar': 'partidoComenzar',
            'click #partido-cancelar': 'partidoCancelar'
        },

        initialize: function(idPartido) {
            this.model = new TorneoPartidoModel();
            this.listenTo(this.model, 'sync', this.syncPartido);
        },

        render: function() {
            return this;
        },

        syncPartido: function() {
            this.$el.html(this.template(this.model.toJSON()));

            new PartidoLocalView(this.model);
            new PartidoVisitaView(this.model);
            new PartidoArbitrosView(this.model);
        },

        setIdPartido: function(idPartido) {
            this.model.set('id', idPartido);
            this.model.fetch();
        },

        partidoEditar: function() {
            new PartidoEditView({modelo: this.model, callbackAceptar: this.successSavePartido});
        },

        successSavePartido: function(partido) {

        },

        agregarArbitro: function() {

        },

        partidoComenzar: function() {
            this.model.set({statusPartido: 'ENPROCESO'});
            this.saveModel();
        },

        partidoCancelar: function() {
            this.model.set({statusPartido: 'CANCELADO'});
            this.saveModel();
        },

        saveModel: function() {
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        console.log('Successfully saved!');
                        alert('Great Success!');
                    },
                    error: function(model, error) {
                        console.log(model.toJSON());
                        console.log('error.responseText');
                    }
                });
            }
        }
	});

	return PartidoAdminView;

});