define([
    'backbone'
], function(Backbone){

    var TorneoJornadaModel = Backbone.Model.extend({

        urlRoot: 'torneojornada',

        defaults: {
            torneoId: '',
            nombre: '',
            fase: 'REGULAR'
        },

        initialize: function() {
        },

        validation: {
            torneoId: {
                required: true
            },
            nombre: {
                required: true
            },
            fase: {
                required: true
            }
        }
    });

	return TorneoJornadaModel;
});