define([
    'backbone'
], function(Backbone){

    var ArbitroModel = Backbone.Model.extend({

        url: function() {
            return 'arbitro';
        },

        defaults: {
            nombre: '',
            paterno: '',
            materno: '',
            sexo: 'FEMENINO',
            rutaFoto: '',
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
            rutaFoto: {
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

	return ArbitroModel;
});
