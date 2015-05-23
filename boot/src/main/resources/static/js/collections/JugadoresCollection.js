define([
	'backbone',
    'models/JugadorModel'
], function(Backbone, JugadorModel){

    var JugadoresCollection = Backbone.Collection.extend({
        model: JugadorModel,
        url: 'jugador'
    });

	return JugadoresCollection;
});