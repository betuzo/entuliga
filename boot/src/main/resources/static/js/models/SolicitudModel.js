define([
	'backbone'
], function(Backbone){

    var SolicitudModel = Backbone.Model.extend({

        urlRoot: 'solicitud',

        defaults: {
            fechaSolicitud: (new Date()).getTime(),
            fechaComentario: (new Date()).getTime(),
            estadoSolicitud: 'SOLICITADA',
            tipoComentario: 'EMPLEADO'
        },

        initialize: function() {
            console.log('Se ha creado una nueva instancia del Modelo Solicitud.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.habitacionId) {
                return 'Debe tener una habitacion la solicitud.';
            }
            if(!atributos.servicioId) {
                return 'Debe tener un servicio la solicitud.';
            }
            if(!atributos.empleadoId) {
                return 'Debe tener un empleado que registre la solicitud.';
            }
            if(!atributos.estadoSolicitud) {
                return 'Debe tener un estado la solicitud.';
            }
            if(!atributos.tipoComentario && !atributos.id) {
                return 'Debe tener un tipo de comentario la solicitud.';
            }
            if(!atributos.comentario && !atributos.id) {
                return 'Debe tener un comentario la solicitud.';
            }
        }

    });

	return SolicitudModel;
});
