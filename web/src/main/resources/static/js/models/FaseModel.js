define([
    'underscore',
    'backbone'

], function(_, Backbone){

    var FaseModel = Backbone.Model.extend({

        urlRoot: 'fase',

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

	return FaseModel;
});
