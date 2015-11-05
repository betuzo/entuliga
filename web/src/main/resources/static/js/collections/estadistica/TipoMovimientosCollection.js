define([
	'backbone',
    'models/estadistica/TipoMovimientoModel'
], function(Backbone, TipoMovimientoModel){

    var TipoMovimientosCollection = Backbone.Collection.extend({
        model: TipoMovimientoModel,
        url: 'tipomovimiento'
    });

	return TipoMovimientosCollection;
});