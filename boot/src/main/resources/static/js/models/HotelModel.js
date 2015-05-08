define([
	'backbone'
], function(Backbone){

    var HotelModel = Backbone.Model.extend({

        urlRoot: 'hotel',

        defaults: {
            pais: 'México'
        },

        initialize: function() {
            console.log('Se ha creado una nueva instancia del Modelo Hotel.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.id) {
                return 'Debe tener un id.';
            }
            if(!atributos.nombreOficial) {
                return 'Debe tener un nombre el hotel.';
            }
        }

    });

	return HotelModel;
});