define([
	'backbone',
    'models/estadistica/BloqueoModel'
], function(Backbone, BloqueoModel){

    var BloqueosCollection = Backbone.Collection.extend({
        model: BloqueoModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/bloqueo';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return BloqueosCollection;
});