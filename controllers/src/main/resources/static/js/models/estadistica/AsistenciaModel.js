define([
    'backbone'
], function(Backbone){

    var AsistenciaModel = Backbone.Model.extend({

        urlRoot: 'asistencia',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            origen: '',
            asisteId: '',
            asisteNombre: '',
            asistidoId: '',
            asistidoNombre: ''
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
            origen: {
                required: true
            },
            asisteId: {
                required: true
            },
            asisteNombre: {
                required: false
            },
            asistidoId: {
                required: true
            },
            asistidoNombre: {
                required: false
            }
        }

    });

	return AsistenciaModel;
});
