define([
	'backbone',
    'models/TorneoEquipoModel'
], function(Backbone, TorneoEquipoModel){

    var TorneoEquiposCollection = Backbone.Collection.extend({
        model: TorneoEquipoModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/equipo';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return TorneoEquiposCollection;
});