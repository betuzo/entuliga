define([
	'backbone',
    'models/PaisModel'
], function(Backbone, PaisModel){

    var PaisesCollection = Backbone.Collection.extend({
        model: PaisModel,
        url: 'pais'
    });

	return PaisesCollection;
});