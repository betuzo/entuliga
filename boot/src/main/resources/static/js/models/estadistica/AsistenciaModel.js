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
