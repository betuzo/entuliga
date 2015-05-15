define([
    'backbone'
], function(Backbone){

    var PaisModel = Backbone.Model.extend({

        url: function() {
            return 'pais';
        },

        defaults: {
        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.nombre) {
                return 'Debe tener una nombre.';
            }
        }

    });

	return PaisModel;
});
