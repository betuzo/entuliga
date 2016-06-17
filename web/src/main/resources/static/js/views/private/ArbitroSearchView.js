define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/ArbitrosCollection',
	'text!templates/private/tplArbitroSearch.html'
], function($, bootstrap, BaseView, ArbitrosCollection, tplArbitroSearch){

	var ArbitroSearchView = BaseView.extend({
	    el: '#modal-arbitro-search',
        template: _.template(tplArbitroSearch),

        events: {
            'keydown #text-busqueda': 'changeBuscar',
            'click #btn-aceptar'    : 'clickAceptar',
            'click .list-group-item': 'clickItemSearch'
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = opts.modelo;
            this.render();
            this.arbitros = new ArbitrosCollection();
            this.listenTo(this.arbitros, 'add', this.agregarArbitro);
            this.listenTo(this.arbitros, 'sync', this.syncArbitros);
        },

        render: function() {
            this.$el.html(this.template());
            this.$('#arbitro-search-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarArbitro: function(modelo) {

        },

        syncArbitros: function() {
            $("#result-search").html('');
            this.arbitros.forEach(function(arbitro) {
                var liitem = "<a class='arbitros list-group-item' id='" + arbitro.get('id') + "'>" + arbitro.get('nombre') +"</a>";
                $(liitem).appendTo("#result-search");
            });
        },

        changeBuscar: function(event) {
            var textName = $('#text-busqueda').val();
            var idTorneo = this.model.get('id');
            if (textName != '' && textName != 'undefined' && textName.length > 2) {
                $("#result-search").html('');
                this.arbitros.setTipo('like');
                this.arbitros.setCriterio(textName);
                this.arbitros.fetch({
                    data: { tipo: 'notInTorneoAndContainName', idTorneo: idTorneo },
                    processData: true
                });
            }
        },

        clickAceptar: function(event) {
            var arbitroId = $('.arbitros.list-group-item.active').attr('id');
            if (arbitroId == 'undefined' || arbitroId == undefined || arbitroId == ''){
                this.$('.alert-danger').show();
                this.$('.alert-danger').html('Debe seleccionar un arbitro');
            } else {
                var modelo = this.arbitros.get(arbitroId);
                this.callbackAceptar(modelo);
            }
        },

        clickItemSearch: function(event) {
            $('.arbitros.list-group-item').removeClass('active');
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

	return ArbitroSearchView;

});