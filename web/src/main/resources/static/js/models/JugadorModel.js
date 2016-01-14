define([
    'backbone'
], function(Backbone){

    var JugadorModel = Backbone.Model.extend({

        urlRoot: 'jugador',

        defaults: {
            nombre: '',
            paterno: '',
            materno: '',
            sexo: 'FEMENINO',
            logoJugadpr: '',
            fechaRegistro: (new Date()).getTime(),
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
            paterno: {
                required: true
            },
            materno: {
                required: true
            },
            sexo: {
                required: true
            },
            logoJugadpr: {
                required: true
            },
            fechaRegistro: {
                required: true
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

	return JugadorModel;
});
