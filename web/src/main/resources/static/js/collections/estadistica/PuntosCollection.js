define([
	'backbone',
    'models/estadistica/PuntoModel'
], function(Backbone, PuntoModel){

    var PuntosCollection = Backbone.Collection.extend({
        model: PuntoModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/punto';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return PuntosCollection;
});