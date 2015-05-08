define([
	'backbone',
    'models/HabitacionModel'
], function(Backbone, HabitacionModel){

    var HabitacionesCollection = Backbone.Collection.extend({
        model: HabitacionModel,
        url: function() {
            return 'hotel/' + this.hotel.id + '/habitacion';
        },
        initialize: function(model){
            this.hotel = model.model;
        }
    });

	return HabitacionesCollection;
});