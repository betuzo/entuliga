define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/PaisesCollection',
	'collections/EstadosCollection',
	'collections/MunicipiosCollection',
	'collections/ColoniasCollection',
	'text!templates/private/tplMainColoniaAdmin.html'
], function($, bootstrap, BaseView, PaisesCollection, EstadosCollection,
            MunicipiosCollection, ColoniasCollection, tplMainColoniaAdmin){

	var MainColoniaAdminView = BaseView.extend({
	    el: '#modal-colonia',
        template: _.template(tplMainColoniaAdmin),

        events: {
            'change #input-pais': 'changePais',
            'change #input-estado': 'changeEstado',
            'change #input-municipio': 'changeMunicipio',
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(callbackAceptar) {
            this.callbackAceptar = callbackAceptar;
            this.render();
            this.paises = new PaisesCollection();
            this.listenTo(this.paises, 'add', this.agregarPais);
            this.listenTo(this.paises, 'sync', this.syncPaises);

            this.estados = new EstadosCollection();
            this.listenTo(this.estados, 'add', this.agregarEstado);
            this.listenTo(this.estados, 'sync', this.syncEstados);

            this.municipios = new MunicipiosCollection();
            this.listenTo(this.municipios, 'add', this.agregarMunicipio);
            this.listenTo(this.municipios, 'sync', this.syncMunicipios);

            this.colonias = new ColoniasCollection();
            this.listenTo(this.colonias, 'add', this.agregarColonia);
            this.listenTo(this.colonias, 'sync', this.syncColonias);

            this.paises.fetch();
        },

        render: function() {
            this.$el.html(this.template());
<<<<<<< HEAD
            this.$('.modal-dialog').modal('show');
            return this;
=======
            this.$('#colonia-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarPais: function(modelo) {
            $('#input-pais').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncPaises: function() {
            $('#input-pais').change();
        },

        agregarEstado: function(modelo) {
            $('#input-estado').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncEstados: function() {
            $('#input-estado').change();
        },

        agregarMunicipio: function(modelo) {
            $('#input-municipio').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncMunicipios: function() {
            $('#input-municipio').change();
        },

        agregarColonia: function(modelo) {
            $('#input-colonia').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre')
            }));
        },

        syncColonias: function() {
            $('#input-colonia').change();
        },

        changePais: function(event) {
            var modelo = this.paises.get($(event.target).val());
            $('#input-estado').html('');
            this.estados.setPais(modelo);
            this.estados.fetch();
        },

        changeEstado: function(event) {
            var modelo = this.estados.get($(event.target).val());
            $('#input-municipio').html('');
            this.municipios.setEstado(modelo);
            this.municipios.fetch();
        },

        changeMunicipio: function(event) {
            var modelo = this.municipios.get($(event.target).val());
            $('#input-colonia').html('');
            this.colonias.setMunicipio(modelo);
            this.colonias.fetch();
        },

        clickAceptar: function(event) {
            var coloniaId = $('#input-colonia').val();
            if (coloniaId == 'undefined' || coloniaId == ''){
                this.$('.alert-danger').show();
                this.$('.alert-danger').html('Debe seleccionar una colonia');
            } else {
                var modelo = this.colonias.get(coloniaId);
                this.callbackAceptar(modelo);
                this.$('#colonia-dialog').modal('hide');
            }
>>>>>>> b0992ba2eb46b6de737aa333280036ae9ad7a8f7
        }
	});

	return MainColoniaAdminView;

});