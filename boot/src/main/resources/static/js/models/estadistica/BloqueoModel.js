define([
    'backbone'
], function(Backbone){

    var BloqueoModel = Backbone.Model.extend({

        urlRoot: 'bloqueo',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            bloqueaId: '',
            bloqueaNombre: '',
            bloqueadoId: '',
            bloqueadoNombre: ''
        },

        initialize: function() {
        },

        validation: {
            partidoId: {
                required: true
            },
            tiempoDes: {
                required: false
            },
            minuto: {
                required: true
            },
            segundo: {
                required: true
            },
            bloqueaId: {
                required: true
            },
            bloqueaNombre: {
                required: false
            },
            bloqueadoId: {
                required: true
            },
            bloqueadoNombre: {
                required: false
            }
        }

    });

	return BloqueoModel;
});
