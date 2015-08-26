define([
    'backbone'
], function(Backbone){

    var TorneoPartidoModel = Backbone.Model.extend({
        urlRoot: 'torneopartido',

        defaults: {
            jornadaId: '',
            localId: '',
            localNombre: '',
            localPuntos: 0,
            visitaId: '',
            visitaNombre: '',
            visitaPuntos: 0,
            canchaId: '',
            canchaNombre: '',
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
            localPuntos: {
                required: false
            },
            visitaId: {
                required: true
            },
            visitaNombre: {
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