define([
    'backbone'
], function(Backbone){

    var TipoReboteModel = Backbone.Model.extend({

        urlRoot: 'tiporebote',

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

	return TipoReboteModel;
});
