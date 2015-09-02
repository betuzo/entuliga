define([
    'backbone',
    'dateformat'
], function(Backbone, dateformat){

    var TorneoPartidoModel = Backbone.Model.extend({
        urlRoot: 'torneopartido',

        defaults: {
            jornadaId: '',
            localId: '',
            localNombre: '',
            localAlias: '',
            localPuntos: 0,
            visitaId: '',
            visitaNombre: '',
            visitaNombre: '',
            visitaPuntos: 0,
            canchaId: '',
            canchaNombre: '',
            canchaDomicilio: '',
            horario: (new Date()).getTime(),
            horarioDes: (new Date()).format("mm/dd/yyyy HH:MM"),
            statusPartido: 'PROGRAMADO'
        },

        initialize: function() {
        },

        validation: {
            jornadaId: {
                required: true
            },
            localId: {
                required: true
            },
            localNombre: {
                required: false
            },
            localAlias: {
                required: false
            },
            localPuntos: {
                required: false
            },
            visitaId: {
                required: true
            },
            visitaNombre: {
                required: false
            },
            visitaAlias: {
                required: false
            },
            visitaPuntos: {
                required: false
            },
            canchaId: {
                required: true
            },
            canchaNombre: {
                required: false
            },
            canchaDomicilio: {
                required: false
            },
            horario: {
                required: true
            },
            horarioDes: {
                required: false
            },
            statusPartido: {
                required: true
            }
        }

    });

	return TorneoPartidoModel;
});