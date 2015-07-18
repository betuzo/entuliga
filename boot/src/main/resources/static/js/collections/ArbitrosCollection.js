define([
	'backbone',
    'models/ArbitroModel'
], function(Backbone, ArbitroModel){

    var ArbitrosCollection = Backbone.Collection.extend({
        model: ArbitroModel,
        url: 'arbitro'
    });

	return ArbitrosCollection;
});