define([
    'backbone'
], function(Backbone){

    var HabitacionModel = Backbone.Model.extend({

        url: function() {
            return 'habitacion';
        },

        defaults: {
        },

        initialize: function() {
            console.log('Se ha creado una nueva instancia del Modelo Habitacion.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.numeroHabitacion) {
                return 'Debe tener una numeroHabitacion.';
            }
            if(!atributos.tipoHabitacion) {
                return 'Debe tener una tipoHabitacion.';
            }
            if(!atributos.hotelId) {
                return 'Debe tener una hotelId.';
            }
        }

    });

	return HabitacionModel;
});
