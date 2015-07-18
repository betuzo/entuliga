define([
    'backbone'
], function(Backbone){

    var FaseModel = Backbone.Model.extend({

        url: function() {
            return 'fase';
        },

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
