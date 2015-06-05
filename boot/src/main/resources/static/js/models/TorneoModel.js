define([
    'backbone',
    'dateformat'
], function(Backbone, dateformat){

    var TorneoModel = Backbone.Model.extend({

        url: function() {
            return 'torneo';
        },

        defaults: {
            ligaId: '',
            nombre: '',
            descripcion: '',
            fechaInicio: (new Date()).getTime(),
            fechaInicioDes: (new Date()).format("mm/dd/yyyy"),
            fechaFin: (new Date()).getTime(),
            fechaFinDes: (new Date()).format("mm/dd/yyyy"),
            status: 'ENPROCESO',
            totalJornadas: 0,
            totalEquipos: 0,
            totalJugadores: 0
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
