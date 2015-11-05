define([
	'backbone',
    'models/estadistica/TipoEncesteModel'
], function(Backbone, TipoEncesteModel){

    var TipoEncestesCollection = Backbone.Collection.extend({
        model: TipoEncesteModel,
        url: 'tipoenceste'
    });

	return TipoEncestesCollection;
});