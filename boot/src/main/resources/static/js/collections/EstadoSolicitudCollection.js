define([
	'backbone',
    'models/EstadoSolicitudModel'
], function(Backbone, EstadoSolicitudModel){

    var EstadoSolicitudCollection = Backbone.Collection.extend({
        model: EstadoSolicitudModel,
        url: 'estadoSolicitud'
    });

	return EstadoSolicitudCollection;
});