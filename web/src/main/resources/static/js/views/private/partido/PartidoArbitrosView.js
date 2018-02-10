define([
	'jquery',
	'backbone',
    'marionette',
    'models/ArbitroPartidoModel',
    'collections/ArbitrosPartidoCollection',
    'views/private/partido/ListArbitroPartidoView',
	'text!templates/private/partido/tplPartidoArbitros.html'
], function($, Backbone, Mn, ArbitroPartidoModel, ArbitrosPartidoCollection, ListArbitroPartidoView, tplPartidoArbitros){

	var PartidoArbitrosView = Mn.View.extend({
	    el: '#section-arbitros',

        template: _.template(tplPartidoArbitros),

        regions: {
            listBody: {
                el: '.list-arbitros-partido',
                replaceElement: true
            }
        },

        modelEvents: {
            'change:someattribute': 'changeMyAttribute'
        },

        collectionEvents: {
            sync: 'modelsChanged'
        },

        events: {

        },

        initialize: function(opts) {
            this.modelPartido = opts.modelo;
            this.parent = opts.parent;
            this.partidoArbitroCollection = new ArbitrosPartidoCollection();
            // this.listenTo(this.partidoArbitroCollection, 'sync', this.syncArbitrosPartido);
            this.partidoArbitroCollection.setTorneoPartido(this.modelPartido);
            this.partidoArbitroCollection.fetch();
        },

        // syncArbitrosPartido: function(opts){
        //     console.log("syncArbitrosPartido");
        //     console.log(opts);
        // },


        onRender: function(opts) {
            this.showChildView('listBody', new ListArbitroPartidoView({
                collection: this.partidoArbitroCollection
            }));
        },

        changeMyAttribute: function() {
            console.log('someattribute was changed');
        },

        modelsChanged: function() {
            console.log('models were added or removed in the collection');
        }

	});

	return PartidoArbitrosView;

});