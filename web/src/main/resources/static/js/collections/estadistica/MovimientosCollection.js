define([
	'backbone',
    'models/estadistica/MovimientoModel'
], function(Backbone, MovimientoModel){

    var MovimientosCollection = Backbone.Collection.extend({
        model: MovimientoModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/movimiento';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return MovimientosCollection;
});