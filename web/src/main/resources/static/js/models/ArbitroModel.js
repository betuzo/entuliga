define([
    'backbone',
    '../core/BaseModel'
], function(Backbone, BaseModel){

    var ArbitroModel = BaseModel.extend({

        urlRoot: 'arbitro',

        defaults: {
            nombre: '',
            paterno: '',
            materno: '',
            sexo: 'FEMENINO',
            logoArbitro: 'novalid',
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
            logoArbitro: {
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
