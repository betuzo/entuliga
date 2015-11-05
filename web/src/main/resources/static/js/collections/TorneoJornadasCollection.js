define([
	'backbone',
    'models/TorneoJornadaModel'
], function(Backbone, TorneoJornadaModel){

    var TorneoJornadasCollection = Backbone.Collection.extend({
        model: TorneoJornadaModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/jornada';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return TorneoJornadasCollection;
});