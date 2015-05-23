define([
	'backbone',
    'models/EquipoModel'
], function(Backbone, EquipoModel){

    var EquiposCollection = Backbone.Collection.extend({
        model: EquipoModel,
        url: function() {
            if (this.tipo == 'like') {
                return 'equipo/likename/' + this.likename;
            } else {
                return 'equipo';
            }
        },
        setLiga: function(likename){
            this.likename = likename;
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        }
    });

	return EquiposCollection;
});