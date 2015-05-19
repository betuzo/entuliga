define([
	'jquery',
	'backbone',
	'datepicker',
	'backboneValidation',
	'jquerySerializeObject',
	'models/TorneoModel',
	'core/BaseView',
	'text!templates/private/tplTorneoEdit.html'
], function($, Backbone, datepicker, backboneValidation,
            jquerySerializeObject, TorneoModel, BaseView, tplTorneoEdit){

	var TorneoEditView = BaseView.extend({
        template: _.template(tplTorneoEdit),

        events: {
            'click #btn-ok': 'saveTorneo'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new TorneoModel();
            } else {
                this.model = opts.modelo;
            }

            this.model.once("sync", this.saveTorneoSuccess);
            this.model.once("error", this.saveTorneoError);
            Backbone.Validation.bind(this);
        },

        remove: function() {
            Backbone.Validation.unbind(this);
            return Backbone.View.prototype.remove.apply(this, arguments);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            $('#dp-fecha-inicio').datepicker();
            $('#dp-fecha-fin').datepicker();
            return this;
        },

        saveTorneo: function(){
            var data = this.$el.find("#form-torneo").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveTorneoSuccess: function(model, response, options){
            console.log('Successfully saved!');
            alert('Great Success!');
        },

        saveTorneoError: function(model, response, options){
            console.log(model.toJSON());
            console.log('error.responseText');
        }
	});

	return TorneoEditView;

});