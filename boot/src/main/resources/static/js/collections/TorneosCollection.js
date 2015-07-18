define([
	'backbone',
    'models/TorneoModel'
], function(Backbone, TorneoModel){

    var TorneosCollection = Backbone.Collection.extend({
        model: TorneoModel,
        url: function() {
            return 'liga/' + this.liga.get('id') + '/torneo';
        },
        setLiga: function(model){
            this.liga = model;
        }
    });

	return TorneosCollection;
});