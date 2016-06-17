define([
	'backbone',
    'models/TorneoArbitroModel'
], function(Backbone, TorneoArbitroModel){

    var TorneoArbitrosCollection = Backbone.Collection.extend({
        model: TorneoArbitroModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/arbitro';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return TorneoArbitrosCollection;
});