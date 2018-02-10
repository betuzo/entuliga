define([
	'backbone',
    'models/ArbitroPartidoModel'
], function(Backbone, ArbitroPartidoModel){

    var ArbitrosPartidoCollection = Backbone.Collection.extend({
        model: ArbitroPartidoModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/partidoarbitro';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
        
    });

	return ArbitrosPartidoCollection;
});