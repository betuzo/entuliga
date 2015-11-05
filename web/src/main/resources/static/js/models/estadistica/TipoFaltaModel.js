define([
    'backbone'
], function(Backbone){

    var TipoFaltaModel = Backbone.Model.extend({

        urlRoot: 'tipofalta',

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

	return TipoFaltaModel;
});
