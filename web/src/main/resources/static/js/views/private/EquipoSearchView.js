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
            'click #btn-aceptar': 'clickAceptar',
            'click .list-group-item': 'clickItemSearch'
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = opts.modelo;
            this.render();
            this.equipos = new EquiposCollection();
            this.listenTo(this.equipos, 'add', this.agregarEquipo);
            this.listenTo(this.equipos, 'sync', this.syncEquipos);
        },

        render: function() {
            this.$el.html(this.template());
            this.$('#equipo-search-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarEquipo: function(modelo) {

        },

        syncEquipos: function() {
            $("#result-search").html('');
            this.equipos.forEach(function(equipo) {
                var liitem = "<a class='equipos list-group-item' id='" + equipo.get('id') + "'>" + equipo.get('nombre') +"</a>";
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
            var equipoId = $('.equipos.list-group-item.active').attr('id');
            if (equipoId == 'undefined' || equipoId == undefined || equipoId == ''){
                this.$('.alert-danger').show();
                this.$('.alert-danger').html('Debe seleccionar un equipo');
            } else {
                var modelo = this.equipos.get(equipoId);
                this.callbackAceptar(modelo);
            }
        },

        clickItemSearch: function(event) {
            $('.equipos.list-group-item').removeClass('active');
            $(event.target).addClass('active');
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