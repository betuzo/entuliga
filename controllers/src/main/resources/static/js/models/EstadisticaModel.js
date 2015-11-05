define([
    'backbone'
], function(Backbone){

    var EstadisticaModel = Backbone.Model.extend({

        urlRoot: 'estadistica',

        defaults: {
        },

        initialize: function() {
        }

    });

	return EstadisticaModel;
});