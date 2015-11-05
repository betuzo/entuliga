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
                this.model.set({ligaId: opts.idLiga});
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
            this.$el.find('#dp-fecha-inicio').datepicker({format: "mm/dd/yyyy"});
            this.$el.find('#dp-fecha-fin').datepicker({format: "mm/dd/yyyy"});
            return this;
        },

        saveTorneo: function(){
            var data = this.$el.find("#form-torneo").serializeObject();
            this.model.set(data);
            var fechaInicio = new Date(this.model.get('fechaInicioDes'));
            var fechaFin = new Date(this.model.get('fechaFinDes'));
            this.model.set({fechaInicio: fechaInicio.getTime(), fechaFin: fechaFin.getTime()});
            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveTorneoSuccess: function(model, response, options){
            app.torneos.add(model);
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