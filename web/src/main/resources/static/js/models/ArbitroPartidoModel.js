define([
    'backbone',
    '../core/BaseModel'
], function(Backbone, BaseModel){

	var ArbitroPartidoModel = BaseModel.extend({
		urlRoot: 'torneopartido',

		defaults: {
		    tipoArbitro: 'PRINCIPAL'
		},

		initialize: function() {

		},

		validate: function(atributos) {
				console.log("validate attributos arbitroPartidoModel");
		}

	});

	return ArbitroPartidoModel;
});