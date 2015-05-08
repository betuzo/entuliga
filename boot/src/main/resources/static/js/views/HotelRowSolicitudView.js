define([
	'jquery',
	'underscore',
	'core/BaseView',
	'views/SolicitudDetailView',
	'text!templates/tplHotelRowSolicitud.html'
], function($, _, BaseView, SolicitudDetailView, tplHotelRowSolicitud){

	var HotelRowSolicitudView = BaseView.extend({
        template: _.template(tplHotelRowSolicitud),
        tagName: 'tr',

        events: {
            'click #ver-solicitud': 'showDetailSolicitud'
        },

        initialize: function(modelo) {
            this.model =  modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$el.addClass( this.model.get('classSolicitud') );
            this.$el.attr("id", "sol-" + this.model.get('id'));
            return this;
        },

        showDetailSolicitud: function(event){
            var modelo = app.solicitudes.get($(event.target).attr('value'));
            var vista = new SolicitudDetailView(modelo);
            $("#detail-solicitud").html(vista.render().$el);
        }
	});

	return HotelRowSolicitudView;

});