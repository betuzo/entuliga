define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoArbitroModel',
	'collections/TorneoArbitrosCollection',
	'views/private/ArbitroSearchView',
	'views/private/RowTorneoArbitroView',
	'text!templates/private/tplTorneoArbitroAdmin.html'
], function($, Backbone, BaseView, TorneoArbitroModel, TorneoArbitrosCollection,
            ArbitroSearchView, RowTorneoArbitroView, tplTorneoArbitroAdmin){

	var TorneoArbitroAdminView = BaseView.extend({
        template: _.template(tplTorneoArbitroAdmin),

        events: {
            'click #arbitro-agregar': 'agregarArbitro'
        },

        initialize: function() {
            app.torneoarbitros = new TorneoArbitrosCollection();
            app.torneoarbitros.setTorneo(this.model);
            this.listenTo(app.torneoarbitros, 'add', this.agregarTorneoArbitro);
            this.listenTo(app.torneoarbitros, 'sync', this.syncTorneoArbitros);

            app.torneoarbitros.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarTorneoArbitro: function(modelo) {
            var vista = new RowTorneoArbitroView(modelo);
            $("#torneo-arbitros").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoArbitros: function() {
        },

        agregarArbitro: function() {
            this.arbitroSearchView = new ArbitroSearchView({modelo: this.model, callbackAceptar: this.selectArbitro});
        },

        selectArbitro: function(arbitro) {
            this.destroyView();
            $("<div id='modal-arbitro-search'></div>").appendTo('#torneo-arbitros');
            var modelo = new TorneoArbitroModel();
            modelo.save({ torneoId: this.model.get('id'),
                           arbitroId: arbitro.get('id')}, {
                wait:true,
                success:function(model, response) {
                    app.torneoarbitros.add(model);
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

	return TorneoArbitroAdminView;

});