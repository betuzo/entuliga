define([
	'jquery',
	'underscore',
	'backbone',
	'bootstrapselect',
	'core/BaseView',
	'models/SolicitudModel',
	'collections/HabitacionesCollection',
	'collections/CategoriasCollection',
	'collections/HotelServiciosCollection',
	'text!templates/tplSolicitudNueva.html',
	'Session'
], function($, _, Backbone, bootstrapselect, BaseView, SolicitudModel, HabitacionesCollection,
            CategoriasCollection, HotelServiciosCollection, tplSolicitudNueva, Session){

	var SolicitudNuevaView = BaseView.extend({
	    el: '#id-modal-solicitud',

        template: _.template(tplSolicitudNueva),

        events: {
            'click #btn-solicitar' : 'enviarSolicitud',
            'change #sol-categoria': 'changeCategoria'
        },

        initialize: function(model, callbackActualizaSolicitudes) {
            this.model = model;
            this.callbackActualizaSolicitudes = callbackActualizaSolicitudes;
            this.render();
            this.habitaciones = new HabitacionesCollection({model: this.model});
            this.listenTo(this.habitaciones, 'add', this.agregarHabitacion);
            this.listenTo(this.habitaciones, 'sync', this.syncHabitaciones);

            this.categorias = new CategoriasCollection({model: this.model});
            this.listenTo(this.categorias, 'add', this.agregarCategoria);
            this.listenTo(this.categorias, 'sync', this.syncCategorias);

            this.hotelservicios = new HotelServiciosCollection({model: this.model});
            this.listenTo(this.hotelservicios, 'add', this.agregarHotelServicio);
            this.listenTo(this.hotelservicios, 'sync', this.syncHotelServicios);

            this.habitaciones.fetch();
            this.categorias.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            this.$('#solicitudModal').modal('show');
        },

        changeCategoria: function(event){
            var modelo = this.categorias.get($(event.target).val());
            this.hotelservicios.setCategoria(modelo);
            $('#sol-servicio').html('');
            this.hotelservicios.fetch();
        },

        syncHabitaciones: function(){
            $('#sol-no-habitacion').change();
        },

        agregarHabitacion: function(modelo){
            $('#sol-no-habitacion').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('numeroHabitacion')
            }));
        },

        syncCategorias: function(){
            $('#sol-categoria').change();
        },

        agregarCategoria: function(modelo){
            $('#sol-categoria').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('claveCategoria')
            }));
        },

        syncHotelServicios: function(){
            $('#sol-servicio').change();
        },

        agregarHotelServicio: function(modelo){
            $('#sol-servicio').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('servicioNombre')
            }));
        },

        enviarSolicitud: function(){
            that = this;
            var solicitud = new SolicitudModel();
            solicitud.save({habitacionId: $("#sol-no-habitacion").val(),
                        servicioId: $("#sol-servicio").val(),
                        comentario: $("#sol-comentario").val(),
                        empleadoId: Session.get('id')}, {
                wait:true,
                success:function(model, response) {
                    console.log('Successfully saved!');
                    that.callbackActualizaSolicitudes();
                    that.cerrarVentana();
                },
                error: function(model, error) {
                    console.log(model.toJSON());
                    console.log('error.responseText');
                }
            });
        },

        cerrarVentana: function() {
            $('#btn-cancelar').click();
        }

	});

	return SolicitudNuevaView;

});