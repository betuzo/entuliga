define([
	'backbone',
    'models/ArbitroModel'
], function(Backbone, ArbitroModel){

    var ArbitrosCollection = Backbone.Collection.extend({
        model: ArbitroModel,
        url: function() {
            if (this.tipo == 'like') {
                return 'arbitro/search/' + this.criterio;
            } else {
                return 'arbitro';
            }
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        },
        setCriterio: function(criterio){
            this.criterio = criterio;
        }
    });

	return ArbitrosCollection;
});