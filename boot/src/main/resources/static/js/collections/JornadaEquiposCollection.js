define([
	'backbone',
    'models/TorneoEquipoModel'
], function(Backbone, TorneoEquipoModel){

    var JornadaEquiposCollection = Backbone.Collection.extend({
        model: TorneoEquipoModel,
        url: function() {
            return 'jornada/' + this.jornada.get('id') + '/equipo';
        },
        setJornada: function(model){
            this.jornada = model;
        }
    });

	return JornadaEquiposCollection;
});