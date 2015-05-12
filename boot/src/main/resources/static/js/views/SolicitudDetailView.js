define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/tplResumenSolicitud.html',
	'models/ComentarioModel',
	'collections/ComentariosCollection',
	'collections/EstadoSolicitudCollection',
	'views/SolicitudComentarioView',
	'Session'
], function($, _, BaseView, tplResumenSolicitud, ComentarioModel,
            ComentariosCollection, EstadoSolicitudCollection, SolicitudComentarioView, Session){

	var SolicitudDetailView = BaseView.extend({
        template: _.template(tplResumenSolicitud),

        events: {
            'click #tabs-detail-solicitud a': 'showTab',
            'click #btn-send-comment'       : 'sendComment',
            'click #btn-cambiar-estado'     : 'sendChangeState',
            'keyup #txt-comment'            : 'writeComment'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.comentarios = new ComentariosCollection({idSolicitud: this.model.get('id')});
            this.listenTo(this.comentarios, 'add', this.agregarComentario);
            this.comentarios.fetch();

            this.estadosSolicitudes = new EstadoSolicitudCollection();
            this.listenTo(this.estadosSolicitudes, 'add', this.agregarEstadoSolicitud);
            this.estadosSolicitudes.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        agregarEstadoSolicitud: function(modelo){
            if (modelo.get('clave') != this.model.get('estadoSolicitud')){
                $('#sol-cambiar-estado').append($('<option>', {
                    value: modelo.get('clave'),
                    text : modelo.get('descripcion')
                }))
            }
        },

        agregarComentario: function(modelo){
            var fechaComentario = new Date(modelo.get('fechaComentario'));
            var target = this.getTarget(modelo.get('tipoComentario'));
            var avatar = this.getAvatar(modelo.get('tipoComentario'));

            modelo.set({fechaComentario: fechaComentario});
            modelo.set({target: target});
            modelo.set({avatar: avatar});

            var vista = new SolicitudComentarioView({model: modelo});
            $("#coment-history").after(vista.render().$el);

        },

        getTarget: function(tipoComentario){
            switch (tipoComentario) {
                case "HABITACION":
                    return "left";
                case "EMPLEADO":
                    return "right";
            }
        },

        getAvatar: function(tipoComentario){
            switch (tipoComentario) {
                case "HABITACION":
                    return "huesped";
                case "EMPLEADO":
                    return "empleado";
            }
        },

        showTab: function(event){
            $(event.target).tab('show');
        },

        writeComment: function(){
            if ($("#txt-comment").val().length > 0) {
                $("#btn-send-comment").prop( "disabled", false );
            } else {
                $("#btn-send-comment").prop( "disabled", true );
            }
        },

        sendComment: function(){
            that = this;
            var comentario = new ComentarioModel();
            var fechaComent = new Date();
            fechaComent = fechaComent.getTime();
            comentario.save({solicitudId: this.model.get('id'),
                        tipoComentario: 'EMPLEADO',
                        comentario: $("#txt-comment").val(),
                        empleadoId: Session.get('id'),
                        empleadoNombre: Session.get('name'),
                        fechaComentario: fechaComent}, {
                wait:true,
                success:function(model, response) {
                    $("#txt-comment").val('');
                    $("#btn-send-comment").prop( "disabled", true );
                    that.agregarComentario(model);
                    console.log('Successfully saved!');
                },
                error: function(model, error) {
                    console.log(model.toJSON());
                    console.log('error.responseText');
                }
            });
        },

        sendChangeState: function(){
            that = this;
            this.model.save(
                {   estadoSolicitud: $("#sol-cambiar-estado").val(),
                    empleadoId: Session.get('id') }, {
                wait:true,
                success:function(model, response) {
                    console.log('Successfully saved!');
                    $('#sol-cambiar-estado').html('');
                    that.estadosSolicitudes.fetch();
                    $("#p-estado-solicitud").html(model.get('estadoSolicitud'));
                    var selector = "#sol-" + model.get('id');
                    $(selector).removeClass();
                    var classSolicitud = that.generateClassByEstadoSolicitud(model.get('estadoSolicitud'));
                    $(selector).addClass( classSolicitud );
                },
                error: function(model, error) {
                    console.log(model.toJSON());
                    console.log('error.responseText');
                }
            });
        },

        generateClassByEstadoSolicitud: function(estadoSolicitud) {
            switch (estadoSolicitud) {
                case "SOLICITADA":
                    return "success";
                case "ENPROCESO":
                    return "warning";
                case "PENDIENTE":
                    return "danger";
                case "ATENDIDA":
                    return "sinestilo";
                case "CANCELADA":
                    return "active";
            }
        }
	});

	return SolicitudDetailView;

});