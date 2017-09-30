define([
    'backbone',
    'dateformat',
    '../core/BaseModel'
], function(Backbone, dateformat, BaseModel){

    var TorneoModel = BaseModel.extend({

        urlRoot: 'torneo',

        defaults: {
            ligaId: '',
            nombre: '',
            descripcion: '',
            fechaInicio: (new Date()).getTime(),
            fechaInicioDes: (new Date()).format("mm/dd/yyyy"),
            fechaFin: (new Date()).getTime(),
            fechaFinDes: (new Date()).format("mm/dd/yyyy"),
            status: 'ENPROCESO'
        },

        validation: {
            ligaId: {
                required: true
            },
            nombre: {
                required: true
            },
            descripcion: {
                required: true
            },
            fechaInicio: {
                required: true
            },
            fechaFin: {
                required: true
            },
            status: {
                required: true
            }
        }

    });

	return TorneoModel;
});
