define([
	'jquery',
	'backbone',
	'bootstrap',
	'core/BaseView',
	'models/JornadaModel',
	'collections/FasesCollection',
	'text!templates/private/tplJornadaNew.html'
], function($, Backbone, bootstrap, BaseView, JornadaModel,
            FasesCollection, tplJornadaNew){

	var EquipoSearchView = BaseView.extend({
	    el: '#modal-jornada-new',
        template: _.template(tplJornadaNew),

        events: {
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = new JornadaModel();
            this.torneo = opts.modelo;
            this.render();

            this.fases = new FasesCollection();
            this.listenTo(this.fases, 'add', this.agregarFase);
            this.listenTo(this.fases, 'sync', this.syncFases);
            this.fases.fetch();

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#jornada-new-dialog').modal('show');
            this.$('.alert-danger').hide();
            this.$('#torneo-liga-id').val(this.torneo.get('id'));
        },

        agregarFase: function(modelo) {
            $('#select-fase').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncFases: function(modelo) {
            $('#select-fase').change();
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-torneo-jornada").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        console.log('Successfully saved!');
                        alert('Great Success!');
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        console.log(model.toJSON());
                        console.log('error.responseText');
                    }
                });
            }
        },

        destroyView: function() {
            // COMPLETELY UNBIND THE VIEW
            this.undelegateEvents();
            this.$el.removeData().unbind();
            // Remove view from DOM
            this.remove();
            Backbone.View.prototype.remove.call(this);
        }
	});

	return EquipoSearchView;

});