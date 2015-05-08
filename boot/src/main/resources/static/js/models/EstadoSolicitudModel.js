define([
    'backbone'
], function(Backbone){

    var EstadoSolicitudModel = Backbone.Model.extend({

        url: function() {
            return 'estadoSolicitud';
        },

        defaults: {
        },

        initialize: function() {
            console.log('Se ha creado una nueva instancia del Modelo Categoria.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.clave) {
                return 'Debe tener una clave.';
            }

            if(!atributos.descripcion) {
                return 'Debe tener una descripcion.';
            }
        }

    });

	return EstadoSolicitudModel;
});
