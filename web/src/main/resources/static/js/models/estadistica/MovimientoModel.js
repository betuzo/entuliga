define([
    'backbone'
], function(Backbone){

    var MovimientoModel = Backbone.Model.extend({

        urlRoot: 'movimiento',

        defaults: {
            partidoId: '',
            tiempoDes: '',
            minuto: '',
            segundo: '',
            tipo: '',
            origen: '',
            entraId: '',
            entraNombre: '',
            saleId: '',
            saleNombre: ''
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
            origen: {
                required: true
            },
            entraId: {
                required: true
            },
            entraNombre: {
                required: false
            },
            saleId: {
                required: false
            },
            saleNombre: {
                required: false
            }
        }

    });

	return MovimientoModel;
});
