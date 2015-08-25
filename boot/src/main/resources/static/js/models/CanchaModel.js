define([
    'backbone'
], function(Backbone){

    var CanchaModel = Backbone.Model.extend({

        urlRoot: 'cancha',

        defaults: {
            nombre: '',
            alias: '',
            descripcion: '',
            calle: '',
            noExterior: '',
            noInterior: '',
            codigoPostal: '',
            coloniaId: '',
            coloniaDesc: '',
            municipioDesc: '',
            estadoDesc: '',
            paisDesc: '',
            longitude: '',
            latitude: ''
        },

        initialize: function() {
        },

        validation: {
            nombre: {
                required: true
            },
            alias: {
                required: true
            },
            descripcion: {
                required: false
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

	return CanchaModel;
});
