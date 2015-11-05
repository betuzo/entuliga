define([
	'backbone',
    'models/estadistica/ReboteModel'
], function(Backbone, ReboteModel){

    var RebotesCollection = Backbone.Collection.extend({
        model: ReboteModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/rebote';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return RebotesCollection;
});