define([
	'jquery',
	'backbone',
	'Session'
], function($, Backbone, Session){

    var ComentarioModel = Backbone.Model.extend({

        url: function() {
            return 'comentario';
        },

        defaults: {
        },

        initialize: function() {
            var token = Session.get('X-CSRF-TOKEN');
            var param = Session.get('X-CSRF-PARAM');
            var formData = new FormData();
            formData.append(param, token);
            $.ajaxSetup({
                //data: formData,
                headers: {
                    _csrf  : token
                }
            });
            console.log('Se ha creado una nueva instancia del Modelo Comentario.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.solicitudId) {
                return 'Debe tener un empleado.';
            }

            if(!atributos.empleadoId) {
                return 'Debe tener un empleado.';
            }
            if(!atributos.tipoComentario) {
                return 'Debe tener un tipo el comentario.';
            }
            if(!atributos.comentario) {
                return 'Debe tener un mensaje el comentario.';
            }
        }

    });

	return ComentarioModel;
});
