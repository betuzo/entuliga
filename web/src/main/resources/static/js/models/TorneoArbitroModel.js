define([
    'backbone'
], function(Backbone){

    var TorneoArbitroModel = Backbone.Model.extend({

        urlRoot: 'torneoarbitro',

        defaults: {
            statusArbitro: 'INSCRITO',
            tipoArbitro: 'PRINCIPAL'
        },

        initialize: function() {
        },

        validate: function(atributos) {
            console.log("validar torneoarbitro");
            console.log(atributos);
            
            if(!atributos.torneoId) {
                return 'Debe tener un Torneo.';
            }
            if(!atributos.arbitroId) {
                return 'Debe tener un Arbitro.';
            }
            if(!atributos.statusArbitro) {
                return 'Debe tener un Status.';
            }
        }

    });

	return TorneoArbitroModel;
});