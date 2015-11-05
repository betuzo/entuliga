define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'models/JugadorModel',
	'core/BaseView',
	'views/private/MainColoniaAdminView',
	'text!templates/private/tplJugadorEdit.html'
], function($, Backbone, backboneValidation, jquerySerializeObject,
            JugadorModel, BaseView, MainColoniaAdminView, tplJugadorEdit){

	var JugadorEditView = BaseView.extend({
        template: _.template(tplJugadorEdit),

        events: {
            'click #colonia-buscar': 'buscarColonia',
            'click #btn-ok': 'saveJugador'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new JugadorModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveJugadorSuccess);
            this.model.once("error", this.saveJugadorError);
            Backbone.Validation.bind(this);
        },

        remove: function() {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        buscarColonia: function() {
            new MainColoniaAdminView(this.selectColonia);
        },

        selectColonia: function(colonia) {
            $('#jugador-colonia-id').val(colonia.get('id'));
            $('#jugador-colonia-desc').val(colonia.get('nombre'));
        },

        saveJugador: function(){
            var data = this.$el.find("#form-jugador").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveJugadorSuccess: function(model, response, options){
            app.jugadores.add(model);
            console.log('Successfully saved!');
            alert('Great Success!');
        },

        saveJugadorError: function(model, response, options){
            console.log(model.toJSON());
            console.log('error.responseText');
        }
	});

	return JugadorEditView;

});