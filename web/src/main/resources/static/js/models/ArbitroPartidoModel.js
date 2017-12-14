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
		},

		validate: function(atributos) {
				console.log("atributos");
		}

	});

	return ArbitroPartidoModel;
});