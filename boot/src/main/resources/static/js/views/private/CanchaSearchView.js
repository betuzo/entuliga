define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'collections/CanchasCollection',
	'text!templates/private/tplCanchaSearch.html'
], function($, bootstrap, BaseView, CanchasCollection, tplCanchaSearch){

	var CanchaSearchView = BaseView.extend({
	    el: '#modal-cancha-search',
        template: _.template(tplCanchaSearch),

        events: {
            'click #btn-buscar': 'clickBuscar',
            'click #btn-aceptar': 'clickAceptar',
            'click .list-group-item': 'clickItemSearch'
        },

        initialize: function(opts) {
            this.callbackAceptar = opts.callbackAceptar;
            this.model = opts.modelo;
            this.render();
            this.canchas = new CanchasCollection();
            this.listenTo(this.canchas, 'add', this.agregarCancha);
            this.listenTo(this.canchas, 'sync', this.syncCanchas);
        },

        render: function() {
            this.$el.html(this.template());
            this.$('#cancha-search-dialog').modal('show');
            this.$('.alert-danger').hide();
        },

        agregarCancha: function(modelo) {

        },

        syncCanchas: function() {
            $("#result-search").html('');
            this.canchas.forEach(function(cancha) {
                var liitem = "<a class='canchas list-group-item' id='" + cancha.get('id') + "'>" + cancha.get('nombre') +"</a>";
                $(liitem).appendTo("#result-search");
            });
        },

        clickBuscar: function(event) {
            var textName = $('#text-busqueda').val();
            var idTorneo = this.model.get('id');
            if (textName != '' && textName != 'undefined') {
                $("#result-search").html('');
                this.canchas.setTipo('like');
                this.canchas.setCriterio(textName);
                this.canchas.fetch({
                    data: { tipo: 'notInTorneoAndContainName', idTorneo: idTorneo },
                    processData: true
                });
            }
        },

        clickAceptar: function(event) {
            var canchaId = $('.canchas.list-group-item.active').attr('id');
            if (canchaId == 'undefined' || canchaId == undefined || canchaId == ''){
                this.$('.alert-danger').show();
                this.$('.alert-danger').html('Debe seleccionar una cancha');
            } else {
                var modelo = this.canchas.get(canchaId);
                this.callbackAceptar(modelo);
            }
        },

        clickItemSearch: function(event) {
            $('.canchas.list-group-item').removeClass('active');
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

	return CanchaSearchView;

});