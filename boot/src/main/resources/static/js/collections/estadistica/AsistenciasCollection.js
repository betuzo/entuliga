define([
	'backbone',
    'models/estadistica/AsistenciaModel'
], function(Backbone, AsistenciaModel){

    var AsistenciasCollection = Backbone.Collection.extend({
        model: AsistenciaModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/asistencia';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return AsistenciasCollection;
});