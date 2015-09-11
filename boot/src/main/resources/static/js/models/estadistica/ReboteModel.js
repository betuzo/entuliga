define([
    'backbone'
], function(Backbone){

    var ReboteModel = Backbone.Model.extend({

        urlRoot: 'rebote',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            tipo: '',
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
            jugadorId: {
                required: true
            },
            jugadorNombre: {
                required: false
            }
        }

    });

	return ReboteModel;
});
