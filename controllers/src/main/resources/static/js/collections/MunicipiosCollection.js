define([
	'backbone',
    'models/MunicipioModel'
], function(Backbone, MunicipioModel){

    var MunicipiosCollection = Backbone.Collection.extend({
        model: MunicipioModel,
        url: function() {
            return 'estado/' + this.estado.id + '/municipio';
        },
        setEstado: function(model){
            this.estado = model;
        }
    });

	return MunicipiosCollection;
});