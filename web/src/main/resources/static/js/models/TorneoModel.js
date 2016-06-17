define([
    'backbone',
    'dateformat'
], function(Backbone, dateformat){

    var TorneoModel = Backbone.Model.extend({

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

        initialize: function() {
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
