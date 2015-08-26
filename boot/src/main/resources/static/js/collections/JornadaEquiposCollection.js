define([
	'backbone',
    'models/TorneoEquipoModel'
], function(Backbone, TorneoEquipoModel){

    var JornadaEquiposCollection = Backbone.Collection.extend({
        model: TorneoEquipoModel,
        url: function() {
            return 'torneojornada/' + this.jornada.get('id') + '/equipo';
        },
        setJornada: function(model){
            this.jornada = model;
        }
    });

	return JornadaEquiposCollection;
});