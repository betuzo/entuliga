define([
    'backbone'
], function(Backbone){

    var TorneoJugadorModel = Backbone.Model.extend({

        url: function() {
            return 'torneojugador';
        },

        defaults: {
            torneoEquipoId: '',
            jugadorId: '',
            numeroJugador: '',
            posicionJugador: 'BASE',
            statusJugador: 'INSCRITO'
        },

        initialize: function() {
        },

        validation: {
            torneoEquipoId: {
                required: true
            },
            jugadorId: {
                required: true
            },
            statusJugador: {
                required: true
            },
            posicionJugador: {
                required: true
            },
            numeroJugador: {
                required: true
            }
        }
    });

	return TorneoJugadorModel;
});