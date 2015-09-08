define([
    'backbone'
], function(Backbone){

    var PuntoModel = Backbone.Model.extend({

        urlRoot: 'punto',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            tipo: '',
            tipoValor: '',
            origen: '',
            jugadorId: '',
            jugadorNombre: ''
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
            tipo: {
                required: true
            },
            tipoValor: {
                required: false
            },
            origen: {
                required: true
            },
            jugadorId: {
                required: true
            },
            jugadorNombre: {
                required: false
            }
        }

    });

	return PuntoModel;
});
