define([
    'backbone'
], function(Backbone){

    var LigaModel = Backbone.Model.extend({

        url: function() {
            return 'liga';
        },

        defaults: {
        },

        initialize: function() {
        },

        validation: {
            nombre: {
                required: true
            },
            nombreCompleto: {
                required: true
            },
            telefono: {
                minLength: 8
            },
            calle: {
                required: true
            },
            noExterior: {
                required: true
            },
            noInterior: {
                required: false
            },
            codigoPostal: {
                required: true
            },
            coloniaId: {
                required: true
            },
            longitude: {
                required: false
            },
            latitude: {
                required: false
            }
        }

    });

	return LigaModel;
});
