define([
    'backbone'
], function(Backbone){

    var TorneoModel = Backbone.Model.extend({

        url: function() {
            return 'torneo';
        },

        defaults: {
            ligaId: '',
            nombre: '',
            descripcion: '',
            fechaInicio: (new Date()).getTime(),
            fechaFin: (new Date()).getTime(),
            status: ''
        },

        initialize: function() {
        },

        validation: {
            ligaId: {
                required: true
            },
            nombre: {
                required: true
            },
            descripcion: {
                required: true
            },
            fechaInicio: {
                required: true
            },
            fechaFin: {
                required: true
            },
            status: {
                required: true
            }
        }

    });

	return TorneoModel;
});
