define([
	'backbone',
    'models/JornadaModel'
], function(Backbone, JornadaModel){

    var JornadasCollection = Backbone.Collection.extend({
        model: JornadaModel,
        url: function() {
            return 'torneo/' + this.torneo.get('id') + '/jornada';
        },
        setTorneo: function(model){
            this.torneo = model;
        }
    });

	return JornadasCollection;
});