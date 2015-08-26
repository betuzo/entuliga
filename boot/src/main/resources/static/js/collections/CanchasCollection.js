define([
	'backbone',
    'models/CanchaModel'
], function(Backbone, CanchaModel){

    var CanchasCollection = Backbone.Collection.extend({
        model: CanchaModel,
        url: function() {
            if (this.tipo == 'like') {
                return 'cancha/search/' + this.criterio;
            } else {
                return 'cancha';
            }
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        },
        setCriterio: function(criterio){
            this.criterio = criterio;
        }
    });

	return CanchasCollection;
});