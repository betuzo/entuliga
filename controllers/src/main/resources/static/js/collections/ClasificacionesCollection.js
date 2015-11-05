define([
	'backbone',
    'models/ClasificacionModel'
], function(Backbone, ClasificacionModel){

    var ClasificacionesCollection = Backbone.Collection.extend({
        model: ClasificacionModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/clasificacion';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return ClasificacionesCollection;
});