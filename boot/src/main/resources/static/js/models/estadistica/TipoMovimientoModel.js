define([
    'backbone'
], function(Backbone){

    var TipoMovimientoModel = Backbone.Model.extend({

        urlRoot: 'tipomovimiento',

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

	return TipoMovimientoModel;
});
