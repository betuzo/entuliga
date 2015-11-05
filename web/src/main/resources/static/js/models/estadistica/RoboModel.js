define([
    'backbone'
], function(Backbone){

    var RoboModel = Backbone.Model.extend({

        urlRoot: 'robo',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            origen: '',
            robadorId: '',
            robadorNombre: '',
            perdedorId: '',
            perdedorNombre: ''
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
            robadorId: {
                required: true
            },
            robadorNombre: {
                required: false
            },
            perdedorId: {
                required: false
            },
            perdedorNombre: {
                required: false
            }
        }

    });

	return RoboModel;
});
