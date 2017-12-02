define([
    'backbone',
    '../core/BaseModel'
], function(Backbone, BaseModel){

	var ArbitroPartidoModel = BaseModel.extend({
		urlRoot: 'partidoarbitro',

		defaults: {
			partidoId:'',
			torneoArbitroId:'',
			nombre: '',
			paterno: '',
			materno: '',
			tipoArbitro: 'PRINCIPAL'
		},

		initialize: function() {
			console.log('initialize model');
		},

		validate: function(atributos) {
				console.log("atributos");
				console.log("validate attributos arbitroPartidoModel");
		}

	});

	return ArbitroPartidoModel;
});