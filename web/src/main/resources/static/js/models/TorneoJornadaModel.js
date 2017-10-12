define([
    'backbone',
    '../core/BaseModel'
], function(Backbone, BaseModel){

    var TorneoJornadaModel = BaseModel.extend({

        urlRoot: 'torneojornada',

        defaults: {
            torneoId: '',
            nombre: '',
            fase: 'REGULAR'
        },


        validation: {
            torneoId: {
                required: true
            },
            nombre: {
                required: true
            },
            fase: {
                required: true
            }
        }
    });

	return TorneoJornadaModel;
});
