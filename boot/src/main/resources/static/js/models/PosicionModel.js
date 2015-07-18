define([
    'backbone'
], function(Backbone){

    var PosicionModel = Backbone.Model.extend({

        url: function() {
            return 'posicion';
        },

        defaults: {
            clave: '',
            descripcion: ''
        },

        initialize: function() {
        },

        validation: {
            clave: {
                required: true
            },
            descripcion: {
                required: true
            }
        }

    });

	return PosicionModel;
});
