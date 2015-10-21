define([
    'backbone'
], function(Backbone){

    var ClasificacionModel = Backbone.Model.extend({

        urlRoot: 'clasificacion',

        defaults: {
        },

        initialize: function() {
        }

    });

	return ClasificacionModel;
});