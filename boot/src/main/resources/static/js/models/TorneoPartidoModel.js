define([
    'backbone'
], function(Backbone){

    var TorneoPartidoModel = Backbone.Model.extend({

        url: function() {
            return 'partido';
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

	return TorneoPartidoModel;
});