define([
	'backbone',
    'models/estadistica/TipoFaltaModel'
], function(Backbone, TipoFaltaModel){

    var TipoFaltasCollection = Backbone.Collection.extend({
        model: TipoFaltaModel,
        url: 'tipofalta'
    });

	return TipoFaltasCollection;
});