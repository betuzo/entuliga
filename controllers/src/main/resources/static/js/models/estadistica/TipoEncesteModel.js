define([
    'backbone'
], function(Backbone){

    var TipoEncesteModel = Backbone.Model.extend({

        urlRoot: 'tipoenceste',

        defaults: {
            clave: '',
            descripcion: ''
        },

        initialize: function() {
        },

        validation: {
            clave: {
                required: true
            },
            descripcion: {
                required: true
            }
        }

    });

	return TipoEncesteModel;
});
