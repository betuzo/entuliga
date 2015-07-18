define([
	'backbone',
    'models/FaseModel'
], function(Backbone, FaseModel){

    var FasesCollection = Backbone.Collection.extend({
        model: FaseModel,
        url: 'fase'
    });

	return FasesCollection;
});