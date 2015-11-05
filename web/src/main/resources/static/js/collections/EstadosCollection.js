define([
	'backbone',
    'models/EstadoModel'
], function(Backbone, EstadoModel){

    var EstadosCollection = Backbone.Collection.extend({
        model: EstadoModel,
        url: function() {
            return 'pais/' + this.pais.id + '/estado';
        },
        setPais: function(model){
            this.pais = model;
        }
    });

	return EstadosCollection;
});