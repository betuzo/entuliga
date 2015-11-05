define([
	'backbone',
    'models/estadistica/RoboModel'
], function(Backbone, RoboModel){

    var RobosCollection = Backbone.Collection.extend({
        model: RoboModel,
        url: function() {
            return 'torneopartido/' + this.partido.get('id') + '/robo';
        },
        setTorneoPartido: function(model){
            this.partido = model;
        }
    });

	return RobosCollection;
});