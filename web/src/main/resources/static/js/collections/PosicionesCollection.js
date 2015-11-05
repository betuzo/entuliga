define([
	'backbone',
    'models/FaseModel'
], function(Backbone, PosicionModel){

    var PosicionesCollection = Backbone.Collection.extend({
        model: PosicionModel,
        url: 'posicion'
    });

	return PosicionesCollection;
});