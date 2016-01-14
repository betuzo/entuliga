define([
    'backbone'
], function(Backbone){

    var EquipoModel = Backbone.Model.extend({

        urlRoot: 'equipo',

        defaults: {
            nombre: '',
            aliasEquipo: '',
            logoEquipo: 'novalid'
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
            logoEquipo: {
                required: true
            }
        }

    });

	return EquipoModel;
});