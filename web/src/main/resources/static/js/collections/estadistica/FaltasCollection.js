define([
	'backbone',
    'models/estadistica/FaltaModel'
], function(Backbone, FaltaModel){

    var FaltasCollection = Backbone.Collection.extend({
        model: FaltaModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/falta';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return FaltasCollection;
});