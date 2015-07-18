define([
    'backbone'
], function(Backbone){

    var EquipoModel = Backbone.Model.extend({

        url: function() {
            return 'equipo';
        },

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