define([
	'backbone',
    'models/CategoriaModel'
], function(Backbone, CategoriaModel){

    var CategoriasCollection = Backbone.Collection.extend({
        model: CategoriaModel,
        url: function() {
            return 'hotel/' + this.hotel.id + '/categoria';
        },
        initialize: function(model){
            this.hotel = model.model;
        }
    });

	return CategoriasCollection;
});