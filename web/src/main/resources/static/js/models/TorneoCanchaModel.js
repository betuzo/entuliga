define([
    'backbone'
], function(Backbone){

    var TorneoCanchaModel = Backbone.Model.extend({

        urlRoot: 'torneocancha',

        defaults: {
            statusCancha: 'INSCRITO'
        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.torneoId) {
                return 'Debe tener un Torneo.';
            }
            if(!atributos.canchaId) {
                return 'Debe tener una Cancha.';
            }
            if(!atributos.statusCancha) {
                return 'Debe tener un Status.';
            }
        }

    });

	return TorneoCanchaModel;
});