define([
	'backbone',
    'models/ComentarioModel'
], function(Backbone, ComentarioModel){

    var ComentariosCollection = Backbone.Collection.extend({
        model: ComentarioModel,
        url: function() {
            return 'solicitud/' + this.solicitud.idSolicitud + '/comentario';
        },
        initialize: function(solicitud){
            this.solicitud = solicitud;
        }
    });

	return ComentariosCollection;
});