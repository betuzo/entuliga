define([
	'jquery',
	'backbone',
    'bootstrap',
	'core/BaseView',
	'models/estadistica/AsistenciaModel',
	'models/TorneoEquipoModel',
	'collections/TorneoJugadoresCollection',
	'views/private/util/ModalGenericView',
	'text!templates/private/estadistica/tplAsistenciaCreate.html'
], function($, Backbone, bootstrap, BaseView, AsistenciaModel, TorneoEquipoModel,
            TorneoJugadoresCollection, ModalGenericView, tplAsistenciaCreate){

	var AsistenciaCreateView = BaseView.extend({
	    el: '#modal-partido',
        template: _.template(tplAsistenciaCreate),

        events: {
            'click #btn-aceptar': 'clickAceptar',
            'change #select-asiste': 'changeAsiste',
            'change .validate-number':'validateNumber',
            'keyup .validate-number':'validateNumber'
        },

        initialize: function(opts) {
            this.model = new AsistenciaModel();
            this.modelPartido = opts.modelo;
            this.callbackAceptar = opts.callbackAceptar;
            this.parent = opts.parent;
            this.origen = opts.origen;
            this.model.set({origen : opts.origen});
            this.model.set({partidoId : opts.modelo.get('id')});
            this.render();

            this.asisten = new TorneoJugadoresCollection();
            this.listenTo(this.asisten, 'add', this.agregarAsiste);
            this.listenTo(this.asisten, 'sync', this.syncAsistees);

            this.asistidon = new TorneoJugadoresCollection();
            this.listenTo(this.asistidon, 'add', this.agregarAsistido);
            this.listenTo(this.asistidon, 'sync', this.syncAsistidoes);
            this.fetchAsistidoesByOrigen(this.origen);

            Backbone.Validation.bind(this);
        },

        fetchAsisteesByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.asisten.setTorneoEquipo(equipo);
            this.asisten.fetch();
        },

        fetchAsistidoesByOrigen: function(origen) {
            var equipo;
            if (origen == 'LOCAL') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('localId') });
            }
            if (origen == 'VISITA') {
                equipo = new TorneoEquipoModel({ id : this.modelPartido.get('visitaId') });
            }
            this.asistidon.setTorneoEquipo(equipo);
            this.asistidon.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#asistencia-create-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarAsiste: function(modelo) {
            $('#select-asiste').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncAsistees: function(modelo) {
            $('#select-asiste').change();
        },

        changeAsiste: function(event) {
            var asiste = this.asisten.get($(event.target).val());
            $('#select-asistido').html('');
            that = this;
            this.asistidon.each(function(modelo){
                if (asiste.get('id') == modelo.get('id')) {
                    return;
                }
                that.agregarAsistido(modelo);
            });
        },

        agregarAsistido: function(modelo) {
            $('#select-asistido').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('jugadorNombre')
            }));
        },

        syncAsistidoes: function(modelo) {
            $('#select-asistido').change();
            this.fetchAsisteesByOrigen(this.origen);
        },

        clickAceptar: function(event) {
            var data = this.$el.find("#form-asistencia").serializeObject();
            this.model.set(data);
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({
                            message: 'Asistencia registrada correctamente'
                        });
                        that.callbackAceptar(model);
                    },
                    error: function(model, error) {
                        new ModalGenericView({
                            message: 'Se presento un error al registrar la asistencia'
                        });
                    }
                });
            }
        },

        validateNumber: function(event){
            if ($.isNumeric($(event.currentTarget).val())) {
                if ($(event.currentTarget).val() != $(event.currentTarget).val().replace(/[^0-9\.]/g, '')) {
                    var value = $(event.currentTarget).val().replace(/[^0-9.]/g, '');
                    $(event.currentTarget).val(value);
                }else{
                    var max = parseInt($(event.currentTarget).attr('max'));
                    var min = parseInt($(event.currentTarget).attr('min'));
                    if ( $(event.currentTarget).val() > max){
                        $(event.currentTarget).val(max);
                    } else if ($(event.currentTarget).val() < min){
                        $(event.currentTarget).val(min);
                    }
                }
            }else{
                var value = $(event.currentTarget).val().replace(/[^0-9.]/g, '');
                $(event.currentTarget).val(value);
            }
        },

        destroyView: function() {
            $("body").removeClass("modal-open");
            // COMPLETELY UNBIND THE VIEW
            this.undelegateEvents();
            this.$el.removeData().unbind();
            // Remove view from DOM
            this.remove();
            Backbone.View.prototype.remove.call(this);
            $("<div id='modal-partido'></div>").appendTo('#modal-partido-parent');
        }
	});

	return AsistenciaCreateView;

});