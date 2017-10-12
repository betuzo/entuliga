define([
    'backbone',
    '../core/BaseModel'
], function(Backbone, BaseModel){

    var EquipoModel = BaseModel.extend({

        urlRoot: 'equipo',

        defaults: {
            nombre: '',
            aliasEquipo: '',
            logoEquipo: 'novalid'
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
