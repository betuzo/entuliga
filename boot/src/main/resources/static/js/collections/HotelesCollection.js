define([
	'backbone',
    'models/HotelModel'
], function(Backbone, HotelModel){

    var HotelesCollection = Backbone.Collection.extend({
        model: HotelModel,
        url: 'hotel'
    });

	return HotelesCollection;
});