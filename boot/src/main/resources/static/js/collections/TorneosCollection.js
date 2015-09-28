define([
	'backbone',
    'models/TorneoModel'
], function(Backbone, TorneoModel){

    var TorneosCollection = Backbone.Collection.extend({
        model: TorneoModel,
        url: function() {
            if ( this.liga != null && this.liga != 'undefined' ) {
                return 'liga/' + this.liga.get('id') + '/torneo';
            } else {
                return 'torneo';
            }
        },
        setLiga: function(model){
            this.liga = model;
        }
    });

	return TorneosCollection;
});