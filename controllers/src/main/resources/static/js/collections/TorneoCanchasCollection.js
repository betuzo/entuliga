define([
	'backbone',
    'models/CanchaModel'
], function(Backbone, CanchaModel){

    var TorneoCanchasCollection = Backbone.Collection.extend({
        model: CanchaModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/cancha';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return TorneoCanchasCollection;
});