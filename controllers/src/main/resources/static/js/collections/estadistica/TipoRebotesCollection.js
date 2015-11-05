define([
	'backbone',
    'models/estadistica/TipoReboteModel'
], function(Backbone, TipoReboteModel){

    var TipoRebotesCollection = Backbone.Collection.extend({
        model: TipoReboteModel,
        url: 'tiporebote'
    });

	return TipoRebotesCollection;
});