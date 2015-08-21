define([
    'backbone'
], function(Backbone){

    var TorneoEquipoModel = Backbone.Model.extend({

        urlRoot: 'torneoequipo',

        defaults: {
            statusEquipo: 'INSCRITO'
        },

        initialize: function() {
        },

        validate: function(atributos) {
            if(!atributos.torneoId) {
                return 'Debe tener un Torneo.';
            }
            if(!atributos.equipoId) {
                return 'Debe tener un Equipo.';
            }
            if(!atributos.statusEquipo) {
                return 'Debe tener un Status.';
            }
        }

    });

	return TorneoEquipoModel;
});