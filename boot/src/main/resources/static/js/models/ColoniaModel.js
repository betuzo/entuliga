define([
    'backbone'
], function(Backbone){

    var ColoniaModel = Backbone.Model.extend({

        urlRoot: 'colonia',

        defaults: {
        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.nombre) {
                return 'Debe tener una nombre.';
            }
            if(!atributos.idMunicipio) {
                return 'Debe tener un idMunicipio.';
            }
        }

    });

	return ColoniaModel;
});