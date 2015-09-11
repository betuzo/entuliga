define([
    'backbone'
], function(Backbone){

    var FaltaModel = Backbone.Model.extend({

        urlRoot: 'falta',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            tipo: '',
            infractorId: '',
            infractorNombre: '',
            receptorId: '',
            receptorNombre: ''
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
            infractorId: {
                required: true
            },
            infractorNombre: {
                required: false
            },
            receptorId: {
                required: false
            },
            receptorNombre: {
                required: false
            }
        }

    });

	return FaltaModel;
});
