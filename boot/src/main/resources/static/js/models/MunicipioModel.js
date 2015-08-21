define([
    'backbone'
], function(Backbone){

    var MunicipioModel = Backbone.Model.extend({

        urlRoot: 'municipio',

        defaults: {
        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.nombre) {
                return 'Debe tener una nombre.';
            }
            if(!atributos.idEstado) {
                return 'Debe tener un idEstado.';
            }
        }

    });

	return MunicipioModel;
});