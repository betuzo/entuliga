define([
	'backbone',
    'models/CanchaModel'
], function(Backbone, CanchaModel){

    var CanchasCollection = Backbone.Collection.extend({
        model: CanchaModel,
        url: 'cancha'
    });

	return CanchasCollection;
});