define([
    'backbone'
], function(Backbone){

    var EstadoModel = Backbone.Model.extend({

        url: function() {
            return 'estado';
        },

        defaults: {
        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.nombre) {
                return 'Debe tener una nombre.';
            }
            if(!atributos.idPais) {
                return 'Debe tener un idPais.';
            }
        }

    });

	return EstadoModel;
});