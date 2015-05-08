define([
    'backbone'
], function(Backbone){

    var CategoriaModel = Backbone.Model.extend({

        url: function() {
            return 'categoria';
        },

        defaults: {
        },

        initialize: function() {
            console.log('Se ha creado una nueva instancia del Modelo Categoria.');

            this.on('change', function(){
                console.log('El modelo ha sido modificado.');
            });
        },

        validate: function(atributos) {
            if(!atributos.claveCategoria) {
                return 'Debe tener una claveCategoria.';
            }
        }

    });

	return CategoriaModel;
});
