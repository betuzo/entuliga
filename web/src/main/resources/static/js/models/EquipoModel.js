define([
    'backbone'
], function(Backbone){

    var EquipoModel = Backbone.Model.extend({

        urlRoot: 'equipo',

        defaults: {
            nombre: '',
            aliasEquipo: '',
            rutaLogoEquipo: ''
        },

        initialize: function() {
        },

        validation: {
            nombre: {
                required: true
            },
            aliasEquipo: {
                required: true
            },
            rutaLogoEquipo: {
                required: true
            }
        }

    });

	return EquipoModel;
});