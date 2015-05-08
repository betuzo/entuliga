define([
    'backbone'
], function(Backbone){

    var HotelServicioModel = Backbone.Model.extend({

        url: function() {
            return 'hotelServicio';
        },

        defaults: {
        },

        initialize: function() {
            console.log('Se ha creado una nueva instancia del Modelo HotelServicio.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.hotelId) {
                return 'Debe tener un hotelId.';
            }

            if(!atributos.categoriaId) {
                return 'Debe tener una categoriaId.';
            }

            if(!atributos.servicioId) {
                return 'Debe tener una servicioId.';
            }
        }

    });

	return HotelServicioModel;
});
