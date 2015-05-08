define([
	'backbone',
    'models/HotelServicioModel'
], function(Backbone, HotelServicioModel){

    var HotelServicioCollection = Backbone.Collection.extend({
        model: HotelServicioModel,
        url: function() {
            return 'hotel/' + this.hotel.id + '/categoria/' + this.categoria.id + '/servicio';
        },
        initialize: function(model){
            this.hotel = model.model;
        },
        setCategoria: function(model){
            this.categoria = model;
        }
    });

	return HotelServicioCollection;
});