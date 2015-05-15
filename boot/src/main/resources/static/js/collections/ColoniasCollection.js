define([
	'backbone',
    'models/ColoniaModel'
], function(Backbone, ColoniaModel){

    var ColoniasCollection = Backbone.Collection.extend({
        model: ColoniaModel,
        url: function() {
            return 'municipio/' + this.municipio.id + '/colonia';
        },
        setMunicipio: function(model){
            this.municipio = model;
        }
    });

	return ColoniasCollection;
});