define([
	'backbone',
    'models/TorneoPartidoModel'
], function(Backbone, TorneoPartidoModel){

    var TorneoPartidosCollection = Backbone.Collection.extend({
        model: TorneoPartidoModel,
        url: function() {
            return 'torneojornada/' + this.torneoJornada.get('id') + '/partido';
        },
        setTorneoJornada: function(model){
            this.torneoJornada = model;
        }
    });

	return TorneoPartidosCollection;
});