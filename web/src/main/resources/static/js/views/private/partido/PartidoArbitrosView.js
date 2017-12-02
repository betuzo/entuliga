define([
	'jquery',
	'backbone',
    'marionette',
	'core/BaseView',
    'models/ArbitroPartidoModel',
    'views/private/partido/ListArbitroPartidoView'
	'text!templates/private/partido/tplPartidoArbitros.html'
], function($, Backbone, Mn, BaseView, ArbitroPartidoModel, ArbitrosPartidoCollection, tplPartidoArbitros){

	var PartidoArbitrosView = Mn.View.extend({
	    el: '#section-arbitros',

        template: _.template(tplPartidoArbitros),

        modelEvents: {
            'change:someattribute': 'changeMyAttribute'
        },

        collectionEvents: {
            sync: 'modelsChanged'
        },

        events: {

        },

        initialize: function(opts) {
            console.log(opts);

            this.modelPartido = opts.modelo;
            this.parent = opts.parent;
            this.partidoArbitroCollection = new ArbitrosPartidoCollection();
            this.listenTo(this.partidoArbitroCollection, 'sync', this.syncArbitrosPartido);
            this.partidoArbitroCollection.setTorneoPartido(this.modelPartido);
            this.partidoArbitroCollection.fetch();
        },

        syncArbitrosPartido: function(opts){
            console.log("syncArbitrosPartido");
            console.log(opts);
        },


        onRender: function(opts) {
            console.log("render view marionette PartidoArbitrosView");

            // this.$el.html(this.template(this.model.toJSON()));          
        },

        changeMyAttribute: function() {
        console.log('someattribute was changed');
        },

        modelsChanged: function() {
        console.log('models were added or removed in the collection');
        }



        // initialize: function(modelo) {
        //     console.log("partidoArbitrosView **** " ,  modelo);

        //     this.model = modelo;
        //     this.render();


        //     // this.model = opts.modelo;
        //     // this.parent = opts.parent;
        //     // app.puntosPartido = new PuntosCollection();
        //     // this.listenTo(app.puntosPartido, 'add', this.agregarPunto);
        //     // this.listenTo(app.puntosPartido, 'sync', this.syncPuntos);
        //     // this.render();

        //     // app.puntosPartido.setTorneoPartido(this.model);
        //     // app.puntosPartido.fetch();

        // },

        // render: function() {
        //     this.$el.html(this.template(this.model.toJSON()));
        // }



	});

	return PartidoArbitrosView;

});