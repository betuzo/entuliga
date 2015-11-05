define([
	'jquery',
	'backbone',
	'core/BaseView',
	'models/TorneoCanchaModel',
	'collections/TorneoCanchasCollection',
	'views/private/CanchaSearchView',
	'views/private/RowTorneoCanchaView',
	'text!templates/private/tplTorneoCanchaAdmin.html'
], function($, Backbone, BaseView, TorneoCanchaModel, TorneoCanchasCollection,
            CanchaSearchView, RowTorneoCanchaView, tplTorneoCanchaAdmin){

	var TorneoCanchaAdminView = BaseView.extend({
        template: _.template(tplTorneoCanchaAdmin),

        events: {
            'click #cancha-agregar': 'agregarCancha'
        },

        initialize: function() {
            app.torneocanchas = new TorneoCanchasCollection();
            app.torneocanchas.setTorneo(this.model);
            this.listenTo(app.torneocanchas, 'add', this.agregarTorneoCancha);
            this.listenTo(app.torneocanchas, 'sync', this.syncTorneoCanchas);

            app.torneocanchas.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarTorneoCancha: function(modelo) {
            var vista = new RowTorneoCanchaView(modelo);
            $("#torneo-canchas").find('tbody:last').append(vista.render().$el);
        },

        syncTorneoCanchas: function() {
        },

        agregarCancha: function() {
            this.canchaSearchView = new CanchaSearchView({modelo: this.model, callbackAceptar: this.selectCancha});
        },

        selectCancha: function(cancha) {
            this.destroyView();
            $("<div id='modal-cancha-search'></div>").appendTo('#torneo-canchas');
            var modelo = new TorneoCanchaModel();
            modelo.save({ torneoId: this.model.get('id'),
                           canchaId: cancha.get('id')}, {
                wait:true,
                success:function(model, response) {
                    app.torneocanchas.add(model);
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

	return TorneoCanchaAdminView;

});