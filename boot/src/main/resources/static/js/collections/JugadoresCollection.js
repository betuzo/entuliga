define([
	'backbone',
    'models/JugadorModel'
], function(Backbone, JugadorModel){

    var JugadoresCollection = Backbone.Collection.extend({
        model: JugadorModel,
        url: function() {
            if (this.tipo == 'like') {
                return 'jugador/search/' + this.criterio;
            } else {
                return 'jugador';
            }
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        },
        setCriterio: function(criterio){
            this.criterio = criterio;
        }
    });

	return JugadoresCollection;
});