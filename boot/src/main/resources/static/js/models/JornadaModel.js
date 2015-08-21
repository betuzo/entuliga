define([
    'backbone'
], function(Backbone){

    var JornadaModel = Backbone.Model.extend({

        urlRoot: 'jornada',

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

	return JornadaModel;
});