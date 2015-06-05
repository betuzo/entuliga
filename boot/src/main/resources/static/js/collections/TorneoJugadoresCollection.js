define([
	'backbone',
    'models/TorneoJugadorModel'
], function(Backbone, TorneoJugadorModel){

    var TorneoJugadoresCollection = Backbone.Collection.extend({
        model: TorneoJugadorModel,
        url: function() {
            return 'torneoequipo/' + this.torneoEquipo.get('id') + '/jugador';
        },
        setTorneoEquipo: function(model){
            this.torneoEquipo = model;
        }
    });

	return TorneoJugadoresCollection;
});