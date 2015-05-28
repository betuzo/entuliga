define([
	'backbone',
    'models/EquipoModel'
], function(Backbone, EquipoModel){

    var EquiposCollection = Backbone.Collection.extend({
        model: EquipoModel,
        url: function() {
            if (this.tipo == 'like') {
                return 'equipo/search/' + this.criterio;
            } else {
                return 'equipo';
            }
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        },
        setCriterio: function(criterio){
            this.criterio = criterio;
        }
    });

	return EquiposCollection;
});