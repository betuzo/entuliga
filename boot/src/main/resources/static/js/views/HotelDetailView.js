define([
	'jquery',
	'underscore',
	'backbone',
	'moment',
	'momentes',
	'core/BaseView',
	'views/HotelRowSolicitudView',
	'views/SolicitudNuevaView',
	'text!templates/tplHotelDetail.html',
	'collections/SolicitudesCollection'
], function($, _, Backbone, moment, momentes, BaseView, HotelRowSolicitudView, SolicitudNuevaView, tplHotelDetail, SolicitudesCollection){

	var HotelDetailView = BaseView.extend({
        template: _.template(tplHotelDetail),

        events: {
            'click #hotel-solicitar': 'solicitar',
            'change .btn.btn-sm input[type=checkbox]': 'changeEstadoSolicitud'
        },

        initialize: function() {
            this.render();
            app.solicitudes = new SolicitudesCollection({idHotel: this.model.get('id')});
            this.listenTo(app.solicitudes, 'add', this.agregarSolicitud);
            this.actualizaSolicitudes();
            $("#close-hotel-detail").show();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));

            return this;
        },

        agregarSolicitud: function(modelo){
            var classSolicitud = this.generateClassByEstadoSolicitud(modelo.get('estadoSolicitud'));

            moment.locale('es');
            var fechaSolicitud = moment(new Date(modelo.get('fechaSolicitud'))).fromNow();

            modelo.set({classSolicitud: classSolicitud});
            modelo.set({fechaSolicitud: fechaSolicitud});

            var vista = new HotelRowSolicitudView(modelo);
            $("#hotel-solicitudes").find('tbody:last').append(vista.render().$el);

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
        },

        solicitar: function(){
            new SolicitudNuevaView(this.model, this.actualizaSolicitudes);
        },

        actualizaSolicitudes: function(){
            app.solicitudes.fetch();
        },

        changeEstadoSolicitud: function(event){
            var classSolicitud = $(event.target).attr('value');
            var selector = 'tbody tr.' + classSolicitud;
            if ($(event.target).is(":checked")){
               $("#hotel-solicitudes").find(selector).show();
            } else {
               $("#hotel-solicitudes").find(selector).hide();
            }
        }

	});

	return HotelDetailView;

});