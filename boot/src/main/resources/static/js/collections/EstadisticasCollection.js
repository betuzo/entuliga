define([
	'backbone',
    'models/EstadisticaModel'
], function(Backbone, EstadisticaModel){

    var EstadisticasCollection = Backbone.Collection.extend({
        model: EstadisticaModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/estadistica';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return EstadisticasCollection;
});