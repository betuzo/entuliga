define([
	'backbone',
    'models/LigaModel'
], function(Backbone, LigaModel){

    var LigasCollection = Backbone.Collection.extend({
        model: LigaModel,
        url: 'liga'
    });

	return LigasCollection;
});