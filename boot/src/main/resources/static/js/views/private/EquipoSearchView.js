define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/EquiposCollection',
	'text!templates/private/tplEquipoSearch.html'
], function($, bootstrap, BaseView, EquiposCollection, tplEquipoSearch){

	var EquipoSearchView = BaseView.extend({
	    el: '#modal-equipo-search',
        template: _.template(tplEquipoSearch),

        events: {
            'click #btn-buscar': 'clickBuscar',
            'click #btn-aceptar': 'clickAceptar'
        },

        initialize: function(callbackAceptar) {
            this.callbackAceptar = callbackAceptar;
            this.render();
            this.equipos = new EquiposCollection();
            this.listenTo(this.equipos, 'add', this.agregarEquipo);
            this.listenTo(this.equipos, 'sync', this.syncEquipos);
        },

        render: function() {
            this.$el.html(this.template());
            this.$('#equipo-search-dialog').modal('show');
        },

        agregarEquipo: function(modelo) {

        },

        syncEquipos: function() {
            this.equipos.forEach(function(equipo) {
                var liitem = "<li class='list-group-item' value='" + equipo.get('id') + "'>" + equipo.get('nombre') +"</li>";
                $(liitem).appendTo("#result-search");
            });
        },

        clickBuscar: function(event) {
            var textName = $('#text-busqueda').val();
            var idTorneo = this.model.get('id');
            if (textName != '' && textName != 'undefined') {
                $("#result-search").html('');
                this.equipos.setTipo('like');
                this.equipos.setCriterio(textName);
                this.equipos.fetch({
                    data: { tipo: 'notInTorneoAndContainName', idTorneo: idTorneo },
                    processData: true
                });
            }
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
        }
	});

	return EquipoSearchView;

});